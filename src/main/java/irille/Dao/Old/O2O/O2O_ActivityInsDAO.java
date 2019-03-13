package irille.Dao.Old.O2O;

import irille.Entity.O2O.O2O_Activity;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/**
 * * O2O活动
 *
 * @author Lijie<HelloBox @ outlook.com>
 * @date 2019/1/26 15:55
 */
public class O2O_ActivityInsDAO extends IduIns<O2O_ActivityInsDAO, O2O_Activity> {
  @Override
  public void before() {
    getB().setUpdatedTime(Env.getSystemTime());
    super.before();
  }
}
