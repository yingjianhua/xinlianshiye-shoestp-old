package irille.Service.RFQ;

import com.google.inject.ImplementedBy;

import irille.Service.RFQ.Imp.RFQConsultServiceImp;
import irille.shop.usr.UsrPurchase;
import irille.view.RFQ.PutInquiryView;
import irille.view.v3.rfq.EditRFQConsultView;
import irille.view.v3.rfq.PutRFQConsultView;
import irille.view.v3.rfq.PutSupplierConsultView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 9:52 */
@ImplementedBy(RFQConsultServiceImp.class)
public interface IRFQConsultService {
  /**
   * @Description:
   *
   * @date 2019/2/19 20:56
   * @author lijie@shoestp.cn
   */
  void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase);

  /**
   * @Description:
   *
   * @date 2019/2/19 20:56
   * @author lijie@shoestp.cn
   */
  void putInquiry(PutInquiryView readValue, UsrPurchase purchase, int countryId);

  /**
   * @Description:
   *
   * @date 2019/2/19 20:56
   * @author lijie@shoestp.cn
   */
  void putPrivateInquiry(PutInquiryView readValue, UsrPurchase purchase);

  /**
   * @Description: 任何字段都可以修改 除了标题
   *
   * @date 2019/2/19 20:47
   * @author lijie@shoestp.cn
   */
  int edItRFQInquiry(EditRFQConsultView readValue, UsrPurchase purchase);

  /**
   * @Description: 供应商询盘
   *
   * @date 2019/3/7 10:11
   * @author lijie@shoestp.cn
   */
  int putSupplierInquiry(PutSupplierConsultView readValue, UsrPurchase purchase);
}
