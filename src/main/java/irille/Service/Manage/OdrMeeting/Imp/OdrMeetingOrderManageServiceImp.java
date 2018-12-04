package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.Old.OdrMeeting.OrderMeetingOrderDao;
import irille.Service.Manage.OdrMeeting.IOdrMeetingOrderManageService;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import java.util.List;
import java.util.Map;

public class OdrMeetingOrderManageServiceImp implements IOdrMeetingOrderManageService {
    @Override
    public void getOdrMeetingOrderInfo() {

    }

    @Override
    public Page getOmtOrderList(Integer omtId, Integer start, Integer limit, Integer classification, Integer orderStatus, String input) {
        OrderMeetingOrderDao dao = new OrderMeetingOrderDao();
        return dao.getOmtOrderList(omtId,start,limit,classification,orderStatus,input);
    }

    @Override
    public List<orderOrderStatusListView> getOrderStatus() {
        return new OrderMeetingOrderDao().getOrderStatus();
    }
}
