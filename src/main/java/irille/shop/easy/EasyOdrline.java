package irille.shop.easy;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtSpec;
import org.json.JSONException;
import org.json.JSONObject;

public class EasyOdrline extends BeanInt<EasyOdrline> {
  public static final Tb<?> TB = new Tb(EasyOdrline.class, "简单订单管理详情").setAutoIncrement();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    ORDER_ID(EasyOdr.fldOutKey().setName("简单订单")),
    SPEC(PdtSpec.fldOutKey().setName("商品规格")),
    IAMGE(SYS.IMG_MULTI__1000, "商品图片"),
    PRODUCTNAME(SYS.MUILTI_LANGUAGE, "商品名称"),
    COLOR(SYS.MUILTI_LANGUAGE, "颜色"),
    SIZE(SYS.MUILTI_LANGUAGE, "尺码"),
    NUM(SYS.INT, "数量"),
    REMARKS(SYS.REM__200_NULL, "备注"),
    ROW_VERSION(SYS.ROW_VERSION),
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

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _orderId; // 简单订单 <表主键:EasyOdr>  INT
  private Integer _spec; // 商品规格 <表主键:PdtSpec>  INT
  private String _iamge; // 商品图片  STR(1000)
  private String _productname; // 商品名称  JSONOBJECT
  private String _color; // 颜色  JSONOBJECT
  private String _size; // 尺码  JSONOBJECT
  private Integer _num; // 数量  INT
  private String _remarks; // 备注  STR(200)<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public EasyOdrline init() {
    super.init();
    _orderId = null; // 简单订单 <表主键:EasyOdr>  INT
    _spec = null; // 商品规格 <表主键:PdtSpec>  INT
    _iamge = null; // 商品图片  STR(1000)
    _productname = null; // 商品名称  JSONOBJECT
    _color = null; // 颜色  JSONOBJECT
    _size = null; // 尺码  JSONOBJECT
    _num = 0; // 数量  INT
    _remarks = null; // 备注  STR(200)
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

  public Integer getOrderId() {
    return _orderId;
  }

  public void setOrderId(Integer orderId) {
    _orderId = orderId;
  }

  public EasyOdr gtOrderId() {
    if (getOrderId() == null) return null;
    return (EasyOdr) get(EasyOdr.class, getOrderId());
  }

  public void stOrderId(EasyOdr orderId) {
    if (orderId == null) setOrderId(null);
    else setOrderId(orderId.getPkey());
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

  public String getIamge() {
    return _iamge;
  }

  public void setIamge(String iamge) {
    _iamge = iamge;
  }

  public String getProductname() {
    return _productname;
  }

  public void setProductname(String productname) {
    _productname = productname;
  }

  public JSONObject gtProductname() throws JSONException {
    return getProductname() == null ? new JSONObject() : new JSONObject(getProductname());
  }

  public void stProductname(JSONObject productname) {
    setProductname(productname == null ? null : productname.toString());
  }

  public String getProductname(FldLanguage.Language l) throws JSONException {
    return gtProductname().has(l.name()) ? gtProductname().getString(l.name()) : "";
  }

  public void setProductname(String productname, FldLanguage.Language l) throws JSONException {
    stProductname(gtProductname().put(l.name(), productname));
  }

  public String getColor() {
    return _color;
  }

  public void setColor(String color) {
    _color = color;
  }

  public JSONObject gtColor() throws JSONException {
    return getColor() == null ? new JSONObject() : new JSONObject(getColor());
  }

  public void stColor(JSONObject color) {
    setColor(color == null ? null : color.toString());
  }

  public String getColor(FldLanguage.Language l) throws JSONException {
    return gtColor().has(l.name()) ? gtColor().getString(l.name()) : "";
  }

  public void setColor(String color, FldLanguage.Language l) throws JSONException {
    stColor(gtColor().put(l.name(), color));
  }

  public String getSize() {
    return _size;
  }

  public void setSize(String size) {
    _size = size;
  }

  public JSONObject gtSize() throws JSONException {
    return getSize() == null ? new JSONObject() : new JSONObject(getSize());
  }

  public void stSize(JSONObject size) {
    setSize(size == null ? null : size.toString());
  }

  public String getSize(FldLanguage.Language l) throws JSONException {
    return gtSize().has(l.name()) ? gtSize().getString(l.name()) : "";
  }

  public void setSize(String size, FldLanguage.Language l) throws JSONException {
    stSize(gtSize().put(l.name(), size));
  }

  public Integer getNum() {
    return _num;
  }

  public void setNum(Integer num) {
    _num = num;
  }

  public String getRemarks() {
    return _remarks;
  }

  public void setRemarks(String remarks) {
    _remarks = remarks;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
