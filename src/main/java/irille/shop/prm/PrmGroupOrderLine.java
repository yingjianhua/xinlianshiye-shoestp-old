package irille.shop.prm;

import java.math.BigDecimal;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtSpec;

/**
 * 采购商订购产品生成的订单明细
 *
 * @author liyichao
 */
public class PrmGroupOrderLine extends BeanInt<PrmGroupOrderLine> {
  public static final Tb TB =
      new Tb(PrmGroupOrderLine.class, "订单明细").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    GROUP_ORDER(PrmGroupOrder.fldOutKey()), // 对应订单
    SPEC(PdtSpec.fldOutKey()), // 对应产品规格
    COUNT(SYS.INT, "订购数量"), // 该产品已售出的数量
    AMT(SYS.PRICE, "订单价格"), // 该产品规格生成的总价
    ROW_VERSION(SYS.ROW_VERSION),
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
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _groupOrder; // 团购订单 <表主键:PrmGroupOrder>  INT
  private Integer _spec; // 产品规格 <表主键:PdtSpec>  INT
  private Integer _count; // 订购数量  INT
  private BigDecimal _amt; // 订单价格  DEC(14,4)
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PrmGroupOrderLine init() {
    super.init();
    _groupOrder = null; // 团购订单 <表主键:PrmGroupOrder>  INT
    _spec = null; // 产品规格 <表主键:PdtSpec>  INT
    _count = 0; // 订购数量  INT
    _amt = ZERO; // 订单价格  DEC(14,4)
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

  public Integer getGroupOrder() {
    return _groupOrder;
  }

  public void setGroupOrder(Integer groupOrder) {
    _groupOrder = groupOrder;
  }

  public PrmGroupOrder gtGroupOrder() {
    if (getGroupOrder() == null) return null;
    return (PrmGroupOrder) get(PrmGroupOrder.class, getGroupOrder());
  }

  public void stGroupOrder(PrmGroupOrder groupOrder) {
    if (groupOrder == null) setGroupOrder(null);
    else setGroupOrder(groupOrder.getPkey());
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

  public Integer getCount() {
    return _count;
  }

  public void setCount(Integer count) {
    _count = count;
  }

  public BigDecimal getAmt() {
    return _amt;
  }

  public void setAmt(BigDecimal amt) {
    _amt = amt;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
