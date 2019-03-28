package irille.shop.pdt;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

/**
 * 产品分类属性分类关联表
 * 
 * @author chen
 *
 */
public class PdtAttrPro extends BeanInt<PdtAttrPro> {
	public static final Tb TB = new Tb(PdtAttrPro.class, "属性产品分类关联表").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld { // @formatter:off
		PKEY(TB.crtIntPkey()), ATTRCAT(PdtAttrCat.fldOutKey("category", "属性分类")),
		PROCAT(PdtCat.fldOutKey("category", "产品")), ROW_VERSION(SYS.ROW_VERSION),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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

	// @formatter:on

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _attrcat;	// 属性分类 <表主键:PdtAttrCat>  INT
  private Integer _procat;	// 产品 <表主键:PdtCat>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtAttrPro init(){
		super.init();
    _attrcat=null;	// 属性分类 <表主键:PdtAttrCat>  INT
    _procat=null;	// 产品 <表主键:PdtCat>  INT
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
  public Integer getAttrcat(){
    return _attrcat;
  }
  public void setAttrcat(Integer attrcat){
    _attrcat=attrcat;
  }
  public PdtAttrCat gtAttrcat(){
    if(getAttrcat()==null)
      return null;
    return (PdtAttrCat)get(PdtAttrCat.class,getAttrcat());
  }
  public void stAttrcat(PdtAttrCat attrcat){
    if(attrcat==null)
      setAttrcat(null);
    else
      setAttrcat(attrcat.getPkey());
  }
  public Integer getProcat(){
    return _procat;
  }
  public void setProcat(Integer procat){
    _procat=procat;
  }
  public PdtCat gtProcat(){
    if(getProcat()==null)
      return null;
    return (PdtCat)get(PdtCat.class,getProcat());
  }
  public void stProcat(PdtCat procat){
    if(procat==null)
      setProcat(null);
    else
      setProcat(procat.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
