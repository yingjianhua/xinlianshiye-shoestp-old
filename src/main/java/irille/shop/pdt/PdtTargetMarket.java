package irille.shop.pdt;

import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltCountry;

public class PdtTargetMarket extends BeanInt<PdtTargetMarket> {
  public static final Tb TB = new Tb(PdtTargetMarket.class, "供应商目标市场").setAutoIncrement();

  public enum T implements IEnumFld {
    PKEY(Tb.crtIntPkey()),
    PRODUCT(PdtProduct.fldOutKey()),
    COUNTRY(PltCountry.fldOutKey()),
    COUNTRY_NAME(SYS.MUILTI_LANGUAGE_NULL, "国家"),
    REGIONAL_MARKET(SYS.STR__20_NULL, "地区"),
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
  private Integer _product; // 产品 <表主键:PdtProduct>  INT
  private Integer _country; // 国家管理 <表主键:PltCountry>  INT
  private String _countryName; // 国家  JSONOBJECT<null>
  private String _regionalMarket; // 地区  STR(20)<null>
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PdtTargetMarket init() {
    super.init();
    _product = null; // 产品 <表主键:PdtProduct>  INT
    _country = null; // 国家管理 <表主键:PltCountry>  INT
    _countryName = null; // 国家  JSONOBJECT
    _regionalMarket = null; // 地区  STR(20)
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

  public Integer getProduct() {
    return _product;
  }

  public void setProduct(Integer product) {
    _product = product;
  }

  public PdtProduct gtProduct() {
    if (getProduct() == null) return null;
    return (PdtProduct) get(PdtProduct.class, getProduct());
  }

  public void stProduct(PdtProduct product) {
    if (product == null) setProduct(null);
    else setProduct(product.getPkey());
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

  public String getCountryName() {
    return _countryName;
  }

  public void setCountryName(String countryName) {
    _countryName = countryName;
  }

  public JSONObject gtCountryName() throws JSONException {
    return getCountryName() == null ? new JSONObject() : new JSONObject(getCountryName());
  }

  public void stCountryName(JSONObject countryName) {
    setCountryName(countryName == null ? null : countryName.toString());
  }

  public String getCountryName(FldLanguage.Language l) throws JSONException {
    return gtCountryName().has(l.name()) ? gtCountryName().getString(l.name()) : "";
  }

  public void setCountryName(String countryName, FldLanguage.Language l) throws JSONException {
    stCountryName(gtCountryName().put(l.name(), countryName));
  }

  public String getRegionalMarket() {
    return _regionalMarket;
  }

  public void setRegionalMarket(String regionalMarket) {
    _regionalMarket = regionalMarket;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
