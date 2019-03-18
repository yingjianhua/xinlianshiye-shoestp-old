package irille.shop.usr;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.Usr.OIMType;

public class UsrSupIm extends BeanInt<UsrSupIm> {
  public static final Tb TB = new Tb(UsrSupIm.class, "在线聊天").setAutoIncrement();

  public enum T implements IEnumFld {
    PKEY(Tb.crtIntPkey()),
    SUPPLIER(UsrSupplier.fldOutKey()),
    ENABLED(SYS.ENABLED),
    ACCOUNT(SYS.STR__40_NULL, "IM账号"),
    DEMO(SYS.STR__500, "IM代码"),
    TITLE(SYS.STR__20_NULL, "标题"),
    TYPE(Tb.crt(Usr.OIMType.DEFAULT)),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private String _account; // IM账号  STR(20)<null>
  private String _demo; // IM代码  STR(100)<null>
  private String _title; // 标题  STR(20)<null>
  private Byte _type; // IM类型 <OIMType>  BYTE
  // PHONE:0,手机
  // COMPUTER:1,电脑
  // DEVICE:2,手机或电脑
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrSupIm init() {
    super.init();
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _account = null; // IM账号  STR(20)
    _demo = null; // IM代码  STR(100)
    _title = null; // 标题  STR(20)
    _type = OIMType.DEFAULT.getLine().getKey(); // IM类型 <OIMType>  BYTE
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

  public Integer getSupplier() {
    return _supplier;
  }

  public void setSupplier(Integer supplier) {
    _supplier = supplier;
  }

  public UsrSupplier gtSupplier() {
    if (getSupplier() == null) return null;
    return (UsrSupplier) get(UsrSupplier.class, getSupplier());
  }

  public void stSupplier(UsrSupplier supplier) {
    if (supplier == null) setSupplier(null);
    else setSupplier(supplier.getPkey());
  }

  public Byte getEnabled() {
    return _enabled;
  }

  public void setEnabled(Byte enabled) {
    _enabled = enabled;
  }

  public Boolean gtEnabled() {
    return byteToBoolean(_enabled);
  }

  public void stEnabled(Boolean enabled) {
    _enabled = booleanToByte(enabled);
  }

  public String getAccount() {
    return _account;
  }

  public void setAccount(String account) {
    _account = account;
  }

  public String getDemo() {
    return _demo;
  }

  public void setDemo(String demo) {
    _demo = demo;
  }

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public OIMType gtType() {
    return (OIMType) (OIMType.DEVICE.getLine().get(_type));
  }

  public void stType(OIMType type) {
    _type = type.getLine().getKey();
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
