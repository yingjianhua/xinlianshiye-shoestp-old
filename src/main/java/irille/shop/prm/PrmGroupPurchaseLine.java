package irille.shop.prm;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.pdt.PdtProduct;
import irille.shop.prm.Prm.OSend;

public class PrmGroupPurchaseLine extends BeanInt<PrmGroupPurchaseLine> {
  public static final Tb TB =
      new Tb(PrmGroupPurchaseLine.class, "联合采购商品").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),

    MAIN(PrmGroupPurchase.fldOutKey()), // 相关联合采购
    PRODUCT(PdtProduct.fldOutKey()), // 参与联合采购的产品
    COUNT(SYS.LONG, "数量"),
    BOUGHT_COUNT(SYS.LONG, "已订购数量"),
    STATE(TB.crt(Prm.OSend.DEFAULT)),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    public static final Index IDX_PRODUCT = TB.addIndex("product", true, PRODUCT);

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
  private Integer _main; // 联合采购 <表主键:PrmGroupPurchase>  INT
  private Integer _product; // 产品 <表主键:PdtProduct>  INT
  private Long _count; // 数量  LONG
  private Long _boughtCount; // 已订购数量  LONG
  private Byte _state; // 明细状态 <OSend>  BYTE
  // NOSEND:0,未发送
  // ALSEND:1,已发送
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PrmGroupPurchaseLine init() {
    super.init();
    _main = null; // 联合采购 <表主键:PrmGroupPurchase>  INT
    _product = null; // 产品 <表主键:PdtProduct>  INT
    _count = (long) 0; // 数量  LONG
    _boughtCount = (long) 0; // 已订购数量  LONG
    _state = OSend.DEFAULT.getLine().getKey(); // 明细状态 <OSend>  BYTE
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static PrmGroupPurchaseLine loadUniqueProduct(boolean lockFlag, Integer product) {
    return (PrmGroupPurchaseLine) loadUnique(T.IDX_PRODUCT, lockFlag, product);
  }

  public static PrmGroupPurchaseLine chkUniqueProduct(boolean lockFlag, Integer product) {
    return (PrmGroupPurchaseLine) chkUnique(T.IDX_PRODUCT, lockFlag, product);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Integer getMain() {
    return _main;
  }

  public void setMain(Integer main) {
    _main = main;
  }

  public PrmGroupPurchase gtMain() {
    if (getMain() == null) return null;
    return (PrmGroupPurchase) get(PrmGroupPurchase.class, getMain());
  }

  public void stMain(PrmGroupPurchase main) {
    if (main == null) setMain(null);
    else setMain(main.getPkey());
  }

  public Integer getProduct() {
    return _product;
  }

  public void setProduct(Integer product) {
    _product = product;
  }

  public PdtProduct gtProduct() {
    if (getProduct() == null) return null;
    return (PdtProduct) get(PdtProduct.class, getProduct());
  }

  public void stProduct(PdtProduct product) {
    if (product == null) setProduct(null);
    else setProduct(product.getPkey());
  }

  public Long getCount() {
    return _count;
  }

  public void setCount(Long count) {
    _count = count;
  }

  public Long getBoughtCount() {
    return _boughtCount;
  }

  public void setBoughtCount(Long boughtCount) {
    _boughtCount = boughtCount;
  }

  public Byte getState() {
    return _state;
  }

  public void setState(Byte state) {
    _state = state;
  }

  public OSend gtState() {
    return (OSend) (OSend.NOSEND.getLine().get(_state));
  }

  public void stState(OSend state) {
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
