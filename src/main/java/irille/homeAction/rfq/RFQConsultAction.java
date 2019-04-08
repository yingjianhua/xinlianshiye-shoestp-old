package irille.homeAction.rfq;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultMessageService;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;

import irille.Filter.svr.ItpCheckPurchaseLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin.UserType;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.Plt.PltService;
import irille.Service.RFQ.IRFQConsultService;
import irille.homeAction.HomeAction;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage;
import irille.pub.util.ipUtils.City;
import irille.view.RFQ.PutInquiryView;
import irille.view.RFQ.RFQPdtInfo;
import irille.view.plt.CountryView;
import irille.view.v3.rfq.EditRFQConsultView;
import irille.view.v3.rfq.PutRFQConsultView;
import irille.view.v3.rfq.PutSupplierConsultView;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 9:35 */
@Setter
public class RFQConsultAction extends HomeAction implements IRFQConsultAction {

  private static final long serialVersionUID = 1L;

  @Inject private ObjectMapper objectMapper;
  @Inject private IRFQConsultService irfqConsultService;
  @Inject private RFQConsultService rFQConsultService;
  @Inject private RFQConsultMessageService rFQConsultMessageService;
  @Inject private IPdtProductService iPdtProductService;

  @Setter private String data;
  @Setter private Integer id;
  @Setter private int type;

  @Inject private PltService pltService;

  /**
   * @Description: RFQ询盘
   *
   * @date 2019/1/30 11:10
   * @author lijie@shoestp.cn
   */
  @NeedLogin(userType = UserType.PURCHASE)
  public void putRFQInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, HomeAction.curLanguage()));
    }
    PutRFQConsultView rFQConsultView = objectMapper.readValue(data, PutRFQConsultView.class);
    validPutRFQInquiry(rFQConsultView);
    irfqConsultService.putRFQInquiry(rFQConsultView, getPurchase());
    write();
  }

  /**
   * @Description: RFQ询盘
   *
   * @date 2019/1/30 11:10
   * @author lijie@shoestp.cn
   */
  @NeedLogin(userType = UserType.PURCHASE)
  public void putSupplierInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, HomeAction.curLanguage()));
    }
    PutSupplierConsultView putSupplierConsultView =
        objectMapper.readValue(data, PutSupplierConsultView.class);
    if (putSupplierConsultView.getTitle() == null
        || putSupplierConsultView.getTitle().length() < 1
        || putSupplierConsultView.getTitle().length() > 100) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_Invalid_Title, HomeAction.curLanguage()));
    }
    irfqConsultService.putSupplierInquiry(putSupplierConsultView, getPurchase());
    write();
  }

  /**
   * @Description: 询盘
   *
   * @date 2019/1/30 11:10
   * @author lijie@shoestp.cn
   */
  @NeedLogin(userType = UserType.PURCHASE)
  public void putInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, HomeAction.curLanguage()));
    }
    PutInquiryView view = objectMapper.readValue(data, PutInquiryView.class);
    String[] country = City.find(ServletActionContext.getRequest().getRemoteAddr());
    int countryId = -1;
    if (country != null && country.length > 0) {
      for (CountryView countryView : pltService.getCountryList(FldLanguage.Language.zh_CN)) {
        if (countryView.getName().indexOf(country[0]) != -1) {
          countryId = countryView.getId();
        }
      }
    }
    irfqConsultService.putInquiry(view, getPurchase(), countryId);
    write();
  }

  public void getPdtInfo() throws IOException {
    RFQPdtInfo rfqPdtInfo = iPdtProductService.getInquiryPdtInfo(id);
    if (type == 1 && id != null && id > 0) {}

    write(rfqPdtInfo);
  }

  /**
   * @Description: 私人展会询盘
   *
   * @date 2019/1/30 11:10
   * @author lijie@shoestp.cn
   */
  @NeedLogin(userType = UserType.PURCHASE)
  public void putPrivateInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, curLanguage()));
    }
    irfqConsultService.putPrivateInquiry(
        objectMapper.readValue(data, PutInquiryView.class), getPurchase());
    write();
  }

  /**
   * @Description: 展会介绍页1
   *
   * @author winson Zhang
   */
  public String exhibitionshow() {
    setResult("/html/exhibition/exhibitionLasVegas.jsp", false);
    return TRENDS;
  }

  /**
   * @Description: 展会介绍页2
   *
   * @author winson Zhang
   */
  public String ExpoRivaSchuhshow() {
    setResult("/html/exhibition/ExpoRivaSchuh.jsp", false);
    return TRENDS;
  }

  /**
   * @Description: 展会介绍页3
   *
   * @author winson Zhang
   */
  public String guangjiaohuishow() {
    setResult("/html/exhibition/guangjiaohui.jsp", false);
    return TRENDS;
  }

  /**
   * @Description: RFQ readMore页面
   *
   * @author zjl
   */
  public String getRFQReadMore() {
    setResult("/home/readMore.jsp", false);
    return TRENDS;
  }

  private String keyword;
  private Byte t;
  private Boolean unread;
  private Integer lastRelation;
  private Integer start = 0;
  private Integer limit = 10;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void pageMine() throws IOException {
    write(
        rFQConsultService.pageMine(getPurchase(), t, keyword, unread, lastRelation, start, limit));
  }

  private Integer quotationPkey;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void deleteQuotation() throws IOException {
    rFQConsultService.deleteQuotation(getPurchase(), quotationPkey);
    write();
  }

  @Override
  public void getRFQList() throws IOException {
    write(rFQConsultService.getRFQList());
  }

  private Integer rfqPkey;

  @Override
  public void getRFQDetails() throws IOException {
    write(rFQConsultService.getRFQDetails(rfqPkey));
  }

  @NeedLogin(userType = UserType.PURCHASE)
  public void quotationDetail() throws IOException {
    write(rFQConsultService.getQuotation(getPurchase(), quotationPkey));
  }

  Integer consultPkey;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void detail() throws IOException {
    write(rFQConsultService.getDetail(getPurchase(), consultPkey, curLanguage()));
  }

  private Integer relationPkey;
  private Integer nextMessagePkey; // 询盘聊天消息主键, 从该消息开始查询 而不是从头开始查询 查询新消息
  private Integer preMessagePkey; // 询盘聊天消息主键, 从该消息开始查询 而不是从头开始查询 查询历史消息

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void pageMsgs() throws IOException {
    write(
        rFQConsultMessageService.page(
            getPurchase(),
            relationPkey,
            nextMessagePkey,
            preMessagePkey,
            start,
            limit,
            curLanguage()));
  }

  private String content;
  private String imageUrl;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void sendMessage() throws IOException {
    if (imageUrl != null) {
      write(rFQConsultMessageService.sendImageMessage(getPurchase(), relationPkey, imageUrl));
    } else if (content != null) {
      write(rFQConsultMessageService.sendTextMessage(getPurchase(), relationPkey, content));
    } else {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.valid_notblank, curLanguage()));
    }
  }

  private String information;
  private Date validDate;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void addInformation() throws IOException {
    rFQConsultService.addMoreInformation(
        getPurchase(), consultPkey, information, validDate, curLanguage());
    write();
  }

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void close() throws IOException {
    rFQConsultService.close(getPurchase(), consultPkey, curLanguage());
    write();
  }

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void edit() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, curLanguage()));
    }
    EditRFQConsultView editRFQConsultView = objectMapper.readValue(data, EditRFQConsultView.class);
    if (editRFQConsultView.getId() != null && editRFQConsultView.getId() > 0) {
      if (irfqConsultService.edItRFQInquiry(editRFQConsultView, getPurchase()) == 1) {
        write();
        return;
      } else {
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.inquiry_wrong_data, curLanguage()));
      }
    }
    writeErr(-1, "ID为空");
  }

  private String images;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void addImage() throws IOException {
    rFQConsultService.addImage(getPurchase(), consultPkey, images, curLanguage());
    write();
  }

  private String products;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void addProductRequest() throws IOException {
    rFQConsultService.addProductRequest(getPurchase(), consultPkey, products, curLanguage());
    write();
  }

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void unreadCount() throws IOException {
    write(rFQConsultService.countUnread(getPurchase()));
  }

  /**
   * 校验putRFQInquiry接口的前端数据
   *
   * @param rfqConsultView 前端数据
   * @author Jianhua Ying
   */
  private void validPutRFQInquiry(PutRFQConsultView rfqConsultView) {

    if (rfqConsultView.getMin_price() != null
        && (rfqConsultView.getMin_price().compareTo(BigDecimal.valueOf(100000)) >= 0
            || rfqConsultView.getMin_price().compareTo(BigDecimal.ZERO) < 0)) {
      throw new WebMessageException(
          MessageBuild.buildMessage(
              ReturnCode.valid_price_range, HomeAction.curLanguage(), "0.00", "99999.99"));
    }
    if (rfqConsultView.getMax_price() != null
        && (rfqConsultView.getMax_price().compareTo(BigDecimal.valueOf(100000)) >= 0
            || rfqConsultView.getMin_price().compareTo(BigDecimal.ZERO) < 0)) {
      throw new WebMessageException(
          MessageBuild.buildMessage(
              ReturnCode.valid_price_range, HomeAction.curLanguage(), "0.00", "99999.99"));
    }
  }
}
