package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.Old.OdrMeeting.OrderMeetingOrderDao;
import irille.Service.Manage.OdrMeeting.IOdrMeetingOrderManageService;
import irille.view.Page;

public class OdrMeetingOrderManageServiceImp implements IOdrMeetingOrderManageService {
    @Override
    public void getOdrMeetingOrderInfo() {

    }

    @Override
    public Page getOmtOrderList(Integer omtId, Integer start, Integer limit, Integer classification, Integer orderStatus, String input) {
        OrderMeetingOrderDao dao = new OrderMeetingOrderDao();
        return dao.getOmtOrderList(omtId,start,limit,classification,orderStatus,input);
    }
}
