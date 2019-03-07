package irille.Entity.pm;

import java.util.Date;

import irille.Entity.pm.PM.OMessageType;
import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PM.OTempType;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

/**
 * 	站内信
 * 	@author liyichao
 */
public class PMMessage extends BeanInt<PMMessage> {
	
	public static final Tb TB = new Tb(PMMessage.class, "站内信").setAutoIncrement().addActIUDL();
	
	public enum T implements IEnumFld {
		PKEY(TB.crtIntPkey()),
		MSG_TYPE(TB.crt(PM.OMessageType.DEFAULT)),//站内信类型
		RCVR_TYPE(TB.crt(PM.ORCVRType.DEFAULT)),//收件人类型
		TYPE(TB.crt(PM.OTempType.DEFAULT)),//消息类型
		RCVR(SYS.INT,"收件人"),
		COUNT(SYS.STR__30_NULL,"发送条数/接收数"),
		TITLE(SYS.TEXT__200_NULL, "标题"),
		CONTENT(SYS.TEXT__20000_NULL, "内容"),
		SEND_TIME(SYS.DATE_TIME__NULL, "发送时间"),
		ROW_VERSION(SYS.ROW_VERSION),
		 //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

        //        public static final Index IDX_CODE = TB.addIndex("code", true, T.);
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
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }


    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Byte _msgType;	// 消息类别 <OMessageType>  BYTE
	// PM:1,站内信
	// EMAIL:0,邮件
  private Byte _rcvrType;	// 收件人类型 <ORCVRType>  BYTE
	// ALL:-1,所有
	// SUPPLIER:1,供应商
	// PURCHASE:0,采购商
  private Byte _type;	// 收件人类型 <OTempType>  BYTE
	// UNSET:-1,
	// REGISTER_SUPPLIER:0,邮件验证
	// SHOP_APPR:1,店铺审核通知
	// ADVERTISEMENT_SUPPLIER:2,广告推送
	// SVS_WRITE_NOTICE:3,SVS填写通知
	// SVS_APPR_NOTICE:4,SVS审核通知
	// PROD_APPR_NOTICE:5,产品审核通知
	// ACTIVITY_NOTICE:6,活动提醒
	// O2O_STOCK:7,O2O批发库存状态提醒
	// O2O_ORDER:8,O2O批发订单状态提醒
	// RFQ_REPLY:9,RFQ报价回复提醒
	// INQUIRY_NOTICE_SUPPLIER:10,询盘提醒
	// CONTACT_INFO_NOTICE_SUPPLIER:11,联系人信息提醒
	// ORDER_STATUS_NOTICE_SUPPLIER:12,订单状态提醒
	// REGISTER_PURCHASE:13,邮件验证
	// SYSTEM_NOTICE:14,系统提醒
	// ADVERTISEMENT_PURCHASE:15,广告推送
	// RFQ_INFO_NOTICE:16,RFQ信息提醒
	// INQUIRY_NOTICE_PURCHASE:17,询盘提醒
	// CONTACT_INFO_NOTICE_PURCHASE:18,联系人信息提醒
  private Integer _rcvr;	// 收件人  INT
  private String _count;	// 发送条数/接收数  STR(30)<null>
  private String _title;	// 标题  TEXT(200)<null>
  private String _content;	// 内容  TEXT(200)<null>
  private Date _sendTime;	// 发送时间  TIME<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PMMessage init(){
		super.init();
    _msgType=OMessageType.DEFAULT.getLine().getKey();	// 消息类别 <OMessageType>  BYTE
    _rcvrType=ORCVRType.DEFAULT.getLine().getKey();	// 收件人类型 <ORCVRType>  BYTE
    _type=OTempType.DEFAULT.getLine().getKey();	// 收件人类型 <OTempType>  BYTE
    _rcvr=0;	// 收件人  INT
    _count=null;	// 发送条数/接收数  STR(30)
    _title=null;	// 标题  TEXT(200)
    _content=null;	// 内容  TEXT(200)
    _sendTime=null;	// 发送时间  TIME
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
  public Byte getMsgType(){
    return _msgType;
  }
  public void setMsgType(Byte msgType){
    _msgType=msgType;
  }
  public OMessageType gtMsgType(){
    return (OMessageType)(OMessageType.PM.getLine().get(_msgType));
  }
  public void stMsgType(OMessageType msgType){
    _msgType=msgType.getLine().getKey();
  }
  public Byte getRcvrType(){
    return _rcvrType;
  }
  public void setRcvrType(Byte rcvrType){
    _rcvrType=rcvrType;
  }
  public ORCVRType gtRcvrType(){
    return (ORCVRType)(ORCVRType.ALL.getLine().get(_rcvrType));
  }
  public void stRcvrType(ORCVRType rcvrType){
    _rcvrType=rcvrType.getLine().getKey();
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OTempType gtType(){
    return (OTempType)(OTempType.UNSET.getLine().get(_type));
  }
  public void stType(OTempType type){
    _type=type.getLine().getKey();
  }
  public Integer getRcvr(){
    return _rcvr;
  }
  public void setRcvr(Integer rcvr){
    _rcvr=rcvr;
  }
  public String getCount(){
    return _count;
  }
  public void setCount(String count){
    _count=count;
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public Date getSendTime(){
    return _sendTime;
  }
  public void setSendTime(Date sendTime){
    _sendTime=sendTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
    
	
}
