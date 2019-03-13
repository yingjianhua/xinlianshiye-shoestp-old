package irille.temporary.entity;

import irille.pub.bean.BeanLong;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class DataData extends BeanLong<DataData> {
  public static final Tb<?> TB = new Tb(DataData.class, "鞋贸港基本信息").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtLongPkey()),
    ALL_MONEY(SYS.INT, "累计成交金额"),
    ALL_REGISTER(SYS.INT, "总注册量"),
    ALL_PRODUCTS(SYS.INT, "总商品数"),
    ALL_TRUE_MONEY(SYS.INT, "累计交易金额"),
    ALL_INQUIRY(SYS.INT, "总询盘量"),
    TODAY_REGISTER(SYS.INT, "今日注册量"),
    TODAY_BROWSE(SYS.INT, "今日浏览量"),
    TODAY_SUCCESS(SYS.INT, "今日成交金额"),
    CUSTOMER_UNIT_PRICE(SYS.INT, "客单价"),
    YESTERDAY_ALL_MONEY(SYS.INT, "较昨日成交额"),
    YESTERDAY_ALL_REGISTER(SYS.INT, "较昨日注册量"),
    YESTERDAY_BROWSE(SYS.INT, "较昨日浏览量"),
    PURCHASE(SYS.INT, "采购商数量"),
    SUPPLIER(SYS.INT, "供应商数量"),
    TIME(SYS.STR__100_NULL, "时间标识"),
    TODAY_MONEY_COUNT(SYS.INT, "今日交易金额限制次数"),
    ALL_MONEY_COUNT(SYS.INT, "总交易金额限制次数"),
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
  private Integer _allMoney; // 累计成交金额  INT
  private Integer _allRegister; // 总注册量  INT
  private Integer _allProducts; // 总商品数  INT
  private Integer _allTrueMoney; // 累计交易金额  INT
  private Integer _allInquiry; // 总询盘量  INT
  private Integer _todayRegister; // 今日注册量  INT
  private Integer _todayBrowse; // 今日浏览量  INT
  private Integer _todaySuccess; // 今日成交金额  INT
  private Integer _customerUnitPrice; // 客单价  INT
  private Integer _yesterdayAllMoney; // 较昨日成交额  INT
  private Integer _yesterdayAllRegister; // 较昨日注册量  INT
  private Integer _yesterdayBrowse; // 较昨日浏览量  INT
  private Integer _purchase; // 采购商数量  INT
  private Integer _supplier; // 供应商数量  INT
  private String _time; // 时间标识  STR(100)<null>
  private Integer _todayMoneyCount; // 今日交易金额限制次数  INT
  private Integer _allMoneyCount; // 总交易金额限制次数  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public DataData init() {
    super.init();
    _allMoney = 0; // 累计成交金额  INT
    _allRegister = 0; // 总注册量  INT
    _allProducts = 0; // 总商品数  INT
    _allTrueMoney = 0; // 累计交易金额  INT
    _allInquiry = 0; // 总询盘量  INT
    _todayRegister = 0; // 今日注册量  INT
    _todayBrowse = 0; // 今日浏览量  INT
    _todaySuccess = 0; // 今日成交金额  INT
    _customerUnitPrice = 0; // 客单价  INT
    _yesterdayAllMoney = 0; // 较昨日成交额  INT
    _yesterdayAllRegister = 0; // 较昨日注册量  INT
    _yesterdayBrowse = 0; // 较昨日浏览量  INT
    _purchase = 0; // 采购商数量  INT
    _supplier = 0; // 供应商数量  INT
    _time = null; // 时间标识  STR(100)
    _todayMoneyCount = 0; // 今日交易金额限制次数  INT
    _allMoneyCount = 0; // 总交易金额限制次数  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static DataData loadUniqueTime(boolean lockFlag, String time) {
    return (DataData) loadUnique(T.IDX_TIME, lockFlag, time);
  }

  public static DataData chkUniqueTime(boolean lockFlag, String time) {
    return (DataData) chkUnique(T.IDX_TIME, lockFlag, time);
  }

  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
  }

  public Integer getAllMoney() {
    return _allMoney;
  }

  public void setAllMoney(Integer allMoney) {
    _allMoney = allMoney;
  }

  public Integer getAllRegister() {
    return _allRegister;
  }

  public void setAllRegister(Integer allRegister) {
    _allRegister = allRegister;
  }

  public Integer getAllProducts() {
    return _allProducts;
  }

  public void setAllProducts(Integer allProducts) {
    _allProducts = allProducts;
  }

  public Integer getAllTrueMoney() {
    return _allTrueMoney;
  }

  public void setAllTrueMoney(Integer allTrueMoney) {
    _allTrueMoney = allTrueMoney;
  }

  public Integer getAllInquiry() {
    return _allInquiry;
  }

  public void setAllInquiry(Integer allInquiry) {
    _allInquiry = allInquiry;
  }

  public Integer getTodayRegister() {
    return _todayRegister;
  }

  public void setTodayRegister(Integer todayRegister) {
    _todayRegister = todayRegister;
  }

  public Integer getTodayBrowse() {
    return _todayBrowse;
  }

  public void setTodayBrowse(Integer todayBrowse) {
    _todayBrowse = todayBrowse;
  }

  public Integer getTodaySuccess() {
    return _todaySuccess;
  }

  public void setTodaySuccess(Integer todaySuccess) {
    _todaySuccess = todaySuccess;
  }

  public Integer getCustomerUnitPrice() {
    return _customerUnitPrice;
  }

  public void setCustomerUnitPrice(Integer customerUnitPrice) {
    _customerUnitPrice = customerUnitPrice;
  }

  public Integer getYesterdayAllMoney() {
    return _yesterdayAllMoney;
  }

  public void setYesterdayAllMoney(Integer yesterdayAllMoney) {
    _yesterdayAllMoney = yesterdayAllMoney;
  }

  public Integer getYesterdayAllRegister() {
    return _yesterdayAllRegister;
  }

  public void setYesterdayAllRegister(Integer yesterdayAllRegister) {
    _yesterdayAllRegister = yesterdayAllRegister;
  }

  public Integer getYesterdayBrowse() {
    return _yesterdayBrowse;
  }

  public void setYesterdayBrowse(Integer yesterdayBrowse) {
    _yesterdayBrowse = yesterdayBrowse;
  }

  public Integer getPurchase() {
    return _purchase;
  }

  public void setPurchase(Integer purchase) {
    _purchase = purchase;
  }

  public Integer getSupplier() {
    return _supplier;
  }

  public void setSupplier(Integer supplier) {
    _supplier = supplier;
  }

  public String getTime() {
    return _time;
  }

  public void setTime(String time) {
    _time = time;
  }

  public Integer getTodayMoneyCount() {
    return _todayMoneyCount;
  }

  public void setTodayMoneyCount(Integer todayMoneyCount) {
    _todayMoneyCount = todayMoneyCount;
  }

  public Integer getAllMoneyCount() {
    return _allMoneyCount;
  }

  public void setAllMoneyCount(Integer allMoneyCount) {
    _allMoneyCount = allMoneyCount;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
