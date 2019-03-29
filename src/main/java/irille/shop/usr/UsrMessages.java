package irille.shop.usr;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.*;

/**
 * 站内信
 *
 * @author yingjianhua
 */
public class UsrMessages extends BeanInt<UsrMessages> {

  public static final Tb TB = new Tb(UsrMessages.class, "站内信").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    SENDUSER(SYS.INT, "发件人"), // 发送者ID
    //        REUSERID(UsrPurchase.fldOutKey()), //接收者ID
    RECIVER(SYS.INT, "收件人"), // 接收者ID
    TITLE(SYS.STR__100, "标题"),
    CONTENT(SYS.TEXT__200_NULL, "内容"),
    REPLY(SYS.TEXT__200_NULL, "回复"),
    STATUS(TB.crt(OMessageStaus.DEFAULT)),
    TYPE(TB.crt(OMessageType.DEFAULT)),
    SEND_TIME(SYS.DATE_TIME, "发送时间"),
    READ_TIME(SYS.DATE_TIME__NULL, "阅读时间"),
    REPLY_TIME(SYS.DATE_TIME__NULL, "回复时间"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

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

  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _senduser; // 发件人  INT
  private Integer _reciver; // 收件人  INT
  private String _title; // 标题  STR(100)
  private String _content; // 内容  TEXT(200)<null>
  private String _reply; // 回复  TEXT(200)<null>
  private Byte _status; // 阅读状态 <OMessageStaus>  BYTE
  // READ:1,已读
  // UNREAD:0,未读
  private Byte _type; // 消息类别 <OMessageType>  BYTE
  // SYS_MESSAGE:1,系统消息
  // USER_MESSAGE:0,用户消息
  private Date _sendTime; // 发送时间  TIME
  private Date _readTime; // 阅读时间  TIME<null>
  private Date _replyTime; // 回复时间  TIME<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrMessages init() {
    super.init();
    _senduser = 0; // 发件人  INT
    _reciver = 0; // 收件人  INT
    _title = null; // 标题  STR(100)
    _content = null; // 内容  TEXT(200)
    _reply = null; // 回复  TEXT(200)
    _status = OMessageStaus.DEFAULT.getLine().getKey(); // 阅读状态 <OMessageStaus>  BYTE
    _type = OMessageType.DEFAULT.getLine().getKey(); // 消息类别 <OMessageType>  BYTE
    _sendTime = Env.getTranBeginTime(); // 发送时间  TIME
    _readTime = null; // 阅读时间  TIME
    _replyTime = null; // 回复时间  TIME
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Integer getSenduser() {
    return _senduser;
  }

  public void setSenduser(Integer senduser) {
    _senduser = senduser;
  }

  public Integer getReciver() {
    return _reciver;
  }

  public void setReciver(Integer reciver) {
    _reciver = reciver;
  }

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public String getContent() {
    return _content;
  }

  public void setContent(String content) {
    _content = content;
  }

  public String getReply() {
    return _reply;
  }

  public void setReply(String reply) {
    _reply = reply;
  }

  public Byte getStatus() {
    return _status;
  }

  public void setStatus(Byte status) {
    _status = status;
  }

  public OMessageStaus gtStatus() {
    return (OMessageStaus) (OMessageStaus.UNREAD.getLine().get(_status));
  }

  public void stStatus(OMessageStaus status) {
    _status = status.getLine().getKey();
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OMessageType gtType() {
    return (OMessageType) (OMessageType.USER_MESSAGE.getLine().get(_type));
  }

  public void stType(OMessageType type) {
    _type = type.getLine().getKey();
  }

  public Date getSendTime() {
    return _sendTime;
  }

  public void setSendTime(Date sendTime) {
    _sendTime = sendTime;
  }

  public Date getReadTime() {
    return _readTime;
  }

  public void setReadTime(Date readTime) {
    _readTime = readTime;
  }

  public Date getReplyTime() {
    return _replyTime;
  }

  public void setReplyTime(Date replyTime) {
    _replyTime = replyTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
  public enum OMessageStaus implements IEnumOpt { // @formatter:off
    READ(1, "已读"),
    UNREAD(0, "未读");
    public static final String NAME = "阅读状态";
    public static final OMessageStaus DEFAULT = UNREAD; // 定义缺省值
    private EnumLine _line;

    private OMessageStaus(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    @Override
    public EnumLine getLine() {
      return _line;
    }
  }

  public enum OMessageType implements IEnumOpt { // @formatter:off
    SYS_MESSAGE(1, "系统消息"),
    USER_MESSAGE(0, "用户消息");
    public static final String NAME = "消息类别";
    public static final OMessageType DEFAULT = USER_MESSAGE; // 定义缺省值
    private EnumLine _line;

    private OMessageType(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    @Override
    public EnumLine getLine() {
      return _line;
    }
  }
}
