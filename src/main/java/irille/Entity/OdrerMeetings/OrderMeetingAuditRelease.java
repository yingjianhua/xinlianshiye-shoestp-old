package irille.Entity.OdrerMeetings;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditType;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:54
 */
public class OrderMeetingAuditRelease extends BeanInt<OrderMeetingAuditRelease> {

  public static final Tb TB = new Tb(OrderMeetingAuditRelease.class, "发布订购会-审核状态").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    ODRMEETING(OrderMeeting.fldOutKey()),
    STATUS(TB.crt(OrderMeetingAuditStatus.DEFAULT)),
    MESSAGE(SYS.STR__200_NULL, "消息"),
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
    OrderMeetingAuditRelease.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
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
  private Integer _odrmeeting;	// 订购会信息 <表主键:OrderMeeting>  INT
  private Byte _status;	// 参加订购会状态 <OrderMeetingAuditStatus>  BYTE
	// PENDING:0,待审核
	// ACTIVITY:1,审核通过
	// FAILURE:2,审核失败
	// DISCONTINUATION:3,停用
	// DELETE:4,已删除
  private String _message;	// 消息  STR(200)<null>
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingAuditRelease init(){
		super.init();
    _odrmeeting=null;	// 订购会信息 <表主键:OrderMeeting>  INT
    _status=OrderMeetingAuditStatus.DEFAULT.getLine().getKey();	// 参加订购会状态 <OrderMeetingAuditStatus>  BYTE
    _message=null;	// 消息  STR(200)
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
  public Integer getOdrmeeting(){
    return _odrmeeting;
  }
  public void setOdrmeeting(Integer odrmeeting){
    _odrmeeting=odrmeeting;
  }
  public OrderMeeting gtOdrmeeting(){
    if(getOdrmeeting()==null)
      return null;
    return (OrderMeeting)get(OrderMeeting.class,getOdrmeeting());
  }
  public void stOdrmeeting(OrderMeeting odrmeeting){
    if(odrmeeting==null)
      setOdrmeeting(null);
    else
      setOdrmeeting(odrmeeting.getPkey());
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
