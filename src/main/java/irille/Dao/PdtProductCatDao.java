package irille.Dao;

import irille.pub.bean.query.BeanQuery;
import irille.shop.usr.UsrProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time: 11:35
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
