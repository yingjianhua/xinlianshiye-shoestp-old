package irille.Dao.Old.RFQ;

import irille.Entity.RFQ.RFQConsultRelation;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 10:13 */
public class RFQConsultRelationDAO extends IduIns<RFQConsultRelationDAO, RFQConsultRelation> {

  @Override
  public void before() {
    getB().setCreateDate(Env.getSystemTime());
    super.before();
  }
}
