package irille.shop.pdt;

import java.util.Date;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.Pdt.OVer;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 产品顏色
 *
 * @author yingjianhua
 */
public class PdtColor extends BeanInt<PdtColor> implements IExtName {
  public static final Tb TB = new Tb(PdtColor.class, "产品顏色").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.MUILTI_LANGUAGE), // 名称 多国语言
    SUPPLIER(UsrSupplier.fldOutKey().setNull()),
    DELETED(SYS.NY, "是否删除"),
    CREATE_BY(SysUser.fldOutKey().setNull()),
    CREATE_TIME(SYS.CREATED_DATE_TIME),
    PICTURE(SYS.STR__200_NULL), // 颜色图片
    TYPE(Tb.crt(Pdt.OVer.ELSE)), // 版本 原颜色数据为0 新颜色数据为1
    DEFAULT_COLOR(SYS.NY, "是否默认"), // 是否系统默认颜色
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

  @Override
  public String getExtName() {
    return getName();
  }

  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private String _name; // 语言  JSONOBJECT
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT<null>
  private Byte _deleted; // 是否删除 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Integer _createBy; // 用户 <表主键:SysUser>  INT<null>
  private Date _createTime; // 建档时间  TIME
  private String _picture; // 字符200  STR(200)<null>
  private Byte _type; // 类型 <OType>  BYTE
  // ELSE:0,其他
  // NEW_1:1,新版本
  private Byte _defaultColor; // 是否默认 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PdtColor init() {
    super.init();
    _name = null; // 语言  JSONOBJECT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _deleted = OYn.DEFAULT.getLine().getKey(); // 是否删除 <OYn>  BYTE
    _createBy = null; // 用户 <表主键:SysUser>  INT
    _createTime = Env.getTranBeginTime(); // 建档时间  TIME
    _picture = null; // 字符200  STR(200)
    _type = OVer.DEFAULT.getLine().getKey(); // 类型 <OType>  BYTE
    _defaultColor = OYn.DEFAULT.getLine().getKey(); // 是否默认 <OYn>  BYTE
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

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public JSONObject gtName() throws JSONException {
    return getName() == null ? new JSONObject() : new JSONObject(getName());
  }

  public void stName(JSONObject name) {
    setName(name == null ? null : name.toString());
  }

  public String getName(FldLanguage.Language l) throws JSONException {
    return gtName().has(l.name()) ? gtName().getString(l.name()) : "";
  }

  public void setName(String name, FldLanguage.Language l) throws JSONException {
    stName(gtName().put(l.name(), name));
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

  public Integer getCreateBy() {
    return _createBy;
  }

  public void setCreateBy(Integer createBy) {
    _createBy = createBy;
  }

  public SysUser gtCreateBy() {
    if (getCreateBy() == null) return null;
    return (SysUser) get(SysUser.class, getCreateBy());
  }

  public void stCreateBy(SysUser createBy) {
    if (createBy == null) setCreateBy(null);
    else setCreateBy(createBy.getPkey());
  }

  public Date getCreateTime() {
    return _createTime;
  }

  public void setCreateTime(Date createTime) {
    _createTime = createTime;
  }

  public String getPicture() {
    return _picture;
  }

  public void setPicture(String picture) {
    _picture = picture;
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OVer gtType() {
    return (OVer) (OVer.ELSE.getLine().get(_type));
  }

  public void stType(OVer type) {
    _type = type.getLine().getKey();
  }

  public Byte getDefaultColor() {
    return _defaultColor;
  }

  public void setDefaultColor(Byte defaultColor) {
    _defaultColor = defaultColor;
  }

  public Boolean gtDefaultColor() {
    return byteToBoolean(_defaultColor);
  }

  public void stDefaultColor(Boolean defaultColor) {
    _defaultColor = booleanToByte(defaultColor);
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
