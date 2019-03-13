package irille.shop.odr;

import java.math.BigDecimal;
import java.util.Date;

import irille.pub.bean.BeanLong;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.odr.Odr.CancelType;
import irille.shop.odr.Odr.OdrState;
import irille.shop.odr.Odr.OdrType;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltPay;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

public class OdrOrder extends BeanLong<OdrOrder> {
  public static final Tb<?> TB = new Tb(OdrOrder.class, "订单管理").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtLongPkey()),
    PURCHASE(UsrPurchase.fldOutKey().setName("用户").setNull()),
    SUPPLIER(UsrSupplier.fldOutKey().setName("供应商")),
    ORDER_NUM(SYS.STR__100_NULL, "订单号"),
    EXPRESS_NUM(SYS.STR__100_NULL, "运单号"),
    //	DELIVERY(PltFreightSeller.fldOutKey().setName("快递公司").setNull()),//发货方式
    DELIVERY(SYS.STR__100_NULL, "发货方式"),
    PAG_REMARKS(SYS.REM__200_NULL, "包裹备注"),
    ODR_REMARKS(SYS.REM__200_NULL, "订单备注"),
    TIME(SYS.DATE_TIME, "下单时间"),
    STATE(TB.crt(Odr.OdrState.DEFAULT)), // 订单状态
    TYPE(TB.crt(Odr.OdrType.DEFAULT)),
    FREIGHT_PRICE(SYS.PRICE, "运费"),
    INSURANCE_PRICE(SYS.PRICE, "保险费"),
    PROD_PRICE(SYS.PRICE_NULL, "产品总价"),
    PRICE_TOTAL(SYS.PRICE_NULL, "总价"),
    CURRENCY(PltErate.fldOutKey("currency", "货币")),
    ADDRESS(SYS.JSON, "收货地址"),
    NAME(SYS.STR__20, "名字"),
    POSTALCODE(SYS.STR__100, "邮政编码"),
    PHONE(SYS.STR__100, "电话号码"),
    PAYCONTENT(SYS.JSON, "支付内容", true),
    BILLING_ADDRESS(SYS.JSON, "账单地址", true),
    SERVICE_CHARGE(SYS.PRICE, "手续费"),
    ADDITIONALCOST(SYS.PRICE, "附加费用"),
    ODR_CANCEL(TB.crt(Odr.CancelType.DEFAULT).setNull()), // 订单取消原因
    PAY_TYPE(PltPay.fldOutKey()),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    public static final Index IDX_ORDER_NUM = TB.addIndex("orderNum", true, ORDER_NUM);
    public static final Index IDX_PURCHASE = TB.addIndex("purchase", false, PURCHASE);
    public static final Index IDX_SUPPLIER = TB.addIndex("supplier", false, SUPPLIER);
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    private Fld<?> _fld;

    private T(Class<?> clazz, String name, boolean... isnull) {
      _fld = TB.addOutKey(clazz, this, name, isnull);
    }

    private T(IEnumFld fld, boolean... isnull) {
      this(fld, null, isnull);
    }

    private T(IEnumFld fld, String name, boolean... null1) {
      _fld = TB.add(fld, this, name, null1);
    }

    private T(IEnumFld fld, String name, int strLen) {
      _fld = TB.add(fld, this, name, strLen);
    }

    private T(Fld<?> fld) {
      _fld = TB.add(fld, this);
    }

    public Fld<?> getFld() {
      return _fld;
    }
  }

  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Long _pkey; // 编号  LONG
  private Integer _purchase; // 用户 <表主键:UsrPurchase>  INT<null>
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private String _orderNum; // 订单号  STR(100)<null>
  private String _expressNum; // 运单号  STR(100)<null>
  private String _delivery; // 发货方式  STR(100)<null>
  private String _pagRemarks; // 包裹备注  STR(200)<null>
  private String _odrRemarks; // 订单备注  STR(200)<null>
  private Date _time; // 下单时间  TIME
  private Byte _state; // 订单状态 <OdrState>  BYTE
  // WAIT:0,待付款
  // WAITCONFIRM:1,等待确认付款
  // ERROR:2,付款错误
  // WAITDELIVER:3,等待发货
  // DELIVER:4,已发货
  // COMPLETE:5,完成订单
  // CANCEL:6,已取消订单
  // DELETED:7,已删除
  private Byte _type; // 订单类型 <OdrType>  BYTE
  // STATETWO:0,普通订单
  // STATEONE:1,联合采购订单
  private BigDecimal _freightPrice; // 运费  DEC(14,4)
  private BigDecimal _insurancePrice; // 保险费  DEC(14,4)
  private BigDecimal _prodPrice; // 产品总价  DEC(14,4)<null>
  private BigDecimal _priceTotal; // 总价  DEC(14,4)<null>
  private Integer _currency; // 货币 <表主键:PltErate>  INT
  private String _address; // 收货地址  JSONOBJECT
  private String _name; // 名字  STR(20)
  private String _postalcode; // 邮政编码  STR(100)
  private String _phone; // 电话号码  STR(100)
  private String _paycontent; // 支付内容  JSONOBJECT<null>
  private String _billingAddress; // 账单地址  JSONOBJECT<null>
  private BigDecimal _serviceCharge; // 手续费  DEC(14,4)
  private BigDecimal _additionalcost; // 附加费用  DEC(14,4)
  private Byte _odrCancel; // 取消原因 <CancelType>  BYTE<null>
  // NOSTOCK:0,缺货
  // NOEFFECTIVEODR:1,不是有效订单
  // BUYERREQ:2,买家要求
  // OTHER:3,其他原因
  private Integer _payType; // 支付设置 <表主键:PltPay>  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public OdrOrder init() {
    super.init();
    _purchase = null; // 用户 <表主键:UsrPurchase>  INT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _orderNum = null; // 订单号  STR(100)
    _expressNum = null; // 运单号  STR(100)
    _delivery = null; // 发货方式  STR(100)
    _pagRemarks = null; // 包裹备注  STR(200)
    _odrRemarks = null; // 订单备注  STR(200)
    _time = Env.getTranBeginTime(); // 下单时间  TIME
    _state = OdrState.DEFAULT.getLine().getKey(); // 订单状态 <OdrState>  BYTE
    _type = OdrType.DEFAULT.getLine().getKey(); // 订单类型 <OdrType>  BYTE
    _freightPrice = ZERO; // 运费  DEC(14,4)
    _insurancePrice = ZERO; // 保险费  DEC(14,4)
    _prodPrice = null; // 产品总价  DEC(14,4)
    _priceTotal = null; // 总价  DEC(14,4)
    _currency = null; // 货币 <表主键:PltErate>  INT
    _address = null; // 收货地址  JSONOBJECT
    _name = null; // 名字  STR(20)
    _postalcode = null; // 邮政编码  STR(100)
    _phone = null; // 电话号码  STR(100)
    _paycontent = null; // 支付内容  JSONOBJECT
    _billingAddress = null; // 账单地址  JSONOBJECT
    _serviceCharge = ZERO; // 手续费  DEC(14,4)
    _additionalcost = ZERO; // 附加费用  DEC(14,4)
    _odrCancel = CancelType.DEFAULT.getLine().getKey(); // 取消原因 <CancelType>  BYTE
    _payType = null; // 支付设置 <表主键:PltPay>  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static OdrOrder loadUniqueOrderNum(boolean lockFlag, String orderNum) {
    return (OdrOrder) loadUnique(T.IDX_ORDER_NUM, lockFlag, orderNum);
  }

  public static OdrOrder chkUniqueOrderNum(boolean lockFlag, String orderNum) {
    return (OdrOrder) chkUnique(T.IDX_ORDER_NUM, lockFlag, orderNum);
  }

  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
  }

  public Integer getPurchase() {
    return _purchase;
  }

  public void setPurchase(Integer purchase) {
    _purchase = purchase;
  }

  public UsrPurchase gtPurchase() {
    if (getPurchase() == null) return null;
    return (UsrPurchase) get(UsrPurchase.class, getPurchase());
  }

  public void stPurchase(UsrPurchase purchase) {
    if (purchase == null) setPurchase(null);
    else setPurchase(purchase.getPkey());
  }

  public Integer getSupplier() {
    return _supplier;
  }

  public void setSupplier(Integer supplier) {
    _supplier = supplier;
  }

  public UsrSupplier gtSupplier() {
    if (getSupplier() == null) return null;
    return (UsrSupplier) get(UsrSupplier.class, getSupplier());
  }

  public void stSupplier(UsrSupplier supplier) {
    if (supplier == null) setSupplier(null);
    else setSupplier(supplier.getPkey());
  }

  public String getOrderNum() {
    return _orderNum;
  }

  public void setOrderNum(String orderNum) {
    _orderNum = orderNum;
  }

  public String getExpressNum() {
    return _expressNum;
  }

  public void setExpressNum(String expressNum) {
    _expressNum = expressNum;
  }

  public String getDelivery() {
    return _delivery;
  }

  public void setDelivery(String delivery) {
    _delivery = delivery;
  }

  public String getPagRemarks() {
    return _pagRemarks;
  }

  public void setPagRemarks(String pagRemarks) {
    _pagRemarks = pagRemarks;
  }

  public String getOdrRemarks() {
    return _odrRemarks;
  }

  public void setOdrRemarks(String odrRemarks) {
    _odrRemarks = odrRemarks;
  }

  public Date getTime() {
    return _time;
  }

  public void setTime(Date time) {
    _time = time;
  }

  public Byte getState() {
    return _state;
  }

  public void setState(Byte state) {
    _state = state;
  }

  public OdrState gtState() {
    return (OdrState) (OdrState.WAIT.getLine().get(_state));
  }

  public void stState(OdrState state) {
    _state = state.getLine().getKey();
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OdrType gtType() {
    return (OdrType) (OdrType.STATETWO.getLine().get(_type));
  }

  public void stType(OdrType type) {
    _type = type.getLine().getKey();
  }

  public BigDecimal getFreightPrice() {
    return _freightPrice;
  }

  public void setFreightPrice(BigDecimal freightPrice) {
    _freightPrice = freightPrice;
  }

  public BigDecimal getInsurancePrice() {
    return _insurancePrice;
  }

  public void setInsurancePrice(BigDecimal insurancePrice) {
    _insurancePrice = insurancePrice;
  }

  public BigDecimal getProdPrice() {
    return _prodPrice;
  }

  public void setProdPrice(BigDecimal prodPrice) {
    _prodPrice = prodPrice;
  }

  public BigDecimal getPriceTotal() {
    return _priceTotal;
  }

  public void setPriceTotal(BigDecimal priceTotal) {
    _priceTotal = priceTotal;
  }

  public Integer getCurrency() {
    return _currency;
  }

  public void setCurrency(Integer currency) {
    _currency = currency;
  }

  public PltErate gtCurrency() {
    if (getCurrency() == null) return null;
    return (PltErate) get(PltErate.class, getCurrency());
  }

  public void stCurrency(PltErate currency) {
    if (currency == null) setCurrency(null);
    else setCurrency(currency.getPkey());
  }

  public String getAddress() {
    return _address;
  }

  public void setAddress(String address) {
    _address = address;
  }

  public JSONObject gtAddress() throws JSONException {
    return getAddress() == null ? new JSONObject() : new JSONObject(getAddress());
  }

  public void stAddress(JSONObject address) {
    setAddress(address == null ? null : address.toString());
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public String getPostalcode() {
    return _postalcode;
  }

  public void setPostalcode(String postalcode) {
    _postalcode = postalcode;
  }

  public String getPhone() {
    return _phone;
  }

  public void setPhone(String phone) {
    _phone = phone;
  }

  public String getPaycontent() {
    return _paycontent;
  }

  public void setPaycontent(String paycontent) {
    _paycontent = paycontent;
  }

  public JSONObject gtPaycontent() throws JSONException {
    return getPaycontent() == null ? new JSONObject() : new JSONObject(getPaycontent());
  }

  public void stPaycontent(JSONObject paycontent) {
    setPaycontent(paycontent == null ? null : paycontent.toString());
  }

  public String getBillingAddress() {
    return _billingAddress;
  }

  public void setBillingAddress(String billingAddress) {
    _billingAddress = billingAddress;
  }

  public JSONObject gtBillingAddress() throws JSONException {
    return getBillingAddress() == null ? new JSONObject() : new JSONObject(getBillingAddress());
  }

  public void stBillingAddress(JSONObject billingAddress) {
    setBillingAddress(billingAddress == null ? null : billingAddress.toString());
  }

  public BigDecimal getServiceCharge() {
    return _serviceCharge;
  }

  public void setServiceCharge(BigDecimal serviceCharge) {
    _serviceCharge = serviceCharge;
  }

  public BigDecimal getAdditionalcost() {
    return _additionalcost;
  }

  public void setAdditionalcost(BigDecimal additionalcost) {
    _additionalcost = additionalcost;
  }

  public Byte getOdrCancel() {
    return _odrCancel;
  }

  public void setOdrCancel(Byte odrCancel) {
    _odrCancel = odrCancel;
  }

  public CancelType gtOdrCancel() {
    return (CancelType) (CancelType.NOSTOCK.getLine().get(_odrCancel));
  }

  public void stOdrCancel(CancelType odrCancel) {
    _odrCancel = odrCancel.getLine().getKey();
  }

  public Integer getPayType() {
    return _payType;
  }

  public void setPayType(Integer payType) {
    _payType = payType;
  }

  public PltPay gtPayType() {
    if (getPayType() == null) return null;
    return (PltPay) get(PltPay.class, getPayType());
  }

  public void stPayType(PltPay payType) {
    if (payType == null) setPayType(null);
    else setPayType(payType.getPkey());
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
