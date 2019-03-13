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
import irille.shop.pdt.Pdt.OSizeType;
import irille.shop.pdt.Pdt.OVer;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 产品尺寸
 *
 * @author yingjianhua
 */
public class PdtSize extends BeanInt<PdtSize> implements IExtName {
  public static final Tb TB = new Tb(PdtSize.class, "产品尺寸").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.MUILTI_LANGUAGE, "名称"), // 名称 多国语言
    PRODUCT_CATEGORY(PdtCat.fldOutKey().setNull()), // 平台分类外键
    SUPPLIER(UsrSupplier.fldOutKey().setNull()),
    DELETED(SYS.NY, "是否删除"),
    CREATE_BY(SysUser.fldOutKey().setNull()),
    CREATE_TIME(SYS.CREATED_DATE_TIME),
    TYPE(Tb.crt(Pdt.OSizeType.EU).setNull()), // 美码 或者 欧码
    TYPEVER(Tb.crt(Pdt.OVer.ELSE)), // 版本 原颜色数据为0 新尺码数据为1
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
    String name = null;
    try {
      name = getName(PltConfigDAO.manageLanguage());
    } catch (JSONException e) {
    }
    return name;
  }

  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private String _name; // 名称  JSONOBJECT
  private Integer _productCategory; // 产品类目 <表主键:PdtCat>  INT<null>
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT<null>
  private Byte _deleted; // 是否删除 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Integer _createBy; // 用户 <表主键:SysUser>  INT<null>
  private Date _createTime; // 建档时间  TIME
  private Byte _type; // 类型 <OSizeType>  BYTE
  // USA:1,美码
  // EU:2,欧码
  private Byte _typever; // 类型 <OVer>  BYTE
  // ELSE:0,其他
  // NEW_1:1,新版本
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PdtSize init() {
    super.init();
    _name = null; // 名称  JSONOBJECT
    _productCategory = null; // 产品类目 <表主键:PdtCat>  INT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _deleted = OYn.DEFAULT.getLine().getKey(); // 是否删除 <OYn>  BYTE
    _createBy = null; // 用户 <表主键:SysUser>  INT
    _createTime = Env.getTranBeginTime(); // 建档时间  TIME
    _type = OSizeType.DEFAULT.getLine().getKey(); // 类型 <OSizeType>  BYTE
    _typever = OVer.DEFAULT.getLine().getKey(); // 类型 <OVer>  BYTE
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

  public Integer getProductCategory() {
    return _productCategory;
  }

  public void setProductCategory(Integer productCategory) {
    _productCategory = productCategory;
  }

  public PdtCat gtProductCategory() {
    if (getProductCategory() == null) return null;
    return (PdtCat) get(PdtCat.class, getProductCategory());
  }

  public void stProductCategory(PdtCat productCategory) {
    if (productCategory == null) setProductCategory(null);
    else setProductCategory(productCategory.getPkey());
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

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OSizeType gtType() {
    return (OSizeType) (OSizeType.EU.getLine().get(_type));
  }

  public void stType(OSizeType type) {
    _type = type.getLine().getKey();
  }

  public Byte getTypever() {
    return _typever;
  }

  public void setTypever(Byte typever) {
    _typever = typever;
  }

  public OVer gtTypever() {
    return (OVer) (OVer.ELSE.getLine().get(_typever));
  }

  public void stTypever(OVer typever) {
    _typever = typever.getLine().getKey();
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
