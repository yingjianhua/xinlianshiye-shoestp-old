package irille.Entity.RFQ;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.Entity.RFQ.Enums.RFQConsultPayType;
import irille.Entity.RFQ.Enums.RFQConsultShipping_Type;
import irille.Entity.RFQ.Enums.RFQConsultStatus;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Entity.RFQ.Enums.RFQConsultUnit;
import irille.Entity.RFQ.Enums.RFQConsultVerifyStatus;
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

public class RFQConsult extends BeanInt<RFQConsult> {
    private static final long serialVersionUID = 7524946206877858631L;
    public static final Tb<?> TB = new Tb<>(RFQConsult.class, "新询盘").setAutoIncrement();

    public enum T implements IEnumFld {//@formatter:off
        PKEY(Tb.crtIntPkey()),
        TITLE(Sys.T.STR__500, "标题"),//一般为产品名字
        IMAGE(Sys.T.IMG_MULTI__2000_NULL),
        COUNTRY(PltCountry.fldOutKey()),
        PRODUCT(PdtProduct.fldOutKey().setNull()), //产品ID
        CONTENT(Sys.T.STR__200_NULL, "内容"),
        LEFT_COUNT(Sys.T.INT_PLUS_OR_ZERO, "剩余抢单次数"), //初始化次数为5
        QUANTITY(Sys.T.INT, "商品数量"),//商品数量
        UNIT(Tb.crt(RFQConsultUnit.DEFAULT)),//商品数量单位
        PURCHASE_ID(UsrPurchase.fldOutKey().setName("采购商")),
        SUPPLIER_ID(UsrSupplier.fldOutKey().setNull().setName("供应商")),
        TYPE(Tb.crt(RFQConsultType.DEFAULT)),//询盘类型
        STATUS(Tb.crt(RFQConsultStatus.DEFAULT)),//发布状态
        VERIFY_STATUS(Tb.crt(RFQConsultVerifyStatus.DEFAULT)),//审核状态
        VALID_DATE(Sys.T.DATE_TIME, "有效期至"),
        PRICE(Sys.T.STR__20_NULL, "价格(价格区间)"),
        PAY_TYPE(Tb.crt(RFQConsultPayType.DEFAULT).setNull()), //支付方式
        SHIPPING_TYPE(Tb.crt(RFQConsultShipping_Type.DEFAULT).setNull()), //运送方式
        CURRENCY(PltErate.fldOutKey().setNull()),  //货币类型
        EXTRA_REQUEST(Sys.T.STR__100_NULL),//额外请求, 店铺询盘专用字段, 是用逗号分隔的请求内容 格式如: price, inspection product, product specification
        PRODUCT_REQUEST(Sys.T.JSON),//请求产品列表, 店铺询盘专用字段, 在店铺的基础上指定感兴趣的产品 格式如: [{"pkey":1, "name":"bb", "image":"aa"}, {"pkey":2, "name":"dd", "image":"cc"}]
        DESTINATION(Sys.T.STR__200_NULL, "目的地"),
        TOTAL(Sys.T.INT_PLUS_OR_ZERO, "总抢单数"),
        CHANGE_COUNT(Sys.T.SHORT, "修改总数"),
        EXTRA_DESCRIPTION(Sys.T.STR__2000_NULL, "额外信息"),//总共可以修改3次, 修改次数在change_count字段统计
        CREATE_TIME(Sys.T.DATE_TIME, "创建时间"),
        IS_DELETED(Sys.T.YN, "是否已删除"),//为了适应在删除询盘的情况, 只影响到询盘不能被抢单,而不影响已抢单询盘的聊天功能, 故增加此字段, 用于标记询盘是否已删除
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
  private String _title;	// 标题  STR(500)
  private String _image;	// 图片  STR(2000)<null>
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _product;	// 产品 <表主键:PdtProduct>  INT<null>
  private String _content;	// 内容  STR(200)<null>
  private Integer _leftCount;	// 剩余抢单次数  INT
  private Integer _quantity;	// 商品数量  INT
  private Byte _unit;	// 货物单位 <RFQConsultUnit>  BYTE
	// PAIR:1,Pairs
	// Twenty_Foot_Container:2,Twenty-Foot Container
	// Forty_Foot_Container:3,Forty-Foot Container
  private Integer _purchaseId;	// 采购商 <表主键:UsrPurchase>  INT
  private Integer _supplierId;	// 供应商 <表主键:UsrSupplier>  INT<null>
  private Byte _type;	// 询盘类型 <RFQConsultType>  BYTE
	// RFQ:1,FRQ询盘
	// INQUIRY:2,询盘
	// Private_INQUIRY:3,私人展会询盘
	// supplier_INQUIRY:4,供应商询盘
  private Byte _status;	// RFQ状态 <RFQConsultStatus>  BYTE
	// ready:1,待发布
	// runing:2,进行中
	// complete:3,已完成
	// close:4,已关闭
  private Byte _verifyStatus;	// 审核状态 <RFQConsultVerifyStatus>  BYTE
	// UNAUDITED:1,未审核
	// FAIL:2,未通过
	// PASS:3,通过
  private Date _validDate;	// 有效期至  TIME
  private String _price;	// 价格(价格区间)  STR(20)<null>
  private Byte _payType;	// 支付方式 <RFQConsultPayType>  BYTE<null>
	// TT:1,TT支付
	// LC:2,L/C
	// DP:3,D/P
	// WesternUnion:4,Western Union
	// MoneyGram:5,Money Gram
  private Byte _shippingType;	// 配送方式 <RFQConsultShipping_Type>  BYTE<null>
	// FOB:1,FOB
	// CIF:2,CIF
	// CNF:3,CNF
	// CRF:4,CRF
  private Integer _currency;	// 费率设置 <表主键:PltErate>  INT<null>
  private String _extraRequest;	// 字符100  STR(100)
  private String _productRequest;	// JSON  JSONOBJECT
  private String _destination;	// 目的地  STR(200)<null>
  private Integer _total;	// 总抢单数  INT
  private Short _changeCount;	// 修改总数  SHORT
  private String _extraDescription;	// 额外信息  STR(2000)<null>
  private Date _createTime;	// 创建时间  TIME
  private Byte _isDeleted;	// 是否已删除 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQConsult init(){
		super.init();
    _title=null;	// 标题  STR(500)
    _image=null;	// 图片  STR(2000)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _product=null;	// 产品 <表主键:PdtProduct>  INT
    _content=null;	// 内容  STR(200)
    _leftCount=0;	// 剩余抢单次数  INT
    _quantity=0;	// 商品数量  INT
    _unit=RFQConsultUnit.DEFAULT.getLine().getKey();	// 货物单位 <RFQConsultUnit>  BYTE
    _purchaseId=null;	// 采购商 <表主键:UsrPurchase>  INT
    _supplierId=null;	// 供应商 <表主键:UsrSupplier>  INT
    _type=RFQConsultType.DEFAULT.getLine().getKey();	// 询盘类型 <RFQConsultType>  BYTE
    _status=RFQConsultStatus.DEFAULT.getLine().getKey();	// RFQ状态 <RFQConsultStatus>  BYTE
    _verifyStatus=RFQConsultVerifyStatus.DEFAULT.getLine().getKey();	// 审核状态 <RFQConsultVerifyStatus>  BYTE
    _validDate=Env.getTranBeginTime();	// 有效期至  TIME
    _price=null;	// 价格(价格区间)  STR(20)
    _payType=RFQConsultPayType.DEFAULT.getLine().getKey();	// 支付方式 <RFQConsultPayType>  BYTE
    _shippingType=RFQConsultShipping_Type.DEFAULT.getLine().getKey();	// 配送方式 <RFQConsultShipping_Type>  BYTE
    _currency=null;	// 费率设置 <表主键:PltErate>  INT
    _extraRequest=null;	// 字符100  STR(100)
    _productRequest=null;	// JSON  JSONOBJECT
    _destination=null;	// 目的地  STR(200)
    _total=0;	// 总抢单数  INT
    _changeCount=0;	// 修改总数  SHORT
    _extraDescription=null;	// 额外信息  STR(2000)
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
    _isDeleted=OYn.DEFAULT.getLine().getKey();	// 是否已删除 <OYn>  BYTE
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
    return (RFQConsultUnit)(RFQConsultUnit.PAIR.getLine().get(_unit));
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
  public RFQConsultType gtType(){
    return (RFQConsultType)(RFQConsultType.RFQ.getLine().get(_type));
  }
  public void stType(RFQConsultType type){
    _type=type.getLine().getKey();
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public RFQConsultStatus gtStatus(){
    return (RFQConsultStatus)(RFQConsultStatus.ready.getLine().get(_status));
  }
  public void stStatus(RFQConsultStatus status){
    _status=status.getLine().getKey();
  }
  public Byte getVerifyStatus(){
    return _verifyStatus;
  }
  public void setVerifyStatus(Byte verifyStatus){
    _verifyStatus=verifyStatus;
  }
  public RFQConsultVerifyStatus gtVerifyStatus(){
    return (RFQConsultVerifyStatus)(RFQConsultVerifyStatus.UNAUDITED.getLine().get(_verifyStatus));
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
    return (RFQConsultPayType)(RFQConsultPayType.TT.getLine().get(_payType));
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
    return (RFQConsultShipping_Type)(RFQConsultShipping_Type.FOB.getLine().get(_shippingType));
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
  public String getExtraRequest(){
    return _extraRequest;
  }
  public void setExtraRequest(String extraRequest){
    _extraRequest=extraRequest;
  }
  public String getProductRequest(){
    return _productRequest;
  }
  public void setProductRequest(String productRequest){
    _productRequest=productRequest;
  }
  public JSONObject gtProductRequest() throws JSONException {
    return getProductRequest()==null?new JSONObject():new JSONObject(getProductRequest());
  }
  public void stProductRequest(JSONObject productRequest){
    setProductRequest(productRequest==null?null:productRequest.toString());
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
  public Byte getIsDeleted(){
    return _isDeleted;
  }
  public void setIsDeleted(Byte isDeleted){
    _isDeleted=isDeleted;
  }
  public Boolean gtIsDeleted(){
    return byteToBoolean(_isDeleted);
  }
  public void stIsDeleted(Boolean isDeleted){
    _isDeleted=booleanToByte(isDeleted);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
