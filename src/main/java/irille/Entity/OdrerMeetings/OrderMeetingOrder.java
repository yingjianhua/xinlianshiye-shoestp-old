package irille.Entity.OdrerMeetings;

import java.util.Date;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.odr.OdrOrder;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:49 */
public class OrderMeetingOrder extends BeanInt<OrderMeetingOrder> {

  public static final Tb TB =
      new Tb(OrderMeetingOrder.class, "订购会-订单补充表").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    ORDERMEETINGID(OrderMeeting.fldOutKey()), // 订购会id
    ORDERID(OdrOrder.fldOutKey()), // 订单id
    PAYMENTTIME(SYS.DATE__NULL, "付款时间"),
    BILLINGSTATUS(TB.crt(Sys.OYn.DEFAULT)), // 结算状态
    WHETHER_TO_SEND(TB.crt(Sys.OYn.NO)), // 是否发送
    BUYERS(UsrPurchase.fldOutKey()), // 采购商
    PARTNER(UsrSupplier.fldOutKey()), // 合作商
    CREATED_TIME(SYS.CREATED_DATE_TIME), // 更新时间
    ROW_VERSION(SYS.ROW_VERSION), // 版本
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    private Fld _fld;

    private T(Class clazz, String name, boolean... isnull) {
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

    private T(Fld fld) {
      _fld = TB.add(fld, this);
    }

    public Fld getFld() {
      return _fld;
    }
  }

  static { // 在此可以加一些对FLD进行特殊设定的代码
    OrderMeetingOrder.T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _ordermeetingid; // 订购会信息 <表主键:OrderMeeting>  INT
  private Long _orderid; // 订单管理 <表主键:OdrOrder>  LONG
  private Date _paymenttime; // 付款时间  DATE<null>
  private Byte _billingstatus; // 是否 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Byte _whetherToSend; // 是否 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Integer _buyers; // 采购商 <表主键:UsrPurchase>  INT
  private Integer _partner; // 供应商 <表主键:UsrSupplier>  INT
  private Date _createdTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public OrderMeetingOrder init() {
    super.init();
    _ordermeetingid = null; // 订购会信息 <表主键:OrderMeeting>  INT
    _orderid = null; // 订单管理 <表主键:OdrOrder>  LONG
    _paymenttime = null; // 付款时间  DATE
    _billingstatus = Sys.OYn.DEFAULT.getLine().getKey(); // 是否 <OYn>  BYTE
    _whetherToSend = Sys.OYn.DEFAULT.getLine().getKey(); // 是否 <OYn>  BYTE
    _buyers = null; // 采购商 <表主键:UsrPurchase>  INT
    _partner = null; // 供应商 <表主键:UsrSupplier>  INT
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Integer getOrdermeetingid() {
    return _ordermeetingid;
  }

  public void setOrdermeetingid(Integer ordermeetingid) {
    _ordermeetingid = ordermeetingid;
  }

  public OrderMeeting gtOrdermeetingid() {
    if (getOrdermeetingid() == null) return null;
    return (OrderMeeting) get(OrderMeeting.class, getOrdermeetingid());
  }

  public void stOrdermeetingid(OrderMeeting ordermeetingid) {
    if (ordermeetingid == null) setOrdermeetingid(null);
    else setOrdermeetingid(ordermeetingid.getPkey());
  }

  public Long getOrderid() {
    return _orderid;
  }

  public void setOrderid(Long orderid) {
    _orderid = orderid;
  }

  public OdrOrder gtOrderid() {
    if (getOrderid() == null) return null;
    return (OdrOrder) get(OdrOrder.class, getOrderid());
  }

  public void stOrderid(OdrOrder orderid) {
    if (orderid == null) setOrderid(null);
    else setOrderid(orderid.getPkey());
  }

  public Date getPaymenttime() {
    return _paymenttime;
  }

  public void setPaymenttime(Date paymenttime) {
    _paymenttime = paymenttime;
  }

  public Byte getBillingstatus() {
    return _billingstatus;
  }

  public void setBillingstatus(Byte billingstatus) {
    _billingstatus = billingstatus;
  }

  public Boolean gtBillingstatus() {
    return byteToBoolean(_billingstatus);
  }

  public void stBillingstatus(Boolean billingstatus) {
    _billingstatus = booleanToByte(billingstatus);
  }

  public Byte getWhetherToSend() {
    return _whetherToSend;
  }

  public void setWhetherToSend(Byte whetherToSend) {
    _whetherToSend = whetherToSend;
  }

  public Boolean gtWhetherToSend() {
    return byteToBoolean(_whetherToSend);
  }

  public void stWhetherToSend(Boolean whetherToSend) {
    _whetherToSend = booleanToByte(whetherToSend);
  }

  public Integer getBuyers() {
    return _buyers;
  }

  public void setBuyers(Integer buyers) {
    _buyers = buyers;
  }

  public UsrPurchase gtBuyers() {
    if (getBuyers() == null) return null;
    return (UsrPurchase) get(UsrPurchase.class, getBuyers());
  }

  public void stBuyers(UsrPurchase buyers) {
    if (buyers == null) setBuyers(null);
    else setBuyers(buyers.getPkey());
  }

  public Integer getPartner() {
    return _partner;
  }

  public void setPartner(Integer partner) {
    _partner = partner;
  }

  public UsrSupplier gtPartner() {
    if (getPartner() == null) return null;
    return (UsrSupplier) get(UsrSupplier.class, getPartner());
  }

  public void stPartner(UsrSupplier partner) {
    if (partner == null) setPartner(null);
    else setPartner(partner.getPkey());
  }

  public Date getCreatedTime() {
    return _createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    _createdTime = createdTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
