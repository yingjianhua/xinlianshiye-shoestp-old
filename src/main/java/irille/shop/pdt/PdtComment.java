package irille.shop.pdt;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.Pdt.OSatisfaction;
import irille.shop.usr.UsrPurchase;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class PdtComment extends BeanInt<PdtComment> {
    public static final Tb TB = new Tb(PdtComment.class, "评论").setAutoIncrement().addActList().addActDel();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),//主键PKEY
        PRODUCT(PdtProduct.fldOutKey()),//产品PKEY
        //订单PKEY
        USERS_PKEY(UsrPurchase.fldOutKey()),//用户PKEY
        COMMENT(SYS.STR__200_NULL, "评论内容"),//评论内容
        REPLY(SYS.STR__200_NULL, "店家回复"),
        COMMENT_TIME(SYS.DATE_TIME, "评论时间"),//评论时间
        IMAGES(SYS.IMG_MULTI__2000_NULL, "图片"),//评论图   多图 用 , 分隔
        PRODUCT_SATISFACTION(Tb.crt(Pdt.OSatisfaction.DEFAULT).setName("产品满意度")),//产品满意度
        OTHOER_SATISFACTION(SYS.JSON, "综合评价"), //综合评价 比如:物流,品质等信息
        USEFUL_NUMBER(SYS.INT_PLUS_OR_ZERO), //赞同数
        UNUSEFUL_NUMBER(SYS.INT_PLUS_OR_ZERO),// 反对数
        ROW_VERSION(SYS.ROW_VERSION)//版本

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
  private Integer _product;	// 产品 <表主键:PdtProduct>  INT
  private Integer _usersPkey;	// 采购商 <表主键:UsrPurchase>  INT
  private String _comment;	// 评论内容  STR(200)<null>
  private String _reply;	// 店家回复  STR(200)<null>
  private Date _commentTime;	// 评论时间  TIME
  private String _images;	// 图片  STR(2000)<null>
  private Byte _productSatisfaction;	// 产品满意度 <OSatisfaction>  BYTE
	// ONE:1,一星评级
	// TWO:2,二星评级
	// THREE:3,三星评级
	// FOUR:4,四星评级
	// FIVE:5,五星评级
  private String _othoerSatisfaction;	// 综合评价  JSONOBJECT
  private Integer _usefulNumber;	// Int正数或零  INT
  private Integer _unusefulNumber;	// Int正数或零  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtComment init(){
		super.init();
    _product=null;	// 产品 <表主键:PdtProduct>  INT
    _usersPkey=null;	// 采购商 <表主键:UsrPurchase>  INT
    _comment=null;	// 评论内容  STR(200)
    _reply=null;	// 店家回复  STR(200)
    _commentTime=Env.getTranBeginTime();	// 评论时间  TIME
    _images=null;	// 图片  STR(2000)
    _productSatisfaction=OSatisfaction.DEFAULT.getLine().getKey();	// 产品满意度 <OSatisfaction>  BYTE
    _othoerSatisfaction=null;	// 综合评价  JSONOBJECT
    _usefulNumber=0;	// Int正数或零  INT
    _unusefulNumber=0;	// Int正数或零  INT
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
  public Integer getUsersPkey(){
    return _usersPkey;
  }
  public void setUsersPkey(Integer usersPkey){
    _usersPkey=usersPkey;
  }
  public UsrPurchase gtUsersPkey(){
    if(getUsersPkey()==null)
      return null;
    return (UsrPurchase)get(UsrPurchase.class,getUsersPkey());
  }
  public void stUsersPkey(UsrPurchase usersPkey){
    if(usersPkey==null)
      setUsersPkey(null);
    else
      setUsersPkey(usersPkey.getPkey());
  }
  public String getComment(){
    return _comment;
  }
  public void setComment(String comment){
    _comment=comment;
  }
  public String getReply(){
    return _reply;
  }
  public void setReply(String reply){
    _reply=reply;
  }
  public Date getCommentTime(){
    return _commentTime;
  }
  public void setCommentTime(Date commentTime){
    _commentTime=commentTime;
  }
  public String getImages(){
    return _images;
  }
  public void setImages(String images){
    _images=images;
  }
  public Byte getProductSatisfaction(){
    return _productSatisfaction;
  }
  public void setProductSatisfaction(Byte productSatisfaction){
    _productSatisfaction=productSatisfaction;
  }
  public OSatisfaction gtProductSatisfaction(){
    return (OSatisfaction)(OSatisfaction.THREE.getLine().get(_productSatisfaction));
  }
  public void stProductSatisfaction(OSatisfaction productSatisfaction){
    _productSatisfaction=productSatisfaction.getLine().getKey();
  }
  public String getOthoerSatisfaction(){
    return _othoerSatisfaction;
  }
  public void setOthoerSatisfaction(String othoerSatisfaction){
    _othoerSatisfaction=othoerSatisfaction;
  }
  public JSONObject gtOthoerSatisfaction() throws JSONException {
    return getOthoerSatisfaction()==null?new JSONObject():new JSONObject(getOthoerSatisfaction());
  }
  public void stOthoerSatisfaction(JSONObject othoerSatisfaction){
    setOthoerSatisfaction(othoerSatisfaction==null?null:othoerSatisfaction.toString());
  }
  public Integer getUsefulNumber(){
    return _usefulNumber;
  }
  public void setUsefulNumber(Integer usefulNumber){
    _usefulNumber=usefulNumber;
  }
  public Integer getUnusefulNumber(){
    return _unusefulNumber;
  }
  public void setUnusefulNumber(Integer unusefulNumber){
    _unusefulNumber=unusefulNumber;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<


}
