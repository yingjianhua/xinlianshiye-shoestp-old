package irille.temporary.entity;

import irille.pub.bean.BeanLong;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class DataLogoData extends BeanLong<DataLogoData> {
  public static final Tb<?> TB = new Tb(DataLogoData.class, "订单管理").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtLongPkey()),
    ALL_BROWSE(SYS.INT, "总浏览量"),
    ALL_MONEY(SYS.INT, "累计成交金额"),
    ABILITY_VALUE(SYS.INT, "能量值"),
    TODAY_REGISTER(SYS.INT, "今日注册量"),
    YESTERDAY_REGISTER(SYS.INT, "较昨日注册量"),
    TODAY_BROWSE(SYS.INT, "今日浏览量"),
    YESTERDAY_BROWSE(SYS.INT, "较昨日浏览量"),
    TODAY_SUCCESS(SYS.INT, "今日成交金额"),
    YESTERDAY_ALL_MONEY(SYS.INT, "较昨日成交额"),
    BENEFICIARIES(SYS.INT, "受助者"),
    CAREGIVER(SYS.INT, "关爱者"),
    TRANSFER_RATE(SYS.INT, ""),
    TIME(SYS.STR__100_NULL, "时间标识"),
    CAREGIVER_TIMES(SYS.INT, "关爱者增长次数"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    public static final Index IDX_TIME = TB.addIndex("time", true, TIME);
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
  private Long _pkey; // 编号  LONG
  private Integer _allBrowse; // 总浏览量  INT
  private Integer _allMoney; // 累计成交金额  INT
  private Integer _abilityValue; // 能量值  INT
  private Integer _todayRegister; // 今日注册量  INT
  private Integer _yesterdayRegister; // 较昨日注册量  INT
  private Integer _todayBrowse; // 今日浏览量  INT
  private Integer _yesterdayBrowse; // 较昨日浏览量  INT
  private Integer _todaySuccess; // 今日成交金额  INT
  private Integer _yesterdayAllMoney; // 较昨日成交额  INT
  private Integer _beneficiaries; // 受助者  INT
  private Integer _caregiver; // 关爱者  INT
  private Integer _transferRate; //   INT
  private String _time; // 时间标识  STR(100)<null>
  private Integer _caregiverTimes; // 关爱者增长次数  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public DataLogoData init() {
    super.init();
    _allBrowse = 0; // 总浏览量  INT
    _allMoney = 0; // 累计成交金额  INT
    _abilityValue = 0; // 能量值  INT
    _todayRegister = 0; // 今日注册量  INT
    _yesterdayRegister = 0; // 较昨日注册量  INT
    _todayBrowse = 0; // 今日浏览量  INT
    _yesterdayBrowse = 0; // 较昨日浏览量  INT
    _todaySuccess = 0; // 今日成交金额  INT
    _yesterdayAllMoney = 0; // 较昨日成交额  INT
    _beneficiaries = 0; // 受助者  INT
    _caregiver = 0; // 关爱者  INT
    _transferRate = 0; //   INT
    _time = null; // 时间标识  STR(100)
    _caregiverTimes = 0; // 关爱者增长次数  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static DataLogoData loadUniqueTime(boolean lockFlag, String time) {
    return (DataLogoData) loadUnique(T.IDX_TIME, lockFlag, time);
  }

  public static DataLogoData chkUniqueTime(boolean lockFlag, String time) {
    return (DataLogoData) chkUnique(T.IDX_TIME, lockFlag, time);
  }

  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
  }

  public Integer getAllBrowse() {
    return _allBrowse;
  }

  public void setAllBrowse(Integer allBrowse) {
    _allBrowse = allBrowse;
  }

  public Integer getAllMoney() {
    return _allMoney;
  }

  public void setAllMoney(Integer allMoney) {
    _allMoney = allMoney;
  }

  public Integer getAbilityValue() {
    return _abilityValue;
  }

  public void setAbilityValue(Integer abilityValue) {
    _abilityValue = abilityValue;
  }

  public Integer getTodayRegister() {
    return _todayRegister;
  }

  public void setTodayRegister(Integer todayRegister) {
    _todayRegister = todayRegister;
  }

  public Integer getYesterdayRegister() {
    return _yesterdayRegister;
  }

  public void setYesterdayRegister(Integer yesterdayRegister) {
    _yesterdayRegister = yesterdayRegister;
  }

  public Integer getTodayBrowse() {
    return _todayBrowse;
  }

  public void setTodayBrowse(Integer todayBrowse) {
    _todayBrowse = todayBrowse;
  }

  public Integer getYesterdayBrowse() {
    return _yesterdayBrowse;
  }

  public void setYesterdayBrowse(Integer yesterdayBrowse) {
    _yesterdayBrowse = yesterdayBrowse;
  }

  public Integer getTodaySuccess() {
    return _todaySuccess;
  }

  public void setTodaySuccess(Integer todaySuccess) {
    _todaySuccess = todaySuccess;
  }

  public Integer getYesterdayAllMoney() {
    return _yesterdayAllMoney;
  }

  public void setYesterdayAllMoney(Integer yesterdayAllMoney) {
    _yesterdayAllMoney = yesterdayAllMoney;
  }

  public Integer getBeneficiaries() {
    return _beneficiaries;
  }

  public void setBeneficiaries(Integer beneficiaries) {
    _beneficiaries = beneficiaries;
  }

  public Integer getCaregiver() {
    return _caregiver;
  }

  public void setCaregiver(Integer caregiver) {
    _caregiver = caregiver;
  }

  public Integer getTransferRate() {
    return _transferRate;
  }

  public void setTransferRate(Integer transferRate) {
    _transferRate = transferRate;
  }

  public String getTime() {
    return _time;
  }

  public void setTime(String time) {
    _time = time;
  }

  public Integer getCaregiverTimes() {
    return _caregiverTimes;
  }

  public void setCaregiverTimes(Integer caregiverTimes) {
    _caregiverTimes = caregiverTimes;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
