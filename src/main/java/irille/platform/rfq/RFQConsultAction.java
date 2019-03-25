package irille.platform.rfq;

import java.io.IOException;

import javax.inject.Inject;

import irille.Dao.RFQ.RFQConsultService;
import irille.Entity.RFQ.RFQConsult;
import irille.action.ActionBase;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.platform.rfq.view.RFQConsultView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RFQConsultAction extends ActionBase<RFQConsult> implements IRFQConsultAction {

  private static final long serialVersionUID = 1L;

  @Inject private RFQConsultService rFQConsultService;

  private RFQConsultView consult = new RFQConsultView();
  private RFQConsultRelationView relation = new RFQConsultRelationView();

  @Override
  public Class<RFQConsult> beanClazz() {
    return RFQConsult.class;
  }

  @Override
  public void list() throws IOException {
    write(rFQConsultService.page(getStart(), getLimit(), consult));
  }

  @Override
  public void detail() throws IOException {
    write(rFQConsultService.detail(consult));
  }

  private Boolean verify;

  @Override
  public void approve() throws IOException {
    rFQConsultService.approve(consult, verify);
    write();
  }

  @Override
  public void delete() throws IOException {
    rFQConsultService.delete(consult);
    write();
  }

  @Override
  public void recommend() throws IOException {
    rFQConsultService.recommend(consult);
    write();
  }

  @Override
  public void offerInfo() throws IOException {
    write(rFQConsultService.relationInfo(relation));
  }

  @Override
  public void getMsgList() throws IOException {
    write(rFQConsultService.getRFQMsgList(consult, getStart(), getLimit()));
  }

  private Integer id;

  @Override
  public void getMessage() throws IOException {
    write(rFQConsultService.getMessage(id, getStart(), getLimit()));
  }

  private Integer rfqPkey;
  private Byte type;
  private String supplierName;
  private String purchaseName;
  private String productName;
  @Override
  public void getInqList() throws IOException {
    write(rFQConsultService.getInqList(getStart(),getLimit(),type,supplierName,purchaseName,productName));
  }

  @Override
  public void getInqStatus() throws Exception {
    writerOrExport(rFQConsultService.getInqStatus());
  }

  @Override
  public void getInqDetail() throws IOException {
    write(rFQConsultService.getInqDetail(rfqPkey));
  }
}
