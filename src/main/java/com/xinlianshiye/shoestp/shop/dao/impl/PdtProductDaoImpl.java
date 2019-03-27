/** */
package com.xinlianshiye.shoestp.shop.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;
import com.xinlianshiye.shoestp.shop.view.pdt.SortView;

import irille.Dao.PdtProductDao;
import irille.Entity.SVS.SVSInfo;
import irille.core.sys.Sys;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

/** @author liyichao */
public class PdtProductDaoImpl {
  @Inject private PdtProductDao pdtProductDao;

  @Inject ObjectMapper om;

  public Page list(
      UsrPurchase purchase,
      ProdSearchView search,
      Integer start,
      Integer limit,
      Language language) {

    String o2oSQL =
        "SELECT"
            + "	O2OProduct.product_id as prodId,count(*) as o2oCount"
            + "FROM"
            + "	o2_o__product O2OProduct"
            + "	LEFT JOIN o2_o__activity O2OActivity ON O2OActivity.pkey = O2OProduct.activity_id "
            + "	LEFT JOIN o2_o__map O2OMap ON O2OMap.pkey = O2OActivity.address"
            + "WHERE"
            + "	O2OActivity.STATUS = 2 "
            + "	AND ( O2OProduct.STATUS = 2 OR O2OProduct.STATUS = 4 OR O2OProduct.STATUS = 5 ) "
            + "	AND O2OMap.name like '%罗马尼亚%'"
            + "GROUP BY O2OProduct.product_id"
            + "	) O2ORecord"
            + "	ON O2ORecord.prodId = PdtProduct.pkey";

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
    sql.JSON_EXTRACT(PltCountry.T.NAME, language.name());
    sql.JSON_EXTRACT(PltProvince.T.NAME, language.name());
    sql.SELECT(PdtCat.T.PKEY, "pdtCatPkey");
    sql.SELECT(PdtCat.T.NAME, "pdtCatName");
    sql.SELECT(PdtCat.T.CATEGORY_UP, "pdtCatUp");
    sql.SELECT("IF(O2ORecord.o2oCount > 0,1,0) AS o2o");
    sql.FROM(PdtProduct.class);
    sql.LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
    sql.LEFT_JOIN(PltCountry.class, UsrSupplier.T.COUNTRY, PltCountry.T.PKEY);
    sql.LEFT_JOIN(PltProvince.class, UsrSupplier.T.PROVINCE, PltProvince.T.PKEY);
    sql.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
    sql.LEFT_JOIN(o2oSQL);
    sql.WHERE(PdtProduct.T.IS_VERIFY, " =? ", Sys.OYn.YES.getLine().getKey());
    sql.WHERE(PdtProduct.T.STATE, " =? ", Pdt.OState.ON.getLine().getKey());
    sql.WHERE(search.getSupplier() != null, PdtProduct.T.SUPPLIER, "=?", search.getSupplier());
    sql.WHERE(null != search.getGrade(), SVSInfo.T.GRADE, " =? ", search.getGrade());
    sql.WHERE(
        null != search.getCategory(),
        PdtProduct.T.CATEGORY,
        " in(?)",
        pdtProductDao.getCatsNodeByCatId(search.getCategory()).stream()
            .map(
                o -> {
                  return String.valueOf(o);
                })
            .collect(Collectors.joining(",")));
    sql.WHERE(
        null != search.getMinCurPrice(), PdtProduct.T.CUR_PRICE, " >=? ", search.getMinCurPrice());
    sql.WHERE(
        null != search.getMaxCurPrice(), PdtProduct.T.CUR_PRICE, " <=? ", search.getMaxCurPrice());
    sql.WHERE(null != search.getMinOq(), PdtProduct.T.MIN_OQ, " <=? ", search.getMinOq());
    if (null != search.getIsO2o()) {
      sql.WHERE(" O2ORecord.o2oCount <= 0 ");
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
    if (null != search.getSort()) {
      try {
        List<SortView> sorts =
            om.readValue(
                search.getSort(),
                om.getTypeFactory().constructParametricType(ArrayList.class, SortView.class));
        
      } catch (IOException e) { // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    sql.ORDER_BY(PdtProduct.T.UPDATE_TIME, " DESC ");

    sql.LIMIT(start, limit);

    String sql1 =
        "SELECT"
            + "	PdtProduct.pkey AS pdtPkey,"
            + "IF(O2ORecord.o2oCount > 0,1,0) AS o2o,"
            + "	PdtProduct.NAME -> '$.de' AS pdtName,"
            + "	PdtProduct.cur_price,"
            + "	PdtProduct.min_oq,"
            + "	PdtProduct.category,"
            + "	PdtProduct.norm_attr,"
            + "	PdtProduct.product_type,"
            + "	PdtProduct.picture,"
            + "	UsrSupplier.pkey AS supPkey,"
            + "	UsrSupplier.show_name AS supName,"
            + "	UsrSupplier.STATUS,"
            + "	UsrSupplier.auth_time,"
            + "	PltCountry.NAME -> '$.de' AS pltCountry,"
            + "	PltProvince.NAME -> '$.de' AS pltProvince,"
            + "	PdtCat.pkey AS pdtCatPkey,"
            + "	PdtCat.NAME -> '$.de' AS pdtCatName,"
            + "	PdtCat.category_up AS pdtCatUp "
            + "FROM"
            + "	pdt_product PdtProduct"
            + " LEFT JOIN usr_supplier UsrSupplier ON UsrSupplier.pkey = PdtProduct.supplier"
            + "	LEFT JOIN plt_country PltCountry ON PltCountry.pkey = UsrSupplier.country"
            + "	LEFT JOIN plt_province PltProvince ON PltProvince.pkey = UsrSupplier.province"
            + "	LEFT JOIN pdt_cat PdtCat ON PdtCat.pkey = PdtProduct.category"
            + "	LEFT JOIN s_v_s_info SVSInfo ON SVSInfo.supplier = UsrSupplier.pkey"
            + "LEFT JOIN ("
            + "SELECT"
            + "	O2OProduct.product_id as prodId,count(*) as o2oCount"
            + "FROM"
            + "	o2_o__product O2OProduct"
            + "	LEFT JOIN o2_o__activity O2OActivity ON O2OActivity.pkey = O2OProduct.activity_id "
            + "	LEFT JOIN o2_o__map O2OMap ON O2OMap.pkey = O2OActivity.address"
            + "WHERE"
            + "	O2OActivity.STATUS = 2 "
            + "	AND ( O2OProduct.STATUS = 2 OR O2OProduct.STATUS = 4 OR O2OProduct.STATUS = 5 ) "
            + "	AND O2OMap.name like '%罗马尼亚%'"
            + "GROUP BY O2OProduct.product_id"
            + "	) O2ORecord"
            + "	ON O2ORecord.prodId = PdtProduct.pkey";

    String where =
        "-- 	WHERE UsrSupplier.pkey = 8"
            + "	WHERE SVSInfo.grade = 1"
            + "	AND PdtProduct.category IN (518,391,512)"
            + "	AND PdtProduct.cur_price >= 6"
            + "	AND PdtProduct.cur_price <= 9"
            + "	AND PdtProduct.min_oq <= 600"
            + "-- 	AND O2ORecord.o2oCount = 0"
            + "-- 	AND (PdtProduct.name like '%州富罗迷鞋%' OR PdtProduct.code like '%州富罗迷鞋%' OR PdtProduct.sku like '%州富罗迷鞋%' OR UsrSupplier.name like '%州富罗迷鞋%')"
            + "-- 	AND UsrSupplier.main_sales_area like '%不限%'";

    String orderBy =
        "  ORDER BY PdtProduct.cur_price DESC,PdtProduct.pkey DESC,PdtProduct.update_time DESC";

    String lim = "LIMIT 0,5";
    return null;
  }
}
