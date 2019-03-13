package irille.shop.usr;

import java.math.BigDecimal;
import java.util.Date;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class UsrMemberLevel extends BeanInt<UsrMemberLevel> implements IExtName {
  public static final Tb TB =
      new Tb(UsrMemberLevel.class, "会员等级").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.NAME__40, "名称"),
    ICON(SYS.IMG__200_NULL, "图片"),
    ENABLED(SYS.ENABLED),
    DISCOUNT(SYS.DR_AMT, "会员折扣"),
    LEVEL_UP(SYS.BALANCE, "升级条件"),
    CREATED_BY(SYS.CREATED_BY),
    CREATED_TIME(SYS.CREATED_DATE_TIME),
    UPDATED_BY(SYS.UPDATED_BY),
    UPDATED_TIME(SYS.UPDATED_DATE_TIME),
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
  private String _name; // 名称  STR(40)
  private String _icon; // 图片  STR(200)<null>
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private BigDecimal _discount; // 会员折扣  DEC(16,2)
  private BigDecimal _levelUp; // 升级条件  DEC(16,2)
  private Integer _createdBy; // 建档员 <表主键:SysUser>  INT
  private Date _createdTime; // 建档时间  TIME
  private Integer _updatedBy; // 更新员 <表主键:SysUser>  INT
  private Date _updatedTime; // 更新时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrMemberLevel init() {
    super.init();
    _name = null; // 名称  STR(40)
    _icon = null; // 图片  STR(200)
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _discount = ZERO; // 会员折扣  DEC(16,2)
    _levelUp = ZERO; // 升级条件  DEC(16,2)
    _createdBy = Idu.getUser().getPkey(); // 建档员 <表主键:SysUser>  INT
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
    _updatedBy = null; // 更新员 <表主键:SysUser>  INT
    _updatedTime = Env.getTranBeginTime(); // 更新时间  TIME
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

  public String getIcon() {
    return _icon;
  }

  public void setIcon(String icon) {
    _icon = icon;
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

  public BigDecimal getDiscount() {
    return _discount;
  }

  public void setDiscount(BigDecimal discount) {
    _discount = discount;
  }

  public BigDecimal getLevelUp() {
    return _levelUp;
  }

  public void setLevelUp(BigDecimal levelUp) {
    _levelUp = levelUp;
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

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
