package irille.Entity.OdrerMeetings;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditType;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:54
 */
public class OrderMeetingAudit extends BeanInt<OrderMeetingAudit> {

  public static final Tb TB = new Tb(OrderMeetingAudit.class, "订购会-审核状态").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    OrderMeeting(irille.Entity.OdrerMeetings.OrderMeeting.fldOutKey()),
    STATUS(TB.crt(OrderMeetingAuditStatus.DEFAULT)),
    MESSAGE(SYS.STR__200_NULL, "消息"),
    TYPE(TB.crt(OrderMeetingAuditType.DEFAULT)),
    SUPPLIERID(UsrSupplier.fldOutKey().setNull()),
    SNAPSHOT(SYS.JSON, "快照"),
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
    OrderMeetingAudit.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
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
  private Integer _ordermeeting;	// 订购会信息 <表主键:OrderMeeting>  INT
  private Byte _status;	// 订购会状态 <OrderMeetingAuditStatus>  BYTE
	// PENDING:0,待审核
	// VERIFYING:1,审核中
	// PASS:2,审核通过
	// DELETE:6,活动已删除
  private String _message;	// 消息  STR(200)<null>
  private Byte _type;	// 供应商认证类型 <OrderMeetingAuditType>  BYTE
	// PLATFORM:0,平台
	// SUPPLIER:1,商家
  private Integer _supplierid;	// 供应商 <表主键:UsrSupplier>  INT<null>
  private String _snapshot;	// 快照  JSONOBJECT
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingAudit init(){
		super.init();
    _ordermeeting=null;	// 订购会信息 <表主键:OrderMeeting>  INT
    _status=OrderMeetingAuditStatus.DEFAULT.getLine().getKey();	// 订购会状态 <OrderMeetingAuditStatus>  BYTE
    _message=null;	// 消息  STR(200)
    _type=OrderMeetingAuditType.DEFAULT.getLine().getKey();	// 供应商认证类型 <OrderMeetingAuditType>  BYTE
    _supplierid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _snapshot=null;	// 快照  JSONOBJECT
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
  public Integer getOrdermeeting(){
    return _ordermeeting;
  }
  public void setOrdermeeting(Integer ordermeeting){
    _ordermeeting=ordermeeting;
  }
  public OrderMeeting gtOrdermeeting(){
    if(getOrdermeeting()==null)
      return null;
    return (OrderMeeting)get(OrderMeeting.class,getOrdermeeting());
  }
  public void stOrdermeeting(OrderMeeting ordermeeting){
    if(ordermeeting==null)
      setOrdermeeting(null);
    else
      setOrdermeeting(ordermeeting.getPkey());
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OrderMeetingAuditStatus gtStatus(){
    return (OrderMeetingAuditStatus)(OrderMeetingAuditStatus.PENDING.getLine().get(_status));
  }
  public void stStatus(OrderMeetingAuditStatus status){
    _status=status.getLine().getKey();
  }
  public String getMessage(){
    return _message;
  }
  public void setMessage(String message){
    _message=message;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OrderMeetingAuditType gtType(){
    return (OrderMeetingAuditType)(OrderMeetingAuditType.PLATFORM.getLine().get(_type));
  }
  public void stType(OrderMeetingAuditType type){
    _type=type.getLine().getKey();
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
  public String getSnapshot(){
    return _snapshot;
  }
  public void setSnapshot(String snapshot){
    _snapshot=snapshot;
  }
  public JSONObject gtSnapshot() throws JSONException {
    return getSnapshot()==null?new JSONObject():new JSONObject(getSnapshot());
  }
  public void stSnapshot(JSONObject snapshot){
    setSnapshot(snapshot==null?null:snapshot.toString());
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
