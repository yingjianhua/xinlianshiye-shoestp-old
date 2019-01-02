package irille.Dao;

import irille.pub.bean.query.BeanQuery;
import irille.pub.tb.FldLanguage;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/8
 * Time: 11:35
 */
public class PdtProductCatDao {

    /**
     * @Description: 获取产品分类
     * @date 2018/11/8 11:35
     * @author lijie@shoestp.cn
     */
    public List<Map> getPdtCatBySupplierId(int i) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                UsrProductCategory.T.PKEY,
                UsrProductCategory.T.NAME,
                UsrProductCategory.T.CATEGORY_UP,
                UsrProductCategory.T.ENABLED
        ).FROM(UsrProductCategory.class)
                .WHERE(UsrProductCategory.T.SUPPLIER, "=?", i);
        return query.queryMaps();
    }

    public List<Map> getProductSEOs(Integer supplier){
        BeanQuery query = new BeanQuery();
        query.SELECT(PdtProduct.T.PKEY,
                PdtProduct.T.SEO_TITLE,
                PdtProduct.T.SEO_KEYWORD,
                PdtProduct.T.SEO_DESCRIPTION)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.SUPPLIER,"=?",supplier);
        return query.queryMaps();
    }

    public Object getSEO(Integer product){
        BeanQuery query = new BeanQuery();
        query.SELECT(PdtProduct.T.SEO_TITLE,
                PdtProduct.T.SEO_KEYWORD,
                PdtProduct.T.SEO_DESCRIPTION)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.PKEY,"=?",product);
        return query.query();
    }
  /**
   * @Description: 获取产品分类
   * @date 2018/11/8 11:35
   * @author lijie@shoestp.cn
   */
  public List<Map> getPdtCatBySupplierId(int i) {
    BeanQuery query = new BeanQuery();
    query.SELECT(
        UsrProductCategory.T.PKEY,
        UsrProductCategory.T.NAME,
        UsrProductCategory.T.CATEGORY_UP,
        UsrProductCategory.T.ENABLED
    ).FROM(UsrProductCategory.class)
        .WHERE(UsrProductCategory.T.SUPPLIER, "=?", i);
    return query.queryMaps();
  }

  /**
   * @Description: 获取产品分类
   * @date 2018/11/8 11:35
   * @author lijie@shoestp.cn
   */
  public Integer getPdtCatCountBySupplierId(int i) {
    BeanQuery query = new BeanQuery();
    query.SELECT(
        UsrProductCategory.T.PKEY,
        UsrProductCategory.T.NAME,
        UsrProductCategory.T.CATEGORY_UP,
        UsrProductCategory.T.ENABLED
    ).FROM(UsrProductCategory.class)
        .WHERE(UsrProductCategory.T.SUPPLIER, "=?", i);
    return query.queryCount();
  }
}
