package irille.Service.RFQ;

import com.google.inject.ImplementedBy;
import irille.Service.RFQ.Imp.RFQConsultServiceImp;
import irille.shop.usr.UsrPurchase;
import irille.view.Rfq.PutInquiryView;
import irille.view.Rfq.PutRFQConsultView;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:52
 */
@ImplementedBy(RFQConsultServiceImp.class)
public interface IRFQConsultService {
    void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase);

    void putInquiry(PutInquiryView readValue, UsrPurchase purchase);

    void putPrivateInquiry(PutRFQConsultView readValue, UsrPurchase purchase);
}
