package irille.Entity.pm;

import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

/**
 * 站内信模板
 *
 * @author liyichao
 */
public class PMTemplate extends BeanInt<PMTemplate> {

  public static final Tb TB = new Tb(PMTemplate.class, "站内信模板").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),

    RCVR_TYPE(TB.crt(PM.ORCVRType.DEFAULT)), // 收件人类型
    TITLE(SYS.TEXT__200_NULL, "模板标题"),
    MAIL_TITLE(SYS.TEXT__200_NULL, "邮件标题"),
    PM_CONTENT(SYS.TEXT__20000_NULL, "站内信模板内容"),
    MAIL_CONTENT(SYS.TEXT__20000_NULL, "邮件模板内容"),
    TYPE(TB.crt(PM.OTempType.DEFAULT)), // 模板类型
    EMAIL_STATUS(SYS.YN), // 邮件启用状态
    PM_STATUS(SYS.YN), // 站内信启用状态
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

    public static final Index IDX_TYPE_RCVR_TYPE =
        TB.addIndex("type_rcvr_type", true, T.TYPE, T.RCVR_TYPE);
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
  private Byte _rcvrType; // 收件人类型 <ORCVRType>  BYTE
  // ALL:-1,所有
  // SUPPLIER:1,供应商
  // PURCHASE:0,采购商
  private String _title; // 模板标题  TEXT(200)<null>
  private String _mailTitle; // 邮件标题  TEXT(200)<null>
  private String _pmContent; // 站内信模板内容  TEXT(200)<null>
  private String _mailContent; // 邮件模板内容  TEXT(200)<null>
  private Byte _type; // 收件人类型 <OTempType>  BYTE
  // UNSET:-1,
  // REGISTER_SUPPLIER:0,邮件验证
  // SHOP_APPR:1,店铺审核通知
  // ADVERTISEMENT_SUPPLIER:2,广告推送
  // SVS_WRITE_NOTICE:3,SVS填写通知
  // SVS_APPR_NOTICE:4,SVS审核通知
  // PROD_APPR_NOTICE:5,产品审核通知
  // O2O_PROD_APPR_NOTICE:22,O2O产品审核通知
  // ACTIVITY_NOTICE:6,活动提醒
  // O2O_STOCK:7,O2O批发库存状态提醒
  // O2O_ORDER:8,O2O批发订单状态提醒
  // RFQ_REPLY:9,RFQ报价回复提醒
  // INQUIRY_NOTICE_SUPPLIER:10,询盘提醒
  // CONTACT_INFO_NOTICE_SUPPLIER:11,联系人信息提醒
  // ORDER_STATUS_NOTICE_SUPPLIER:12,订单状态提醒
  // O2O_ACTIVITY_NOTICE:24,O2O活动提醒
  // REGISTER_PURCHASE:13,邮件验证
  // SYSTEM_NOTICE:14,系统提醒
  // ADVERTISEMENT_PURCHASE:15,广告推送
  // RFQ_INFO_NOTICE:16,RFQ信息提醒
  private Byte _emailStatus; // 是否 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Byte _pmStatus; // 是否 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PMTemplate init() {
    super.init();
    _rcvrType = ORCVRType.DEFAULT.getLine().getKey(); // 收件人类型 <ORCVRType>  BYTE
    _title = null; // 模板标题  TEXT(200)
    _mailTitle = null; // 邮件标题  TEXT(200)
    _pmContent = null; // 站内信模板内容  TEXT(200)
    _mailContent = null; // 邮件模板内容  TEXT(200)
    _type = OTempType.DEFAULT.getLine().getKey(); // 收件人类型 <OTempType>  BYTE
    _emailStatus = OYn.DEFAULT.getLine().getKey(); // 是否 <OYn>  BYTE
    _pmStatus = OYn.DEFAULT.getLine().getKey(); // 是否 <OYn>  BYTE
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static PMTemplate loadUniqueType_rcvr_type(boolean lockFlag, Byte type, Byte rcvrType) {
    return (PMTemplate) loadUnique(T.IDX_TYPE_RCVR_TYPE, lockFlag, type, rcvrType);
  }

  public static PMTemplate chkUniqueType_rcvr_type(boolean lockFlag, Byte type, Byte rcvrType) {
    return (PMTemplate) chkUnique(T.IDX_TYPE_RCVR_TYPE, lockFlag, type, rcvrType);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Byte getRcvrType() {
    return _rcvrType;
  }

  public void setRcvrType(Byte rcvrType) {
    _rcvrType = rcvrType;
  }

  public ORCVRType gtRcvrType() {
    return (ORCVRType) (ORCVRType.ALL.getLine().get(_rcvrType));
  }

  public void stRcvrType(ORCVRType rcvrType) {
    _rcvrType = rcvrType.getLine().getKey();
  }

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public String getMailTitle() {
    return _mailTitle;
  }

  public void setMailTitle(String mailTitle) {
    _mailTitle = mailTitle;
  }

  public String getPmContent() {
    return _pmContent;
  }

  public void setPmContent(String pmContent) {
    _pmContent = pmContent;
  }

  public String getMailContent() {
    return _mailContent;
  }

  public void setMailContent(String mailContent) {
    _mailContent = mailContent;
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OTempType gtType() {
    return (OTempType) (OTempType.UNSET.getLine().get(_type));
  }

  public void stType(OTempType type) {
    _type = type.getLine().getKey();
  }

  public Byte getEmailStatus() {
    return _emailStatus;
  }

  public void setEmailStatus(Byte emailStatus) {
    _emailStatus = emailStatus;
  }

  public Boolean gtEmailStatus() {
    return byteToBoolean(_emailStatus);
  }

  public void stEmailStatus(Boolean emailStatus) {
    _emailStatus = booleanToByte(emailStatus);
  }

  public Byte getPmStatus() {
    return _pmStatus;
  }

  public void setPmStatus(Byte pmStatus) {
    _pmStatus = pmStatus;
  }

  public Boolean gtPmStatus() {
    return byteToBoolean(_pmStatus);
  }

  public void stPmStatus(Boolean pmStatus) {
    _pmStatus = booleanToByte(pmStatus);
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
