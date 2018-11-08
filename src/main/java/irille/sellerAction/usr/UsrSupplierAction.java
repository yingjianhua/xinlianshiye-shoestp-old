package irille.sellerAction.usr;

import irille.Service.Usr.IUsrSupplierService;
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
import irille.pub.tb.FldLanguage.Language;
import irille.pub.verify.RandomImageServlet;
import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrSupplierAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierDAO;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.AccountSettingsView;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UsrSupplierAction extends SellerAction<UsrSupplier> implements IUsrSupplierAction {
    @Getter
    @Setter
    private String logo;
    @Getter
    @Setter
    private String newPwd;
    @Getter
    @Setter
    private String oldPwd;

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
        if (ssn == null)
            return null;
        return (String) ssn.getAttribute(RandomImageServlet.RANDOM_LOGIN_KEY);
    }

    private String email;
    private String password;
    private String vCode;

    @Override
    public void login() throws IOException {
        System.out.println("vCode:" + vCode);
        String verifyCode = verifyCode();
        System.out.println("verifyCode:" + verifyCode);

        if (Str.isEmpty(verifyCode) || Str.isEmpty(vCode) || !verifyCode.equals(vCode)) {
            writeErr("验证码错误");
            return;
        }
        UserView user;
        try {
            user = UsrUserDAO.supplierSignIn(email, password);
            setUser(user);
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }

//    	UsrSupplier supplier=new UsrSupplier();
//        try {
//        	String verifyCode = verifyCode();
//            if (Str.isEmpty(verifyCode) || Str.isEmpty(getCheckCode()) || verifyCode.equals(getCheckCode()) == false)
//                throw LOG.err("errcode", "验证码错误");
//             supplier = UsrSupplierDAO.loginCheck(getBean().getLoginName(), getMmCheck());
//
//            this.session.put(LOGIN, supplier);
//            SellerAction.initTran(supplier);
//        } catch (Exp e) {
//            setSarg1(e.getLastMessage());
//            return SellerAction.LOGIN;
//        }
//
//        setResult("/seller/admin/index/index.html");
//        return RTRENDS;

    }


    /**
     * 注销供应商登录信息
     *
     * @return
     */
    @Override
    public String logon() {
        setUser(null);
        setResult("/seller/admin/index/login.jsp");
        return RTRENDS;
    }

    /**
     * 获取当前商家name
     *
     * @throws Exception
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
     * @return
     * @throws Exception
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
     * 更新账户设置
     *
     * @author zjl
     */
    @Getter
    @Setter
    private AccountSettingsView asv;

    /**
     * 更新账户设置
     *
     * @throws IOException
     */
    public void upAccount() throws IOException {
        UsrSupplierDAO.updAccount(getSupplier().getPkey(), getAsv());
        write();
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


    /**
     * 修改密码
     *
     * @author yingjianhua
     */
    public void UpdPwd() throws Exception {
        UsrUserDAO.updSupplierPassword(getSupplier().getPkey(), oldPwd, newPwd);
        write();
    }

    @Override
    public void getLoginMsg() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 供应商列表页面
     * Created by IntelliJ IDEA.
     * User: lijie@shoestp.cn
     * Date: 2018/7/19
     * Time: 15:46
     */

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
        if (_showItem == 0)
            return 6;
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

    /***
     * 获取供应商列表信息
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
        page_supplierDto.setRecommendList(pageSelect.getSupplierListAndPdtList(iduPage));
        iduPage.setStart(getCurr());
        iduPage.setWhere(String.valueOf(getCated()));
        iduPage.setLimit(getShowItem());
        page_supplierDto.setManufacturersList(pageSelect.getSupplierListAndPdtList(iduPage));
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
        page_supplierDto.setManufacturersList(pageSelect.getSupplierListAndPdtList(iduPage));
        setSupplierDto(page_supplierDto);
        setResult("Ajax_supplier.jsp");
        return HomeAction.TRENDS;
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
        supplierJson.put(UsrSupplier.T.HOME_PAGE_DIY.getFld().getCode(), supplier.getHomePageDiy(Language.en));
        supplierJson.put(UsrSupplier.T.COUNTRY.getFld().getCode(), crtJsonByBean(supplier.gtCountry()));
        supplierJson.put(UsrSupplier.T.PROVINCE.getFld().getCode(), crtJsonByBean(supplier.gtProvince()));
        supplierJson.put(UsrSupplier.T.CATEGORY.getFld().getCode(), crtJsonByBean(supplier.gtCategory()));
        //supplierArray.put(supplierJson);
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
        UsrSupplier supplier = BeanBase.loadAndLock(UsrSupplier.class, SellerAction.getSupplier().getPkey());
        SQL sql = new SQL() {{
            SELECT(UsrSupplier.T.SHOW_NAME);
            FROM(UsrSupplier.class);
            WHERE(UsrSupplier.T.PKEY, "=?").PARAM(SellerAction.getSupplier().getPkey());
        }};
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
    @Getter
    @Setter
    private String showName;

    public void updInfo() throws Exception {
        try {
            JSONObject json = new JSONObject(getShowName());
            Iterator<String> jsonIte = json.keys();
            while (jsonIte.hasNext()) {
                String key = jsonIte.next();
                String value = json.getString(key);
                if ((key.equals("en") && value.equals("")) || (key.equals("zh_CN") && value.equals("")) || (key.equals("zh_TW") && value.equals(""))) {
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
     * @return
     * @throws Exception
     * @author wilson zhang
     */
    public void loadshopsetting() throws Exception {
        write(UsrSupplierDAO.loadshopsetting(getSupplier().getPkey(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));

    }

    /*                 新的写法   分割线               */
    @Inject
    IUsrSupplierService usrSupplier;


    public void getShopSetting() throws IOException{
    	write(usrSupplier.getSettingInfoById(getSupplier().getPkey()));
    }

    @Override
    public void Info() throws IOException {
        write(usrSupplier.getInfoById(getSupplier().getPkey()));
    }

    public void logout() throws IOException, JSONException {
        setUser(null);
        writeSuccess();
    }
}
