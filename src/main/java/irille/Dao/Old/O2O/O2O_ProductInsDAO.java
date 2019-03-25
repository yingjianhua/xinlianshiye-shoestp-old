package irille.Dao.Old.O2O;

import irille.Entity.O2O.O2O_Product;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/**
 * * 普通O2O商品
 *
 * @date 2019/1/26 15:58
 */
public class O2O_ProductInsDAO extends IduIns<O2O_ProductInsDAO, O2O_Product> {
  @Override
  public void before() {
    getB().setUpdatedTime(Env.getSystemTime());
    super.before();
  }
}
