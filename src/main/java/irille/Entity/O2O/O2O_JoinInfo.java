package irille.Entity.O2O;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 O2O产品 */
public class O2O_JoinInfo extends BeanInt<O2O_JoinInfo> {

  public static final Tb TB =
      new Tb(O2O_JoinInfo.class, "O2O负责人信息").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    NAME(SYS.STR__100_NULL, "负责人信息"),
    Tel(SYS.STR__100_NULL, "电话"),
    ACTIVITY(O2O_Activity.fldOutKey()), // O2O活动
    SUPPLIER(UsrSupplier.fldOutKey()), // 供应商
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    public static final Tb.Index IDX_ACTIVITY_SUPPLIER =
        TB.addIndex("activity_supplier", true, T.ACTIVITY, T.SUPPLIER);

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

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private String _name; // 负责人信息  STR(100)<null>
  private String _tel; // 电话  STR(100)<null>
  private Integer _activity; // O2O活动信息 <表主键:O2O_Activity>  INT
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public O2O_JoinInfo init() {
    super.init();
    _name = null; // 负责人信息  STR(100)
    _tel = null; // 电话  STR(100)
    _activity = null; // O2O活动信息 <表主键:O2O_Activity>  INT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static O2O_JoinInfo loadUniqueActivity_supplier(
      boolean lockFlag, Integer activity, Integer supplier) {
    return (O2O_JoinInfo) loadUnique(T.IDX_ACTIVITY_SUPPLIER, lockFlag, activity, supplier);
  }

  public static O2O_JoinInfo chkUniqueActivity_supplier(
      boolean lockFlag, Integer activity, Integer supplier) {
    return (O2O_JoinInfo) chkUnique(T.IDX_ACTIVITY_SUPPLIER, lockFlag, activity, supplier);
  }

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

  public Integer getActivity() {
    return _activity;
  }

  public void setActivity(Integer activity) {
    _activity = activity;
  }

  public O2O_Activity gtActivity() {
    if (getActivity() == null) return null;
    return (O2O_Activity) get(O2O_Activity.class, getActivity());
  }

  public void stActivity(O2O_Activity activity) {
    if (activity == null) setActivity(null);
    else setActivity(activity.getPkey());
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

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
