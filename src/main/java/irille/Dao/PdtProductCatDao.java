package irille.Dao;

import java.util.List;
import java.util.Map;

import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrProductCategory;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time: 11:35 */
public class PdtProductCatDao {

  public List<Map> getProductSEOs(Integer supplier) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            PdtProduct.T.PKEY,
            PdtProduct.T.SEO_TITLE,
            PdtProduct.T.SEO_KEYWORD,
            PdtProduct.T.SEO_DESCRIPTION)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", supplier);
    return query.queryMaps();
  }

  public Object getSEO(Integer product) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PdtProduct.T.SEO_TITLE, PdtProduct.T.SEO_KEYWORD, PdtProduct.T.SEO_DESCRIPTION)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.PKEY, "=?", product);
    return query.query();
  }

  /**
   * @Description: 获取产品分类
   *
   * @date 2018/11/8 11:35
   * @author lijie@shoestp.cn
   */
  public List<Map> getPdtCatBySupplierId(int i) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            UsrProductCategory.T.PKEY,
            UsrProductCategory.T.NAME,
            UsrProductCategory.T.CATEGORY_UP,
            UsrProductCategory.T.ENABLED)
        .FROM(UsrProductCategory.class)
        .WHERE(UsrProductCategory.T.SUPPLIER, "=?", i);
    return query.queryMaps();
  }

  /**
   * @Description: 获取产品分类
   *
   * @date 2018/11/8 11:35
   * @author lijie@shoestp.cn
   */
  public Integer getPdtCatCountBySupplierId(int i) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(
            UsrProductCategory.T.PKEY,
            UsrProductCategory.T.NAME,
            UsrProductCategory.T.CATEGORY_UP,
            UsrProductCategory.T.ENABLED)
        .FROM(UsrProductCategory.class)
        .WHERE(UsrProductCategory.T.SUPPLIER, "=?", i);
    return query.queryCount();
  }

  /** 获取所有一级分类 */
  public List<PdtCat> pList() {
    SQL sql = new SQL();
    sql.SELECT(PdtCat.class)
        .FROM(PdtCat.class)
        .WHERE(PdtCat.T.DELETED, "=?", Sys.OYn.NO.getLine().getKey())
        .WHERE(PdtCat.T.CATEGORY_UP, " IS NULL ");
    return Query.sql(sql).queryList(PdtCat.class);
  }
}
