package irille.Entity.O2O;

import java.util.Date;

import irille.Entity.O2O.Enums.O2O_BuyerType;
import irille.Entity.O2O.Enums.O2O_Sex;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;

public class O2oRegistration extends BeanInt<O2oRegistration> {

  public static final Tb TB =
      new Tb(O2oRegistration.class, "O2O展会活动报名表").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    FULL_NAME(SYS.STR__50, "报名人名称"),
    GENDER(TB.crt(O2O_Sex.DEFAULT)), // 性别
    COUNTRY(PltCountry.fldOutKey().setName("国家")),
    EMAIL(SYS.EMAIL__NULL, "邮箱"),
    TELPHONE(SYS.PHOTO__NULL, "手机号码"),
    FOOTWEAR(SYS.STR__100, "鞋分类"),
    ACTIVITY_ID(O2O_Activity.fldOutKey()), // 展会活动id
    MARKETING(SYS.STR__100, "主销售市场"), // 主要销售市场
    BUYER_TYPE(TB.crt(O2O_BuyerType.DEFAULT)), // 买家类型
    EXHIBITION_COUNTRY(SYS.STR__100, "展会国家"), // 展会国家
    CREATE_TIME(SYS.CREATED_DATE_TIME, "报名时间"),
    REMARKS(SYS.STR__500_NULL, "备注"),
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

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private String _fullName; // 报名人名称  STR(50)
  private Byte _gender; // 性别 <O2O_Sex>  BYTE
  // men:1,男
  // women:2,女
  private Integer _country; // 国家 <表主键:PltCountry>  INT
  private String _email; // 邮箱  STR(100)<null>
  private String _telphone; // 手机号码  STR(200)<null>
  private String _footwear; // 鞋分类  STR(100)
  private Integer _activityId; // O2O活动信息 <表主键:O2O_Activity>  INT
  private String _marketing; // 主销售市场  STR(100)
  private Byte _buyerType; // 买家类型 <O2O_BuyerType>  BYTE
  // dealer:1,经销商
  // distributor:2,分销商
  // wholesaler:3,批发商
  // other:4,其他
  private String _exhibitionCountry; // 展会国家  STR(100)
  private Date _createTime; // 报名时间  TIME
  private String _remarks; // 备注  STR(500)<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public O2oRegistration init() {
    super.init();
    _fullName = null; // 报名人名称  STR(50)
    _gender = O2O_Sex.DEFAULT.getLine().getKey(); // 性别 <O2O_Sex>  BYTE
    _country = null; // 国家 <表主键:PltCountry>  INT
    _email = null; // 邮箱  STR(100)
    _telphone = null; // 手机号码  STR(200)
    _footwear = null; // 鞋分类  STR(100)
    _activityId = null; // O2O活动信息 <表主键:O2O_Activity>  INT
    _marketing = null; // 主销售市场  STR(100)
    _buyerType = O2O_BuyerType.DEFAULT.getLine().getKey(); // 买家类型 <O2O_BuyerType>  BYTE
    _exhibitionCountry = null; // 展会国家  STR(100)
    _createTime = Env.getTranBeginTime(); // 报名时间  TIME
    _remarks = null; // 备注  STR(500)
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

  public String getFullName() {
    return _fullName;
  }

  public void setFullName(String fullName) {
    _fullName = fullName;
  }

  public Byte getGender() {
    return _gender;
  }

  public void setGender(Byte gender) {
    _gender = gender;
  }

  public O2O_Sex gtGender() {
    return (O2O_Sex) (O2O_Sex.men.getLine().get(_gender));
  }

  public void stGender(O2O_Sex gender) {
    _gender = gender.getLine().getKey();
  }

  public Integer getCountry() {
    return _country;
  }

  public void setCountry(Integer country) {
    _country = country;
  }

  public PltCountry gtCountry() {
    if (getCountry() == null) return null;
    return (PltCountry) get(PltCountry.class, getCountry());
  }

  public void stCountry(PltCountry country) {
    if (country == null) setCountry(null);
    else setCountry(country.getPkey());
  }

  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }

  public String getTelphone() {
    return _telphone;
  }

  public void setTelphone(String telphone) {
    _telphone = telphone;
  }

  public String getFootwear() {
    return _footwear;
  }

  public void setFootwear(String footwear) {
    _footwear = footwear;
  }

  public Integer getActivityId() {
    return _activityId;
  }

  public void setActivityId(Integer activityId) {
    _activityId = activityId;
  }

  public O2O_Activity gtActivityId() {
    if (getActivityId() == null) return null;
    return (O2O_Activity) get(O2O_Activity.class, getActivityId());
  }

  public void stActivityId(O2O_Activity activityId) {
    if (activityId == null) setActivityId(null);
    else setActivityId(activityId.getPkey());
  }

  public String getMarketing() {
    return _marketing;
  }

  public void setMarketing(String marketing) {
    _marketing = marketing;
  }

  public Byte getBuyerType() {
    return _buyerType;
  }

  public void setBuyerType(Byte buyerType) {
    _buyerType = buyerType;
  }

  public O2O_BuyerType gtBuyerType() {
    return (O2O_BuyerType) (O2O_BuyerType.other.getLine().get(_buyerType));
  }

  public void stBuyerType(O2O_BuyerType buyerType) {
    _buyerType = buyerType.getLine().getKey();
  }

  public String getExhibitionCountry() {
    return _exhibitionCountry;
  }

  public void setExhibitionCountry(String exhibitionCountry) {
    _exhibitionCountry = exhibitionCountry;
  }

  public Date getCreateTime() {
    return _createTime;
  }

  public void setCreateTime(Date createTime) {
    _createTime = createTime;
  }

  public String getRemarks() {
    return _remarks;
  }

  public void setRemarks(String remarks) {
    _remarks = remarks;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
