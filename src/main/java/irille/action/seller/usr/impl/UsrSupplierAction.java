package irille.action.seller.usr.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import irille.Service.Usr.IUsrSupplierService;
import irille.action.seller.SellerAction;
import irille.action.seller.usr.IUsrSupplierAction;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.Page_supplierView;
import irille.pub.Exp;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierDAO;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class UsrSupplierAction extends SellerAction<UsrSupplier> implements IUsrSupplierAction {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory.getLogger(UsrSupplierAction.class);

  private String email;
  private String password;
  private String vCode;

  @Inject IUsrSupplierService usrSupplierService;

  @Override
  public void login() throws IOException {
    String verifyCode = verifyCode();
    logger.info("vCode:" + vCode + "|verifyCode:" + verifyCode);

    if (Str.isEmpty(verifyCode) || Str.isEmpty(vCode) || !verifyCode.equals(vCode)) {
      writeErr("验证码错误");
      return;
    }
    UserView user;
    user = UsrUserDAO.supplierSignIn(email, password);
    setUser(user);
    write(UsrSupplierDAO.getSupplierInfo(user.getSupplier().getPkey()));
  }

  /**
   * 注销供应商登录信息
   *
   * @return
   * @throws IOException
   */
  @Override
  public void signOut() throws IOException {
    setUser(null);
    write();
  }

  /**
   * 更新店铺设置信息
   *
   * @throws Exception
   */
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
   * 查询店铺设置信息
   *
   * @throws Exception
   * @author guosong
   */
  public void selSupplier() throws Exception {
    write(UsrSupplierDAO.findById4AccountSet(getSupplier().getPkey()));
  }

  private String logo;

  /**
   * 更新商家LOGO
   *
   * @throws Exception
   * @author guosong
   */
  public void updLogo() throws Exception {
    UsrSupplierDAO.updLogo(logo, getSupplier().getPkey());
    write();
  }

  private String newPwd;
  private String oldPwd;

  /**
   * 修改密码
   *
   * @author yingjianhua
   */
  public void updPassword() throws Exception {
    UsrUserDAO.updSupplierPassword(getSupplier().getPkey(), oldPwd, newPwd);
    write();
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
    if (_showItem == 0) return 6;
    return _showItem;
  }

  public void setShowItem(int showItem) {
    this._showItem = showItem;
  }

  private Page_supplierView _supplierDto;

  public Page_supplierView getSupplierDto() {
    return _supplierDto;
  }

  public void setSupplierDto(Page_supplierView supplierDto) {
    this._supplierDto = supplierDto;
  }

  /**
   * * 获取供应商列表信息
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/20 10:14
   */
  public String gtSupplier() throws Exception {
    UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
    IduPage iduPage = new IduPage();
    iduPage.setStart(0);
    iduPage.setLimit(3);
    iduPage.setWhere(String.valueOf(getCated()));
    Page_supplierView page_supplierDto = new Page_supplierView();
    page_supplierDto.setRecommendList(
        usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
    iduPage.setStart(getCurr());
    iduPage.setWhere(String.valueOf(getCated()));
    iduPage.setLimit(getShowItem());
    page_supplierDto.setManufacturersList(
        usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
    page_supplierDto.setCategory(pageSelect.getCategory());
    _supplierDto = page_supplierDto;
    setResult("supplier.jsp");
    return HomeAction.TRENDS;
  }

  public String gtSupplierAjax() throws Exception {
    UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
    IduPage iduPage = new IduPage();
    iduPage.setStart(getCurr());
    iduPage.setLimit(getShowItem());
    iduPage.setWhere(String.valueOf(getCated()));
    Page_supplierView page_supplierDto = new Page_supplierView();
    page_supplierDto.setManufacturersList(
        usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
    setSupplierDto(page_supplierDto);
    setResult("Ajax_supplier.jsp");
    return HomeAction.TRENDS;
  }

  /**
   * 获取国家数据,省份数据,以及供应商分类数据,采购商信息,返会给前端页面
   *
   * @throws JSONException
   * @author GS
   */
  public void getCountryAndSupType() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray countryArray = new JSONArray();
    JSONArray categoryArray = new JSONArray();
    JSONArray provinceArray = new JSONArray();
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, SellerAction.getSupplier().getPkey());
    PltCountry country = new PltCountry();
    PltProvince province = new PltProvince();
    UsrSupplierCategory category = new UsrSupplierCategory();
    List<PltCountry> countryList = country.list(PltCountry.class, "", false);
    List<UsrSupplierCategory> categoryList = category.list(UsrSupplierCategory.class, "", false);
    List<PltProvince> provinceList = province.list(PltProvince.class, "", false);
    JSONObject countryJson = null;
    JSONObject categoryJson = null;
    JSONObject provinceJson = null;
    JSONObject supplierJson = crtJsonByBean(supplier);
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
   * 获取登录供应商信息
   *
   * @return
   * @throws Exception
   * @author liyichao
   */
  public void loadOnlineSup() throws Exception {
    UsrSupplier supplier =
        BeanBase.loadAndLock(UsrSupplier.class, SellerAction.getSupplier().getPkey());
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.T.SHOW_NAME);
            FROM(UsrSupplier.class);
            WHERE(UsrSupplier.T.PKEY, "=?").PARAM(SellerAction.getSupplier().getPkey());
          }
        };
    SqlQuery query = Query.sql(sql);
    JSONObject showname = null;
    if (query.queryObject(UsrSupplier.class) != null) {
      showname = new JSONObject(String.valueOf(query.queryObject(UsrSupplier.class)));
    }
    JSONObject json = crtJsonByBean(supplier);
    json.put("showname", showname);
    writerOrExport(json);
  }

  /**
   * 更新供应商信息
   *
   * @author liyichao
   * @return
   * @throws IOException
   */
  private String showName;

  public void updInfo() throws Exception {
    try {
      JSONObject json = new JSONObject(getShowName());
      Iterator<String> jsonIte = json.keys();
      while (jsonIte.hasNext()) {
        String key = jsonIte.next();
        String value = json.getString(key);
        if ((key.equals("en") && value.equals(""))
            || (key.equals("zh_CN") && value.equals(""))
            || (key.equals("zh_TW") && value.equals(""))) {
          throw LOG.err("needWrite", "{0}语种必填", FldLanguage.Language.valueOf(key).displayName());
        }
        if (value == "") {
          json.put(key, json.getString("en"));
        }
      }
      UsrSupplier newSupplier = UsrSupplierDAO.updInfo(getBean());
      newSupplier.stShowName(json);
      newSupplier.upd();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }
}
