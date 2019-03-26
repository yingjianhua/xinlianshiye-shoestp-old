package irille.shop.prm;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.prm.Prm.OPreTime;
import irille.shop.prm.Prm.OStatus;
import irille.shop.usr.UsrSupplier;

/**
 * 联合采购活动 send -> 用于将活动中产生的订单信息发送给相应供应商
 *
 * @author liyichao
 */
public class PrmGroupPurchase extends BeanInt<PrmGroupPurchase> implements IExtName {
  public static final Tb TB =
      new Tb(PrmGroupPurchase.class, "联合采购").setAutoIncrement().addActList();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    TITLE(SYS.STR__100_NULL, "活动标题"),
    START_TIME(SYS.DATE_TIME, "开始时间"),
    END_TIME(SYS.DATE_TIME, "结束时间"),
    STATUS(TB.crt(Prm.OStatus.DEFAULT)), // 活动状态
    PRE_TIME(TB.crt(Prm.OPreTime.DEFAULT)), // 提前预告时间
    CREATED_BY(UsrSupplier.fldOutKey()),
    CREATED_TIME(SYS.CREATED_DATE_TIME),
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

  @Override
  public String getExtName() {
    return getTitle();
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
  private String _title; // 活动标题  STR(100)<null>
  private Date _startTime; // 开始时间  TIME
  private Date _endTime; // 结束时间  TIME
  private Byte _status; // 活动状态 <OStatus>  BYTE
  // NOTSTART:0,未开始
  // SOONSTART:1,即将开始
  // HAVEINHAND:2,进行中
  // SOONEND:3,即将结束
  // END:4,已结束
  private Byte _preTime; // 提前预告 <OPreTime>  BYTE
  // THREEDAY:3,提前三天
  // SEVENDAY:7,提前七天
  // FIFTEENDAY:15,提前十五天
  // MONTH:30,提前一个月
  private Integer _createdBy; // 供应商 <表主键:UsrSupplier>  INT
  private Date _createdTime; // 建档时间  TIME
  private Date _updatedTime; // 更新时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PrmGroupPurchase init() {
    super.init();
    _title = null; // 活动标题  STR(100)
    _startTime = Env.getTranBeginTime(); // 开始时间  TIME
    _endTime = Env.getTranBeginTime(); // 结束时间  TIME
    _status = OStatus.DEFAULT.getLine().getKey(); // 活动状态 <OStatus>  BYTE
    _preTime = OPreTime.DEFAULT.getLine().getKey(); // 提前预告 <OPreTime>  BYTE
    _createdBy = null; // 供应商 <表主键:UsrSupplier>  INT
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
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

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public Date getStartTime() {
    return _startTime;
  }

  public void setStartTime(Date startTime) {
    _startTime = startTime;
  }

  public Date getEndTime() {
    return _endTime;
  }

  public void setEndTime(Date endTime) {
    _endTime = endTime;
  }

  public Byte getStatus() {
    return _status;
  }

  public void setStatus(Byte status) {
    _status = status;
  }

  public OStatus gtStatus() {
    return (OStatus) (OStatus.NOTSTART.getLine().get(_status));
  }

  public void stStatus(OStatus status) {
    _status = status.getLine().getKey();
  }

  public Byte getPreTime() {
    return _preTime;
  }

  public void setPreTime(Byte preTime) {
    _preTime = preTime;
  }

  public OPreTime gtPreTime() {
    return (OPreTime) (OPreTime.MONTH.getLine().get(_preTime));
  }

  public void stPreTime(OPreTime preTime) {
    _preTime = preTime.getLine().getKey();
  }

  public Integer getCreatedBy() {
    return _createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    _createdBy = createdBy;
  }

  public UsrSupplier gtCreatedBy() {
    if (getCreatedBy() == null) return null;
    return (UsrSupplier) get(UsrSupplier.class, getCreatedBy());
  }

  public void stCreatedBy(UsrSupplier createdBy) {
    if (createdBy == null) setCreatedBy(null);
    else setCreatedBy(createdBy.getPkey());
  }

  public Date getCreatedTime() {
    return _createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    _createdTime = createdTime;
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
