package irille.Dao;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;

import irille.Aops.Caches;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.RFQ.RFQConsult;
import irille.action.dataimport.util.StringUtil;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.DbPool;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.pdt.view.PrivatePdtView;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtAttrLineDAO;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtTieredPricing;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrFavoritesDAO;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.pdt.PdtProductBaseInfoView;
import irille.view.pdt.PdtProductCatView;
import irille.view.pdt.PdtSearchView;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static irille.core.sys.Sys.OYn.YES;
import static java.util.stream.Collectors.toList;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/7 Time: 14:47 */
public class PdtProductDao {
  @Inject private PdtProductDao pdtProductDao;
  private static final Logger logger = LoggerFactory.getLogger(PdtProductDao.class);

  /**
   * * 首页新品
   *
   * @author lijie@shoestp.cn
   * @param
   * @return List
   * @date 2018/7/23 14:38
   */
  @Caches
  public List getNewProductsList(int start, int limit, String where) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE, PdtProduct.T.CUR_PRICE)
        .FROM(PdtProduct.class)
        .limit(start, limit);
    productRules(query);
    newProduct(query);
    if (where != null && where.length() > 0) {
      query.WHERE(PdtProduct.T.CATEGORY, "=?", where);
    }
    return query.queryMaps();
  }

  /**
   * * 首页新品 Version 2 版本 带收藏信息
   *
   * @author lijie@shoestp.cn
   * @param
   * @return List
   * @date 2018/7/23 14:38
   */
  @Caches
  public List getNewProductsAndFavoritesInfoList(int start, int limit, Integer pubPkey) {
    SQL query = new SQL();
    SQL childrenQuery = new SQL();
    childrenQuery
        .SELECT(UsrFavorites.T.PKEY)
        .FROM(UsrFavorites.class)
        .WHERE(UsrFavorites.T.PURCHASE, "=?", pubPkey)
        .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY);
    query
        .SELECT(PdtProduct.T.PKEY, "id")
        .SELECT(PdtProduct.T.NAME, PdtProduct.T.MIN_OQ)
        .SELECT(PdtProduct.T.PICTURE, "image")
        .SELECT(PdtProduct.T.CUR_PRICE, "price")
        .SELECT(childrenQuery, "ismyfavorite")
        .FROM(PdtProduct.class)
        .LIMIT(start, limit);
    productRules(query);
    newProduct(query);
    return Query.sql(query).queryMaps();
  }

  public Integer getProductSupId(int pdtId) {
    BeanQuery beanQuery = new BeanQuery();
    PdtProduct result =
        (PdtProduct)
            beanQuery
                .SELECT(PdtProduct.T.PKEY, PdtProduct.T.SUPPLIER)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.PKEY, "=?", pdtId)
                .query(PdtProduct.class);
    if (result != null) return result.getSupplier();
    return -1;
  }

  /**
   * * 商品列表页 热榜 div #what_hot
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 14:47
   */
  public List<Map> getProductsIndexHot(int start, int limit) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE, PdtProduct.T.CUR_PRICE)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.IS_HOT, "=?", YES)
        .limit(start, limit);
    productRules(query);
    query.ORDER_BY(PdtProduct.T.MY_ORDER, "desc");
    return query.queryMaps();
  }

  public Map getProductList(PdtProductView pdtProductView) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.MIN_OQ,
            PdtProduct.T.PICTURE,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.Favorite_Count)
        .FROM(PdtProduct.class)
        .limit(pdtProductView.getPage().getStart(), pdtProductView.getPage().getLimit());
    query.ORDER_BY(UsrSupplier.T.IS_AUTH, "desc");
    productRules(query);
    if (pdtProductView.getCategory() > -1) {
      List list = getCatsNodeByCatId(pdtProductView.getCategory());
      if (list != null) query.WHERE(PdtProduct.T.CATEGORY, "in(" + String.join(",", list) + ")");
    }
    // TODO
    /**
     * @Description: 暂时折中方案, 性能差
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/23 14:42
     */
    if (pdtProductView.getSpec() != null) {
      for (String string : pdtProductView.getSpec().split(",")) {
        if (string.length() > 1) {
          query.WHERE("find_in_set( ?, norm_attr )", string);
        }
      }
    }
    /**
     * 根据供应商Id 查询
     *
     * @author lijie@shoestp.cn @Description:
     * @date 2018/8/3 9:26
     */
    if (pdtProductView.getSupplierId() > -1) {
      query.WHERE(PdtProduct.T.SUPPLIER, "=?", pdtProductView.getSupplierId());
    }
    /**
     * 根据张伟，添加商品标题关键词查询
     *
     * @author lijie@shoestp.cn @Description:
     * @date 2018/8/6 16:45
     */
    if (pdtProductView.getKeyword() != null && pdtProductView.getKeyword().length() > 0) {
      try {
        query.WHERE(
            PdtProduct.T.NAME,
            "like ?",
            "%" + URLDecoder.decode(pdtProductView.getKeyword(), "utf-8") + "%");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    /**
     * @Description: 根据李逸超要求添加 PRODUCT_TYPE字段判断
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/20 11:08
     */
    if (pdtProductView.getProductType() != null) {
      query.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", pdtProductView.getProductType());
    }

    if (pdtProductView.getOnlyFld() != null) {
      switch (pdtProductView.getOnlyFld()) {
        case Hot:
          {
            query.WHERE(PdtProduct.T.IS_HOT, "=?", Sys.OYn.YES);
          }
          break;
        case New:
          {
            newProduct(query);
          }
          break;
      }
    }
    // 缺少权重计算，待优化 策略模式
    if (pdtProductView.getFlds() != null) {
      for (String fld : pdtProductView.getFlds()) {
        try {
          PdtProduct.ProductsIndexOrderByType orderByType =
              PdtProduct.ProductsIndexOrderByType.valueOf(
                  URLDecoder.decode(fld, "utf-8").replace(" ", ""));
          if (orderByType != null) {
            if (pdtProductView.isOrder()) {
              if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                newProduct(query, true);
              } else {
                for (IEnumFld iEnumFld : orderByType.getFld()) {
                  query.ORDER_BY(iEnumFld, "desc");
                }
              }
            } else {
              if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                newProduct(query, false);
              } else {
                for (IEnumFld iEnumFld : orderByType.getFld()) {
                  query.ORDER_BY(iEnumFld, "asc");
                }
              }
            }
            String pageWhere = pdtProductView.getPage().getWhere();
            if (pageWhere != null) {
              Pattern pattern = Pattern.compile("([0-9]{0,})-([0-9]{0,})");
              Matcher matcher = pattern.matcher(pageWhere);
              if (matcher.matches()) {
                if (matcher.group(1).length() > 0) {
                  query.WHERE(PdtProduct.T.CUR_PRICE, ">?", matcher.group(1));
                }
                if (matcher.group(2).length() > 0) {
                  query.WHERE(PdtProduct.T.CUR_PRICE, "<?", matcher.group(2));
                }
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    Map result = new HashMap();
    List<Map> list = query.queryMaps();
    list =
        list.stream()
            .map(
                o -> {
                  o.put(
                      "rewrite",
                      SEOUtils.getPdtProductTitle(
                          Integer.parseInt(String.valueOf(o.get("pkey"))),
                          String.valueOf(o.get("name"))));
                  return o;
                })
            .collect(toList());
    result.put("items", SetBeans.setList(list, PdtProductBaseInfoView.class));
    result.put("total", query.queryCount());
    if (pdtProductView.getCategory() > -1) {
      result.put("breadcrumbnav", getBreadcrumbNav(pdtProductView.getCategory()));
    }
    return result;
  }

  public Map getProductListV2(PdtProductView pdtProductView) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.PICTURE,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.MIN_OQ,
            PdtProduct.T.Favorite_Count)
        .FROM(PdtProduct.class)
        .limit(pdtProductView.getPage().getStart(), pdtProductView.getPage().getLimit());
    query.ORDER_BY(UsrSupplier.T.IS_AUTH, "desc");
    productRules(query);
    if (pdtProductView.getCategory() > -1) {
      List list = getCatsNodeByCatId(pdtProductView.getCategory());
      if (list != null) query.WHERE(PdtProduct.T.CATEGORY, "in(" + String.join(",", list) + ")");
    }
    // TODO
    /**
     * @Description: 暂时折中方案, 性能差
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/23 14:42
     */
    if (pdtProductView.getSpec() != null) {
      for (String string : pdtProductView.getSpec().split(",")) {
        if (string.length() > 1) {
          query.WHERE("find_in_set( ?, norm_attr )", string);
        }
      }
    }
    /**
     * 根据供应商名称查询
     *
     * @author lijie@shoestp.cn @Description:
     * @date 2018/8/3 9:26
     */
    if (pdtProductView.getSupplierId() > -1) {
      query.WHERE(PdtProduct.T.SUPPLIER, "=?", pdtProductView.getSupplierId());
    }
    if (pdtProductView.getKeyword() != null && pdtProductView.getKeyword().length() > 0) {
      // query.LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER,
      // UsrSupplier.T.PKEY);
      try {
        query.WHERE(
            UsrSupplier.T.NAME,
            "like ?",
            "%" + URLDecoder.decode(pdtProductView.getKeyword(), "utf-8").replace(" ", "") + "%");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    /**
     * @Description: 根据李逸超要求添加 PRODUCT_TYPE字段判断
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/20 11:08
     */
    if (pdtProductView.getProductType() != null) {
      query.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", pdtProductView.getProductType());
    }

    if (pdtProductView.getOnlyFld() != null) {
      switch (pdtProductView.getOnlyFld()) {
        case Hot:
          {
            query.WHERE(PdtProduct.T.IS_HOT, "=?", Sys.OYn.YES);
          }
          break;
        case New:
          {
            newProduct(query);
          }
          break;
      }
    }
    // 缺少权重计算，待优化 策略模式
    if (pdtProductView.getFlds() != null) {
      for (String fld : pdtProductView.getFlds()) {
        try {
          PdtProduct.ProductsIndexOrderByType orderByType =
              PdtProduct.ProductsIndexOrderByType.valueOf(
                  URLDecoder.decode(fld, "utf-8").replace(" ", ""));
          if (orderByType != null) {
            if (pdtProductView.isOrder()) {
              if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                newProduct(query, true);
              } else {
                for (IEnumFld iEnumFld : orderByType.getFld()) {
                  query.ORDER_BY(iEnumFld, "desc");
                }
              }
            } else {
              if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                newProduct(query, false);
              } else {
                for (IEnumFld iEnumFld : orderByType.getFld()) {
                  query.ORDER_BY(iEnumFld, "asc");
                }
              }
            }
            String pageWhere = pdtProductView.getPage().getWhere();
            if (pageWhere != null) {
              Pattern pattern = Pattern.compile("([0-9]{0,})-([0-9]{0,})");
              Matcher matcher = pattern.matcher(pageWhere);
              if (matcher.matches()) {
                if (matcher.group(1).length() > 0) {
                  query.WHERE(PdtProduct.T.CUR_PRICE, ">?", matcher.group(1));
                }
                if (matcher.group(2).length() > 0) {
                  query.WHERE(PdtProduct.T.CUR_PRICE, "<?", matcher.group(2));
                }
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    Map result = new HashMap();
    List<Map> list = query.queryMaps();
    list =
        list.stream()
            .map(
                o -> {
                  o.put(
                      "rewrite",
                      SEOUtils.getPdtProductTitle(
                          Integer.parseInt(String.valueOf(o.get("pkey"))),
                          String.valueOf(o.get("name"))));
                  return o;
                })
            .collect(toList());
    result.put("items", SetBeans.setList(list, PdtProductBaseInfoView.class));
    result.put("total", query.queryCount());
    if (pdtProductView.getCategory() > -1) {
      result.put("breadcrumbnav", getBreadcrumbNav(pdtProductView.getCategory()));
    }
    return result;
  }

  /**
   * @Description: 面包屑导航
   *
   * @author lijie@shoestp.cn
   * @date 2018/7/27 14:01
   */
  public List getBreadcrumbNav(long id) {
    FormaterSql sql = FormaterSql.build();
    List nav = new ArrayList();
    Long i = id;
    do {
      sql.clean().select(PdtCat.T.PKEY, PdtCat.T.NAME, PdtCat.T.CATEGORY_UP).eq(PdtCat.T.PKEY);
      Map map = sql.castMap(BeanBase.queryOneRow(sql.buildSql(), i));
      if (map != null) {
        Object t = map.get(PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField());
        nav.add(0, map);
        if (t == null) {
          break;
        }
        i = Long.valueOf(String.valueOf(t));
      } else {
        break;
      }
    } while (true);
    return nav;
  }

  /**
   * @Description: 获取商品节点下方所有子节点
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/21 18:05
   */
  public List<Integer> getCatsNodeByCatId(int id) {
    if (id < 1) {
      return null;
    }
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PdtCat.T.PKEY)
        .FROM(PdtCat.class)
        .WHERE(PdtCat.T.CATEGORY_UP, "=?", id)
        .WHERE(PdtCat.T.ENABLED, " =? ", OEnabled.TRUE)
        .WHERE(PdtCat.T.DELETED, " =? ", OYn.NO);
    List<PdtCat> l = new ArrayList();
    List<PdtCat> tList = query.queryList();
    List ttList = new ArrayList();
    l.addAll(tList);
    List<Integer> result = new ArrayList();
    result.add(id);
    do {
      ttList.clear();
      for (PdtCat objects : tList) {
        ttList.addAll(
            new BeanQuery()
                .SELECT(PdtCat.T.PKEY)
                .FROM(PdtCat.class)
                .WHERE(PdtCat.T.CATEGORY_UP, "=?", objects.getPkey())
                .WHERE(PdtCat.T.ENABLED, " =? ", OEnabled.TRUE)
                .WHERE(PdtCat.T.DELETED, " =? ", OYn.NO)
                .queryList());
      }
      l.addAll(ttList);
      tList.clear();
      tList.addAll(ttList);
      if (ttList.size() < 1) break;
    } while (true);
    for (PdtCat o : l) {
      if (o.getPkey() > 0) {
        result.add(o.getPkey());
      }
    }
    return result;
  }

  public Page getProductListManage(
      String name,
      String number,
      Integer supplierId,
      int cat,
      int start,
      int limit,
      Integer search,
      String data) {
    SQL sql1 = new SQL();
    sql1.SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.SKU,
            PdtProduct.T.CODE,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.PICTURE,
            PdtProduct.T.PRODUCT_TYPE,
            PdtProduct.T.UPDATE_TIME,
            PdtProduct.T.STATE,
            PdtProduct.T.IS_VERIFY)
        .SELECT(PdtProduct.T.CATEGORY, "cat")
        .SELECT(PdtCat.T.NAME, "category")
        .SELECT(UsrProductCategory.T.NAME, "categoryDiy")
        .SELECT(O2O_Product.T.JOIN_INFO_ID)
        .FROM(PdtProduct.class)
        .LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY)
        .LEFT_JOIN(UsrProductCategory.class, PdtProduct.T.CATEGORY_DIY, UsrProductCategory.T.PKEY)
        .LEFT_JOIN(O2O_PrivateExpoPdt.class, O2O_PrivateExpoPdt.T.PDT_ID, PdtProduct.T.PKEY)
        // .LEFT_JOIN(O2O_Product.class, O2O_Product.T.PRODUCT_ID, PdtProduct.T.PKEY)
        .LEFT_JOIN(
            "(select * from o2_o__product ORDER BY updated_time DESC limit 1) O2O_Product  ON O2O_Product.product_id = PdtProduct.pkey ")
        .WHERE(name != null && name.length() > 0, PdtProduct.T.NAME, "like ?", "%" + name + "%")
        .WHERE(
            number != null && number.length() > 0, PdtProduct.T.CODE, "like ?", "%" + number + "%")
        .WHERE(PdtProduct.T.SUPPLIER, "=?", supplierId)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, " <> ?", Pdt.OProductType.GROUP.getLine().getKey())
        .WHERE(PdtProduct.T.PRODUCT_TYPE, " <> ?", Pdt.OProductType.GATHER.getLine().getKey())
        .WHERE(PdtProduct.T.STATE, "<>?", Pdt.OState.DELETE.getLine().getKey())
        .WHERE(PdtProduct.T.STATE, "<>?", Pdt.OState.MERCHANTDEL.getLine().getKey())
        .WHERE(PdtProduct.T.SOLD_TIME_B, " IS NOT NULL ")
        .WHERE(
            "("
                + PdtProduct.class.getSimpleName()
                + "."
                + PdtProduct.T.STATE.getFld().getCodeSqlField()
                + " = ? OR "
                + PdtProduct.class.getSimpleName()
                + "."
                + PdtProduct.T.STATE.getFld().getCodeSqlField()
                + " =? )",
            Pdt.OState.OFF.getLine().getKey(),
            Pdt.OState.ON.getLine().getKey())
        .ORDER_BY(PdtProduct.T.UPDATE_TIME, "desc");

    if (null != data) {
      sql1.WHERE(
          "("
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.CODE.getFld().getCodeSqlField()
              + " like ? OR "
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.NAME.getFld().getCodeSqlField()
              + " like ? OR "
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.SKU.getFld().getCodeSqlField()
              + " like ? )",
          "%" + data + "%",
          "%" + data + "%",
          "%" + data + "%");
    }
    if (search != null) {
      switch (search) {
        case 0:
          sql1.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL.getLine().getKey());
          break;
        case 2:
          sql1.WHERE(
              PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.PrivateExpo.getLine().getKey());
          break;
        case 3:
          sql1.WHERE(
              "(("
                  + O2O_PrivateExpoPdt.class.getSimpleName()
                  + "."
                  + O2O_PrivateExpoPdt.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + "=? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ) OR ("
                  + O2O_Product.class.getSimpleName()
                  + "."
                  + O2O_Product.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =?) OR ("
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ))",
              O2O_PrivateExpoPdtStatus.PASS.getLine().getKey(),
              Pdt.OProductType.PrivateExpo.getLine().getKey(),
              O2O_ProductStatus.PASS.getLine().getKey(),
              Pdt.OProductType.O2O.getLine().getKey(),
              Pdt.OAppr.PASS.getLine().getKey(),
              Pdt.OProductType.GENERAL.getLine().getKey());
          break;
        case 4:
          sql1.WHERE(
              "(("
                  + O2O_PrivateExpoPdt.class.getSimpleName()
                  + "."
                  + O2O_PrivateExpoPdt.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + "=? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ) OR ("
                  + O2O_Product.class.getSimpleName()
                  + "."
                  + O2O_Product.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =?) OR ("
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ))",
              O2O_PrivateExpoPdtStatus.Failed.getLine().getKey(),
              Pdt.OProductType.PrivateExpo.getLine().getKey(),
              O2O_ProductStatus.Failed.getLine().getKey(),
              Pdt.OProductType.O2O.getLine().getKey(),
              Pdt.OAppr.Failed.getLine().getKey(),
              Pdt.OProductType.GENERAL.getLine().getKey());
          // sql1.WHERE(
          // "("
          // + O2O_PrivateExpoPdt.class.getSimpleName()
          // + "."
          // + O2O_PrivateExpoPdt.T.VERIFY_STATUS.getFld().getCodeSqlField()
          // + "=? OR "
          // + O2O_Product.class.getSimpleName()
          // + "."
          // + O2O_Product.T.VERIFY_STATUS.getFld().getCodeSqlField()
          // + " =?)",
          // O2O_PrivateExpoPdtStatus.Failed.getLine().getKey(),
          // O2O_ProductStatus.Failed.getLine().getKey());
          break;
        case 5:
          sql1.WHERE(
              "(("
                  + O2O_PrivateExpoPdt.class.getSimpleName()
                  + "."
                  + O2O_PrivateExpoPdt.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + "=? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ) OR ("
                  + O2O_Product.class.getSimpleName()
                  + "."
                  + O2O_Product.T.VERIFY_STATUS.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =?) OR ("
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
                  + " =? AND "
                  + PdtProduct.class.getSimpleName()
                  + "."
                  + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                  + " =? ))",
              O2O_PrivateExpoPdtStatus._DEFAULT.getLine().getKey(),
              Pdt.OProductType.PrivateExpo.getLine().getKey(),
              O2O_ProductStatus._DEFAULT.getLine().getKey(),
              Pdt.OProductType.O2O.getLine().getKey(),
              Pdt.OAppr._DEFAULT.getLine().getKey(),
              Pdt.OProductType.GENERAL.getLine().getKey());
          break;
      }
    }
    if (cat > 0) {
      List list =
          (List)
              getCatsNodeByCatId(cat).stream()
                  .map(
                      o -> {
                        return String.valueOf(o);
                      })
                  .collect(toList());
      sql1.WHERE(cat != 0, PdtProduct.T.CATEGORY, "in (" + String.join(",", list) + ")");
    }
    Integer totalCount = Query.sql(sql1).queryCount();
    // start = (start - 1 > -1 ? start - 1 : 0);
    // q.limit(start * limit, limit).queryMaps();
    sql1.LIMIT(start, limit);
    List<Map<String, Object>> list = Query.sql(sql1).queryMaps();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    Page page =
        new Page(
            list.stream()
                .map(
                    o -> {
                      for (Pdt.OProductType value : Pdt.OProductType.values()) {
                        if (value.getLine().getKey()
                            == (Byte) o.get(PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField())) {
                          o.put("O2OSort", value.getLine().getName());
                        }
                      }
                      if ((Byte) o.get(PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField())
                          == Pdt.OProductType.PrivateExpo.getLine().getKey()) {
                        O2O_PrivateExpoPdt privateExpoPdt =
                            O2O_PrivateExpoPdt.loadUniquePdt_Id(
                                false,
                                (Integer) o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()));
                        for (O2O_PrivateExpoPdtStatus value : O2O_PrivateExpoPdtStatus.values()) {
                          if (value.getLine().getKey() == privateExpoPdt.getVerifyStatus()) {
                            o.put("status", value.getLine().getName());
                          }
                        }
                        if (privateExpoPdt.getStatus().equals((byte) 2)) {
                          o.put("upANDlow", 1);
                        } else {
                          o.put("upANDlow", 0);
                        }
                      } else if ((Byte) o.get(PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField())
                          == Pdt.OProductType.O2O.getLine().getKey()) {
                        Integer joinInfoId =
                            GetValue.get(o, O2O_Product.T.JOIN_INFO_ID, Integer.class, null);
                        O2O_Product p =
                            O2O_Product.chkUniqueProduct_id_join_info_id(
                                true,
                                GetValue.get(o, PdtProduct.T.PKEY, Integer.class, null),
                                joinInfoId);
                        if (null != p) {
                          SQL sql =
                              new SQL() {
                                {
                                  SELECT(O2O_Product.class)
                                      .FROM(O2O_Product.class)
                                      .WHERE(O2O_Product.T.PRODUCT_ID, "=?", p.getProductId());
                                }
                              };
                          List<O2O_Product> o2oList = Query.sql(sql).queryList(O2O_Product.class);
                          boolean flag = false;
                          for (O2O_Product o2o : o2oList) {
                            if (!o2o.getStatus()
                                .equals(O2O_ProductStatus.PASS.getLine().getKey())) {
                              flag = true;
                              break;
                            }
                          }
                          if (!flag) {
                            o.put("o2o_lower", 1); // o2o商品已下架,可编辑状态
                          }
                          o.put("status", p.gtVerifyStatus().getLine().getName());
                        } else {
                          o.put("status", "");
                          logger.error(
                              String.format(
                                  "存在脏数据,商品Id:%d",
                                  GetValue.get(o, PdtProduct.T.PKEY, Integer.class, -1)));
                        }
                      } else {
                        if ((byte) o.get(PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField())
                            == Pdt.OAppr.PASS.getLine().getKey()) {
                          o.put("status", "审核通过");
                        } else if ((byte) o.get(PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField())
                            == Pdt.OAppr.Failed.getLine().getKey()) {
                          o.put("status", "审核失败");
                        } else if ((byte) o.get(PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField())
                            == Pdt.OAppr._DEFAULT.getLine().getKey()) {
                          o.put("status", "待审核");
                        }
                        o.put(
                            "upANDlow",
                            (Byte) o.get(PdtProduct.T.STATE.getFld().getCodeSqlField()));
                      }
                      o.put(
                          "category",
                          translateUtil.getLanguage(
                              o.get("category"), PltConfigDAO.supplierLanguage(supplierId)));
                      o.put(
                          "rewrite",
                          SEOUtils.getPdtProductTitle(
                              Integer.valueOf(String.valueOf(o.get("pkey"))),
                              String.valueOf(o.get("name"))));
                      o.put(
                          "name",
                          translateUtil.getLanguage(
                              o.get("name"), PltConfigDAO.supplierLanguage(supplierId)));
                      o.put(
                          "categoryDiy",
                          translateUtil.getLanguage(
                              o.get("categoryDiy"), PltConfigDAO.supplierLanguage(supplierId)));
                      o.put("update_time", dateFormat.format(o.get("update_time")));
                      o.put("sku", (String) o.get(PdtProduct.T.SKU.getFld().getCodeSqlField()));
                      return o;
                    })
                .collect(toList()),
            start,
            limit,
            totalCount);
    return page;
  }

  @Caches
  public List<Map<String, Object>> getYouMayLike(int cat, Integer pubPkey) {
    SQL sql = new SQL();
    if (pubPkey != null) {
      SQL childrenQuery = new SQL();
      childrenQuery
          .SELECT(UsrFavorites.T.PKEY)
          .FROM(UsrFavorites.class)
          .WHERE(UsrFavorites.T.PURCHASE, "=?", pubPkey)
          .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY);
      sql.SELECT(childrenQuery, "isFavorite");
    }
    sql.SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.PICTURE,
            PdtProduct.T.MIN_OQ)
        .FROM(PdtProduct.class);
    if (cat > 0) {
      String pkeys = getYouMayLikeProd(cat);
      sql.WHERE(PdtProduct.T.PKEY, " in(" + pkeys + ") ");
    }
    productRules(sql);
    return irille.pub.bean.Query.sql(sql).queryMaps();
  }

  @Caches
  public List<Map<String, Object>> getYouMayLike(int start, int limit, int cat, Integer pubPkey) {
    SQL sql = new SQL();
    if (pubPkey != null) {
      SQL childrenQuery = new SQL();
      childrenQuery
          .SELECT(UsrFavorites.T.PKEY)
          .FROM(UsrFavorites.class)
          .WHERE(UsrFavorites.T.PURCHASE, "=?", pubPkey)
          .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY);
      sql.SELECT(childrenQuery, "isFavorite");
    }

//    子查询  查询o2o表的状态
    SQL childrensql = new SQL();
    childrensql
        .SELECT("count(1)")
        .FROM(O2O_Product.class)
        .WHERE(PdtProduct.T.PKEY, "=", O2O_Product.T.PRODUCT_ID)
        .WHERE(O2O_Product.T.VERIFY_STATUS, "=?", O2O_ProductStatus.PASS)
        .WHERE(
            O2O_Product.T.STATUS,
            "in(?,?,?)",
            O2O_ProductStatus._DEFAULT,
            O2O_ProductStatus.ON,
            O2O_ProductStatus.Failed);
    sql.SELECT(childrensql, "o2o")
        .SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.PICTURE,
            PdtProduct.T.PRODUCT_TYPE,
            PdtProduct.T.MIN_OQ)
        .FROM(PdtProduct.class);
    if (cat > 0) {
      String pkeys = getYouMayLikeProd(cat);
      sql.WHERE(PdtProduct.T.PKEY, " in(" + pkeys + ") ");
    }

    noProductTypeRules(sql)
        .LIMIT(start, limit)
        .WHERE(
            PdtProduct.T.PRODUCT_TYPE, "in (?,?)", Pdt.OProductType.O2O, Pdt.OProductType.GENERAL);
    return irille.pub.bean.Query.sql(sql).queryMaps();
  }

  /**
   * 随机获取猜你喜欢的产品Pkey
   *
   * @param cat
   * @return
   */
  private String getYouMayLikeProd(int cat) {
    SQL sql =
        new SQL() {
          {
            SELECT(PdtProduct.T.PKEY);
            FROM(PdtProduct.class);
            WHERE(PdtProduct.T.CATEGORY, "=?", cat);
            WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON.getLine().getKey());
            WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES);
            WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
            WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR);
            LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
            ORDER_BY(PdtProduct.T.PKEY, " DESC ");
          }
        };
    String pkeys = "";
    Set<Integer> prodPkeys = new HashSet<Integer>();
    List<PdtProduct> prodPkeyList = irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class);
    if (prodPkeyList.size() > 0) {
      Random rand = new Random();
      for (int i = 0; ; i++) {
        if (prodPkeys.size() > 4 || i > 100) {
          break;
        }
        prodPkeys.add(prodPkeyList.get(rand.nextInt(prodPkeyList.size())).getPkey());
      }
      for (Integer pkey : prodPkeys) {
        if (pkeys.equals("")) {
          pkeys += pkey;
        } else {
          pkeys += "," + pkey;
        }
      }
    }
    return pkeys;
  }

  /**
   * @Description: 商品显示统一逻辑 修改的时候,PdtProduct.ProductsIndexOrderByType 一起修改
   *
   * @date 2018/11/8 9:57
   * @author lijie@shoestp.cn
   */
  private BeanQuery productRules(BeanQuery query) {
    return query
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.IS_VERIFY, "=?", YES)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
        .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
        .LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
  }

  /**
   * @Description: 商品显示统一逻辑 修改的时候,PdtProduct.ProductsIndexOrderByType 一起修改
   *
   * @date 2018/11/8 9:57
   * @author lijie@shoestp.cn
   */
  private SQL productRules(SQL query) {
    return noProductTypeRules(query)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
  }

  private SQL noProductTypeRules(SQL query) {
    return query
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.IS_VERIFY, "=?", YES)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
        .WHERE(UsrSupplier.T.STORE_STATUS, "=?", Usr.SStatus.OPEN)
        .LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
  }

  /**
   * @Description: 新品排序规则..解决 排序规则碎片化问题 (未完成)
   *
   * @date 2018/11/8 11:04
   * @author lijie@shoestp.cn
   */
  private BeanQuery newProduct(BeanQuery query) {
    return newProduct(query, true);
  }

  private SQL newProduct(SQL query) {
    return newProduct(query, true);
  }

  private SQL newProduct(SQL query, boolean type) {
    return query
        .ORDER_BY(PdtProduct.T.PKEY, type ? "desc" : "asc")
        .ORDER_BY(PdtProduct.T.MY_ORDER, type ? "asc" : "desc")
        .ORDER_BY(PdtProduct.T.UPDATE_TIME, type ? "desc" : "asc");
  }

  private BeanQuery newProduct(BeanQuery query, boolean type) {
    return query
        .ORDER_BY(PdtProduct.T.PKEY, type ? "desc" : "asc")
        .ORDER_BY(PdtProduct.T.MY_ORDER, type ? "asc" : "desc")
        .ORDER_BY(PdtProduct.T.UPDATE_TIME, type ? "desc" : "asc");
  }

  /**
   * @Description: 根据分类获取该节点子节点-商城端-三级分类调用-过滤首页显示字段
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/22 21:59
   */
  public List<PdtProductCatView> getCatChildNodesByCatId(
      Integer integer, FldLanguage.Language language) {
    FormaterSql sql = FormaterSql.build();
    if (integer == null || integer == 0) {
      sql.isNull(PdtCat.T.CATEGORY_UP);
    } else {
      sql.eqAutoAnd(PdtCat.T.CATEGORY_UP, integer);
    }
    sql.eqAutoAnd(PdtCat.T.DISPLAY, 1);
    List<PdtProductCatView> result =
        PdtCat.list(PdtCat.class, sql.toWhereString(), false, sql.getParms()).stream()
            .filter(s -> !s.gtDeleted())
            .map(
                s -> {
                  PdtProductCatView pdtProductVueView = new PdtProductCatView();
                  translateUtil.getAutoTranslate(s, language);
                  pdtProductVueView.setValue(s.getPkey());
                  pdtProductVueView.setLabel(s.getName());
                  List l = getCatChildNodesByCatId(s.getPkey(), language);
                  if (l.size() > 0) {
                    pdtProductVueView.setChildren(l);
                  }
                  return pdtProductVueView;
                })
            .collect(Collectors.toList());
    return result;
  }

  /**
   * @Description: 根据分类获取该节点子节点-商家端-商品分类调用-没有过滤首页显示字段
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/22 21:59
   */
  public List<PdtProductCatView> getCatChildNodesByCatIdRemoveDisplay(
      Integer integer, FldLanguage.Language language) {
    FormaterSql sql = FormaterSql.build();
    if (integer == null || integer == 0) {
      sql.isNull(PdtCat.T.CATEGORY_UP);
    } else {
      sql.eqAutoAnd(PdtCat.T.CATEGORY_UP, integer);
    }
    List<PdtProductCatView> result =
        PdtCat.list(PdtCat.class, sql.toWhereString(), false, sql.getParms()).stream()
            .filter(s -> !s.gtDeleted())
            .map(
                s -> {
                  PdtProductCatView pdtProductVueView = new PdtProductCatView();
                  translateUtil.getAutoTranslate(s, language);
                  pdtProductVueView.setValue(s.getPkey());
                  pdtProductVueView.setLabel(s.getName());
                  List l = getCatChildNodesByCatId(s.getPkey(), language);
                  if (l.size() > 0) {
                    pdtProductVueView.setChildren(l);
                  }
                  return pdtProductVueView;
                })
            .collect(Collectors.toList());
    return result;
  }

  public List<PdtProduct> findAllByPkeys(String pkeys) {
    SQL sql = new SQL();
    sql.SELECT(PdtProduct.class)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.PKEY, " IN (" + pkeys + ") ");
    return Query.sql(sql).queryList(PdtProduct.class);
  }

  public Map getInquiryPdtInfo(Integer id) {
    SQL sql = new SQL();
    sql.SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.PICTURE,
            PdtProduct.T.PRODUCT_TYPE,
            PdtProduct.T.NAME,
            PdtProduct.T.MIN_OQ,
            UsrSupplier.T.ROLE,
            UsrSupplier.T.LOGO)
        .SELECT(UsrSupplier.T.PKEY, "supId")
        .SELECT(UsrSupplier.T.SHOW_NAME, "supName")
        .FROM(PdtProduct.class)
        .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, PdtProduct.T.SUPPLIER)
        .WHERE(PdtProduct.T.PKEY, "=?", id);
    return Query.sql(sql).queryMap();
  }

  /**
   * * 展会商品信息
   *
   * @author GS
   * @param
   * @return List
   * @date 2018/7/23 14:38
   */
  @Caches
  public List<PdtProduct> getExhibitionProductsList(int start, int limit, String where) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.NAME,
            PdtProduct.T.PICTURE,
            PdtProduct.T.CUR_PRICE,
            PdtProduct.T.PICTURE,
            PdtProduct.T.MIN_OQ)
        .FROM(PdtProduct.class)
        .limit(start, limit);
    if (where != null && where.length() > 0) {
      query.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", where);
      query.ORDER_BY(PdtProduct.T.SOLD_TIME_B, "desc");
    }
    return query.queryList();
  }

  private static final Integer men = 373; // PdtCat男鞋顶级分类pkey
  private static final Integer women = 380; // PdtCat女鞋顶级分类pkey
  private static final int inner = 137; // PdtAttr内部材料pkey
  private static final int season = 136; // PdtAttr适合季节pkey
  private static final int sole = 138; // PdtAttr鞋底材质pkey
  private static final int upper = 139; // PdtAttr鞋面材料pkey
  private static final int colsed = 152; // PdtAttr闭合方式pkey

  /**
   * @param orderfld
   * @param purchase 当前用户
   * @param curLanguage 当前语言
   * @param lose 1:需要按分类搜索 其他数字:不按分类搜索
   * @param pName 搜索字符
   * @param cate 产品分类
   * @param level 供应商等级
   * @param export 出口国家
   * @param mOrder 起订量
   * @param min 最小价格
   * @param max 最大价格
   * @param IsO2o 是否为查询o2o产品 1:为查询o2o产品 其他数字为普通产品
   * @param o2oAddress o2o产品所在活动地区
   * @param start 开始条数
   * @param limit 显示条数
   * @author xy v == 3搜索 -新PC商城端搜索功能
   */
  public Page searchPdtByQuery(
      String[] orderfld,
      UsrPurchase purchase,
      Integer supplier,
      FldLanguage.Language curLanguage,
      Integer lose,
      String pName,
      Integer cate,
      Integer level,
      String export,
      Integer mOrder,
      BigDecimal min,
      BigDecimal max,
      Integer IsO2o,
      String o2oAddress,
      Integer start,
      Integer limit) {
    List<O2O_Product> o2oProduct = null;
    Set<Integer> o2oPdtPkey = new HashSet<>();
    if (IsO2o != null && IsO2o == 1) {
      SQL o2oActivitySql = new SQL();
      o2oActivitySql.SELECT(O2O_Activity.T.PKEY);
      o2oActivitySql.FROM(O2O_Activity.class);
      if (StringUtil.hasValue(o2oAddress)) {
        String[] addressSplit = o2oAddress.split(",");
        for (String item : addressSplit) {
          o2oActivitySql.orWhere(O2O_Activity.T.ADDRESS, " =?", Integer.parseInt(item));
        }
      }
      Date now = new Date();
      o2oActivitySql.WHERE(O2O_Activity.T.START_DATE, "<?", now);
      o2oActivitySql.WHERE(O2O_Activity.T.END_DATE, ">?", now);

      List<Integer> activityList =
          Query.sql(o2oActivitySql).queryMaps().stream()
              .map(
                  bean -> {
                    return GetValue.get(bean, O2O_Activity.T.PKEY, Integer.class, null);
                  })
              .collect(Collectors.toList());
      if (activityList.isEmpty()) {
        return new Page(new ArrayList<>(), start, limit, 0);
      }
      SQL o2oPdtSql = new SQL();
      o2oPdtSql.SELECT(
          O2O_Product.T.PRODUCT_ID,
          O2O_Product.T.PRICE,
          O2O_Product.T.MIN_OQ,
          O2O_Product.T.UPDATED_TIME);
      o2oPdtSql.FROM(O2O_Product.class);
      o2oPdtSql.WHERE(
          "(" + O2O_Product.T.STATUS + " = ?  OR " + O2O_Product.T.STATUS + "= ? )",
          O2O_ProductStatus.ON.getLine().getKey(),
          O2O_ProductStatus.WAITOFF.getLine().getKey());
      o2oPdtSql.WHERE(
          O2O_Product.T.VERIFY_STATUS, " =? ", O2O_ProductStatus.PASS.getLine().getKey());
      StringBuffer buff = new StringBuffer("");
      for (int i = 0; i < activityList.size(); i++) {
        if (i == activityList.size() - 1) buff.append(activityList.get(i));
        else {
          buff.append(activityList.get(i));
          buff.append(",");
        }
      }
      if (!buff.toString().equals("")) {
        o2oPdtSql.WHERE(O2O_Product.T.ACTIVITY_ID, " in(" + buff.toString() + ") ");
      }
      o2oPdtSql.LIMIT(start, limit);
      o2oPdtSql.ORDER_BY(O2O_Product.T.UPDATED_TIME, "asc");
      o2oProduct = Query.sql(o2oPdtSql).queryList(O2O_Product.class);
      for (O2O_Product pdt : o2oProduct) {
        o2oPdtPkey.add(pdt.getProductId());
      }
      start = 0;
    }
    SQL sql = new SQL();
    sql.SELECT(PdtProduct.T.PKEY, "pdtPkey");
    sql.SELECT(PdtProduct.T.NAME, "pdtName");
    sql.SELECT(
        PdtProduct.T.CUR_PRICE,
        PdtProduct.T.MIN_OQ,
        PdtProduct.T.CATEGORY,
        PdtProduct.T.NORM_ATTR,
        PdtProduct.T.PRODUCT_TYPE,
        PdtProduct.T.PICTURE);
    sql.SELECT(UsrSupplier.T.PKEY, "supPkey");
    sql.SELECT(UsrSupplier.T.SHOW_NAME, "supName");
    sql.SELECT(UsrSupplier.T.STATUS, UsrSupplier.T.AUTH_TIME);
    sql.SELECT(PltCountry.T.NAME, "pltCountry");
    sql.SELECT(PltProvince.T.NAME, "pltProvince");
    sql.SELECT(PdtCat.T.PKEY, "pdtCatPkey");
    sql.SELECT(PdtCat.T.NAME, "pdtCatName");
    sql.SELECT(PdtCat.T.CATEGORY_UP, "pdtCatUp");
    sql.FROM(PdtProduct.class);
    sql.LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
    sql.LEFT_JOIN(PltCountry.class, UsrSupplier.T.COUNTRY, PltCountry.T.PKEY);
    sql.LEFT_JOIN(PltProvince.class, UsrSupplier.T.PROVINCE, PltProvince.T.PKEY);
    sql.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
    sql.WHERE(PdtProduct.T.IS_VERIFY, " =? ", Sys.OYn.YES.getLine().getKey());
    sql.WHERE(PdtProduct.T.STATE, " =? ", Pdt.OState.ON.getLine().getKey());
    sql.WHERE(supplier != null, PdtProduct.T.SUPPLIER, "=?", supplier);
    /*
     * sql.WHERE(PdtProduct.T.PRODUCT_TYPE,
     * " =? ",Pdt.OProductType.GENERAL.getLine().getKey())
     * .orWhere(PdtProduct.T.PRODUCT_TYPE, " =? ",
     * Pdt.OProductType.O2O.getLine().getKey());
     */
    if (IsO2o != null && IsO2o.equals(1)) {
      sql.WHERE("PdtProduct.product_type =?", Pdt.OProductType.O2O);
    } else {
      sql.WHERE(
          " ( PdtProduct.product_type =? OR PdtProduct.product_type =?)",
          Pdt.OProductType.GENERAL,
          Pdt.OProductType.O2O);
    }
    if (orderfld != null && orderfld.length > 0) {
      switch (orderfld[0]) {
        case "MostPopular":
          {
            sql.WHERE(PdtProduct.T.IS_HOT, "=?", Sys.OYn.YES);
          }
          break;
        case "Sales":
          {
            sql.ORDER_BY(PdtProduct.T.SALES, "Desc");
          }
          break;
      }
    }
    if (lose != null && lose == 1)
      if (cate != null && cate > 0) {
        List<Integer> cPkeys = pdtProductDao.getCatsNodeByCatId(cate);
        String pkeys = "";
        for (int i = 0; i < cPkeys.size(); i++) {
          if (i != cPkeys.size() - 1) {
            pkeys += cPkeys.get(i) + ",";
          } else {
            pkeys += cPkeys.get(i);
          }
        }
        sql.WHERE(PdtProduct.T.CATEGORY, " in(" + pkeys + ") ");
      }
    if (StringUtil.hasValue(pName)) {
      sql.WHERE(
          " upper(JSON_EXTRACT(PdtProduct.name,?))  like upper(?) ",
          "$." + curLanguage.name(),
          "%" + pName + "%");
    }
    if (level != null) sql.WHERE(UsrSupplier.T.ROLE, " =? ", level);
    if (StringUtil.hasValue(export)) {
      String[] exSplit = export.split(",");
      StringBuffer buff = new StringBuffer("");
      for (int i = 0; i < exSplit.length; i++) {
        if (i == 0 && exSplit.length > 1) // length不为1时的第一个
        {
          sql.WHERE(
              "( upper(JSON_EXTRACT(UsrSupplier.main_sales_area,?))  like upper(?)",
              "$." + curLanguage.name(),
              "%" + exSplit[i] + "%");
        } else if (exSplit.length == 1) { // 只有一个
          sql.WHERE(
              " ( upper(JSON_EXTRACT(UsrSupplier.main_sales_area,?))  like upper(?) or UsrSupplier.main_sales_area like '%不限%' ) ",
              "$." + curLanguage.name(), "%" + exSplit[0] + "%");
        } else if (i == exSplit.length - 1) { // 最后一个
          sql.WHERE(
              " or upper(JSON_EXTRACT(UsrSupplier.main_sales_area,?))  like upper(?) or UsrSupplier.main_sales_area like '%不限%' ) ",
              "$." + curLanguage.name(), "%" + exSplit[i] + "%");
        } else { // 中间的
          sql.WHERE(
              " or upper(JSON_EXTRACT(UsrSupplier.main_sales_area,?))  like upper(?) ",
              "$." + curLanguage.name(),
              "%" + exSplit[i] + "%");
        }
      }
      if (!buff.toString().equals("")) sql.WHERE(buff.toString());
    }
    if (mOrder != null) {
      sql.WHERE(PdtProduct.T.MIN_OQ, " <=? ", mOrder);
      sql.WHERE(PdtProduct.T.MIN_OQ, " >? ", 0);
      sql.ORDER_BY(PdtProduct.T.MIN_OQ, "asc");
    }
    if (min != null && min.compareTo(BigDecimal.ZERO) == 1) {
      sql.WHERE(PdtProduct.T.CUR_PRICE, " >=? ", min);
      sql.ORDER_BY(PdtProduct.T.CUR_PRICE, "asc");
    }
    sql.WHERE(
        max != null && max.compareTo(BigDecimal.ZERO) == 1, PdtProduct.T.CUR_PRICE, " <=? ", max);
    if (IsO2o != null && IsO2o.equals(1)) {
      if (!o2oPdtPkey.isEmpty()) {
        StringBuffer buff = new StringBuffer("");
        int i = 0;
        for (Integer item : o2oPdtPkey) {
          if (o2oPdtPkey.size() - 1 == i) buff.append(item);
          else {
            buff.append(item);
            buff.append(",");
          }
          i++;
        }
        if (!buff.toString().equals("")) {
          sql.WHERE(PdtProduct.T.PKEY, " in(" + buff.toString() + ") ");
        }
      } else {
        sql.WHERE(PdtProduct.T.PKEY, " in(-1) ");
      }
    }
    sql.ORDER_BY(PdtProduct.T.MY_ORDER, "desc");
    List<Integer> menList = PdtCatDAO.getListByGender(men); // 男鞋分类
    List<Integer> womenList = PdtCatDAO.getListByGender(women); // 女鞋分类
    List<PdtAttrLine> attrList = PdtAttrLineDAO.getListByMain(inner, season, sole, upper, colsed);
    List<PdtAttrLine> innerList = new ArrayList<>();
    List<PdtAttrLine> seasonList = new ArrayList<>();
    List<PdtAttrLine> soleList = new ArrayList<>();
    List<PdtAttrLine> upperList = new ArrayList<>();
    List<PdtAttrLine> colsedList = new ArrayList<>();
    for (PdtAttrLine attrItem : attrList) {
      if (attrItem.getMain() == inner) innerList.add(attrItem);
      else if (attrItem.getMain() == season) seasonList.add(attrItem);
      else if (attrItem.getMain() == sole) soleList.add(attrItem);
      else if (attrItem.getMain() == upper) upperList.add(attrItem);
      else if (attrItem.getMain() == colsed) colsedList.add(attrItem);
    }
    List<Integer> userFavoritePdt = UsrFavoritesDAO.getUserFavorite(purchase);
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<Map<String, Object>> queryMaps =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps();
    /*
     * List<O2O_Product> copy = o2oProduct.stream().sorted((c1,c2)->{{ return
     * c1.getUpdatedTime().compareTo(c2.getUpdatedTime());
     * }}).collect(Collectors.toList());
     */
    List<O2O_Product> copy = o2oProduct;
    List<PdtSearchView> pdtListViwe =
        queryMaps.stream()
            .map(
                map ->
                    new PdtSearchView() {
                      {
                        Integer pdtPkey = Integer.parseInt(map.get("pdtPkey").toString());
                        setPdtId(pdtPkey);

                        SQL curPriceSql = new SQL();
                        curPriceSql.SELECT(
                            " MAX("
                                + PdtTieredPricing.class.getSimpleName()
                                + "."
                                + PdtTieredPricing.T.CUR_PRICE.getFld().getCodeSqlField()
                                + ") AS maxCurPrice");
                        curPriceSql.SELECT(
                            " MIN("
                                + PdtTieredPricing.class.getSimpleName()
                                + "."
                                + PdtTieredPricing.T.CUR_PRICE.getFld().getCodeSqlField()
                                + ") AS minCurPrice");
                        curPriceSql.FROM(PdtTieredPricing.class);
                        curPriceSql.WHERE(PdtTieredPricing.T.PRODUCT, " =? ", pdtPkey);
                        curPriceSql.WHERE(
                            PdtTieredPricing.T.DELETED, " =? ", OYn.NO.getLine().getKey());

                        Map<String, Object> mm = Query.sql(curPriceSql).queryMap();
                        if (null != mm) {
                          BigDecimal p =
                              new BigDecimal(
                                  String.valueOf(
                                      map.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField())));
                          setMinCurPrice(p);
                          setMaxCurPrice(GetValue.get(mm, "maxCurPrice", BigDecimal.class, p));
                          setMinCurPrice(GetValue.get(mm, "minCurPrice", BigDecimal.class, p));
                        }

                        setPdtName(map.get("pdtName").toString());

                        if (IsO2o != null && IsO2o == 1) {
                          for (O2O_Product o2opdt : copy) {
                            if (pdtPkey.equals(o2opdt.getProductId())) {
                              System.out.println(o2opdt.getPrice());
                              // 目前o2o产品的价格字段没有用起来 ,所以暂时还是取普通产品表的价格字段
                              setPrice(
                                  new BigDecimal(
                                      map.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField())
                                          .toString()));
                              // setPrice(o2opdt.getPrice());
                              setMinOrder(o2opdt.getMinOq());
                              break;
                            }
                          }
                        } else {
                          setPrice(
                              new BigDecimal(
                                  map.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField())
                                      .toString()));

                          setMinOrder(
                              Integer.parseInt(
                                  map.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField())
                                      .toString()));
                        }
                        Integer pkey1 = GetValue.get(map, "pdtPkey", Integer.class, null);
                        List<PdtSpec> specs =
                            BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT + "=" + pkey1, false);
                        ArrayList<String> stringList = new ArrayList<>();
                        for (PdtSpec spec : specs) {
                          String[] s = spec.getPics().split(",");
                          if (s.length > 0) {
                            for (String s1 : s) {
                              if (s1.length() > 0 && !stringList.contains(s1)) {
                                stringList.add(s1);
                              }
                            }
                          }
                        }
                        if (stringList.size() > 0) {
                          String t =
                              GetValue.getFirstImage(
                                  GetValue.get(map, PdtProduct.T.PICTURE, String.class, ""));
                          if (!seasonList.contains(t)) stringList.add(0, t);
                          setPicture(Strings.join(stringList, ','));
                        } else {
                          setPicture(GetValue.get(map, PdtProduct.T.PICTURE, String.class, ""));
                        }

                        String pdtCatName = map.get("pdtCatName").toString();
                        Integer gender = 0;
                        if (pdtCatName.indexOf("男") != -1) {
                          gender = 1;
                        } else if (pdtCatName.indexOf("女") != -1) {
                          gender = 2;
                        }
                        if (gender == 0) {
                          Integer pdtCatPkey = Integer.parseInt(map.get("pdtCatPkey").toString());
                          Integer pdtCatUp =
                              Integer.parseInt(
                                  map.get("pdtCatUp") == null
                                      ? "0"
                                      : map.get("pdtCatUp").toString());
                          if (menList.contains(pdtCatUp)
                              || menList.contains(pdtCatPkey)
                              || pdtCatPkey.equals(men)) gender = 1;
                          else if (womenList.contains(pdtCatUp)
                              || womenList.contains(pdtCatPkey)
                              || pdtCatPkey.equals(women)) gender = 2;
                          else gender = 3;
                        }
                        setGender(gender);
                        Object attr = map.get(PdtProduct.T.NORM_ATTR.getFld().getCodeSqlField());
                        String[] attrSplit = {};
                        if (attr == null) attrSplit = null;
                        else attrSplit = attr.toString().split(",");
                        setInner(getAttr(innerList, attrSplit, curLanguage));
                        setSeason(getAttr(seasonList, attrSplit, curLanguage));
                        setSole(getAttr(soleList, attrSplit, curLanguage));
                        setClosed(getAttr(colsedList, attrSplit, curLanguage));
                        setRewrite(SEOUtils.getPdtProductTitle(getPdtId(), getPdtName()));
                        setUpper(getAttr(upperList, attrSplit, curLanguage));
                        ;
                        setOriginCountry(String.valueOf(map.get("pltCountry")));
                        setOriginProvince(String.valueOf(map.get("pltProvince")));
                        if (purchase != null) setEshrine(userFavoritePdt.contains(pdtPkey));
                        setPdtType(
                            Integer.parseInt(
                                map.get(PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField())
                                    .toString()));
                        try {
                          setSupId(Integer.parseInt(map.get("supPkey").toString()));
                        } catch (Exception e) {
                          logger.error(String.format("%d 该商品供应商不存在", getPdtId()));
                        }
                        if (map.get("supName") != null) setSupName(map.get("supName").toString());

                        Date authDate =
                            (Date) map.get(UsrSupplier.T.AUTH_TIME.getFld().getCodeSqlField());
                        SimpleDateFormat sim = new SimpleDateFormat("yyyy");
                        if (authDate == null) authDate = new Date();
                        String authFormat = sim.format(authDate);
                        String dateFormat = sim.format(new Date());
                        Integer enter = Integer.parseInt(authFormat) - Integer.parseInt(dateFormat);
                        setEnter(enter <= 0 ? 1 : enter + 1);
                      }
                    })
            .filter(
                pdtSearchView -> {
                  return pdtSearchView.getSupId() != null;
                })
            .collect(Collectors.toList());
    return new Page(pdtListViwe, start, limit, count);
  }

  public static String getAttr(
      List<PdtAttrLine> pdtAttr, String[] attrSplit, FldLanguage.Language curLanguage) {
    if (pdtAttr == null || attrSplit == null) return "";
    if (pdtAttr.isEmpty() || attrSplit.length == 0) return "";
    StringBuffer buff = new StringBuffer("");
    for (int i = 0; i < attrSplit.length; i++) {
      if (!attrSplit[i].trim().equals(""))
        for (PdtAttrLine item : pdtAttr) {
          if (Integer.parseInt(attrSplit[i]) == item.getPkey()) {
            try {
              buff.append(item.getName(curLanguage));
              buff.append(" ");
            } catch (JSONException e) {
              buff.append("");
              e.printStackTrace();
            }
          }
        }
    }
    return buff.toString();
  }

  public PdtProduct findByPkey(Integer productPkey) {
    return Query.SELECT(PdtProduct.class, productPkey);
  }

  /*
   * 查找供应商中心列表,供应商要展示的产品 条件:IS_VERIFY= 1 STATE = 1 PRODUCT_TYPE = 0
   *
   * @Author HuangHaoBin
   **/
  public List findBySupplier(Integer supplier) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", supplier)
        .limit(0, 4);
    return query.queryMaps();
  }

  public void upd(List<PdtProduct> prods) {
    for (int i = 0; i < prods.size(); i++) {
      PdtProduct a = prods.get(i);
      String sql =
          " UPDATE "
              + PdtProduct.TB.getCodeSqlTb()
              + " SET "
              + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
              + " = "
              + a.getProductType()
              + " WHERE "
              + PdtProduct.T.PKEY.getFld().getCodeSqlField()
              + " = "
              + a.getPkey()
              + ";";
      try {
        System.out.println(new SqlQuery(sql).executeUpdate());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    try {
      DbPool.getInstance().getConn().commit();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * 传入分类的上级分类
   *
   * @auther liyichao
   * @param category
   */
  public List<String> getParent(Integer cat) {
    PdtCat category = BeanBase.load(PdtCat.class, cat);
    if (null == category) {
      throw new WebMessageException(ReturnCode.failure, "分类记录不存在");
    }
    List<String> pkeys = new ArrayList<>();
    do {
      pkeys.add(String.valueOf(category.getPkey()));
      if (null == category.getCategoryUp()) {
        break;
      }
      category = category.gtCategoryUp();
    } while (true);
    return pkeys;
  }

  public Page getPrivatePdts(Integer supId, Integer start, Integer limit) {
    SQL sql =
        new SQL() {
          {
            SELECT(PdtProduct.T.PKEY, PdtProduct.T.PICTURE, PdtProduct.T.NAME)
                .FROM(PdtProduct.class)
                .LEFT_JOIN(O2O_PrivateExpoPdt.class, O2O_PrivateExpoPdt.T.PDT_ID, PdtProduct.T.PKEY)
                .WHERE(PdtProduct.T.SUPPLIER, "=?", supId)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.PrivateExpo)
                .WHERE(O2O_PrivateExpoPdt.T.STATUS, "=?", O2O_PrivateExpoPdtStatus.ON)
                .WHERE(O2O_PrivateExpoPdt.T.VERIFY_STATUS, "=?", O2O_PrivateExpoPdtStatus.PASS);
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<PrivatePdtView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new PrivatePdtView() {
                      {
                        System.out.println(o);
                        setPkey((Integer) o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()));
                        setName((String) o.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
                        setImages((String) o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()));
                      }
                    })
            .collect(toList());
    return new Page(list, start, limit, count);
  }

  @Caches
  public int getPdtCount() {
    return Query.sql(productRules(new SQL()).SELECT(PdtProduct.class).FROM(PdtProduct.class))
        .queryCount();
  }

  /**
   * 获取供应商最新发布的三条产品信息
   *
   * @author GS
   * @param supplierId
   * @return
   */
  public List findPdtBySupplier(Integer supplierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(
                    PdtProduct.T.NAME,
                    PdtProduct.T.PICTURE,
                    PdtProduct.T.PKEY,
                    PdtProduct.T.IS_DEFAULT_REVIEW)
                .FROM(PdtProduct.class)
                .WHERE(supplierId != null, PdtProduct.T.SUPPLIER, "=?", supplierId)
                .WHERE(PdtProduct.T.IS_VERIFY, "=?", Pdt.OAppr.PASS)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.PrivateExpo)
                .ORDER_BY(PdtProduct.T.VERIFY_TIME, "desc")
                .LIMIT(0, 3);
          }
        };
    return Query.sql(sql).queryMaps();
  }

  /**
   * 手机端商家产品列表
   *
   * @param pkey
   * @param start
   * @param limit
   * @param checkType
   * @return
   */
  public List findPdtListBySupplier(Integer pkey, Integer start, Integer limit, Integer checkType) {
    List list = null;

    SQL sql = new SQL();
    SQL favoritesSql = new SQL();
    SQL minSql = new SQL();
    SQL maxSql = new SQL();
    minSql
        .SELECT(
            " MIN("
                + PdtTieredPricing.class.getSimpleName()
                + "."
                + PdtTieredPricing.T.CUR_PRICE
                + ")")
        .FROM(PdtTieredPricing.class)
        .WHERE(PdtTieredPricing.T.PRODUCT, "=", PdtProduct.T.PKEY);
    maxSql
        .SELECT(
            " MAX("
                + PdtTieredPricing.class.getSimpleName()
                + "."
                + PdtTieredPricing.T.CUR_PRICE
                + ")")
        .FROM(PdtTieredPricing.class)
        .WHERE(PdtTieredPricing.T.PRODUCT, "=", PdtProduct.T.PKEY);
    favoritesSql
        .SELECT("count(1)")
        .FROM(UsrFavorites.class)
        .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY);
    SQL subSQL = new SQL();
    subSQL
        .SELECT(" count(1) ")
        .FROM(RFQConsult.class)
        .WHERE(RFQConsult.T.PRODUCT, " =", PdtProduct.T.PKEY);
    sql.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE, PdtProduct.T.CODE)
        .SELECT(minSql, "minPrice")
        .SELECT(maxSql, "maxPrice")
        .SELECT(subSQL, "sub")
        .SELECT(favoritesSql, "favorites")
        .FROM(PdtProduct.class)
        .LEFT_JOIN(PdtTieredPricing.class, PdtTieredPricing.T.PRODUCT, PdtProduct.T.PKEY)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", pkey)
        .WHERE(PdtProduct.T.IS_VERIFY, "=?", Pdt.OAppr.PASS)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.PrivateExpo)
        .GROUP_BY(PdtProduct.T.PKEY);

    if (checkType == 0) {
      sql.ORDER_BY(PdtProduct.T.VERIFY_TIME, "desc");
    }
    ;
    if (checkType == 1) {
      sql.orderByDesc("minPrice");
    }
    if (checkType == 2) {
      sql.orderByDesc("sub");
    }
    if (checkType == 3) {
      sql.orderByDesc("favorites");
    }
    sql.LIMIT(start, limit);
    return Query.sql(sql).queryMaps();
  }

  /**
   * 手机端商家产品列表总条数
   *
   * @param pkey
   * @param start
   * @param limit
   * @param checkType
   * @return
   */
  public int count(Integer pkey, Integer start, Integer limit, Integer checkType) {
    List list = null;

    SQL sql = new SQL();
    SQL favoritesSql = new SQL();
    SQL minSql = new SQL();
    SQL maxSql = new SQL();
    minSql
        .SELECT(
            " MIN("
                + PdtTieredPricing.class.getSimpleName()
                + "."
                + PdtTieredPricing.T.CUR_PRICE
                + ")")
        .FROM(PdtTieredPricing.class)
        .WHERE(PdtTieredPricing.T.PRODUCT, "=", PdtProduct.T.PKEY);
    maxSql
        .SELECT(
            " MAX("
                + PdtTieredPricing.class.getSimpleName()
                + "."
                + PdtTieredPricing.T.CUR_PRICE
                + ")")
        .FROM(PdtTieredPricing.class)
        .WHERE(PdtTieredPricing.T.PRODUCT, "=", PdtProduct.T.PKEY);
    favoritesSql
        .SELECT("count(1)")
        .FROM(UsrFavorites.class)
        .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY);
    SQL subSQL = new SQL();
    subSQL
        .SELECT(" count(1) ")
        .FROM(RFQConsult.class)
        .WHERE(RFQConsult.T.PRODUCT, " =", PdtProduct.T.PKEY);
    sql.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE, PdtProduct.T.CODE)
        .SELECT(minSql, "minPrice")
        .SELECT(maxSql, "maxPrice")
        .SELECT(subSQL, "sub")
        .SELECT(favoritesSql, "favorites")
        .FROM(PdtProduct.class)
        .LEFT_JOIN(PdtTieredPricing.class, PdtTieredPricing.T.PRODUCT, PdtProduct.T.PKEY)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", pkey)
        .WHERE(PdtProduct.T.IS_VERIFY, "=?", Pdt.OAppr.PASS)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.PrivateExpo)
        .GROUP_BY(PdtProduct.T.PKEY);
    if (checkType == 0) {
      sql.ORDER_BY(PdtProduct.T.VERIFY_TIME, "desc");
    }
    ;
    if (checkType == 1) {
      sql.orderByDesc("minPrice");
    }
    if (checkType == 2) {
      sql.orderByDesc("sub");
    }
    if (checkType == 3) {
      sql.orderByDesc("favorites");
    }
    return Query.sql(sql).queryMaps().size();
  }

  /**
   * 获取商家主要产品类型名称
   *
   * @author GS
   * @param supplier
   * @return
   */
  public List getTopPdtCaties(Integer supplier) {
    SQL sql = new SQL();
    sql.SELECT(PdtCat.T.NAME)
        .SELECT("count(1) cat")
        .FROM(PdtProduct.class)
        .LEFT_JOIN(PdtCat.class, PdtCat.T.PKEY, PdtProduct.T.CATEGORY)
        .WHERE(supplier != null, PdtProduct.T.SUPPLIER, "=?", supplier)
        .GROUP_BY(PdtProduct.T.CATEGORY)
        .orderByDesc("cat")
        .LIMIT(0, 3);
    return Query.sql(sql).queryMaps();
  }

  /** @Author wilson Zhang @Description 判断货号是否存在 并且产品不是本身 @Date 17:30 2019/3/30 */
  public static Integer verifyCode(String code, Integer pruductPkey, Integer supplierPkey) {
    SQL sql =
        new SQL() {
          {
            SELECT(PdtProduct.T.PKEY)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.CODE, "=?", code)
                .WHERE(PdtProduct.T.SUPPLIER, "=?", supplierPkey);
          }
        };
    if (Query.sql(sql).queryCount() > 1) {
      return 0;
    } else if (Query.sql(sql).queryCount() == 0) {
      return 1;
    } else if (Query.sql(sql).queryCount() == 1) {
      Integer num = Query.sql(sql).query(PdtProduct.class).getPkey();
      if (num.equals(pruductPkey)) {
        return 1;
      }
    }
    return 0;
  };
}
