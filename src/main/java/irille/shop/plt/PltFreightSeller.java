package irille.shop.plt;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.plt.Plt.WeightType;
import irille.shop.usr.UsrSupplier;

public class PltFreightSeller extends BeanInt<PltFreightSeller> {
  public static final Tb TB =
      new Tb(PltFreightSeller.class, "商家运费管理").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    SUPPLIER(UsrSupplier.fldOutKey().setNull()),
    COMPANY(SYS.STR__100, "快递公司"),
    LOGO(SYS.IMG__400_NULL, "LOGO"),
    ENABLED(SYS.ENABLED), // 启用
    EXPRESS_URL(SYS.URL__NULL, "快递单号查询地址"),
    SORT(SYS.INT, "排序"),
    USE_INTERFACE(SYS.NY, "使用接口"),
    TYPE(TB.crt(Plt.WeightType.DEFAULT)),
    WEIGHT_MIN(SYS.INT, "最小重量/体积/件数"),
    WEIGHT_MAX(SYS.INT, "最大重量/体积/件数"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    public static final Index IDX_SUPPLIER_COMPANY =
        TB.addIndex("supplier_company", true, SUPPLIER, COMPANY);
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
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT<null>
  private String _company; // 快递公司  STR(100)
  private String _logo; // LOGO  STR(400)<null>
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private String _expressUrl; // 快递单号查询地址  STR(200)<null>
  private Integer _sort; // 排序  INT
  private Byte _useInterface; // 使用接口 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Byte _type; // 重量计算方式选择 <WeightType>  BYTE
  // WEIGHT:0,重量
  // VOLUME:1,体积
  // QUANTITY:2,件数
  private Integer _weightMin; // 最小重量/体积/件数  INT
  private Integer _weightMax; // 最大重量/体积/件数  INT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PltFreightSeller init() {
    super.init();
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _company = null; // 快递公司  STR(100)
    _logo = null; // LOGO  STR(400)
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _expressUrl = null; // 快递单号查询地址  STR(200)
    _sort = 0; // 排序  INT
    _useInterface = OYn.DEFAULT.getLine().getKey(); // 使用接口 <OYn>  BYTE
    _type = WeightType.DEFAULT.getLine().getKey(); // 重量计算方式选择 <WeightType>  BYTE
    _weightMin = 0; // 最小重量/体积/件数  INT
    _weightMax = 0; // 最大重量/体积/件数  INT
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static PltFreightSeller loadUniqueSupplier_company(
      boolean lockFlag, Integer supplier, String company) {
    return (PltFreightSeller) loadUnique(T.IDX_SUPPLIER_COMPANY, lockFlag, supplier, company);
  }

  public static PltFreightSeller chkUniqueSupplier_company(
      boolean lockFlag, Integer supplier, String company) {
    return (PltFreightSeller) chkUnique(T.IDX_SUPPLIER_COMPANY, lockFlag, supplier, company);
  }

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

  public String getCompany() {
    return _company;
  }

  public void setCompany(String company) {
    _company = company;
  }

  public String getLogo() {
    return _logo;
  }

  public void setLogo(String logo) {
    _logo = logo;
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

  public String getExpressUrl() {
    return _expressUrl;
  }

  public void setExpressUrl(String expressUrl) {
    _expressUrl = expressUrl;
  }

  public Integer getSort() {
    return _sort;
  }

  public void setSort(Integer sort) {
    _sort = sort;
  }

  public Byte getUseInterface() {
    return _useInterface;
  }

  public void setUseInterface(Byte useInterface) {
    _useInterface = useInterface;
  }

  public Boolean gtUseInterface() {
    return byteToBoolean(_useInterface);
  }

  public void stUseInterface(Boolean useInterface) {
    _useInterface = booleanToByte(useInterface);
  }

  public Byte getType() {
    return _type;
  }

  public void setType(Byte type) {
    _type = type;
  }

  public WeightType gtType() {
    return (WeightType) (WeightType.WEIGHT.getLine().get(_type));
  }

  public void stType(WeightType type) {
    _type = type.getLine().getKey();
  }

  public Integer getWeightMin() {
    return _weightMin;
  }

  public void setWeightMin(Integer weightMin) {
    _weightMin = weightMin;
  }

  public Integer getWeightMax() {
    return _weightMax;
  }

  public void setWeightMax(Integer weightMax) {
    _weightMax = weightMax;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
