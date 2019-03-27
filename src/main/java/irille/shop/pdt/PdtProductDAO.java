package irille.shop.pdt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMMessageServiceImp;

import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.PdtView;
import irille.platform.pdt.View.PdtProductView;
import irille.platform.pdt.View.productView.ProductsView;
import irille.platform.pdt.View.productView.SeartchView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.TranslateFilter;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.Pdt.OState;
import irille.shop.pdt.PdtProduct.T;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrProductCategoryDAO;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class PdtProductDAO {

  public static final Log LOG = new Log(PdtProductDAO.class);

  /**
   * @Description: 查询产品列表
   *
   * @date 2019/1/23 19:27
   * @anthor lingjian
   */
  public static Page listproduct(String fldvalue, String condition, Integer start, Integer limit) {
    SQL sql =
        new SQL() {
          {
            SELECT(PdtProduct.class).FROM(PdtProduct.class);
            if (fldvalue != null) {
              if (fldvalue.equalsIgnoreCase("name")) {
                WHERE(PdtProduct.T.NAME, "like ?", "'%" + condition + "%'");
              }
              if (fldvalue.equalsIgnoreCase("sku")) {
                WHERE(T.SKU, "like ?", "'%" + condition + "%'");
              }
              if (fldvalue.equalsIgnoreCase("code")) {
                WHERE(T.CODE, "like ?", "'%" + condition + "%'");
              }
              if (fldvalue.equalsIgnoreCase("supplier")) {
                WHERE(T.SUPPLIER, "=?" + condition);
              }
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<PdtProductView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new PdtProductView() {
                      {
                        setPkey((Integer) bean.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
                        setPicture((String) bean.get(T.PICTURE.getFld().getCodeSqlField()));
                        setCode((String) bean.get(T.CODE.getFld().getCodeSqlField()));
                        setSku((String) bean.get(T.SKU.getFld().getCodeSqlField()));
                        setCategory(
                            Bean.load(
                                    PdtCat.class,
                                    (Integer) bean.get(T.CATEGORY.getFld().getCodeSqlField()))
                                .getName());
                        setSupplier(
                            Bean.load(
                                    UsrSupplier.class,
                                    (Integer) bean.get(T.SUPPLIER.getFld().getCodeSqlField()))
                                .getName());
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static class QueryDetail extends IduOther<Query, PdtProduct> {

    public PdtProduct load(Integer pkey) {
      return PdtProduct.load(PdtProduct.class, pkey);
    }
  }

  /**
   * 产品审核弃审
   *
   * <p>弃审同时下架产品
   */
  public static PdtProduct verify(boolean verify, Integer pkey) {
    PdtProduct bean = BeanBase.load(PdtProduct.class, pkey);
    bean.stIsVerify(verify);

    if (!verify) {
      bean.stState(OState.OFF);
    }

    bean.upd();
    return bean;
  }

  private static final UsrCartDAO.Query cartQuery = new UsrCartDAO.Query();

  /** @author yingjianhua */
  public static class Query extends IduOther<Query, PdtProduct> {

    public List<PdtProduct> listByPurchaseCart(Integer pkey) {
      List<UsrCart> carts = cartQuery.listByPurchase(pkey);
      Map<Integer, PdtProduct> productMap = new HashMap<Integer, PdtProduct>();
      for (UsrCart cart : carts) {
        Integer pid = cart.gtSpec().getProduct();
        if (!productMap.containsKey(pid)) {
          productMap.put(pid, cart.gtSpec().gtProduct());
        }
      }
      return languageShift(new ArrayList<PdtProduct>(productMap.values())); // 转
    }

    public List<PdtProduct> listBySpec(List<PdtSpec> specs) {
      if (specs.size() == 0) {
        return new ArrayList<>();
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < specs.size(); i++) {
        if (i != 0) {
          b.append(",");
        }
        b.append(specs.get(i).getProduct());
      }
      String where = PdtProduct.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
      return languageShift(BeanBase.list(PdtProduct.class, where, false)); // 转
    }
  }

  public static class Ins extends IduIns<Ins, PdtProduct> {

    @Override
    public void before() {
      super.before();
      getB().setUpdateTime(Env.getTranBeginTime());
      try {
        translateUtil.autoTranslate(getB()); // 转
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 商家发布产品
   *
   * @author yingjianhua
   */
  public static class Publish extends IduIns<Publish, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
      //			super.before();
      PdtProduct dbBean = new PdtProduct().init();
      dbBean.setSupplier(SellerAction.getSupplier().getPkey());
      dbBean.setMemberLevel(null);
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME, // 名字
          T.IS_VERIFY, // 审核状态
          T.CATEGORY, // 产品类目
          T.CATEGORY_DIY, // 店铺-产品类目
          T.CODE, // 编号
          T.SKU, // sku
          T.PICTURE, // 产品图片
          T.NORM_ATTR, // 属性
          T.CUR_PRICE, // 价格
          T.MIN_OQ, // 起订量
          T.MAX_OQ, // 最大购买量
          T.STOCK, // 库存
          T.WARN_STOCK, // 警告库存
          T.STATE, // 销售状态上架下架
          T.SOLD_IN_TIME, // 定时上架
          T.SOLD_TIME_B, // 上架时间开始
          T.SOLD_TIME_E, // 上架时间结束
          T.SIZE_ATTR, // 规格属性    PdtSize的pkeys
          T.COLOR_ATTR, // 颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, // 免运费
          T.WEIGHT, // 重量
          T.LENGTH, // 长
          T.WIDTH, // 宽
          T.HEIGHT, // 高
          T.BRIEF_DESCRIPTION, // 简短描述
          T.DESCRIPTION, // 详细介绍
          T.SEO_DESCRIPTION,
          T.SEO_KEYWORD,
          T.SEO_TITLE,
          T.PRODUCT_TYPE);
      translateUtil.newAutoTranslate(
          dbBean, translateUtil.buildFilter(dbBean.getName(), FldLanguage.Language.en));
      getB().setUpdateTime(Env.getTranBeginTime());
      dbBean.setDescribeModule1(getB().getDescribeModule1());
      dbBean.setDescribeModule2(getB().getDescribeModule2());
      dbBean.setDescribeModule3(getB().getDescribeModule3());
      setB(dbBean);
    }

    @Override
    public void valid() {}

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        translateUtil.autoTranslate(line);
      }
      insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }

  /**
   * 商家发布产品
   *
   * @author yingjianhua
   */
  public static class ExcelLoad extends IduIns<ExcelLoad, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
      //			super.before();
      PdtProduct dbBean = new PdtProduct().init();
      //            dbBean.setSupplier(SellerAction.getSupplier().getPkey());
      dbBean.setSupplier(262);
      dbBean.setMemberLevel(null);
      TranslateFilter translateFilter = new TranslateFilter();
      translateFilter.setLanguageList(new ArrayList<>());
      JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
      translateFilter.setMode(0);
      translateUtil.addFilterToGlobalFilter(PdtSpec.class, PdtSpec.T.KEY_NAME);
      jsonObject
          .entrySet()
          .forEach(
              stringJsonElementEntry -> {
                // 如果不相等  添加到清单
                if (stringJsonElementEntry.getValue().getAsString().length() > 0) {
                  translateFilter
                      .getLanguageList()
                      .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                }
              });
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME, // 名字
          T.CATEGORY, // 产品类目
          T.CATEGORY_DIY, // 店铺-产品类目
          T.CODE, // 编号
          T.SKU, // sku
          T.PICTURE, // 产品图片
          T.NORM_ATTR, // 属性
          T.CUR_PRICE, // 价格
          T.MIN_OQ, // 起订量
          T.MAX_OQ, // 最大购买量
          T.STOCK, // 库存
          T.WARN_STOCK, // 警告库存
          T.STATE, // 销售状态上架下架
          T.SOLD_IN_TIME, // 定时上架
          T.SOLD_TIME_B, // 上架时间开始
          T.SOLD_TIME_E, // 上架时间结束
          T.SIZE_ATTR, // 规格属性    PdtSize的pkeys
          T.COLOR_ATTR, // 颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, // 免运费
          T.WEIGHT, // 重量
          T.LENGTH, // 长
          T.WIDTH, // 宽
          T.HEIGHT, // 高
          T.BRIEF_DESCRIPTION, // 简短描述
          T.DESCRIPTION // 详细介绍
          );
      translateUtil.newAutoTranslate(dbBean, translateFilter);

      getB().setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }

    @Override
    public void valid() {}

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        translateUtil.autoTranslate(line);
      }
      insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }

  public static class Upd extends IduUpd<Upd, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {

      //			super.before();
      PdtProduct dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME, // 名字
          T.CATEGORY, // 产品类目
          T.CATEGORY_DIY, // 店铺-产品类目
          T.CODE, // 编号
          T.SKU, // sku
          T.PICTURE, // 产品图片
          T.NORM_ATTR, // 属性
          T.CUR_PRICE, // 价格
          T.MIN_OQ, // 起订量
          T.MAX_OQ, // 最大购买量
          T.STOCK, // 库存
          T.WARN_STOCK, // 警告库存
          T.STATE, // 销售状态上架下架
          T.SOLD_IN_TIME, // 定时上架
          T.SOLD_TIME_B, // 上架时间开始
          T.SOLD_TIME_E, // 上架时间结束
          T.SIZE_ATTR, // 规格属性    PdtSize的pkeys
          T.COLOR_ATTR, // 颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, // 免运费
          T.WEIGHT, // 重量
          T.LENGTH, // 长
          T.WIDTH, // 宽
          T.HEIGHT, // 高
          T.BRIEF_DESCRIPTION, // 简短描述
          T.DESCRIPTION // 详细介绍
          );

      //			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
      //			dbBean.setMemberLevel(null);
      try {
        dbBean.setName(dbBean.getName(HomeAction.curLanguage())); // 转
        dbBean.setDescription(dbBean.getDescription(HomeAction.curLanguage())); // 转
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      getB().setUpdateTime(Env.getTranBeginTime());
      //            translateUtil.autoTranslateSaveOrUpdate(dbBean, true, false, null);
      //			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
      //			getB().upd();
    }

    public void updateFavorite(int id) {
      String sql =
          "update "
              + FormaterSql.getTableName(T.PKEY)
              + " set "
              + T.Favorite_Count.getFld().getCodeSqlField()
              + "="
              + T.Favorite_Count.getFld().getCodeSqlField()
              + "+1 where "
              + T.PKEY
              + " = ?";
      BeanBase.executeUpdate(sql, id);
    }

    public void removeFavorite(int id) {
      String sql =
          "update "
              + FormaterSql.getTableName(T.PKEY)
              + " set "
              + T.Favorite_Count.getFld().getCodeSqlField()
              + "="
              + T.Favorite_Count.getFld().getCodeSqlField()
              + "-1 where "
              + T.PKEY
              + " = ?";
      BeanBase.executeUpdate(sql, id);
    }

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        line.setProduct(getB().getPkey());

        line.setKeyName(line.gtColor().getName() + "+" + line.gtSize().getName());
        line.setMarkup(null);

        line.setMarkup(BigDecimal.ZERO);
      }
      updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }

  /**
   * xiayan
   *
   * @author xinlian
   */
  public static class Upd1 extends IduUpd<Upd1, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {

      //			super.before();
      PdtProduct dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME, // 名字
          T.CATEGORY, // 产品类目
          T.CATEGORY_DIY, // 店铺-产品类目
          T.CODE, // 编号
          T.SKU, // sku
          T.PICTURE, // 产品图片
          T.NORM_ATTR, // 属性
          T.CUR_PRICE, // 价格
          T.MIN_OQ, // 起订量
          T.MAX_OQ, // 最大购买量
          T.STOCK, // 库存
          T.WARN_STOCK, // 警告库存
          T.STATE, // 销售状态上架下架
          T.SOLD_IN_TIME, // 定时上架
          T.SOLD_TIME_B, // 上架时间开始
          T.SOLD_TIME_E, // 上架时间结束
          T.SIZE_ATTR, // 规格属性    PdtSize的pkeys
          T.COLOR_ATTR, // 颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, // 免运费
          T.WEIGHT, // 重量
          T.LENGTH, // 长
          T.WIDTH, // 宽
          T.HEIGHT, // 高
          T.BRIEF_DESCRIPTION, // 简短描述
          T.DESCRIPTION // 详细介绍
          );

      //			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
      //			dbBean.setMemberLevel(null);
      setB(dbBean);
      getB().setUpdateTime(Env.getTranBeginTime());
      //			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
      //			getB().upd();
    }
  }

  /**
   * xiayan
   *
   * @author xinlian
   */
  public static class Upd2 extends IduUpd<Upd2, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
      PdtProduct dbBean = load(getB().getPkey());
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME, // 名字
          T.IS_VERIFY, // 产品审核
          T.CATEGORY, // 产品类目
          T.CATEGORY_DIY, // 店铺-产品类目
          T.CODE, // 编号
          T.SKU, // sku
          T.PICTURE, // 产品图片
          T.NORM_ATTR, // 属性
          T.CUR_PRICE, // 价格
          T.MIN_OQ, // 起订量
          T.MAX_OQ, // 最大购买量
          T.STOCK, // 库存
          T.WARN_STOCK, // 警告库存
          T.STATE, // 销售状态上架下架
          T.SOLD_IN_TIME, // 定时上架
          T.SOLD_TIME_B, // 上架时间开始
          T.SOLD_TIME_E, // 上架时间结束
          T.SIZE_ATTR, // 规格属性    PdtSize的pkeys
          T.COLOR_ATTR, // 颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, // 免运费
          T.WEIGHT, // 重量
          T.LENGTH, // 长
          T.WIDTH, // 宽
          T.HEIGHT, // 高
          T.BRIEF_DESCRIPTION, // 简短描述,
          T.DESCRIPTION, // 简短描述
          T.SEO_DESCRIPTION,
          T.SEO_KEYWORD,
          T.SEO_TITLE,
          T.PRODUCT_TYPE);
      translateUtil.newAutoTranslate(
          dbBean,
          translateUtil.buildFilter(dbBean.getName(), getB().getName(), FldLanguage.Language.en));
      dbBean.setDescribeModule1(getB().getDescribeModule1());
      dbBean.setDescribeModule2(getB().getDescribeModule2());
      dbBean.setDescribeModule3(getB().getDescribeModule3());
      setB(dbBean);
      getLines()
          .forEach(
              pdtSpec -> {
                pdtSpec.setRowVersion((short) 0);
                translateUtil.autoTranslate(pdtSpec);
              });
      updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }

    @Override
    public void valid() {}
  }

  public static class DelDetails extends IduOther<DelDetails, PdtProduct> {

    public void before() {
      getB().setState(Pdt.OState.DELETE.getLine().getKey());
    }

    public void run() {
      getB().upd();
    }
  }

  public static class Reduction extends IduOther<Reduction, PdtProduct> {
    public void run() {
      PdtProduct product = BeanBase.load(PdtProduct.class, getB().getPkey());
      product.setState(OState.OFF.getLine().getKey());
      product.upd();
    }
  }

  public static class Remove extends IduOther<Remove, PdtProduct> {
    public void run() {
      PdtProduct product = BeanBase.load(PdtProduct.class, getB().getPkey());
      product.setState(OState.MERCHANTDEL.getLine().getKey());
      product.setIsVerify(Sys.OYn.NO.getLine().getKey());
      product.upd();
      if (product.getProductType() == Pdt.OProductType.PrivateExpo.getLine().getKey()) {
        O2O_PrivateExpoPdt o2o_privateExpoPdt =
            O2O_PrivateExpoPdt.chkUniquePdt_Id(false, product.getPkey());
        o2o_privateExpoPdt.setVerifyStatus(O2O_PrivateExpoPdtStatus._DEFAULT.getLine().getKey());
        o2o_privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.OFF.getLine().getKey());
        o2o_privateExpoPdt.upd();
      }
    }
  }

  public static class Obtained extends IduOther<Obtained, PdtProduct> {
    public void run() {
      PdtProduct product = BeanBase.load(PdtProduct.class, getB().getPkey());
      product.setState(OState.OFF.getLine().getKey());
      product.setIsVerify(Sys.OYn.NO.getLine().getKey());
      product.setSoldTimeE(Env.getTranBeginTime());
      product.upd();
      if (product.getProductType() == Pdt.OProductType.PrivateExpo.getLine().getKey()) {
        O2O_PrivateExpoPdt o2o_privateExpoPdt =
            O2O_PrivateExpoPdt.chkUniquePdt_Id(false, product.getPkey());
        o2o_privateExpoPdt.setVerifyStatus(O2O_PrivateExpoPdtStatus._DEFAULT.getLine().getKey());
        o2o_privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.OFF.getLine().getKey());
        o2o_privateExpoPdt.upd();
      }
    }
  }

  /** xiayan List<PdtProduct> 查询时多国语言转换 */
  public static List<PdtProduct> languageShift(List<PdtProduct> _productList) { // 转
    for (int i = 0; i < _productList.size(); i++) {
      try {
        _productList.get(i).setName(_productList.get(i).getName(HomeAction.curLanguage()));
        _productList
            .get(i)
            .setDescription(_productList.get(i).getDescription(HomeAction.curLanguage()));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return _productList;
  }

  public static class Select extends IduOther<Select, PdtProduct> {

    /**
     * 获取店铺产品页产品(条件:/对应商家/上架/通过审核/普通及采集产品 (搜索条件:/页码/店铺分类) (排序条件)
     *
     * @param lang 语言
     * @param supplier 供应商pkey
     * @param start 查询起始位置
     * @param limit 查询记录数
     * @param cat 商家产品分类
     * @param sort 排序依据 1->热卖 2->收藏数 3->新品 4->产品价格 默认审核时间
     * @param orderType 排序方式 1->ASC 默认->DESC
     */
    public static Map getProductBySup(
        FldLanguage.Language lang,
        Serializable supplier,
        Integer start,
        Integer limit,
        Integer cat,
        Integer sort,
        Integer orderType) {
      Map map = new HashMap();
      SQL sql = new SQL();
      StringJoiner joiner = new StringJoiner(",");
      joiner.add(String.valueOf(Pdt.OProductType.GENERAL.getLine().getKey()));
      joiner.add(String.valueOf(Pdt.OProductType.GATHER.getLine().getKey()));
      sql.SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE, T.IS_HOT)
          .FROM(PdtProduct.class)
          .WHERE(T.SUPPLIER, "=?", supplier)
          .WHERE(T.STATE, "=?", Pdt.OState.ON)
          .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
          .WHERE(T.PRODUCT_TYPE, "in (" + joiner.toString() + ")");
      //            粘合O2O商品
      SQL o2oSql = new SQL();
      o2oSql
          .SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE, T.IS_HOT)
          .FROM(PdtProduct.class)
          .LEFT_JOIN(O2O_Product.class, O2O_Product.T.PRODUCT_ID, T.PKEY)
          .WHERE(O2O_Product.T.VERIFY_STATUS, "=?", O2O_ProductStatus.PASS)
          .WHERE(O2O_Product.T.STATUS, "=?", O2O_ProductStatus.ON)
          .WHERE(
              cat != -1,
              T.CATEGORY_DIY,
              "in(" + UsrProductCategoryDAO.Sellect.getAllChild(lang, supplier, cat) + ")");

      if (cat != -1) {
        sql.WHERE(
            T.CATEGORY_DIY,
            "in(" + UsrProductCategoryDAO.Sellect.getAllChild(lang, supplier, cat) + ")");
      }
      map.put("pageAll", Math.ceil((double) irille.pub.bean.Query.sql(sql).queryCount() / limit));
      String rankingBasis = " DESC ";
      if (orderType == 1) {
        rankingBasis = " ASC ";
      }
      switch (sort) {
        case 1:
          sql.ORDER_BY(T.SALES, rankingBasis);
          o2oSql.ORDER_BY(T.SALES, rankingBasis);
          break;
        case 2:
          sql.ORDER_BY(T.Favorite_Count, rankingBasis);
          o2oSql.ORDER_BY(T.Favorite_Count, rankingBasis);
          break;
        case 3:
          sql.ORDER_BY(T.IS_NEW, rankingBasis);
          o2oSql.ORDER_BY(T.IS_NEW, rankingBasis);
          break;
        case 4:
          sql.ORDER_BY(T.CUR_PRICE, rankingBasis);
          o2oSql.ORDER_BY(T.CUR_PRICE, rankingBasis);
          break;
        default:
          sql.ORDER_BY(T.IS_HOT, rankingBasis.trim().equalsIgnoreCase("desc") ? " ASC " : " DESC ");
          o2oSql.ORDER_BY(
              T.IS_HOT, rankingBasis.trim().equalsIgnoreCase("desc") ? " ASC " : " DESC ");
          break;
      }
      sql.ORDER_BY(T.PKEY, rankingBasis);
      o2oSql.ORDER_BY(T.PKEY, rankingBasis);
      sql.LIMIT(start, limit);
      o2oSql.LIMIT(start, limit);
      List<PdtView> list = new ArrayList<>();
      irille.pub.bean.Query.sql(sql)
          .queryList(PdtProduct.class)
          .forEach(
              pdtProduct -> {
                PdtView pdtView = new PdtView();
                String name = pdtProduct.getName();
                translateUtil.getAutoTranslate(pdtProduct, lang);
                pdtView.setPdt(pdtProduct);
                pdtView.setRewrite(pdtProduct.getPkey(), name);
                list.add(pdtView);
              });
      irille.pub.bean.Query.sql(o2oSql)
          .queryList(PdtProduct.class)
          .forEach(
              pdtProduct -> {
                PdtView pdtView = new PdtView();
                String name = pdtProduct.getName();
                translateUtil.getAutoTranslate(pdtProduct, lang);
                pdtView.setPdt(pdtProduct);
                pdtView.setRewrite(pdtProduct.getPkey(), name);
                list.add(pdtView);
              });
      map.put("items", list);
      return map;
    }

    /** 获取店铺首页产品(条件:/对应商家/新品/上架/通过审核/首页显示/普通产品 */
    public static List<PdtView> getIndexProduct(Integer supplier, FldLanguage.Language lang) {
      SQL sql =
          new SQL() {
            {
              SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE)
                  .FROM(PdtProduct.class)
                  .WHERE(T.SUPPLIER, "=?", supplier)
                  //                        .WHERE(T.IS_NEW, "=?", Sys.OYn.YES)
                  .WHERE(T.STATE, "=?", Pdt.OState.ON)
                  //                        TODO  商家目前不能设置首页商品所以展示去掉
                  //                        .WHERE(T.IS_INDEX, "=?", Sys.OYn.YES)
                  .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
                  // TODO 这里缺少O2O的商品列表
                  .WHERE(T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
                  .ORDER_BY(T.UPDATE_TIME, "DESC")
                  .LIMIT(0, 8);
            }
          };

      return irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class).stream()
          .map(
              bean ->
                  new PdtView() {
                    {
                      String name = bean.getName();
                      translateUtil.getAutoTranslate(bean, lang);
                      setPdt(bean);
                      setRewrite(bean.getPkey(), name);
                    }
                  })
          .collect(Collectors.toList());
    }
  }

  public static Page getProducts(Integer start, Integer limit, SeartchView seartch) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 0;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(T.PKEY, "pkey")
                .SELECT(T.PICTURE, "pdtImage")
                .SELECT(T.NAME, "pdtName")
                .SELECT(T.CODE, "pdtCode")
                .SELECT(PdtCat.T.NAME, "pdtCategory")
                .SELECT(T.MKT_PRICE, "mktPrice")
                .SELECT(T.CUR_PRICE, "curPrice")
                .SELECT(T.STOCK, "stock")
                .SELECT(T.MIN_OQ, "minOq")
                .SELECT(UsrSupplier.T.NAME, "supplierName")
                .SELECT(T.SOLD_TIME_B, "soldTimeB")
                .SELECT(T.IS_VERIFY, "isVerify")
                .SELECT(T.STATE, "state")
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.GROUP)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.GATHER)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "<>?", Pdt.OProductType.PrivateExpo)
                .WHERE(T.STATE, "<>?", OState.DELETE)
                .WHERE(T.STATE, "<>?", OState.MERCHANTDEL);
            if (seartch != null) {
              WHERE(
                      seartch.getProductType() != null,
                      T.PRODUCT_TYPE,
                      "=?",
                      seartch.getProductType())
                  .WHERE(
                      null != seartch.getProductCode(),
                      PdtProduct.T.CODE,
                      " like ?",
                      "%" + seartch.getProductCode() + "%")
                  .WHERE(
                      seartch.getShopName() != null,
                      UsrSupplier.T.NAME,
                      "like ?",
                      "%" + seartch.getShopName() + "%")
                  .WHERE(
                      seartch.getProductClassification() != null,
                      PdtCat.T.NAME,
                      "like ?",
                      "%" + seartch.getProductClassification() + "%")
                  .WHERE(
                      seartch.getProductStatus() != null,
                      T.STATE,
                      "=?",
                      seartch.getProductStatus());
              if (seartch.getProductType() == Pdt.OProductType.GENERAL.getLine().getKey()) {
                WHERE(seartch.getVerify() != null, T.IS_VERIFY, "=?", seartch.getVerify());
              } else {
                WHERE(
                    seartch.getVerify() != null,
                    O2O_Product.T.VERIFY_STATUS,
                    "=?",
                    seartch.getVerify());
              }
            }
            LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, T.SUPPLIER)
                .LEFT_JOIN(PdtCat.class, PdtCat.T.PKEY, T.CATEGORY)
                .LEFT_JOIN(O2O_Product.class, O2O_Product.T.PRODUCT_ID, T.PKEY);
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<ProductsView> list =
        irille.pub.bean.Query.sql(
                sql.ORDER_BY(PdtProduct.T.SOLD_TIME_B, " DESC ")
                    .ORDER_BY(PdtProduct.T.PKEY, " DESC ")
                    .LIMIT(start, limit))
            .queryMaps().stream()
            .map(
                o ->
                    new ProductsView() {
                      {
                        SQL sql =
                            new SQL() {
                              {
                                SELECT(O2O_Product.class)
                                    .FROM(O2O_Product.class)
                                    .WHERE(O2O_Product.T.PRODUCT_ID, "=?", (Integer) o.get("pkey"))
                                    .WHERE(T.PRODUCT_TYPE, "=?", Pdt.OProductType.O2O)
                                    .LEFT_JOIN(PdtProduct.class, T.PKEY, O2O_Product.T.PRODUCT_ID);
                              }
                            };
                        O2O_Product o2OProduct =
                            irille.pub.bean.Query.sql(sql).query(O2O_Product.class);
                        if (o2OProduct != null) {
                          setIsO2O("O2O商品");
                          switch (o2OProduct.getVerifyStatus()) {
                            case 0:
                              setIsVerify((byte) 0);
                              break;
                            case 3:
                              setIsVerify((byte) 1);
                              break;
                            case 4:
                              setIsVerify((byte) 2);
                              break;
                          }
                          if (o2OProduct.getStatus() == 1) {
                            setState((byte) 0);
                          } else if (o2OProduct.getStatus() == 2) {
                            setState((byte) 1);
                          }
                        } else {
                          setIsO2O("普通商品");
                          if ((Byte) o.get("isVerify") == 1) {
                            setIsVerify((byte) 1);
                          } else {
                            setIsVerify((byte) 2);
                          }
                          setState((Byte) o.get("state"));
                        }
                        setPkey((Integer) o.get("pkey"));
                        setPdtImage((String) o.get("pdtImage"));
                        setPdtName((String) o.get("pdtName"));
                        setPdtCode((String) o.get("pdtCode"));
                        setPdtCategory((String) o.get("pdtCategory"));
                        setMktPrice((BigDecimal) o.get("mktPrice"));
                        setCurPrice((BigDecimal) o.get("curPrice"));
                        setStock((Integer) o.get("stock"));
                        setMinOq((Integer) o.get("minOq"));
                        setSupplierName((String) o.get("supplierName"));
                        setSoldTimeB((Date) o.get("soldTimeB"));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * @param id
   * @param status 1:审核通过,2:审核失败
   * @param message
   */
  public static void review(Integer id, Byte status, String message) {
    SQL pdt =
        new SQL() {
          {
            SELECT(PdtProduct.class).FROM(PdtProduct.class).WHERE(T.PKEY, "=?", id);
          }
        };
    PdtProduct pp = irille.pub.bean.Query.sql(pdt).query(PdtProduct.class);
    O2O_Product o2oPdt = null;
    if (pp == null) {
      throw new WebMessageException(ReturnCode.service_gone, "产品不存在");
    }
    if (pp.getProductType() == Pdt.OProductType.O2O.getLine().getKey()) {
      SQL o2o =
          new SQL() {
            {
              SELECT(O2O_Product.class)
                  .FROM(O2O_Product.class)
                  .WHERE(O2O_Product.T.PRODUCT_ID, "=?", id);
            }
          };
      o2oPdt = irille.pub.bean.Query.sql(o2o).query(O2O_Product.class);
      if (status == 0) {
        o2oPdt.setMessage(message);
        o2oPdt.stVerifyStatus(O2O_ProductStatus.Failed);
      } else {
        o2oPdt.stVerifyStatus(O2O_ProductStatus.PASS);
      }
      o2oPdt.upd();
    } else {
      pp.setIsVerify(status);
      if (status == 0) {
        pp.setTab3(message);
      }
      pp.upd();
    }

    // TODO 产品审核发送站内信
    IPMMessageService messageService = new PMMessageServiceImp();
    UsrSupplier supplier = pp.gtSupplier();
    messageService.send(OTempType.PROD_APPR_NOTICE, supplier, null, pp, supplier, o2oPdt);
  }

  /**
   * @param id
   * @param status 0:下架,1:上架
   * @param message
   */
  public static void lower(Integer id, Byte status, String message) {
    SQL pdt =
        new SQL() {
          {
            SELECT(PdtProduct.class).FROM(PdtProduct.class).WHERE(T.PKEY, "=?", id);
          }
        };
    PdtProduct pp = irille.pub.bean.Query.sql(pdt).query(PdtProduct.class);
    if (pp == null) {
      throw new WebMessageException(ReturnCode.service_gone, "产品不存在");
    }
    if (pp.getProductType() == Pdt.OProductType.O2O.getLine().getKey()) {
      SQL o2o =
          new SQL() {
            {
              SELECT(O2O_Product.class)
                  .FROM(O2O_Product.class)
                  .WHERE(O2O_Product.T.PRODUCT_ID, "=?", id);
            }
          };
      O2O_Product o2oPdt = irille.pub.bean.Query.sql(o2o).query(O2O_Product.class);
      if (status == 0) {
        o2oPdt.setMessage(message);
        o2oPdt.stStatus(O2O_ProductStatus.OFF);
      } else {
        o2oPdt.stStatus(O2O_ProductStatus.ON);
      }
      o2oPdt.upd();
    } else {
      pp.stIsVerify(false);
      pp.setState(status);
      if (status == 0) {
        pp.setTab3(message);
      }
      pp.upd();
    }
  }
}
