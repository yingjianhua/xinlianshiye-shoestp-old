package irille.Entity.newInq;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;


public class NewInquiry extends BeanInt<NewInquiry> {
    public static final Tb TB = new Tb(NewInquiry.class, "新询盘").setAutoIncrement().addActList();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),
        SUPPLIERID(UsrSupplier.fldOutKey()),//供应商id
        NAME(SYS.STR__100,"名字"),
        EMAIL(SYS.STR__100,"邮箱"),
        DETAIL(SYS.STR__500,"详情"),;
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<

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
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }

    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supplierid;	// 供应商 <表主键:UsrSupplier>  INT
  private String _name;	// 名字  STR(100)
  private String _email;	// 邮箱  STR(100)
  private String _detail;	// 详情  STR(500)

	@Override
  public NewInquiry init(){
		super.init();
    _supplierid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _name=null;	// 名字  STR(100)
    _email=null;	// 邮箱  STR(100)
    _detail=null;	// 详情  STR(500)
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getSupplierid(){
    return _supplierid;
  }
  public void setSupplierid(Integer supplierid){
    _supplierid=supplierid;
  }
  public UsrSupplier gtSupplierid(){
    if(getSupplierid()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplierid());
  }
  public void stSupplierid(UsrSupplier supplierid){
    if(supplierid==null)
      setSupplierid(null);
    else
      setSupplierid(supplierid.getPkey());
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getEmail(){
    return _email;
  }
  public void setEmail(String email){
    _email=email;
  }
  public String getDetail(){
    return _detail;
  }
  public void setDetail(String detail){
    _detail=detail;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
    // @formatter:off
    }
