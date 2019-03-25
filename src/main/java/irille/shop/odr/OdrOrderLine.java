package irille.shop.odr;

import java.math.BigDecimal;

import irille.pub.bean.BeanLong;
import irille.pub.tb.Fld;
import irille.pub.tb.FldDec;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtSpec;

public class OdrOrderLine extends BeanLong<OdrOrderLine> {
  public static final Tb TB = new Tb(OdrOrderLine.class, "订单详情").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtLongPkey()),
    SPEC(PdtSpec.fldOutKey().setName("产品")),
    QTY(SYS.INT, "数量"),
    SUBTOTAL(new FldDec("price", "小计", 14, 2).setNull()),
    // COUNTPRICE(SYS.PRICE_NULL,"小计"),
    MAIN(OdrOrder.fldOutKey().setName("订单")),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // public static final Index IDX_PKEY = TB.addIndex("pkey", true, PKEY);
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
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Long _pkey; // 编号  LONG
  private Integer _spec; // 产品 <表主键:PdtSpec>  INT
  private Integer _qty; // 数量  INT
  private BigDecimal _subtotal; // 小计  DEC(14,2)<null>
  private Long _main; // 订单 <表主键:OdrOrder>  LONG
  private Short _rowVersion; // 版本  SHORT

  @Override
  public OdrOrderLine init() {
    super.init();
    _spec = null; // 产品 <表主键:PdtSpec>  INT
    _qty = 0; // 数量  INT
    _subtotal = null; // 小计  DEC(14,2)
    _main = null; // 订单 <表主键:OdrOrder>  LONG
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
  }

  public Integer getSpec() {
    return _spec;
  }

  public void setSpec(Integer spec) {
    _spec = spec;
  }

  public PdtSpec gtSpec() {
    if (getSpec() == null) return null;
    return (PdtSpec) get(PdtSpec.class, getSpec());
  }

  public void stSpec(PdtSpec spec) {
    if (spec == null) setSpec(null);
    else setSpec(spec.getPkey());
  }

  public Integer getQty() {
    return _qty;
  }

  public void setQty(Integer qty) {
    _qty = qty;
  }

  public BigDecimal getSubtotal() {
    return _subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    _subtotal = subtotal;
  }

  public Long getMain() {
    return _main;
  }

  public void setMain(Long main) {
    _main = main;
  }

  public OdrOrder gtMain() {
    if (getMain() == null) return null;
    return (OdrOrder) get(OdrOrder.class, getMain());
  }

  public void stMain(OdrOrder main) {
    if (main == null) setMain(null);
    else setMain(main.getPkey());
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
