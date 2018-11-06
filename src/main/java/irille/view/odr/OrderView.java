package irille.view.odr;

import irille.pub.formatter.CurrencyFormatter;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltErate;
import irille.view.BaseView;
import irille.view.plt.PayTypeView;
import irille.view.usr.AddressView;
import irille.view.usr.SupplierView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderView implements BaseView{
	
	private String number;//订单号
	private String currency;//订单采用货币的简写
	private String currencySymbol;//订单采用货币的符号
	private Date date;//下单时间
	private Byte status;//订单状态
	private Byte cancelReason;//订单取消原因
	
	//private Byte payType;//支付方式
	private PayTypeView payType;//支付方式
	private Map<String, Object> payContent;//支付信息,json格式
	private String shippingCharges;//运费价格
	private String Insurance;//保险费用
	private String shippingChargesAndInsurance;//运费价格+保险费用
	private Integer itemsCount;//商品合计数量
	private String subtotal;//商品金额合计
	private String total;//订单总金额
	private String remark;//订单备注
	
	private String deliveryType;//发货方式
	private String shipName;//收货人名字
	private AddressView shipAddress;//收货地址
	private AddressView billAddress;//账单地址
	private String contactNum;//联系号码
	
	private List<OrderLineView> lines;//订单明细
	private List<HistoryView> historys;//订单历史
	private  String supplierShowName;//供应商店铺名称
	private String usrEmail;//当前采购商的登录邮箱
	private Integer orderType;
	private  String expressNum;
	private String pagRemark;
	private  String usrlang;
	
	
	public String getUsrlang() {
		return usrlang;
	}
	public void setUsrlang(String usrlang) {
		this.usrlang = usrlang;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getExpressNum() {
		return expressNum;
	}
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	public String getPagRemark() {
		return pagRemark;
	}
	public void setPagRemark(String pagRemark) {
		this.pagRemark = pagRemark;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getSupplierShowName() {
		return supplierShowName;
	}
	public void setSupplierShowName(String supplierShowName) {
		this.supplierShowName = supplierShowName;
	}
	private SupplierView supplier;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public Long getDate() {
		return date==null?null:date.getTime();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(Byte cancelReason) {
		this.cancelReason = cancelReason;
	}
	public PayTypeView getPayType() {
		return payType;
	}
	public void setPayType(PayTypeView payType) {
		this.payType = payType;
	}
	public Map<String, Object> getPayContent() {
		return payContent;
	}
	@SuppressWarnings("unchecked")
	public void setPayContent(String payContent) throws JsonParseException, JsonMappingException, IOException, JSONException {
		if(payContent == null)
			this.payContent = new HashMap<String, Object>();
		else {
//			System.out.println(payContent);
//			JSONObject payContentJson = new JSONObject(payContent);
//			this.payContent = new HashMap<>();
//			this.payContent.put("Name", payContentJson.getString("Name"));
//			this.payContent.put("SentMoney", payContentJson.getString("SentMoney"));
//			this.payContent.put("MTCNNumber", payContentJson.getString("MTCNNumber"));
//			this.payContent.put("Contents", payContentJson.getString("Contents"));
			this.payContent = new ObjectMapper().readValue(payContent, Map.class);
		}
	}
	public String getShippingCharges() {
		return shippingCharges;
	}
	public void setShippingCharges(BigDecimal shippingCharges, PltErate currency, boolean symbol, boolean shortName) {
		this.shippingCharges = CurrencyFormatter.format(shippingCharges, currency, symbol, shortName);
	}
	public void setShippingCharges(BigDecimal shippingCharges) {
		this.shippingCharges = shippingCharges.toString();
	}
	public String getInsurance() {
		return Insurance;
	}
	public void setInsurance(BigDecimal insurance, PltErate currency, boolean symbol, boolean shortName) {
		Insurance = CurrencyFormatter.format(insurance, currency, symbol, shortName);
	}
	public void setInsurance(BigDecimal insurance) {
		Insurance = insurance.toString();
	}
	public String getShippingChargesAndInsurance() {
		return shippingChargesAndInsurance;
	}
	public void setShippingChargesAndInsurance(BigDecimal shippingChargesAndInsurance) {
		this.shippingChargesAndInsurance = shippingChargesAndInsurance.toString();
	}
	public void setShippingChargesAndInsurance(BigDecimal shippingChargesAndInsurance, PltErate currency, boolean symbol, boolean shortName) {
		this.shippingChargesAndInsurance = CurrencyFormatter.format(shippingChargesAndInsurance, currency, symbol, shortName);
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public AddressView getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(AddressView shipAddress) {
		this.shipAddress = shipAddress;
	}
	public AddressView getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(AddressView billAddress) {
		this.billAddress = billAddress;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public Integer getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(Integer itemsCount) {
		this.itemsCount = itemsCount;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal.toString();
	}
	public void setSubtotal(BigDecimal subtotal, PltErate currency, boolean symbol, boolean shortName) {
		this.subtotal = CurrencyFormatter.format(subtotal, currency, symbol, shortName);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total.toString();
	}
	public void setTotal(BigDecimal total, PltErate currency, boolean symbol, boolean shortName) {
		this.total = CurrencyFormatter.format(total, currency, symbol, shortName);
	}
	public List<OrderLineView> getLines() {
		return lines;
	}
	public void setLines(List<OrderLineView> lines) {
		this.lines = lines;
	}
	public List<HistoryView> getHistorys() {
		return historys;
	}
	public void setHistorys(List<HistoryView> historys) {
		this.historys = historys;
	}
	public SupplierView getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierView supplier) {
		this.supplier = supplier;
	}
	
}
