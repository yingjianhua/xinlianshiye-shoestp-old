package irille.Dao.Old.RFQ;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 10:13 */
public class RFQConsultMessageDAO extends IduIns<RFQConsultMessageDAO, RFQConsultMessage> {

  @Override
  public void before() {
    getB().setSendTime(Env.getSystemTime());
    super.before();
  }
}
