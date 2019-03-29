package irille.shop.plt;

import java.math.BigDecimal;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class PltFreightLine extends BeanInt<PltFreightLine> {
  public static final Tb TB = new Tb(PltFreightLine.class, "配送区域").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()), // 区域主键
    MAIN(PltFreight.fldOutKey()), // 快递公司
    SECTION(SYS.STR__100, "区间"),
    BRIEF(SYS.STR__100_NULL, "简介"),
    FREE(SYS.NY, "免运费"),
    FREE_PRICE(SYS.PRICE, "免运费价格"),
    EXTRA_PRICE(SYS.PRICE, "附加费用"),
    WEIGHT_SECTION(SYS.PRICE, "首重区间"),
    AGGRAVATE_SECTION(SYS.PRICE, "续重区间"),
    WEIGHT_PRICE(SYS.PRICE, "首重价格"),
    AGGRAVATE_PRICE(SYS.PRICE, "加重价格"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    //	public static final Index IDX_NAME = TB.addIndex("name", true, NAME);
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
  private Integer _main;	// 运费管理 <表主键:PltFreight>  INT
  private String _section;	// 区间  STR(100)
  private String _brief;	// 简介  STR(100)<null>
  private Byte _free;	// 免运费 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private BigDecimal _freePrice;	// 免运费价格  DEC(14,4)
  private BigDecimal _extraPrice;	// 附加费用  DEC(14,4)
  private BigDecimal _weightSection;	// 首重区间  DEC(14,4)
  private BigDecimal _aggravateSection;	// 续重区间  DEC(14,4)
  private BigDecimal _weightPrice;	// 首重价格  DEC(14,4)
  private BigDecimal _aggravatePrice;	// 加重价格  DEC(14,4)
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltFreightLine init(){
		super.init();
    _main=null;	// 运费管理 <表主键:PltFreight>  INT
    _section=null;	// 区间  STR(100)
    _brief=null;	// 简介  STR(100)
    _free=OYn.DEFAULT.getLine().getKey();	// 免运费 <OYn>  BYTE
    _freePrice=ZERO;	// 免运费价格  DEC(14,4)
    _extraPrice=ZERO;	// 附加费用  DEC(14,4)
    _weightSection=ZERO;	// 首重区间  DEC(14,4)
    _aggravateSection=ZERO;	// 续重区间  DEC(14,4)
    _weightPrice=ZERO;	// 首重价格  DEC(14,4)
    _aggravatePrice=ZERO;	// 加重价格  DEC(14,4)
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
  public Integer getMain(){
    return _main;
  }
  public void setMain(Integer main){
    _main=main;
  }
  public PltFreight gtMain(){
    if(getMain()==null)
      return null;
    return (PltFreight)get(PltFreight.class,getMain());
  }
  public void stMain(PltFreight main){
    if(main==null)
      setMain(null);
    else
      setMain(main.getPkey());
  }
  public String getSection(){
    return _section;
  }
  public void setSection(String section){
    _section=section;
  }
  public String getBrief(){
    return _brief;
  }
  public void setBrief(String brief){
    _brief=brief;
  }
  public Byte getFree(){
    return _free;
  }
  public void setFree(Byte free){
    _free=free;
  }
  public Boolean gtFree(){
    return byteToBoolean(_free);
  }
  public void stFree(Boolean free){
    _free=booleanToByte(free);
  }
  public BigDecimal getFreePrice(){
    return _freePrice;
  }
  public void setFreePrice(BigDecimal freePrice){
    _freePrice=freePrice;
  }
  public BigDecimal getExtraPrice(){
    return _extraPrice;
  }
  public void setExtraPrice(BigDecimal extraPrice){
    _extraPrice=extraPrice;
  }
  public BigDecimal getWeightSection(){
    return _weightSection;
  }
  public void setWeightSection(BigDecimal weightSection){
    _weightSection=weightSection;
  }
  public BigDecimal getAggravateSection(){
    return _aggravateSection;
  }
  public void setAggravateSection(BigDecimal aggravateSection){
    _aggravateSection=aggravateSection;
  }
  public BigDecimal getWeightPrice(){
    return _weightPrice;
  }
  public void setWeightPrice(BigDecimal weightPrice){
    _weightPrice=weightPrice;
  }
  public BigDecimal getAggravatePrice(){
    return _aggravatePrice;
  }
  public void setAggravatePrice(BigDecimal aggravatePrice){
    _aggravatePrice=aggravatePrice;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
