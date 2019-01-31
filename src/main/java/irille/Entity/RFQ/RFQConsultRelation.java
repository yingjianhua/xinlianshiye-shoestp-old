package irille.Entity.RFQ;

import irille.Entity.RFQ.Enums.RFQConsultPayType;
import irille.Entity.RFQ.Enums.RFQConsultShipping_Type;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

import java.util.Date;

public class RFQConsultRelation extends BeanInt<RFQConsultRelation> {
    public static final Tb TB = new Tb(RFQConsultRelation.class, "询盘关联表")
            .setAutoIncrement();

    public enum T implements IEnumFld {// @formatter:off
        PKEY(TB.crtIntPkey()),
        CONSULT(RFQConsult.fldOutKey("consult", "询盘")), //
        SUPPLIER_ID(UsrSupplier.fldOutKey().setName("供应商")),
        PURCHASE_ID(UsrPurchase.fldOutKey().setName("采购商")),
        IN_RECYCLE_BIN(Sys.T.YN, "是否在回收站"),//是否在回收站, true: 在回收站, false: 不在回收站
        FAVORITE(SYS.YN, "是否添加FLAG"),
        TITLE(Sys.T.STR__20_NULL),
        DESTINATION(Sys.T.STR__20_NULL, "描述"),
        Image(Sys.T.STR__200_NULL, "图片(多图)"),
        QUANTITY(Sys.T.INT, "数量"),
        MINPRICE(Sys.T.INT, "价格区间"),
        MAXPRICE(Sys.T.INT, "价格区间"),
        CURRENCY(PltErate.fldOutKey()),
        VALID_DATE(Sys.T.DATE_TIME__NULL),
        PAYTYPE(Tb.crt(RFQConsultPayType.DEFAULT)),
        TRANSITTYPE(Tb.crt(RFQConsultShipping_Type.DEFAULT)),
        SAMPLE(Sys.T.YN),
        COMPANYDESCRIBE(Sys.T.STR__2000_NULL),
        THROWAWAY(Sys.T.STR__2000_NULL),
        CREATE_DATE(Sys.T.CREATED_DATE_TIME),
        HAD_READ(Sys.T.YN,"是否已读"),
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
        return Tb.crtOutKey(TB, code, name).setType(null);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _consult;	// 询盘 <表主键:RFQConsult>  INT
  private Integer _supplierId;	// 供应商 <表主键:UsrSupplier>  INT
  private Integer _purchaseId;	// 采购商 <表主键:UsrPurchase>  INT
  private Byte _inRecycleBin;	// 是否在回收站 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _favorite;	// 是否添加FLAG <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _title;	// 字符20  STR(20)<null>
  private String _destination;	// 描述  STR(20)<null>
  private String _image;	// 图片(多图)  STR(200)<null>
  private Integer _quantity;	// 数量  INT
  private Integer _minprice;	// 价格区间  INT
  private Integer _maxprice;	// 价格区间  INT
  private Integer _currency;	// 费率设置 <表主键:PltErate>  INT
  private Date _validDate;	// 日期时间  TIME<null>
  private Byte _paytype;	// 支付方式 <RFQConsultPayType>  BYTE
	// TT:1,TT支付
	// OFFLINE_PAY:2,线下支付
  private Byte _transittype;	// 配送方式 <RFQConsultShipping_Type>  BYTE
	// FOB:1,FOB
  private Byte _sample;	// 是否 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _companydescribe;	// 字符2000  STR(2000)<null>
  private String _throwaway;	// 字符2000  STR(2000)<null>
  private Date _createDate;	// 建档时间  TIME
  private Byte _hadRead;	// 是否已读 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQConsultRelation init(){
		super.init();
    _consult=null;	// 询盘 <表主键:RFQConsult>  INT
    _supplierId=null;	// 供应商 <表主键:UsrSupplier>  INT
    _purchaseId=null;	// 采购商 <表主键:UsrPurchase>  INT
    _inRecycleBin=OYn.DEFAULT.getLine().getKey();	// 是否在回收站 <OYn>  BYTE
    _favorite=OYn.DEFAULT.getLine().getKey();	// 是否添加FLAG <OYn>  BYTE
    _title=null;	// 字符20  STR(20)
    _destination=null;	// 描述  STR(20)
    _image=null;	// 图片(多图)  STR(200)
    _quantity=0;	// 数量  INT
    _minprice=0;	// 价格区间  INT
    _maxprice=0;	// 价格区间  INT
    _currency=null;	// 费率设置 <表主键:PltErate>  INT
    _validDate=null;	// 日期时间  TIME
    _paytype=RFQConsultPayType.DEFAULT.getLine().getKey();	// 支付方式 <RFQConsultPayType>  BYTE
    _transittype=RFQConsultShipping_Type.DEFAULT.getLine().getKey();	// 配送方式 <RFQConsultShipping_Type>  BYTE
    _sample=OYn.DEFAULT.getLine().getKey();	// 是否 <OYn>  BYTE
    _companydescribe=null;	// 字符2000  STR(2000)
    _throwaway=null;	// 字符2000  STR(2000)
    _createDate=Env.getTranBeginTime();	// 建档时间  TIME
    _hadRead=OYn.DEFAULT.getLine().getKey();	// 是否已读 <OYn>  BYTE
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
  public Integer getConsult(){
    return _consult;
  }
  public void setConsult(Integer consult){
    _consult=consult;
  }
  public RFQConsult gtConsult(){
    if(getConsult()==null)
      return null;
    return (RFQConsult)get(RFQConsult.class,getConsult());
  }
  public void stConsult(RFQConsult consult){
    if(consult==null)
      setConsult(null);
    else
      setConsult(consult.getPkey());
  }
  public Integer getSupplierId(){
    return _supplierId;
  }
  public void setSupplierId(Integer supplierId){
    _supplierId=supplierId;
  }
  public UsrSupplier gtSupplierId(){
    if(getSupplierId()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplierId());
  }
  public void stSupplierId(UsrSupplier supplierId){
    if(supplierId==null)
      setSupplierId(null);
    else
      setSupplierId(supplierId.getPkey());
  }
  public Integer getPurchaseId(){
    return _purchaseId;
  }
  public void setPurchaseId(Integer purchaseId){
    _purchaseId=purchaseId;
  }
  public UsrPurchase gtPurchaseId(){
    if(getPurchaseId()==null)
      return null;
    return (UsrPurchase)get(UsrPurchase.class,getPurchaseId());
  }
  public void stPurchaseId(UsrPurchase purchaseId){
    if(purchaseId==null)
      setPurchaseId(null);
    else
      setPurchaseId(purchaseId.getPkey());
  }
  public Byte getInRecycleBin(){
    return _inRecycleBin;
  }
  public void setInRecycleBin(Byte inRecycleBin){
    _inRecycleBin=inRecycleBin;
  }
  public Boolean gtInRecycleBin(){
    return byteToBoolean(_inRecycleBin);
  }
  public void stInRecycleBin(Boolean inRecycleBin){
    _inRecycleBin=booleanToByte(inRecycleBin);
  }
  public Byte getFavorite(){
    return _favorite;
  }
  public void setFavorite(Byte favorite){
    _favorite=favorite;
  }
  public Boolean gtFavorite(){
    return byteToBoolean(_favorite);
  }
  public void stFavorite(Boolean favorite){
    _favorite=booleanToByte(favorite);
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getDestination(){
    return _destination;
  }
  public void setDestination(String destination){
    _destination=destination;
  }
  public String getImage(){
    return _image;
  }
  public void setImage(String image){
    _image=image;
  }
  public Integer getQuantity(){
    return _quantity;
  }
  public void setQuantity(Integer quantity){
    _quantity=quantity;
  }
  public Integer getMinprice(){
    return _minprice;
  }
  public void setMinprice(Integer minprice){
    _minprice=minprice;
  }
  public Integer getMaxprice(){
    return _maxprice;
  }
  public void setMaxprice(Integer maxprice){
    _maxprice=maxprice;
  }
  public Integer getCurrency(){
    return _currency;
  }
  public void setCurrency(Integer currency){
    _currency=currency;
  }
  public PltErate gtCurrency(){
    if(getCurrency()==null)
      return null;
    return (PltErate)get(PltErate.class,getCurrency());
  }
  public void stCurrency(PltErate currency){
    if(currency==null)
      setCurrency(null);
    else
      setCurrency(currency.getPkey());
  }
  public Date getValidDate(){
    return _validDate;
  }
  public void setValidDate(Date validDate){
    _validDate=validDate;
  }
  public Byte getPaytype(){
    return _paytype;
  }
  public void setPaytype(Byte paytype){
    _paytype=paytype;
  }
  public RFQConsultPayType gtPaytype(){
    return (RFQConsultPayType)(RFQConsultPayType.TT.getLine().get(_paytype));
  }
  public void stPaytype(RFQConsultPayType paytype){
    _paytype=paytype.getLine().getKey();
  }
  public Byte getTransittype(){
    return _transittype;
  }
  public void setTransittype(Byte transittype){
    _transittype=transittype;
  }
  public RFQConsultShipping_Type gtTransittype(){
    return (RFQConsultShipping_Type)(RFQConsultShipping_Type.FOB.getLine().get(_transittype));
  }
  public void stTransittype(RFQConsultShipping_Type transittype){
    _transittype=transittype.getLine().getKey();
  }
  public Byte getSample(){
    return _sample;
  }
  public void setSample(Byte sample){
    _sample=sample;
  }
  public Boolean gtSample(){
    return byteToBoolean(_sample);
  }
  public void stSample(Boolean sample){
    _sample=booleanToByte(sample);
  }
  public String getCompanydescribe(){
    return _companydescribe;
  }
  public void setCompanydescribe(String companydescribe){
    _companydescribe=companydescribe;
  }
  public String getThrowaway(){
    return _throwaway;
  }
  public void setThrowaway(String throwaway){
    _throwaway=throwaway;
  }
  public Date getCreateDate(){
    return _createDate;
  }
  public void setCreateDate(Date createDate){
    _createDate=createDate;
  }
  public Byte getHadRead(){
    return _hadRead;
  }
  public void setHadRead(Byte hadRead){
    _hadRead=hadRead;
  }
  public Boolean gtHadRead(){
    return byteToBoolean(_hadRead);
  }
  public void stHadRead(Boolean hadRead){
    _hadRead=booleanToByte(hadRead);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
