package irille.shop.pdt;

import java.math.BigDecimal;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class PdtTieredPricing extends BeanInt<PdtTieredPricing> implements IExtName {

	public static final Tb TB = new Tb(PdtTieredPricing.class, "产品阶梯价").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {
		PKEY(TB.crtIntPkey()),
		PRODUCT(PdtProduct.fldOutKey()), //产品
		MIN_OQ(SYS.INT_PLUS_OR_ZERO, "起订量"),//起订量
		CUR_PRICE(SYS.AMT, "价格"),//商城价$
		MAIN(SYS.NY,"是否为主要阶梯价"),
		DELETED(SYS.NY,"是否删除"),
		ROW_VERSION(SYS.ROW_VERSION),
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
		T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	}

	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	@Override
	public String getExtName() {
		// TODO Auto-generated method stub
		return null;
	}

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _product;	// 产品 <表主键:PdtProduct>  INT
  private Integer _minOq;	// 起订量  INT
  private BigDecimal _curPrice;	// 价格  DEC(16,2)
  private Byte _main;	// 是否为主要阶梯价 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _deleted;	// 是否删除 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtTieredPricing init(){
		super.init();
    _product=null;	// 产品 <表主键:PdtProduct>  INT
    _minOq=0;	// 起订量  INT
    _curPrice=ZERO;	// 价格  DEC(16,2)
    _main=OYn.DEFAULT.getLine().getKey();	// 是否为主要阶梯价 <OYn>  BYTE
    _deleted=OYn.DEFAULT.getLine().getKey();	// 是否删除 <OYn>  BYTE
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
  public Integer getProduct(){
    return _product;
  }
  public void setProduct(Integer product){
    _product=product;
  }
  public PdtProduct gtProduct(){
    if(getProduct()==null)
      return null;
    return (PdtProduct)get(PdtProduct.class,getProduct());
  }
  public void stProduct(PdtProduct product){
    if(product==null)
      setProduct(null);
    else
      setProduct(product.getPkey());
  }
  public Integer getMinOq(){
    return _minOq;
  }
  public void setMinOq(Integer minOq){
    _minOq=minOq;
  }
  public BigDecimal getCurPrice(){
    return _curPrice;
  }
  public void setCurPrice(BigDecimal curPrice){
    _curPrice=curPrice;
  }
  public Byte getMain(){
    return _main;
  }
  public void setMain(Byte main){
    _main=main;
  }
  public Boolean gtMain(){
    return byteToBoolean(_main);
  }
  public void stMain(Boolean main){
    _main=booleanToByte(main);
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
