package irille.shop.cnt;

import java.util.Date;

import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class CntMagazine extends BeanInt<CntMagazine> {
  public static final Tb TB = new Tb(CntMagazine.class, "杂志管理").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.STR__100, "名称"),
    SPECIAL_PICTURES(SYS.IMG__400_NULL),
    TIME(SYS.TIME, "发布时间"),
    CYCLE(SYS.INT, "周期"),
    CONTENT(SYS.STR__200_NULL, "内容"),
    CONTENTURL(SYS.URL__NULL),
    CREATED_BY(SYS.CREATED_BY),
    CREATED_TIME(SYS.CREATED_DATE_TIME),
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
  private String _name; // 名称  STR(100)
  private String _specialPictures; // 图片  STR(400)<null>
  private Date _time; // 发布时间  TIME
  private Integer _cycle; // 周期  INT
  private String _content; // 内容  STR(200)<null>
  private String _contenturl; // URL  STR(200)<null>
  private Integer _createdBy; // 建档员 <表主键:SysUser>  INT
  private Date _createdTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public CntMagazine init() {
    super.init();
    _name = null; // 名称  STR(100)
    _specialPictures = null; // 图片  STR(400)
    _time = null; // 发布时间  TIME
    _cycle = 0; // 周期  INT
    _content = null; // 内容  STR(200)
    _contenturl = null; // URL  STR(200)
    _createdBy = Idu.getUser().getPkey(); // 建档员 <表主键:SysUser>  INT
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
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

  public String getSpecialPictures() {
    return _specialPictures;
  }

  public void setSpecialPictures(String specialPictures) {
    _specialPictures = specialPictures;
  }

  public Date getTime() {
    return _time;
  }

  public void setTime(Date time) {
    _time = time;
  }

  public Integer getCycle() {
    return _cycle;
  }

  public void setCycle(Integer cycle) {
    _cycle = cycle;
  }

  public String getContent() {
    return _content;
  }

  public void setContent(String content) {
    _content = content;
  }

  public String getContenturl() {
    return _contenturl;
  }

  public void setContenturl(String contenturl) {
    _contenturl = contenturl;
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
