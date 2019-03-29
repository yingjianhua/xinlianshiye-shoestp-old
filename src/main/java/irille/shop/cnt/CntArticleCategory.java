package irille.shop.cnt;

import java.util.Date;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import org.json.JSONException;
import org.json.JSONObject;

public class CntArticleCategory extends BeanInt<CntArticleCategory> { // implements ICmbRpRpt{
  public static final Tb TB =
      new Tb(CntArticleCategory.class, "文章类型").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.MUILTI_LANGUAGE, "名称"), // 多国语言
    ROOTID(CntArticleCategory.fldOutKey().setNull()),
    LANG(SYS.STR__10, "语言标识"), // TODO outkey
    ENABLED(SYS.ENABLED),
    CREATE_BY(SYS.CREATED_BY),
    CREATE_TIME(SYS.CREATED_DATE_TIME),
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
  private String _name; // 名称  JSONOBJECT
  private Integer _rootid; // 文章类型 <表主键:CntArticleCategory>  INT<null>
  private String _lang; // 语言标识  STR(10)
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private Integer _createBy; // 建档员 <表主键:SysUser>  INT
  private Date _createTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public CntArticleCategory init() {
    super.init();
    _name = null; // 名称  JSONOBJECT
    _rootid = null; // 文章类型 <表主键:CntArticleCategory>  INT
    _lang = null; // 语言标识  STR(10)
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _createBy = Idu.getUser().getPkey(); // 建档员 <表主键:SysUser>  INT
    _createTime = Env.getTranBeginTime(); // 建档时间  TIME
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

  public Integer getRootid() {
    return _rootid;
  }

  public void setRootid(Integer rootid) {
    _rootid = rootid;
  }

  public CntArticleCategory gtRootid() {
    if (getRootid() == null) return null;
    return (CntArticleCategory) get(CntArticleCategory.class, getRootid());
  }

  public void stRootid(CntArticleCategory rootid) {
    if (rootid == null) setRootid(null);
    else setRootid(rootid.getPkey());
  }

  public String getLang() {
    return _lang;
  }

  public void setLang(String lang) {
    _lang = lang;
  }

  public Byte getEnabled() {
    return _enabled;
  }

  public void setEnabled(Byte enabled) {
    _enabled = enabled;
  }

  public Boolean gtEnabled() {
    return byteToBoolean(_enabled);
  }

  public void stEnabled(Boolean enabled) {
    _enabled = booleanToByte(enabled);
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

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
