package irille.Entity.RFQ;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.Config.Attribute;
import irille.Config.Variable;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

@Variable(group = {OTempType.RFQ_REPLY},enumType=RFQConsultMessage.T.class,clazz=RFQConsultMessage.class,attributes = {
		@Attribute(name="RFQ内容",field="CONTENT",type=String.class),
		@Attribute(name="留言时间",field="SEND_TIME",type=Date.class)}
)
public class RFQConsultMessage extends BeanInt<RFQConsultMessage> {
	
	private static final long serialVersionUID = 2455737683431610037L;
	
	public static final Tb<?> TB = new Tb<>(RFQConsultMessage.class, "询盘留言").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {//@formatter:off
        PKEY(Tb.crtIntPkey()),
        UUID(Sys.T.STR__100, "uuid"),//uuid 唯一值
        CONTENT(Sys.T.JSON, "内容"),//留言内容
        TYPE(Tb.crt(RFQConsultMessageType.DEFAULT)),//消息类型
        SEND_TIME(Sys.T.TIME, "留言时间"), //留言时间
        RELATION(RFQConsultRelation.fldOutKey("relation", "询盘关联")),
        P2S(Sys.T.YN, "是采购商留言"),//true:采购商给供应商留言，false:供应商给采购商留言
        HAD_READ(Sys.T.YN),//是否已读
        ROW_VERSION(Sys.T.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        private Fld<?> _fld;

        private T(Class<?> clazz, String name, boolean... isnull) {
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

        private T(Fld<?> fld) {
            _fld = TB.add(fld, this);
        }

        public Fld<?> getFld() {
            return _fld;
        }
    }

    static { //在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
    }

    //@formatter:on
    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _uuid;	// uuid  STR(100)
  private String _content;	// 内容  JSONOBJECT
  private Byte _type;	// 消息体类型 <RFQConsultMessageType>  BYTE
	// TEXT:1,文本信息
	// IMAGE:2,图片信息
	// URL:3,链接类型
	// ALERT_URL:4,有消息提示的_链接类型
  private Date _sendTime;	// 留言时间  TIME
  private Integer _relation;	// 询盘关联 <表主键:RFQConsultRelation>  INT
  private Byte _p2s;	// 是采购商留言 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _hadRead;	// 是否 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQConsultMessage init(){
		super.init();
    _uuid=null;	// uuid  STR(100)
    _content=null;	// 内容  JSONOBJECT
    _type=RFQConsultMessageType.DEFAULT.getLine().getKey();	// 消息体类型 <RFQConsultMessageType>  BYTE
    _sendTime=null;	// 留言时间  TIME
    _relation=null;	// 询盘关联 <表主键:RFQConsultRelation>  INT
    _p2s=OYn.DEFAULT.getLine().getKey();	// 是采购商留言 <OYn>  BYTE
    _hadRead=OYn.DEFAULT.getLine().getKey();	// 是否 <OYn>  BYTE
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
  public String getUuid(){
    return _uuid;
  }
  public void setUuid(String uuid){
    _uuid=uuid;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public JSONObject gtContent() throws JSONException {
    return getContent()==null?new JSONObject():new JSONObject(getContent());
  }
  public void stContent(JSONObject content){
    setContent(content==null?null:content.toString());
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public RFQConsultMessageType gtType(){
    return (RFQConsultMessageType)(RFQConsultMessageType.TEXT.getLine().get(_type));
  }
  public void stType(RFQConsultMessageType type){
    _type=type.getLine().getKey();
  }
  public Date getSendTime(){
    return _sendTime;
  }
  public void setSendTime(Date sendTime){
    _sendTime=sendTime;
  }
  public Integer getRelation(){
    return _relation;
  }
  public void setRelation(Integer relation){
    _relation=relation;
  }
  public RFQConsultRelation gtRelation(){
    if(getRelation()==null)
      return null;
    return (RFQConsultRelation)get(RFQConsultRelation.class,getRelation());
  }
  public void stRelation(RFQConsultRelation relation){
    if(relation==null)
      setRelation(null);
    else
      setRelation(relation.getPkey());
  }
  public Byte getP2s(){
    return _p2s;
  }
  public void setP2s(Byte p2s){
    _p2s=p2s;
  }
  public Boolean gtP2s(){
    return byteToBoolean(_p2s);
  }
  public void stP2s(Boolean p2s){
    _p2s=booleanToByte(p2s);
  }
  public Byte getHadRead(){
    return _hadRead;
  }
  public void setHadRead(Byte hadRead){
    _hadRead=hadRead;
  }
  public Boolean gtHadRead(){
    return byteToBoolean(_hadRead);
  }
  public void stHadRead(Boolean hadRead){
    _hadRead=booleanToByte(hadRead);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
