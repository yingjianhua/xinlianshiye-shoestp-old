package irille.shop.odr;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.odr.Odr.OdrState;

public class OdrHistory extends BeanInt<OdrHistory> {
  public static final Tb TB = new Tb(OdrHistory.class, "订单历史").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    ODRORDER(OdrOrder.fldOutKey().setName("订单号")),
    OPERATOR(SYS.STR__100, "操作人"),
    TIME(SYS.DATE_TIME, "操作时间"), // 时间
    DESCRIP(SYS.DESCRIP__200_NULL, "订单描述"),
    STATE(TB.crt(Odr.OdrState.DEFAULT)), // 历史订单状态
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // public static final Index IDX_ORDERID = TB.addIndex("orderid", true, ORDERID);
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
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name).setType(null);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Long _odrorder; // 订单号 <表主键:OdrOrder>  LONG
  private String _operator; // 操作人  STR(100)
  private Date _time; // 操作时间  TIME
  private String _descrip; // 订单描述  STR(200)<null>
  private Byte _state; // 订单状态 <OdrState>  BYTE
  // WAIT:0,待付款
  // WAITCONFIRM:1,等待确认付款
  // ERROR:2,付款错误
  // WAITDELIVER:3,等待发货
  // DELIVER:4,已发货
  // COMPLETE:5,完成订单
  // CANCEL:6,已取消订单
  // DELETED:7,已删除
  private Short _rowVersion; // 版本  SHORT

  @Override
  public OdrHistory init() {
    super.init();
    _odrorder = null; // 订单号 <表主键:OdrOrder>  LONG
    _operator = null; // 操作人  STR(100)
    _time = Env.getTranBeginTime(); // 操作时间  TIME
    _descrip = null; // 订单描述  STR(200)
    _state = OdrState.DEFAULT.getLine().getKey(); // 订单状态 <OdrState>  BYTE
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

  public Long getOdrorder() {
    return _odrorder;
  }

  public void setOdrorder(Long odrorder) {
    _odrorder = odrorder;
  }

  public OdrOrder gtOdrorder() {
    if (getOdrorder() == null) return null;
    return (OdrOrder) get(OdrOrder.class, getOdrorder());
  }

  public void stOdrorder(OdrOrder odrorder) {
    if (odrorder == null) setOdrorder(null);
    else setOdrorder(odrorder.getPkey());
  }

  public String getOperator() {
    return _operator;
  }

  public void setOperator(String operator) {
    _operator = operator;
  }

  public Date getTime() {
    return _time;
  }

  public void setTime(Date time) {
    _time = time;
  }

  public String getDescrip() {
    return _descrip;
  }

  public void setDescrip(String descrip) {
    _descrip = descrip;
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

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
