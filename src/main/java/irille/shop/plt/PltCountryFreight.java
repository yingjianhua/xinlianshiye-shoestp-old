package irille.shop.plt;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

import irille.shop.usr.UsrSupplier;

public class PltCountryFreight extends BeanInt<PltCountryFreight> {
	public static final Tb TB = new Tb(PltCountryFreight.class,"国家运费").setAutoIncrement().addActIUDL();
	public enum T implements IEnumFld{//@formatter:off
		PKEY(TB.crtIntPkey()),//主键
		SUPPLIER(UsrSupplier.fldOutKey()),//当前商家
		COUNTRY(PltCountry.fldOutKey()),//国家
		REGION(PltFreightSellerLine.fldOutKey()),//快递公司
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
  private Integer _pkey;	// 编号  INT
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _region;	// 商家配送区域 <表主键:PltFreightSellerLine>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltCountryFreight init(){
		super.init();
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _region=null;	// 商家配送区域 <表主键:PltFreightSellerLine>  INT
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
  public Integer getCountry(){
    return _country;
  }
  public void setCountry(Integer country){
    _country=country;
  }
  public PltCountry gtCountry(){
    if(getCountry()==null)
      return null;
    return (PltCountry)get(PltCountry.class,getCountry());
  }
  public void stCountry(PltCountry country){
    if(country==null)
      setCountry(null);
    else
      setCountry(country.getPkey());
  }
  public Integer getRegion(){
    return _region;
  }
  public void setRegion(Integer region){
    _region=region;
  }
  public PltFreightSellerLine gtRegion(){
    if(getRegion()==null)
      return null;
    return (PltFreightSellerLine)get(PltFreightSellerLine.class,getRegion());
  }
  public void stRegion(PltFreightSellerLine region){
    if(region==null)
      setRegion(null);
    else
      setRegion(region.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

			//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
	
}
