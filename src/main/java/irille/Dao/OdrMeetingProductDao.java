package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeeting.T;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.pub.bean.query.BeanQuery;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingProductDao {

    /**
     * @Description: 检查是否有条件参加添加该商品到订购会
     * @date 2018/11/15 11:21
     * @author lijie@shoestp.cn
     */
    public boolean isAddToOdrMeeting(int odrMeetingId, int supplier) {
        BeanQuery query = new BeanQuery();
//        query.SELECT(T.SUPPLIERID)
//                .FROM(OrderMeetingAudit.class)
//                .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingAudit.T.OrderMeeting)
//                .WHERE(T.SUPPLIERID, "= ?", supplier)  //判断是不是发起人
//                .WHERE(T.STATUS, "=?", OrderMeetingStatus.TOBEGIN)  //判断订购会状态
//                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)   //判断是通过的供应商
//                .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.ACTIVITY)  //状态通过
//                .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetingId)
//        ;
        return query.queryCount() > 0;
    }


}
