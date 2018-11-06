package irille.shop.plt;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

import java.util.HashMap;
import java.util.Map;

public class PltConfig extends BeanInt<PltConfig> {
	public static final Tb TB = new Tb(PltConfig.class, "基础配置").setAutoIncrement().addActIUDL();
	
	public enum Variable {
		Language, //eg. en,zh 用,分隔的语言 表示已启用的语言
		LanguageDefault,//eg. en 表示网站默认的语言
		MangeLanguage, //eg. zh-cn 表示管理后台使用的语言
	}
	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),
		VARIABLE(SYS.STR__100, "变量名"),
		VALUE(SYS.TEXT__1000_NULL, "变量值"),
		//NAME(new FldLanguage<FldLanguage>("name", "名字")),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		public static final Index IDX_VARIABLE = TB.addIndex("variable", true, VARIABLE);
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
	private static Map<String, String> configMap = new HashMap<>();
	
	public static String getVariable(Variable v) {
		if(!configMap.containsKey(v.name())) {
			synchronized (PltConfig.class) {
				if(!configMap.containsKey(v.name())) {
					configMap.put(v.name(), PltConfig.chkUniqueVariable(false, v.name()).getValue());
				}
			}
		}
		return configMap.get(v.name());
	}
	
	public static synchronized void setVariable(String value, Variable v) {
		PltConfig config = PltConfig.chkUniqueVariable(false, v.name());
		if(config == null)  {
			config = new PltConfig().init();
			config.setVariable(v.name());
			config.setValue(value);
			config.ins();
		} else {
			config.setValue(value);
			config.upd();
		}
		configMap.remove(v.name());
	}
	//@formatter:on
	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _variable;	// 变量名  STR(100)
  private String _value;	// 变量值  TEXT(200)<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltConfig init(){
		super.init();
    _variable=null;	// 变量名  STR(100)
    _value=null;	// 变量值  TEXT(200)
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static PltConfig loadUniqueVariable(boolean lockFlag,String variable) {
    return (PltConfig)loadUnique(T.IDX_VARIABLE,lockFlag,variable);
  }
  public static PltConfig chkUniqueVariable(boolean lockFlag,String variable) {
    return (PltConfig)chkUnique(T.IDX_VARIABLE,lockFlag,variable);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getVariable(){
    return _variable;
  }
  public void setVariable(String variable){
    _variable=variable;
  }
  public String getValue(){
    return _value;
  }
  public void setValue(String value){
    _value=value;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
