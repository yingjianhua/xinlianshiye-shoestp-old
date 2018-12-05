package irille.Entity.EO;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class EasyOdr extends BeanInt<EasyOdr> {
    public static final Tb<?> TB =  new Tb(EasyOdr.class,"简单订单管理").setAutoIncrement().addActIUDL();
    public  enum T  implements IEnumFld {//@formatter:off
        PKEY(TB.crtIntPkey()),
        PURCHASE(UsrPurchase.fldOutKey().setName("用户").setNull()),
        SUPPLIER(UsrSupplier.fldOutKey().setName("供应商")),
        ORDER_NUM(SYS.STR__100_NULL,"订单号"),
        TIME(SYS.DATE_TIME,"下单时间"),
        NAME(SYS.STR__20,"名字"),
        PHONE(SYS.STR__100,"电话号码"),
        ADDRESS(SYS.JSON,"收货地址"),
        COUNYPD(SYS.INT,"商品总数量"),
        ROW_VERSION(SYS.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        public static final Index IDX_ORDER_NUM = TB.addIndex("orderNum", true, ORDER_NUM);
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        private Fld<?> _fld;
        private T(Class<?> clazz,String name,boolean... isnull){
            _fld=TB.addOutKey(clazz,this,name,isnull);
        }
        private T(IEnumFld fld,boolean... isnull) {
            this(fld,null,isnull);
        }
        private T(IEnumFld fld, String name,boolean... null1) {
            _fld=TB.add(fld,this,name,null1);
        }
        private T(IEnumFld fld, String name,int strLen) {
            _fld=TB.add(fld,this,name,strLen);
        }
        private T(Fld<?> fld) {
            _fld=TB.add(fld,this);
        }
        public Fld<?> getFld(){
            return _fld;
        }
    }

    static { //在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
    }
    public static Fld fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }
    //@formatter:on
    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _purchase;	// 用户 <表主键:UsrPurchase>  INT<null>
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private String _orderNum;	// 订单号  STR(100)<null>
  private Date _time;	// 下单时间  TIME
  private String _name;	// 名字  STR(20)
  private String _phone;	// 电话号码  STR(100)
  private String _address;	// 收货地址  JSONOBJECT
  private Integer _counypd;	// 商品总数量  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public EasyOdr init(){
		super.init();
    _purchase=null;	// 用户 <表主键:UsrPurchase>  INT
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _orderNum=null;	// 订单号  STR(100)
    _time=Env.getTranBeginTime();	// 下单时间  TIME
    _name=null;	// 名字  STR(20)
    _phone=null;	// 电话号码  STR(100)
    _address=null;	// 收货地址  JSONOBJECT
    _counypd=0;	// 商品总数量  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static EasyOdr loadUniqueOrderNum(boolean lockFlag,String orderNum) {
    return (EasyOdr)loadUnique(T.IDX_ORDER_NUM,lockFlag,orderNum);
  }
  public static EasyOdr chkUniqueOrderNum(boolean lockFlag,String orderNum) {
    return (EasyOdr)chkUnique(T.IDX_ORDER_NUM,lockFlag,orderNum);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getPurchase(){
    return _purchase;
  }
  public void setPurchase(Integer purchase){
    _purchase=purchase;
  }
  public UsrPurchase gtPurchase(){
    if(getPurchase()==null)
      return null;
    return (UsrPurchase)get(UsrPurchase.class,getPurchase());
  }
  public void stPurchase(UsrPurchase purchase){
    if(purchase==null)
      setPurchase(null);
    else
      setPurchase(purchase.getPkey());
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
  public String getOrderNum(){
    return _orderNum;
  }
  public void setOrderNum(String orderNum){
    _orderNum=orderNum;
  }
  public Date getTime(){
    return _time;
  }
  public void setTime(Date time){
    _time=time;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getPhone(){
    return _phone;
  }
  public void setPhone(String phone){
    _phone=phone;
  }
  public String getAddress(){
    return _address;
  }
  public void setAddress(String address){
    _address=address;
  }
  public JSONObject gtAddress() throws JSONException {
    return getAddress()==null?new JSONObject():new JSONObject(getAddress());
  }
  public void stAddress(JSONObject address){
    setAddress(address==null?null:address.toString());
  }
  public Integer getCounypd(){
    return _counypd;
  }
  public void setCounypd(Integer counypd){
    _counypd=counypd;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
    }
