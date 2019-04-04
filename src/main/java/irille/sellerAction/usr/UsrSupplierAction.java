package irille.sellerAction.usr;

import static irille.pub.validate.Regular.REGULAR_NAME;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xinlianshiye.shoestp.common.service.UsrMainService;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Service.Manage.Usr.IUsrSupplierManageService;
import irille.action.dataimport.util.StringUtil;
import irille.pub.DateTools;
import irille.pub.Exp;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex;
import irille.pub.validate.ValidRegex2;
import irille.pub.verify.RandomImageServlet;
import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.dto.UsrSupplierInfo;
import irille.sellerAction.usr.inf.IUsrSupplierAction;
import irille.sellerAction.view.SupinfoView;
import irille.sellerAction.view.operateinfoView;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrAnnex;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierDAO;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.AccountSettingsView;
import irille.view.usr.UserView;
import irille.view.usr.UsrshopSettingView;
import lombok.Getter;
import lombok.Setter;

public class UsrSupplierAction extends SellerAction<UsrSupplier> implements IUsrSupplierAction {

  @Inject UsrMainService usrMainService;
  @Getter @Setter private String logo;
  @Getter @Setter private String newPwd;
  @Getter @Setter private String oldPwd;

  public void updBase() throws Exception {
    UsrSupplierDAO.UpdBase upd = new UsrSupplierDAO.UpdBase();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updPage() throws Exception {
    UsrSupplierDAO.UpdPage upd = new UsrSupplierDAO.UpdPage();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updDiy() throws Exception {
    UsrSupplierDAO.UpdDiy upd = new UsrSupplierDAO.UpdDiy();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updMarketing() throws Exception {
    UsrSupplierDAO.UpdMarketing upd = new UsrSupplierDAO.UpdMarketing();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  private String _mmOld; // 原密码
  private String _mmNew; // 新密码
  private String _mmCheck; // 新密码确认
  private String _checkCode; // 验证码

  // 取Session中的验证码
  private String verifyCode() {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession ssn = request.getSession(false);
    if (ssn == null) {
      return null;
    }
    return (String) ssn.getAttribute(RandomImageServlet.RANDOM_LOGIN_KEY);
  }

  private String email;
  private String password;
  private String vCode;

  @Override
  public void login() throws Exception {
    String verifyCode = verifyCode();
    if (Str.isEmpty(verifyCode) || Str.isEmpty(vCode) || !verifyCode.equals(vCode)) {
      writeErr("验证码错误");
      return;
    }
    UserView user = null;
    if (Str.isEmpty(email)) throw LOG.err("loginCheck", "请输入用户名");
    if (password == null || Str.isEmpty(password)) throw LOG.err("loginCheck", "请输入密码");
    // TODO Email 改为小写
    UsrMain main = UsrMain.chkUniqueEmail(false, email.toLowerCase());
    if (main == null || main.getIdentity() == 0) {
      throw LOG.err("Invalid User", "用户名不存在或无效的用户名");
    } else {
      if (!DateTools.getDigest(main.getPkey() + password).equals(main.getPassword())) {
        throw LOG.err("wrong password", "用户名和密码不匹配");
      }
    }
    BeanQuery<UsrSupplier> query = new BeanQuery();
    query
        .SELECT(UsrSupplier.class)
        .FROM(UsrSupplier.class)
        .WHERE(UsrSupplier.T.UserId, "=?", main.getPkey());
    UsrSupplier supplier = query.query();
    if (supplier == null) {
      JSONObject json = new JSONObject();
      json.put("ret", -2);
      json.put("msg", "用户尚未开通店铺,是否前往开通店铺");
      writerOrExport(json);
      return;
    }else if (supplier.getStoreStatus() == 0){
      JSONObject json = new JSONObject();
      json.put("ret", -2);
      json.put("msg", "用户店铺已关闭,请重新开通店铺");
      writerOrExport(json);
      return;
    }
    if (supplier.gtStatus() == Usr.OStatus.INIT) {
      throw LOG.err("wait for appr", "审核中不能登录");
    } else if(supplier.gtStatus() == Usr.OStatus.FAIL) {
      throw LOG.err("wait for appr", "审核失败，请前往开通店铺页面");
    }
    user = UsrUserDAO.supplierSignIn(supplier, main);
    setUser(user);
    write();
  }

  /**
   * 获取当前商家name
   *
   * @author zw
   */
  public void returnname() throws Exception {
    UsrSupplier us = new UsrSupplier();
    us.setName(getSupplier().getName());
    writeSuccess(us);
  }

  /**
   * 获取当前商家邮箱
   *
   * @author zjl
   */
  public void returnEmail() throws Exception {
    UsrSupplier us = new UsrSupplier();
    us.setEmail(getSupplier().getEmail());
    writeSuccess(us);
  }

  public String getMmOld() {
    return _mmOld;
  }

  public void setMmOld(String _mmOld) {
    this._mmOld = _mmOld;
  }

  public String getMmNew() {
    return _mmNew;
  }

  public void setMmNew(String _mmNew) {
    this._mmNew = _mmNew;
  }

  public String getMmCheck() {
    return _mmCheck;
  }

  public void setMmCheck(String _mmCheck) {
    this._mmCheck = _mmCheck;
  }

  public String getCheckCode() {
    return _checkCode;
  }

  public void setCheckCode(String _checkCode) {
    this._checkCode = _checkCode;
  }

  /** 更新店铺设置信息 */
  public void UpdBizDiy() throws Exception {
    UsrSupplierDAO.UpdBizDiy updbizdiy = new UsrSupplierDAO.UpdBizDiy();
    getBean().setPkey(SellerAction.getSupplier().getPkey());
    getBean().setRole(SellerAction.getSupplier().gtRole().getPkey());
    getBean().setRowVersion(SellerAction.getSupplier().getRowVersion());
    updbizdiy.setB(getBean());
    try {
      updbizdiy.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 更新账户设置
   *
   * @author zjl
   */
  @Getter @Setter private AccountSettingsView asv;

  /** 更新账户设置 */
  public void upAccount() throws IOException {
    UsrSupplierDAO.updAccount(getSupplier().getPkey(), getAsv());
    write();
  }

  /**
   * 查询店铺设置信息
   *
   * @author guosong
   */
  public void selSupplier() throws Exception {
    write(UsrSupplierDAO.findById4AccountSet(getSupplier().getPkey()));
  }

  /**
   * 更新商家LOGO
   *
   * @author guosong
   */
  public void updLogo() throws Exception {
    UsrSupplierDAO.updLogo(logo, getSupplier().getPkey());
    write();
  }

  /**
   * 修改密码
   *
   * @author yingjianhua
   */
  public void UpdPwd() throws Exception {
    if ("".equals(newPwd) || null == newPwd || "".equals(oldPwd) || null == oldPwd)
      throw new WebMessageException(ReturnCode.failure, "密码输入不能为空");
    if (!ValidRegex.regMarch(Regular.REGULAR_PWD, newPwd))
      throw new WebMessageException(ReturnCode.password_format, "密码为6到20为的数字和字母组成");
    UsrUserDAO.updSupplierPassword(getSupplier().getPkey(), oldPwd, newPwd);
    write();
  }

  @Getter @Setter private UsrshopSettingView usv;
  @Inject private UsrSupplierDAO.setting usrSupplierSetting;

  /**
   * 店铺装修
   *
   * @author yingjianhua
   */
  public void Updsuppliersetting() throws Exception {
    UsrSupplier us = new UsrSupplier();
    us.setPkey(getSupplier().getPkey());
    us.setAdPhoto(usv.getAdPhoto());
    us.setAdPhotoLink(usv.getAdPhotoLink());
    us.setCompanyPhoto(usv.getCompanyPhoto());
    us.setCompanyPhotoLink(usv.getCompanyPhotoLink());
    us.setHomePageDiy(usv.getHomePageDIY());
    us.stHomePageOn(usv.isHomePageOn());
    us.setLogo(usv.getLogo());
    us.setSignBackgd(usv.getSignBackGD());
    us.setCompanyIntroductionPageCustomDecoration(usv.getCompanyIntroductionPageCustomDecoration());
    us.stBottomHomeProductsOn(usv.isBottomproductdisplay());
    us.stIsPro(usv.isBottomproductdisplay());
    us.stHomePosterOn(usv.isHomebigposter());
    us.stHomeBusinessBigPosterOn(usv.isHomebusinessbigposter());
    us.stCompanyIntroductionPageCustomDecorationOn(usv.isAboutpagecustomdecoration());
    usrSupplierSetting.setB(us);
    usrSupplierSetting.commit();
    write();
  }

  @Override
  public void getLoginMsg() throws Exception {
    // TODO Auto-generated method stub

  }

  /** 供应商列表页面 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/19 Time: 15:46 */
  private int _curr;

  private int _showItem;
  private int _cated = -1;

  public int getCated() {
    return _cated;
  }

  public void setCated(int cated) {
    this._cated = cated;
  }

  public int getCurr() {
    return _curr;
  }

  public void setCurr(int curr) {
    this._curr = curr;
  }

  public int getShowItem() {
    if (_showItem == 0) {
      return 6;
    }
    return _showItem;
  }

  public void setShowItem(int showItem) {
    this._showItem = showItem;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getVCode() {
    return vCode;
  }

  public void setVCode(String vCode) {
    this.vCode = vCode;
  }

  /**
   * 获取国家数据,省份数据,以及供应商分类数据,采购商信息,返会给前端页面
   *
   * @author GS
   */
  public void getCountryAndSupType() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray countryArray = new JSONArray();
    JSONArray categoryArray = new JSONArray();
    JSONArray provinceArray = new JSONArray();
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, SellerAction.getSupplier().getPkey());
    List<PltCountry> countryList = BeanBase.list(PltCountry.class, "", false);
    List<UsrSupplierCategory> categoryList = BeanBase.list(UsrSupplierCategory.class, "", false);
    List<PltProvince> provinceList = BeanBase.list(PltProvince.class, "", false);
    JSONObject countryJson = null;
    JSONObject categoryJson = null;
    JSONObject provinceJson = null;
    JSONObject supplierJson = crtJsonByBean(supplier);
    supplierJson.put(
        UsrSupplier.T.HOME_PAGE_DIY.getFld().getCode(), supplier.getHomePageDiy(Language.en));
    supplierJson.put(UsrSupplier.T.COUNTRY.getFld().getCode(), crtJsonByBean(supplier.gtCountry()));
    supplierJson.put(
        UsrSupplier.T.PROVINCE.getFld().getCode(), crtJsonByBean(supplier.gtProvince()));
    supplierJson.put(
        UsrSupplier.T.CATEGORY.getFld().getCode(), crtJsonByBean(supplier.gtCategory()));
    // supplierArray.put(supplierJson);
    for (PltCountry countryObj : countryList) {
      countryJson = crtJsonByBean(countryObj);
      countryArray.put(countryJson);
    }
    for (UsrSupplierCategory categoryObj : categoryList) {
      categoryJson = crtJsonByBean(categoryObj);
      categoryArray.put(categoryJson);
    }
    for (PltProvince provinceObj : provinceList) {
      provinceJson = crtJsonByBean(provinceObj);
      provinceArray.put(provinceJson);
    }
    json.put(UsrSupplier.T.COUNTRY.getFld().getCode(), countryArray);
    json.put(UsrSupplier.T.CATEGORY.getFld().getCode(), categoryArray);
    json.put(UsrSupplier.T.PROVINCE.getFld().getCode(), provinceArray);
    json.put(STORE_ROOT, supplierJson);
    writerOrExport(json);
  }

  /**
   * 获取供应商信息
   *
   * @author: lingjian @Date: 2019/3/1 15:49
   */
  public void loadOnlineSup() throws Exception {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, getSupplier().getPkey());
    SQL sql =
        new SQL() {
          {
            SELECT(UsrAnnex.class);
            FROM(UsrAnnex.class);
            WHERE(UsrAnnex.T.SUPPLIER, "=?", supplier.getPkey());
          }
        };
    SqlQuery query = Query.sql(sql);
    Map<String, Object> obj = query.queryMap();
    JSONObject json = crtJsonByBean(supplier);
    JSONObject j = new JSONObject();
    for (String key : obj.keySet()) {
      j.put(key, obj.get(key));
    }
    json.put("annex", j);
    writerOrExport(json);
  }

  /**
   * @Description: 店铺信息
   *
   * @date 2018/12/18 9:34
   * @anthor wilson zhang
   */
  public void getsupinfo() throws Exception {
    write(
        UsrSupplierDAO.getsupinfo(
            getSupplier().getPkey(), PltConfigDAO.supplierLanguage(getSupplier().getPkey())));
  }

  @Getter @Setter private Integer purchasePkey;

  @Getter @Setter private String contactsIdCardFrontPhotoName;
  @Getter @Setter private String idCardFrontPhotoName;

  /**
   * 更新供应商信息
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/1 15:49
   */
  public void updInfo() throws Exception {
    try {
      regex();
      UsrAnnex annex = UsrAnnex.chkUniqueSupplier(false, getSupplier().getPkey());
      if (annex != null) {
        annex.setIdCardFrontPhotoName(idCardFrontPhotoName);
        annex.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName);
        annex.upd();
      } else {
        UsrAnnex annex1 = new UsrAnnex();
        annex1.setSupplier(getBean().getPkey());
        annex1.setIdCardFrontPhotoName(idCardFrontPhotoName);
        annex1.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName);
        annex1.ins();
      }
      UsrMain main = BeanBase.load(UsrMain.class, getBean().getUserid());
      if (main != null) {
        main.setContacts(getBean().getContacts());
        main.setTelphone(getBean().getPhone());
        main.upd();
      }
      UsrSupplier newSupplier = UsrSupplierDAO.updInfo(getBean());
      newSupplier.upd();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  // 正则校验
  public void regex() throws Exception {
    ValidForm valid = new ValidForm(getBean());
    valid.validNotEmpty(UsrSupplier.T.TARGETED_MARKET);
    ValidRegex2 regex = new ValidRegex2(getBean());
    if (getBean().getWebsite() != null)
      regex.validRegexMatched(
          "http[s]?:\\/\\/[\\w]{1,}.?[\\w]{1,}.?[\\w/.?&=-]{1,}",
          "请输入完整的网址格式，如https://www.shoestp.com",
          UsrSupplier.T.WEBSITE);
    if (getBean().getAnnualProduction() != null)
      regex.validRegexMatched(
          "([1-9]\\d*|0)(\\.\\d*[1-9])?", "年产量请填写数字,不能以0开头", UsrSupplier.T.ANNUAL_PRODUCTION);
    if (getBean().getTelephone() != null)
      regex.validRegexMatched(
          "((\\d{3,4}-)?\\d{7,8})|(1\\d{10})", "请填写正确的固定电话格式", UsrSupplier.T.TELEPHONE);
    if (getBean().getFax() != null)
      regex.validRegexMatched("(\\d{3,4}-)?\\d{7,8}", "请填写正确传真格式", UsrSupplier.T.FAX);
    if (getBean().getPostcode() != null)
      regex.validRegexMatched("[0-9]{6}", "邮编只能输入数字，且数字个数为6个", UsrSupplier.T.POSTCODE);
    if (getBean().getRegisteredCapital() != null)
      regex.validRegexMatched(
          "([1-9]\\d*|0)(\\.\\d*[1-9])?", "注册资本请填写数字,不能以0开头", UsrSupplier.T.REGISTERED_CAPITAL);
    if (getBean().getEntity() != null)
      regex.validRegexMatched(
          "[\\u4e00-\\u9fa5]{2,6}", "法定代表人只能输入中文，且个数为2~6个", UsrSupplier.T.ENTITY);
    if (getBean().getContacts() != null)
      regex.validRegexMatched(REGULAR_NAME, "联系人姓名首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.CONTACTS);
    if (getBean().getDepartment() != null)
      regex.validRegexMatched(REGULAR_NAME, "联系人部门首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.DEPARTMENT);
    if (getBean().getJobTitle() != null)
      regex.validRegexMatched(REGULAR_NAME, "联系人职称首尾不能为符号 且 长度在1-32位之间", UsrSupplier.T.JOB_TITLE);
    if (getBean().getPhone() != null)
      regex.validRegexMatched("1\\d{10}", "请填写11位手机格式的号码", UsrSupplier.T.PHONE);
    if (getBean().getContactEmail() != null)
      regex.validRegexMatched(
          "^[\\w]{1,32}@+\\w{1,15}.\\w{2,5}$", "联系人邮箱请填写正确的邮箱格式", UsrSupplier.T.CONTACT_EMAIL);
    if (getBean().getIdCard() != null)
      regex.validRegexMatched(
          "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", "请输入正确的18位身份证号码", UsrSupplier.T.ID_CARD);
    if (getBean().getOperateIdCard() != null)
      regex.validRegexMatched(
          "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)",
          "请输入正确的18位身份证号码",
          UsrSupplier.T.OPERATE_ID_CARD);
  }

  public String getNewPwd() {
    return newPwd;
  }

  public void setNewPwd(String newPwd) {
    this.newPwd = newPwd;
  }

  public String getOldPwd() {
    return oldPwd;
  }

  public void setOldPwd(String oldPwd) {
    this.oldPwd = oldPwd;
  }

  /**
   * 获取供应商店铺装修
   *
   * @author wilson zhang
   */
  public void loadshopsetting() throws Exception {
    write(
        UsrSupplierDAO.loadshopsetting(
            getSupplier().getPkey(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
  }

  /* 新的写法 分割线 */
  @Inject IUsrSupplierManageService usrSupplierManageService;

  public void getShopSetting() throws IOException {
    write(usrSupplierManageService.getSettingInfoById(getSupplier().getPkey()));
  }

  @Override
  public void Info() throws IOException {
    write(usrSupplierManageService.getInfoById(getSupplier().getPkey()));
  }

  public void logout() throws IOException, JSONException {
    irille.Filter.svr.SessionMsg sessionMsg =
        (irille.Filter.svr.SessionMsg) session.get(irille.Filter.svr.SessionMsg.session_key);
    if (sessionMsg != null && sessionMsg.haveUser()) {
      UsrMain usrMain = sessionMsg.getUsrMain();
      usrMainService.signOut(usrMain);
    }
    setUser(null);
    writeSuccess();
  }

  @Getter @Setter UsrshopSettingView view;

  public void updShopSetting() throws IOException {
    usrSupplierManageService.updShopSetting(getSupplier().getPkey(), getView());
    write();
  }

  /**
   * @Description: 修改 店铺信息
   *
   * @date 2018/12/19 14:40
   * @anthor wilson zhang
   */
  @Getter @Setter SupinfoView results;

  public void updShopbase() throws Exception {
    if (!StringUtil.hasValue(results.getQQ()) || !StringUtil.hasValue(results.getCredit_code())) {
      writeErr("请输入QQ与信用代码");
      return;
    }
    try {
      JSONObject json = new JSONObject(results.getProd_patiern());
      if (!StringUtil.hasValue(json.get("en"))) throw new NullPointerException();
    } catch (JSONException | NullPointerException e) {
      writeErr("请输入正确的生产模式");
      return;
    }
    UsrSupplier us = new UsrSupplier();
    us.setPkey(results.getId());
    us.setCompanyNature(results.getCompany_nature());
    us.setCompanyType(results.getCompany_Type());
    us.setCategory(results.getType());
    us.setQq(results.getQQ());
    us.setFax(results.getFAX());
    us.setOperationTerm(results.getOperation_term());
    us.setMainSalesArea(results.getMain_sale_area());
    us.setProdPattern(results.getProd_patiern());
    us.setDes(results.getDes());
    us.setMainProd(results.getMain_prod());
    us.setCreditCode(results.getCredit_code());
    us.setTaxpayerType(results.getTaxpayer_Type());
    UsrSupplierDAO.updShopbase ud = new UsrSupplierDAO.updShopbase();
    ud.setB(us);
    ud.commit();
    write();
  }

  /**
   * @Description: 运营信息
   *
   * @date 2018/12/19 14:40
   * @anthor wilson zhang
   */
  public void getoperateinfo() throws Exception {
    write(
        UsrSupplierDAO.getoperateinfo(
            getSupplier().getPkey(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
  }

  /**
   * @Description: 修改 运营信息
   *
   * @date 2018/12/19 14:40
   * @anthor wilson zhang
   */
  @Getter @Setter operateinfoView ovs;

  public void updoperateinfo() throws Exception {
    UsrSupplier us = new UsrSupplier();
    us.setPkey(getSupplier().getPkey());
    us.setWebSizeTitle(ovs.getWebsizetitle());
    us.setProduction(ovs.getProduction());
    us.setDeveloper(ovs.getTotalProduction());
    us.setTotalEmployees(ovs.getNumberEmployees());
    us.setTop3Markets(ovs.getTOP3MARKETS());
    us.setWebsite(ovs.getWebsite());
    us.setCity(ovs.getCity());
    us.setContacts(ovs.getContacts());
    us.setPhone(ovs.getPhone());
    us.setHeadPic(ovs.getHeadpic());
    us.setAnnualSales(ovs.getAnnualSalesFigure());
    UsrSupplierDAO.updoperatebase ud = new UsrSupplierDAO.updoperatebase();
    ud.setB(us);
    ud.commit();
    write();
  }

  /**
   * @Description: 2.1 认证信息
   *
   * @date 2018/12/21 16:16
   * @anthor wilson zhang
   */
  public void authInfo() throws Exception {
    write(UsrSupplierDAO.auth(getSupplier().getPkey()));
  }

  @Inject UsrSupplierDAO dao;

  public void getSupplierDetails() throws IOException {
    write(dao.getSupplierDetails(getSupplier().getPkey()));
  }

  @Override
  public void getTargetedMarket() throws Exception {
    JSONObject json = new JSONObject(dao.getTargetedMarket(getSupplier().getPkey()));
    writerOrExport(json);
  }

  /** @Author wilson Zhang @Description 商家端首页信息总方法 @Date 21:27 2019/3/28 */
  @Inject RFQConsultDao rfqConsultDao;

  @Inject PdtProductDAO pdtProductDAO;

  public void getsupplierinfo() throws Exception {
    UsrSupplierInfo usi = new UsrSupplierInfo();
    Integer pkey = getSupplier().getPkey();
    usi.setSupplierDetailsDTO(dao.getSupplierDetails(pkey));
    if (usi.getSupplierDetailsDTO().getSvsRatingAndRosDTO() != null) {
      for (SVSGradeType value : SVSGradeType.values()) {
        if (usi.getSupplierDetailsDTO().getSvsRatingAndRosDTO().getGrade()
            == value.getLine().getKey()) {
          usi.setSvsLevel(value.getLine().getName());
        }
      }
    }
    usi.setInquiriesCount(rfqConsultDao.getConsultCount(pkey));
    usi.setContactsCount(rfqConsultDao.getcontactsCount(pkey));
    usi.setMessageCount(rfqConsultDao.getMessageCount(pkey));
    usi.setProductCount(pdtProductDAO.productCount(pkey));
    usi.setPrivateproductCount(pdtProductDAO.privateproductCount(pkey));
    usi.setWareHouseProductCount(pdtProductDAO.wareHouseProductCount(pkey));
    usi.setVerifyProductCount(pdtProductDAO.verifyProductCount(pkey));
    usi.setFailedProductCount(pdtProductDAO.failedProductCount(pkey));
    usi.setWarehouseProductCounts(pdtProductDAO.warehouseProductCount(pkey));
    usi.setSellerIndexConsultViewList(rfqConsultDao.getIndexInqlist(pkey));
    write(usi);
  }
}
