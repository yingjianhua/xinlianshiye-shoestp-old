package irille.shop.pdt;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysSeq;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.bean.ISeq;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.Pdt.OProductType;
import irille.shop.pdt.Pdt.OState;
import irille.shop.pdt.Pdt.OStockOut;
import irille.shop.usr.UsrMemberLevel;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 产品
 *
 * @author yingjianhua
 */
public class PdtProduct extends BeanInt<PdtProduct> implements IExtName, ISeq {
    public static final Tb TB = new Tb(PdtProduct.class, "产品").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {//@formatter:off
        PKEY(TB.crtIntPkey()),
        NAME(SYS.MUILTI_LANGUAGE, "名称"), // 名称 多国语言
        IS_VERIFY(SYS.YN, "产品审核"), //是否审核通过
        VERIFY_BY(SYS.CHECK_BY__NULL, "审核人员"), //审核人员
        VERIFY_TIME(SYS.DATE_TIME, "审核时间"), //审核时间
        CATEGORY(PdtCat.fldOutKey("category", "产品类目")),
        CATEGORY_DIY(UsrProductCategory.fldOutKey("category", "店铺类目").setNull()),
        CODE(SYS.CODE__100_NULL, "编号"), //产品编号,不填写为系统默认编号
        SKU(SYS.CODE__100_NULL, "SKU（出厂编码）"),//SKU(出厂编码),不填写为系统默认编号
        //		COMMISSION(SYS.AMT, "佣金"),//佣金
        SUPPLIER(UsrSupplier.fldOutKey()), //供应商
        MEMBER_LEVEL(UsrMemberLevel.fldOutKey().setNull()),//会员等级  保留字段，用户等级优先级排后，有时间再做
        //		DIY_WEBSIZE(SYS.URL__NULL, "自定义地址"),//自定义地址
        PICTURE(SYS.IMG_MULTI__1000, "产品图片"),//产品图片 多图 图片大小建议: 500*500像素
        //		UPDATE_WATERMARK(SYS.YN, "更新水印图片"),//更新水印图片
        MKT_PRICE(SYS.AMT, "市场价$"),//市场价$   做预留 默认值:0
        CUR_PRICE(SYS.AMT, "商城价$"),//商城价$
        PUR_PRICE(SYS.AMT, "进货价$"), //进货价$  做预留 默认值:0
        //当前台选择的产品属性的价格是单价，批发价的计算就会以折扣的方式来计算
        WS_PRICE(SYS.AMT, "批发价"), //批发价 wholesale_price {100:10,1000:90,2000:80}
        MIN_OQ(SYS.INT_PLUS_OR_ZERO, "起订量"),//起订量
        MAX_OQ(SYS.INT_PLUS_OR_ZERO, "最大购买量"), //最大购买量
        SALES(SYS.INT_PLUS_OR_ZERO, "产品销量"), //产品销量 显示已经完成订单的商品数量
        STOCK(SYS.INT_PLUS_OR_ZERO, "库存"), //库存
        WARN_STOCK(SYS.INT_PLUS_OR_ZERO, "警告库存"), //警告库存 低于警告库存量，提醒供应商
        NORM_ATTR(SYS.STR__1000_NULL, "商品属性"), //普通商品属性 通过,分隔 对应AttributeLine
        SIZE_ATTR(SYS.STR__100, "规格属性"), //规格属性    PdtSize的pkeys
        COLOR_ATTR(SYS.STR__100, "颜色属性"), //颜色属性   PdtColor的pkeys

        STOCK_OUT(Tb.crt(Pdt.OStockOut.SOLDOUT)), //脱销状态  商品脱销后加入购物车和购买按钮的显示
        STATE(Tb.crt(Pdt.OState.DEFAULT)), //销售状态 上架和下架
        SOLD_IN_TIME(SYS.YN, "定时上架"), //定时上架
        SOLD_TIME_B(SYS.DATE_TIME__NULL, "上架时间(开始)"), // 上架时间(开始)
        SOLD_TIME_E(SYS.DATE_TIME__NULL, "上架时间(结束)"), // 上架时间(结束)

        IS_DEFAULT_REVIEW(SYS.YN, "默认评论"), //默认评论
        DEFAULT_REVIEW_RATING(SYS.AMT, "默认评论平均分"), //默认评论平均分
        DEFAULT_REVIEW_COUNT(SYS.INT, "默认评论人数"), //默认评论人数
        Favorite_Count(SYS.INT, "收藏数"), //收藏数

        SOURCE_PRODUCT(PdtProduct.fldOutKey().setNull()),//原始产品
        PRODUCT_TYPE(Tb.crt(Pdt.OProductType.DEFAULT)),

        IS_NEW(SYS.YN, "新品"),//新品
        IS_INDEX(SYS.YN, "首页显示"), //首页显示
        IS_HOT(SYS.YN, "热卖"), //热卖
        ISBESTDEALS(SYS.YN, "畅销"), //畅销
        MY_ORDER(SYS.SORT__SHORT, "排序优先级"), //排序优先级

        //标题与标签
        SEO_TITLE(SYS.MUILTI_LANGUAGE, "标题"), //标题
        SEO_KEYWORD(SYS.MUILTI_LANGUAGE, "关键词"), //关键词
        SEO_DESCRIPTION(SYS.MUILTI_LANGUAGE, "简述"), //简述
        //物流运费
        IS_FREE_SHIPPING(SYS.YN, "免运费"), //免运费
        WEIGHT(SYS.AMT, "重量"), //重量
        //CUBAGE(SYS.STR__100_NULL, "体积(长,宽,高)"), //体积 长，宽，高
        LENGTH(SYS.AMT, "长"),
        WIDTH(SYS.AMT, "宽"),
        HEIGHT(SYS.AMT, "高"),

        //文字描述
        BRIEF_DESCRIPTION(SYS.STR__100_NULL, "简短描述"), //简短描述
        DESCRIPTION(SYS.MUILTI_LANGUAGE, "详细介绍"), //详细介绍  多国语言
        //选项卡1
        IS_SHOW_TAB_1(SYS.YN, "选项卡1"), //显示选项卡1
        TABNAME_1(SYS.NAME__100_NULL, "选项卡1标题"), //选项卡1 标题
        TAB_1(SYS.STR__1000_NULL, "选项卡1内容"), //选项卡1内容
        //选项卡2
        IS_SHOW_TAB_2(SYS.YN, "选项卡2"),//显示选项卡1
        TABNAME_2(SYS.NAME__100_NULL, "选项卡2标题"), //选项卡1 标题
        TAB_2(SYS.STR__1000_NULL, "选项卡2内容"), //选项卡1内容
        //选项卡3
        IS_SHOW_TAB_3(SYS.YN, "选项卡3"),//显示选项卡1
        TABNAME_3(SYS.NAME__100_NULL, "选项卡3标题"), //选项卡1 标题
        TAB_3(SYS.STR__1000_NULL, "选项卡3内容"), //选项卡1内容

        UPDATE_TIME(SYS.UPDATED_DATE_TIME),
        ROW_VERSION(SYS.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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

    static { //在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
    }

    public static Fld fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }

    @Override
    public String getExtName() {
        return getName();
    }

    @Override
    public void initSeq(SysSeq s) {
        s.setPkey(gtTable().getPkey());
        s.stOrgFlag(false);
        s.stType(Sys.OType.NONE);

    }


    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  JSONOBJECT
  private Byte _isVerify;	// 产品审核 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _verifyBy;	// 审核人员 <表主键:SysUser>  INT<null>
  private Date _verifyTime;	// 审核时间  TIME
  private Integer _category;	// 产品类目 <表主键:PdtCat>  INT
  private Integer _categoryDiy;	// 店铺类目 <表主键:UsrProductCategory>  INT<null>
  private String _code;	// 编号  STR(100)<null>
  private String _sku;	// SKU（出厂编码）  STR(100)<null>
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private Integer _memberLevel;	// 会员等级 <表主键:UsrMemberLevel>  INT<null>
  private String _picture;	// 产品图片  STR(1000)
  private BigDecimal _mktPrice;	// 市场价$  DEC(16,2)
  private BigDecimal _curPrice;	// 商城价$  DEC(16,2)
  private BigDecimal _purPrice;	// 进货价$  DEC(16,2)
  private BigDecimal _wsPrice;	// 批发价  DEC(16,2)
  private Integer _minOq;	// 起订量  INT
  private Integer _maxOq;	// 最大购买量  INT
  private Integer _sales;	// 产品销量  INT
  private Integer _stock;	// 库存  INT
  private Integer _warnStock;	// 警告库存  INT
  private String _normAttr;	// 商品属性  STR(1000)<null>
  private String _sizeAttr;	// 规格属性  STR(100)
  private String _colorAttr;	// 颜色属性  STR(100)
  private Byte _stockOut;	// 脱销状态 <OStockOut>  BYTE
	// SOLDOUT:0,脱销
	// NOTICE:1,到货通知
  private Byte _state;	// 销售状态 <OState>  BYTE
	// ON:1,上架
	// OFF:0,下架
	// DELETE:2,删除
  private Byte _soldInTime;	// 定时上架 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Date _soldTimeB;	// 上架时间(开始)  TIME<null>
  private Date _soldTimeE;	// 上架时间(结束)  TIME<null>
  private Byte _isDefaultReview;	// 默认评论 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private BigDecimal _defaultReviewRating;	// 默认评论平均分  DEC(16,2)
  private Integer _defaultReviewCount;	// 默认评论人数  INT
  private Integer _favoriteCount;	// 收藏数  INT
  private Integer _sourceProduct;	// 产品 <表主键:PdtProduct>  INT<null>
  private Byte _productType;	// 产品种类 <OProductType>  BYTE
	// GENERAL:0,普通产品
	// GROUP:1,联合采购产品
	// GATHER:2,采集商品
  private Byte _isNew;	// 新品 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _isIndex;	// 首页显示 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _isHot;	// 热卖 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _isbestdeals;	// 畅销 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _myOrder;	// 排序优先级  SHORT
  private String _seoTitle;	// 标题  JSONOBJECT
  private String _seoKeyword;	// 关键词  JSONOBJECT
  private String _seoDescription;	// 简述  JSONOBJECT
  private Byte _isFreeShipping;	// 免运费 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private BigDecimal _weight;	// 重量  DEC(16,2)
  private BigDecimal _length;	// 长  DEC(16,2)
  private BigDecimal _width;	// 宽  DEC(16,2)
  private BigDecimal _height;	// 高  DEC(16,2)
  private String _briefDescription;	// 简短描述  STR(100)<null>
  private String _description;	// 详细介绍  JSONOBJECT
  private Byte _isShowTab1;	// 选项卡1 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _tabname1;	// 选项卡1标题  STR(100)<null>
  private String _tab1;	// 选项卡1内容  STR(1000)<null>
  private Byte _isShowTab2;	// 选项卡2 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _tabname2;	// 选项卡2标题  STR(100)<null>
  private String _tab2;	// 选项卡2内容  STR(1000)<null>
  private Byte _isShowTab3;	// 选项卡3 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _tabname3;	// 选项卡3标题  STR(100)<null>
  private String _tab3;	// 选项卡3内容  STR(1000)<null>
  private Date _updateTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtProduct init(){
		super.init();
    _name=null;	// 名称  JSONOBJECT
    _isVerify=OYn.DEFAULT.getLine().getKey();	// 产品审核 <OYn>  BYTE
    _verifyBy=null;	// 审核人员 <表主键:SysUser>  INT
    _verifyTime=Env.getTranBeginTime();	// 审核时间  TIME
    _category=null;	// 产品类目 <表主键:PdtCat>  INT
    _categoryDiy=null;	// 店铺类目 <表主键:UsrProductCategory>  INT
    _code=null;	// 编号  STR(100)
    _sku=null;	// SKU（出厂编码）  STR(100)
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _memberLevel=null;	// 会员等级 <表主键:UsrMemberLevel>  INT
    _picture=null;	// 产品图片  STR(1000)
    _mktPrice=ZERO;	// 市场价$  DEC(16,2)
    _curPrice=ZERO;	// 商城价$  DEC(16,2)
    _purPrice=ZERO;	// 进货价$  DEC(16,2)
    _wsPrice=ZERO;	// 批发价  DEC(16,2)
    _minOq=0;	// 起订量  INT
    _maxOq=0;	// 最大购买量  INT
    _sales=0;	// 产品销量  INT
    _stock=0;	// 库存  INT
    _warnStock=0;	// 警告库存  INT
    _normAttr=null;	// 商品属性  STR(1000)
    _sizeAttr=null;	// 规格属性  STR(100)
    _colorAttr=null;	// 颜色属性  STR(100)
    _stockOut=OStockOut.DEFAULT.getLine().getKey();	// 脱销状态 <OStockOut>  BYTE
    _state=OState.DEFAULT.getLine().getKey();	// 销售状态 <OState>  BYTE
    _soldInTime=OYn.DEFAULT.getLine().getKey();	// 定时上架 <OYn>  BYTE
    _soldTimeB=null;	// 上架时间(开始)  TIME
    _soldTimeE=null;	// 上架时间(结束)  TIME
    _isDefaultReview=OYn.DEFAULT.getLine().getKey();	// 默认评论 <OYn>  BYTE
    _defaultReviewRating=ZERO;	// 默认评论平均分  DEC(16,2)
    _defaultReviewCount=0;	// 默认评论人数  INT
    _favoriteCount=0;	// 收藏数  INT
    _sourceProduct=null;	// 产品 <表主键:PdtProduct>  INT
    _productType=OProductType.DEFAULT.getLine().getKey();	// 产品种类 <OProductType>  BYTE
    _isNew=OYn.DEFAULT.getLine().getKey();	// 新品 <OYn>  BYTE
    _isIndex=OYn.DEFAULT.getLine().getKey();	// 首页显示 <OYn>  BYTE
    _isHot=OYn.DEFAULT.getLine().getKey();	// 热卖 <OYn>  BYTE
    _isbestdeals=OYn.DEFAULT.getLine().getKey();	// 畅销 <OYn>  BYTE
    _myOrder=0;	// 排序优先级  SHORT
    _seoTitle=null;	// 标题  JSONOBJECT
    _seoKeyword=null;	// 关键词  JSONOBJECT
    _seoDescription=null;	// 简述  JSONOBJECT
    _isFreeShipping=OYn.DEFAULT.getLine().getKey();	// 免运费 <OYn>  BYTE
    _weight=ZERO;	// 重量  DEC(16,2)
    _length=ZERO;	// 长  DEC(16,2)
    _width=ZERO;	// 宽  DEC(16,2)
    _height=ZERO;	// 高  DEC(16,2)
    _briefDescription=null;	// 简短描述  STR(100)
    _description=null;	// 详细介绍  JSONOBJECT
    _isShowTab1=OYn.DEFAULT.getLine().getKey();	// 选项卡1 <OYn>  BYTE
    _tabname1=null;	// 选项卡1标题  STR(100)
    _tab1=null;	// 选项卡1内容  STR(1000)
    _isShowTab2=OYn.DEFAULT.getLine().getKey();	// 选项卡2 <OYn>  BYTE
    _tabname2=null;	// 选项卡2标题  STR(100)
    _tab2=null;	// 选项卡2内容  STR(1000)
    _isShowTab3=OYn.DEFAULT.getLine().getKey();	// 选项卡3 <OYn>  BYTE
    _tabname3=null;	// 选项卡3标题  STR(100)
    _tab3=null;	// 选项卡3内容  STR(1000)
    _updateTime=Env.getTranBeginTime();	// 更新时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public JSONObject gtName() throws JSONException {
    return getName()==null?new JSONObject():new JSONObject(getName());
  }
  public void stName(JSONObject name){
    setName(name==null?null:name.toString());
  }
  public String getName(FldLanguage.Language l) throws JSONException {
    return gtName().has(l.name())?gtName().getString(l.name()):"";
  }
  public void setName(String name, FldLanguage.Language l) throws JSONException {
    stName(gtName().put(l.name(), name));
  }
  public Byte getIsVerify(){
    return _isVerify;
  }
  public void setIsVerify(Byte isVerify){
    _isVerify=isVerify;
  }
  public Boolean gtIsVerify(){
    return byteToBoolean(_isVerify);
  }
  public void stIsVerify(Boolean isVerify){
    _isVerify=booleanToByte(isVerify);
  }
  public Integer getVerifyBy(){
    return _verifyBy;
  }
  public void setVerifyBy(Integer verifyBy){
    _verifyBy=verifyBy;
  }
  public SysUser gtVerifyBy(){
    if(getVerifyBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getVerifyBy());
  }
  public void stVerifyBy(SysUser verifyBy){
    if(verifyBy==null)
      setVerifyBy(null);
    else
      setVerifyBy(verifyBy.getPkey());
  }
  public Date getVerifyTime(){
    return _verifyTime;
  }
  public void setVerifyTime(Date verifyTime){
    _verifyTime=verifyTime;
  }
  public Integer getCategory(){
    return _category;
  }
  public void setCategory(Integer category){
    _category=category;
  }
  public PdtCat gtCategory(){
    if(getCategory()==null)
      return null;
    return (PdtCat)get(PdtCat.class,getCategory());
  }
  public void stCategory(PdtCat category){
    if(category==null)
      setCategory(null);
    else
      setCategory(category.getPkey());
  }
  public Integer getCategoryDiy(){
    return _categoryDiy;
  }
  public void setCategoryDiy(Integer categoryDiy){
    _categoryDiy=categoryDiy;
  }
  public UsrProductCategory gtCategoryDiy(){
    if(getCategoryDiy()==null)
      return null;
    return (UsrProductCategory)get(UsrProductCategory.class,getCategoryDiy());
  }
  public void stCategoryDiy(UsrProductCategory categoryDiy){
    if(categoryDiy==null)
      setCategoryDiy(null);
    else
      setCategoryDiy(categoryDiy.getPkey());
  }
  public String getCode(){
    return _code;
  }
  public void setCode(String code){
    _code=code;
  }
  public String getSku(){
    return _sku;
  }
  public void setSku(String sku){
    _sku=sku;
  }
  public Integer getSupplier(){
    return _supplier;
  }
  public void setSupplier(Integer supplier){
    _supplier=supplier;
  }
  public UsrSupplier gtSupplier(){
    if(getSupplier()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplier());
  }
  public void stSupplier(UsrSupplier supplier){
    if(supplier==null)
      setSupplier(null);
    else
      setSupplier(supplier.getPkey());
  }
  public Integer getMemberLevel(){
    return _memberLevel;
  }
  public void setMemberLevel(Integer memberLevel){
    _memberLevel=memberLevel;
  }
  public UsrMemberLevel gtMemberLevel(){
    if(getMemberLevel()==null)
      return null;
    return (UsrMemberLevel)get(UsrMemberLevel.class,getMemberLevel());
  }
  public void stMemberLevel(UsrMemberLevel memberLevel){
    if(memberLevel==null)
      setMemberLevel(null);
    else
      setMemberLevel(memberLevel.getPkey());
  }
  public String getPicture(){
    return _picture;
  }
  public void setPicture(String picture){
    _picture=picture;
  }
  public BigDecimal getMktPrice(){
    return _mktPrice;
  }
  public void setMktPrice(BigDecimal mktPrice){
    _mktPrice=mktPrice;
  }
  public BigDecimal getCurPrice(){
    return _curPrice;
  }
  public void setCurPrice(BigDecimal curPrice){
    _curPrice=curPrice;
  }
  public BigDecimal getPurPrice(){
    return _purPrice;
  }
  public void setPurPrice(BigDecimal purPrice){
    _purPrice=purPrice;
  }
  public BigDecimal getWsPrice(){
    return _wsPrice;
  }
  public void setWsPrice(BigDecimal wsPrice){
    _wsPrice=wsPrice;
  }
  public Integer getMinOq(){
    return _minOq;
  }
  public void setMinOq(Integer minOq){
    _minOq=minOq;
  }
  public Integer getMaxOq(){
    return _maxOq;
  }
  public void setMaxOq(Integer maxOq){
    _maxOq=maxOq;
  }
  public Integer getSales(){
    return _sales;
  }
  public void setSales(Integer sales){
    _sales=sales;
  }
  public Integer getStock(){
    return _stock;
  }
  public void setStock(Integer stock){
    _stock=stock;
  }
  public Integer getWarnStock(){
    return _warnStock;
  }
  public void setWarnStock(Integer warnStock){
    _warnStock=warnStock;
  }
  public String getNormAttr(){
    return _normAttr;
  }
  public void setNormAttr(String normAttr){
    _normAttr=normAttr;
  }
  public String getSizeAttr(){
    return _sizeAttr;
  }
  public void setSizeAttr(String sizeAttr){
    _sizeAttr=sizeAttr;
  }
  public String getColorAttr(){
    return _colorAttr;
  }
  public void setColorAttr(String colorAttr){
    _colorAttr=colorAttr;
  }
  public Byte getStockOut(){
    return _stockOut;
  }
  public void setStockOut(Byte stockOut){
    _stockOut=stockOut;
  }
  public OStockOut gtStockOut(){
    return (OStockOut)(OStockOut.SOLDOUT.getLine().get(_stockOut));
  }
  public void stStockOut(OStockOut stockOut){
    _stockOut=stockOut.getLine().getKey();
  }
  public Byte getState(){
    return _state;
  }
  public void setState(Byte state){
    _state=state;
  }
  public OState gtState(){
    return (OState)(OState.ON.getLine().get(_state));
  }
  public void stState(OState state){
    _state=state.getLine().getKey();
  }
  public Byte getSoldInTime(){
    return _soldInTime;
  }
  public void setSoldInTime(Byte soldInTime){
    _soldInTime=soldInTime;
  }
  public Boolean gtSoldInTime(){
    return byteToBoolean(_soldInTime);
  }
  public void stSoldInTime(Boolean soldInTime){
    _soldInTime=booleanToByte(soldInTime);
  }
  public Date getSoldTimeB(){
    return _soldTimeB;
  }
  public void setSoldTimeB(Date soldTimeB){
    _soldTimeB=soldTimeB;
  }
  public Date getSoldTimeE(){
    return _soldTimeE;
  }
  public void setSoldTimeE(Date soldTimeE){
    _soldTimeE=soldTimeE;
  }
  public Byte getIsDefaultReview(){
    return _isDefaultReview;
  }
  public void setIsDefaultReview(Byte isDefaultReview){
    _isDefaultReview=isDefaultReview;
  }
  public Boolean gtIsDefaultReview(){
    return byteToBoolean(_isDefaultReview);
  }
  public void stIsDefaultReview(Boolean isDefaultReview){
    _isDefaultReview=booleanToByte(isDefaultReview);
  }
  public BigDecimal getDefaultReviewRating(){
    return _defaultReviewRating;
  }
  public void setDefaultReviewRating(BigDecimal defaultReviewRating){
    _defaultReviewRating=defaultReviewRating;
  }
  public Integer getDefaultReviewCount(){
    return _defaultReviewCount;
  }
  public void setDefaultReviewCount(Integer defaultReviewCount){
    _defaultReviewCount=defaultReviewCount;
  }
  public Integer getFavoriteCount(){
    return _favoriteCount;
  }
  public void setFavoriteCount(Integer favoriteCount){
    _favoriteCount=favoriteCount;
  }
  public Integer getSourceProduct(){
    return _sourceProduct;
  }
  public void setSourceProduct(Integer sourceProduct){
    _sourceProduct=sourceProduct;
  }
  public PdtProduct gtSourceProduct(){
    if(getSourceProduct()==null)
      return null;
    return (PdtProduct)get(PdtProduct.class,getSourceProduct());
  }
  public void stSourceProduct(PdtProduct sourceProduct){
    if(sourceProduct==null)
      setSourceProduct(null);
    else
      setSourceProduct(sourceProduct.getPkey());
  }
  public Byte getProductType(){
    return _productType;
  }
  public void setProductType(Byte productType){
    _productType=productType;
  }
  public OProductType gtProductType(){
    return (OProductType)(OProductType.GENERAL.getLine().get(_productType));
  }
  public void stProductType(OProductType productType){
    _productType=productType.getLine().getKey();
  }
  public Byte getIsNew(){
    return _isNew;
  }
  public void setIsNew(Byte isNew){
    _isNew=isNew;
  }
  public Boolean gtIsNew(){
    return byteToBoolean(_isNew);
  }
  public void stIsNew(Boolean isNew){
    _isNew=booleanToByte(isNew);
  }
  public Byte getIsIndex(){
    return _isIndex;
  }
  public void setIsIndex(Byte isIndex){
    _isIndex=isIndex;
  }
  public Boolean gtIsIndex(){
    return byteToBoolean(_isIndex);
  }
  public void stIsIndex(Boolean isIndex){
    _isIndex=booleanToByte(isIndex);
  }
  public Byte getIsHot(){
    return _isHot;
  }
  public void setIsHot(Byte isHot){
    _isHot=isHot;
  }
  public Boolean gtIsHot(){
    return byteToBoolean(_isHot);
  }
  public void stIsHot(Boolean isHot){
    _isHot=booleanToByte(isHot);
  }
  public Byte getIsbestdeals(){
    return _isbestdeals;
  }
  public void setIsbestdeals(Byte isbestdeals){
    _isbestdeals=isbestdeals;
  }
  public Boolean gtIsbestdeals(){
    return byteToBoolean(_isbestdeals);
  }
  public void stIsbestdeals(Boolean isbestdeals){
    _isbestdeals=booleanToByte(isbestdeals);
  }
  public Short getMyOrder(){
    return _myOrder;
  }
  public void setMyOrder(Short myOrder){
    _myOrder=myOrder;
  }
  public String getSeoTitle(){
    return _seoTitle;
  }
  public void setSeoTitle(String seoTitle){
    _seoTitle=seoTitle;
  }
  public JSONObject gtSeoTitle() throws JSONException {
    return getSeoTitle()==null?new JSONObject():new JSONObject(getSeoTitle());
  }
  public void stSeoTitle(JSONObject seoTitle){
    setSeoTitle(seoTitle==null?null:seoTitle.toString());
  }
  public String getSeoTitle(FldLanguage.Language l) throws JSONException {
    return gtSeoTitle().has(l.name())?gtSeoTitle().getString(l.name()):"";
  }
  public void setSeoTitle(String seoTitle, FldLanguage.Language l) throws JSONException {
    stSeoTitle(gtSeoTitle().put(l.name(), seoTitle));
  }
  public String getSeoKeyword(){
    return _seoKeyword;
  }
  public void setSeoKeyword(String seoKeyword){
    _seoKeyword=seoKeyword;
  }
  public JSONObject gtSeoKeyword() throws JSONException {
    return getSeoKeyword()==null?new JSONObject():new JSONObject(getSeoKeyword());
  }
  public void stSeoKeyword(JSONObject seoKeyword){
    setSeoKeyword(seoKeyword==null?null:seoKeyword.toString());
  }
  public String getSeoKeyword(FldLanguage.Language l) throws JSONException {
    return gtSeoKeyword().has(l.name())?gtSeoKeyword().getString(l.name()):"";
  }
  public void setSeoKeyword(String seoKeyword, FldLanguage.Language l) throws JSONException {
    stSeoKeyword(gtSeoKeyword().put(l.name(), seoKeyword));
  }
  public String getSeoDescription(){
    return _seoDescription;
  }
  public void setSeoDescription(String seoDescription){
    _seoDescription=seoDescription;
  }
  public JSONObject gtSeoDescription() throws JSONException {
    return getSeoDescription()==null?new JSONObject():new JSONObject(getSeoDescription());
  }
  public void stSeoDescription(JSONObject seoDescription){
    setSeoDescription(seoDescription==null?null:seoDescription.toString());
  }
  public String getSeoDescription(FldLanguage.Language l) throws JSONException {
    return gtSeoDescription().has(l.name())?gtSeoDescription().getString(l.name()):"";
  }
  public void setSeoDescription(String seoDescription, FldLanguage.Language l) throws JSONException {
    stSeoDescription(gtSeoDescription().put(l.name(), seoDescription));
  }
  public Byte getIsFreeShipping(){
    return _isFreeShipping;
  }
  public void setIsFreeShipping(Byte isFreeShipping){
    _isFreeShipping=isFreeShipping;
  }
  public Boolean gtIsFreeShipping(){
    return byteToBoolean(_isFreeShipping);
  }
  public void stIsFreeShipping(Boolean isFreeShipping){
    _isFreeShipping=booleanToByte(isFreeShipping);
  }
  public BigDecimal getWeight(){
    return _weight;
  }
  public void setWeight(BigDecimal weight){
    _weight=weight;
  }
  public BigDecimal getLength(){
    return _length;
  }
  public void setLength(BigDecimal length){
    _length=length;
  }
  public BigDecimal getWidth(){
    return _width;
  }
  public void setWidth(BigDecimal width){
    _width=width;
  }
  public BigDecimal getHeight(){
    return _height;
  }
  public void setHeight(BigDecimal height){
    _height=height;
  }
  public String getBriefDescription(){
    return _briefDescription;
  }
  public void setBriefDescription(String briefDescription){
    _briefDescription=briefDescription;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public JSONObject gtDescription() throws JSONException {
    return getDescription()==null?new JSONObject():new JSONObject(getDescription());
  }
  public void stDescription(JSONObject description){
    setDescription(description==null?null:description.toString());
  }
  public String getDescription(FldLanguage.Language l) throws JSONException {
    return gtDescription().has(l.name())?gtDescription().getString(l.name()):"";
  }
  public void setDescription(String description, FldLanguage.Language l) throws JSONException {
    stDescription(gtDescription().put(l.name(), description));
  }
  public Byte getIsShowTab1(){
    return _isShowTab1;
  }
  public void setIsShowTab1(Byte isShowTab1){
    _isShowTab1=isShowTab1;
  }
  public Boolean gtIsShowTab1(){
    return byteToBoolean(_isShowTab1);
  }
  public void stIsShowTab1(Boolean isShowTab1){
    _isShowTab1=booleanToByte(isShowTab1);
  }
  public String getTabname1(){
    return _tabname1;
  }
  public void setTabname1(String tabname1){
    _tabname1=tabname1;
  }
  public String getTab1(){
    return _tab1;
  }
  public void setTab1(String tab1){
    _tab1=tab1;
  }
  public Byte getIsShowTab2(){
    return _isShowTab2;
  }
  public void setIsShowTab2(Byte isShowTab2){
    _isShowTab2=isShowTab2;
  }
  public Boolean gtIsShowTab2(){
    return byteToBoolean(_isShowTab2);
  }
  public void stIsShowTab2(Boolean isShowTab2){
    _isShowTab2=booleanToByte(isShowTab2);
  }
  public String getTabname2(){
    return _tabname2;
  }
  public void setTabname2(String tabname2){
    _tabname2=tabname2;
  }
  public String getTab2(){
    return _tab2;
  }
  public void setTab2(String tab2){
    _tab2=tab2;
  }
  public Byte getIsShowTab3(){
    return _isShowTab3;
  }
  public void setIsShowTab3(Byte isShowTab3){
    _isShowTab3=isShowTab3;
  }
  public Boolean gtIsShowTab3(){
    return byteToBoolean(_isShowTab3);
  }
  public void stIsShowTab3(Boolean isShowTab3){
    _isShowTab3=booleanToByte(isShowTab3);
  }
  public String getTabname3(){
    return _tabname3;
  }
  public void setTabname3(String tabname3){
    _tabname3=tabname3;
  }
  public String getTab3(){
    return _tab3;
  }
  public void setTab3(String tab3){
    _tab3=tab3;
  }
  public Date getUpdateTime(){
    return _updateTime;
  }
  public void setUpdateTime(Date updateTime){
    _updateTime=updateTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<


    /***
     * 所有商品列表页
     * 根据什么排列，枚举类
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/23 15:28
     */
    public enum ProductsIndexOrderByType {
        MostPopular(T.MY_ORDER, T.UPDATE_TIME),
        Sales(T.SALES),
        Favorites(T.Favorite_Count),
        Price(T.CUR_PRICE),
        New(T.PKEY, T.MY_ORDER, T.UPDATE_TIME),
        Hot(T.IS_HOT);

        private List<IEnumFld> fld;

        ProductsIndexOrderByType(IEnumFld... fld) {
            this.fld = Arrays.asList(fld);
        }

        public List<IEnumFld> getFld() {
            return fld;
        }
    }
}
