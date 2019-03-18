package irille.homeAction.rfq;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultMessageService;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;

import irille.Filter.svr.ItpCheckPurchaseLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
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
  @ItpCheckPurchaseLogin.NeedLogin
  public void putRFQInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, HomeAction.curLanguage()));
    }
    irfqConsultService.putRFQInquiry(
        objectMapper.readValue(data, PutRFQConsultView.class), getPurchase());
    write();
  }

  /**
   * @Description: RFQ询盘
   *
   * @date 2019/1/30 11:10
   * @author lijie@shoestp.cn
   */
  @ItpCheckPurchaseLogin.NeedLogin
  public void putSupplierInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, HomeAction.curLanguage()));
    }
    PutSupplierConsultView putSupplierConsultView =
        objectMapper.readValue(data, PutSupplierConsultView.class);
    if (putSupplierConsultView.getTitle() == null
        || putSupplierConsultView.getTitle().length() < 1) {
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
  @ItpCheckPurchaseLogin.NeedLogin
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
  @ItpCheckPurchaseLogin.NeedLogin
  public void putPrivateInquiry() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      write(MessageBuild.build(201, HomeAction.curLanguage()));
      return;
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
  private Integer start = 0;
  private Integer limit = 10;

  @Override
  @NeedLogin
  public void pageMine() throws IOException {
    write(rFQConsultService.pageMine(getPurchase(), t, keyword, unread, start, limit));
  }

  private Integer quotationPkey;

  @Override
  @NeedLogin
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

  @NeedLogin
  public void quotationDetail() throws IOException {
    write(rFQConsultService.getQuotation(getPurchase(), quotationPkey));
  }

  Integer consultPkey;

  @Override
  @NeedLogin
  public void detail() throws IOException {
    write(rFQConsultService.getDetail(getPurchase(), consultPkey));
  }

  private Integer relationPkey;
  private Integer nextMessagePkey; // 询盘聊天消息主键, 从该消息开始查询 而不是从头开始查询 查询新消息
  private Integer preMessagePkey; // 询盘聊天消息主键, 从该消息开始查询 而不是从头开始查询 查询历史消息

  @Override
  @NeedLogin
  public void pageMsgs() throws IOException {
    write(
        rFQConsultMessageService.page(
            getPurchase(), relationPkey, nextMessagePkey, preMessagePkey, start, limit));
  }

  private String content;

  @Override
  @NeedLogin
  public void sendMessage() throws IOException {
    write(rFQConsultMessageService.send(getPurchase(), relationPkey, content));
  }

  private String information;
  private Date validDate;

  @Override
  @NeedLogin
  public void addInformation() throws IOException {
    rFQConsultService.addMoreInformation(getPurchase(), consultPkey, information, validDate);
    write();
  }

  @Override
  @NeedLogin
  public void close() throws IOException {
    rFQConsultService.close(getPurchase(), consultPkey);
    write();
  }

  @Override
  @NeedLogin
  public void edit() throws IOException {
    String data = getJsonBody();
    if (data == null) {
      write(MessageBuild.build(201, HomeAction.curLanguage()));
      return;
    }
    EditRFQConsultView editRFQConsultView = objectMapper.readValue(data, EditRFQConsultView.class);
    if (editRFQConsultView.getId() != null && editRFQConsultView.getId() > 0) {
      if (irfqConsultService.edItRFQInquiry(editRFQConsultView, getPurchase()) == 1) {
        write();
        return;
      } else {
        writeErr(-1, "该询盘不存在或者发生未知错误");
        return;
      }
    }
    writeErr(-1, "ID为空");
  }

  private String images;

  @Override
  @NeedLogin
  public void addImage() throws IOException {
    rFQConsultService.addImage(getPurchase(), consultPkey, images);
    write();
  }

  private String products;

  @Override
  @NeedLogin
  public void addProductRequest() throws IOException {
    rFQConsultService.addProductRequest(getPurchase(), consultPkey, products);
    write();
  }

  @Override
  @NeedLogin
  public void unreadCount() throws IOException {
    write(rFQConsultService.countUnread(getPurchase()));
  }
}
