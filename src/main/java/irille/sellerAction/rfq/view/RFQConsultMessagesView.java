package irille.sellerAction.rfq.view;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultMessagesView implements BaseView {

  public RFQConsultMessagesView(
      List<RFQConsultMessageView> msgs,
      RFQConsultMessageContactView myself,
      RFQConsultMessageContactView another) {
    this.msgs = msgs;
    this.myself = myself;
    this.another = another;
  }

  private List<RFQConsultMessageView> msgs;
  private RFQConsultMessageContactView myself;
  private RFQConsultMessageContactView another;
}
