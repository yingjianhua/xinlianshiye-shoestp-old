package irille.shop.plt;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.math.BigDecimal;
import java.util.Date;

public class PltErate  extends BeanInt<PltErate> implements IExtName{
	public static final Tb TB = new Tb(PltErate.class, "费率设置").setAutoIncrement().addActIUDL().addActEnabled().addActOpt("defcur", "网站默认货币").addActOpt("supdefcur", "商家默认货币");
	
	public  enum T implements  IEnumFld{//@formatter:off
	PKEY(TB.crtIntPkey()),
	LOGO(SYS.IMG__200_NULL,"LOGO"),
	CUR_NAME(SYS.STR__20,"货币简称"),
	SYMBOL(SYS.STR__20,"符号"),
	ENABLED(SYS.ENABLED),
	SITE_CUR(SYS.NY,"网站默认货币"),//TB.crt(Plt.OOpt.DEFAULT)
	RATE(SYS.RATE,"美元汇率"),
	SUP_CUR(SYS.NY,"商家默认货币"),//
	NOWRATE(SYS.RATE,"现用汇率"),
	CREATED_BY(SYS.CREATED_BY),
	CREATED_TIME(SYS.CREATED_DATE_TIME),
	ROW_VERSION(SYS.ROW_VERSION)
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
	@Override
	public String getExtName() {
		return getCurName();
	}
	//@formatter:on
	
	
	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _logo;	// LOGO  STR(200)<null>
  private String _curName;	// 货币简称  STR(20)
  private String _symbol;	// 符号  STR(20)
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Byte _siteCur;	// 网站默认货币 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private BigDecimal _rate;	// 美元汇率  DEC(8,4)
  private Byte _supCur;	// 商家默认货币 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private BigDecimal _nowrate;	// 现用汇率  DEC(8,4)
  private Integer _createdBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltErate init(){
		super.init();
    _logo=null;	// LOGO  STR(200)
    _curName=null;	// 货币简称  STR(20)
    _symbol=null;	// 符号  STR(20)
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _siteCur=OYn.DEFAULT.getLine().getKey();	// 网站默认货币 <OYn>  BYTE
    _rate=ZERO;	// 美元汇率  DEC(8,4)
    _supCur=OYn.DEFAULT.getLine().getKey();	// 商家默认货币 <OYn>  BYTE
    _nowrate=ZERO;	// 现用汇率  DEC(8,4)
    _createdBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getLogo(){
    return _logo;
  }
  public void setLogo(String logo){
    _logo=logo;
  }
  public String getCurName(){
    return _curName;
  }
  public void setCurName(String curName){
    _curName=curName;
  }
  public String getSymbol(){
    return _symbol;
  }
  public void setSymbol(String symbol){
    _symbol=symbol;
  }
  public Byte getEnabled(){
    return _enabled;
  }
  public void setEnabled(Byte enabled){
    _enabled=enabled;
  }
  public Boolean gtEnabled(){
    return byteToBoolean(_enabled);
  }
  public void stEnabled(Boolean enabled){
    _enabled=booleanToByte(enabled);
  }
  public Byte getSiteCur(){
    return _siteCur;
  }
  public void setSiteCur(Byte siteCur){
    _siteCur=siteCur;
  }
  public Boolean gtSiteCur(){
    return byteToBoolean(_siteCur);
  }
  public void stSiteCur(Boolean siteCur){
    _siteCur=booleanToByte(siteCur);
  }
  public BigDecimal getRate(){
    return _rate;
  }
  public void setRate(BigDecimal rate){
    _rate=rate;
  }
  public Byte getSupCur(){
    return _supCur;
  }
  public void setSupCur(Byte supCur){
    _supCur=supCur;
  }
  public Boolean gtSupCur(){
    return byteToBoolean(_supCur);
  }
  public void stSupCur(Boolean supCur){
    _supCur=booleanToByte(supCur);
  }
  public BigDecimal getNowrate(){
    return _nowrate;
  }
  public void setNowrate(BigDecimal nowrate){
    _nowrate=nowrate;
  }
  public Integer getCreatedBy(){
    return _createdBy;
  }
  public void setCreatedBy(Integer createdBy){
    _createdBy=createdBy;
  }
  public SysUser gtCreatedBy(){
    if(getCreatedBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreatedBy());
  }
  public void stCreatedBy(SysUser createdBy){
    if(createdBy==null)
      setCreatedBy(null);
    else
      setCreatedBy(createdBy.getPkey());
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
