package irille.shop.cnt;

import java.util.Date;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanLong;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class CntLink extends BeanLong<CntLink> {
  public static final Tb TB =
      new Tb(CntLink.class, "友情链接").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld {
    PKEY(TB.crtLongPkey()),
    ENABLED(SYS.ENABLED),
    NAME(SYS.NAME__100), // 启用
    IMAGE(SYS.IMG__200_NULL, "图片"),
    URL(SYS.STR__100_NULL, "链接地址"),
    UPDATED_BY(SYS.UPDATED_BY),
    UPDATED_TIME(SYS.UPDATED_DATE_TIME),
    SORT(SYS.INT, "排序"),
    CREATED_BY(SYS.CREATED_BY),
    CREATED_TIME(SYS.CREATED_DATE_TIME),
    ROW_VERSION(SYS.ROW_VERSION),

  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    public static final Index IDX_NAME = TB.addIndex("name", true, NAME);

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
  private Long _pkey; // 编号  LONG
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private String _name; // 名称  STR(100)
  private String _image; // 图片  STR(200)<null>
  private String _url; // 链接地址  STR(100)<null>
  private Integer _updatedBy; // 更新员 <表主键:SysUser>  INT
  private Date _updatedTime; // 更新时间  TIME
  private Integer _sort; // 排序  INT
  private Integer _createdBy; // 建档员 <表主键:SysUser>  INT
  private Date _createdTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public CntLink init() {
    super.init();
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _name = null; // 名称  STR(100)
    _image = null; // 图片  STR(200)
    _url = null; // 链接地址  STR(100)
    _updatedBy = null; // 更新员 <表主键:SysUser>  INT
    _updatedTime = Env.getTranBeginTime(); // 更新时间  TIME
    _sort = 0; // 排序  INT
    _createdBy = Idu.getUser().getPkey(); // 建档员 <表主键:SysUser>  INT
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static CntLink loadUniqueName(boolean lockFlag, String name) {
    return (CntLink) loadUnique(T.IDX_NAME, lockFlag, name);
  }

  public static CntLink chkUniqueName(boolean lockFlag, String name) {
    return (CntLink) chkUnique(T.IDX_NAME, lockFlag, name);
  }

  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
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

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public String getImage() {
    return _image;
  }

  public void setImage(String image) {
    _image = image;
  }

  public String getUrl() {
    return _url;
  }

  public void setUrl(String url) {
    _url = url;
  }

  public Integer getUpdatedBy() {
    return _updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    _updatedBy = updatedBy;
  }

  public SysUser gtUpdatedBy() {
    if (getUpdatedBy() == null) return null;
    return (SysUser) get(SysUser.class, getUpdatedBy());
  }

  public void stUpdatedBy(SysUser updatedBy) {
    if (updatedBy == null) setUpdatedBy(null);
    else setUpdatedBy(updatedBy.getPkey());
  }

  public Date getUpdatedTime() {
    return _updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    _updatedTime = updatedTime;
  }

  public Integer getSort() {
    return _sort;
  }

  public void setSort(Integer sort) {
    _sort = sort;
  }

  public Integer getCreatedBy() {
    return _createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    _createdBy = createdBy;
  }

  public SysUser gtCreatedBy() {
    if (getCreatedBy() == null) return null;
    return (SysUser) get(SysUser.class, getCreatedBy());
  }

  public void stCreatedBy(SysUser createdBy) {
    if (createdBy == null) setCreatedBy(null);
    else setCreatedBy(createdBy.getPkey());
  }

  public Date getCreatedTime() {
    return _createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    _createdTime = createdTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
