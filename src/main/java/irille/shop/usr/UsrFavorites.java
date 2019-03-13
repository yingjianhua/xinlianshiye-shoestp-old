package irille.shop.usr;

import java.util.Date;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.pdt.PdtProduct;

public class UsrFavorites extends BeanInt<UsrFavorites> {

  public static final Tb TB =
      new Tb(UsrFavorites.class, "收藏夹").setAutoIncrement().addActList().addActDel().addActIns();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    PURCHASE(UsrPurchase.fldOutKey()), // 采购商外键
    PRODUCT(PdtProduct.fldOutKey()), // 商品的pkey
    SHOW_STATE(SYS.YN, "显示状态"),
    ADD_TIME(SYS.DATE_TIME__NULL, "加入时间"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    public static final Index IDX_PURCHASE_PRODUCT =
        TB.addIndex("purchase_product", true, PURCHASE, PRODUCT);

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
  private Integer _purchase; // 采购商 <表主键:UsrPurchase>  INT
  private Integer _product; // 产品 <表主键:PdtProduct>  INT
  private Byte _showState; // 显示状态 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Date _addTime; // 加入时间  TIME<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrFavorites init() {
    super.init();
    _purchase = null; // 采购商 <表主键:UsrPurchase>  INT
    _product = null; // 产品 <表主键:PdtProduct>  INT
    _showState = OYn.DEFAULT.getLine().getKey(); // 显示状态 <OYn>  BYTE
    _addTime = null; // 加入时间  TIME
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static UsrFavorites loadUniquePurchase_product(
      boolean lockFlag, Integer purchase, Integer product) {
    return (UsrFavorites) loadUnique(T.IDX_PURCHASE_PRODUCT, lockFlag, purchase, product);
  }

  public static UsrFavorites chkUniquePurchase_product(
      boolean lockFlag, Integer purchase, Integer product) {
    return (UsrFavorites) chkUnique(T.IDX_PURCHASE_PRODUCT, lockFlag, purchase, product);
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

  public Byte getShowState() {
    return _showState;
  }

  public void setShowState(Byte showState) {
    _showState = showState;
  }

  public Boolean gtShowState() {
    return byteToBoolean(_showState);
  }

  public void stShowState(Boolean showState) {
    _showState = booleanToByte(showState);
  }

  public Date getAddTime() {
    return _addTime;
  }

  public void setAddTime(Date addTime) {
    _addTime = addTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
