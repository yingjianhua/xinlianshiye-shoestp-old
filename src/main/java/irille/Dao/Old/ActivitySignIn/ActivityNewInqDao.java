package irille.Dao.Old.ActivitySignIn;

import irille.Entity.NewInquiry.NewInquiry;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

public class ActivityNewInqDao extends IduIns<ActivityNewInqDao, NewInquiry> {
  @Override
  public void before() {
    getB().setCreateTime(Env.getSystemTime());
    super.before();
  }
}
