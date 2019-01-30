package irille.platform.rfq.view;

import lombok.Data;

@Data
public class RFQConsultRelationView {

	private Integer pkey; // 编号 INT
	private Integer consult; // 询盘 <表主键:RFQConsult> INT
	private SupplierView supplier; // 供应商 <表主键:UsrSupplier> INT
	private Integer purchaseId; // 采购商 <表主键:UsrPurchase> INT
	private Byte favorite; // 是否添加FLAG <OYn> BYTE
	// YES:1,是
	// NO:0,否
	private Short rowVersion; // 版本 SHORT
}
