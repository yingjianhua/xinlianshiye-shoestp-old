package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.OdrMeetingProductDao;
import irille.Dao.Old.OdrMeeting.OrderMeetingOrderDao;
import irille.Service.Manage.OdrMeeting.IOdrMeetingOrderManageService;
import irille.pub.tb.FldLanguage;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import javax.inject.Inject;
import java.util.List;

public class OdrMeetingOrderManageServiceImp implements IOdrMeetingOrderManageService {
    @Inject
    private OrderMeetingOrderDao orderMeetingOrderDao;
    @Inject
    private OdrMeetingProductDao odrMeetingProductDao;

    @Override
    public void getOdrMeetingOrderInfo() {

    }

    @Override
    public Page getOmtOrderList(Integer omtId, Integer start, Integer limit, Integer classification, Integer orderStatus, String input) {
        return orderMeetingOrderDao.getOmtOrderList(omtId, start, limit, classification, orderStatus, input);
    }

    @Override
    public List<orderOrderStatusListView> getOrderStatus() {
        return new OrderMeetingOrderDao().getOrderStatus();
    }

    @Override
    public Page getSalesDetails(Integer start, Integer limit, Integer id, String input, Integer status, FldLanguage.Language lang, Integer supplierId) {
        return odrMeetingProductDao.salesDetailslist(start, limit, id, input, status, lang, supplierId);
    }

}
