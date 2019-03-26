package irille.Entity.Activity;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;

/** 活动页实体类 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/20 Time: 10:43 */
public class ActivityInfo extends BeanInt<ActivityInfo> {
  public static final Tb TB = new Tb(ActivityInfo.class, "报名管理").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    NAME(SYS.STR__100_NULL, "姓名"),
    COUNTRY(PltCountry.fldOutKey()), // 国家
    TEL(SYS.STR__20, "TEL"), // 电话
    EMAIL(SYS.STR__50, "Email"), // email
    INQUIRY(SYS.STR__2000_NULL), // 询盘信息
    CREATED_TIME(SYS.CREATED_DATE_TIME),
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
    ActivityInfo.T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
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
  private String _name; // 姓名  STR(100)<null>
  private Integer _country; // 国家管理 <表主键:PltCountry>  INT
  private String _tel; // TEL  STR(20)
  private String _email; // Email  STR(50)
  private String _inquiry; // 字符2000  STR(2000)<null>
  private Date _createdTime; // 建档时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public ActivityInfo init() {
    super.init();
    _name = null; // 姓名  STR(100)
    _country = null; // 国家管理 <表主键:PltCountry>  INT
    _tel = null; // TEL  STR(20)
    _email = null; // Email  STR(50)
    _inquiry = null; // 字符2000  STR(2000)
    _createdTime = Env.getTranBeginTime(); // 建档时间  TIME
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

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public Integer getCountry() {
    return _country;
  }

  public void setCountry(Integer country) {
    _country = country;
  }

  public PltCountry gtCountry() {
    if (getCountry() == null) return null;
    return (PltCountry) get(PltCountry.class, getCountry());
  }

  public void stCountry(PltCountry country) {
    if (country == null) setCountry(null);
    else setCountry(country.getPkey());
  }

  public String getTel() {
    return _tel;
  }

  public void setTel(String tel) {
    _tel = tel;
  }

  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }

  public String getInquiry() {
    return _inquiry;
  }

  public void setInquiry(String inquiry) {
    _inquiry = inquiry;
  }

  public Date getCreatedTime() {
    return _createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    _createdTime = createdTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
