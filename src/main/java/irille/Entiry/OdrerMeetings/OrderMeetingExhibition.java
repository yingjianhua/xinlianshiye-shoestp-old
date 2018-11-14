package irille.Entiry.OdrerMeetings;

import irille.Entiry.OdrerMeetings.Enums.OrderMeetingExhibitionStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:25
 */
public class OrderMeetingExhibition extends BeanInt<OrderMeetingExhibition> {

  public static final Tb TB = new Tb(OrderMeetingExhibition.class, "订购会-会场信息").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    ADDRESS(SYS.STR__200_NULL, "订购会标题"),
    COUTRY(SYS.STR__200_NULL, "订购会标题"),
    START_TIME(SYS.TIME, "结束时间"),
    END_TIME(SYS.TIME, "开始时间"),
    STATUS(TB.crt(OrderMeetingExhibitionStatus.DEFAULT)),
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
    OrderMeetingExhibition.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
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
  private String _address;	// 订购会标题  STR(200)<null>
  private String _coutry;	// 订购会标题  STR(200)<null>
  private Date _startTime;	// 结束时间  TIME
  private Date _endTime;	// 开始时间  TIME
  private Byte _status;	// 供应商场地 <OrderMeetingExhibitionStatus>  BYTE
	// FAILED:0,未通过
	// PASS:1,通过
	// DELETE:2,删除
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingExhibition init(){
		super.init();
    _address=null;	// 订购会标题  STR(200)
    _coutry=null;	// 订购会标题  STR(200)
    _startTime=null;	// 结束时间  TIME
    _endTime=null;	// 开始时间  TIME
    _status=OrderMeetingExhibitionStatus.DEFAULT.getLine().getKey();	// 供应商场地 <OrderMeetingExhibitionStatus>  BYTE
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
  public String getAddress(){
    return _address;
  }
  public void setAddress(String address){
    _address=address;
  }
  public String getCoutry(){
    return _coutry;
  }
  public void setCoutry(String coutry){
    _coutry=coutry;
  }
  public Date getStartTime(){
    return _startTime;
  }
  public void setStartTime(Date startTime){
    _startTime=startTime;
  }
  public Date getEndTime(){
    return _endTime;
  }
  public void setEndTime(Date endTime){
    _endTime=endTime;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OrderMeetingExhibitionStatus gtStatus(){
    return (OrderMeetingExhibitionStatus)(OrderMeetingExhibitionStatus.FAILED.getLine().get(_status));
  }
  public void stStatus(OrderMeetingExhibitionStatus status){
    _status=status.getLine().getKey();
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
