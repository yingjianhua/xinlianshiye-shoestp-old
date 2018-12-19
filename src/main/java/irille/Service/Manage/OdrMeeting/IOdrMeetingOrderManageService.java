package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingOrderManageServiceImp;
import irille.pub.tb.FldLanguage;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import java.util.List;

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
    Page getOmtOrderList(Integer omtId,Integer productId,Integer supplierId,Integer supplier, Integer start, Integer limit, Integer classification, Integer status,Integer orderStatus, String input);

    /**
     * @Description: 获取订单状态列表
     * @date 2018/12/6 9:37
     * @author zjl
     */
    List<orderOrderStatusListView> getOrderStatus();
    Page getSalesDetails(Integer start, Integer limit, Integer id, String input, Integer status,Integer productId,Integer supplierId);

    /**
     * @Description: 修改是否发送订单状态
     * @date 2018/12/11 11:51
     * @author zjl
     */
    void updSendStatus(Integer id);
}
