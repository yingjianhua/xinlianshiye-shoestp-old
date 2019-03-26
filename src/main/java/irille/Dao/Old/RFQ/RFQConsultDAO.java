package irille.Dao.Old.RFQ;

import irille.Entity.RFQ.RFQConsult;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 10:13 */
public class RFQConsultDAO extends IduIns<RFQConsultDAO, RFQConsult> {

  @Override
  public void before() {
    getB().setCreateTime(Env.getSystemTime());
    super.before();
  }
}
