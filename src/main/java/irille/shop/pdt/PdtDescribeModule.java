package irille.shop.pdt;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

public class PdtDescribeModule extends BeanInt<PdtDescribeModule> implements IExtName {

	public static final Tb TB = new Tb(PdtDescribeModule.class, "产品描述模块").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {
		PKEY(TB.crtIntPkey()), 
		SUPPLIER(UsrSupplier.fldOutKey().setNull()), //供应商
		NAME(SYS.STR__100, "模块名称"), 
		REMARK(SYS.STR__100_NULL, "模块备注"), 
		DESCRIPTION(SYS.MUILTI_LANGUAGE, "详细描述"), // 详细介绍 多国语言
		CREATE_TIME(SYS.CREATED_DATE_TIME),
		UPDATE_TIME(SYS.UPDATED_DATE_TIME),
		DELETED(SYS.NY,"是否删除"),
		DEFAULT_MODULE(SYS.NY,"是否默认"), //是否系统默认模块
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

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

	static { //在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
    }

    public static Fld fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }
	
	@Override
	public String getExtName() {
		// TODO Auto-generated method stub
		return null;
	}

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private String _name;	// 模块名称  STR(100)
  private String _remark;	// 模块备注  STR(100)<null>
  private String _description;	// 详细描述  JSONOBJECT
  private Date _createTime;	// 建档时间  TIME
  private Date _updateTime;	// 更新时间  TIME
  private Byte _deleted;	// 是否删除 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _defaultModule;	// 是否默认 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtDescribeModule init(){
		super.init();
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _name=null;	// 模块名称  STR(100)
    _remark=null;	// 模块备注  STR(100)
    _description=null;	// 详细描述  JSONOBJECT
    _createTime=Env.getTranBeginTime();	// 建档时间  TIME
    _updateTime=Env.getTranBeginTime();	// 更新时间  TIME
    _deleted=OYn.DEFAULT.getLine().getKey();	// 是否删除 <OYn>  BYTE
    _defaultModule=OYn.DEFAULT.getLine().getKey();	// 是否默认 <OYn>  BYTE
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
  public Integer getSupplier(){
    return _supplier;
  }
  public void setSupplier(Integer supplier){
    _supplier=supplier;
  }
  public UsrSupplier gtSupplier(){
    if(getSupplier()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplier());
  }
  public void stSupplier(UsrSupplier supplier){
    if(supplier==null)
      setSupplier(null);
    else
      setSupplier(supplier.getPkey());
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getRemark(){
    return _remark;
  }
  public void setRemark(String remark){
    _remark=remark;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public JSONObject gtDescription() throws JSONException {
    return getDescription()==null?new JSONObject():new JSONObject(getDescription());
  }
  public void stDescription(JSONObject description){
    setDescription(description==null?null:description.toString());
  }
  public String getDescription(FldLanguage.Language l) throws JSONException {
    return gtDescription().has(l.name())?gtDescription().getString(l.name()):"";
  }
  public void setDescription(String description, FldLanguage.Language l) throws JSONException {
    stDescription(gtDescription().put(l.name(), description));
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Date getUpdateTime(){
    return _updateTime;
  }
  public void setUpdateTime(Date updateTime){
    _updateTime=updateTime;
  }
  public Byte getDeleted(){
    return _deleted;
  }
  public void setDeleted(Byte deleted){
    _deleted=deleted;
  }
  public Boolean gtDeleted(){
    return byteToBoolean(_deleted);
  }
  public void stDeleted(Boolean deleted){
    _deleted=booleanToByte(deleted);
  }
  public Byte getDefaultModule(){
    return _defaultModule;
  }
  public void setDefaultModule(Byte defaultModule){
    _defaultModule=defaultModule;
  }
  public Boolean gtDefaultModule(){
    return byteToBoolean(_defaultModule);
  }
  public void stDefaultModule(Boolean defaultModule){
    _defaultModule=booleanToByte(defaultModule);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
	
}
