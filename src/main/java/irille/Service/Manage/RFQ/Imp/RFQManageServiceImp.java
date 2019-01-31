package irille.Service.Manage.RFQ.Imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Dao.Old.RFQ.RFQConsultMessageDAO;
import irille.Dao.Old.RFQ.RFQConsultRelationDAO;
import irille.Dao.RFQ.RFQConsultDao;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.Entity.RFQ.JSON.RFQConsultQuoteInfo;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Service.Manage.RFQ.IRFQManageService;
import irille.action.dataimport.util.DateUtil;
import irille.pub.tb.FldLanguage;
import irille.pub.util.GetValue;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.view.Manage.RFQ.RFQListBodyInfoView;
import irille.view.Manage.RFQ.RFQManageInfoView;
import irille.view.Manage.RFQ.RFQManageMyQuoteListBody;
import irille.view.Manage.RFQ.RFQPdtInfo;
import irille.view.Page;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 14:58
 */
public class RFQManageServiceImp implements IRFQManageService {
    @Inject
    RFQConsultDao rfqConsultDao;

    @Inject
    RFQConsultMessageDAO rfqConsultMessageDAO;

    @Inject
    RFQConsultRelationDAO rfqConsultRelationDAO;


    @Inject
    private ObjectMapper objectMapper;

    @Override
    public Page getRFQList(int start, int limit, String keyword, Integer supId) {
        if (keyword != null)
            keyword = "%" + keyword + "%";
        List<Map<String, Object>> list = rfqConsultDao.getRFQList(start, limit, keyword, supId);
        List<RFQListBodyInfoView> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            RFQListBodyInfoView rfqListBodyInfoView = new RFQListBodyInfoView();
            rfqListBodyInfoView.setId(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, 0));
            rfqListBodyInfoView.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
            rfqListBodyInfoView.setCountryId(GetValue.get(map, RFQConsult.T.COUNTRY, Integer.class, 0));
            rfqListBodyInfoView.setCreate_date(GetValue.get(map, RFQConsult.T.CREATE_TIME, Date.class, null));
            rfqListBodyInfoView.setQuantity(GetValue.get(map, RFQConsult.T.QUANTITY, Integer.class, 0));
            rfqListBodyInfoView.setLeft_count(GetValue.get(map, RFQConsult.T.TOTAL, Integer.class, 0) - GetValue.get(map, RFQConsult.T.LEFT_COUNT, Integer.class, 0));
            rfqListBodyInfoView.setImage(GetValue.getFirstImage(GetValue.get(map, RFQConsult.T.IMAGE, String.class, "")));
            byte b = GetValue.get(map, RFQConsultRelation.T.FAVORITE, Byte.class, (byte) 0);
            if (b == 0) {
                rfqListBodyInfoView.setFavorite(false);
            } else {
                rfqListBodyInfoView.setFavorite(true);
            }
            if (GetValue.get(map, "inquiry", Integer.class, -1) > 0) {
                rfqListBodyInfoView.setInquiry(true);
            } else {
                rfqListBodyInfoView.setInquiry(false);
            }
            result.add(rfqListBodyInfoView);
        }

        return new Page(result, start, limit, rfqConsultDao.getRFQListCount(start, limit, keyword, supId));
    }

    @Override
    public RFQManageInfoView getRFQInfo(int id, int supId) {
        RFQConsult rfqConsult = rfqConsultDao.getRFQInfo(id);
        RFQManageInfoView infoView = new RFQManageInfoView();
        infoView.setId(rfqConsult.getPkey());
        infoView.setTitle(rfqConsult.getTitle());
        infoView.setCreate_date(rfqConsult.getCreateTime());
        infoView.setValid_date(rfqConsult.getValidDate());
        infoView.setLeft_count(rfqConsult.getTotal() - rfqConsult.getLeftCount());
        infoView.setImage(rfqConsult.getImage());
        infoView.setMin_price(Integer.valueOf(GetValue.getStringIndex(rfqConsult.getPrice(), "-", 0)));
        infoView.setMax_price(Integer.valueOf(GetValue.getStringIndex(rfqConsult.getPrice(), "-", 1)));
        infoView.setCountryId(rfqConsult.getCountry());
        infoView.setQuantity(rfqConsult.getQuantity());
        infoView.setDescriotion(rfqConsult.getDestination());
        if (rfqConsult.getPayType() != null)
            infoView.setPay_type(rfqConsult.gtPayType().getLine().getName());
        if (rfqConsult.getShippingType() != null)
            infoView.setShipping_type(rfqConsult.gtShippingType().getLine().getName());
        List<Map<String, Object>> list = rfqConsultDao.getRFQofferList(id);
        infoView.setQuotation_record(
                new ArrayList()
        );
        for (Map<String, Object> map : list) {
            Map map1 = new HashMap();
            map1.put("company", "********");
            map1.put("address", translateUtil.getLanguage(map.get("city"), FldLanguage.Language.zh_CN));
            map1.put("date", DateUtil.format(GetValue.get(map, RFQConsultMessage.T.SEND_TIME, Date.class, null)));
            infoView.getQuotation_record().add(map1);
        }
        RFQConsultRelation rfqConsultRelation = rfqConsultDao.getRFQRelation(id, supId);
        if (rfqConsultRelation != null) {
            infoView.setStatus(1);
        }
        return infoView;
    }

    @Override
    public int putRFQQuoteInfo(RFQConsultQuoteInfo quoteInfo, Integer pkey) {
        RFQConsultMessage message = new RFQConsultMessage();
        message.stType(RFQConsultMessageType.Quote);
        message.stP2S(false);
        message.stHadRead(false);
        try {
            message.setContent(objectMapper.writeValueAsString(quoteInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RFQConsult consult = rfqConsultDao.getRFQInfo(quoteInfo.getRfqId());
        RFQConsultRelation rfqConsultRelation = rfqConsultDao.getRFQRelation(quoteInfo.getRfqId(), pkey);
        if (rfqConsultRelation == null) {
            return 0;
        }
        rfqConsultRelation.setConsult(quoteInfo.getRfqId());
        rfqConsultRelation.setSupplierId(pkey);
        rfqConsultRelation.setConsult(consult.getPkey());
        rfqConsultRelation.setPurchaseId(consult.getPurchaseId());
        rfqConsultRelation.stFavorite(false);
        rfqConsultRelationDAO.setB(rfqConsultRelation).commit();
        RFQConsultRelation rfqConsultRelation1 = rfqConsultRelationDAO.getB();
        message.setRelation(rfqConsultRelation1.getPkey());
        rfqConsultMessageDAO.setB(message);
        rfqConsultMessageDAO.commit();
        return 1;
    }

    @Override
    public RFQPdtInfo getPdtInfo(Integer id, Integer pkey) {
        rfqConsultDao.getMyPdtInfo(id, pkey);
        return null;
    }

    @Override
    public Page getPdtList(Integer start, Integer limit, String keyword, Integer pkey) {
        List<Map> maps = rfqConsultDao.getPdtList(start, limit, keyword, pkey);
        List result = new ArrayList();
        for (Map map1 : maps) {
            Map map = new HashMap();
            map.put("id", GetValue.get(map1, PdtProduct.T.PKEY, Integer.class, 0));
            map.put("title", translateUtil.getLanguage(GetValue.get(map1, PdtProduct.T.NAME, String.class, ""), FldLanguage.Language.en));
            map.put("image", GetValue.get(map1, PdtProduct.T.PICTURE, String.class, ""));
            result.add(map);
        }

        return new Page(result, start, limit, rfqConsultDao.getPdtListCount(start, limit, keyword, pkey));
    }

    @Override
    public Page getMyRFQQuoteList(Integer start, Integer limit, Date date, String keyword, boolean flag, Integer status, Integer country, int Supid) throws IOException {
        List<Map<String, Object>> list = rfqConsultDao.getMyRFQQuoteList(start, limit, date, keyword, flag, status, country, Supid);
        List<RFQManageMyQuoteListBody> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            RFQManageMyQuoteListBody body = new RFQManageMyQuoteListBody();
            body.setId(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, 0));
            body.setRfqCreate_date(GetValue.get(map, RFQConsult.T.CREATE_TIME, Date.class, null));
            body.setDescriotion(GetValue.get(map, RFQConsult.T.CONTENT, String.class, null));
            String s = GetValue.get(map, "quoteContent", String.class, null);
            RFQConsultQuoteInfo quoteInfo = objectMapper.readValue(s, RFQConsultQuoteInfo.class);
            body.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
            body.setQuantity(quoteInfo.getQuantity());
            body.setQuoteTitle(quoteInfo.getTitle());
            body.setQuoteDescriotion(quoteInfo.getDescriotion());
            body.setQuoteRFQCreate_date(GetValue.get(map, RFQConsultMessage.T.SEND_TIME, Date.class, null));
            if (GetValue.get(map, RFQConsultMessage.T.HAD_READ, Byte.class, (byte) -1) == 0)
                body.setStatus(1);
            else {
                body.setStatus(2);
            }
            result.add(body);
        }
        return new Page(result, start, limit, 10);
    }

	@Override
	public void page(Integer start, Integer limit, String keyword, Integer groupId, Boolean flagId, Byte type,
			Boolean haveNewMsg, Boolean isDeleted, Date startDate, Date endDate) {
		// TODO Auto-generated method stub 未完成

	}
}
