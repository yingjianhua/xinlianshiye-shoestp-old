package irille.Service.Manage.OdrMeeting.Imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import irille.Dao.OdrMeetingProductDao;
import irille.Dao.Old.OdrMeeting.OdrMeetingProductInsDao;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.Service.Manage.OdrMeeting.IOdrMeetingProductManageService;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingProductView;
import irille.view.Manage.OdrMeeting.initiatedActivity.AllProductsView;
import irille.view.Page;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 14:03 */
public class OdrMeetingProductManageServiceImp implements IOdrMeetingProductManageService {

  @Inject private OdrMeetingProductDao odrMeetingProductDao;
  @Inject private OdrMeetingProductInsDao odrMeetingProductInsDao;

  @Override
  public List<OdrMeetingProductView> getOdrMeetingProductList(IduPage iduPage, int odrMeetingId) {
    return null;
  }

  @Override
  public void insProductToOdrMeeting(
      OdrMeetingProductView odrMeetingProductView, int odrMeetingId, int supplierId) {
    if (odrMeetingProductDao.isAddToOdrMeeting(odrMeetingId, supplierId)) {
      OrderMeetingProduct orderMeetingProduct = new OrderMeetingProduct();
      orderMeetingProduct.setProductid(odrMeetingProductView.getId());
      orderMeetingProduct.setSupplierid(supplierId);
      orderMeetingProduct.setOrdermeetingid(odrMeetingId);
      orderMeetingProduct.setMoq(odrMeetingProductView.getMoq());
      orderMeetingProduct.setPrice(BigDecimal.valueOf(odrMeetingProductView.getPrice()));
      orderMeetingProduct.setNewmoq(odrMeetingProductView.getNewMoq());
      orderMeetingProduct.setNewprice(BigDecimal.valueOf(odrMeetingProductView.getNewPrice()));
      orderMeetingProduct.setTargetCount(odrMeetingProductView.getTargetCount());
      orderMeetingProduct.setUpdatedTime(new Date());
      odrMeetingProductInsDao.setB(orderMeetingProduct).commit();
    }
  }

  @Override
  public Page getOrderGoodsList(
      Integer start,
      Integer limit,
      Integer id,
      Integer status,
      String inputContent,
      Integer supplierId,
      Integer supplier) {
    return odrMeetingProductDao.getOrderGoodsList(
        start, limit, id, status, inputContent, supplierId, supplier);
  }

  @Override
  public void updateStatus(Integer id) {
    odrMeetingProductDao.updateStatus(id);
  }

  @Override
  public void removeProduct(Integer id) {
    odrMeetingProductDao.removePorduct(id);
  }

  @Override
  public List<AllProductsView> getProducts(Integer id) {
    return odrMeetingProductDao.getProducts(id);
  }

  @Override
  public List getAddedProducts(Integer id, Integer supplierId) {
    return odrMeetingProductDao.getAddedProducts(id, supplierId);
  }

  @Override
  public void addProducts(Integer id, Integer pkey, List<AllProductsView> list) {
    odrMeetingProductDao.addProducts(id, pkey, list);
  }
}
