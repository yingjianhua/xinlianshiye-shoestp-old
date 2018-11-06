package irille.shop.usr;

import irille.core.sys.Sys.OYn;
import irille.gl.gl.GlNote;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr.OAddress;

public class UsrPurchaseLine extends BeanInt<UsrPurchaseLine>{
	public static final Tb TB = new Tb(UsrPurchaseLine.class,"地址簿").setAutoIncrement().addActIUDL();
	
	public enum T implements IEnumFld{
		PKEY(TB.crtIntPkey()),
		NAME(SYS.STR__100,"名字"),
		SURNAME(SYS.STR__100,"姓氏"),
		EMAILCODE(SYS.STR__100,"邮件编码"),
		COUNTRY(PltCountry.fldOutKey()),
		REGION(PltProvince.fldOutKey()),
		CITY(SYS.STR__100_NULL,"城市"),
		ADDRESS(SYS.ADDR__200_NULL,"具体地址"),
		PHONENUMBER(SYS.STR__100,"联系电话"),
		ISDEFAULT(SYS.YN,"默认地址"),
		PURCHASE(UsrPurchase.fldOutKey()),
		ROW_VERSION(SYS.ROW_VERSION),
		ADDRSSTYPE(TB.crt(Usr.OAddress.DEFAULT)),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
		return Tb.crtOutKey(TB, code, name);
	}
	
	//@formatter:on

	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名字  STR(100)
  private String _surname;	// 姓氏  STR(100)
  private String _emailcode;	// 邮件编码  STR(100)
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _region;	// 省份 <表主键:PltProvince>  INT
  private String _city;	// 城市  STR(100)<null>
  private String _address;	// 具体地址  STR(200)<null>
  private String _phonenumber;	// 联系电话  STR(100)
  private Byte _isdefault;	// 默认地址 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _purchase;	// 采购商 <表主键:UsrPurchase>  INT
  private Short _rowVersion;	// 版本  SHORT
  private Byte _addrsstype;	// 收货地址类型 <OAddress>  BYTE
	// COMMON:0,收货地址
	// BILLED:1,账单地址

	@Override
  public UsrPurchaseLine init(){
		super.init();
    _name=null;	// 名字  STR(100)
    _surname=null;	// 姓氏  STR(100)
    _emailcode=null;	// 邮件编码  STR(100)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _region=null;	// 省份 <表主键:PltProvince>  INT
    _city=null;	// 城市  STR(100)
    _address=null;	// 具体地址  STR(200)
    _phonenumber=null;	// 联系电话  STR(100)
    _isdefault=OYn.DEFAULT.getLine().getKey();	// 默认地址 <OYn>  BYTE
    _purchase=null;	// 采购商 <表主键:UsrPurchase>  INT
    _rowVersion=0;	// 版本  SHORT
    _addrsstype=OAddress.DEFAULT.getLine().getKey();	// 收货地址类型 <OAddress>  BYTE
    return this;
  }

  //方法----------------------------------------------
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
  public String getEmailcode(){
    return _emailcode;
  }
  public void setEmailcode(String emailcode){
    _emailcode=emailcode;
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
  public Integer getRegion(){
    return _region;
  }
  public void setRegion(Integer region){
    _region=region;
  }
  public PltProvince gtRegion(){
    if(getRegion()==null)
      return null;
    return (PltProvince)get(PltProvince.class,getRegion());
  }
  public void stRegion(PltProvince region){
    if(region==null)
      setRegion(null);
    else
      setRegion(region.getPkey());
  }
  public String getCity(){
    return _city;
  }
  public void setCity(String city){
    _city=city;
  }
  public String getAddress(){
    return _address;
  }
  public void setAddress(String address){
    _address=address;
  }
  public String getPhonenumber(){
    return _phonenumber;
  }
  public void setPhonenumber(String phonenumber){
    _phonenumber=phonenumber;
  }
  public Byte getIsdefault(){
    return _isdefault;
  }
  public void setIsdefault(Byte isdefault){
    _isdefault=isdefault;
  }
  public Boolean gtIsdefault(){
    return byteToBoolean(_isdefault);
  }
  public void stIsdefault(Boolean isdefault){
    _isdefault=booleanToByte(isdefault);
  }
  public Integer getPurchase(){
    return _purchase;
  }
  public void setPurchase(Integer purchase){
    _purchase=purchase;
  }
  public UsrPurchase gtPurchase(){
    if(getPurchase()==null)
      return null;
    return (UsrPurchase)get(UsrPurchase.class,getPurchase());
  }
  public void stPurchase(UsrPurchase purchase){
    if(purchase==null)
      setPurchase(null);
    else
      setPurchase(purchase.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }
  public Byte getAddrsstype(){
    return _addrsstype;
  }
  public void setAddrsstype(Byte addrsstype){
    _addrsstype=addrsstype;
  }
  public OAddress gtAddrsstype(){
    return (OAddress)(OAddress.COMMON.getLine().get(_addrsstype));
  }
  public void stAddrsstype(OAddress addrsstype){
    _addrsstype=addrsstype.getLine().getKey();
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
