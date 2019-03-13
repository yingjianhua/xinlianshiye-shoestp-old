package irille.shop.odr;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class OdrLog extends BeanInt<OdrLog> {
  public static final Tb TB = new Tb(OdrLog.class, "订单历史").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    MAIN(OdrOrder.fldOutKey().setName("订单号").setNull()),
    TIME(SYS.DATE_TIME, "日志时间"),
    CONTENT(SYS.STR__200, "日志内容"),
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
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Long _main; // 订单号 <表主键:OdrOrder>  LONG<null>
  private Date _time; // 日志时间  TIME
  private String _content; // 日志内容  STR(200)
  private Short _rowVersion; // 版本  SHORT

  @Override
  public OdrLog init() {
    super.init();
    _main = null; // 订单号 <表主键:OdrOrder>  LONG
    _time = Env.getTranBeginTime(); // 日志时间  TIME
    _content = null; // 日志内容  STR(200)
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

  public Long getMain() {
    return _main;
  }

  public void setMain(Long main) {
    _main = main;
  }

  public OdrOrder gtMain() {
    if (getMain() == null) return null;
    return (OdrOrder) get(OdrOrder.class, getMain());
  }

  public void stMain(OdrOrder main) {
    if (main == null) setMain(null);
    else setMain(main.getPkey());
  }

  public Date getTime() {
    return _time;
  }

  public void setTime(Date time) {
    _time = time;
  }

  public String getContent() {
    return _content;
  }

  public void setContent(String content) {
    _content = content;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
