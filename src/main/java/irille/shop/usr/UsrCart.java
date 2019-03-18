package irille.shop.usr;

import java.math.BigDecimal;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.pdt.PdtSpec;

public class UsrCart extends BeanInt<UsrCart> {

  public static final Tb TB = new Tb(UsrCart.class, "购物车").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    PURCHASE(UsrPurchase.fldOutKey()), // 采购商外键
    SUPPLIER(UsrSupplier.fldOutKey()), // 供应商外键
    SPEC(PdtSpec.fldOutKey()), // 根据类型加入不同类型的商品,如join_type=0时加入普通商品的pkey,join_type=1时加入联合采购商品的pkey
    QTY(SYS.INT_PLUS_OR_ZERO, "商品数量"),
    AMT_TOTAL(SYS.AMT, "总价"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    public static final Index IDX_SPEC_PURCHASE =
        TB.addIndex("spec_purchase", true, SPEC, PURCHASE);

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

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _purchase; // 采购商 <表主键:UsrPurchase>  INT
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private Integer _spec; // 产品规格 <表主键:PdtSpec>  INT
  private Integer _qty; // 商品数量  INT
  private BigDecimal _amtTotal; // 总价  DEC(16,2)
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrCart init() {
    super.init();
    _purchase = null; // 采购商 <表主键:UsrPurchase>  INT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _spec = null; // 产品规格 <表主键:PdtSpec>  INT
    _qty = 0; // 商品数量  INT
    _amtTotal = ZERO; // 总价  DEC(16,2)
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static UsrCart loadUniqueSpec_purchase(boolean lockFlag, Integer spec, Integer purchase) {
    return (UsrCart) loadUnique(T.IDX_SPEC_PURCHASE, lockFlag, spec, purchase);
  }

  public static UsrCart chkUniqueSpec_purchase(boolean lockFlag, Integer spec, Integer purchase) {
    return (UsrCart) chkUnique(T.IDX_SPEC_PURCHASE, lockFlag, spec, purchase);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
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

  public BigDecimal getAmtTotal() {
    return _amtTotal;
  }

  public void setAmtTotal(BigDecimal amtTotal) {
    _amtTotal = amtTotal;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
