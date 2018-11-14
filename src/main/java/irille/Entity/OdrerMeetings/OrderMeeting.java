package irille.Entity.OdrerMeetings;

import irille.Entity.OdrerMeetings.Enums.OrdrerMettingAuditStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrSupplier;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public class OrderMeeting extends BeanInt<OrderMeeting> {

  public static final Tb TB = new Tb(OrderMeeting.class, "订购会信息").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    SUPPLIERID(UsrSupplier.fldOutKey()),
    NAME(SYS.STR__200_NULL, "订购会标题"),
    STATUS(TB.crt(OrdrerMettingAuditStatus.DEFAULT)),
    EXHIBITION(OrderMeetingExhibition.fldOutKey()),
    CUSTOM_EXHIBITION(SYS.STR__200_NULL),
    COUNTRY(PltCountry.fldOutKey()),
    LOGO(SYS.STR__100_NULL),
    START_TIME(SYS.TIME),
    END_TIME(SYS.TIME),
    MAILADDRESS(SYS.JSON),
    MAILAFULLDDRESS(SYS.STR__200),
    POSTCODE(SYS.STR__20),
    MAILNAME(SYS.STR__20),
    MAILTEL(SYS.STR__20),
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
    OrderMeeting.T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
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
  private Integer _supplierid;	// 供应商 <表主键:UsrSupplier>  INT
  private String _name;	// 订购会标题  STR(200)<null>
  private Byte _status;	// 订购会状态 <OrdrerMettingAuditStatus>  BYTE
	// PENDING:0,待审核
	// VERIFYING:1,审核中
	// PROCEEDING:2,通过
	// FAILED:3,未通过
	// TOBEGIN:4,即将开始
	// SUSPEND:6,暂停
  private Integer _exhibition;	// 订购会-会场信息 <表主键:OrderMeetingExhibition>  INT
  private String _customExhibition;	// 字符200  STR(200)<null>
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private String _logo;	// 字符100  STR(100)<null>
  private Date _startTime;	// 时间  TIME
  private Date _endTime;	// 时间  TIME
  private String _mailaddress;	// JSON  JSONOBJECT
  private String _mailafullddress;	// 字符200  STR(200)
  private String _postcode;	// 字符20  STR(20)
  private String _mailname;	// 字符20  STR(20)
  private String _mailtel;	// 字符20  STR(20)
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public OrderMeeting init(){
		super.init();
    _supplierid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _name=null;	// 订购会标题  STR(200)
    _status=OrdrerMettingAuditStatus.DEFAULT.getLine().getKey();	// 订购会状态 <OrdrerMettingAuditStatus>  BYTE
    _exhibition=null;	// 订购会-会场信息 <表主键:OrderMeetingExhibition>  INT
    _customExhibition=null;	// 字符200  STR(200)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _logo=null;	// 字符100  STR(100)
    _startTime=null;	// 时间  TIME
    _endTime=null;	// 时间  TIME
    _mailaddress=null;	// JSON  JSONOBJECT
    _mailafullddress=null;	// 字符200  STR(200)
    _postcode=null;	// 字符20  STR(20)
    _mailname=null;	// 字符20  STR(20)
    _mailtel=null;	// 字符20  STR(20)
    _updatedTime=Env.getTranBeginTime();	// 更新时间  TIME
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OrdrerMettingAuditStatus gtStatus(){
    return (OrdrerMettingAuditStatus)(OrdrerMettingAuditStatus.PENDING.getLine().get(_status));
  }
  public void stStatus(OrdrerMettingAuditStatus status){
    _status=status.getLine().getKey();
  }
  public Integer getExhibition(){
    return _exhibition;
  }
  public void setExhibition(Integer exhibition){
    _exhibition=exhibition;
  }
  public OrderMeetingExhibition gtExhibition(){
    if(getExhibition()==null)
      return null;
    return (OrderMeetingExhibition)get(OrderMeetingExhibition.class,getExhibition());
  }
  public void stExhibition(OrderMeetingExhibition exhibition){
    if(exhibition==null)
      setExhibition(null);
    else
      setExhibition(exhibition.getPkey());
  }
  public String getCustomExhibition(){
    return _customExhibition;
  }
  public void setCustomExhibition(String customExhibition){
    _customExhibition=customExhibition;
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
  public String getLogo(){
    return _logo;
  }
  public void setLogo(String logo){
    _logo=logo;
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
  public String getMailaddress(){
    return _mailaddress;
  }
  public void setMailaddress(String mailaddress){
    _mailaddress=mailaddress;
  }
  public JSONObject gtMailaddress() throws JSONException {
    return getMailaddress()==null?new JSONObject():new JSONObject(getMailaddress());
  }
  public void stMailaddress(JSONObject mailaddress){
    setMailaddress(mailaddress==null?null:mailaddress.toString());
  }
  public String getMailafullddress(){
    return _mailafullddress;
  }
  public void setMailafullddress(String mailafullddress){
    _mailafullddress=mailafullddress;
  }
  public String getPostcode(){
    return _postcode;
  }
  public void setPostcode(String postcode){
    _postcode=postcode;
  }
  public String getMailname(){
    return _mailname;
  }
  public void setMailname(String mailname){
    _mailname=mailname;
  }
  public String getMailtel(){
    return _mailtel;
  }
  public void setMailtel(String mailtel){
    _mailtel=mailtel;
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
