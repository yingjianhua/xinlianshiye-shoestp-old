package irille.Service.RFQ.Imp;

import irille.Dao.Old.RFQ.RFQConsultDAO;
import irille.Entity.RFQ.Enums.*;
import irille.Entity.RFQ.RFQConsult;
import irille.Service.RFQ.IRFQConsultService;
import irille.shop.usr.UsrPurchase;
import irille.view.RFQ.PutInquiryView;
import irille.view.RFQ.PutRFQConsultView;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:52
 */
public class RFQConsultServiceImp implements IRFQConsultService {

    @Inject
    private RFQConsultDAO rfqConsultDAO;

    @Override
    public void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(rfqConsultView.getTitle());
        rfqConsult.setImage(rfqConsultView.getImages());
        rfqConsult.stHaveNewMsg(true);
        rfqConsult.setContent(rfqConsultView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(rfqConsultView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);

        rfqConsult.setPurchaseId(usrPurchase.getPkey());
//        rfqConsult.setPurchaseId(1);

        rfqConsult.stType(RFQConsultType.RFQ);
        //TODO 合并后枚举类更新
        rfqConsult.stStatus(RFQConsultStatus.ON);
        rfqConsult.stVerifyStatus(RFQConsultVerifyStatus.UNAUDITED);
        rfqConsult.setValidDate(
                Date.from(
                        LocalDate
                                .now()
                                .plusMonths(1)
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                )
                                .toInstant()
                )
        );
        rfqConsult.setPrice(rfqConsultView.getMin_price() + "-" + rfqConsultView.getMax_price());
        rfqConsult.stPayType((RFQConsultPayType) RFQConsultPayType.DEFAULT.getLine().get(rfqConsultView.getPay_type()));
        rfqConsult.stShippingType(RFQConsultShipping_Type.OCEAN_SHIPPING);
        rfqConsult.setCurrency(rfqConsultView.getCurrency());
        rfqConsult.setDestination(rfqConsultView.getDestination());
        rfqConsult.setTotal(10);
        rfqConsult.setChangeCount((short) 0);
        rfqConsult.setCountry(usrPurchase.getCountry());
//        rfqConsult.setCountry(1);
        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();

    }

    @Override
    public void putInquiry(PutInquiryView inquiryView, UsrPurchase purchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(inquiryView.getTitle());
        rfqConsult.setImage(inquiryView.getImages());
        rfqConsult.setProduct(inquiryView.getPdtId());
        rfqConsult.stHaveNewMsg(true);
        rfqConsult.setContent(inquiryView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(inquiryView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);
        rfqConsult.setPurchaseId(purchase.getPkey());
//        rfqConsult.setPurchaseId(1);
        rfqConsult.setTotal(0);
        rfqConsult.stType(RFQConsultType.INQUIRY);
        rfqConsult.stStatus(RFQConsultStatus.ON);
        rfqConsult.stVerifyStatus(RFQConsultVerifyStatus.PASS);
        rfqConsult.setValidDate(
                Date.from(
                        LocalDate
                                .now()
                                .plusDays(1)
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                )
                                .toInstant()
                )
        );
        rfqConsult.setChangeCount((short) 0);
        rfqConsult.setCountry(purchase.getCountry());
//        rfqConsult.setCountry(1);
        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();
    }


    @Override
    public void putPrivateInquiry(PutInquiryView inquiryView, UsrPurchase purchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(inquiryView.getTitle());
        rfqConsult.setImage(inquiryView.getImages());
        rfqConsult.setProduct(inquiryView.getPdtId());

        rfqConsult.stHaveNewMsg(true);
        rfqConsult.setContent(inquiryView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(inquiryView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);
//        rfqConsult.setPurchaseId(purchase.getPkey());
        rfqConsult.setPurchaseId(1);
        rfqConsult.setTotal(0);
        rfqConsult.stType(RFQConsultType.Private_INQUIRY);
        rfqConsult.stStatus(RFQConsultStatus.ON);
        rfqConsult.stVerifyStatus(RFQConsultVerifyStatus.PASS);
        rfqConsult.setValidDate(
                Date.from(
                        LocalDate
                                .now()
                                .plusDays(1)
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                )
                                .toInstant()
                )
        );
        rfqConsult.setChangeCount((short) 0);
//        rfqConsult.setCountry(purchase.getCountry());
        rfqConsult.setCountry(1);
        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();
    }

}