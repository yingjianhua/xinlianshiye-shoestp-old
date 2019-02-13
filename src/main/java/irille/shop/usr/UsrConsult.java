package irille.shop.usr;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;

import java.util.Date;

public class UsrConsult extends BeanInt<UsrConsult> {
    private static final long serialVersionUID = 7524946206877858631L;
    public static final Tb<?> TB = new Tb<>(UsrConsult.class, "询盘").setAutoIncrement().addActList().addActDel();

    public enum T implements IEnumFld {//@formatter:off
        PKEY(Tb.crtIntPkey()),
        TITLE(Sys.T.STR__200, "标题"),//一般为产品名字
        IMAGE(Sys.T.IMG_MULTI__2000_NULL),
        COUNTRY(PltCountry.fldOutKey()),
        PRODUCT(PdtProduct.fldOutKey().setNull()),
        HAVE_NEW_MSG(Sys.T.YN, "有新消息"),//是否有新消息 1:有,0:没有
        CONTENT(Sys.T.STR__200_NULL, "内容"),
        IS_PUBLIC(Sys.T.YN, "是否为公共询盘"),
        COUNT(Sys.T.INT_PLUS_OR_ZERO, "剩余抢单次数"), //初始化次数为5
        QUANTITY(Sys.T.INT, "商品数量"),
        PURCHASE(UsrPurchase.fldOutKey().setName("采购商")),
        NAME(Sys.T.STR__20, "名字"), //采购商提供的名字
        EMAIL(Sys.T.EMAIL__NULL), //采购商提供的邮箱地址
        CREATE_TIME(Sys.T.DATE_TIME, "创建时间"),
        ROW_VERSION(Sys.T.ROW_VERSION),
        // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        private Fld<?> _fld;

        private T(Class<?> clazz, String name, boolean... isnull) {
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

        private T(Fld<?> fld) {
            _fld = TB.add(fld, this);
        }

        public Fld<?> getFld() {
            return _fld;
        }
    }

    static { // 在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
    }

    public static Fld<?> fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld<?> fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
    //实例变量定义-----------------------------------------
    private Integer _pkey;    // 编号  INT
    private String _title;    // 标题  STR(200)
    private String _image;    // 图片  STR(2000)<null>
    private Integer _country;    // 国家管理 <表主键:PltCountry>  INT
    private Integer _product;    // 产品 <表主键:PdtProduct>  INT<null>
    private Byte _haveNewMsg;    // 有新消息 <OYn>  BYTE
    // YES:1,是
    // NO:0,否
    private String _content;    // 内容  STR(200)<null>
    private Byte _isPublic;    // 是否为公共询盘 <OYn>  BYTE
    // YES:1,是
    // NO:0,否
    private Integer _count;    // 剩余抢单次数  INT
    private Integer _quantity;    // 商品数量  INT
    private Integer _purchase;    // 采购商 <表主键:UsrPurchase>  INT
    private String _name;    // 名字  STR(20)
    private String _email;    // 邮箱  STR(100)<null>
    private Date _createTime;    // 创建时间  TIME
    private Short _rowVersion;    // 版本  SHORT

    @Override
    public UsrConsult init() {
        super.init();
        _title = null;    // 标题  STR(200)
        _image = null;    // 图片  STR(2000)
        _country = null;    // 国家管理 <表主键:PltCountry>  INT
        _product = null;    // 产品 <表主键:PdtProduct>  INT
        _haveNewMsg = OYn.DEFAULT.getLine().getKey();    // 有新消息 <OYn>  BYTE
        _content = null;    // 内容  STR(200)
        _isPublic = OYn.DEFAULT.getLine().getKey();    // 是否为公共询盘 <OYn>  BYTE
        _count = 0;    // 剩余抢单次数  INT
        _quantity = 0;    // 商品数量  INT
        _purchase = null;    // 采购商 <表主键:UsrPurchase>  INT
        _name = null;    // 名字  STR(20)
        _email = null;    // 邮箱  STR(100)
        _createTime = Env.getTranBeginTime();    // 创建时间  TIME
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

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getImage() {
        return _image;
    }

    public void setImage(String image) {
        _image = image;
    }

    public Integer getCountry() {
        return _country;
    }

    public void setCountry(Integer country) {
        _country = country;
    }

    public PltCountry gtCountry() {
        if (getCountry() == null)
            return null;
        return (PltCountry) get(PltCountry.class, getCountry());
    }

    public void stCountry(PltCountry country) {
        if (country == null)
            setCountry(null);
        else
            setCountry(country.getPkey());
    }

    public Integer getProduct() {
        return _product;
    }

    public void setProduct(Integer product) {
        _product = product;
    }

    public PdtProduct gtProduct() {
        if (getProduct() == null)
            return null;
        return (PdtProduct) get(PdtProduct.class, getProduct());
    }

    public void stProduct(PdtProduct product) {
        if (product == null)
            setProduct(null);
        else
            setProduct(product.getPkey());
    }

    public Byte getHaveNewMsg() {
        return _haveNewMsg;
    }

    public void setHaveNewMsg(Byte haveNewMsg) {
        _haveNewMsg = haveNewMsg;
    }

    public Boolean gtHaveNewMsg() {
        return byteToBoolean(_haveNewMsg);
    }

    public void stHaveNewMsg(Boolean haveNewMsg) {
        _haveNewMsg = booleanToByte(haveNewMsg);
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Byte getIsPublic() {
        return _isPublic;
    }

    public void setIsPublic(Byte isPublic) {
        _isPublic = isPublic;
    }

    public Boolean gtIsPublic() {
        return byteToBoolean(_isPublic);
    }

    public void stIsPublic(Boolean isPublic) {
        _isPublic = booleanToByte(isPublic);
    }

    public Integer getCount() {
        return _count;
    }

    public void setCount(Integer count) {
        _count = count;
    }

    public Integer getQuantity() {
        return _quantity;
    }

    public void setQuantity(Integer quantity) {
        _quantity = quantity;
    }

    public Integer getPurchase() {
        return _purchase;
    }

    public void setPurchase(Integer purchase) {
        _purchase = purchase;
    }

    public UsrPurchase gtPurchase() {
        if (getPurchase() == null)
            return null;
        return (UsrPurchase) get(UsrPurchase.class, getPurchase());
    }

    public void stPurchase(UsrPurchase purchase) {
        if (purchase == null)
            setPurchase(null);
        else
            setPurchase(purchase.getPkey());
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
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
