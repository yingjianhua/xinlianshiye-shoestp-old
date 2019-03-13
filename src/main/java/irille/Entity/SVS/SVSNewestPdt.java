package irille.Entity.SVS;

import java.util.Date;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;

public class SVSNewestPdt extends BeanInt<SVSNewestPdt> {
  private static final long serialVersionUID = 7524946206877858631L;
  public static final Tb<?> TB = new Tb<>(SVSNewestPdt.class, "SVS上新产品").setAutoIncrement();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(Tb.crtIntPkey()),
    PRODUCT_ID(PdtProduct.fldOutKey()), // 产品
    SUPPLIER_ID(UsrSupplier.fldOutKey()), // 供应商
    ADDED_TIME(SYS.DATE_TIME__NULL, "上架时间"), // 第一次上架时间
    ROW_VERSION(Sys.T.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;

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

  public static Fld<?> fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld<?> fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _productId; // 产品 <表主键:PdtProduct>  INT
  private Integer _supplierId; // 供应商 <表主键:UsrSupplier>  INT
  private Date _addedTime; // 上架时间  TIME<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public SVSNewestPdt init() {
    super.init();
    _productId = null; // 产品 <表主键:PdtProduct>  INT
    _supplierId = null; // 供应商 <表主键:UsrSupplier>  INT
    _addedTime = null; // 上架时间  TIME
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

  public Integer getProductId() {
    return _productId;
  }

  public void setProductId(Integer productId) {
    _productId = productId;
  }

  public PdtProduct gtProductId() {
    if (getProductId() == null) return null;
    return (PdtProduct) get(PdtProduct.class, getProductId());
  }

  public void stProductId(PdtProduct productId) {
    if (productId == null) setProductId(null);
    else setProductId(productId.getPkey());
  }

  public Integer getSupplierId() {
    return _supplierId;
  }

  public void setSupplierId(Integer supplierId) {
    _supplierId = supplierId;
  }

  public UsrSupplier gtSupplierId() {
    if (getSupplierId() == null) return null;
    return (UsrSupplier) get(UsrSupplier.class, getSupplierId());
  }

  public void stSupplierId(UsrSupplier supplierId) {
    if (supplierId == null) setSupplierId(null);
    else setSupplierId(supplierId.getPkey());
  }

  public Date getAddedTime() {
    return _addedTime;
  }

  public void setAddedTime(Date addedTime) {
    _addedTime = addedTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
