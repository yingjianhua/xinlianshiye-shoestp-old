package irille.Service.Manage.RFQ.Imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Dao.Old.RFQ.RFQConsultMessageDAO;
import irille.Dao.Old.RFQ.RFQConsultRelationDAO;
import irille.Dao.Old.RFQ.RFQConsultUpdDAO;
import irille.Dao.RFQ.RFQConsultDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultPayType;
import irille.Entity.RFQ.Enums.RFQConsultRelationReadStatus;
import irille.Entity.RFQ.Enums.RFQConsultShipping_Type;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Entity.RFQ.Enums.RFQConsultUnit;
import irille.Service.Manage.RFQ.IRFQManageService;
import irille.action.dataimport.util.DateUtil;
import irille.pub.bean.BeanBase;
import irille.pub.tb.FldLanguage;
import irille.pub.util.GetValue;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import irille.sellerAction.rfq.view.RFQOfferListView;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.Manage.RFQ.RFQListBodyInfoView;
import irille.view.Manage.RFQ.RFQManageInfoView;
import irille.view.Manage.RFQ.RFQManageMyQuoteListBody;
import irille.view.Manage.RFQ.RFQMyuoteInfo;
import irille.view.Manage.RFQ.RFQPdtInfo;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 14:58 */
public class RFQManageServiceImp implements IRFQManageService {
  @Inject RFQConsultDao rfqConsultDao;

  @Inject RFQConsultMessageDAO rfqConsultMessageDAO;

  @Inject RFQConsultRelationDAO rfqConsultRelationDAO;

  @Inject RFQConsultUpdDAO rfqConsultUpdDAO;

  @Inject private ObjectMapper objectMapper;

  @Override
  public Page getRFQList(int start, int limit, String keyword, Integer supId) {
    if (keyword != null) keyword = "%" + keyword + "%";
    List<Map<String, Object>> list = rfqConsultDao.getRFQList(start, limit, keyword, supId);
    List<RFQListBodyInfoView> result = new ArrayList<>();
    for (Map<String, Object> map : list) {
      RFQListBodyInfoView rfqListBodyInfoView = new RFQListBodyInfoView();
      rfqListBodyInfoView.setId(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, 0));
      rfqListBodyInfoView.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
      rfqListBodyInfoView.setCountryId(GetValue.get(map, RFQConsult.T.COUNTRY, Integer.class, 0));
      rfqListBodyInfoView.setCreate_date(
          GetValue.get(map, RFQConsult.T.CREATE_TIME, Date.class, null));
      rfqListBodyInfoView.setQuantity(GetValue.get(map, RFQConsult.T.QUANTITY, Integer.class, 0));
      rfqListBodyInfoView.setLeft_count(
          GetValue.get(map, RFQConsult.T.TOTAL, Integer.class, 0)
              - GetValue.get(map, RFQConsult.T.LEFT_COUNT, Integer.class, 0));
      rfqListBodyInfoView.setImage(
          GetValue.getFirstImage(GetValue.get(map, RFQConsult.T.IMAGE, String.class, "")));
      rfqListBodyInfoView.setContent(GetValue.get(map, RFQConsult.T.CONTENT, String.class, ""));
      rfqListBodyInfoView.setUnit(GetValue.get(map, RFQConsult.T.UNIT, Byte.class, null));
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

    return new Page(
        result, start, limit, rfqConsultDao.getRFQListCount(start, limit, keyword, supId));
  }

  @Override
  public RFQManageInfoView getRFQInfo(int id, int supId) {
    RFQConsult rfqConsult = rfqConsultDao.getRFQInfo(id);
    RFQManageInfoView infoView = new RFQManageInfoView();
    infoView.setId(rfqConsult.getPkey());
    infoView.setTitle(rfqConsult.getTitle());
    infoView.setType(rfqConsult.getType());
    infoView.setProductId(rfqConsult.getProduct());
    infoView.setCreate_date(rfqConsult.getCreateTime());
    infoView.setValid_date(rfqConsult.getValidDate());
    infoView.setLeft_count(rfqConsult.getTotal() - rfqConsult.getLeftCount());
    infoView.setImage(rfqConsult.getImage());
    infoView.setPurchaseName(rfqConsult.gtPurchaseId().getName());
    if (rfqConsult.gtType() == RFQConsultType.RFQ) {
      String[] price = rfqConsult.getPrice().split("-");
      if (price.length > 0) {
        String min_price = price[0];
        if (min_price != null && !min_price.trim().equals("")) {
          infoView.setMin_price(BigDecimal.valueOf(Double.valueOf(min_price)));
        }
      }
      if (price.length > 1) {
        String max_price = price[1];
        if (max_price != null && !max_price.trim().equals("")) {
          infoView.setMax_price(BigDecimal.valueOf(Double.valueOf(max_price)));
        }
      }
      infoView.setCurrencyname(
          BeanBase.load(PltErate.class, rfqConsult.getCurrency()).getCurName());
      infoView.setDescriotion(rfqConsult.getContent()); // 询盘内容
    }
    infoView.setCountryId(rfqConsult.getCountry());
    infoView.setDestination(rfqConsult.getDestination()); // 目的地
    infoView.setQuantity(rfqConsult.getQuantity());
    infoView.setDestination(rfqConsult.getDestination()); // 目的地
    infoView.setDescriotion(rfqConsult.getContent()); // 询盘内容
    if (rfqConsult.getCurrency() != null) {
      infoView.setCurrencyname(
          BeanBase.load(PltErate.class, rfqConsult.getCurrency()).getCurName());
    }
    if (rfqConsult.getPayType() != null)
      infoView.setPay_type(rfqConsult.gtPayType().getLine().getName());
    if (rfqConsult.getShippingType() != null)
      infoView.setShipping_type(rfqConsult.gtShippingType().getLine().getName());
    List<Map<String, Object>> list = rfqConsultDao.getRFQofferList(id);
    infoView.setQuotation_record(new ArrayList());
    for (Map<String, Object> map : list) {
      Map map1 = new HashMap();
      map1.put("company", "********");
      map1.put("address", translateUtil.getLanguage(map.get("city"), FldLanguage.Language.zh_CN));
      map1.put(
          "date",
          DateUtil.format(GetValue.get(map, RFQConsultRelation.T.CREATE_DATE, Date.class, null)));
      infoView.getQuotation_record().add(map1);
    }
    RFQConsultRelation rfqConsultRelation = rfqConsultDao.getRFQRelation(id, supId);
    if (rfqConsult.getValidDate().before(new Date())) {
      infoView.setStatus(-1);
    } else if (rfqConsultRelation == null || rfqConsult.getLeftCount() >= rfqConsult.getTotal()) {
      infoView.setStatus(1);
    }
    return infoView;
  }

  @Override
  public int putRFQQuoteInfo(RFQConsultQuoteInfoView quoteInfo, Integer pkey) {
    // FIXME 当报价次数超出上限时 仍能插入relation数据
    RFQConsult consult = rfqConsultDao.getRFQInfo(quoteInfo.getRfqId());
    if (consult == null) return 0;
    RFQConsultRelation rfqConsultRelation =
        rfqConsultDao.getRFQRelation(quoteInfo.getRfqId(), pkey);
    if (rfqConsultRelation == null) {
      return 0;
    }
    rfqConsultRelation.setConsult(consult.getPkey());
    rfqConsultRelation.setSupplierId(pkey);
    rfqConsultRelation.setPurchaseId(consult.getPurchaseId());
    rfqConsultRelation.stInRecycleBin(false);
    rfqConsultRelation.stFavorite(false);
    rfqConsultRelation.setCurrency(quoteInfo.getCurrency());
    rfqConsultRelation.setTitle(quoteInfo.getTitle());
    rfqConsultRelation.setDescription(quoteInfo.getDescriotion());
    rfqConsultRelation.setQuantity(quoteInfo.getQuantity());
    rfqConsultRelation.setMinprice(quoteInfo.getMin_price());
    rfqConsultRelation.setMaxprice(quoteInfo.getMax_price());
    rfqConsultRelation.setCurrency(quoteInfo.getCurrency());
    rfqConsultRelation.setValidDate(quoteInfo.getValidity());
    rfqConsultRelation.stUnit(RFQConsultUnit.PAIR);
    rfqConsultRelation.stPaytype(
        (RFQConsultPayType) RFQConsultPayType.DEFAULT.getLine().get(quoteInfo.getPayType()));
    rfqConsultRelation.stTransittype(
        (RFQConsultShipping_Type)
            RFQConsultShipping_Type.FOB.getLine().get(quoteInfo.getTransitType()));
    rfqConsultRelation.stSample(quoteInfo.isSample());
    rfqConsultRelation.setCompanydescribe(quoteInfo.getCompanyDescribe());
    try {
      rfqConsultRelation.setImage(objectMapper.writeValueAsString(quoteInfo.getImages()));
      rfqConsultRelation.setThrowaway(objectMapper.writeValueAsString(quoteInfo.getThrowaway()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    rfqConsultRelation.setLastMessageSendTime(new Date());
    rfqConsultRelation.stReadStatus(RFQConsultRelationReadStatus.PURCHASE_UNREAD);
    rfqConsultRelation.stIsNew(true);
    rfqConsultRelation.stIsDeletedPurchase(false);
    rfqConsultRelation.stIsDeletedSupplier(false);
    rfqConsultRelationDAO.setB(rfqConsultRelation);
    rfqConsultRelationDAO.commit();
    if (consult.getLeftCount() < consult.getTotal()) {
      consult.setLeftCount(consult.getLeftCount() + 1);
      consult.setProductRequest("{}");
      consult.setLastMessageSendTime(new Date());
      rfqConsultUpdDAO.setB(consult).commit();
    }
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
      map.put(
          "title",
          translateUtil.getLanguage(
              GetValue.get(map1, PdtProduct.T.NAME, String.class, ""), FldLanguage.Language.en));
      map.put("image", GetValue.get(map1, PdtProduct.T.PICTURE, String.class, ""));
      result.add(map);
    }

    return new Page(
        result, start, limit, rfqConsultDao.getPdtListCount(start, limit, keyword, pkey));
  }

  @Override
  public Page<RFQManageMyQuoteListBody> getMyRFQQuoteList(
      Integer start,
      Integer limit,
      Date date,
      String keyword,
      boolean flag,
      Byte readStatus,
      Integer country,
      int Supid,
      Integer usrCountry)
      throws IOException {
    List<Map<String, Object>> list = null;
    list =
        rfqConsultDao.getMyRFQQuoteList(
            start, limit, date, keyword, flag, readStatus, country, Supid, usrCountry);
    List<RFQManageMyQuoteListBody> result = new ArrayList<>();
    Map<Integer, Object> countryMap = new HashMap<>();
    for (Map<String, Object> map : list) {
      RFQManageMyQuoteListBody body = new RFQManageMyQuoteListBody();
      body.setId(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, 0));
      body.setRfqCreate_date(GetValue.get(map, RFQConsult.T.CREATE_TIME, Date.class, null));
      body.setDescriotion(GetValue.get(map, RFQConsult.T.CONTENT, String.class, null));
      body.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
      body.setQuantity(GetValue.get(map, RFQConsultRelation.T.QUANTITY, Integer.class, null));
      body.setQuoteTitle(GetValue.get(map, "myTitle", String.class, null));
      body.setQuoteDescriotion(
          GetValue.get(map, RFQConsultRelation.T.DESCRIPTION, String.class, null));
      body.setQuoteRFQCreate_date(GetValue.get(map, "myCreate_time", Date.class, null));
      UsrPurchase up =
          BeanBase.load(
              UsrPurchase.class,
              GetValue.get(map, RFQConsultRelation.T.PURCHASE_ID, Date.class, null));
      body.setPurchaseName(up.getName());
      body.setPurchaseCountryIMG(up.gtCountry().getNationalFlag());
      body.setPurchaseCountry(up.gtCountry().getName());
      body.setPurchaseCountryPkey(up.gtCountry().getPkey());
      body.setReadStatus(GetValue.get(map, RFQConsultRelation.T.READ_STATUS, Byte.class, null));
      countryMap.put(up.gtCountry().getPkey(), up.gtCountry().getName());
      result.add(body);
    }
    return new RFQOfferListView(
        result,
        start,
        limit,
        rfqConsultDao.countMyRFQQuoteList(
            date, keyword, flag, readStatus, country, Supid, usrCountry),
        countryMap);
  }

  @Override
  public RFQMyuoteInfo getMyRFQQuoteInfo(Integer id, Integer pkey) throws IOException {
    Map map = rfqConsultDao.getMyRFQQuoteInfo(id, pkey);
    RFQMyuoteInfo rfqMyuoteInfo = new RFQMyuoteInfo();
    rfqMyuoteInfo.setId(GetValue.get(map, RFQConsultRelation.T.PKEY, Integer.class, -1));
    rfqMyuoteInfo.setTitle(GetValue.get(map, RFQConsultRelation.T.TITLE, String.class, null));
    rfqMyuoteInfo.setDescriotion(
        GetValue.get(map, RFQConsultRelation.T.DESCRIPTION, String.class, null));
    rfqMyuoteInfo.setImages(GetValue.get(map, RFQConsultRelation.T.IMAGE, String.class, null));
    rfqMyuoteInfo.setQuantity(GetValue.get(map, RFQConsultRelation.T.QUANTITY, Integer.class, 0));
    rfqMyuoteInfo.setCurrency(GetValue.get(map, RFQConsultRelation.T.CURRENCY, Integer.class, 0));
    rfqMyuoteInfo.setShipping_type(
        GetValue.get(map, RFQConsultRelation.T.TRANSITTYPE, Byte.class, (byte) 0));
    rfqMyuoteInfo.setMin_price(GetValue.get(map, RFQConsultRelation.T.MINPRICE, Integer.class, 0));
    rfqMyuoteInfo.setMax_price(GetValue.get(map, RFQConsultRelation.T.MAXPRICE, Integer.class, 0));
    rfqMyuoteInfo.setValid_date(
        GetValue.get(map, RFQConsultRelation.T.VALID_DATE, Date.class, null));
    rfqMyuoteInfo.setPay_type(
        GetValue.get(map, RFQConsultRelation.T.PAYTYPE, Byte.class, (byte) 0));
    rfqMyuoteInfo.setSample(
        GetValue.get(map, RFQConsultRelation.T.SAMPLE, Byte.class, (byte) 0) == 1);
    rfqMyuoteInfo.setCompanyDescribe(
        GetValue.get(map, RFQConsultRelation.T.COMPANYDESCRIBE, String.class, null));
    rfqMyuoteInfo.setThrowaway(
        GetValue.get(map, RFQConsultRelation.T.THROWAWAY, String.class, null));
    return rfqMyuoteInfo;
  }
}
