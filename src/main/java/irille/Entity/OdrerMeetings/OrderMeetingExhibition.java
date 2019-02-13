package irille.Entity.OdrerMeetings;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingExhibitionStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;

import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:25
 */
public class OrderMeetingExhibition extends BeanInt<OrderMeetingExhibition> {

  public static final Tb TB = new Tb(OrderMeetingExhibition.class, "订购会-会场信息").setAutoIncrement()
      .addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    ADDRESS(SYS.STR__200_NULL, "展厅地址"),
    NAME(SYS.STR__200_NULL, "订购会标题"),
    COUNTRY(PltCountry.fldOutKey()),
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
  private String _address;	// 展厅地址  STR(200)<null>
  private String _name;	// 订购会标题  STR(200)<null>
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Byte _status;	// 供应商场地 <OrderMeetingExhibitionStatus>  BYTE
	// FAILED:0,未通过
	// PASS:1,通过
	// DELETE:2,删除
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeetingExhibition init(){
		super.init();
    _address=null;	// 展厅地址  STR(200)
    _name=null;	// 订购会标题  STR(200)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
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
