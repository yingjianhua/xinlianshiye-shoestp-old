package irille.Entity.O2O;

import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 O2O产品
 */
public class O2O_Product extends BeanInt<O2O_Product> {

    public static final Tb TB = new Tb(O2O_Product.class, "O2O商品信息").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),
        STATUS(TB.crt(O2O_ProductStatus.DEFAULT)),//上下架
        VERIFY_STATUS(TB.crt(O2O_ProductStatus.DEFAULT)),//审核
        PRICE(SYS.AMT),
        MIN_OQ(SYS.INT),
        PRODUCT_ID(PdtProduct.fldOutKey()),
        JOIN_INFO_ID(O2O_JoinInfo.fldOutKey()),
        MESSAGE(SYS.STR__100_NULL, "信息"),
        UPPER_DATE(SYS.DATE_TIME__NULL),
        LOWER_DATE(SYS.DATE_TIME__NULL),
        REMARK(SYS.STR__100_NULL, "备注"),
        UPDATED_TIME(SYS.UPDATED_DATE_TIME),
        ROW_VERSION(SYS.ROW_VERSION),
        // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        public static final Tb.Index IDX_PRODUCTID_JOININFOID = TB.addIndex("product_id_join_info_id",true,T.PRODUCT_ID,T.JOIN_INFO_ID);
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

    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Byte _status;	// O2O商品状态 <O2O_ProductStatus>  BYTE
	// _DEFAULT:0,未审核
	// OFF:1,下架
	// WAITOFF:5,等待确认下架
	// ON:2,上架
	// PASS:3,审核通过
	// Failed:4,审核失败
  private Byte _verifyStatus;	// O2O商品状态 <O2O_ProductStatus>  BYTE
	// _DEFAULT:0,未审核
	// OFF:1,下架
	// WAITOFF:5,等待确认下架
	// ON:2,上架
	// PASS:3,审核通过
	// Failed:4,审核失败
  private BigDecimal _price;	// 金额  DEC(16,2)
  private Integer _minOq;	// INT  INT
  private Integer _productId;	// 产品 <表主键:PdtProduct>  INT
  private Integer _joinInfoId;	// O2O负责人信息 <表主键:O2O_JoinInfo>  INT
  private String _message;	// 信息  STR(100)<null>
  private Date _upperDate;	// 日期时间  TIME<null>
  private Date _lowerDate;	// 日期时间  TIME<null>
  private String _remark;	// 备注  STR(100)<null>
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public O2O_Product init(){
		super.init();
    _status=O2O_ProductStatus.DEFAULT.getLine().getKey();	// O2O商品状态 <O2O_ProductStatus>  BYTE
    _verifyStatus=O2O_ProductStatus.DEFAULT.getLine().getKey();	// O2O商品状态 <O2O_ProductStatus>  BYTE
    _price=ZERO;	// 金额  DEC(16,2)
    _minOq=0;	// INT  INT
    _productId=null;	// 产品 <表主键:PdtProduct>  INT
    _joinInfoId=null;	// O2O负责人信息 <表主键:O2O_JoinInfo>  INT
    _message=null;	// 信息  STR(100)
    _upperDate=null;	// 日期时间  TIME
    _lowerDate=null;	// 日期时间  TIME
    _remark=null;	// 备注  STR(100)
    _updatedTime=Env.getTranBeginTime();	// 更新时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static O2O_Product loadUniqueProduct_id_join_info_id(boolean lockFlag,Integer productId,Integer joinInfoId) {
    return (O2O_Product)loadUnique(T.IDX_PRODUCTID_JOININFOID,lockFlag,productId,joinInfoId);
  }
  public static O2O_Product chkUniqueProduct_id_join_info_id(boolean lockFlag,Integer productId,Integer joinInfoId) {
    return (O2O_Product)chkUnique(T.IDX_PRODUCTID_JOININFOID,lockFlag,productId,joinInfoId);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public O2O_ProductStatus gtStatus(){
    return (O2O_ProductStatus)(O2O_ProductStatus._DEFAULT.getLine().get(_status));
  }
  public void stStatus(O2O_ProductStatus status){
    _status=status.getLine().getKey();
  }
  public Byte getVerifyStatus(){
    return _verifyStatus;
  }
  public void setVerifyStatus(Byte verifyStatus){
    _verifyStatus=verifyStatus;
  }
  public O2O_ProductStatus gtVerifyStatus(){
    return (O2O_ProductStatus)(O2O_ProductStatus._DEFAULT.getLine().get(_verifyStatus));
  }
  public void stVerifyStatus(O2O_ProductStatus verifyStatus){
    _verifyStatus=verifyStatus.getLine().getKey();
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public Integer getMinOq(){
    return _minOq;
  }
  public void setMinOq(Integer minOq){
    _minOq=minOq;
  }
  public Integer getProductId(){
    return _productId;
  }
  public void setProductId(Integer productId){
    _productId=productId;
  }
  public PdtProduct gtProductId(){
    if(getProductId()==null)
      return null;
    return (PdtProduct)get(PdtProduct.class,getProductId());
  }
  public void stProductId(PdtProduct productId){
    if(productId==null)
      setProductId(null);
    else
      setProductId(productId.getPkey());
  }
  public Integer getJoinInfoId(){
    return _joinInfoId;
  }
  public void setJoinInfoId(Integer joinInfoId){
    _joinInfoId=joinInfoId;
  }
  public O2O_JoinInfo gtJoinInfoId(){
    if(getJoinInfoId()==null)
      return null;
    return (O2O_JoinInfo)get(O2O_JoinInfo.class,getJoinInfoId());
  }
  public void stJoinInfoId(O2O_JoinInfo joinInfoId){
    if(joinInfoId==null)
      setJoinInfoId(null);
    else
      setJoinInfoId(joinInfoId.getPkey());
  }
  public String getMessage(){
    return _message;
  }
  public void setMessage(String message){
    _message=message;
  }
  public Date getUpperDate(){
    return _upperDate;
  }
  public void setUpperDate(Date upperDate){
    _upperDate=upperDate;
  }
  public Date getLowerDate(){
    return _lowerDate;
  }
  public void setLowerDate(Date lowerDate){
    _lowerDate=lowerDate;
  }
  public String getRemark(){
    return _remark;
  }
  public void setRemark(String remark){
    _remark=remark;
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

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
