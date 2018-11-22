package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingAuditRelease;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

public class OrderMeetingAuditReleaseDao {
    /**
     *@Description:  商家申请 参加订购会
     *@date 2018/11/19 20:09
     *@anthor wilson zhang
     */
    public static class insertOdr extends IduIns<insertOdr, OrderMeetingAuditRelease> {
        @Override
        public void before() {
            getB().setCreatedTime(Env.getTranBeginTime());
            getB().setStatus(OrderMeetingAuditStatus.PENDING.getLine().getKey());
            super.before();
        }
    }
}
