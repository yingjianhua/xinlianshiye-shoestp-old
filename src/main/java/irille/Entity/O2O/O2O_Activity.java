package irille.Entity.O2O;

import java.util.Date;

import irille.Config.Attribute;
import irille.Config.Variable;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import org.json.JSONException;
import org.json.JSONObject;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 */
@Variable(
    group = {OTempType.O2O_ACTIVITY_NOTICE},
    enumType = O2O_Activity.T.class,
    clazz = O2O_Activity.class,
    attributes = {
      @Attribute(name = "活动名称", field = "NAME", type = String.class),
      @Attribute(name = "活动状态", field = "STATUS", type = O2O_ActivityStatus.class),
      @Attribute(name = "活动开始时间", field = "START_DATE", type = Date.class),
      @Attribute(name = "活动结束时间", field = "END_DATE", type = Date.class),
      @Attribute(name = "活动规则", field = "RULES", type = FldLanguage.class)
    })
public class O2O_Activity extends BeanInt<O2O_Activity> {

  private static final long serialVersionUID = 1L;

  public static final Tb<?> TB =
      new Tb<>(O2O_Activity.class, "O2O活动信息").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(Tb.crtIntPkey()),
    NAME(Sys.T.STR__100_NULL, "活动名称"),
    STATUS(Tb.crt(O2O_ActivityStatus.DEFAULT)),
    ACTIVITY_CAT(Sys.T.STR__200_NULL), // 当这个值为null的时候,及通用, 关联到productCat表的pkey字段 多选时用英文逗号分隔
    RULES(Sys.T.MUILTI_LANGUAGE, "规则描述"),
    ADDRESS(O2O_Map.fldOutKey()), // 地址
    START_DATE(Sys.T.DATE_TIME),
    END_DATE(Sys.T.DATE_TIME),
    UPDATED_TIME(Sys.T.UPDATED_DATE_TIME),
    ROW_VERSION(Sys.T.ROW_VERSION),
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

  public static Fld<?> fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld<?> fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private String _name; // 活动名称  STR(100)<null>
  private Byte _status; // 活动状态 <O2O_ActivityStatus>  BYTE
  // TOBEGIN:1,即将开始
  // ACTIVITY:2,活动中
  // END:3,活动结束
  private String _activityCat; // 字符200  STR(200)<null>
  private String _rules; // 规则描述  JSONOBJECT
  private Integer _address; // o2o地图 <表主键:O2O_Map>  INT
  private Date _startDate; // 日期时间  TIME
  private Date _endDate; // 日期时间  TIME
  private Date _updatedTime; // 更新时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public O2O_Activity init() {
    super.init();
    _name = null; // 活动名称  STR(100)
    _status = O2O_ActivityStatus.DEFAULT.getLine().getKey(); // 活动状态 <O2O_ActivityStatus>  BYTE
    _activityCat = null; // 字符200  STR(200)
    _rules = null; // 规则描述  JSONOBJECT
    _address = null; // o2o地图 <表主键:O2O_Map>  INT
    _startDate = Env.getTranBeginTime(); // 日期时间  TIME
    _endDate = Env.getTranBeginTime(); // 日期时间  TIME
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

  public Byte getStatus() {
    return _status;
  }

  public void setStatus(Byte status) {
    _status = status;
  }

  public O2O_ActivityStatus gtStatus() {
    return (O2O_ActivityStatus) (O2O_ActivityStatus.TOBEGIN.getLine().get(_status));
  }

  public void stStatus(O2O_ActivityStatus status) {
    _status = status.getLine().getKey();
  }

  public String getActivityCat() {
    return _activityCat;
  }

  public void setActivityCat(String activityCat) {
    _activityCat = activityCat;
  }

  public String getRules() {
    return _rules;
  }

  public void setRules(String rules) {
    _rules = rules;
  }

  public JSONObject gtRules() throws JSONException {
    return getRules() == null ? new JSONObject() : new JSONObject(getRules());
  }

  public void stRules(JSONObject rules) {
    setRules(rules == null ? null : rules.toString());
  }

  public String getRules(FldLanguage.Language l) throws JSONException {
    return gtRules().has(l.name()) ? gtRules().getString(l.name()) : "";
  }

  public void setRules(String rules, FldLanguage.Language l) throws JSONException {
    stRules(gtRules().put(l.name(), rules));
  }

  public Integer getAddress() {
    return _address;
  }

  public void setAddress(Integer address) {
    _address = address;
  }

  public O2O_Map gtAddress() {
    if (getAddress() == null) return null;
    return (O2O_Map) get(O2O_Map.class, getAddress());
  }

  public void stAddress(O2O_Map address) {
    if (address == null) setAddress(null);
    else setAddress(address.getPkey());
  }

  public Date getStartDate() {
    return _startDate;
  }

  public void setStartDate(Date startDate) {
    _startDate = startDate;
  }

  public Date getEndDate() {
    return _endDate;
  }

  public void setEndDate(Date endDate) {
    _endDate = endDate;
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
