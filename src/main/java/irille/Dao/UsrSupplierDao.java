package irille.Dao;

import irille.Aops.Caches;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.Page_supplierProductView;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.util.SEOUtils;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 16:36
 */
public class UsrSupplierDao {

  /**
   * @Description: 获取用户信息
   * @date 2018/11/5 17:30
   * @author lijie@shoestp.cn
   */
  public Map<String, Object> getInfoById(int i) {
    SQL sql = new SQL();
    return Query.sql(sql.SELECT(
        UsrSupplier.T.PKEY,
        UsrSupplier.T.NAME,
        UsrSupplier.T.LOGO
    ).SELECT(
        UsrSupplierRole.T.NAME.getFld().getTb().getCode() + "." + UsrSupplierRole.T.NAME.getFld()
            .getCodeSqlField() + " as  role_name"
    ).LEFT_JOIN(UsrSupplierRole.class, UsrSupplier.T.ROLE, UsrSupplierRole.T.PKEY)
        .FROM(UsrSupplier.class)).queryMap();

  }

  public UsrSupplier getUsrShopSetting(int i) {
    return BeanBase.load(UsrSupplier.class, i);
  }


  /***
   * 获取Supplier页面数据       返回供应商列表及三个商品
   *
   * usr_UsrSupplier
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/19 16:26
   */
  public List<SupplierListView> getSupplierListAndPdtList(int start, int limit, String where) {
    BeanQuery query = new BeanQuery();
    query.SELECT(
        UsrSupplier.T.PKEY,
        UsrSupplier.T.LOGO,
        UsrSupplier.T.PROD_PATTERN, //公司生产模式
        UsrSupplier.T.NAME //公司名称
    )
        .FROM(UsrSupplier.class)
        .limit(start, limit)
        .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
        .ORDER_BY(UsrSupplier.T.IS_AUTH, "desc")
        .ORDER_BY(UsrSupplier.T.SORT, "desc");

    if (where != null && !where.equalsIgnoreCase("-1")) {
      query
          .LEFT_JOIN(UsrProductCategory.class, UsrSupplierCategory.T.SHOW_NAME,
              UsrSupplier.T.CATEGORY)
          .WHERE(UsrSupplierCategory.T.PKEY, "=?", where);
    }
    List<Map> tempMap = query.queryMaps();
    List<SupplierListView> queryResult = new ArrayList();
    tempMap.forEach(map -> {
      queryResult.add(org.start2do.SetBean.SetBeans.set(map, SupplierListView.class));
    });

    List<SupplierListView> result = new ArrayList();
    for (int i = 0; i < queryResult.size(); i++) {
      SupplierListView page_supplierDto = queryResult.get(i);
      List proDucts = new ArrayList();
      BeanQuery query1 = new BeanQuery();
      List<Map> t = query1.SELECT(
          PdtProduct.T.PKEY,
          PdtProduct.T.PICTURE,
          PdtProduct.T.NAME
      ).FROM(PdtProduct.class)
          .WHERE(PdtProduct.T.SUPPLIER, "=?", page_supplierDto.getPkey())
          .WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES)
          .ORDER_BY(PdtProduct.T.MY_ORDER, "desc")
          .limit(0, 3).queryMaps();
      page_supplierDto.setProDuctCount(
          query1.queryCount()
      );
      t.forEach(objects -> {
        org.start2do.SetBean.SetBeans
            .set(objects, Page_supplierProductView.class, (objects1, page_supplierProductView) -> {
              objects1.put("rewrit", SEOUtils
                  .getPdtProductTitle(Integer.parseInt(String.valueOf(objects1.get("pkey"))),
                      String.valueOf(objects1.get("name"))));
            }, null);
      });
      page_supplierDto.setProDuctDtos(
          proDucts
      );
      result.add(page_supplierDto);
    }
    return result;
  }


  /***
   *  首页 供应商列表
   *  审核  通过了  店铺 可以显示
   *  认证只是有个认证标识
   *
   * 主键 公司图片  图片链接 公司名
   * @return
   */
  @Caches
  public List getSupplier(int start, int limit) {
    BeanQuery query = new BeanQuery();
    query.SELECT(
        UsrSupplier.T.PKEY,
        UsrSupplier.T.LOGO,
        UsrSupplier.T.COMPANY_PHOTO,
        UsrSupplier.T.COMPANY_PHOTO_LINK,
        UsrSupplier.T.NAME
    ).FROM(UsrSupplier.class)
        .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
        .WHERE("logo != '' and company_photo!=''")
        .ORDER_BY(UsrSupplier.T.SORT, "asc")
        .limit(start, limit)
    ;
    return query.queryMaps();
  }
}
