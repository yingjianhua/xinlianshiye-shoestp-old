package irille.Entity.RFQ;

import irille.Entity.RFQ.Enums.*;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

import java.util.Date;

public class RFQConsult extends BeanInt<RFQConsult> {
    private static final long serialVersionUID = 7524946206877858631L;
    public static final Tb<?> TB = new Tb<>(RFQConsult.class, "新询盘").setAutoIncrement();

    public enum T implements IEnumFld {//@formatter:off
        PKEY(Tb.crtIntPkey()),
        TITLE(Sys.T.STR__200, "标题"),//一般为产品名字
        IMAGE(Sys.T.IMG_MULTI__2000_NULL),
        COUNTRY(PltCountry.fldOutKey()),
        PRODUCT(PdtProduct.fldOutKey().setNull()), //产品ID
        HAVE_NEW_MSG(Sys.T.YN, "有新消息"),//是否有新消息 1:有,0:没有
        CONTENT(Sys.T.STR__200_NULL, "内容"),
        LEFT_COUNT(Sys.T.INT_PLUS_OR_ZERO, "剩余抢单次数"), //初始化次数为5
        QUANTITY(Sys.T.INT, "商品数量"),
        UNIT(Tb.crt(RFQConsultUnit.DEFAULT)),
        PURCHASE_ID(UsrPurchase.fldOutKey().setName("采购商")),
        SUPPLIER_ID(UsrSupplier.fldOutKey().setName("供应商")),
        TYPE(Tb.crt(RFQConsultStatus.DEFAULT)),
        Status(Tb.crt(RFQConsultType.DEFAULT)),
        VERIFY_STATUS(Tb.crt(RFQConsultVerifyStatus.DEFAULT)),
        VALID_DATE(SYS.DATE_TIME, "有效期至"),
        PRICE(SYS.STR__20_NULL, "价格(价格区间)"),
        PAY_TYPE(Tb.crt(RFQConsultPayType.DEFAULT)), //支付方式
        SHIPPING_TYPE(Tb.crt(RFQConsultShipping_Type.DEFAULT)), //运送方式
        CURRENCY(PltErate.fldOutKey()),  //货币类型
        DESTINATION(SYS.STR__200_NULL),
        TOTAL(Sys.T.INT_PLUS_OR_ZERO, "总抢单数"),
        CHANGE_COUNT(SYS.SHORT, "修改总数"), //普通,私有询盘时有效
        EXTRA_DESCRIPTION(SYS.STR__2000_NULL, "修改总数"),//普通,私有询盘时有效
        CREATE_TIME(Sys.T.DATE_TIME, "创建时间"),

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
        return Tb.crtOutKey(TB, code, name);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _title;	// 标题  STR(200)
  private String _image;	// 图片  STR(2000)<null>
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _product;	// 产品 <表主键:PdtProduct>  INT<null>
  private Byte _haveNewMsg;	// 有新消息 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _content;	// 内容  STR(200)<null>
  private Integer _leftCount;	// 剩余抢单次数  INT
  private Integer _quantity;	// 商品数量  INT
  private Byte _unit;	// 活动状态 <RFQConsultUnit>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Integer _purchaseId;	// 采购商 <表主键:UsrPurchase>  INT
  private Integer _supplierId;	// 供应商 <表主键:UsrSupplier>  INT
  private Byte _type;	// 活动状态 <RFQConsultStatus>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Byte _status;	// 活动状态 <RFQConsultType>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Byte _verifyStatus;	// 活动状态 <RFQConsultVerifyStatus>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Date _validDate;	// 有效期至  TIME
  private String _price;	// 价格(价格区间)  STR(20)<null>
  private Byte _payType;	// 活动状态 <RFQConsultPayType>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Byte _shippingType;	// 活动状态 <RFQConsultShipping_Type>  BYTE
	// TOBEGIN:1,即将开始
	// ACTIVITY:2,活动中
	// END:3,活动结束
  private Integer _currency;	// 费率设置 <表主键:PltErate>  INT
  private String _destination;	// 字符200  STR(200)<null>
  private Integer _total;	// 总抢单数  INT
  private Short _changeCount;	// 修改总数  SHORT
  private String _extraDescription;	// 修改总数  STR(2000)<null>
  private Date _createTime;	// 创建时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQConsult init(){
		super.init();
    _title=null;	// 标题  STR(200)
    _image=null;	// 图片  STR(2000)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _product=null;	// 产品 <表主键:PdtProduct>  INT
    _haveNewMsg=OYn.DEFAULT.getLine().getKey();	// 有新消息 <OYn>  BYTE
    _content=null;	// 内容  STR(200)
    _leftCount=0;	// 剩余抢单次数  INT
    _quantity=0;	// 商品数量  INT
    _unit=RFQConsultUnit.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultUnit>  BYTE
    _purchaseId=null;	// 采购商 <表主键:UsrPurchase>  INT
    _supplierId=null;	// 供应商 <表主键:UsrSupplier>  INT
    _type=RFQConsultStatus.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultStatus>  BYTE
    _status=RFQConsultType.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultType>  BYTE
    _verifyStatus=RFQConsultVerifyStatus.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultVerifyStatus>  BYTE
    _validDate=Env.getTranBeginTime();	// 有效期至  TIME
    _price=null;	// 价格(价格区间)  STR(20)
    _payType=RFQConsultPayType.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultPayType>  BYTE
    _shippingType=RFQConsultShipping_Type.DEFAULT.getLine().getKey();	// 活动状态 <RFQConsultShipping_Type>  BYTE
    _currency=null;	// 费率设置 <表主键:PltErate>  INT
    _destination=null;	// 字符200  STR(200)
    _total=0;	// 总抢单数  INT
    _changeCount=0;	// 修改总数  SHORT
    _extraDescription=null;	// 修改总数  STR(2000)
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
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
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getImage(){
    return _image;
  }
  public void setImage(String image){
    _image=image;
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
  public Byte getHaveNewMsg(){
    return _haveNewMsg;
  }
  public void setHaveNewMsg(Byte haveNewMsg){
    _haveNewMsg=haveNewMsg;
  }
  public Boolean gtHaveNewMsg(){
    return byteToBoolean(_haveNewMsg);
  }
  public void stHaveNewMsg(Boolean haveNewMsg){
    _haveNewMsg=booleanToByte(haveNewMsg);
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public Integer getLeftCount(){
    return _leftCount;
  }
  public void setLeftCount(Integer leftCount){
    _leftCount=leftCount;
  }
  public Integer getQuantity(){
    return _quantity;
  }
  public void setQuantity(Integer quantity){
    _quantity=quantity;
  }
  public Byte getUnit(){
    return _unit;
  }
  public void setUnit(Byte unit){
    _unit=unit;
  }
  public RFQConsultUnit gtUnit(){
    return (RFQConsultUnit)(RFQConsultUnit.TOBEGIN.getLine().get(_unit));
  }
  public void stUnit(RFQConsultUnit unit){
    _unit=unit.getLine().getKey();
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
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public RFQConsultStatus gtType(){
    return (RFQConsultStatus)(RFQConsultStatus.TOBEGIN.getLine().get(_type));
  }
  public void stType(RFQConsultStatus type){
    _type=type.getLine().getKey();
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public RFQConsultType gtStatus(){
    return (RFQConsultType)(RFQConsultType.TOBEGIN.getLine().get(_status));
  }
  public void stStatus(RFQConsultType status){
    _status=status.getLine().getKey();
  }
  public Byte getVerifyStatus(){
    return _verifyStatus;
  }
  public void setVerifyStatus(Byte verifyStatus){
    _verifyStatus=verifyStatus;
  }
  public RFQConsultVerifyStatus gtVerifyStatus(){
    return (RFQConsultVerifyStatus)(RFQConsultVerifyStatus.TOBEGIN.getLine().get(_verifyStatus));
  }
  public void stVerifyStatus(RFQConsultVerifyStatus verifyStatus){
    _verifyStatus=verifyStatus.getLine().getKey();
  }
  public Date getValidDate(){
    return _validDate;
  }
  public void setValidDate(Date validDate){
    _validDate=validDate;
  }
  public String getPrice(){
    return _price;
  }
  public void setPrice(String price){
    _price=price;
  }
  public Byte getPayType(){
    return _payType;
  }
  public void setPayType(Byte payType){
    _payType=payType;
  }
  public RFQConsultPayType gtPayType(){
    return (RFQConsultPayType)(RFQConsultPayType.TOBEGIN.getLine().get(_payType));
  }
  public void stPayType(RFQConsultPayType payType){
    _payType=payType.getLine().getKey();
  }
  public Byte getShippingType(){
    return _shippingType;
  }
  public void setShippingType(Byte shippingType){
    _shippingType=shippingType;
  }
  public RFQConsultShipping_Type gtShippingType(){
    return (RFQConsultShipping_Type)(RFQConsultShipping_Type.TOBEGIN.getLine().get(_shippingType));
  }
  public void stShippingType(RFQConsultShipping_Type shippingType){
    _shippingType=shippingType.getLine().getKey();
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
  public String getDestination(){
    return _destination;
  }
  public void setDestination(String destination){
    _destination=destination;
  }
  public Integer getTotal(){
    return _total;
  }
  public void setTotal(Integer total){
    _total=total;
  }
  public Short getChangeCount(){
    return _changeCount;
  }
  public void setChangeCount(Short changeCount){
    _changeCount=changeCount;
  }
  public String getExtraDescription(){
    return _extraDescription;
  }
  public void setExtraDescription(String extraDescription){
    _extraDescription=extraDescription;
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
