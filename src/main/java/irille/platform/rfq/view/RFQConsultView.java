package irille.platform.rfq.view;

import java.util.Date;
import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultView implements BaseView {

	private Integer pkey; // 编号 INT
	private String title; // 标题 STR(200)
	private String image; // 图片 STR(2000)<null>
	private CountryView country; // 国家管理 <表主键:PltCountry> INT
	private ProductView product; // 产品 <表主键:PdtProduct> INT<null>
	private Byte haveNewMsg; // 有新消息 <OYn> BYTE
	// YES:1,是
	// NO:0,否
	private String content; // 内容 STR(200)<null>
	private Integer leftCount; // 剩余抢单次数 INT
	private Integer quantity; // 商品数量 INT
	private Byte unit; // 货物单位 <RFQConsultUnit> BYTE
	// PAIR:1,双
	private PurchaseView purchase; // 采购商 <表主键:UsrPurchase> INT
	private SupplierView supplier; // 供应商 <表主键:UsrSupplier> INT
	private String type; // RFQ状态 <RFQConsultStatus> BYTE
	// OFF:1,关闭
	// ON:2,开启
	private Byte status; // 询盘类型 <RFQConsultType> BYTE
	// RFQ:1,FRQ询盘
	// INQUIRY:2,询盘
	// PrivateINQUIRY:3,私人展会询盘
	private Byte verifyStatus; // 审核状态 <RFQConsultVerifyStatus> BYTE
	// UNAUDITED:1,未审核
	// FAIL:2,未通过
	// PASS:3,通过
	private Date validDate; // 有效期至 TIME
	private String price; // 价格(价格区间) STR(20)<null>
	private Byte payType; // 支付方式 <RFQConsultPayType> BYTE
	// TT:1,TT支付
	// OFFINEPAY:2,线下支付
	private Byte shippingType; // 配送方式 <RFQConsultShippingType> BYTE
	// OCEANSHIPPING:1,海运
	private Integer currency; // 费率设置 <表主键:PltErate> INT
	private String destination; // 字符200 STR(200)<null>
	private Integer total; // 总抢单数 INT
	private Short changeCount; // 修改总数 SHORT
	private String extraDescription; // 修改总数 STR(2000)<null>
	private Date createTime; // 创建时间 TIME
	private Short rowVersion; // 版本 SHORT
	private List<RFQConsultRelationView> relations;//抢单成功的供应商
}
