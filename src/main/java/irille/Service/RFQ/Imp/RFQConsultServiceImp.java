package irille.Service.RFQ.Imp;

import irille.Dao.Old.RFQ.RFQConsultDAO;
import irille.Dao.Old.RFQ.RFQConsultUpdDAO;
import irille.Dao.PdtProductDao;
import irille.Entity.RFQ.Enums.*;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Service.RFQ.IRFQConsultService;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.shop.usr.UsrPurchase;
import irille.view.RFQ.PutInquiryView;
import irille.view.v3.rfq.EditRFQConsultView;
import irille.view.v3.rfq.PutRFQConsultView;
import irille.view.v3.rfq.PutSupplierConsultView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

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
    @Inject
    PdtProductDao pdtProductDao;

    private static final Logger logger = LogManager.getLogger(RFQConsultServiceImp.class);

    @Override
    public void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase) {
        RFQConsult rfqConsult = new RFQConsult();
        rfqConsult.setTitle(rfqConsultView.getTitle());
        rfqConsult.setImage(rfqConsultView.getImages());
        rfqConsult.setContent(rfqConsultView.getDescriotion());
        rfqConsult.setLeftCount(0);
        rfqConsult.setQuantity(rfqConsultView.getQuantity());
        rfqConsult.stUnit((RFQConsultUnit) RFQConsultUnit.PAIR.getLine().get(rfqConsultView.getUnit()));

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
        rfqConsult.setExtraRequest(rfqConsultView.getExtraRequest());
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
        rfqConsult.stUnit((RFQConsultUnit) RFQConsultUnit.PAIR.getLine().get(inquiryView.getUnitType()));
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
        RFQConsultRelation rfqConsultRelation = new RFQConsultRelation();
        rfqConsultRelation.setConsult(rfqConsult.getPkey());
        Map map = pdtProductDao.getInquiryPdtInfo(inquiryView.getPdtId());
        if (map == null) {
            logger.info(String.format("查找不到该商品 商品ID:%d", inquiryView.getPdtId()));
        }
        Integer integer = GetValue.get(map, "supId", Integer.class, null);
        if (integer == null) {
            logger.error(String.format("疑似脏数据  商品Id:%s  没有供应商", inquiryView.getPdtId()));
        }
        rfqConsultRelation.setSupplierId(integer);
        rfqConsultRelation.setPurchaseId(purchase.getPkey());
        rfqConsultRelation.stInRecycleBin(false);
        rfqConsultRelation.stFavorite(false);
        rfqConsultRelation.setTitle("");
        rfqConsultRelation.setDescription("");
        rfqConsultRelation.setImage("{}");
        rfqConsultRelation.setQuantity(0);
        rfqConsultRelation.stUnit(RFQConsultUnit.PAIR);
        rfqConsultRelation.setMinprice(0);
        rfqConsultRelation.setMaxprice(0);
        rfqConsultRelation.setCurrency(0);
        rfqConsultRelation.setValidDate(null);
        rfqConsultRelation.stPaytype(RFQConsultPayType.DEFAULT);
        rfqConsultRelation.stTransittype(RFQConsultShipping_Type.FOB);
        rfqConsultRelation.setCreateDate(new Date());
        rfqConsultRelation.stIsNew(false);
        rfqConsultRelation.stSample(false);
        rfqConsultRelation.stHadReadSupplier(false);
        rfqConsultRelation.stHadReadPurchase(false);
        rfqConsultRelation.stIsDeletedPurchase(false);
        rfqConsultRelation.stIsDeletedSupplier(false);
        rfqConsultRelation.setThrowaway("{}");
        rfqConsultRelation.ins();
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
        rfqConsult.stUnit((RFQConsultUnit) RFQConsultUnit.PAIR.getLine().get(inquiryView.getUnitType()));
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
        RFQConsultRelation rfqConsultRelation = new RFQConsultRelation();
        rfqConsultRelation.setConsult(rfqConsult.getPkey());
        Map map = pdtProductDao.getInquiryPdtInfo(inquiryView.getPdtId());
        if (map == null) {
            logger.info(String.format("查找不到该商品 商品ID:%d", inquiryView.getPdtId()));
        }
        Integer integer = GetValue.get(map, "supId", Integer.class, null);
        if (integer == null) {
            logger.error(String.format("疑似脏数据  商品Id:%s  没有供应商", inquiryView.getPdtId()));
        }
        rfqConsultRelation.setSupplierId(integer);
        rfqConsultRelation.setPurchaseId(purchase.getPkey());
        rfqConsultRelation.stInRecycleBin(false);
        rfqConsultRelation.stFavorite(false);
        rfqConsultRelation.setTitle("");
        rfqConsultRelation.setDescription("");
        rfqConsultRelation.setImage("{}");
        rfqConsultRelation.setQuantity(0);
        rfqConsultRelation.stUnit(RFQConsultUnit.PAIR);
        rfqConsultRelation.setMinprice(0);
        rfqConsultRelation.setMaxprice(0);
        rfqConsultRelation.setCurrency(0);
        rfqConsultRelation.setValidDate(null);
        rfqConsultRelation.stPaytype(RFQConsultPayType.DEFAULT);
        rfqConsultRelation.stTransittype(RFQConsultShipping_Type.FOB);
        rfqConsultRelation.setCreateDate(new Date());
        rfqConsultRelation.stIsNew(false);
        rfqConsultRelation.stSample(false);
        rfqConsultRelation.stHadReadSupplier(false);
        rfqConsultRelation.stHadReadPurchase(false);
        rfqConsultRelation.stIsDeletedPurchase(false);
        rfqConsultRelation.stIsDeletedSupplier(false);
        rfqConsultRelation.setThrowaway("{}");
        rfqConsultRelation.ins();

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

    @Override
    public int putSupplierInquiry(PutSupplierConsultView consultView, UsrPurchase purchase) {
        RFQConsult consult = new RFQConsult();
        consult.setTitle(consultView.getTitle());
        consult.setDestination(consultView.getDescription());
        StringJoiner joiner = new StringJoiner(",");
        for (String s : consultView.getExtra_request()) {
            joiner.add(s);
        }
        consult.setExtraRequest(joiner.toString());
        consult.setSupplierId(consultView.getSupplierId());
        consult.setImage(consultView.getImages());
        consult.setQuantity(consultView.getQuantity());
        RFQConsultUnit rfqConsultUnit;
        try {
            rfqConsultUnit = (RFQConsultUnit) RFQConsultUnit.PAIR.getLine().get(consultView.getUnit());
        } catch (Exception e) {
            e.printStackTrace();
            rfqConsultUnit = RFQConsultUnit.PAIR;
        }
        ;
        consult.stUnit(rfqConsultUnit);
        rfqConsultDAO.setB(consult).commit();
        RFQConsultRelation rfqConsultRelation = new RFQConsultRelation();
        rfqConsultRelation.setConsult(consult.getPkey());
        rfqConsultRelation.setSupplierId(consultView.getSupplierId());
        rfqConsultRelation.setPurchaseId(purchase.getPkey());
        rfqConsultRelation.stInRecycleBin(false);
        rfqConsultRelation.stFavorite(false);
        rfqConsultRelation.setTitle("");
        rfqConsultRelation.setDescription("");
        rfqConsultRelation.setImage("{}");
        rfqConsultRelation.setQuantity(0);
        rfqConsultRelation.stUnit(RFQConsultUnit.PAIR);
        rfqConsultRelation.setMinprice(0);
        rfqConsultRelation.setMaxprice(0);
        rfqConsultRelation.setCurrency(0);
        rfqConsultRelation.setValidDate(null);
        rfqConsultRelation.stPaytype(RFQConsultPayType.DEFAULT);
        rfqConsultRelation.stTransittype(RFQConsultShipping_Type.FOB);
        rfqConsultRelation.setCreateDate(new Date());
        rfqConsultRelation.stIsNew(false);
        rfqConsultRelation.stSample(false);
        rfqConsultRelation.stHadReadSupplier(false);
        rfqConsultRelation.stHadReadPurchase(false);
        rfqConsultRelation.stIsDeletedPurchase(false);
        rfqConsultRelation.stIsDeletedSupplier(false);
        rfqConsultRelation.setThrowaway("{}");
        rfqConsultRelation.ins();
        return 0;
    }
}

