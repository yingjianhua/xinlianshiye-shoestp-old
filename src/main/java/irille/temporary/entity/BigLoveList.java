package irille.temporary.entity;

import irille.pub.bean.BeanLong;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class BigLoveList extends BeanLong<BigLoveList> {
  public static final Tb<?> TB = new Tb(BigLoveList.class, "订单管理").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtLongPkey()),
    FROM_NAME(SYS.STR__100_NULL, "地名"),
    NAME(SYS.STR__100_NULL, "名称"),
    REG_TIME(SYS.STR__100_NULL, "注册时间"),
    OPERATION(SYS.STR__100_NULL, "操作"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;

    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
  private Long _pkey; // 编号  LONG
  private String _fromName; // 地名  STR(100)<null>
  private String _name; // 名称  STR(100)<null>
  private String _regTime; // 注册时间  STR(100)<null>
  private String _operation; // 操作  STR(100)<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public BigLoveList init() {
    super.init();
    _fromName = null; // 地名  STR(100)
    _name = null; // 名称  STR(100)
    _regTime = null; // 注册时间  STR(100)
    _operation = null; // 操作  STR(100)
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public Long getPkey() {
    return _pkey;
  }

  public void setPkey(Long pkey) {
    _pkey = pkey;
  }

  public String getFromName() {
    return _fromName;
  }

  public void setFromName(String fromName) {
    _fromName = fromName;
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public String getRegTime() {
    return _regTime;
  }

  public void setRegTime(String regTime) {
    _regTime = regTime;
  }

  public String getOperation() {
    return _operation;
  }

  public void setOperation(String operation) {
    _operation = operation;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
