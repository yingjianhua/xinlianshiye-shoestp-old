package irille.Entity.RFQ;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultRelation extends BeanInt<RFQConsultRelation> {
    public static final Tb TB = new Tb(RFQConsultRelation.class, "询盘关联表")
            .setAutoIncrement();

    public enum T implements IEnumFld {// @formatter:off
        PKEY(TB.crtIntPkey()),
        CONSULT(RFQConsult.fldOutKey("consult", "询盘")), //
        SUPPLIER_ID(UsrSupplier.fldOutKey().setName("供应商")),
        PURCHASE_ID(UsrPurchase.fldOutKey().setName("采购商")),
        FAVORITE(SYS.YN, "是否添加FLAG"),
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
        T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
    }

    public static Fld fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name).setType(null);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
    //实例变量定义-----------------------------------------
    private Integer _pkey;    // 编号  INT
    private Integer _consult;    // 询盘 <表主键:RFQConsult>  INT
    private Integer _supplierId;    // 供应商 <表主键:UsrSupplier>  INT
    private Integer _purchaseId;    // 采购商 <表主键:UsrPurchase>  INT
    private Byte _favorite;    // 是否添加FLAG <OYn>  BYTE
    // YES:1,是
    // NO:0,否
    private Short _rowVersion;    // 版本  SHORT

    @Override
    public RFQConsultRelation init() {
        super.init();
        _consult = null;    // 询盘 <表主键:RFQConsult>  INT
        _supplierId = null;    // 供应商 <表主键:UsrSupplier>  INT
        _purchaseId = null;    // 采购商 <表主键:UsrPurchase>  INT
        _favorite = Sys.OYn.DEFAULT.getLine().getKey();    // 是否添加FLAG <OYn>  BYTE
        _rowVersion = 0;    // 版本  SHORT
        return this;
    }

    //方法----------------------------------------------
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

    public RFQConsult gtConsult() {
        if (getConsult() == null)
            return null;
        return (RFQConsult) get(RFQConsult.class, getConsult());
    }

    public void stConsult(RFQConsult consult) {
        if (consult == null)
            setConsult(null);
        else
            setConsult(consult.getPkey());
    }

    public Integer getSupplierId() {
        return _supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        _supplierId = supplierId;
    }

    public UsrSupplier gtSupplierId() {
        if (getSupplierId() == null)
            return null;
        return (UsrSupplier) get(UsrSupplier.class, getSupplierId());
    }

    public void stSupplierId(UsrSupplier supplierId) {
        if (supplierId == null)
            setSupplierId(null);
        else
            setSupplierId(supplierId.getPkey());
    }

    public Integer getPurchaseId() {
        return _purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        _purchaseId = purchaseId;
    }

    public UsrPurchase gtPurchaseId() {
        if (getPurchaseId() == null)
            return null;
        return (UsrPurchase) get(UsrPurchase.class, getPurchaseId());
    }

    public void stPurchaseId(UsrPurchase purchaseId) {
        if (purchaseId == null)
            setPurchaseId(null);
        else
            setPurchaseId(purchaseId.getPkey());
    }

    public Byte getFavorite() {
        return _favorite;
    }

    public void setFavorite(Byte favorite) {
        _favorite = favorite;
    }

    public Boolean gtFavorite() {
        return byteToBoolean(_favorite);
    }

    public void stFavorite(Boolean favorite) {
        _favorite = booleanToByte(favorite);
    }

    public Short getRowVersion() {
        return _rowVersion;
    }

    public void setRowVersion(Short rowVersion) {
        _rowVersion = rowVersion;
    }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
