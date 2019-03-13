package irille.shop.pdt;

import java.math.BigDecimal;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 产品规格
 *
 * @author yingjianhua
 */
public class PdtSpec extends BeanInt<PdtSpec> implements IExtName {
  public static final Tb TB = new Tb(PdtSpec.class, "产品规格").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    PRODUCT(PdtProduct.fldOutKey()), // 产品
    COLOR(PdtColor.fldOutKey()), // 颜色属性
    SIZE(PdtSize.fldOutKey()), // 尺码属性
    KEY_NAME(SYS.MUILTI_LANGUAGE, "组合属性"), // 组合属性 颜色+尺码 多国语言
    SKU(SYS.CODE__100_NULL, "SKU（出厂编码）"), // SKU(出厂编码),不填写为系统默认编号
    PRICE(SYS.AMT, "价格"), // 价格
    MARKUP(SYS.AMT, "加价"), // 加价
    STORE_COUNT(SYS.INT_PLUS_OR_ZERO, "库存数量"), // 库存数量
    WEIGHT(SYS.PRICE), // 重量(千克)
    PICS(SYS.IMG_MULTI__1000_NULL, "产品颜色关联图片"), // 产品的颜色关联图片 ， 多图
    DELETED(SYS.NY, "是否删除"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    public static final Index IDX_PDT_COLOR_SIZE =
        TB.addIndex("pdt_color_size", true, T.PRODUCT, T.COLOR, T.SIZE);
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

  @Override
  public String getExtName() {
    return getKeyName();
  }

  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _product; // 产品 <表主键:PdtProduct>  INT
  private Integer _color; // 产品顏色 <表主键:PdtColor>  INT
  private Integer _size; // 产品尺寸 <表主键:PdtSize>  INT
  private String _keyName; // 组合属性  JSONOBJECT
  private String _sku; // SKU（出厂编码）  STR(100)<null>
  private BigDecimal _price; // 价格  DEC(16,2)
  private BigDecimal _markup; // 加价  DEC(16,2)
  private Integer _storeCount; // 库存数量  INT
  private BigDecimal _weight; // 单价  DEC(14,4)
  private String _pics; // 产品颜色关联图片  STR(1000)<null>
  private Byte _deleted; // 是否删除 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PdtSpec init() {
    super.init();
    _product = null; // 产品 <表主键:PdtProduct>  INT
    _color = null; // 产品顏色 <表主键:PdtColor>  INT
    _size = null; // 产品尺寸 <表主键:PdtSize>  INT
    _keyName = null; // 组合属性  JSONOBJECT
    _sku = null; // SKU（出厂编码）  STR(100)
    _price = ZERO; // 价格  DEC(16,2)
    _markup = ZERO; // 加价  DEC(16,2)
    _storeCount = 0; // 库存数量  INT
    _weight = ZERO; // 单价  DEC(14,4)
    _pics = null; // 产品颜色关联图片  STR(1000)
    _deleted = OYn.DEFAULT.getLine().getKey(); // 是否删除 <OYn>  BYTE
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static PdtSpec loadUniquePdt_color_size(
      boolean lockFlag, Integer product, Integer color, Integer size) {
    return (PdtSpec) loadUnique(T.IDX_PDT_COLOR_SIZE, lockFlag, product, color, size);
  }

  public static PdtSpec chkUniquePdt_color_size(
      boolean lockFlag, Integer product, Integer color, Integer size) {
    return (PdtSpec) chkUnique(T.IDX_PDT_COLOR_SIZE, lockFlag, product, color, size);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
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

  public Integer getColor() {
    return _color;
  }

  public void setColor(Integer color) {
    _color = color;
  }

  public PdtColor gtColor() {
    if (getColor() == null) return null;
    return (PdtColor) get(PdtColor.class, getColor());
  }

  public void stColor(PdtColor color) {
    if (color == null) setColor(null);
    else setColor(color.getPkey());
  }

  public Integer getSize() {
    return _size;
  }

  public void setSize(Integer size) {
    _size = size;
  }

  public PdtSize gtSize() {
    if (getSize() == null) return null;
    return (PdtSize) get(PdtSize.class, getSize());
  }

  public void stSize(PdtSize size) {
    if (size == null) setSize(null);
    else setSize(size.getPkey());
  }

  public String getKeyName() {
    return _keyName;
  }

  public void setKeyName(String keyName) {
    _keyName = keyName;
  }

  public JSONObject gtKeyName() throws JSONException {
    return getKeyName() == null ? new JSONObject() : new JSONObject(getKeyName());
  }

  public void stKeyName(JSONObject keyName) {
    setKeyName(keyName == null ? null : keyName.toString());
  }

  public String getKeyName(FldLanguage.Language l) throws JSONException {
    return gtKeyName().has(l.name()) ? gtKeyName().getString(l.name()) : "";
  }

  public void setKeyName(String keyName, FldLanguage.Language l) throws JSONException {
    stKeyName(gtKeyName().put(l.name(), keyName));
  }

  public String getSku() {
    return _sku;
  }

  public void setSku(String sku) {
    _sku = sku;
  }

  public BigDecimal getPrice() {
    return _price;
  }

  public void setPrice(BigDecimal price) {
    _price = price;
  }

  public BigDecimal getMarkup() {
    return _markup;
  }

  public void setMarkup(BigDecimal markup) {
    _markup = markup;
  }

  public Integer getStoreCount() {
    return _storeCount;
  }

  public void setStoreCount(Integer storeCount) {
    _storeCount = storeCount;
  }

  public BigDecimal getWeight() {
    return _weight;
  }

  public void setWeight(BigDecimal weight) {
    _weight = weight;
  }

  public String getPics() {
    return _pics;
  }

  public void setPics(String pics) {
    _pics = pics;
  }

  public Byte getDeleted() {
    return _deleted;
  }

  public void setDeleted(Byte deleted) {
    _deleted = deleted;
  }

  public Boolean gtDeleted() {
    return byteToBoolean(_deleted);
  }

  public void stDeleted(Boolean deleted) {
    _deleted = booleanToByte(deleted);
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
