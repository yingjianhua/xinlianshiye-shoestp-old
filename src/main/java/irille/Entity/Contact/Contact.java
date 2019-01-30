package irille.Entity.Contact;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class Contact extends BeanInt<Contact> {
    public static final Tb TB = new Tb(Contact.class, "联系人")
            .setAutoIncrement();

    public enum T implements IEnumFld {// @formatter:off
        PKEY(TB.crtIntPkey()),
        SUPPER_ID(UsrSupplier.fldOutKey()),
        PURCHASE_ID(UsrPurchase.fldOutKey()),
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
    private Integer _supperId;    // 供应商 <表主键:UsrSupplier>  INT
    private Integer _purchaseId;    // 采购商 <表主键:UsrPurchase>  INT
    private Short _rowVersion;    // 版本  SHORT

    @Override
    public Contact init() {
        super.init();
        _supperId = null;    // 供应商 <表主键:UsrSupplier>  INT
        _purchaseId = null;    // 采购商 <表主键:UsrPurchase>  INT
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

    public Integer getSupperId() {
        return _supperId;
    }

    public void setSupperId(Integer supperId) {
        _supperId = supperId;
    }

    public UsrSupplier gtSupperId() {
        if (getSupperId() == null)
            return null;
        return (UsrSupplier) get(UsrSupplier.class, getSupperId());
    }

    public void stSupperId(UsrSupplier supperId) {
        if (supperId == null)
            setSupperId(null);
        else
            setSupperId(supperId.getPkey());
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

    public Short getRowVersion() {
        return _rowVersion;
    }

    public void setRowVersion(Short rowVersion) {
        _rowVersion = rowVersion;
    }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
