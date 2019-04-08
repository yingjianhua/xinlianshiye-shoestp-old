package irille.shop.pdt;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrPurchase;

public class PdtConsultPdtList extends BeanInt<PdtConsultPdtList> {
  public static final Tb TB =
      new Tb(PdtConsultPdtList.class, "询盘商品列表").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    PURCHASE(UsrPurchase.fldOutKey().setName("采购商")),
    PRODUCT(PdtProduct.fldOutKey().setName("产品")),
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

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _purchase; // 采购商 <表主键:UsrPurchase>  INT
  private Integer _product; // 产品 <表主键:PdtProduct>  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PdtConsultPdtList init() {
    super.init();
    _purchase = null; // 采购商 <表主键:UsrPurchase>  INT
    _product = null; // 产品 <表主键:PdtProduct>  INT
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

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
