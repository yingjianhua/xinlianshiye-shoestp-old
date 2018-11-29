package irille.Entity.OdrerMeetings;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.odr.OdrOrder;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:49
 */
public class OrderMeetingOrder extends BeanInt<OrderMeetingOrder> {

  public static final Tb TB = new Tb(OrderMeetingOrder.class, "订购会-订单补充表").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    ORDERMEETINGID(OrderMeeting.fldOutKey()),
    ORDERID(OdrOrder.fldOutKey()),
    PAYMENTTIME(SYS.DATE__NULL,"付款时间"),
    BILLINGSTATUS(TB.crt(Sys.OYn.DEFAULT)),//结算状态
    CREATED_TIME(SYS.CREATED_DATE_TIME),
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
    OrderMeetingOrder.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
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
  private Integer _ordermeetingid;	// 订购会信息 <表主键:OrderMeeting>  INT
  private Long _orderid;	// 订单管理 <表主键:OdrOrder>  LONG
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingOrder init(){
		super.init();
    _ordermeetingid=null;	// 订购会信息 <表主键:OrderMeeting>  INT
    _orderid=null;	// 订单管理 <表主键:OdrOrder>  LONG
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
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
  public Long getOrderid(){
    return _orderid;
  }
  public void setOrderid(Long orderid){
    _orderid=orderid;
  }
  public OdrOrder gtOrderid(){
    if(getOrderid()==null)
      return null;
    return (OdrOrder)get(OdrOrder.class,getOrderid());
  }
  public void stOrderid(OdrOrder orderid){
    if(orderid==null)
      setOrderid(null);
    else
      setOrderid(orderid.getPkey());
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
