package irille.Entity.O2O;

import java.math.BigDecimal;
import java.util.Date;

import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 O2O产品 */
public class O2O_PrivateExpoPdt extends BeanInt<O2O_PrivateExpoPdt> {

  public static final Tb TB =
      new Tb(O2O_PrivateExpoPdt.class, "O2O_私人展会产品补充表").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    PDT_ID(PdtProduct.fldOutKey()),
    PRICE(SYS.AMT),
    MIN_OQ(SYS.INT),
    STATUS(Tb.crt(O2O_PrivateExpoPdtStatus.DEFAULT)),
    VERIFY_STATUS(Tb.crt(O2O_PrivateExpoPdtStatus.DEFAULT)),
    MESSAGE(SYS.STR__100_NULL),
    CREATE_TIME(SYS.UPDATED_DATE_TIME),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    public static final Tb.Index IDX_PDT_ID = TB.addIndex("pdt_Id", true, T.PDT_ID);
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
  private Integer _pdtId; // 产品 <表主键:PdtProduct>  INT
  private BigDecimal _price; // 金额  DEC(16,2)
  private Integer _minOq; // INT  INT
  private Byte _status; // O2O商品状态 <O2O_PrivateExpoPdtStatus>  BYTE
  // _DEFAULT:0,未审核
  // OFF:1,下架
  // ON:2,上架
  // PASS:3,审核通过
  // Failed:4,审核失败
  private Byte _verifyStatus; // O2O商品状态 <O2O_PrivateExpoPdtStatus>  BYTE
  // _DEFAULT:0,未审核
  // OFF:1,下架
  // ON:2,上架
  // PASS:3,审核通过
  // Failed:4,审核失败
  private String _message; // 字符100  STR(100)<null>
  private Date _createTime; // 更新时间  TIME
  private Short _rowVersion; // 版本  SHORT

  @Override
  public O2O_PrivateExpoPdt init() {
    super.init();
    _pdtId = null; // 产品 <表主键:PdtProduct>  INT
    _price = ZERO; // 金额  DEC(16,2)
    _minOq = 0; // INT  INT
    _status =
        O2O_PrivateExpoPdtStatus.DEFAULT
            .getLine()
            .getKey(); // O2O商品状态 <O2O_PrivateExpoPdtStatus>  BYTE
    _verifyStatus =
        O2O_PrivateExpoPdtStatus.DEFAULT
            .getLine()
            .getKey(); // O2O商品状态 <O2O_PrivateExpoPdtStatus>  BYTE
    _message = null; // 字符100  STR(100)
    _createTime = Env.getTranBeginTime(); // 更新时间  TIME
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static O2O_PrivateExpoPdt loadUniquePdt_Id(boolean lockFlag, Integer pdtId) {
    return (O2O_PrivateExpoPdt) loadUnique(T.IDX_PDT_ID, lockFlag, pdtId);
  }

  public static O2O_PrivateExpoPdt chkUniquePdt_Id(boolean lockFlag, Integer pdtId) {
    return (O2O_PrivateExpoPdt) chkUnique(T.IDX_PDT_ID, lockFlag, pdtId);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Integer getPdtId() {
    return _pdtId;
  }

  public void setPdtId(Integer pdtId) {
    _pdtId = pdtId;
  }

  public PdtProduct gtPdtId() {
    if (getPdtId() == null) return null;
    return (PdtProduct) get(PdtProduct.class, getPdtId());
  }

  public void stPdtId(PdtProduct pdtId) {
    if (pdtId == null) setPdtId(null);
    else setPdtId(pdtId.getPkey());
  }

  public BigDecimal getPrice() {
    return _price;
  }

  public void setPrice(BigDecimal price) {
    _price = price;
  }

  public Integer getMinOq() {
    return _minOq;
  }

  public void setMinOq(Integer minOq) {
    _minOq = minOq;
  }

  public Byte getStatus() {
    return _status;
  }

  public void setStatus(Byte status) {
    _status = status;
  }

  public O2O_PrivateExpoPdtStatus gtStatus() {
    return (O2O_PrivateExpoPdtStatus) (O2O_PrivateExpoPdtStatus._DEFAULT.getLine().get(_status));
  }

  public void stStatus(O2O_PrivateExpoPdtStatus status) {
    _status = status.getLine().getKey();
  }

  public Byte getVerifyStatus() {
    return _verifyStatus;
  }

  public void setVerifyStatus(Byte verifyStatus) {
    _verifyStatus = verifyStatus;
  }

  public O2O_PrivateExpoPdtStatus gtVerifyStatus() {
    return (O2O_PrivateExpoPdtStatus)
        (O2O_PrivateExpoPdtStatus._DEFAULT.getLine().get(_verifyStatus));
  }

  public void stVerifyStatus(O2O_PrivateExpoPdtStatus verifyStatus) {
    _verifyStatus = verifyStatus.getLine().getKey();
  }

  public String getMessage() {
    return _message;
  }

  public void setMessage(String message) {
    _message = message;
  }

  public Date getCreateTime() {
    return _createTime;
  }

  public void setCreateTime(Date createTime) {
    _createTime = createTime;
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
