package irille.Service.RFQ.Imp;

import irille.Dao.Old.RFQ.RFQConsultDAO;
import irille.Dao.Old.RFQ.RFQConsultUpdDAO;
import irille.Entity.RFQ.Enums.*;
import irille.Entity.RFQ.RFQConsult;
import irille.Service.RFQ.IRFQConsultService;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrPurchase;
import irille.view.RFQ.PutInquiryView;
import irille.view.v3.rfq.EditRFQConsultView;
import irille.view.v3.rfq.PutRFQConsultView;

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
    @Inject
    private RFQConsultUpdDAO rfqConsultUpdDAO;

    @Override
    public void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(rfqConsultView.getTitle());
        rfqConsult.setImage(rfqConsultView.getImages());
        rfqConsult.setContent(rfqConsultView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(rfqConsultView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);

        rfqConsult.setPurchaseId(usrPurchase.getPkey());
        rfqConsult.stType(RFQConsultType.RFQ);
        //TODO 合并后枚举类更新
        rfqConsult.stStatus(RFQConsultStatus.ready);
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
        rfqConsult.stShippingType(RFQConsultShipping_Type.FOB);
        rfqConsult.setCurrency(rfqConsultView.getCurrency());
        rfqConsult.setDestination(rfqConsultView.getDestination());
        rfqConsult.setProductRequest("{}");
        rfqConsult.setTotal(10);
        rfqConsult.stIsDeleted(false);
        rfqConsult.setChangeCount((short) 0);
        rfqConsult.setCountry(usrPurchase.getCountry());
        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();

    }

    @Override
    public void putInquiry(PutInquiryView inquiryView, UsrPurchase purchase, int countryId) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(inquiryView.getTitle());
        rfqConsult.setImage(inquiryView.getImages());
        rfqConsult.setProduct(inquiryView.getPdtId());
        rfqConsult.setContent(inquiryView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(inquiryView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);
        rfqConsult.setPurchaseId(purchase.getPkey());

//        rfqConsult.setPurchaseId(1);
        rfqConsult.stIsDeleted(false);
        rfqConsult.setTotal(0);
        rfqConsult.stType(RFQConsultType.INQUIRY);
        rfqConsult.stStatus(RFQConsultStatus.runing);
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
        rfqConsult.setCountry(countryId);
        rfqConsult.setProductRequest("{}");

        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();
    }


    @Override
    public void putPrivateInquiry(PutInquiryView inquiryView, UsrPurchase purchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(inquiryView.getTitle());
        rfqConsult.setImage(inquiryView.getImages());
        rfqConsult.setProduct(inquiryView.getPdtId());
        rfqConsult.setContent(inquiryView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(inquiryView.getQuantity());
        rfqConsult.stUnit(RFQConsultUnit.PAIR);
        rfqConsult.setPurchaseId(purchase.getPkey());
        rfqConsult.stIsDeleted(false);
        rfqConsult.setTotal(0);
        rfqConsult.stType(RFQConsultType.Private_INQUIRY);
        rfqConsult.stStatus(RFQConsultStatus.runing);
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
        rfqConsult.setProductRequest("{}");
        rfqConsultDAO.setB(rfqConsult);
        rfqConsultDAO.commit();
    }

    @Override
    public int edItRFQInquiry(EditRFQConsultView inquiryView, UsrPurchase purchase) {
        SQL sql = new SQL();
        sql.SELECT(RFQConsult.class).FROM(RFQConsult.class)
                .WHERE(RFQConsult.T.PKEY, "=?", inquiryView.getId())
                .WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey());
        RFQConsult rfqConsult = Query.sql(sql).query(RFQConsult.class);
        if (rfqConsult != null && rfqConsult.gtStatus() == RFQConsultStatus.ready) {
            rfqConsult.setImage(inquiryView.getImages());
            rfqConsult.setContent(inquiryView.getDescriotion());
            rfqConsult.setQuantity(inquiryView.getQuantity());
            //TODO 这里单位应该是由前端传值
            rfqConsult.stUnit((RFQConsultUnit) RFQConsultUnit.PAIR.getLine().get(inquiryView.getUnit()));
            rfqConsult.setPrice(inquiryView.getMin_price() + "-" + inquiryView.getMax_price());
            rfqConsult.stPayType((RFQConsultPayType) RFQConsultPayType.DEFAULT.getLine().get(inquiryView.getPay_type()));
            rfqConsult.stShippingType(RFQConsultShipping_Type.FOB);
            rfqConsult.setCurrency(inquiryView.getCurrency());
            rfqConsult.setDestination(inquiryView.getDestination());
            rfqConsult.setCountry(purchase.getCountry());
            rfqConsultUpdDAO.setB(rfqConsult).commit();
            return 1;
        }
        return -1;
    }

}
