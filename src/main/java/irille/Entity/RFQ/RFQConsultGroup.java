package irille.Entity.RFQ;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

public class RFQConsultGroup extends BeanInt<RFQConsultGroup> {
	
	private static final long serialVersionUID = 7424722974611181778L;
	
	public static final Tb<?> TB = new Tb<>(RFQConsultGroup.class, "询盘分组").setAutoIncrement();

    public enum T implements IEnumFld {// @formatter:off	
        PKEY(Tb.crtIntPkey()),
        NAME(Sys.T.STR__20_NULL), //
        SUPPLIER(UsrSupplier.fldOutKey().setName("供应商")),
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
        T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
    }

    public static Fld<?> fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld<?> fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name).setType(null);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 字符20  STR(20)<null>
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQConsultGroup init(){
		super.init();
    _name=null;	// 字符20  STR(20)
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
