package irille.shop.plt;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class PltCity  extends BeanInt<PltCity>{
 public static final Tb TB = new Tb(PltCity.class, "中国城市表").setAutoIncrement().addActIUDL();
	
	public enum T implements IEnumFld {
		PKEY(TB.crtIntPkey()),
		CODE(SYS.CODE__100_NULL,"区号"),
		NAME(SYS.STR__200_NULL,"名字"),
		PROVINCES(PltProvinces.fldOutKey().setName("省份")),
		ROW_VERSION(SYS.ROW_VERSION),
		
		
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_CODE = TB.addIndex("code", true,T.CODE);
		
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
  //@formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _code;	// 区号  STR(100)<null>
  private String _name;	// 名字  STR(200)<null>
  private Integer _provinces;	// 省份 <表主键:PltProvinces>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltCity init(){
		super.init();
    _code=null;	// 区号  STR(100)
    _name=null;	// 名字  STR(200)
    _provinces=null;	// 省份 <表主键:PltProvinces>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static PltCity loadUniqueCode(boolean lockFlag,String code) {
    return (PltCity)loadUnique(T.IDX_CODE,lockFlag,code);
  }
  public static PltCity chkUniqueCode(boolean lockFlag,String code) {
    return (PltCity)chkUnique(T.IDX_CODE,lockFlag,code);
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
  public Integer getProvinces(){
    return _provinces;
  }
  public void setProvinces(Integer provinces){
    _provinces=provinces;
  }
  public PltProvinces gtProvinces(){
    if(getProvinces()==null)
      return null;
    return (PltProvinces)get(PltProvinces.class,getProvinces());
  }
  public void stProvinces(PltProvinces provinces){
    if(provinces==null)
      setProvinces(null);
    else
      setProvinces(provinces.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
