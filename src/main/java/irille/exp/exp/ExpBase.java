package irille.exp.exp;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysUser;
import irille.exp.exp.Exp.OOpt;
import irille.pub.bean.BeanLong;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.util.Date;

/**
 * 单据关联关系表，包括源--目的，主--明细，关联单据在此定义
 * @author surface1
 */
public class ExpBase extends BeanLong<ExpBase> { // implements ICmbRpRpt{
	public static final Tb TB = new Tb(ExpBase.class, "测试菜单").setAutoIncrement().addActIUDL().addActEnabled().addActOpt("todo", "处理").addActOpt("undo", "撤销");

	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtLongPkey()),
		CODE(SYS.CODE__100),
		NAME(SYS.NAME__100),
		OPT(TB.crt(Exp.OOpt.DEFAULT)),//系统选项
		KIND(ExpKind.fldOutKey()),//外键
		ENABLED(SYS.ENABLED),
		IMG(SYS.IMG__200_NULL),
		REM(SYS.REM_TEXT__200_NULL),
		CREATE_BY(SYS.CREATED_BY),
		CREATE_TIME(SYS.CREATED_DATE_TIME),
		ROW_VERSION(SYS.ROW_VERSION),
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
  private Long _pkey;	// 编号  LONG
  private String _code;	// 代码  STR(100)
  private String _name;	// 名称  STR(100)
  private Byte _opt;	// 测试选项 <OOpt>  BYTE
	// ONE:0,选项一1
	// TWO:1,选项二
	// THREE:2,选项三
  private Integer _kind;	// 测试类型 <表主键:ExpKind>  INT
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private String _img;	// 图片  STR(200)<null>
  private String _rem;	// 备注  TEXT(200)<null>
  private Integer _createBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public ExpBase init(){
		super.init();
    _code=null;	// 代码  STR(100)
    _name=null;	// 名称  STR(100)
    _opt=OOpt.DEFAULT.getLine().getKey();	// 测试选项 <OOpt>  BYTE
    _kind=null;	// 测试类型 <表主键:ExpKind>  INT
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _img=null;	// 图片  STR(200)
    _rem=null;	// 备注  TEXT(200)
    _createBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createTime=Env.getTranBeginTime();	// 建档时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Long getPkey(){
    return _pkey;
  }
  public void setPkey(Long pkey){
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
  public Byte getOpt(){
    return _opt;
  }
  public void setOpt(Byte opt){
    _opt=opt;
  }
  public OOpt gtOpt(){
    return (OOpt)(OOpt.ONE.getLine().get(_opt));
  }
  public void stOpt(OOpt opt){
    _opt=opt.getLine().getKey();
  }
  public Integer getKind(){
    return _kind;
  }
  public void setKind(Integer kind){
    _kind=kind;
  }
  public ExpKind gtKind(){
    if(getKind()==null)
      return null;
    return (ExpKind)get(ExpKind.class,getKind());
  }
  public void stKind(ExpKind kind){
    if(kind==null)
      setKind(null);
    else
      setKind(kind.getPkey());
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
  public String getImg(){
    return _img;
  }
  public void setImg(String img){
    _img=img;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Integer getCreateBy(){
    return _createBy;
  }
  public void setCreateBy(Integer createBy){
    _createBy=createBy;
  }
  public SysUser gtCreateBy(){
    if(getCreateBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreateBy());
  }
  public void stCreateBy(SysUser createBy){
    if(createBy==null)
      setCreateBy(null);
    else
      setCreateBy(createBy.getPkey());
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
