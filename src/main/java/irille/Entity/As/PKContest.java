package irille.Entity.As;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class PKContest extends BeanInt<PKContest> {
  public static final Tb TB = new Tb(PKContest.class, "pk大赛").setAutoIncrement().addActList();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    NAME(SYS.STR__100, "名字"),
    TEL(SYS.STR__20, "电话"),
    EMAIL(SYS.STR__100, "邮箱"),
    COMPANYNAME(SYS.STR__100, "公司名称"),
    ROW_VERSION(SYS.ROW_VERSION),
    ;
    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<

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
  private String _name; // 名字  STR(100)
  private String _tel; // 电话  STR(20)
  private String _email; // 邮箱  STR(100)
  private String _companyname; // 公司名称  STR(100)
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PKContest init() {
    super.init();
    _name = null; // 名字  STR(100)
    _tel = null; // 电话  STR(20)
    _email = null; // 邮箱  STR(100)
    _companyname = null; // 公司名称  STR(100)
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

  public String getCompanyname() {
    return _companyname;
  }

  public void setCompanyname(String companyname) {
    _companyname = companyname;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
  // @formatter:off
}
