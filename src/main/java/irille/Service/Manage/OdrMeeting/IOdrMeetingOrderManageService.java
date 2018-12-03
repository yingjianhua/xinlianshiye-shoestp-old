package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingOrderManageServiceImp;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import java.util.List;
import java.util.Map;

/**
 * 订购会-订单相关接口 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 14:00
 */
@ImplementedBy(OdrMeetingOrderManageServiceImp.class)
public interface IOdrMeetingOrderManageService {

    /**
     * @Description: 获取订购会相关订单
     * @date 2018/11/14 18:38
     * @author lijie@shoestp.cn
     */
    void getOdrMeetingOrderInfo();

    /**
     * @Description: 获取订购会订单列表
     * @date 2018/11/29 19:18
     * @author zjl
     */
    Page getOmtOrderList(Integer omtId, Integer start, Integer limit, Integer classification, Integer orderStatus, String input);

    List<orderOrderStatusListView> getOrderStatus();
}
