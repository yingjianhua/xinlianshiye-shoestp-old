package irille.shop.plt;

import java.math.BigDecimal;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.BeanInt;
import irille.pub.tb.*;
import irille.pub.tb.Tb.Index;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 单据关联关系表，包括源--目的，主--明细，关联单据在此定义
 *
 * @author surface1
 */
public class PltPay extends BeanInt<PltPay> {
  public static final Tb TB = new Tb(PltPay.class, "支付设置").setAutoIncrement();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    MODE(TB.crt(OPay_Mode.DEFAULT)),
    SUPPLIER(UsrSupplier.fldOutKey()),
    POUNDAGE(SYS.RATE, "手续费"),
    EXTRA_COSTS(SYS.PRICE, "附加费用"),
    //        MIN_COST(SYS.PRICE, "最低价格限制"),
    //        MAX_COST(SYS.PRICE, "最高价格限制"),
    ENABLED(SYS.ENABLED),
    PAYSETTING(SYS.JSON),
    ROW_VERSION(SYS.ROW_VERSION)
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    public static final Index IDX_SUPPLIER_MODE =
        TB.addIndex("supplie_moder", false, SUPPLIER, MODE);
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

  // @formatter:on
  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Byte _mode; // 支付设置 <OPay_Mode>  BYTE
  // OFFINE_PAY:5,线下支付
  // TTPAY:10,TT支付
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private BigDecimal _poundage; // 手续费  DEC(8,4)
  private BigDecimal _extraCosts; // 附加费用  DEC(14,4)
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private String _paysetting; // JSON  JSONOBJECT
  private Short _rowVersion; // 版本  SHORT

  @Override
  public PltPay init() {
    super.init();
    _mode = OPay_Mode.DEFAULT.getLine().getKey(); // 支付设置 <OPay_Mode>  BYTE
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _poundage = ZERO; // 手续费  DEC(8,4)
    _extraCosts = ZERO; // 附加费用  DEC(14,4)
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _paysetting = null; // JSON  JSONOBJECT
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

  public Byte getMode() {
    return _mode;
  }

  public void setMode(Byte mode) {
    _mode = mode;
  }

  public OPay_Mode gtMode() {
    return (OPay_Mode) (OPay_Mode.OFFINE_PAY.getLine().get(_mode));
  }

  public void stMode(OPay_Mode mode) {
    _mode = mode.getLine().getKey();
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

  public BigDecimal getPoundage() {
    return _poundage;
  }

  public void setPoundage(BigDecimal poundage) {
    _poundage = poundage;
  }

  public BigDecimal getExtraCosts() {
    return _extraCosts;
  }

  public void setExtraCosts(BigDecimal extraCosts) {
    _extraCosts = extraCosts;
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

  public String getPaysetting() {
    return _paysetting;
  }

  public void setPaysetting(String paysetting) {
    _paysetting = paysetting;
  }

  public JSONObject gtPaysetting() throws JSONException {
    return getPaysetting() == null ? new JSONObject() : new JSONObject(getPaysetting());
  }

  public void stPaysetting(JSONObject paysetting) {
    setPaysetting(paysetting == null ? null : paysetting.toString());
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
  public enum OPay_Mode implements IEnumOpt {
    OFFINE_PAY(5, "线下支付"),
    TTPAY(10, "TT支付");
    public static final String NAME = "支付设置";
    public static final OPay_Mode DEFAULT = OFFINE_PAY;
    private EnumLine _line;

    private OPay_Mode(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }

  public enum Ooffline_Pay {
    ACCOUNT_NAME("account_name"), // 结算开户人

    BANK_ACCOUNT("bank_account"), // 银行账户
    //        DEPOS_IT_BANK("depos_it_bank"),//开户行
    DEPOS_IT_BANK_SUBBRANCH("depos_it_bank_subbranch"), // 开户支行
    DEPOS_IT_BANK_SUBBRANCH_ASCRIPTION("depos_it_bank_subbranch_ascription") // 开户支行归属地
  ;
    /**
     * 前端定义字段
     *
     * @author lijie@shoestp.cn @Description:
     * @date 2018/8/6 9:47
     */
    private String fldName;

    Ooffline_Pay(String fldName) {
      this.fldName = fldName;
    }

    public String getFldName() {
      return fldName;
    }
  }

  public enum TT_Pay {
    BENEFICIARYBANK("beneficiarybank"), // 收款行
    SWIFTCODE("swiftcode"), // swift 号
    BRNEFICIARY("brneficiary"), // 受益人 （公司名
    BANKADDRESS("bankaddress"), // 银行开户地址
    BENEFICIARYACCOUNT("beneficiaryaccount"); // 银行开户账户
    /**
     * 前端定义字段
     *
     * @author lijie@shoestp.cn @Description:
     * @date 2018/8/6 9:42
     */
    private String fldName;

    TT_Pay(String fldName) {
      this.fldName = fldName;
    }

    public String getFldName() {
      return fldName;
    }
  }

  @Override
  public String toString() {
    return "PltPay{"
        + "_pkey="
        + _pkey
        + ", _mode="
        + _mode
        + ", _supplier="
        + _supplier
        + ", _poundage="
        + _poundage
        + ", _extraCosts="
        + _extraCosts
        + ", _enabled="
        + _enabled
        + ", _paysetting='"
        + _paysetting
        + '\''
        + ", _rowVersion="
        + _rowVersion
        + "} "
        + super.toString();
  }
}
