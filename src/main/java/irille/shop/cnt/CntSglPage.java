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

/**
 * 单据关联关系表，包括源--目的，主--明细，关联单据在此定义
 *
 * @author surface1
 */
public class CntSglPage extends BeanInt<CntSglPage> { // implements ICmbRpRpt{
  public static final Tb TB =
      new Tb(CntSglPage.class, "单页管理").setAutoIncrement().addActIUDL().addActEnabled();

  // 表结构定义
  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()), // 字段字段字段
    TITLE(SYS.MUILTI_LANGUAGE, "标题"), // 多国语言
    PAGE_TYPE(CntSglPageCategory.fldOutKey()),
    BRIEF(SYS.SUMMARY__100_NULL, "简述"),
    DESCRIP(SYS.MUILTI_LANGUAGE, "详细描述", true), // 多国语言
    ENABLED(SYS.ENABLED),
    SORT(SYS.SORT__SHORT),
    //        LANG(SYS.STR__8, "语言标识"),
    CREATE_BY(SYS.CREATED_BY),
    CREATE_TIME(SYS.CREATED_DATE_TIME),
    ROW_VERSION(SYS.ROW_VERSION),
    ;

    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<

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
  private String _title; // 标题  JSONOBJECT
  private Integer _pageType; // 单页类型 <表主键:CntSglPageCategory>  INT
  private String _brief; // 简述  STR(100)<null>
  private String _descrip; // 详细描述  JSONOBJECT
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private Short _sort; // 排序号  SHORT
  private Integer _createBy; // 建档员 <表主键:SysUser>  INT
  private Date _createTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public CntSglPage init() {
    super.init();
    _title = null; // 标题  JSONOBJECT
    _pageType = null; // 单页类型 <表主键:CntSglPageCategory>  INT
    _brief = null; // 简述  STR(100)
    _descrip = null; // 详细描述  JSONOBJECT
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _sort = 0; // 排序号  SHORT
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

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public JSONObject gtTitle() throws JSONException {
    return getTitle() == null ? new JSONObject() : new JSONObject(getTitle());
  }

  public void stTitle(JSONObject title) {
    setTitle(title == null ? null : title.toString());
  }

  public String getTitle(FldLanguage.Language l) throws JSONException {
    return gtTitle().has(l.name()) ? gtTitle().getString(l.name()) : "";
  }

  public void setTitle(String title, FldLanguage.Language l) throws JSONException {
    stTitle(gtTitle().put(l.name(), title));
  }

  public Integer getPageType() {
    return _pageType;
  }

  public void setPageType(Integer pageType) {
    _pageType = pageType;
  }

  public CntSglPageCategory gtPageType() {
    if (getPageType() == null) return null;
    return (CntSglPageCategory) get(CntSglPageCategory.class, getPageType());
  }

  public void stPageType(CntSglPageCategory pageType) {
    if (pageType == null) setPageType(null);
    else setPageType(pageType.getPkey());
  }

  public String getBrief() {
    return _brief;
  }

  public void setBrief(String brief) {
    _brief = brief;
  }

  public String getDescrip() {
    return _descrip;
  }

  public void setDescrip(String descrip) {
    _descrip = descrip;
  }

  public JSONObject gtDescrip() throws JSONException {
    return getDescrip() == null ? new JSONObject() : new JSONObject(getDescrip());
  }

  public void stDescrip(JSONObject descrip) {
    setDescrip(descrip == null ? null : descrip.toString());
  }

  public String getDescrip(FldLanguage.Language l) throws JSONException {
    return gtDescrip().has(l.name()) ? gtDescrip().getString(l.name()) : "";
  }

  public void setDescrip(String descrip, FldLanguage.Language l) throws JSONException {
    stDescrip(gtDescrip().put(l.name(), descrip));
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

  public Short getSort() {
    return _sort;
  }

  public void setSort(Short sort) {
    _sort = sort;
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
