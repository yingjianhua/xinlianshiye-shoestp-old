package irille.platform.omt;

import irille.Dao.OdrMeetingDao;
import irille.Dao.OrderMeetingAuditReleaseDao;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.action.MgtAction;
import irille.platform.omt.view.OmtDetailsView;
import irille.view.Page;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


public class OrderMeetingAction extends MgtAction<OrderMeeting> {
    @Getter
    @Setter
    private String omtName;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private Integer id;

    @Override
    public Class beanClazz() {
        return null;
    }

    @Inject
    private OdrMeetingDao odrMeetingDao;
    @Inject
    private OrderMeetingAuditReleaseDao orderMeetingAuditReleaseDao;

    /**
     * @Description: (平台)获取发布订购会审核列表
     * *@date 2019/1/3 10:13
     * *@anthor zjl
     */
    public Page getApplications() {
        return orderMeetingAuditReleaseDao.getApplications(getStart(), getLimit(), omtName, status);
    }

    /**
     * @Description: (平台)获取单个订购会详情
     * *@date 2019/1/3 10:58
     * *@anthor zjl
     */
    public OmtDetailsView getDetails() {
        return odrMeetingDao.getDetails(id);
    }
    /**
     * @Description: (平台)获取审核状态列表
     * *@date 2019/1/3 14:05
     * *@anthor zjl
     */
    public Map<Byte,String> getStatusList(){
        Map<Byte,String> map = new HashMap<>();
        for (OrderMeetingAuditStatus value : OrderMeetingAuditStatus.values()) {
            map.put(value.getLine().getKey(),value.getLine().getName());
        }
        return map;
    }
}
