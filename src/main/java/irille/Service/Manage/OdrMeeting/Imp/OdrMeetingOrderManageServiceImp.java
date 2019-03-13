package irille.Service.Manage.OdrMeeting.Imp;

import java.util.List;

import javax.inject.Inject;

import irille.Dao.OdrMeetingProductDao;
import irille.Dao.Old.OdrMeeting.OrderMeetingOrderDao;
import irille.Service.Manage.OdrMeeting.IOdrMeetingOrderManageService;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

public class OdrMeetingOrderManageServiceImp implements IOdrMeetingOrderManageService {
  @Inject private OrderMeetingOrderDao orderMeetingOrderDao;
  @Inject private OdrMeetingProductDao odrMeetingProductDao;

  @Override
  public void getOdrMeetingOrderInfo() {}

  @Override
  public Page getOmtOrderList(
      Integer omtId,
      Integer productId,
      Integer supplierId,
      Integer supplier,
      Integer start,
      Integer limit,
      Integer classification,
      Integer status,
      Integer orderStatus,
      String input) {
    return orderMeetingOrderDao.getOmtOrderList(
        omtId,
        productId,
        supplierId,
        supplier,
        start,
        limit,
        classification,
        status,
        orderStatus,
        input);
  }

  @Override
  public List<orderOrderStatusListView> getOrderStatus() {
    return new OrderMeetingOrderDao().getOrderStatus();
  }

  @Override
  public Page getSalesDetails(
      Integer start,
      Integer limit,
      Integer id,
      String input,
      Integer status,
      Integer productId,
      Integer supplierId) {
    return odrMeetingProductDao.salesDetailslist(
        start, limit, id, input, status, productId, supplierId);
  }

  @Override
  public void updSendStatus(Integer id) {
    orderMeetingOrderDao.updSendStatus(id);
  }
}
