// Created on 2005-10-20
package irille.shop.usr;

import irille.pub.Log;
import irille.pub.bean.BeanStr;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class UsrAccess extends BeanStr<UsrAccess> {
  private static final Log LOG = new Log(UsrAccess.class);

  public static final Tb TB = new Tb(UsrAccess.class, "用户访问功能").setAutoLocal();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtStrPkey().setLen(50)), // 访问功能 如:usr_UsrAccess_list
    MODULE(SYS.STR__100, "模块"), // 模块 如：usr
    CONTROLLER(SYS.STR__100, "控制器"), // 控制器 如：用户访问功能
    NAME(SYS.NAME__100), // 名称 如：列表
    SORT(SYS.SORT__INT), // 排序
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    private Fld _fld;

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
      _fld = TB.add(fld);
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
  private String _pkey; // 编号  STR(50)
  private String _module; // 模块  STR(100)
  private String _controller; // 控制器  STR(100)
  private String _name; // 名称  STR(100)
  private Integer _sort; // 排序号  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrAccess init() {
    super.init();
    _module = null; // 模块  STR(100)
    _controller = null; // 控制器  STR(100)
    _name = null; // 名称  STR(100)
    _sort = 0; // 排序号  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public String getPkey() {
    return _pkey;
  }

  public void setPkey(String pkey) {
    _pkey = pkey;
  }

  public String getModule() {
    return _module;
  }

  public void setModule(String module) {
    _module = module;
  }

  public String getController() {
    return _controller;
  }

  public void setController(String controller) {
    _controller = controller;
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public Integer getSort() {
    return _sort;
  }

  public void setSort(Integer sort) {
    _sort = sort;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
