package irille.shop.usr;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr.OIsAuth;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.Usr.SStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * 供应商
 *
 * @author yingjianhua
 */
public class UsrSupplier extends BeanInt<UsrSupplier> implements IExtName {

  public static final Tb TB = new Tb(UsrSupplier.class, "供应商").setAutoIncrement()
      .addActList()
      .addActApprove()
      .addActOpt("updBase", "基本信息", "upd-icon")
      .addActOpt("updPage", "页面资料", "upd-icon")
      .addActOpt("updDiy", "个性装修", "upd-icon")
      .addActOpt("updMarketing", "营销设置", "upd-icon");

  public enum T implements IEnumFld {//@formatter:off
    PKEY(TB.crtIntPkey()),
    ROLE(UsrSupplierRole.fldOutKey()),
    LOGIN_NAME(SYS.CODE__40, "登录账号"),
    PASSWORD(SYS.PASSWORD__NULL),
    STATUS(TB.crt(Usr.OStatus.DEFAULT)), //审核状态 createBy liyichao
    APPR_BY(SYS.APPR_BY_NULL),       //createBy liyichao	审核人
    APPR_TIME(SYS.APPR_DATE_TIME__NULL), //createBy liyichao	审核时间
    /**
     * 3.1.0新增字段
     */
    REASON(SYS.STR__100,"审核不通过理由备注"),
    STORE_STATUS(TB.crt(SStatus.DOWN)), //店铺状态 0：关闭，1开启
    ENGLISH_NAME(SYS.STR__100, "英文名称"),
    ANNUAL_PRODUCTION(SYS.STR__100, "年产量"),
    POSTCODE(SYS.STR__20, "邮编"),
    TARGETED_MARKET(SYS.STR__100_NULL, "目标市场"),
    CONTACT_EMAIL(SYS.STR__100, "联系人邮箱"),
    APPLICATION_TIME(SYS.DATE, "申请时间"),
    USER_ID(UsrMain.fldOutKey()),
    /**
     * 公司信息
     */
    NAME(SYS.NAME__100, "名称"), // 名称
    REGISTERED_CAPITAL(SYS.STR__100, "注册资金"),//注册资金
    CATEGORY(UsrSupplierCategory.fldOutKey()),//供应商分类
    IS_AUTH(TB.crt(Usr.OIsAuth.DEFAULT)),// 认证 true false
    SORT(SYS.SORT__INT),//排序： 默认，最后，1-100
    SEO_TITLE(SYS.MUILTI_LANGUAGE_NULL, "店铺关键字"),//客人能通过这些字搜索到店铺，多个关键字用空格分开
    SEO_CONTENT(SYS.MUILTI_LANGUAGE_NULL, "搜索引擎说明"),// 客人能通过这些字描述决定进不进店，多个描述字用空格分开
    AUTH_TIME(SYS.TIME__NULL, "认证时间"),//认证时间
    SHOW_NAME(SYS.MUILTI_LANGUAGE_NULL, "网站显示名称"),// 网站显示名称
    ENTITY(SYS.NAME__100, "企业法人"),//企业法人
    COMPANY_TYPE(SYS.MUILTI_LANGUAGE_NULL, "企业类型"),//类型 多国语言
    COMPANY_NATURE(SYS.MUILTI_LANGUAGE_NULL, "企业性质"),//性质 多国语言
    CREDIT_CODE(SYS.CODE__100_NULL, "信用代码"),//信用代码
    COMPANY_ESTABLISH_TIME(SYS.DATE__NULL, "成立时间"),//成立时间
    OPERATION_TERM(SYS.STR__100_NULL, "业务期限"),//业务期限
    MAIN_SALES_AREA(SYS.MUILTI_LANGUAGE_NULL, "主销售区"),//主销售区 多国语言
    MAIN_PROD(SYS.MUILTI_LANGUAGE_NULL, "主要产品"),//主要产品 多国语言
    PROD_PATTERN(SYS.MUILTI_LANGUAGE_NULL, "生产模式"),//生产模式 多国语言
    COMPANY_ADDR(SYS.MUILTI_LANGUAGE_NULL, "地址"),//地址 多国语言
    DES(SYS.DESCRIP__200_NULL),//备注
    EMAIL(SYS.STR__100, "Email"),//Email 必填重要！新的询盘会通知到这里！
    BUSINESS_LICENSE_BEGIN_TIME(SYS.STR__100_NULL, "营业执照开始时间"),
    BUSINESS_LICENSE_END_TIME(SYS.STR__100_NULL, "营业执照到期时间"),
    BUSINESS_LICENSE_IS_SECULAR(SYS.NY, "是否长期"),
    TELEPHONE(SYS.STR__20_NULL, "电话"),//电话
    FAX(SYS.STR__20_NULL, "传真"),//传真
    QQ(SYS.QQ),//QQ
    CERT_PHOTO(SYS.STR__200_NULL, "资质证书"),//资质证书
    ID_CARD_FRONT_PHOTO(SYS.STR__200_NULL, "法人身份证正面"),//实际使用（正反面图片）
    ID_CARD_BACK_PHOTO(SYS.STR__200_NULL, "法人身份证反面"),//身份证反面
    COOP_CERT_PHOTO(SYS.STR__200_NULL, "合作凭证"),//合作凭证
    TAXPAYER_TYPE(SYS.STR__100_NULL, "纳税人类型"),
    ID_CARD(SYS.STR__50_NULL, "法人身份证号码"),//法人身份证号码
    OPERATE_ID_CARD(SYS.STR__100_NULL, "运营身份证号码"),//运营身份证号码
    /**
     * 运营信息
     */
    CONTACTS(SYS.NAME__40_NULL, "联系人"),//联系人
    PHONE(SYS.STR__20_NULL, "手机"),//手机 必填重要！新的询盘会通知到这里！
    SETTLEMENT_BANK(SYS.STR__100_NULL, "结算开户行"),
    BANK_ACCOUNT(SYS.STR__100_NULL, "银行账号"),
    BANK_BRANCH(SYS.STR__100_NULL, "银行开户行"),
    BANK_COUNTRY(PltCountry.fldOutKey()),//,"开户行国家"
    BANK_PROVINCE(PltProvince.fldOutKey()),//"开户行省份"
    CONTACTS_ID_CARD_FRONT_PHOTO(SYS.STR__200_NULL, "运营负责人身份证正面"), //实际使用（正反面图片）
    CONTACTS_ID_CARD_BACK_PHOTO(SYS.STR__200_NULL, "运营负责人身份证反面"),

    /**
     * 前台页面资料
     */
    BUSINESS_TYP(SYS.MUILTI_LANGUAGE_NULL, "Business_typ"),//Business_typ 多国语言
    LOCATION(SYS.MUILTI_LANGUAGE_NULL, "Location"),//Location 多国语言
    PRODUCTION(SYS.MUILTI_LANGUAGE_NULL, "Production"),//Production 多国语言
    DEVELOPER(SYS.MUILTI_LANGUAGE_NULL, "Developer"),//Developer 多国语言
    TOTAL_EMPLOYEES(SYS.MUILTI_LANGUAGE_NULL, "Total_employees"),//Total_employees 多国语言
    ANNUAL_SALES(SYS.MUILTI_LANGUAGE_NULL, "Annual_sales"),//Annual_sales 多国语言
    TOP_3_MARKETS(SYS.MUILTI_LANGUAGE_NULL, "Top 3 Markets"),//Top 3 Markets 多国语言
    MATERIALS(SYS.MUILTI_LANGUAGE_NULL, "Materials"),//Materials 多国语言
    HEAD_PIC(SYS.STR__200_NULL, "头像"),//头像
    DEPARTMENT(SYS.MUILTI_LANGUAGE_NULL, "联系人部门"),//联系人部门 多国语言
    JOB_TITLE(SYS.MUILTI_LANGUAGE_NULL, "联系人职称"),//联系人职称  多国语言
    WEBSITE(SYS.STR__100_NULL, "Website"),//Website
    COUNTRY(PltCountry.fldOutKey()),//Country/Region 	国家
    PROVINCE(PltProvince.fldOutKey()),//Province/state 	省份
    CITY(SYS.MUILTI_LANGUAGE_NULL, "City"),//City 多国语言

    Is_Pro(SYS.NY, "供应商首页产品展示"),//供应商首页产品展
    LOGO(SYS.URL__NULL, "logo"),//logo 150*150像素
    SIGN_BACKGD(SYS.URL__NULL, "店招背景"),//店招背景 1920x150像素
    AD_PHOTO(SYS.MUILTI_LANGUAGE_NULL, "广告图"),//广告图 图片大小建议: 1200*550像素
    AD_PHOTO_MOBILE(SYS.STR__1000_NULL, "移动端广告图"),//移动端广告图 图片大小建议: 640*340像素
    AD_PHOTO_LINK(SYS.MUILTI_LANGUAGE_NULL,
        "广告连接"),//广告连接 外链必须使用完整域名，即包含http://或https://，例： https://www.google.com。内链不需加域名，例: /products/
    COMPANY_PHOTO(SYS.MUILTI_LANGUAGE_NULL, "企业图片"),//企业图片 图片大小建议: 780x480像素
    COMPANY_PHOTO_LINK(SYS.MUILTI_LANGUAGE_NULL,
        "企业图片连接"),//企业图片连接 外链必须使用完整域名，即包含http://或https://，例： https://www.google.com。内链不需加域名，例: /products/

    /**
     * 个性装修 选择模板
     */
    HOME_PAGE_DIY(SYS.MUILTI_LANGUAGE_NULL, "首页个性装修"),//首页个性装修  多国语言
    PRODUCT_PAGE_DIY(SYS.MUILTI_LANGUAGE_NULL, "产品页个性装修"),//产品页个性装修  多国语言
    CONTACT_PAGE_DIY(SYS.MUILTI_LANGUAGE_NULL, "联系页个性装修"),//联系页个性装修  多国语言
    COMPANY_INTRODUCTION_PAGE_CUSTOM_DECORATION(SYS.MUILTI_LANGUAGE_NULL,
        "公司介绍页自定义装修"),//公司介绍页自定义装修  多国语言
    HOME_PAGE_DIY_MOBILE(SYS.STR__1000_NULL, "首页个性装修（移动）"),//首页个性装修（移动）
    PRODUCT_PAGE_DIY_MOBILE(SYS.STR__1000_NULL, "产品页个性装修（移动）"),//产品页个性装修（移动）
    CONTACT_PAGE_DIY_MOBILE(SYS.STR__1000_NULL, "联系页个性装修（移动）"),//联系页个性装修（移动）
    HOME_PAGE_ON(SYS.NY, "首页个性装修开关"),
    PRODUCT_PAGE_ON(SYS.NY, "产品页个性装修开关"),
    CONTACT_PAGE_ON(SYS.NY, "联系页个性装修开关"),
    BOTTOM_HOME_PRODUCTS_ON(SYS.NY, "首页底部产品展示开关"),
    HOME_POSTER_ON(SYS.NY, "首页大海报开关"),
    HOME_BUSINESS_BIG_POSTER_ON(SYS.NY, "首页企业大海报开关"),
    COMPANY_INTRODUCTION_PAGE_CUSTOM_DECORATION_ON(SYS.NY, "公司介绍页自定义装修开关"),
    /**
     * 营销设置
     */
    TRACE_CODE(SYS.TEXT__1000_NULL, "跟踪代码"),//跟踪代码
    WEB_SIZE_TITLE(SYS.STR__100_NULL, "自定义链接名称"),//英文、数字、下划线
    WEB_SITE(SYS.STR__100_NULL, "网址"),
    TONGJI_URL(SYS.URL__NULL, "第三方统计地址"),//第三方统计 地址
    TONGJI_PWD(SYS.PASSWORD__NULL, "第三方统计密码"),//第三方统计 密码
    UPDATE_TIME(SYS.UPDATED_DATE_TIME),//最后编辑时间

    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_LOGIN_NAME = TB.addIndex("login_name", true, T.LOGIN_NAME);
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
  //@formatter:on

  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _role;	// 供应商角色 <表主键:UsrSupplierRole>  INT
  private String _loginName;	// 登录账号  STR(40)
  private String _password;	// 密码  STR(40)<null>
  private Byte _status;	// 审核状态 <OStatus>  BYTE
	// INIT:0,未审核
	// APPR:1,审核通过
	// FAIL:2,审核不通过
  private Integer _apprBy;	// 审核员 <表主键:SysUser>  INT<null>
  private Date _apprTime;	// 审核时间  TIME<null>
  private String _reason;	// 审核不通过理由备注  STR(100)
  private Byte _storeStatus;	// 店铺状态 <SStatus>  BYTE
	// DOWN:0,关闭
	// OPEN:1,开启
  private String _englishName;	// 英文名称  STR(100)
  private String _annualProduction;	// 年产量  STR(100)
  private String _postcode;	// 邮编  STR(20)
  private String _targetedMarket;	// 目标市场  STR(100)<null>
  private String _contactEmail;	// 联系人邮箱  STR(100)
  private Date _applicationTime;	// 申请时间  DATE
  private Integer _userId;	// 用户登陆信息表 <表主键:UsrMain>  INT
  private String _name;	// 名称  STR(100)
  private String _registeredCapital;	// 注册资金  STR(100)
  private Integer _category;	// 供应商分类 <表主键:UsrSupplierCategory>  INT
  private Byte _isAuth;	// 供应商认证 <OIsAuth>  BYTE
	// NO:0,未认证
	// YES:1,已认证
  private Integer _sort;	// 排序号  INT
  private String _seoTitle;	// 店铺关键字  JSONOBJECT<null>
  private String _seoContent;	// 搜索引擎说明  JSONOBJECT<null>
  private Date _authTime;	// 认证时间  TIME<null>
  private String _showName;	// 网站显示名称  JSONOBJECT<null>
  private String _entity;	// 企业法人  STR(100)
  private String _companyType;	// 企业类型  JSONOBJECT<null>
  private String _companyNature;	// 企业性质  JSONOBJECT<null>
  private String _creditCode;	// 信用代码  STR(100)<null>
  private Date _companyEstablishTime;	// 成立时间  DATE<null>
  private String _operationTerm;	// 业务期限  STR(100)<null>
  private String _mainSalesArea;	// 主销售区  JSONOBJECT<null>
  private String _mainProd;	// 主要产品  JSONOBJECT<null>
  private String _prodPattern;	// 生产模式  JSONOBJECT<null>
  private String _companyAddr;	// 地址  JSONOBJECT<null>
  private String _des;	// 描述  STR(200)<null>
  private String _email;	// Email  STR(100)
  private String _businessLicenseBeginTime;	// 营业执照开始时间  STR(100)<null>
  private String _businessLicenseEndTime;	// 营业执照到期时间  STR(100)<null>
  private Byte _businessLicenseIsSecular;	// 是否长期 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _telephone;	// 电话  STR(20)<null>
  private String _fax;	// 传真  STR(20)<null>
  private String _qq;	// QQ  STR(100)<null>
  private String _certPhoto;	// 资质证书  STR(200)<null>
  private String _idCardFrontPhoto;	// 法人身份证正面  STR(200)<null>
  private String _idCardBackPhoto;	// 法人身份证反面  STR(200)<null>
  private String _coopCertPhoto;	// 合作凭证  STR(200)<null>
  private String _taxpayerType;	// 纳税人类型  STR(100)<null>
  private String _idCard;	// 法人身份证号码  STR(50)<null>
  private String _operateIdCard;	// 运营身份证号码  STR(100)<null>
  private String _contacts;	// 联系人  STR(40)<null>
  private String _phone;	// 手机  STR(20)<null>
  private String _settlementBank;	// 结算开户行  STR(100)<null>
  private String _bankAccount;	// 银行账号  STR(100)<null>
  private String _bankBranch;	// 银行开户行  STR(100)<null>
  private Integer _bankCountry;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _bankProvince;	// 省份 <表主键:PltProvince>  INT
  private String _contactsIdCardFrontPhoto;	// 运营负责人身份证正面  STR(200)<null>
  private String _contactsIdCardBackPhoto;	// 运营负责人身份证反面  STR(200)<null>
  private String _businessTyp;	// Business_typ  JSONOBJECT<null>
  private String _location;	// Location  JSONOBJECT<null>
  private String _production;	// Production  JSONOBJECT<null>
  private String _developer;	// Developer  JSONOBJECT<null>
  private String _totalEmployees;	// Total_employees  JSONOBJECT<null>
  private String _annualSales;	// Annual_sales  JSONOBJECT<null>
  private String _top3Markets;	// Top 3 Markets  JSONOBJECT<null>
  private String _materials;	// Materials  JSONOBJECT<null>
  private String _headPic;	// 头像  STR(200)<null>
  private String _department;	// 联系人部门  JSONOBJECT<null>
  private String _jobTitle;	// 联系人职称  JSONOBJECT<null>
  private String _website;	// Website  STR(100)<null>
  private Integer _country;	// 国家管理 <表主键:PltCountry>  INT
  private Integer _province;	// 省份 <表主键:PltProvince>  INT
  private String _city;	// City  JSONOBJECT<null>
  private Byte _isPro;	// 供应商首页产品展示 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _logo;	// logo  STR(200)<null>
  private String _signBackgd;	// 店招背景  STR(200)<null>
  private String _adPhoto;	// 广告图  JSONOBJECT<null>
  private String _adPhotoMobile;	// 移动端广告图  STR(1000)<null>
  private String _adPhotoLink;	// 广告连接  JSONOBJECT<null>
  private String _companyPhoto;	// 企业图片  JSONOBJECT<null>
  private String _companyPhotoLink;	// 企业图片连接  JSONOBJECT<null>
  private String _homePageDiy;	// 首页个性装修  JSONOBJECT<null>
  private String _productPageDiy;	// 产品页个性装修  JSONOBJECT<null>
  private String _contactPageDiy;	// 联系页个性装修  JSONOBJECT<null>
  private String _companyIntroductionPageCustomDecoration;	// 公司介绍页自定义装修  JSONOBJECT<null>
  private String _homePageDiyMobile;	// 首页个性装修（移动）  STR(1000)<null>
  private String _productPageDiyMobile;	// 产品页个性装修（移动）  STR(1000)<null>
  private String _contactPageDiyMobile;	// 联系页个性装修（移动）  STR(1000)<null>
  private Byte _homePageOn;	// 首页个性装修开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _productPageOn;	// 产品页个性装修开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _contactPageOn;	// 联系页个性装修开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _bottomHomeProductsOn;	// 首页底部产品展示开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _homePosterOn;	// 首页大海报开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _homeBusinessBigPosterOn;	// 首页企业大海报开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _companyIntroductionPageCustomDecorationOn;	// 公司介绍页自定义装修开关 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _traceCode;	// 跟踪代码  TEXT(200)<null>
  private String _webSizeTitle;	// 自定义链接名称  STR(100)<null>
  private String _webSite;	// 网址  STR(100)<null>
  private String _tongjiUrl;	// 第三方统计地址  STR(200)<null>
  private String _tongjiPwd;	// 第三方统计密码  STR(40)<null>
  private Date _updateTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrSupplier init(){
		super.init();
    _role=null;	// 供应商角色 <表主键:UsrSupplierRole>  INT
    _loginName=null;	// 登录账号  STR(40)
    _password=null;	// 密码  STR(40)
    _status=OStatus.DEFAULT.getLine().getKey();	// 审核状态 <OStatus>  BYTE
    _apprBy=null;	// 审核员 <表主键:SysUser>  INT
    _apprTime=null;	// 审核时间  TIME
    _reason=null;	// 审核不通过理由备注  STR(100)
    _storeStatus=SStatus.DEFAULT.getLine().getKey();	// 店铺状态 <SStatus>  BYTE
    _englishName=null;	// 英文名称  STR(100)
    _annualProduction=null;	// 年产量  STR(100)
    _postcode=null;	// 邮编  STR(20)
    _targetedMarket=null;	// 目标市场  STR(100)
    _contactEmail=null;	// 联系人邮箱  STR(100)
    _applicationTime=null;	// 申请时间  DATE
    _userId=null;	// 用户登陆信息表 <表主键:UsrMain>  INT
    _name=null;	// 名称  STR(100)
    _registeredCapital=null;	// 注册资金  STR(100)
    _category=null;	// 供应商分类 <表主键:UsrSupplierCategory>  INT
    _isAuth=OIsAuth.DEFAULT.getLine().getKey();	// 供应商认证 <OIsAuth>  BYTE
    _sort=0;	// 排序号  INT
    _seoTitle=null;	// 店铺关键字  JSONOBJECT
    _seoContent=null;	// 搜索引擎说明  JSONOBJECT
    _authTime=null;	// 认证时间  TIME
    _showName=null;	// 网站显示名称  JSONOBJECT
    _entity=null;	// 企业法人  STR(100)
    _companyType=null;	// 企业类型  JSONOBJECT
    _companyNature=null;	// 企业性质  JSONOBJECT
    _creditCode=null;	// 信用代码  STR(100)
    _companyEstablishTime=null;	// 成立时间  DATE
    _operationTerm=null;	// 业务期限  STR(100)
    _mainSalesArea=null;	// 主销售区  JSONOBJECT
    _mainProd=null;	// 主要产品  JSONOBJECT
    _prodPattern=null;	// 生产模式  JSONOBJECT
    _companyAddr=null;	// 地址  JSONOBJECT
    _des=null;	// 描述  STR(200)
    _email=null;	// Email  STR(100)
    _businessLicenseBeginTime=null;	// 营业执照开始时间  STR(100)
    _businessLicenseEndTime=null;	// 营业执照到期时间  STR(100)
    _businessLicenseIsSecular=OYn.DEFAULT.getLine().getKey();	// 是否长期 <OYn>  BYTE
    _telephone=null;	// 电话  STR(20)
    _fax=null;	// 传真  STR(20)
    _qq=null;	// QQ  STR(100)
    _certPhoto=null;	// 资质证书  STR(200)
    _idCardFrontPhoto=null;	// 法人身份证正面  STR(200)
    _idCardBackPhoto=null;	// 法人身份证反面  STR(200)
    _coopCertPhoto=null;	// 合作凭证  STR(200)
    _taxpayerType=null;	// 纳税人类型  STR(100)
    _idCard=null;	// 法人身份证号码  STR(50)
    _operateIdCard=null;	// 运营身份证号码  STR(100)
    _contacts=null;	// 联系人  STR(40)
    _phone=null;	// 手机  STR(20)
    _settlementBank=null;	// 结算开户行  STR(100)
    _bankAccount=null;	// 银行账号  STR(100)
    _bankBranch=null;	// 银行开户行  STR(100)
    _bankCountry=null;	// 国家管理 <表主键:PltCountry>  INT
    _bankProvince=null;	// 省份 <表主键:PltProvince>  INT
    _contactsIdCardFrontPhoto=null;	// 运营负责人身份证正面  STR(200)
    _contactsIdCardBackPhoto=null;	// 运营负责人身份证反面  STR(200)
    _businessTyp=null;	// Business_typ  JSONOBJECT
    _location=null;	// Location  JSONOBJECT
    _production=null;	// Production  JSONOBJECT
    _developer=null;	// Developer  JSONOBJECT
    _totalEmployees=null;	// Total_employees  JSONOBJECT
    _annualSales=null;	// Annual_sales  JSONOBJECT
    _top3Markets=null;	// Top 3 Markets  JSONOBJECT
    _materials=null;	// Materials  JSONOBJECT
    _headPic=null;	// 头像  STR(200)
    _department=null;	// 联系人部门  JSONOBJECT
    _jobTitle=null;	// 联系人职称  JSONOBJECT
    _website=null;	// Website  STR(100)
    _country=null;	// 国家管理 <表主键:PltCountry>  INT
    _province=null;	// 省份 <表主键:PltProvince>  INT
    _city=null;	// City  JSONOBJECT
    _isPro=OYn.DEFAULT.getLine().getKey();	// 供应商首页产品展示 <OYn>  BYTE
    _logo=null;	// logo  STR(200)
    _signBackgd=null;	// 店招背景  STR(200)
    _adPhoto=null;	// 广告图  JSONOBJECT
    _adPhotoMobile=null;	// 移动端广告图  STR(1000)
    _adPhotoLink=null;	// 广告连接  JSONOBJECT
    _companyPhoto=null;	// 企业图片  JSONOBJECT
    _companyPhotoLink=null;	// 企业图片连接  JSONOBJECT
    _homePageDiy=null;	// 首页个性装修  JSONOBJECT
    _productPageDiy=null;	// 产品页个性装修  JSONOBJECT
    _contactPageDiy=null;	// 联系页个性装修  JSONOBJECT
    _companyIntroductionPageCustomDecoration=null;	// 公司介绍页自定义装修  JSONOBJECT
    _homePageDiyMobile=null;	// 首页个性装修（移动）  STR(1000)
    _productPageDiyMobile=null;	// 产品页个性装修（移动）  STR(1000)
    _contactPageDiyMobile=null;	// 联系页个性装修（移动）  STR(1000)
    _homePageOn=OYn.DEFAULT.getLine().getKey();	// 首页个性装修开关 <OYn>  BYTE
    _productPageOn=OYn.DEFAULT.getLine().getKey();	// 产品页个性装修开关 <OYn>  BYTE
    _contactPageOn=OYn.DEFAULT.getLine().getKey();	// 联系页个性装修开关 <OYn>  BYTE
    _bottomHomeProductsOn=OYn.DEFAULT.getLine().getKey();	// 首页底部产品展示开关 <OYn>  BYTE
    _homePosterOn=OYn.DEFAULT.getLine().getKey();	// 首页大海报开关 <OYn>  BYTE
    _homeBusinessBigPosterOn=OYn.DEFAULT.getLine().getKey();	// 首页企业大海报开关 <OYn>  BYTE
    _companyIntroductionPageCustomDecorationOn=OYn.DEFAULT.getLine().getKey();	// 公司介绍页自定义装修开关 <OYn>  BYTE
    _traceCode=null;	// 跟踪代码  TEXT(200)
    _webSizeTitle=null;	// 自定义链接名称  STR(100)
    _webSite=null;	// 网址  STR(100)
    _tongjiUrl=null;	// 第三方统计地址  STR(200)
    _tongjiPwd=null;	// 第三方统计密码  STR(40)
    _updateTime=Env.getTranBeginTime();	// 更新时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static UsrSupplier loadUniqueLogin_name(boolean lockFlag,String loginName) {
    return (UsrSupplier)loadUnique(T.IDX_LOGIN_NAME,lockFlag,loginName);
  }
  public static UsrSupplier chkUniqueLogin_name(boolean lockFlag,String loginName) {
    return (UsrSupplier)chkUnique(T.IDX_LOGIN_NAME,lockFlag,loginName);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getRole(){
    return _role;
  }
  public void setRole(Integer role){
    _role=role;
  }
  public UsrSupplierRole gtRole(){
    if(getRole()==null)
      return null;
    return (UsrSupplierRole)get(UsrSupplierRole.class,getRole());
  }
  public void stRole(UsrSupplierRole role){
    if(role==null)
      setRole(null);
    else
      setRole(role.getPkey());
  }
  public String getLoginName(){
    return _loginName;
  }
  public void setLoginName(String loginName){
    _loginName=loginName;
  }
  public String getPassword(){
    return _password;
  }
  public void setPassword(String password){
    _password=password;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OStatus gtStatus(){
    return (OStatus)(OStatus.INIT.getLine().get(_status));
  }
  public void stStatus(OStatus status){
    _status=status.getLine().getKey();
  }
  public Integer getApprBy(){
    return _apprBy;
  }
  public void setApprBy(Integer apprBy){
    _apprBy=apprBy;
  }
  public SysUser gtApprBy(){
    if(getApprBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getApprBy());
  }
  public void stApprBy(SysUser apprBy){
    if(apprBy==null)
      setApprBy(null);
    else
      setApprBy(apprBy.getPkey());
  }
  public Date getApprTime(){
    return _apprTime;
  }
  public void setApprTime(Date apprTime){
    _apprTime=apprTime;
  }
  public String getReason(){
    return _reason;
  }
  public void setReason(String reason){
    _reason=reason;
  }
  public Byte getStoreStatus(){
    return _storeStatus;
  }
  public void setStoreStatus(Byte storeStatus){
    _storeStatus=storeStatus;
  }
  public SStatus gtStoreStatus(){
    return (SStatus)(SStatus.DOWN.getLine().get(_storeStatus));
  }
  public void stStoreStatus(SStatus storeStatus){
    _storeStatus=storeStatus.getLine().getKey();
  }
  public String getEnglishName(){
    return _englishName;
  }
  public void setEnglishName(String englishName){
    _englishName=englishName;
  }
  public String getAnnualProduction(){
    return _annualProduction;
  }
  public void setAnnualProduction(String annualProduction){
    _annualProduction=annualProduction;
  }
  public String getPostcode(){
    return _postcode;
  }
  public void setPostcode(String postcode){
    _postcode=postcode;
  }
  public String getTargetedMarket(){
    return _targetedMarket;
  }
  public void setTargetedMarket(String targetedMarket){
    _targetedMarket=targetedMarket;
  }
  public String getContactEmail(){
    return _contactEmail;
  }
  public void setContactEmail(String contactEmail){
    _contactEmail=contactEmail;
  }
  public Date getApplicationTime(){
    return _applicationTime;
  }
  public void setApplicationTime(Date applicationTime){
    _applicationTime=applicationTime;
  }
  public Integer getUserId(){
    return _userId;
  }
  public void setUserId(Integer userId){
    _userId=userId;
  }
  public UsrMain gtUserId(){
    if(getUserId()==null)
      return null;
    return (UsrMain)get(UsrMain.class,getUserId());
  }
  public void stUserId(UsrMain userId){
    if(userId==null)
      setUserId(null);
    else
      setUserId(userId.getPkey());
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getRegisteredCapital(){
    return _registeredCapital;
  }
  public void setRegisteredCapital(String registeredCapital){
    _registeredCapital=registeredCapital;
  }
  public Integer getCategory(){
    return _category;
  }
  public void setCategory(Integer category){
    _category=category;
  }
  public UsrSupplierCategory gtCategory(){
    if(getCategory()==null)
      return null;
    return (UsrSupplierCategory)get(UsrSupplierCategory.class,getCategory());
  }
  public void stCategory(UsrSupplierCategory category){
    if(category==null)
      setCategory(null);
    else
      setCategory(category.getPkey());
  }
  public Byte getIsAuth(){
    return _isAuth;
  }
  public void setIsAuth(Byte isAuth){
    _isAuth=isAuth;
  }
  public OIsAuth gtIsAuth(){
    return (OIsAuth)(OIsAuth.NO.getLine().get(_isAuth));
  }
  public void stIsAuth(OIsAuth isAuth){
    _isAuth=isAuth.getLine().getKey();
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
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
  public String getSeoContent(){
    return _seoContent;
  }
  public void setSeoContent(String seoContent){
    _seoContent=seoContent;
  }
  public JSONObject gtSeoContent() throws JSONException {
    return getSeoContent()==null?new JSONObject():new JSONObject(getSeoContent());
  }
  public void stSeoContent(JSONObject seoContent){
    setSeoContent(seoContent==null?null:seoContent.toString());
  }
  public String getSeoContent(FldLanguage.Language l) throws JSONException {
    return gtSeoContent().has(l.name())?gtSeoContent().getString(l.name()):"";
  }
  public void setSeoContent(String seoContent, FldLanguage.Language l) throws JSONException {
    stSeoContent(gtSeoContent().put(l.name(), seoContent));
  }
  public Date getAuthTime(){
    return _authTime;
  }
  public void setAuthTime(Date authTime){
    _authTime=authTime;
  }
  public String getShowName(){
    return _showName;
  }
  public void setShowName(String showName){
    _showName=showName;
  }
  public JSONObject gtShowName() throws JSONException {
    return getShowName()==null?new JSONObject():new JSONObject(getShowName());
  }
  public void stShowName(JSONObject showName){
    setShowName(showName==null?null:showName.toString());
  }
  public String getShowName(FldLanguage.Language l) throws JSONException {
    return gtShowName().has(l.name())?gtShowName().getString(l.name()):"";
  }
  public void setShowName(String showName, FldLanguage.Language l) throws JSONException {
    stShowName(gtShowName().put(l.name(), showName));
  }
  public String getEntity(){
    return _entity;
  }
  public void setEntity(String entity){
    _entity=entity;
  }
  public String getCompanyType(){
    return _companyType;
  }
  public void setCompanyType(String companyType){
    _companyType=companyType;
  }
  public JSONObject gtCompanyType() throws JSONException {
    return getCompanyType()==null?new JSONObject():new JSONObject(getCompanyType());
  }
  public void stCompanyType(JSONObject companyType){
    setCompanyType(companyType==null?null:companyType.toString());
  }
  public String getCompanyType(FldLanguage.Language l) throws JSONException {
    return gtCompanyType().has(l.name())?gtCompanyType().getString(l.name()):"";
  }
  public void setCompanyType(String companyType, FldLanguage.Language l) throws JSONException {
    stCompanyType(gtCompanyType().put(l.name(), companyType));
  }
  public String getCompanyNature(){
    return _companyNature;
  }
  public void setCompanyNature(String companyNature){
    _companyNature=companyNature;
  }
  public JSONObject gtCompanyNature() throws JSONException {
    return getCompanyNature()==null?new JSONObject():new JSONObject(getCompanyNature());
  }
  public void stCompanyNature(JSONObject companyNature){
    setCompanyNature(companyNature==null?null:companyNature.toString());
  }
  public String getCompanyNature(FldLanguage.Language l) throws JSONException {
    return gtCompanyNature().has(l.name())?gtCompanyNature().getString(l.name()):"";
  }
  public void setCompanyNature(String companyNature, FldLanguage.Language l) throws JSONException {
    stCompanyNature(gtCompanyNature().put(l.name(), companyNature));
  }
  public String getCreditCode(){
    return _creditCode;
  }
  public void setCreditCode(String creditCode){
    _creditCode=creditCode;
  }
  public Date getCompanyEstablishTime(){
    return _companyEstablishTime;
  }
  public void setCompanyEstablishTime(Date companyEstablishTime){
    _companyEstablishTime=companyEstablishTime;
  }
  public String getOperationTerm(){
    return _operationTerm;
  }
  public void setOperationTerm(String operationTerm){
    _operationTerm=operationTerm;
  }
  public String getMainSalesArea(){
    return _mainSalesArea;
  }
  public void setMainSalesArea(String mainSalesArea){
    _mainSalesArea=mainSalesArea;
  }
  public JSONObject gtMainSalesArea() throws JSONException {
    return getMainSalesArea()==null?new JSONObject():new JSONObject(getMainSalesArea());
  }
  public void stMainSalesArea(JSONObject mainSalesArea){
    setMainSalesArea(mainSalesArea==null?null:mainSalesArea.toString());
  }
  public String getMainSalesArea(FldLanguage.Language l) throws JSONException {
    return gtMainSalesArea().has(l.name())?gtMainSalesArea().getString(l.name()):"";
  }
  public void setMainSalesArea(String mainSalesArea, FldLanguage.Language l) throws JSONException {
    stMainSalesArea(gtMainSalesArea().put(l.name(), mainSalesArea));
  }
  public String getMainProd(){
    return _mainProd;
  }
  public void setMainProd(String mainProd){
    _mainProd=mainProd;
  }
  public JSONObject gtMainProd() throws JSONException {
    return getMainProd()==null?new JSONObject():new JSONObject(getMainProd());
  }
  public void stMainProd(JSONObject mainProd){
    setMainProd(mainProd==null?null:mainProd.toString());
  }
  public String getMainProd(FldLanguage.Language l) throws JSONException {
    return gtMainProd().has(l.name())?gtMainProd().getString(l.name()):"";
  }
  public void setMainProd(String mainProd, FldLanguage.Language l) throws JSONException {
    stMainProd(gtMainProd().put(l.name(), mainProd));
  }
  public String getProdPattern(){
    return _prodPattern;
  }
  public void setProdPattern(String prodPattern){
    _prodPattern=prodPattern;
  }
  public JSONObject gtProdPattern() throws JSONException {
    return getProdPattern()==null?new JSONObject():new JSONObject(getProdPattern());
  }
  public void stProdPattern(JSONObject prodPattern){
    setProdPattern(prodPattern==null?null:prodPattern.toString());
  }
  public String getProdPattern(FldLanguage.Language l) throws JSONException {
    return gtProdPattern().has(l.name())?gtProdPattern().getString(l.name()):"";
  }
  public void setProdPattern(String prodPattern, FldLanguage.Language l) throws JSONException {
    stProdPattern(gtProdPattern().put(l.name(), prodPattern));
  }
  public String getCompanyAddr(){
    return _companyAddr;
  }
  public void setCompanyAddr(String companyAddr){
    _companyAddr=companyAddr;
  }
  public JSONObject gtCompanyAddr() throws JSONException {
    return getCompanyAddr()==null?new JSONObject():new JSONObject(getCompanyAddr());
  }
  public void stCompanyAddr(JSONObject companyAddr){
    setCompanyAddr(companyAddr==null?null:companyAddr.toString());
  }
  public String getCompanyAddr(FldLanguage.Language l) throws JSONException {
    return gtCompanyAddr().has(l.name())?gtCompanyAddr().getString(l.name()):"";
  }
  public void setCompanyAddr(String companyAddr, FldLanguage.Language l) throws JSONException {
    stCompanyAddr(gtCompanyAddr().put(l.name(), companyAddr));
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public String getEmail(){
    return _email;
  }
  public void setEmail(String email){
    _email=email;
  }
  public String getBusinessLicenseBeginTime(){
    return _businessLicenseBeginTime;
  }
  public void setBusinessLicenseBeginTime(String businessLicenseBeginTime){
    _businessLicenseBeginTime=businessLicenseBeginTime;
  }
  public String getBusinessLicenseEndTime(){
    return _businessLicenseEndTime;
  }
  public void setBusinessLicenseEndTime(String businessLicenseEndTime){
    _businessLicenseEndTime=businessLicenseEndTime;
  }
  public Byte getBusinessLicenseIsSecular(){
    return _businessLicenseIsSecular;
  }
  public void setBusinessLicenseIsSecular(Byte businessLicenseIsSecular){
    _businessLicenseIsSecular=businessLicenseIsSecular;
  }
  public Boolean gtBusinessLicenseIsSecular(){
    return byteToBoolean(_businessLicenseIsSecular);
  }
  public void stBusinessLicenseIsSecular(Boolean businessLicenseIsSecular){
    _businessLicenseIsSecular=booleanToByte(businessLicenseIsSecular);
  }
  public String getTelephone(){
    return _telephone;
  }
  public void setTelephone(String telephone){
    _telephone=telephone;
  }
  public String getFax(){
    return _fax;
  }
  public void setFax(String fax){
    _fax=fax;
  }
  public String getQq(){
    return _qq;
  }
  public void setQq(String qq){
    _qq=qq;
  }
  public String getCertPhoto(){
    return _certPhoto;
  }
  public void setCertPhoto(String certPhoto){
    _certPhoto=certPhoto;
  }
  public String getIdCardFrontPhoto(){
    return _idCardFrontPhoto;
  }
  public void setIdCardFrontPhoto(String idCardFrontPhoto){
    _idCardFrontPhoto=idCardFrontPhoto;
  }
  public String getIdCardBackPhoto(){
    return _idCardBackPhoto;
  }
  public void setIdCardBackPhoto(String idCardBackPhoto){
    _idCardBackPhoto=idCardBackPhoto;
  }
  public String getCoopCertPhoto(){
    return _coopCertPhoto;
  }
  public void setCoopCertPhoto(String coopCertPhoto){
    _coopCertPhoto=coopCertPhoto;
  }
  public String getTaxpayerType(){
    return _taxpayerType;
  }
  public void setTaxpayerType(String taxpayerType){
    _taxpayerType=taxpayerType;
  }
  public String getIdCard(){
    return _idCard;
  }
  public void setIdCard(String idCard){
    _idCard=idCard;
  }
  public String getOperateIdCard(){
    return _operateIdCard;
  }
  public void setOperateIdCard(String operateIdCard){
    _operateIdCard=operateIdCard;
  }
  public String getContacts(){
    return _contacts;
  }
  public void setContacts(String contacts){
    _contacts=contacts;
  }
  public String getPhone(){
    return _phone;
  }
  public void setPhone(String phone){
    _phone=phone;
  }
  public String getSettlementBank(){
    return _settlementBank;
  }
  public void setSettlementBank(String settlementBank){
    _settlementBank=settlementBank;
  }
  public String getBankAccount(){
    return _bankAccount;
  }
  public void setBankAccount(String bankAccount){
    _bankAccount=bankAccount;
  }
  public String getBankBranch(){
    return _bankBranch;
  }
  public void setBankBranch(String bankBranch){
    _bankBranch=bankBranch;
  }
  public Integer getBankCountry(){
    return _bankCountry;
  }
  public void setBankCountry(Integer bankCountry){
    _bankCountry=bankCountry;
  }
  public PltCountry gtBankCountry(){
    if(getBankCountry()==null)
      return null;
    return (PltCountry)get(PltCountry.class,getBankCountry());
  }
  public void stBankCountry(PltCountry bankCountry){
    if(bankCountry==null)
      setBankCountry(null);
    else
      setBankCountry(bankCountry.getPkey());
  }
  public Integer getBankProvince(){
    return _bankProvince;
  }
  public void setBankProvince(Integer bankProvince){
    _bankProvince=bankProvince;
  }
  public PltProvince gtBankProvince(){
    if(getBankProvince()==null)
      return null;
    return (PltProvince)get(PltProvince.class,getBankProvince());
  }
  public void stBankProvince(PltProvince bankProvince){
    if(bankProvince==null)
      setBankProvince(null);
    else
      setBankProvince(bankProvince.getPkey());
  }
  public String getContactsIdCardFrontPhoto(){
    return _contactsIdCardFrontPhoto;
  }
  public void setContactsIdCardFrontPhoto(String contactsIdCardFrontPhoto){
    _contactsIdCardFrontPhoto=contactsIdCardFrontPhoto;
  }
  public String getContactsIdCardBackPhoto(){
    return _contactsIdCardBackPhoto;
  }
  public void setContactsIdCardBackPhoto(String contactsIdCardBackPhoto){
    _contactsIdCardBackPhoto=contactsIdCardBackPhoto;
  }
  public String getBusinessTyp(){
    return _businessTyp;
  }
  public void setBusinessTyp(String businessTyp){
    _businessTyp=businessTyp;
  }
  public JSONObject gtBusinessTyp() throws JSONException {
    return getBusinessTyp()==null?new JSONObject():new JSONObject(getBusinessTyp());
  }
  public void stBusinessTyp(JSONObject businessTyp){
    setBusinessTyp(businessTyp==null?null:businessTyp.toString());
  }
  public String getBusinessTyp(FldLanguage.Language l) throws JSONException {
    return gtBusinessTyp().has(l.name())?gtBusinessTyp().getString(l.name()):"";
  }
  public void setBusinessTyp(String businessTyp, FldLanguage.Language l) throws JSONException {
    stBusinessTyp(gtBusinessTyp().put(l.name(), businessTyp));
  }
  public String getLocation(){
    return _location;
  }
  public void setLocation(String location){
    _location=location;
  }
  public JSONObject gtLocation() throws JSONException {
    return getLocation()==null?new JSONObject():new JSONObject(getLocation());
  }
  public void stLocation(JSONObject location){
    setLocation(location==null?null:location.toString());
  }
  public String getLocation(FldLanguage.Language l) throws JSONException {
    return gtLocation().has(l.name())?gtLocation().getString(l.name()):"";
  }
  public void setLocation(String location, FldLanguage.Language l) throws JSONException {
    stLocation(gtLocation().put(l.name(), location));
  }
  public String getProduction(){
    return _production;
  }
  public void setProduction(String production){
    _production=production;
  }
  public JSONObject gtProduction() throws JSONException {
    return getProduction()==null?new JSONObject():new JSONObject(getProduction());
  }
  public void stProduction(JSONObject production){
    setProduction(production==null?null:production.toString());
  }
  public String getProduction(FldLanguage.Language l) throws JSONException {
    return gtProduction().has(l.name())?gtProduction().getString(l.name()):"";
  }
  public void setProduction(String production, FldLanguage.Language l) throws JSONException {
    stProduction(gtProduction().put(l.name(), production));
  }
  public String getDeveloper(){
    return _developer;
  }
  public void setDeveloper(String developer){
    _developer=developer;
  }
  public JSONObject gtDeveloper() throws JSONException {
    return getDeveloper()==null?new JSONObject():new JSONObject(getDeveloper());
  }
  public void stDeveloper(JSONObject developer){
    setDeveloper(developer==null?null:developer.toString());
  }
  public String getDeveloper(FldLanguage.Language l) throws JSONException {
    return gtDeveloper().has(l.name())?gtDeveloper().getString(l.name()):"";
  }
  public void setDeveloper(String developer, FldLanguage.Language l) throws JSONException {
    stDeveloper(gtDeveloper().put(l.name(), developer));
  }
  public String getTotalEmployees(){
    return _totalEmployees;
  }
  public void setTotalEmployees(String totalEmployees){
    _totalEmployees=totalEmployees;
  }
  public JSONObject gtTotalEmployees() throws JSONException {
    return getTotalEmployees()==null?new JSONObject():new JSONObject(getTotalEmployees());
  }
  public void stTotalEmployees(JSONObject totalEmployees){
    setTotalEmployees(totalEmployees==null?null:totalEmployees.toString());
  }
  public String getTotalEmployees(FldLanguage.Language l) throws JSONException {
    return gtTotalEmployees().has(l.name())?gtTotalEmployees().getString(l.name()):"";
  }
  public void setTotalEmployees(String totalEmployees, FldLanguage.Language l) throws JSONException {
    stTotalEmployees(gtTotalEmployees().put(l.name(), totalEmployees));
  }
  public String getAnnualSales(){
    return _annualSales;
  }
  public void setAnnualSales(String annualSales){
    _annualSales=annualSales;
  }
  public JSONObject gtAnnualSales() throws JSONException {
    return getAnnualSales()==null?new JSONObject():new JSONObject(getAnnualSales());
  }
  public void stAnnualSales(JSONObject annualSales){
    setAnnualSales(annualSales==null?null:annualSales.toString());
  }
  public String getAnnualSales(FldLanguage.Language l) throws JSONException {
    return gtAnnualSales().has(l.name())?gtAnnualSales().getString(l.name()):"";
  }
  public void setAnnualSales(String annualSales, FldLanguage.Language l) throws JSONException {
    stAnnualSales(gtAnnualSales().put(l.name(), annualSales));
  }
  public String getTop3Markets(){
    return _top3Markets;
  }
  public void setTop3Markets(String top3Markets){
    _top3Markets=top3Markets;
  }
  public JSONObject gtTop3Markets() throws JSONException {
    return getTop3Markets()==null?new JSONObject():new JSONObject(getTop3Markets());
  }
  public void stTop3Markets(JSONObject top3Markets){
    setTop3Markets(top3Markets==null?null:top3Markets.toString());
  }
  public String getTop3Markets(FldLanguage.Language l) throws JSONException {
    return gtTop3Markets().has(l.name())?gtTop3Markets().getString(l.name()):"";
  }
  public void setTop3Markets(String top3Markets, FldLanguage.Language l) throws JSONException {
    stTop3Markets(gtTop3Markets().put(l.name(), top3Markets));
  }
  public String getMaterials(){
    return _materials;
  }
  public void setMaterials(String materials){
    _materials=materials;
  }
  public JSONObject gtMaterials() throws JSONException {
    return getMaterials()==null?new JSONObject():new JSONObject(getMaterials());
  }
  public void stMaterials(JSONObject materials){
    setMaterials(materials==null?null:materials.toString());
  }
  public String getMaterials(FldLanguage.Language l) throws JSONException {
    return gtMaterials().has(l.name())?gtMaterials().getString(l.name()):"";
  }
  public void setMaterials(String materials, FldLanguage.Language l) throws JSONException {
    stMaterials(gtMaterials().put(l.name(), materials));
  }
  public String getHeadPic(){
    return _headPic;
  }
  public void setHeadPic(String headPic){
    _headPic=headPic;
  }
  public String getDepartment(){
    return _department;
  }
  public void setDepartment(String department){
    _department=department;
  }
  public JSONObject gtDepartment() throws JSONException {
    return getDepartment()==null?new JSONObject():new JSONObject(getDepartment());
  }
  public void stDepartment(JSONObject department){
    setDepartment(department==null?null:department.toString());
  }
  public String getDepartment(FldLanguage.Language l) throws JSONException {
    return gtDepartment().has(l.name())?gtDepartment().getString(l.name()):"";
  }
  public void setDepartment(String department, FldLanguage.Language l) throws JSONException {
    stDepartment(gtDepartment().put(l.name(), department));
  }
  public String getJobTitle(){
    return _jobTitle;
  }
  public void setJobTitle(String jobTitle){
    _jobTitle=jobTitle;
  }
  public JSONObject gtJobTitle() throws JSONException {
    return getJobTitle()==null?new JSONObject():new JSONObject(getJobTitle());
  }
  public void stJobTitle(JSONObject jobTitle){
    setJobTitle(jobTitle==null?null:jobTitle.toString());
  }
  public String getJobTitle(FldLanguage.Language l) throws JSONException {
    return gtJobTitle().has(l.name())?gtJobTitle().getString(l.name()):"";
  }
  public void setJobTitle(String jobTitle, FldLanguage.Language l) throws JSONException {
    stJobTitle(gtJobTitle().put(l.name(), jobTitle));
  }
  public String getWebsite(){
    return _website;
  }
  public void setWebsite(String website){
    _website=website;
  }
  public Integer getCountry(){
    return _country;
  }
  public void setCountry(Integer country){
    _country=country;
  }
  public PltCountry gtCountry(){
    if(getCountry()==null)
      return null;
    return (PltCountry)get(PltCountry.class,getCountry());
  }
  public void stCountry(PltCountry country){
    if(country==null)
      setCountry(null);
    else
      setCountry(country.getPkey());
  }
  public Integer getProvince(){
    return _province;
  }
  public void setProvince(Integer province){
    _province=province;
  }
  public PltProvince gtProvince(){
    if(getProvince()==null)
      return null;
    return (PltProvince)get(PltProvince.class,getProvince());
  }
  public void stProvince(PltProvince province){
    if(province==null)
      setProvince(null);
    else
      setProvince(province.getPkey());
  }
  public String getCity(){
    return _city;
  }
  public void setCity(String city){
    _city=city;
  }
  public JSONObject gtCity() throws JSONException {
    return getCity()==null?new JSONObject():new JSONObject(getCity());
  }
  public void stCity(JSONObject city){
    setCity(city==null?null:city.toString());
  }
  public String getCity(FldLanguage.Language l) throws JSONException {
    return gtCity().has(l.name())?gtCity().getString(l.name()):"";
  }
  public void setCity(String city, FldLanguage.Language l) throws JSONException {
    stCity(gtCity().put(l.name(), city));
  }
  public Byte getIsPro(){
    return _isPro;
  }
  public void setIsPro(Byte isPro){
    _isPro=isPro;
  }
  public Boolean gtIsPro(){
    return byteToBoolean(_isPro);
  }
  public void stIsPro(Boolean isPro){
    _isPro=booleanToByte(isPro);
  }
  public String getLogo(){
    return _logo;
  }
  public void setLogo(String logo){
    _logo=logo;
  }
  public String getSignBackgd(){
    return _signBackgd;
  }
  public void setSignBackgd(String signBackgd){
    _signBackgd=signBackgd;
  }
  public String getAdPhoto(){
    return _adPhoto;
  }
  public void setAdPhoto(String adPhoto){
    _adPhoto=adPhoto;
  }
  public JSONObject gtAdPhoto() throws JSONException {
    return getAdPhoto()==null?new JSONObject():new JSONObject(getAdPhoto());
  }
  public void stAdPhoto(JSONObject adPhoto){
    setAdPhoto(adPhoto==null?null:adPhoto.toString());
  }
  public String getAdPhoto(FldLanguage.Language l) throws JSONException {
    return gtAdPhoto().has(l.name())?gtAdPhoto().getString(l.name()):"";
  }
  public void setAdPhoto(String adPhoto, FldLanguage.Language l) throws JSONException {
    stAdPhoto(gtAdPhoto().put(l.name(), adPhoto));
  }
  public String getAdPhotoMobile(){
    return _adPhotoMobile;
  }
  public void setAdPhotoMobile(String adPhotoMobile){
    _adPhotoMobile=adPhotoMobile;
  }
  public String getAdPhotoLink(){
    return _adPhotoLink;
  }
  public void setAdPhotoLink(String adPhotoLink){
    _adPhotoLink=adPhotoLink;
  }
  public JSONObject gtAdPhotoLink() throws JSONException {
    return getAdPhotoLink()==null?new JSONObject():new JSONObject(getAdPhotoLink());
  }
  public void stAdPhotoLink(JSONObject adPhotoLink){
    setAdPhotoLink(adPhotoLink==null?null:adPhotoLink.toString());
  }
  public String getAdPhotoLink(FldLanguage.Language l) throws JSONException {
    return gtAdPhotoLink().has(l.name())?gtAdPhotoLink().getString(l.name()):"";
  }
  public void setAdPhotoLink(String adPhotoLink, FldLanguage.Language l) throws JSONException {
    stAdPhotoLink(gtAdPhotoLink().put(l.name(), adPhotoLink));
  }
  public String getCompanyPhoto(){
    return _companyPhoto;
  }
  public void setCompanyPhoto(String companyPhoto){
    _companyPhoto=companyPhoto;
  }
  public JSONObject gtCompanyPhoto() throws JSONException {
    return getCompanyPhoto()==null?new JSONObject():new JSONObject(getCompanyPhoto());
  }
  public void stCompanyPhoto(JSONObject companyPhoto){
    setCompanyPhoto(companyPhoto==null?null:companyPhoto.toString());
  }
  public String getCompanyPhoto(FldLanguage.Language l) throws JSONException {
    return gtCompanyPhoto().has(l.name())?gtCompanyPhoto().getString(l.name()):"";
  }
  public void setCompanyPhoto(String companyPhoto, FldLanguage.Language l) throws JSONException {
    stCompanyPhoto(gtCompanyPhoto().put(l.name(), companyPhoto));
  }
  public String getCompanyPhotoLink(){
    return _companyPhotoLink;
  }
  public void setCompanyPhotoLink(String companyPhotoLink){
    _companyPhotoLink=companyPhotoLink;
  }
  public JSONObject gtCompanyPhotoLink() throws JSONException {
    return getCompanyPhotoLink()==null?new JSONObject():new JSONObject(getCompanyPhotoLink());
  }
  public void stCompanyPhotoLink(JSONObject companyPhotoLink){
    setCompanyPhotoLink(companyPhotoLink==null?null:companyPhotoLink.toString());
  }
  public String getCompanyPhotoLink(FldLanguage.Language l) throws JSONException {
    return gtCompanyPhotoLink().has(l.name())?gtCompanyPhotoLink().getString(l.name()):"";
  }
  public void setCompanyPhotoLink(String companyPhotoLink, FldLanguage.Language l) throws JSONException {
    stCompanyPhotoLink(gtCompanyPhotoLink().put(l.name(), companyPhotoLink));
  }
  public String getHomePageDiy(){
    return _homePageDiy;
  }
  public void setHomePageDiy(String homePageDiy){
    _homePageDiy=homePageDiy;
  }
  public JSONObject gtHomePageDiy() throws JSONException {
    return getHomePageDiy()==null?new JSONObject():new JSONObject(getHomePageDiy());
  }
  public void stHomePageDiy(JSONObject homePageDiy){
    setHomePageDiy(homePageDiy==null?null:homePageDiy.toString());
  }
  public String getHomePageDiy(FldLanguage.Language l) throws JSONException {
    return gtHomePageDiy().has(l.name())?gtHomePageDiy().getString(l.name()):"";
  }
  public void setHomePageDiy(String homePageDiy, FldLanguage.Language l) throws JSONException {
    stHomePageDiy(gtHomePageDiy().put(l.name(), homePageDiy));
  }
  public String getProductPageDiy(){
    return _productPageDiy;
  }
  public void setProductPageDiy(String productPageDiy){
    _productPageDiy=productPageDiy;
  }
  public JSONObject gtProductPageDiy() throws JSONException {
    return getProductPageDiy()==null?new JSONObject():new JSONObject(getProductPageDiy());
  }
  public void stProductPageDiy(JSONObject productPageDiy){
    setProductPageDiy(productPageDiy==null?null:productPageDiy.toString());
  }
  public String getProductPageDiy(FldLanguage.Language l) throws JSONException {
    return gtProductPageDiy().has(l.name())?gtProductPageDiy().getString(l.name()):"";
  }
  public void setProductPageDiy(String productPageDiy, FldLanguage.Language l) throws JSONException {
    stProductPageDiy(gtProductPageDiy().put(l.name(), productPageDiy));
  }
  public String getContactPageDiy(){
    return _contactPageDiy;
  }
  public void setContactPageDiy(String contactPageDiy){
    _contactPageDiy=contactPageDiy;
  }
  public JSONObject gtContactPageDiy() throws JSONException {
    return getContactPageDiy()==null?new JSONObject():new JSONObject(getContactPageDiy());
  }
  public void stContactPageDiy(JSONObject contactPageDiy){
    setContactPageDiy(contactPageDiy==null?null:contactPageDiy.toString());
  }
  public String getContactPageDiy(FldLanguage.Language l) throws JSONException {
    return gtContactPageDiy().has(l.name())?gtContactPageDiy().getString(l.name()):"";
  }
  public void setContactPageDiy(String contactPageDiy, FldLanguage.Language l) throws JSONException {
    stContactPageDiy(gtContactPageDiy().put(l.name(), contactPageDiy));
  }
  public String getCompanyIntroductionPageCustomDecoration(){
    return _companyIntroductionPageCustomDecoration;
  }
  public void setCompanyIntroductionPageCustomDecoration(String companyIntroductionPageCustomDecoration){
    _companyIntroductionPageCustomDecoration=companyIntroductionPageCustomDecoration;
  }
  public JSONObject gtCompanyIntroductionPageCustomDecoration() throws JSONException {
    return getCompanyIntroductionPageCustomDecoration()==null?new JSONObject():new JSONObject(getCompanyIntroductionPageCustomDecoration());
  }
  public void stCompanyIntroductionPageCustomDecoration(JSONObject companyIntroductionPageCustomDecoration){
    setCompanyIntroductionPageCustomDecoration(companyIntroductionPageCustomDecoration==null?null:companyIntroductionPageCustomDecoration.toString());
  }
  public String getCompanyIntroductionPageCustomDecoration(FldLanguage.Language l) throws JSONException {
    return gtCompanyIntroductionPageCustomDecoration().has(l.name())?gtCompanyIntroductionPageCustomDecoration().getString(l.name()):"";
  }
  public void setCompanyIntroductionPageCustomDecoration(String companyIntroductionPageCustomDecoration, FldLanguage.Language l) throws JSONException {
    stCompanyIntroductionPageCustomDecoration(gtCompanyIntroductionPageCustomDecoration().put(l.name(), companyIntroductionPageCustomDecoration));
  }
  public String getHomePageDiyMobile(){
    return _homePageDiyMobile;
  }
  public void setHomePageDiyMobile(String homePageDiyMobile){
    _homePageDiyMobile=homePageDiyMobile;
  }
  public String getProductPageDiyMobile(){
    return _productPageDiyMobile;
  }
  public void setProductPageDiyMobile(String productPageDiyMobile){
    _productPageDiyMobile=productPageDiyMobile;
  }
  public String getContactPageDiyMobile(){
    return _contactPageDiyMobile;
  }
  public void setContactPageDiyMobile(String contactPageDiyMobile){
    _contactPageDiyMobile=contactPageDiyMobile;
  }
  public Byte getHomePageOn(){
    return _homePageOn;
  }
  public void setHomePageOn(Byte homePageOn){
    _homePageOn=homePageOn;
  }
  public Boolean gtHomePageOn(){
    return byteToBoolean(_homePageOn);
  }
  public void stHomePageOn(Boolean homePageOn){
    _homePageOn=booleanToByte(homePageOn);
  }
  public Byte getProductPageOn(){
    return _productPageOn;
  }
  public void setProductPageOn(Byte productPageOn){
    _productPageOn=productPageOn;
  }
  public Boolean gtProductPageOn(){
    return byteToBoolean(_productPageOn);
  }
  public void stProductPageOn(Boolean productPageOn){
    _productPageOn=booleanToByte(productPageOn);
  }
  public Byte getContactPageOn(){
    return _contactPageOn;
  }
  public void setContactPageOn(Byte contactPageOn){
    _contactPageOn=contactPageOn;
  }
  public Boolean gtContactPageOn(){
    return byteToBoolean(_contactPageOn);
  }
  public void stContactPageOn(Boolean contactPageOn){
    _contactPageOn=booleanToByte(contactPageOn);
  }
  public Byte getBottomHomeProductsOn(){
    return _bottomHomeProductsOn;
  }
  public void setBottomHomeProductsOn(Byte bottomHomeProductsOn){
    _bottomHomeProductsOn=bottomHomeProductsOn;
  }
  public Boolean gtBottomHomeProductsOn(){
    return byteToBoolean(_bottomHomeProductsOn);
  }
  public void stBottomHomeProductsOn(Boolean bottomHomeProductsOn){
    _bottomHomeProductsOn=booleanToByte(bottomHomeProductsOn);
  }
  public Byte getHomePosterOn(){
    return _homePosterOn;
  }
  public void setHomePosterOn(Byte homePosterOn){
    _homePosterOn=homePosterOn;
  }
  public Boolean gtHomePosterOn(){
    return byteToBoolean(_homePosterOn);
  }
  public void stHomePosterOn(Boolean homePosterOn){
    _homePosterOn=booleanToByte(homePosterOn);
  }
  public Byte getHomeBusinessBigPosterOn(){
    return _homeBusinessBigPosterOn;
  }
  public void setHomeBusinessBigPosterOn(Byte homeBusinessBigPosterOn){
    _homeBusinessBigPosterOn=homeBusinessBigPosterOn;
  }
  public Boolean gtHomeBusinessBigPosterOn(){
    return byteToBoolean(_homeBusinessBigPosterOn);
  }
  public void stHomeBusinessBigPosterOn(Boolean homeBusinessBigPosterOn){
    _homeBusinessBigPosterOn=booleanToByte(homeBusinessBigPosterOn);
  }
  public Byte getCompanyIntroductionPageCustomDecorationOn(){
    return _companyIntroductionPageCustomDecorationOn;
  }
  public void setCompanyIntroductionPageCustomDecorationOn(Byte companyIntroductionPageCustomDecorationOn){
    _companyIntroductionPageCustomDecorationOn=companyIntroductionPageCustomDecorationOn;
  }
  public Boolean gtCompanyIntroductionPageCustomDecorationOn(){
    return byteToBoolean(_companyIntroductionPageCustomDecorationOn);
  }
  public void stCompanyIntroductionPageCustomDecorationOn(Boolean companyIntroductionPageCustomDecorationOn){
    _companyIntroductionPageCustomDecorationOn=booleanToByte(companyIntroductionPageCustomDecorationOn);
  }
  public String getTraceCode(){
    return _traceCode;
  }
  public void setTraceCode(String traceCode){
    _traceCode=traceCode;
  }
  public String getWebSizeTitle(){
    return _webSizeTitle;
  }
  public void setWebSizeTitle(String webSizeTitle){
    _webSizeTitle=webSizeTitle;
  }
  public String getWebSite(){
    return _webSite;
  }
  public void setWebSite(String webSite){
    _webSite=webSite;
  }
  public String getTongjiUrl(){
    return _tongjiUrl;
  }
  public void setTongjiUrl(String tongjiUrl){
    _tongjiUrl=tongjiUrl;
  }
  public String getTongjiPwd(){
    return _tongjiPwd;
  }
  public void setTongjiPwd(String tongjiPwd){
    _tongjiPwd=tongjiPwd;
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
}
