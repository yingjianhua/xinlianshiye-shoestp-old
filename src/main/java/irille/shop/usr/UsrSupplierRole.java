/**
 * 
 */
package irille.shop.usr;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

import java.util.Date;

public class UsrSupplierRole extends BeanInt<UsrSupplierRole> implements IExtName {
	private static final Log LOG = new Log(UsrSupplierRole.class);
	public static final Tb TB = new Tb(UsrSupplierRole.class, "供应商角色")
	.setAutoIncrement()
	.addActIUDL()
	.addActOpt("setDefault", "设为默认")
	.addActOpt("updCtrl", "权限控制");

	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),
		CODE(SYS.CODE__20),
		NAME(SYS.NAME__100),
		IS_DEFAULT(SYS.YN),
		ACTS(SYS.TEXT__20000_NULL, "拥有权限"),
		UPDATED_BY(SYS.UPDATED_BY),
		UPDATED_TIME(SYS.UPDATED_DATE_TIME),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_CODE = TB.addIndex("code", true,T.CODE);
		private Fld _fld;
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
	@Override
	public String getExtName() {
	  return getName();
	}
	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}
	//@formatter:on

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _code;	// 代码  STR(20)
  private String _name;	// 名称  STR(100)
  private Byte _isDefault;	// 是否 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _acts;	// 拥有权限  TEXT(200)<null>
  private Integer _updatedBy;	// 更新员 <表主键:SysUser>  INT
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrSupplierRole init(){
		super.init();
    _code=null;	// 代码  STR(20)
    _name=null;	// 名称  STR(100)
    _isDefault=OYn.DEFAULT.getLine().getKey();	// 是否 <OYn>  BYTE
    _acts=null;	// 拥有权限  TEXT(200)
    _updatedBy=null;	// 更新员 <表主键:SysUser>  INT
    _updatedTime=Env.getTranBeginTime();	// 更新时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static UsrSupplierRole loadUniqueCode(boolean lockFlag,String code) {
    return (UsrSupplierRole)loadUnique(T.IDX_CODE,lockFlag,code);
  }
  public static UsrSupplierRole chkUniqueCode(boolean lockFlag,String code) {
    return (UsrSupplierRole)chkUnique(T.IDX_CODE,lockFlag,code);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getCode(){
    return _code;
  }
  public void setCode(String code){
    _code=code;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Byte getIsDefault(){
    return _isDefault;
  }
  public void setIsDefault(Byte isDefault){
    _isDefault=isDefault;
  }
  public Boolean gtIsDefault(){
    return byteToBoolean(_isDefault);
  }
  public void stIsDefault(Boolean isDefault){
    _isDefault=booleanToByte(isDefault);
  }
  public String getActs(){
    return _acts;
  }
  public void setActs(String acts){
    _acts=acts;
  }
  public Integer getUpdatedBy(){
    return _updatedBy;
  }
  public void setUpdatedBy(Integer updatedBy){
    _updatedBy=updatedBy;
  }
  public SysUser gtUpdatedBy(){
    if(getUpdatedBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getUpdatedBy());
  }
  public void stUpdatedBy(SysUser updatedBy){
    if(updatedBy==null)
      setUpdatedBy(null);
    else
      setUpdatedBy(updatedBy.getPkey());
  }
  public Date getUpdatedTime(){
    return _updatedTime;
  }
  public void setUpdatedTime(Date updatedTime){
    _updatedTime=updatedTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
