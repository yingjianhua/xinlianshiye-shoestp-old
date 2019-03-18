package irille.shop.usr;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class UsrConsultRelation extends BeanInt<UsrConsultRelation> {
  public static final Tb TB = new Tb(UsrConsultRelation.class, "询盘关联表").setAutoIncrement();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    CONSULT(UsrConsult.fldOutKey("consult", "询盘")), //
    SUPPLIER(UsrSupplier.fldOutKey().setName("供应商")),
    P_TO_S_NEW_MSG(SYS.YN, "采购商发送新消息"), // 1:有,0:没有
    S_TO_P_NEW_MSG(SYS.YN, "供应商发送新消息"), // 1:有,0:没有
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    public static final Index IDX_CONSULT_SUPPLIER =
        TB.addIndex("consult_supplier", true, T.CONSULT, T.SUPPLIER);
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
    return Tb.crtOutKey(TB, code, name).setType(null);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Integer _consult; // 询盘 <表主键:UsrConsult>  INT
  private Integer _supplier; // 供应商 <表主键:UsrSupplier>  INT
  private Byte _pToSNewMsg; // 采购商发送新消息 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Byte _sToPNewMsg; // 供应商发送新消息 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Short _rowVersion; // 版本  SHORT

  @Override
  public UsrConsultRelation init() {
    super.init();
    _consult = null; // 询盘 <表主键:UsrConsult>  INT
    _supplier = null; // 供应商 <表主键:UsrSupplier>  INT
    _pToSNewMsg = OYn.DEFAULT.getLine().getKey(); // 采购商发送新消息 <OYn>  BYTE
    _sToPNewMsg = OYn.DEFAULT.getLine().getKey(); // 供应商发送新消息 <OYn>  BYTE
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public static UsrConsultRelation loadUniqueConsult_supplier(
      boolean lockFlag, Integer consult, Integer supplier) {
    return (UsrConsultRelation) loadUnique(T.IDX_CONSULT_SUPPLIER, lockFlag, consult, supplier);
  }

  public static UsrConsultRelation chkUniqueConsult_supplier(
      boolean lockFlag, Integer consult, Integer supplier) {
    return (UsrConsultRelation) chkUnique(T.IDX_CONSULT_SUPPLIER, lockFlag, consult, supplier);
  }

  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Integer getConsult() {
    return _consult;
  }

  public void setConsult(Integer consult) {
    _consult = consult;
  }

  public UsrConsult gtConsult() {
    if (getConsult() == null) return null;
    return (UsrConsult) get(UsrConsult.class, getConsult());
  }

  public void stConsult(UsrConsult consult) {
    if (consult == null) setConsult(null);
    else setConsult(consult.getPkey());
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

  public Byte getPToSNewMsg() {
    return _pToSNewMsg;
  }

  public void setPToSNewMsg(Byte pToSNewMsg) {
    _pToSNewMsg = pToSNewMsg;
  }

  public Boolean gtPToSNewMsg() {
    return byteToBoolean(_pToSNewMsg);
  }

  public void stPToSNewMsg(Boolean pToSNewMsg) {
    _pToSNewMsg = booleanToByte(pToSNewMsg);
  }

  public Byte getSToPNewMsg() {
    return _sToPNewMsg;
  }

  public void setSToPNewMsg(Byte sToPNewMsg) {
    _sToPNewMsg = sToPNewMsg;
  }

  public Boolean gtSToPNewMsg() {
    return byteToBoolean(_sToPNewMsg);
  }

  public void stSToPNewMsg(Boolean sToPNewMsg) {
    _sToPNewMsg = booleanToByte(sToPNewMsg);
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
