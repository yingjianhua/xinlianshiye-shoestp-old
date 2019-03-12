package irille.platform.rfq.view;

import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultRelationView implements BaseView {

  private Integer pkey; // 编号 INT
  private SupplierView supplier; // 供应商 <表主键:UsrSupplier> INT
  private Integer purchaseId; // 采购商 <表主键:UsrPurchase> INT
  private Boolean favorite; // 是否添加FLAG <OYn> BYTE
  // YES:1,是
  // NO:0,否
  private Short rowVersion; // 版本 SHORT

  private RFQConsultView consult; // 询盘信息

  private RFQConsultMessageView lastMsg; // 最后一条消息
}
