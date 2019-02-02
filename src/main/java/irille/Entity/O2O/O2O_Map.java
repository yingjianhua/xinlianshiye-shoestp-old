package irille.Entity.O2O;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import org.json.JSONException;
import org.json.JSONObject;

public class O2O_Map extends BeanInt<O2O_Map> {

	private static final long serialVersionUID = 1L;

	public static final Tb<?> TB = new Tb<>(O2O_Map.class,"o2o地图").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld{
        PKEY(Tb.crtIntPkey()),
        NAME(Sys.T.MUILTI_LANGUAGE,"地址"),
        LONGITUDE(Sys.T.STR__40_NULL,"经度"),
        LATITUDE(Sys.T.STR__40_NULL,"纬度"),
        IS_DELETE(Tb.crt(Sys.OYn.DEFAULT)),
        ROW_VERSION(Sys.T.ROW_VERSION),
        // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

        private Fld<?> _fld;

        private T(Class<?> clazz, String name, boolean... isnull) {
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

        private T(Fld<?> fld) {
            _fld = TB.add(fld, this);
        }

        public Fld<?> getFld() {
            return _fld;
        }
    }
    static { // 在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
    }

    public static Fld<?> fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld<?> fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }

    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 地址  JSONOBJECT
  private String _longitude;	// 经度  STR(40)<null>
  private String _latitude;	// 纬度  STR(40)<null>
  private Byte _isDelete;	// 是否 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public O2O_Map init(){
		super.init();
    _name=null;	// 地址  JSONOBJECT
    _longitude=null;	// 经度  STR(40)
    _latitude=null;	// 纬度  STR(40)
    _isDelete= Sys.OYn.DEFAULT.getLine().getKey();	// 是否 <OYn>  BYTE
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
  public JSONObject gtName() throws JSONException {
    return getName()==null?new JSONObject():new JSONObject(getName());
  }
  public void stName(JSONObject name){
    setName(name==null?null:name.toString());
  }
  public String getName(FldLanguage.Language l) throws JSONException {
    return gtName().has(l.name())?gtName().getString(l.name()):"";
  }
  public void setName(String name, FldLanguage.Language l) throws JSONException {
    stName(gtName().put(l.name(), name));
  }
  public String getLongitude(){
    return _longitude;
  }
  public void setLongitude(String longitude){
    _longitude=longitude;
  }
  public String getLatitude(){
    return _latitude;
  }
  public void setLatitude(String latitude){
    _latitude=latitude;
  }
  public Byte getIsDelete(){
    return _isDelete;
  }
  public void setIsDelete(Byte isDelete){
    _isDelete=isDelete;
  }
  public Boolean gtIsDelete(){
    return byteToBoolean(_isDelete);
  }
  public void stIsDelete(Boolean isDelete){
    _isDelete=booleanToByte(isDelete);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
