package irille.shop.usr;

import java.util.Date;

import irille.Config.Attribute;
import irille.Config.Variable;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys.OSex;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;

@Variable(group = {OTempType.INQUIRY_NOTICE_SUPPLIER,OTempType.PURCHASE_FORGET_PASSWORD},enumType=UsrPurchase.T.class,clazz=UsrPurchase.class,attributes = {
		@Attribute(name="采购商名称",field="NAME",type=String.class),
		@Attribute(name="采购商手机号",field="TELPHONE",type=String.class)}
)
public class UsrPurchase extends BeanInt<UsrPurchase> implements IExtName{
	
	public static final Tb TB = new Tb(UsrPurchase.class,"采购商")
								.setAutoIncrement()
								.addActList();
	
	public enum T implements IEnumFld{
		PKEY(TB.crtIntPkey()),
		/**
		 * 基本信息
		 */
		NAME(SYS.STR__100_NULL,"名字"),
		SURNAME(SYS.STR__100_NULL,"姓氏"),
		SEX(SYS.SEX,"性别"),
		ICON(SYS.IMG__200_NULL,"头像"),
		EMAIL(SYS.EMAIL__NULL,"邮箱"),
		REG_TIME(SYS.DATE_TIME__NULL,"注册时间"),
		LOGIN_NAME(SYS.STR__50,"登录账号"),
		PASSWORD(SYS.PASSWORD__NULL),
		CURRENCY(PltErate.fldOutKey("currency", "默认货币")), //弃用字段
		REG_IP(SYS.IP__NULL,"注册IP"),

		TELPHONE(SYS.PHOTO__NULL,"手机号码"),
		COMPANY(SYS.STR__100_NULL,"公司名称"),
		ADDRESS(SYS.STR__200_NULL,"地址"),
		LAST_LOGIN_TIME(SYS.DATE_TIME__NULL,"最后登录时间"),
		COUNTRY(PltCountry.fldOutKey().setName("国家").setNull()),
		FACEBOOK_USER_ID(SYS.STR__100_NULL,"FACEBOOK用户ID"),
		GOOGLE_USER_ID(SYS.STR__100_NULL,"谷歌用户ID"),
		UserId(UsrMain.fldOutKey().setName("用户").setNull()),
		/**
		 * 平台信息
		 */
		MEMBER_LEVEL(UsrMemberLevel.fldOutKey().setName("会员等级").setNull()),
		ROW_VERSION(SYS.ROW_VERSION),
		
		
		
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		
		// 索引
		public static final Index IDX_LOGIN_NAME = TB.addIndex("login_name", true,T.LOGIN_NAME);
		public static final Index IDX_FACEBOOK_USER_ID= TB.addIndex("facebook_user_id", true,T.FACEBOOK_USER_ID);
		public static final Index IDX_GOOGLE_USER_ID = TB.addIndex("google_user_id", true,T.GOOGLE_USER_ID);
		
		private Fld _fld;
        private T(Class clazz,String name,boolean... isnull)
        {_fld=TB.addOutKey(clazz,this,name,isnull);	}
        private T(IEnumFld fld,boolean... isnull) { this(fld,null,isnull); }
        private T(IEnumFld fld, String name,boolean... null1) {
            _fld=TB.add(fld,this,name,null1);}
        private T(IEnumFld fld, String name,int strLen) {
            _fld=TB.add(fld,this,name,strLen);}
        private T(Fld fld) {_fld=TB.add(fld,this); }
        public Fld getFld(){return _fld;}
		
	}
	
	static { //在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
	}
	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name).setType(null);
	}
	
	@Override
	public String getExtName() {
		return getName();
	}
	//@formatter:on

	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名字  STR(100)<null>
  private String _surname;	// 姓氏  STR(100)<null>
  private Byte _sex;	// 性别 <OSex>  BYTE
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
  private String _icon;	// 头像  STR(200)<null>
  private String _email;	// 邮箱  STR(100)<null>
  private Date _regTime;	// 注册时间  TIME<null>
  private String _loginName;	// 登录账号  STR(50)
  private String _password;	// 密码  STR(40)<null>
  private Integer _currency;	// 默认货币 <表主键:PltErate>  INT
  private String _regIp;	// 注册IP  STR(30)<null>
  private String _telphone;	// 手机号码  STR(200)<null>
  private String _company;	// 公司名称  STR(100)<null>
  private String _address;	// 地址  STR(200)<null>
  private Date _lastLoginTime;	// 最后登录时间  TIME<null>
  private Integer _country;	// 国家 <表主键:PltCountry>  INT<null>
  private String _facebookUserId;	// FACEBOOK用户ID  STR(100)<null>
  private String _googleUserId;	// 谷歌用户ID  STR(100)<null>
  private Integer _userid;	// 用户 <表主键:UsrMain>  INT<null>
  private Integer _memberLevel;	// 会员等级 <表主键:UsrMemberLevel>  INT<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrPurchase init(){
		super.init();
    _name=null;	// 名字  STR(100)
    _surname=null;	// 姓氏  STR(100)
    _sex=OSex.DEFAULT.getLine().getKey();	// 性别 <OSex>  BYTE
    _icon=null;	// 头像  STR(200)
    _email=null;	// 邮箱  STR(100)
    _regTime=null;	// 注册时间  TIME
    _loginName=null;	// 登录账号  STR(40)
    _password=null;	// 密码  STR(40)
    _currency=null;	// 默认货币 <表主键:PltErate>  INT
    _regIp=null;	// 注册IP  STR(30)
    _telphone=null;	// 手机号码  STR(200)
    _company=null;	// 公司名称  STR(100)
    _address=null;	// 地址  STR(200)
    _lastLoginTime=null;	// 最后登录时间  TIME
    _country=null;	// 国家 <表主键:PltCountry>  INT
    _facebookUserId=null;	// FACEBOOK用户ID  STR(100)
    _googleUserId=null;	// 谷歌用户ID  STR(100)
    _userid=null;	// 用户 <表主键:UsrMain>  INT
    _memberLevel=null;	// 会员等级 <表主键:UsrMemberLevel>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static UsrPurchase loadUniqueLogin_name(boolean lockFlag,String loginName) {
    return (UsrPurchase)loadUnique(T.IDX_LOGIN_NAME,lockFlag,loginName);
  }
  public static UsrPurchase chkUniqueLogin_name(boolean lockFlag,String loginName) {
    return (UsrPurchase)chkUnique(T.IDX_LOGIN_NAME,lockFlag,loginName);
  }
  public static UsrPurchase loadUniqueFacebook_user_id(boolean lockFlag,String facebookUserId) {
    return (UsrPurchase)loadUnique(T.IDX_FACEBOOK_USER_ID,lockFlag,facebookUserId);
  }
  public static UsrPurchase chkUniqueFacebook_user_id(boolean lockFlag,String facebookUserId) {
    return (UsrPurchase)chkUnique(T.IDX_FACEBOOK_USER_ID,lockFlag,facebookUserId);
  }
  public static UsrPurchase loadUniqueGoogle_user_id(boolean lockFlag,String googleUserId) {
    return (UsrPurchase)loadUnique(T.IDX_GOOGLE_USER_ID,lockFlag,googleUserId);
  }
  public static UsrPurchase chkUniqueGoogle_user_id(boolean lockFlag,String googleUserId) {
    return (UsrPurchase)chkUnique(T.IDX_GOOGLE_USER_ID,lockFlag,googleUserId);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getSurname(){
    return _surname;
  }
  public void setSurname(String surname){
    _surname=surname;
  }
  public Byte getSex(){
    return _sex;
  }
  public void setSex(Byte sex){
    _sex=sex;
  }
  public OSex gtSex(){
    return (OSex)(OSex.UNKNOW.getLine().get(_sex));
  }
  public void stSex(OSex sex){
    _sex=sex.getLine().getKey();
  }
  public String getIcon(){
    return _icon;
  }
  public void setIcon(String icon){
    _icon=icon;
  }
  public String getEmail(){
    return _email;
  }
  public void setEmail(String email){
    _email=email;
  }
  public Date getRegTime(){
    return _regTime;
  }
  public void setRegTime(Date regTime){
    _regTime=regTime;
  }
  public String getLoginName(){
    return _loginName;
  }
  public void setLoginName(String loginName){
    _loginName=loginName;
  }
  public String getPassword(){
    return _password;
  }
  public void setPassword(String password){
    _password=password;
  }
  public Integer getCurrency(){
    return _currency;
  }
  public void setCurrency(Integer currency){
    _currency=currency;
  }
  public PltErate gtCurrency(){
    if(getCurrency()==null)
      return null;
    return (PltErate)get(PltErate.class,getCurrency());
  }
  public void stCurrency(PltErate currency){
    if(currency==null)
      setCurrency(null);
    else
      setCurrency(currency.getPkey());
  }
  public String getRegIp(){
    return _regIp;
  }
  public void setRegIp(String regIp){
    _regIp=regIp;
  }
  public String getTelphone(){
    return _telphone;
  }
  public void setTelphone(String telphone){
    _telphone=telphone;
  }
  public String getCompany(){
    return _company;
  }
  public void setCompany(String company){
    _company=company;
  }
  public String getAddress(){
    return _address;
  }
  public void setAddress(String address){
    _address=address;
  }
  public Date getLastLoginTime(){
    return _lastLoginTime;
  }
  public void setLastLoginTime(Date lastLoginTime){
    _lastLoginTime=lastLoginTime;
  }
  public Integer getCountry(){
    return _country;
  }
  public void setCountry(Integer country){
    _country=country;
  }
  public PltCountry gtCountry(){
    if(getCountry()==null)
      return null;
    return (PltCountry)get(PltCountry.class,getCountry());
  }
  public void stCountry(PltCountry country){
    if(country==null)
      setCountry(null);
    else
      setCountry(country.getPkey());
  }
  public String getFacebookUserId(){
    return _facebookUserId;
  }
  public void setFacebookUserId(String facebookUserId){
    _facebookUserId=facebookUserId;
  }
  public String getGoogleUserId(){
    return _googleUserId;
  }
  public void setGoogleUserId(String googleUserId){
    _googleUserId=googleUserId;
  }
  public Integer getUserid(){
    return _userid;
  }
  public void setUserid(Integer userid){
    _userid=userid;
  }
  public UsrMain gtUserid(){
    if(getUserid()==null)
      return null;
    return (UsrMain)get(UsrMain.class,getUserid());
  }
  public void stUserid(UsrMain userid){
    if(userid==null)
      setUserid(null);
    else
      setUserid(userid.getPkey());
  }
  public Integer getMemberLevel(){
    return _memberLevel;
  }
  public void setMemberLevel(Integer memberLevel){
    _memberLevel=memberLevel;
  }
  public UsrMemberLevel gtMemberLevel(){
    if(getMemberLevel()==null)
      return null;
    return (UsrMemberLevel)get(UsrMemberLevel.class,getMemberLevel());
  }
  public void stMemberLevel(UsrMemberLevel memberLevel){
    if(memberLevel==null)
      setMemberLevel(null);
    else
      setMemberLevel(memberLevel.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
