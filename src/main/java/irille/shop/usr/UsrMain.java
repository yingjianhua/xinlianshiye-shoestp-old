package irille.shop.usr;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.plt.PltArea;
import irille.shop.plt.PltCity;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvinces;
import irille.shop.usr.Usr.IDType;

public class UsrMain extends BeanInt<UsrMain> {

  public static final Tb TB = new Tb(UsrMain.class, "用户登陆信息表").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    EMAIL(SYS.EMAIL__NULL, "邮箱"),
    NICKNAME(SYS.STR__200_NULL, "昵称"),
    PASSWORD(SYS.PASSWORD__NULL),
    COUNTRY(PltCountry.fldOutKey().setName("国家").setNull()),
    TELPHONE(SYS.PHOTO__NULL, "手机号码"),
    COMPANY(SYS.STR__100_NULL, "公司名称"),
    CONTACTS(SYS.NAME__40_NULL, "联系人"), // 联系人
    PROVINCE(PltProvinces.fldOutKey().setName("省份").setNull()),
    CITY(PltCity.fldOutKey().setName("城市").setNull()),
    ZONE(PltArea.fldOutKey().setName("地区").setNull()),
    ADDRESS(SYS.STR__200_NULL),
    REG_TIME(SYS.DATE_TIME__NULL, "注册时间"),
    IDENTITY(TB.crt(Usr.IDType.DEFAULT)),
    FACEBOOK_USER_ID(SYS.STR__100_NULL, "FACEBOOK用户ID"),
    GOOGLE_USER_ID(SYS.STR__100_NULL, "谷歌用户ID"),
    LINKEDIN_USER_ID(SYS.STR__100_NULL, "领英用户ID"),
    TWITTER_USER_ID(SYS.STR__100_NULL, "推特用户ID"),
    ROW_VERSION(SYS.ROW_VERSION),

  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_EMAIL = TB.addIndex("email", true, T.EMAIL);
    public static final Index IDX_FACEBOOK_USER_ID =
        TB.addIndex("facebook_user_id", true, T.FACEBOOK_USER_ID);
    public static final Index IDX_GOOGLE_USER_ID =
        TB.addIndex("google_user_id", true, T.GOOGLE_USER_ID);
    public static final Index IDX_LINKEDIN_USER_ID =
        TB.addIndex("linkedin_user_id", true, T.LINKEDIN_USER_ID);
    public static final Index IDX_TWITTER_USER_ID =
        TB.addIndex("twitter_user_id", true, T.TWITTER_USER_ID);

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
  private String _email; // 邮箱  STR(100)<null>
  private String _nickname; // 昵称  STR(200)<null>
  private String _password; // 密码  STR(40)<null>
  private Integer _country; // 国家 <表主键:PltCountry>  INT<null>
  private String _telphone; // 手机号码  STR(200)<null>
  private String _company; // 公司名称  STR(100)<null>
  private String _contacts; // 联系人  STR(40)<null>
  private Integer _province; // 省份 <表主键:PltProvinces>  INT<null>
  private Integer _city; // 城市 <表主键:PltCity>  INT<null>
  private Integer _zone; // 地区 <表主键:PltArea>  INT<null>
  private String _address; // 字符200  STR(200)<null>
  private Date _regTime; // 注册时间  TIME<null>
  private Byte _identity; // 身份类型 <IDType>  BYTE
  // BUYNER:0,买家
  // SELLER:1,卖家
  private String _facebookUserId; // FACEBOOK用户ID  STR(100)<null>
  private String _googleUserId; // 谷歌用户ID  STR(100)<null>
  private String _linkedinUserId; // 领英用户ID  STR(100)<null>
  private String _twitterUserId; // 推特用户ID  STR(100)<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrMain init() {
    super.init();
    _email = null; // 邮箱  STR(100)
    _nickname = null; // 昵称  STR(200)
    _password = null; // 密码  STR(40)
    _country = null; // 国家 <表主键:PltCountry>  INT
    _telphone = null; // 手机号码  STR(200)
    _company = null; // 公司名称  STR(100)
    _contacts = null; // 联系人  STR(40)
    _province = null; // 省份 <表主键:PltProvinces>  INT
    _city = null; // 城市 <表主键:PltCity>  INT
    _zone = null; // 地区 <表主键:PltArea>  INT
    _address = null; // 字符200  STR(200)
    _regTime = null; // 注册时间  TIME
    _identity = IDType.DEFAULT.getLine().getKey(); // 身份类型 <IDType>  BYTE
    _facebookUserId = null; // FACEBOOK用户ID  STR(100)
    _googleUserId = null; // 谷歌用户ID  STR(100)
    _linkedinUserId = null; // 领英用户ID  STR(100)
    _twitterUserId = null; // 推特用户ID  STR(100)
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static UsrMain loadUniqueEmail(boolean lockFlag, String email) {
    return (UsrMain) loadUnique(T.IDX_EMAIL, lockFlag, email);
  }

  public static UsrMain chkUniqueEmail(boolean lockFlag, String email) {
    return (UsrMain) chkUnique(T.IDX_EMAIL, lockFlag, email);
  }

  public static UsrMain loadUniqueFacebook_user_id(boolean lockFlag, String facebookUserId) {
    return (UsrMain) loadUnique(T.IDX_FACEBOOK_USER_ID, lockFlag, facebookUserId);
  }

  public static UsrMain chkUniqueFacebook_user_id(boolean lockFlag, String facebookUserId) {
    return (UsrMain) chkUnique(T.IDX_FACEBOOK_USER_ID, lockFlag, facebookUserId);
  }

  public static UsrMain loadUniqueGoogle_user_id(boolean lockFlag, String googleUserId) {
    return (UsrMain) loadUnique(T.IDX_GOOGLE_USER_ID, lockFlag, googleUserId);
  }

  public static UsrMain chkUniqueGoogle_user_id(boolean lockFlag, String googleUserId) {
    return (UsrMain) chkUnique(T.IDX_GOOGLE_USER_ID, lockFlag, googleUserId);
  }

  public static UsrMain loadUniqueLinkedin_user_id(boolean lockFlag, String linkedinUserId) {
    return (UsrMain) loadUnique(T.IDX_LINKEDIN_USER_ID, lockFlag, linkedinUserId);
  }

  public static UsrMain chkUniqueLinkedin_user_id(boolean lockFlag, String linkedinUserId) {
    return (UsrMain) chkUnique(T.IDX_LINKEDIN_USER_ID, lockFlag, linkedinUserId);
  }

  public static UsrMain loadUniqueTwitter_user_id(boolean lockFlag, String twitterUserId) {
    return (UsrMain) loadUnique(T.IDX_TWITTER_USER_ID, lockFlag, twitterUserId);
  }

  public static UsrMain chkUniqueTwitter_user_id(boolean lockFlag, String twitterUserId) {
    return (UsrMain) chkUnique(T.IDX_TWITTER_USER_ID, lockFlag, twitterUserId);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }

  public String getNickname() {
    return _nickname;
  }

  public void setNickname(String nickname) {
    _nickname = nickname;
  }

  public String getPassword() {
    return _password;
  }

  public void setPassword(String password) {
    _password = password;
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

  public String getTelphone() {
    return _telphone;
  }

  public void setTelphone(String telphone) {
    _telphone = telphone;
  }

  public String getCompany() {
    return _company;
  }

  public void setCompany(String company) {
    _company = company;
  }

  public String getContacts() {
    return _contacts;
  }

  public void setContacts(String contacts) {
    _contacts = contacts;
  }

  public Integer getProvince() {
    return _province;
  }

  public void setProvince(Integer province) {
    _province = province;
  }

  public PltProvinces gtProvince() {
    if (getProvince() == null) return null;
    return (PltProvinces) get(PltProvinces.class, getProvince());
  }

  public void stProvince(PltProvinces province) {
    if (province == null) setProvince(null);
    else setProvince(province.getPkey());
  }

  public Integer getCity() {
    return _city;
  }

  public void setCity(Integer city) {
    _city = city;
  }

  public PltCity gtCity() {
    if (getCity() == null) return null;
    return (PltCity) get(PltCity.class, getCity());
  }

  public void stCity(PltCity city) {
    if (city == null) setCity(null);
    else setCity(city.getPkey());
  }

  public Integer getZone() {
    return _zone;
  }

  public void setZone(Integer zone) {
    _zone = zone;
  }

  public PltArea gtZone() {
    if (getZone() == null) return null;
    return (PltArea) get(PltArea.class, getZone());
  }

  public void stZone(PltArea zone) {
    if (zone == null) setZone(null);
    else setZone(zone.getPkey());
  }

  public String getAddress() {
    return _address;
  }

  public void setAddress(String address) {
    _address = address;
  }

  public Date getRegTime() {
    return _regTime;
  }

  public void setRegTime(Date regTime) {
    _regTime = regTime;
  }

  public Byte getIdentity() {
    return _identity;
  }

  public void setIdentity(Byte identity) {
    _identity = identity;
  }

  public IDType gtIdentity() {
    return (IDType) (IDType.BUYNER.getLine().get(_identity));
  }

  public void stIdentity(IDType identity) {
    _identity = identity.getLine().getKey();
  }

  public String getFacebookUserId() {
    return _facebookUserId;
  }

  public void setFacebookUserId(String facebookUserId) {
    _facebookUserId = facebookUserId;
  }

  public String getGoogleUserId() {
    return _googleUserId;
  }

  public void setGoogleUserId(String googleUserId) {
    _googleUserId = googleUserId;
  }

  public String getLinkedinUserId() {
    return _linkedinUserId;
  }

  public void setLinkedinUserId(String linkedinUserId) {
    _linkedinUserId = linkedinUserId;
  }

  public String getTwitterUserId() {
    return _twitterUserId;
  }

  public void setTwitterUserId(String twitterUserId) {
    _twitterUserId = twitterUserId;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
