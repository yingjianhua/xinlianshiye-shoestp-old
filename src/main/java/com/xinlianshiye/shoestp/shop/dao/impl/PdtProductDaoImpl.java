/** */
package com.xinlianshiye.shoestp.shop.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.logging.log4j.util.Strings;
import org.json.JSONException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;
import com.xinlianshiye.shoestp.shop.view.pdt.SortView;

import irille.Dao.PdtProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Map;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtAttrLineDAO;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtTargetMarket;
import irille.shop.pdt.PdtTieredPricing;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.pdt.PdtSearchView;

/** @author liyichao */
public class PdtProductDaoImpl implements com.xinlianshiye.shoestp.shop.dao.PdtProductDao {
  @Inject private PdtProductDao pdtProductDao;

  @Inject ObjectMapper om;

  private static final Integer men = 373; // PdtCat男鞋顶级分类pkey
  private static final Integer women = 380; // PdtCat女鞋顶级分类pkey
  private static final int inner = 137; // PdtAttr内部材料pkey
  private static final int season = 136; // PdtAttr适合季节pkey
  private static final int sole = 138; // PdtAttr鞋底材质pkey
  private static final int upper = 139; // PdtAttr鞋面材料pkey
  private static final int colsed = 152; // PdtAttr闭合方式pkey

  @Override
  public Page list(
      UsrPurchase purchase,
      ProdSearchView search,
      Integer start,
      Integer limit,
      Language language) {
    if (null == search) {
      search = new ProdSearchView();
    }

    String o2oSQL =
        "(SELECT"
            + "	O2OProduct.product_id as prodId,count(*) as o2oCount "
            + " FROM"
            + "	o2_o__product O2OProduct"
            + "	LEFT JOIN o2_o__activity O2OActivity ON O2OActivity.pkey = O2OProduct.activity_id "
            + "	LEFT JOIN o2_o__map O2OMap ON O2OMap.pkey = O2OActivity.address"
            + " WHERE"
            + "	O2OActivity.STATUS = 2 "
            + "	AND ( O2OProduct.STATUS = 2 OR O2OProduct.STATUS = 4 OR O2OProduct.STATUS = 5 ) "
            + " GROUP BY O2OProduct.product_id"
            + "	) O2ORecord"
            + "	ON O2ORecord.prodId = PdtProduct.pkey";

    SQL o2oSql = new SQL();
    o2oSql.SELECT(" IF(count(*) > 0,1,0)");
    o2oSql.FROM(O2O_Product.class);
    o2oSql.LEFT_JOIN(O2O_Activity.class, O2O_Activity.T.PKEY, O2O_Product.T.ACTIVITY_ID);
    o2oSql.LEFT_JOIN(O2O_Map.class, O2O_Map.T.PKEY, O2O_Activity.T.ADDRESS);
    o2oSql.WHERE(O2O_Activity.T.STATUS, " =? ", O2O_ActivityStatus.ACTIVITY.getLine().getKey());
    o2oSql.WHERE(
        "( "
            + O2O_Product.class.getSimpleName()
            + "."
            + O2O_Product.T.STATUS.getFld().getCodeSqlField()
            + " = ? OR "
            + O2O_Product.class.getSimpleName()
            + "."
            + O2O_Product.T.STATUS.getFld().getCodeSqlField()
            + " = ? OR "
            + O2O_Product.class.getSimpleName()
            + "."
            + O2O_Product.T.STATUS.getFld().getCodeSqlField()
            + " = ? )",
        O2O_ProductStatus.ON.getLine().getKey(),
        O2O_ProductStatus.Failed.getLine().getKey(),
        O2O_ProductStatus.WAITOFF.getLine().getKey());

    o2oSql.WHERE(
        null != search.getO2oAddress(),
        O2O_Map.T.NAME,
        " like ? ",
        "%" + search.getO2oAddress() + "%");
    o2oSql.GROUP_BY(O2O_Product.T.PRODUCT_ID);
    o2oSql.HAVING(O2O_Product.T.PRODUCT_ID, "= pdtPkey");

    SQL maxCurPrice = new SQL();
    maxCurPrice.SELECT(
        " MAX("
            + PdtTieredPricing.class.getSimpleName()
            + "."
            + PdtTieredPricing.T.CUR_PRICE.getFld().getCodeSqlField()
            + ")");
    maxCurPrice.FROM(PdtTieredPricing.class);
    maxCurPrice.WHERE(PdtTieredPricing.T.PRODUCT, " =pdtPkey ");
    SQL minCurPrice = new SQL();
    minCurPrice.SELECT(
        " MIN("
            + PdtTieredPricing.class.getSimpleName()
            + "."
            + PdtTieredPricing.T.CUR_PRICE.getFld().getCodeSqlField()
            + ")");
    minCurPrice.FROM(PdtTieredPricing.class);
    minCurPrice.WHERE(PdtTieredPricing.T.PRODUCT, " =pdtPkey ");
    SQL favorite = new SQL();
    favorite.SELECT(" IF(count(*)>0,1,0) ");
    favorite.FROM(UsrFavorites.class);
    favorite.WHERE(UsrFavorites.T.PRODUCT, " =pdtPkey ");
    favorite.WHERE(UsrFavorites.T.PURCHASE, "=?", null == purchase ? -1 : purchase.getPkey());

    SQL sql = new SQL();
    sql.SELECT(
        " DISTINCT "
            + PdtProduct.class.getSimpleName()
            + "."
            + PdtProduct.T.PKEY.getFld().getCodeSqlField()
            + " AS pdtPkey ");
    sql.SELECT(PdtProduct.T.NAME, "pdtName");
    sql.SELECT(maxCurPrice, "maxCurPrice");
    sql.SELECT(minCurPrice, "minCurPrice");
    sql.SELECT(favorite, "favorite");
    //    sql.SELECT(o2oSql, "o2o");
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
    sql.SELECT("IF(O2ORecord.o2oCount > 0,1,0) AS o2o");
    sql.FROM(PdtProduct.class);
    sql.LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
    sql.LEFT_JOIN(PltCountry.class, UsrSupplier.T.COUNTRY, PltCountry.T.PKEY);
    sql.LEFT_JOIN(PltProvince.class, UsrSupplier.T.PROVINCE, PltProvince.T.PKEY);
    sql.LEFT_JOIN(SVSInfo.class, SVSInfo.T.SUPPLIER, UsrSupplier.T.PKEY);
    sql.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
    sql.LEFT_JOIN(o2oSQL);
    sql.WHERE(
        null != purchase,
        UsrFavorites.T.PURCHASE,
        " =? ",
        null == purchase ? -1 : purchase.getPkey());
    sql.WHERE(PdtProduct.T.IS_VERIFY, " =? ", Sys.OYn.YES.getLine().getKey());
    sql.WHERE(PdtProduct.T.STATE, " =? ", Pdt.OState.ON.getLine().getKey());
    sql.WHERE(search.getSupplier() != null, PdtProduct.T.SUPPLIER, "=?", search.getSupplier());
    sql.WHERE(null != search.getGrade(), SVSInfo.T.GRADE, " in( ? )", search.getGrade());
    if (null != search.getGrade()) {
      String where =
          SVSInfo.class.getSimpleName()
              + "."
              + SVSInfo.T.GRADE.getFld().getCodeSqlField()
              + " IN(?) ";
      List<Integer> grades =
          Arrays.asList(search.getGrade().split(",")).stream()
              .map(
                  b -> {
                    return Integer.valueOf(b);
                  })
              .collect(Collectors.toList());
      boolean flag = false;
      if (grades.contains(Integer.valueOf(SVSGradeType.NotAvailable.getLine().getKey()))) {
        flag = true;
        where +=
            " OR "
                + SVSInfo.class.getSimpleName()
                + "."
                + SVSInfo.T.GRADE.getFld().getCodeSqlField()
                + " IS NULL ";
      }
      if (flag) {
        where = "(" + where + ")";
      }

      sql.WHERE(where, search.getGrade());
    }
    //    if (null != search.getGrade()
    //        && !search.getGrade().equals(SVSGradeType.NotAvailable.getLine().getKey())) {
    //      sql.WHERE(SVSInfo.T.GRADE, " =? ", search.getGrade());
    //    }
    //    if (null != search.getGrade()
    //        && search.getGrade().equals(SVSGradeType.NotAvailable.getLine().getKey())) {
    //      sql.WHERE(
    //          "("
    //              + SVSInfo.class.getSimpleName()
    //              + "."
    //              + SVSInfo.T.GRADE.getFld().getCodeSqlField()
    //              + " =? OR "
    //              + SVSInfo.class.getSimpleName()
    //              + "."
    //              + SVSInfo.T.GRADE.getFld().getCodeSqlField()
    //              + " IS NULL )",
    //          search.getGrade());
    //    }
    List<Integer> catsitems =
        pdtProductDao.getCatsNodeByCatId(null == search.getCategory() ? 0 : search.getCategory());
    String cats = null;
    if (null != catsitems) {
      cats =
          catsitems.stream()
              .map(
                  o -> {
                    return String.valueOf(o);
                  })
              .collect(Collectors.joining(","));
    }

    sql.WHERE(null != cats, PdtProduct.T.CATEGORY, " in(?)", cats);
    sql.WHERE(
        null != search.getMinCurPrice(), PdtProduct.T.CUR_PRICE, " >=? ", search.getMinCurPrice());
    sql.WHERE(
        null != search.getMaxCurPrice(), PdtProduct.T.CUR_PRICE, " <=? ", search.getMaxCurPrice());
    sql.WHERE(null != search.getMinOq(), PdtProduct.T.MIN_OQ, " <=? ", search.getMinOq());
    if (null != search.getIsO2o()) {
      sql.WHERE(" O2ORecord.o2oCount > 0 ");
    }
    if (null != search.getKeywords()) {
      String keywords = "%" + search.getKeywords() + "%";
      sql.WHERE(
          "("
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.NAME.getFld().getCodeSqlField()
              + " like ? OR "
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.CODE.getFld().getCodeSqlField()
              + " like ? OR "
              + PdtProduct.class.getSimpleName()
              + "."
              + PdtProduct.T.SKU.getFld().getCodeSqlField()
              + " like ? OR "
              + UsrSupplier.class.getSimpleName()
              + "."
              + UsrSupplier.T.NAME.getFld().getCodeSqlField()
              + " like ? ) ",
          keywords,
          keywords,
          keywords,
          keywords);
    }
    if (null != search.getExport()) {
      sql.LEFT_JOIN(PdtTargetMarket.class, PdtTargetMarket.T.PRODUCT, PdtProduct.T.PKEY);
      sql.WHERE(PdtTargetMarket.T.COUNTRY, "  IN(?) ", search.getExport());
      //      List<String> strs = Arrays.asList(search.getExport().split(","));
      //      String s = "";
      //      for (int i = 0; i < strs.size(); i++) {
      //        s +=
      //            " FIND_IN_SET( ? ," // strs.get(i)
      //                + PdtProduct.class.getSimpleName()
      //                + "."
      //                + PdtProduct.T.TARGETED_MARKET.getFld().getCodeSqlField()
      //                + ") ";
      //        if (i != strs.size() - 1) {
      //          s += " OR ";
      //        }
      //      }
      //      sql.WHERE("(" + s + ")", search.getExport().split(","));
    }

    if (null != search.getSort()) {
      try {
        List<SortView> sorts =
            om.readValue(
                search.getSort(),
                om.getTypeFactory().constructParametricType(ArrayList.class, SortView.class));

        sorts =
            sorts.stream()
                .sorted(Comparator.comparing(SortView::getSort).reversed())
                .collect(Collectors.toList());
        for (SortView s : sorts) {
          if (s.getName().equals(PdtProduct.T.CUR_PRICE.getFld().getCode())) {
            if (null != s.getRule() && SortView.sortMap.containsKey(s.getRule())) {
              sql.ORDER_BY(PdtProduct.T.CUR_PRICE, SortView.sortMap.get(s.getRule()));
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    sql.ORDER_BY(PdtProduct.T.UPDATE_TIME, " DESC ");
    Integer count = Query.sql(sql).queryMaps().size();
    sql.LIMIT(start, limit);
    List<PdtSearchView> items = toView(Query.sql(sql).queryMaps(), language);
    return new Page(items, start, limit, count);
  }

  public List<PdtSearchView> toView(List<Map<String, Object>> data, Language language) {

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
    return data.stream()
        .map(
            d -> {
              PdtSearchView view = new PdtSearchView();
              view.setSupId(GetValue.get(d, "supPkey", Integer.class, null));
              Integer pdtPkey = GetValue.get(d, "pdtPkey", Integer.class, null);
              view.setPdtId(pdtPkey);
              BigDecimal p =
                  GetValue.get(d, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO);
              view.setPrice(p);
              view.setMinCurPrice(GetValue.get(d, "minCurPrice", BigDecimal.class, p));
              view.setMaxCurPrice(GetValue.get(d, "maxCurPrice", BigDecimal.class, p));
              view.setPdtName(GetValue.get(d, "pdtName", String.class, null));
              view.setMinOrder(GetValue.get(d, PdtProduct.T.MIN_OQ, Integer.class, null));
              List<PdtSpec> specs =
                  BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT + " =? ", false, pdtPkey);
              ArrayList<String> stringList = new ArrayList<>();
              for (PdtSpec spec : specs) {
                if (null == spec.getPics()) {
                  continue;
                }
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
                    GetValue.getFirstImage(GetValue.get(d, PdtProduct.T.PICTURE, String.class, ""));
                //                if (!seasonList.contains(t)) stringList.add(0, t);
                view.setPicture(Strings.join(stringList, ','));
              } else {
                view.setPicture(GetValue.get(d, PdtProduct.T.PICTURE, String.class, ""));
              }
              String pdtCatName =
                  d.get("pdtCatName") == null ? null : d.get("pdtCatName").toString();
              Integer gender = 0;
              if (pdtCatName.indexOf("男") != -1) {
                gender = 1;
              } else if (pdtCatName.indexOf("女") != -1) {
                gender = 2;
              }
              if (gender == 0) {
                Integer pdtCatPkey = Integer.parseInt(d.get("pdtCatPkey").toString());
                Integer pdtCatUp =
                    Integer.parseInt(
                        d.get("pdtCatUp") == null ? "0" : d.get("pdtCatUp").toString());
                if (menList.contains(pdtCatUp)
                    || menList.contains(pdtCatPkey)
                    || pdtCatPkey.equals(men)) gender = 1;
                else if (womenList.contains(pdtCatUp)
                    || womenList.contains(pdtCatPkey)
                    || pdtCatPkey.equals(women)) gender = 2;
                else gender = 3;
              }
              view.setGender(gender);

              Object attr = d.get(PdtProduct.T.NORM_ATTR.getFld().getCodeSqlField());
              String[] attrSplit = {};
              if (attr == null) attrSplit = null;
              else attrSplit = attr.toString().split(",");
              view.setInner(getAttr(innerList, attrSplit, language));
              view.setSeason(getAttr(seasonList, attrSplit, language));
              view.setSole(getAttr(soleList, attrSplit, language));
              view.setClosed(getAttr(colsedList, attrSplit, language));
              view.setRewrite(SEOUtils.getPdtProductTitle(view.getPdtId(), view.getPdtName()));
              view.setUpper(getAttr(upperList, attrSplit, language));

              view.setOriginCountry(String.valueOf(d.get("pltCountry")));
              view.setOriginProvince(String.valueOf(d.get("pltProvince")));
              Integer favorites = GetValue.get(d, "favorites", Integer.class, 0);
              view.setEshrine(favorites.equals(0) ? false : true);

              view.setPdtType(GetValue.get(d, PdtProduct.T.PRODUCT_TYPE, Integer.class, null));

              view.setSupName(GetValue.get(d, "supName", String.class, null));

              SimpleDateFormat sim = new SimpleDateFormat("yyyy");
              Date authDate = GetValue.get(d, UsrSupplier.T.AUTH_TIME, Date.class, new Date());
              String authFormat = sim.format(authDate);
              String dateFormat = sim.format(new Date());
              Integer enter = Integer.parseInt(authFormat) - Integer.parseInt(dateFormat);
              view.setEnter(enter <= 0 ? 1 : enter + 1);
              return view;
            })
        .collect(Collectors.toList());
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
}
