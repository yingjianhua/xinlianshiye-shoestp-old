package irille.Entiry.OdrerMettings;

import irille.Entiry.OdrerMettings.Enums.OdrerMettingProductStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:49
 */
public class OrderMeetingProduct extends BeanInt<OrderMeetingProduct> {

  public static final Tb TB = new Tb(OrderMeetingProduct.class, "订购会-产品信息").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    PRODUCTID(PdtProduct.fldOutKey()),
    SUPPLIERID(UsrSupplier.fldOutKey()),
    ORDERMEETINGID(OrderMeeting.fldOutKey()),
    STATUS(TB.crt(OdrerMettingProductStatus.DEFAULT)),
    MOQ(SYS.INT, "原起订量"),
    PRICE(SYS.PRICE, "原商品价格"),
    NEWMOQ(SYS.INT, "活动起订量"),
    NEWPRICE(SYS.PRICE, "活动价"),
    TARGET_COUNT(SYS.INT, "目标量"),
    UPDATED_TIME(SYS.UPDATED_DATE_TIME),
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
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
    OrderMeetingProduct.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _productid;	// 产品 <表主键:PdtProduct>  INT
  private Integer _supplierid;	// 供应商 <表主键:UsrSupplier>  INT
  private Integer _ordermeetingid;	// 订购会信息 <表主键:OrderMeeting>  INT
  private Byte _status;	// 供应商认证 <OdrerMettingProductStatus>  BYTE
	// NO:0,未认证
	// YES:1,已认证
  private Integer _moq;	// 原起订量  INT
  private BigDecimal _price;	// 原商品价格  DEC(14,4)
  private Integer _newmoq;	// 活动起订量  INT
  private BigDecimal _newprice;	// 活动价  DEC(14,4)
  private Integer _targetCount;	// 目标量  INT
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingProduct init(){
		super.init();
    _productid=null;	// 产品 <表主键:PdtProduct>  INT
    _supplierid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _ordermeetingid=null;	// 订购会信息 <表主键:OrderMeeting>  INT
    _status=OdrerMettingProductStatus.DEFAULT.getLine().getKey();	// 供应商认证 <OdrerMettingProductStatus>  BYTE
    _moq=0;	// 原起订量  INT
    _price=ZERO;	// 原商品价格  DEC(14,4)
    _newmoq=0;	// 活动起订量  INT
    _newprice=ZERO;	// 活动价  DEC(14,4)
    _targetCount=0;	// 目标量  INT
    _updatedTime= Env.getTranBeginTime();	// 更新时间  TIME
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
  public Integer getProductid(){
    return _productid;
  }
  public void setProductid(Integer productid){
    _productid=productid;
  }
  public PdtProduct gtProductid(){
    if(getProductid()==null)
      return null;
    return (PdtProduct)get(PdtProduct.class,getProductid());
  }
  public void stProductid(PdtProduct productid){
    if(productid==null)
      setProductid(null);
    else
      setProductid(productid.getPkey());
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
  public Integer getOrdermeetingid(){
    return _ordermeetingid;
  }
  public void setOrdermeetingid(Integer ordermeetingid){
    _ordermeetingid=ordermeetingid;
  }
  public OrderMeeting gtOrdermeetingid(){
    if(getOrdermeetingid()==null)
      return null;
    return (OrderMeeting)get(OrderMeeting.class,getOrdermeetingid());
  }
  public void stOrdermeetingid(OrderMeeting ordermeetingid){
    if(ordermeetingid==null)
      setOrdermeetingid(null);
    else
      setOrdermeetingid(ordermeetingid.getPkey());
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OdrerMettingProductStatus gtStatus(){
    return (OdrerMettingProductStatus)(OdrerMettingProductStatus.NO.getLine().get(_status));
  }
  public void stStatus(OdrerMettingProductStatus status){
    _status=status.getLine().getKey();
  }
  public Integer getMoq(){
    return _moq;
  }
  public void setMoq(Integer moq){
    _moq=moq;
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public Integer getNewmoq(){
    return _newmoq;
  }
  public void setNewmoq(Integer newmoq){
    _newmoq=newmoq;
  }
  public BigDecimal getNewprice(){
    return _newprice;
  }
  public void setNewprice(BigDecimal newprice){
    _newprice=newprice;
  }
  public Integer getTargetCount(){
    return _targetCount;
  }
  public void setTargetCount(Integer targetCount){
    _targetCount=targetCount;
  }
  public Date getUpdatedTime(){
    return _updatedTime;
  }
  public void setUpdatedTime(Date updatedTime){
    _updatedTime=updatedTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
