package irille.sellerAction.omt;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeetingOrderManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeetingProductManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeettingExhibitionService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.omt.inf.IOdrMeetingAction;
import irille.view.Manage.OdrMeeting.initiatedActivity.AllProductsView;
import irille.view.Manage.OdrMeeting.initiatedActivity.LaunchlistMeettingView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderInformationView;
import lombok.Getter;
import lombok.Setter;

public class OdrMeetingAction extends SellerAction<OrderMeeting> implements IOdrMeetingAction {

  @Inject private IOdrMeetingManageService odrMeetingManageService;
  @Inject private IOdrMeetingProductManageService odrMeetingProductManageService;

  @Inject private IOdrMeettingExhibitionService odrMeettingExhibitionService;

  @Inject private IOdrMeetingOrderManageService odrMeetingOrderManageService;

  @Override
  public void loadsupstate() throws Exception {
    writerOrExport(odrMeetingManageService.loadsupstate());
  }

  @Override
  public void loadstate() throws Exception {
    writerOrExport(odrMeetingManageService.loadstate());
  }

  @Override
  public void exhibitionlist() throws Exception {
    write(odrMeettingExhibitionService.listExhibition());
  }

  @Setter @Getter private String name;
  @Setter @Getter private Integer state;

  @Setter @Getter private Integer supstate;

  public void getMyOdrMeetingList() throws Exception {
    write(
        odrMeetingManageService.getMyOdrMeetingList(
            getStart(), getLimit(), name, supstate, state, getSupplier().getPkey()));
    ;
  }

  @Override
  public void getMyJoinOdrMeetingList() throws Exception {
    write(
        odrMeetingManageService.getMyJoinOdrMeetingList(
            getStart(), getLimit(), name, state, getSupplier().getPkey()));
  }

  @Override
  public void getMyOtherOdrMeetingList() throws Exception {
    write(
        odrMeetingManageService.getMyOtherOdrMeetingList(
            getStart(), getLimit(), name, state, getSupplier().getPkey()));
  }

  @Setter @Getter private String pkeys;

  @Override // 發佈訂購會 刪除訂購會
  public void batchdelete() throws Exception {
    odrMeetingManageService.batchdelete(pkeys);
    write();
  }

  @Override // 參加訂購會 刪除訂購會
  public void joindelete() throws Exception {
    odrMeetingManageService.joindelete(pkeys);
    write();
  }

  @Override // 參加訂購會 插入訂購會
  public void joininsert() throws Exception {
    odrMeetingManageService.insertjoinOdr(id, getSupplier().getPkey());
    write();
  }

  @Setter @Getter private Integer id;
  @Setter @Getter private Integer fstate; // 发起订购会修改状态 继续/暂停

  @Override
  public void Meettingupdstate() throws Exception {
    odrMeetingManageService.Meettingupdstate(id, fstate);
    write();
  }

  public void getorderInformation() throws IOException {
    write(odrMeetingManageService.getorderInformation(id));
  }

  @Setter @Getter private Integer status;
  @Setter @Getter private String inputContent;
  @Setter @Getter private Integer productId;

  public void getOrderGoodsList() throws IOException {
    write(
        odrMeetingProductManageService.getOrderGoodsList(
            getStart(), getLimit(), id, status, inputContent, getSupplier().getPkey(), supplierId));
  }

  public void updateStatus() throws IOException {
    odrMeetingProductManageService.updateStatus(id);
    write();
  }

  /**
   * @Description: 逻辑删除 参加订购会合作商
   *
   * @date 2018/11/22 11:14
   * @anthor wilson zhang
   */
  public void deletejoinOdr() throws IOException {
    odrMeetingManageService.deletejoinOdr(id);
    write();
  }

  /**
   * @Description: 插入发布订购会
   *
   * @date 2018/11/20 16:26
   * @anthor wilson zhang
   */
  @Setter @Getter private String llmv;

  @Override
  public void insOdrmeetting() throws Exception {
    LaunchlistMeettingView ll = objectMapper.readValue(llmv, LaunchlistMeettingView.class);
    ll.setSupplierid(getSupplier().getPkey());
    odrMeetingManageService.insOdrmeetting(ll);
    write();
  }

  public void removeProduct() throws IOException {
    odrMeetingProductManageService.removeProduct(id);
    write();
  }

  public void getProducts() throws IOException {
    write(odrMeetingProductManageService.getProducts(getSupplier().getPkey()));
  }

  public void getAddedProducts() throws IOException {
    write(odrMeetingProductManageService.getAddedProducts(id, getSupplier().getPkey()));
  }

  @Setter @Getter private String information;

  @Override
  public void updaddress() throws Exception {
    OrderInformationView l = objectMapper.readValue(information, OrderInformationView.class);
    OrderMeeting omt = new OrderMeeting();
    omt.setPkey(l.getId());
    omt.setMailname(l.getReceiver());
    omt.setPostcode(l.getZip());
    omt.setMailtel(l.getContactNumber());
    omt.setMailafullddress(l.getAddress());
    odrMeetingManageService.updaddress(omt);
    write();
  }

  @Setter @Getter String products;

  public void addProducts() throws IOException {
    JsonParser jsonParser = new JsonFactory().createParser(products);
    List<AllProductsView> productsViewList =
        new ObjectMapper().readValue(jsonParser, new TypeReference<List<AllProductsView>>() {});
    odrMeetingProductManageService.addProducts(id, getSupplier().getPkey(), productsViewList);
    write();
  }

  public void getPartnerList() throws IOException {
    write(odrMeetingManageService.cooperationsupplier(getStart(), getLimit(), status, name, id));
  }

  public void getSalesInfo() throws IOException {
    write(
        odrMeetingManageService.getMeetingSaleInfo(
            getStart(), getLimit(), getId(), getSupplier().getPkey()));
  }

  @Getter @Setter private Integer classification;
  @Getter @Setter private Integer supplierId;
  @Getter @Setter private Integer orderStatus;

  public void getOmtOrderList() throws IOException {
    write(
        odrMeetingOrderManageService.getOmtOrderList(
            id,
            productId,
            supplierId,
            getSupplier().getPkey(),
            getStart(),
            getLimit(),
            classification,
            status,
            orderStatus,
            inputContent));
  }

  public void getOrderStatus() throws IOException {
    write(odrMeetingOrderManageService.getOrderStatus());
  }

  @Getter @Setter private String input;

  public void getSalesDetails() throws IOException {
    write(
        odrMeetingOrderManageService.getSalesDetails(
            getStart(), getLimit(), id, input, status, productId, getSupplier().getPkey()));
  }

  public void updSendStatus() throws IOException {
    odrMeetingOrderManageService.updSendStatus(id);
    write();
  }

  public void updLoadSupStatus() throws IOException {
    odrMeetingManageService.updLoadSupStatus(id, status);
    write();
  }

  public void isAuthStatus() throws Exception {
    writerOrExport(odrMeetingManageService.isAuthStatus());
  }

  @Getter @Setter private String orderNumber;
  @Getter @Setter private String remarks;

  public void updAudit() throws IOException {
    odrMeetingManageService.updAudit(id, getSupplier().getPkey(), orderNumber, remarks);
    write();
  }

  public void getLogistics() throws IOException {
    write(odrMeetingManageService.getLogistics(id, getSupplier().getPkey()));
  }
}
