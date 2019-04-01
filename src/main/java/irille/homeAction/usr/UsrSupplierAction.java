package irille.homeAction.usr;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.shop.service.usr.UsrSupplierService;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin.UserType;
import irille.Service.Usr.IUsrSupplierService;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.Page_supplierView;
import irille.homeAction.usr.dto.ProductView;
import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex2;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.plt.PltProvince;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseDAO;
import irille.shop.usr.Usr;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrAnnex;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrProductCategoryDAO;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplier.T;
import irille.shop.usr.UsrSupplierCategoryDAO;
import irille.shop.usr.UsrSupplierDAO;
import irille.view.pdt.CategoryView;
import irille.view.usr.SupplierView;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static irille.pub.validate.Regular.REGULAR_NAME;

@Getter
@Setter
public class UsrSupplierAction extends HomeAction<UsrSupplier> implements ISupplierAction {

  private static final long serialVersionUID = 1L;

  private static final UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
  private Integer _length;
  private String category;
  private String sort;
  private Integer pageNumber;
  private List<ProductView> _productView;

  @Inject IUsrSupplierService usrSupplierService;

  public Integer getLength() {
    return _length;
  }

  public void setLength(Integer _length) {
    this._length = _length;
  }

  public List<ProductView> get_productView() {
    return _productView;
  }

  public void set_productView(List<ProductView> _productView) {
    this._productView = _productView;
  }

  @Getter @Setter private Integer purchasePkey;
  @Getter @Setter private FldLanguage.Language lang;
  @Getter @Setter private String certPhotoName;
  @Getter @Setter private String contactsIdCardFrontPhotoName;
  @Getter @Setter private String idCardFrontPhotoName;

  /**
   * 创建供应商信息
   *
   * @author: lingjian @Date: 2019/3/4 14:23
   */
  @NeedLogin(userType = UserType.SUPPLIER)
  public void insInfo() throws Exception {
    if (getUser().getUser_type() == 1) {
      regex(); // 正则校验
      getBean().setLoginName(getUser().getLoginName());
      UsrSupplier supplier = UsrSupplierDAO.insSupplierNo(getBean(), lang); // 没有多国语言翻译
      //      UsrSupplier supplier = UsrSupplierDAO.insSupplier(getBean()); //有多国语言翻译
      UsrAnnex annex = new UsrAnnex();
      if (supplier.getPkey() != null) {
        annex.setSupplier(supplier.getPkey());
        annex.setCertPhotoName(certPhotoName); // 资质证书复印件文件名
        annex.setIdCardFrontPhotoName(idCardFrontPhotoName); // 法人身份证复印件文件名
        annex.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName); // 运营负责人身份证复印件文件名
      }
      annex.ins();
      write();
    }
  }

  // 正则校验
  public void regex() throws Exception {
    ValidForm valid = new ValidForm(getBean());
    valid.validNotEmpty(
        UsrSupplier.T.NAME,
        UsrSupplier.T.ENGLISH_NAME,
        UsrSupplier.T.COMPANY_ADDR,
        UsrSupplier.T.TARGETED_MARKET,
        UsrSupplier.T.PROD_PATTERN,
        UsrSupplier.T.CREDIT_CODE,
        UsrSupplier.T.CERT_PHOTO);
    ValidRegex2 regex = new ValidRegex2(getBean());
    if (getBean().getEnglishName() != null){
      regex.validRegexMatched("^[a-zA-Z ]{1,60}$", "请填写英文字母,且不超过60位", UsrSupplier.T.ENGLISH_NAME);
    }
    if (getBean().getWebsite() != null){
      regex.validRegexMatched(
              "http[s]?:\\/\\/[\\w]{1,}.?[\\w]{1,}.?[\\w/.?&=-]{1,}",
              "请输入完整的网址格式，如https://www.shoestp.com",
              UsrSupplier.T.WEBSITE);
    }
    if (getBean().getAnnualProduction() != null){
      regex.validRegexMatched(
              "([1-9]\\d*|0)(\\.\\d*[1-9])?", "年产量请填写数字,不能以0开头", UsrSupplier.T.ANNUAL_PRODUCTION);
    }
    if (getBean().getTelephone() != null) {
      regex.validRegexMatched(
              "((\\d{3,4}-)?\\d{7,8})|(1\\d{10})", "请填写正确的固定电话格式", UsrSupplier.T.TELEPHONE);
    }
    if (getBean().getFax() != null) {
      regex.validRegexMatched("(\\d{3,4}-)?\\d{7,8}", "请填写正确传真格式", UsrSupplier.T.FAX);
    }
    if (getBean().getPostcode() != null){
      regex.validRegexMatched("[0-9]{6}", "邮编只能输入数字，且数字个数为6个", UsrSupplier.T.POSTCODE);
    }
    if (getBean().getRegisteredCapital() != null){
      regex.validRegexMatched(
              "([1-9]\\d*|0)(\\.\\d*[1-9])?", "注册资本请填写数字,不能以0开头", UsrSupplier.T.REGISTERED_CAPITAL);
    }
    if (getBean().getEntity() != null){
      regex.validRegexMatched(
              "[\\u4e00-\\u9fa5]{2,6}", "法定代表人只能输入中文，且个数为2~6个", UsrSupplier.T.ENTITY);
    }
    if (getBean().getContacts() != null){
      regex.validRegexMatched(REGULAR_NAME, "联系人姓名首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.CONTACTS);
    }
    if (getBean().getDepartment() != null){
      regex.validRegexMatched(REGULAR_NAME, "联系人部门首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.DEPARTMENT);
    }
    if (getBean().getJobTitle() != null){
      regex.validRegexMatched(REGULAR_NAME, "联系人职称首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.JOB_TITLE);
    }
    if (getBean().getPhone() != null) {
      regex.validRegexMatched("1\\d{10}", "请填写11位手机格式的号码", UsrSupplier.T.PHONE);
    }
    if (getBean().getContactEmail() != null){
      regex.validRegexMatched(
              "^[\\w]{1,32}@+\\w{1,15}.\\w{2,5}$", "联系人邮箱请填写正确的邮箱格式", UsrSupplier.T.CONTACT_EMAIL);
    }
    if (getBean().getIdCard() != null){
      regex.validRegexMatched(
              "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", "请输入正确的18位身份证号码", UsrSupplier.T.ID_CARD);
    }
    if (getBean().getOperateIdCard() != null){
      regex.validRegexMatched(
              "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)",
              "请输入正确的18位身份证号码",
              UsrSupplier.T.OPERATE_ID_CARD);
    }
  }

  /**
   * 获取供应商信息
   *
   * @author: lingjian @Date: 2019/3/5 16:19
   */
  @NeedLogin(userType = UserType.SUPPLIER)
  public void loadOnlineSup() throws Exception {
    SQL sql1 =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(T.UserId, " =? ", purchasePkey);
          }
        };
    List<UsrSupplier> supplier = Query.sql(sql1).queryList(UsrSupplier.class);

    SQL sql = new SQL();
    JSONObject json = null;
    if (null != supplier && supplier.size() > 0) {
      sql.SELECT(UsrAnnex.class)
          .FROM(UsrAnnex.class)
          .WHERE(UsrAnnex.T.SUPPLIER, " =? ", supplier.get(0).getPkey());
      SqlQuery query = Query.sql(sql);
      if (crtJsonByBean(supplier.get(0)) != null) {
        json = crtJsonByBean(supplier.get(0));
      }
      Map<String, Object> obj = query.queryMap();

      JSONObject j = new JSONObject();
      for (String key : obj.keySet()) {
        j.put(key, obj.get(key));
      }
      json.put("annex", j);
    }
    writerOrExport(json);
  }

  /**
   * 更新供应商信息
   *
   * @author: lingjian @Date: 2019/3/1 15:49
   */
  @NeedLogin(userType = UserType.SUPPLIER)
  public void updInfo() throws Exception {
    try {
      regex(); // 正则校验
      UsrAnnex annex = UsrAnnex.chkUniqueSupplier(false, getBean().getPkey());
      if (getBean().getPkey() != null) {
        annex.setCertPhotoName(certPhotoName);
        annex.setIdCardFrontPhotoName(idCardFrontPhotoName);
        annex.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName);
      }
      UsrMain main = BeanBase.load(UsrMain.class, getBean().getUserid());
      if (main != null) {
        main.setCompany(getBean().getName());
        main.setAddress(getBean().getCompanyAddr());
        main.setContacts(getBean().getContacts());
        main.setTelphone(getBean().getPhone());
        main.upd();
      }
      UsrSupplier newSupplier = UsrSupplierDAO.updInfoNo(getBean(), lang); // 没有多国语言翻译
      //      UsrSupplier newSupplier = UsrSupplierDAO.updInfo(getBean()); //有多国语言翻译
      newSupplier.stStatus(OStatus.INIT);
      newSupplier.stStoreStatus(Usr.SStatus.DOWN);
      newSupplier.upd();
      annex.upd();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 返回店铺首页
   *
   * @author liyichao
   */
  public String gtSupIndex() {
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 1));
    setResult("/home/shop-index.jsp");
    return HomeAction.TRENDS;
  }

  private double pageAll;
  private Integer type;

  private String catName;

  private SupplierView supView;

  /**
   * 返回供应商产品页面|根据产品分类和页码搜索产品|根据Most Popular/Sales/Favorites/New/Price进行排序
   *
   * @throws JSONException
   * @author liyichao
   */
  private List<UsrProductCategory> topDiyCat = new ArrayList<UsrProductCategory>();

  public List<UsrProductCategory> getTopDiyCat() {
    return topDiyCat;
  }

  public void setTopDiyCat(List<UsrProductCategory> topDiyCat) {
    this.topDiyCat = topDiyCat;
  }

  public String gtSupPro()
      throws JSONException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 2));
    setTopDiyCat(UsrProductCategoryDAO.Sellect.getTopCat(curLanguage(), getPkey()));
    setResult("/home/shop-productCenter.jsp");
    return HomeAction.TRENDS;
  }

  /**
   * 返回供应商信息页面
   *
   * @author liyichao
   */
  public String gtSupInfo()
      throws JSONException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 3));
    setResult("/home/shop-company.jsp");
    return HomeAction.TRENDS;
  }

  /**
   * 返回联系供应商页面
   *
   * @author liyichao
   */
  public String gtSupContact()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          JSONException {
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 3));
    setResult("/home/shop-contactUs.jsp");
    return HomeAction.TRENDS;
  }

  private List<PrmGroupPurchase> unionList;
  private String groupState;
  private List<CategoryView> catList;

  /**
   * 返回联合采购页面
   *
   * @author liyichao
   */
  public String gtSupGroup() throws JSONException {
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 1));
    PrmGroupPurchaseDAO.GroupList group = new PrmGroupPurchaseDAO.GroupList();
    group.setSellerPkey((Integer) getPkey());
    group.commit();
    unionList = group.getGroupList();
    setCatList(PdtCatDAO.listTopCatView(curLanguage()));
    setResult("/home/groupPurchase.jsp");
    return TRENDS;
  }

  /**
   * 获取指定供应商数据
   *
   * @author liyichao
   */
  private UsrSupplierDAO.GtSup getSup() {
    UsrSupplierDAO.GtSup gtSup = new UsrSupplierDAO.GtSup();
    gtSup.setBKey(getPkey());
    gtSup.commit();
    _bean = gtSup.getB();
    return gtSup;
  }

  /** 供应商列表页面 Created by IntelliJ IDEA. User: Passxml@gmail.com Date: 2018/7/19 Time: 15:46 */
  private int _cated = -1;

  public int getCated() {
    return _cated;
  }

  public void setCated(int cated) {
    this._cated = cated;
  }

  private Page_supplierView _supplierDto;

  public Page_supplierView getSupplierDto() {
    return _supplierDto;
  }

  public void setSupplierDto(Page_supplierView supplierDto) {
    this._supplierDto = supplierDto;
  }

  /**
   * * 获取供应商列表信息 待优化，目标：json数据返回
   *
   * @author Passxml@gmail.com
   * @param
   * @return
   * @date 2018/7/20 10:14
   */
  @Override
  public String execute() throws Exception {
    IduPage iduPage = new IduPage();
    iduPage.setStart(0);
    iduPage.setLimit(3);
    iduPage.setWhere(String.valueOf(getCated()));
    Page_supplierView page_supplierDto = new Page_supplierView();
    page_supplierDto.setRecommendList(
        usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
    iduPage.setStart(getPage());
    iduPage.setWhere(String.valueOf(getCated()));
    int i = getLimit();
    if (i == 0) {
      i = 6;
    }
    iduPage.setStart(3);
    iduPage.setLimit(i);
    page_supplierDto.setManufacturersList(
        usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
    page_supplierDto.setCategory(UsrSupplierCategoryDAO.listViewIsTop(curLanguage()));
    _supplierDto = page_supplierDto;
    setResult("/home/supplier.jsp");
    return TRENDS;
  }

  /**
   * * 获取供应商列表信息 待优化，目标：json数据返回
   *
   * @author liyichao
   * @date 2018/10/11
   */
  public void getSupplierList() throws JsonProcessingException, IOException {
    IduPage page = new IduPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    Map map = pageSelect.SupplierList(page, getCated(), curLanguage());
    write(
        new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .writeValueAsString(map));
  }

  /**
   * * ajax返回供应商及三个商品列表
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/20 16:27
   */
  public void gtSupplierAndPdtListAjax() throws Exception {
    IduPage iduPage = new IduPage();
    iduPage.setStart(getPage());
    iduPage.setLimit(getLimit());
    // 修改
    iduPage.setWhere(String.valueOf(getCated()));
    write(
        new ObjectMapper()
            .writeValueAsString(
                usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage())));
  }

  //    /***
  //     * ajax返回供应商列表
  //     * @author lijie@shoestp.cn
  //     * @param
  //     * @return
  //     * @date 2018/7/20 16:27
  //     */
  //    public void gtSupplierAjax() throws Exception {
  //        UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
  //        IduPage iduPage = new IduPage();
  //        iduPage.setStart(getPage());
  //        iduPage.setLimit(getLimit());
  //        write(pageSelect.getSupplierList(iduPage, getCated()));
  //    }

  /**
   * * ajax返回供应商列表
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/20 16:27
   */
  public void gtSupplierAndPdtAjax() throws Exception {
    UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
    IduPage iduPage = new IduPage();
    iduPage.setStart(getPage());
    iduPage.setLimit(getLimit());
    JSONObject json = new JSONObject();
    json.put(STORE_TOTAL, BeanBase.list(UsrSupplier.class, "", false).size());
    json.put(
        STORE_ROOT,
        new JSONArray(
            usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()),
            false));
    writerOrExport(json);
  }

  @Override
  public void list() throws Exception {
    super.list();
  }

  /** 转发页面到店铺内产品详情 */
  public String goProduct() throws Exception {
    setResult("/home/storegoosinfo.jsp");
    return TRENDS;
  }

  private Byte entryStep;

  /** 商家入驻页面 */
  @NeedLogin(userType = UserType.SUPPLIER)
  public String supplierEntry() {
    UserView user = getUser();
    if (!user.isSupplier()) {
      entryStep = 0;
      setResult("/home/usr_UsrPurchase_sign");
      return RTRENDS;
    }
    // 没有Supplier 信息未入住商家
    UsrSupplier supplier = user.getSupplier();
    if (supplier != null && supplier.gtStatus() == OStatus.APPR) {
      setResult("/newseller/", false);
      return RTRENDS;
    }
    setResult("/home/v3/jsp/supplier-entry/index.jsp");
    return TRENDS;
  }

  private SupplierView view;

  /**
   * 商家入驻信息提交
   *
   * @author yingjianhua
   */
  @NeedLogin
  public void applyEntry() throws Exception {
    UsrSupplier supplier = UsrSupplierDAO.apply(view, getPurchase().getPkey(), curLanguage());
    UserView user = getUser();
    user.setSupplier(supplier);
    setUser(user);
    write();
  }
  //
  //	/**
  //     * 商家入驻
  //     * @throws Exception
  //     */
  //    public void enterSup() throws Exception {
  //        UsrSupplierDAO.Enter ins = new UsrSupplierDAO.Enter();
  //        ins.setPurchase(getPurchase());
  //        ins.setB(getBean());
  //        ins.commit();
  //        writeSuccess(ins.getB());
  //    }

  private List<PltProvince> pltProvince;
  private Integer id;

  public void pltprovince() throws Exception {
    pltProvince = BeanBase.list(PltProvince.class, PltProvince.T.MAIN + " = " + getId(), false);
    JSONArray proJSON = new JSONArray(pltProvince, false);
    JSONObject json = new JSONObject();
    json.put(STORE_ROOT, proJSON);
    writerOrExport(json);
  }

  private String storeName; // 店铺名称

  private String targetMarket; // 目标市场

  private Integer processType; // 工艺类型

  private String grade; // svs等级

  private Integer pdtCategory; // 产品类型

  private Integer checkType; // 查询类型

  /*
   *   获取供应商中心列表
   * @Author HuangHaoBin
   **/
  public void supplierList() throws Exception {
    if (getLimit() == 0) setLimit(10);
    write(
        usrSupplierService.listSupplier(
            getStart(),
            getLimit(),
            storeName,
            targetMarket,
            processType,
            grade,
            pdtCategory,
            checkType));
  }

  private Integer pkey;
  /**
   * 手机端获取商家详情信息
   *
   * @author GS
   * @throws Exception
   */
  public void getSupplierInfo() throws Exception {
    if (null == pkey)
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, curLanguage()));
    write(usrSupplierService.getSupplierInfo(pkey));
  }

  @Inject private UsrSupplierService usrSupplierService2;

  private Integer supplierPkey;

  @Override
  @NeedLogin
  public void getDetail() throws IOException, JSONException {
    write(usrSupplierService2.detail(getPurchase(), supplierPkey, curLanguage()));
  }

  public String goContactSupplier() {
    setResult("/home/contact-supplier.jsp");
    return TRENDS;
  }

  public void getSupplierDetail() throws IOException {
    write(usrSupplierService.getSuplierDetail(supplierPkey));
  }
}
