package irille.shop.usr;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

/**
 * 供应商分类
 * @author yingjianhua
 */
public class UsrSupplierCategory extends BeanInt<UsrSupplierCategory> implements IExtName{
	public static final Tb TB = new Tb(UsrSupplierCategory.class, "供应商分类").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),
		NAME(SYS.NAME__100),
		SHOW_NAME(SYS.MUILTI_LANGUAGE, "网站显示名称"),//网站显示名称 多语言
		CATEGORY_UP(UsrSupplierCategory.fldOutKey().setName("上级分类").setNull()),
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
	
	@Override
	public String getExtName() {
		return getName();
	}
	
	@Override
	public String toString() {
		return "UsrSupplierCategory [_pkey=" + _pkey + ", _name=" + _name
				+ ", _showName=" + _showName + ", _categoryUp=" + _categoryUp
				+ ", _createBy=" + _createBy + ", _createTime=" + _createTime
				+ ", _rowVersion=" + _rowVersion + "]";
	}
	
	//@formatter:on


//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(100)
  private String _showName;	// 网站显示名称  JSONOBJECT
  private Integer _categoryUp;	// 上级分类 <表主键:UsrSupplierCategory>  INT<null>
  private Integer _createBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrSupplierCategory init(){
		super.init();
    _name=null;	// 名称  STR(100)
    _showName=null;	// 网站显示名称  JSONOBJECT
    _categoryUp=null;	// 上级分类 <表主键:UsrSupplierCategory>  INT
    _createBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createTime=Env.getTranBeginTime();	// 建档时间  TIME
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getShowName(){
    return _showName;
  }
  public void setShowName(String showName){
    _showName=showName;
  }
  public JSONObject gtShowName() throws JSONException {
    return getShowName()==null?new JSONObject():new JSONObject(getShowName());
  }
  public void stShowName(JSONObject showName){
    setShowName(showName==null?null:showName.toString());
  }
  public String getShowName(FldLanguage.Language l) throws JSONException {
    return gtShowName().has(l.name())?gtShowName().getString(l.name()):"";
  }
  public void setShowName(String showName, FldLanguage.Language l) throws JSONException {
    stShowName(gtShowName().put(l.name(), showName));
  }
  public Integer getCategoryUp(){
    return _categoryUp;
  }
  public void setCategoryUp(Integer categoryUp){
    _categoryUp=categoryUp;
  }
  public UsrSupplierCategory gtCategoryUp(){
    if(getCategoryUp()==null)
      return null;
    return (UsrSupplierCategory)get(UsrSupplierCategory.class,getCategoryUp());
  }
  public void stCategoryUp(UsrSupplierCategory categoryUp){
    if(categoryUp==null)
      setCategoryUp(null);
    else
      setCategoryUp(categoryUp.getPkey());
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
