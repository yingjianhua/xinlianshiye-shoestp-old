package irille.homeAction.usr;

import com.sun.mail.util.MailSSLSocketFactory;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.Pdt.Imp.PdtproductPageselect;
import irille.Service.Plt.PltService;
import irille.Service.Usr.IUsrSupplierService;
import irille.homeAction.HomeAction;
import irille.homeAction.cnt.dto.CntAd_IndexCategoryView;
import irille.homeAction.usr.dto.PurchaseIndexView;
import irille.homeAction.usr.inf.IUsrPurchaseAction;
import irille.platform.usr.View.UsrPurchaseView;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.util.AppConfig;
import irille.pub.util.sendHttpsUtils;
import irille.shop.cnt.CntAd;
import irille.shop.cnt.CntAd.OAdLocation;
import irille.shop.cnt.CntAdDAO;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.usr.*;
import irille.view.cnt.IndexAdView4Mobile;
import irille.view.cnt.IndexAdView4PC;
import irille.view.plt.CountryView;
import irille.view.usr.AddressView;
import irille.view.usr.SupplierView;
import irille.view.usr.UserIndexView;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 采购商action
 *
 * @author yjh
 */
public class UsrPurchaseAction extends HomeAction<UsrPurchase> implements IUsrPurchaseAction {
    private static LogMessage LOG = new LogMessage(UsrPurchaseAction.class);


    @Inject
    private PdtproductPageselect pdtPageSelectt = new PdtproductPageselect();
    @Inject
    private PltService pltService;


    private static final CntAdDAO.pageSelect adPageSelect = new CntAdDAO.pageSelect();
    private static final UsrPurchaseDAO.pageselect purpageselect = new UsrPurchaseDAO.pageselect();


    @Inject
    private IPdtProductService pdtProduct;
    @Inject
    private IUsrSupplierService usrSupplierService;

    private String password;
    private String password2;
    private String email;
    private String _checkCode; // 验证码
    private PurchaseIndexView purchaseIndexView;
    @Getter
    @Setter
    private IndexAdView4Mobile indexAdView4Mobile;
    private IndexAdView4PC indexAdView4PC;
    private List<UsrPurchaseLine> usrPurchaseLine;
    private Integer pkey;

    public Integer getPkey() {
        return pkey;
    }

    public void setPkey(Integer pkey) {
        this.pkey = pkey;
    }

    /**
     * 首页
     */
    @Override
    public String execute() throws Exception {
        IduPage page = new IduPage();
        page.setStart(1);
        page.setLimit(3);
        IduPage adInfoPage = new IduPage();
        //判断平台
        CntAd.OSIGNAGE osignage = isMobile() ? CntAd.OSIGNAGE.ANY : CntAd.OSIGNAGE.PC;
        if (isMobile()) {
            adInfoPage.setLimit(3);
            adInfoPage.setStart(1);
        } else {
            adInfoPage.setLimit(7);
            adInfoPage.setStart(1);
        }
        IduPage categoryPage = new IduPage();
        categoryPage.setStart(1);
        categoryPage.setLimit(8);
        List categoryInfo = new ArrayList();
        for (Map adId : adPageSelect.getHasAdCatInfos(page, CntAd.OAdLocation.PDT_CAT, osignage)) {
            CntAd_IndexCategoryView adIndexCategory = new CntAd_IndexCategoryView();
            long categoryId = Long.valueOf(String.valueOf(adId.get(CntAd.T.CATEGORY.getFld().getCodeSqlField())));
            adIndexCategory.setAds(adPageSelect.getCatAdInfoList(adInfoPage, categoryId, osignage));
            if (!isMobile()) {
                adIndexCategory.setPkey(categoryId);
                adIndexCategory.setName(pdtPageSelectt.getCategorieNameById(categoryId));
                adIndexCategory.setCategorys(pdtPageSelectt.getProductsCategorieNodesByCategory(categoryPage, categoryId));
            }
            categoryInfo.add(adIndexCategory);
        }
        purchaseIndexView = new PurchaseIndexView();
        purchaseIndexView.setPdtCategoryAds(categoryInfo);
        page.setLimit(isMobile() ? 2 : 3);

        indexAdView4PC = new IndexAdView4PC();
        indexAdView4PC.setMiddleAd(CntAdDAO.listViewBySignagePosition(osignage, OAdLocation.INDEX_BANNER_MIDDLE, 0, 3));
        indexAdView4PC.setProductCategoryAd(CntAdDAO.listViewBySignagePosition(osignage, OAdLocation.PDT_CAT, 0, 7));

        page.setLimit(5);
        purchaseIndexView.setHeadedSwiperAds(adPageSelect.getAdsInfo(page, CntAd.OAdLocation.INDEX_SWIPER, osignage));
        if (!isMobile()) {
            page.setLimit(1);
            purchaseIndexView.setHeadedRightTopAds(adPageSelect.getAdsInfo(page, CntAd.OAdLocation.INDEX_RIGHT_TOP, osignage));
            page.setLimit(2);
            purchaseIndexView.setHeadedBottomTopAds(adPageSelect.getAdsInfo(page, CntAd.OAdLocation.INDEX_RIGHT_BOTTOM, osignage));
        }
        page.setStart(0);
        page.setLimit(isMobile() ? 8 : 21);
        purchaseIndexView.setSupplier(usrSupplierService.getSupplierInfo(page));
        page.setLimit(6);
        purchaseIndexView.setNewProducts(pdtProduct.getNewProductsListByIndex(page));

        indexAdView4Mobile = new IndexAdView4Mobile();
        indexAdView4Mobile.setProductCategoryAd(CntAdDAO.listViewBySignagePosition(osignage, OAdLocation.PDT_CAT, 0, 4));
        /**
         * @Description: 因为不知道为什么被注释, 所以暂时只对手机生效
         * @date 2018/11/6 11:07
         * @author lijie@shoestp.cn
         */
        //中间广告
        if (isMobile())
            purchaseIndexView.setMiddleAds(adPageSelect.getAdsInfo(page, CntAd.OAdLocation.INDEX_BANNER_MIDDLE, osignage));
        setResult("/home/home.jsp");
        return HomeAction.TRENDS;
    }


    public List<UsrPurchaseLine> getUsrPurchaseLine() {
        return usrPurchaseLine;
    }

    public void setUsrPurchaseLine(List<UsrPurchaseLine> usrPurchaseLine) {
        this.usrPurchaseLine = usrPurchaseLine;
    }


    private Integer vcodeNum = 3;    //登录次数
    private static final String vcode_err = "Verification code error.";
    private static final String login_err = "Incorrect email address or password. Please try again.Make sure the Caps Lock is off before you enter password.";

    /**
     * 登录
     */
    public void signIn() throws Exception {
        String verifyCode = verifyCode();
        if (vcodeNum >= 3 && (Str.isEmpty(verifyCode) || Str.isEmpty(_checkCode) || !verifyCode.equals(_checkCode))) {
            writeSuccess(new JSONObject().put("ret", -1).put("msg", new JSONArray().put(vcode_err)));
            return;
        }
        UserView user = UsrUserDAO.purchaseSignIn(email, password);
        if (user.isPurchase()) {
            //登录成功
            setUser(user);
            JSONObject json = new JSONObject().put("ret", 1);
            if (!Str.isEmpty(getJumpUrl()))
                json.put("msg", new JSONArray().put(getJumpUrl()));
            writeSuccess(json);
            return;
        }
        writeSuccess(new JSONObject().put("ret", 0).put("msg", new JSONArray().put(login_err)));
        return;
    }

    /**
     * 注销
     */
    public String signOut() {
        setUser(null);
        if (isMobile())
            setResult("/home/usr_UsrPurchase_sign", false);
        else
            setResult("/", false);

        return HomeAction.RTRENDS;
    }

    private Map accountInfo;

    public Map getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Map accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * 用户中心页面
     *
     * @return
     */
    private UsrPurchase usrPurchase;
    private Integer num;
    private Integer orders;
    private UsrPurchaseLine purchaseLine;

    @Override
    @NeedLogin
    public String userIndex() {
        if (isMobile()) {
            usrPurchase = BeanBase.load(UsrPurchase.class, HomeAction.getPurchase().getPkey());
            num = BeanBase.list(UsrCart.class, UsrCart.T.PURCHASE.getFld().getCodeSqlField() + " = " + usrPurchase.getPkey(), false).size();
            orders = BeanBase.list(OdrOrder.class, OdrOrder.T.PURCHASE.getFld().getCodeSqlField() + " = " + usrPurchase.getPkey(), false).size();
            List<UsrPurchaseLine> li = BeanBase.list(UsrPurchaseLine.class, UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField() + " = " + usrPurchase.getPkey() + " AND " + UsrPurchaseLine.T.ISDEFAULT.getFld().getCodeSqlField() + " = " + 1, false);
            if (li != null && !li.isEmpty()) {
                purchaseLine = li.get(0);
            }
            setResult("/home/account.jsp");
            try {
                setAccountInfo(purpageselect.getAccountInfo(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setResult("/home/user-index.jsp");
        }
        return HomeAction.TRENDS;
    }


    public UsrPurchase getUsrPurchase() {
        return usrPurchase;
    }

    public void setUsrPurchase(UsrPurchase usrPurchase) {
        this.usrPurchase = usrPurchase;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public UsrPurchaseLine getPurchaseLine() {
        return purchaseLine;
    }

    public void setPurchaseLine(UsrPurchaseLine purchaseLine) {
        this.purchaseLine = purchaseLine;
    }

    /**
     * PC端注册
     *
     * @author liyichao
     */
    public void pcRegister() throws Exception {
        String verifyCode = verifyCode();
        if (Str.isEmpty(verifyCode) || Str.isEmpty(getCheckCode()) || verifyCode.equals(getCheckCode()) == false)
            throw LOG.errTran("signIn%verification", "验证码错误");
        insBefore();
        UsrPurchaseDAO.Ins ins = new UsrPurchaseDAO.Ins();
        ins.setB(getBean());
        ins.setPassword(password);
        ins.setCopyPassword(password2);
        try {
            ins.commit();
            insAfter();
            setPurchase(ins.getB());
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    private static final Map<Integer, EmailView> FIND_POSSWORD_TOKEN_MAP = new ConcurrentHashMap<>();

    class EmailView {
        private String pkey; //采购商PKEY
        private Long time;

        public String getPkey() {
            return pkey;
        }

        public void setPkey(String pkey) {
            this.pkey = pkey;
        }

        public Long gettime() {
            return time;
        }

        public void settime(Long time) {
            this.time = time;
        }
    }

    private Integer randomNum;


    public Integer getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(Integer randomNum) {
        this.randomNum = randomNum;
    }

    private String returnresult;

    public String getReturnresult() {
        return returnresult;
    }

    public void setReturnresult(String returnresult) {
        this.returnresult = returnresult;
    }

    public String forgetUI() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer randomNumber1 = Integer.valueOf(request.getParameter("randomNumber1"));
        if (FIND_POSSWORD_TOKEN_MAP.containsKey(randomNumber1)) {
            EmailView ev = FIND_POSSWORD_TOKEN_MAP.get(randomNumber1);
            Long ststime = (System.currentTimeMillis() - ev.gettime()) / 1000 / 60;
            if (ststime < 5) {
                randomNum = randomNumber1;
                purchasepkey = ev.getPkey();

            } else {
                returnresult = "已经超时不可访问";
            }

        } else {
            returnresult = "抱歉 请先到申请忘记密码页面发送邮件";
        }
        setResult("/home/forgetPWD.jsp");
        return HomeAction.TRENDS;

    }


    /**
     * 返回发送邮件页
     */
    public String sendEmail() {
        setResult("/home/sendEmail.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * 返回发送成功邮件页
     *
     * @throws GeneralSecurityException
     */
    private String purchasepkey;
    private String useremailpwd;

    public String getUseremailpwd() {
        return useremailpwd;
    }

    public void setUseremailpwd(String useremailpwd) {
        this.useremailpwd = useremailpwd;
    }

    public void useremail() throws Exception {
        JSONObject json = new JSONObject();
        try {
            String sql = UsrPurchase.T.EMAIL.getFld().getCodeSqlField() + " = ? ";
            UsrPurchase purchase = BeanBase.list(UsrPurchase.class, sql, false, useremailpwd).get(0);
            json.put("purchase", purchase.getPkey());
            writerOrExport(json);
        } catch (ArrayIndexOutOfBoundsException e) {
            writeErr("format%isemail");
        }
    }

    //	public static void main(String[] args) throws GeneralSecurityException {
//		UsrPurchaseAction a = new UsrPurchaseAction();
//		a.setPurchasepkey("1206");
//		a.setEmail("a86291151@163.com");
//		a.sendemail();
//	}
//
    public String sendemail() throws GeneralSecurityException {
        Random r = new Random();
        int randomNumber1 = r.nextInt(10000000);
        EmailView ev = new EmailView();
        ev.setPkey(purchasepkey);
        ev.settime(System.currentTimeMillis());
        FIND_POSSWORD_TOKEN_MAP.put(randomNumber1, ev);
        // 收件人电子邮箱
        String to = "";
        to = getEmail();
        // 发件人电子邮箱
        String from = "notice@service.shoestp.com";
        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.mxhichina.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notice@service.shoestp.com", "7p7UaRqBb"); //发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject(AppConfig.mail_template_domain + " Password Recovery");

            // 设置消息体

            String text = AppConfig.mail_template
                    .replaceAll("\\{ForgotUrl\\}", AppConfig.domain + "/home/usr_UsrPurchase_forgetUI?randomNumber1=" + randomNumber1)
                    .replaceAll("\\{Time\\}", DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.ENGLISH).format(new Date()))
                    .replaceAll("\\{Logo\\}", "<img style=\"max-width:350px;\" src=\"" + AppConfig.mail_template_logo + "\" border=\"0\">")
                    .replaceAll("\\{FullDomain\\}", AppConfig.mail_template_index)
                    .replaceAll("\\{Domain\\}", AppConfig.mail_template_domain)
                    .toString();
//            message.setText(text);
            MimeMultipart mainpart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, "text/html; charset=utf-8");
            mainpart.addBodyPart(bodyPart);
            message.setContent(mainpart);
            // 发送消息
            Transport.send(message);
            setResult("/home/sendsuccess.jsp");
            return HomeAction.TRENDS;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        setResult("/home/sendEmail.jsp");
        return HomeAction.TRENDS;
    }

    public String getPurchasepkey() {
        return purchasepkey;
    }

    public void setPurchasepkey(String purchasepkey) {
        this.purchasepkey = purchasepkey;
    }

    public String forget() {
        setResult("/home/forget.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * 手机端注册
     *
     * @return
     * @throws Exception
     * @throws JSONException
     */
    public void register() throws JSONException, Exception {
        String verifyCode = verifyCode();
        UsrPurchase purchase = null;
        if ((Str.isEmpty(verifyCode) || Str.isEmpty(_checkCode) || verifyCode.equals(_checkCode) == false)) {
            writeSuccess(new JSONObject().put("ret", -1).put("msg", "signIn%verification"));
            return;
        } else {
            UsrPurchaseDAO.Ins ins = new UsrPurchaseDAO.Ins();
            purchase = new UsrPurchase();
            purchase.setEmail(email);
            ins.setB(purchase);
            ins.setPassword(password);
            ins.setCopyPassword(password2);
            ins.commit();
            purchase = ins.getB();
            setPurchase(purchase);
//          this.session.put(LOGIN, purchase);
//          HomeAction.initTran(purchase);
            JSONObject json = new JSONObject().put("ret", 1);
            writeSuccess(json);
            return;
        }
    }

    private List<CountryView> countrys;

    /**
     * 登录和注册页面
     *
     * @author yingjianhua
     */
    public String sign() throws JSONException {

        countrys = pltService.getCountryList(curLanguage(),null);
        setResult("/home/sign-up.jsp");
        return TRENDS;
    }

    /**
     * 用户重置密码
     */
    public void uda() throws Exception {
        FIND_POSSWORD_TOKEN_MAP.remove(randomNum);
        UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, purchasepkey);
        UsrPurchaseDAO.Uda uda = new UsrPurchaseDAO.Uda();
        uda.setB(purchase);
        uda.setPassword(password);
        uda.setCopyPassword(password2);
        uda.commit();
        writeSuccess();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckCode() {
        return _checkCode;
    }

    public void setCheckCode(String _checkCode) {
        this._checkCode = _checkCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Integer getVcodeNum() {
        return vcodeNum;
    }

    public void setVcodeNum(Integer vcodeNum) {
        this.vcodeNum = vcodeNum;
    }

    public PurchaseIndexView getPurchaseIndexView() {
        return purchaseIndexView;
    }

    public void setPurchaseIndexView(PurchaseIndexView purchaseIndexView) {
        this.purchaseIndexView = purchaseIndexView;
    }


    /**
     * 查询当前登录用户信息
     *
     * @throws Exception
     */
    public void selPurchase() throws Exception {
        JSONObject JsonObject = new JSONObject();
        UsrPurchase usrPurchase = BeanBase.get(UsrPurchase.class, "1");
        JSONObject ja = crtJsonByBean(usrPurchase);
        JsonObject.put(STORE_ROOT, ja);
        writerOrExport(JsonObject);
    }

    /**
     * 更新用户信息
     *
     * @throws JSONException
     * @throws IOException
     */
    private String firstName;
    private String lastName;
    private String oldPwd;
    private String newPwd;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    /**
     * 更新采购商信息
     *
     * @throws Exception
     */
    public void updPurchaseInf() throws Exception {
        UsrPurchaseDAO.updPurchaseInf updInf = new UsrPurchaseDAO.updPurchaseInf();
        getBean().setPkey(HomeAction.getPurchase().getPkey());
        updInf.setB(getBean());
        updInf.commit();
        write();
    }

    /**
     * 采购商修改密码
     *
     * @throws JSONException
     * @throws IOException
     * @author guosong
     */
    public void updPwd() throws Exception {
        UsrPurchaseDAO.updPwd usrPurchaseDao = new UsrPurchaseDAO.updPwd();
        usrPurchaseDao.setOldPwd(oldPwd);
        usrPurchaseDao.setNewPwd(newPwd);
        getBean().setPkey(HomeAction.getPurchase().getPkey());
        usrPurchaseDao.setB(getBean());
        try {
            usrPurchaseDao.commit();
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    /**
     * 返回到密码修改页面
     *
     * @return
     * @author guosong
     */
    public String toPassword() {
        setResult("/home/password.jsp");
        return HomeAction.TRENDS;
    }


    /**
     * 返回个人信息设置页面
     *
     * @return
     * @author zhengjianli
     */
    public String usrSetting() {
        if (getPurchase() == null) {
            setJumpUrl("/home/usr_UsrPurchase_usrSetting");
            return LOGIN;
        }
        usrPurchase = BeanBase.load(UsrPurchase.class, getPurchase().getPkey() + " = " + HomeAction.getPurchase().getPkey());

        setResult("/home/account_setting.jsp");
        return HomeAction.TRENDS;
    }


    /**
     * 获取PC端会员中心页面信息
     *
     * @author yingjianhua
     */
    public void detailById() throws Exception {
        UsrPurchase purchase = getPurchase();
        UserIndexView view = new UserIndexView();

        view.setLoginName(purchase.getLoginName());
        view.setEmail(purchase.getEmail());
        view.setOrders(OdrOrderDAO.pageByPurchase(purchase.getPkey(), -1, 0, 5).getItems());
        UsrPurchaseLine address = UsrPurchaseLineDAO.findDefaultByPurchase(purchase.getPkey());
        if (address != null)
            view.setDefaultAddress(AddressView.trans(address));
        view.setFavorites(UsrFavoritesDAO.pageByPurchase(purchase.getPkey(), 0, 5).getItems());

        write(view);
        //我的评论在页面上屏蔽了
/*    	JSONObject commentJson = new JSONObject();
        JSONArray commentArray = new JSONArray();
        List<PdtComment> commentList = BeanBase.list(PdtComment.class, PdtComment.T.USERS_PKEY.getFld().getCodeSqlField() + "=? ORDER BY pkey limit 0,2 ", false, purchase.getPkey());
        for (PdtComment pdtComment : commentList) {
            commentJson = crtJsonByBean(pdtComment);
            commentJson.put(PdtProduct.T.NAME.getFld().getCode(), pdtComment.gtProduct().getName(new UsrPurchaseAction().curLanguage()));
            commentJson.put(PdtProduct.T.PICTURE.getFld().getCode(), pdtComment.gtProduct().getPicture().split(",")[0]);//.split(",")[0]
            commentJson.put(UsrPurchase.T.SURNAME.getFld().getCode(), pdtComment.gtUsersPkey().getSurname());
            // TODO 因模型修改 此处业务逻辑带确认
            commentJson.put("satisfaction", (pdtComment.getProductSatisfaction() + pdtComment.getProductSatisfaction()) / 2);
//				commentJson.put(PdtComment.T.COMMENT.getFld().getCode(), pdtComment.getComment());
            commentArray.put(commentJson);
        }
        */
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    private String pwd;
    private String newEmail;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * 修改邮箱地址
     *
     * @throws Exception
     * @author zhengjianli
     */
    public void upEmail() throws Exception {
        UsrPurchaseDAO.upEmail usrPurchaseDAO = new UsrPurchaseDAO.upEmail();
        usrPurchaseDAO.setPwd(pwd);
        usrPurchaseDAO.setNewEmail(newEmail);
        usrPurchaseDAO.setB(getBean());
        try {
            usrPurchaseDAO.commit();
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    /**
     * 返回罗马尼亚页面
     *
     * @author zhengjianli
     */
    public String Romania() {
        setResult("/home/Romania.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * 进入商家店铺列表
     *
     * @return
     */
    private List<SupplierView> supplier = new ArrayList<SupplierView>();

    public List<SupplierView> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<SupplierView> supplier) {
        this.supplier = supplier;
    }

    /**
     * 跳转联合采购站
     *
     * @author zw
     */
    public String center() {
//    	List<Object[]> list = BeanBase.list("SELECT " + UsrSupplier.T.PKEY.getFld().getCodeSqlField() + "," + UsrSupplier.T.SHOW_NAME.getFld().getCodeSqlField() + "," + UsrSupplier.T.COMPANY_PHOTO.getFld().getCodeSqlField() + " FROM " + UsrSupplier.TB.getCodeSqlTb());
//
//    	for(int i=0;i<list.size();i++) {
//    		SupplierView sup = new SupplierView();
//    		sup.setPkey((Integer)list.get(i)[0]);
//    		sup.setName((String)list.get(i)[1]);
//    		sup.setCompanyPhoto((String)list.get(i)[2]);
//    		supplier.add(sup);
//    	}
        setResult("center.jsp");
        return TRENDS;
    }

    /**
     * 跳转联
     *
     * @author zw
     */
    public String garda() {
        setResult("gardaFair.jsp");
        return TRENDS;
    }

    private String faceBookToken;
    private String faceBooKUserName;
    private String login_name;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getFaceBooKUserName() {
        return faceBooKUserName;
    }

    public void setFaceBooKUserName(String faceBooKUserName) {
        this.faceBooKUserName = faceBooKUserName;
    }

    public String getFaceBookToken() {
        return faceBookToken;
    }

    public void setFaceBookToken(String faceBookToken) {
        this.faceBookToken = faceBookToken;
    }

    private static final String faceBook_AppId = "718848724966585";
    private static final String faceBook_Secret = "76de67dabb685e1b389d6b744a3492a7";

    @Getter
    @Setter
    private String facebookID;

    @Getter
    @Setter
    private String googleID;



    /**
     * facebook第三方登陆
     * @author: lingjian
     * @Date: 2019/2/21 16:31
     * @throws Exception
     */
    public void faceBookNewlogin() throws Exception {
        JSONObject returnJson = new JSONObject();
        List<UsrPurchaseView> list = UsrPurchaseDAO.selectFaceBookeId();
        for (int i = 0; i < list.size(); i++) {
            UsrPurchaseView obj = list.get(i);
            if(facebookID != null && facebookID.equals(obj.getFacebookID())){
                UserView user = UsrUserDAO.purchaseSignInByFacebook(obj.getLoginName(), obj.getFacebookID());
                System.err.println("user====>>"+user);
                if (user.isPurchase()) {
                    //登录成功
                    setUser(user);
                    JSONObject json = new JSONObject().put("ret", 1);
                    if (!Str.isEmpty(getJumpUrl()))
                        json.put("msg", new JSONArray().put(getJumpUrl()));
                    writeSuccess(json);
                    return;
                }
                writeSuccess(new JSONObject().put("ret", 0).put("msg", new JSONArray().put(login_err)));
                return;
            }
        }
    }

    @Getter
    @Setter
    private String code;

    public void test() throws Exception {

        System.err.println("code======>"+code);
        String url = "https://www.linkedin.com/uas/oauth2/accessToken";

        HttpClient httpclient= HttpClients.createDefault();

        HttpPost httpPost=new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("grant_type","authorization_code"));
        params.add(new BasicNameValuePair("code",code));
        params.add(new BasicNameValuePair("redirect_uri","http://localhost:8080/home/usr_UsrPurchase_sign"));
        params.add(new BasicNameValuePair("client_id","81xpp0e4b5z1fh"));
        params.add(new BasicNameValuePair("client_secret","ZE7gQVTlCDiimST8"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = httpclient.execute(httpPost);
        String s = EntityUtils.toString(response.getEntity(), "utf-8");

        JSONObject returnJson = new JSONObject(s);
        String access_token = returnJson.get("access_token").toString();
        System.err.println("access_token====>"+access_token);


        String url2 = "https://api.linkedin.com/v2/me?access_token"+access_token;
        HttpGet httpGet = new HttpGet(url2);
        httpGet.setHeader("accept","*/*");
        httpGet.setHeader("connection","Keep-Alive");
        httpGet.setHeader("Host","api.linkedin.com");
        httpGet.setHeader("Authorization","Bearer "+access_token);
        System.err.println("httpGet====>"+httpGet);

        HttpResponse responseGet = httpclient.execute(httpGet);
        System.err.println("HttpResponse======>"+responseGet);
        String s1 = EntityUtils.toString(responseGet.getEntity(), "utf-8");
//        System.err.println("HttpResponse======>"+s1);


    }

    /**
     * google第三方登陆
     * @author: lingjian
     * @Date: 2019/2/21 16:31
     * @throws Exception
     */
    public void googleNewlogin() throws Exception {
        JSONObject returnJson = new JSONObject();
        List<UsrPurchaseView> list = UsrPurchaseDAO.selectFaceBookeId();
        for (int i = 0; i < list.size(); i++) {
            UsrPurchaseView obj = list.get(i);
            if(googleID != null && googleID.equals(obj.getGoogleID())){
                UserView user = UsrUserDAO.purchaseSignInByGoole(obj.getLoginName(), obj.getGoogleID());
                if (user.isPurchase()) {
                    //登录成功
                    setUser(user);
                    JSONObject json = new JSONObject().put("ret", 1);
                    if (!Str.isEmpty(getJumpUrl()))
                        json.put("msg", new JSONArray().put(getJumpUrl()));
                    writeSuccess(json);
                    return;
                }
                writeSuccess(new JSONObject().put("ret", 0).put("msg", new JSONArray().put(login_err)));
                return;
            }
        }


    }


    /**
     * FACEBOOK第三方账号登陆
     *
     * @throws Exception
     * @author GS
     */
    public void faceBookLogin() throws Exception {
        JSONObject returnJson = new JSONObject();
        UsrPurchaseDAO.upFacebook UsrPurchaseDAO = new UsrPurchaseDAO.upFacebook();
        String user_id = "";
        if (null == faceBookToken || "".equals(faceBookToken)) {
            writeErr("参与校验的Token不能为空！");
            return;
        } else {
            String faceBook_checkUrl = "https://graph.facebook.com/debug_token?access_token=" + faceBook_AppId + "%7C" + faceBook_Secret + "&input_token=";
            String checkResult = sendHttpsUtils.sendGet(faceBook_checkUrl + faceBookToken, "127.0.0.1", 1080);
            JSONObject checkData = new JSONObject(checkResult);
            JSONObject chekedValid = checkData.getJSONObject("data");
            boolean valid = chekedValid.getBoolean("is_valid");
            if (valid) {
                user_id = chekedValid.getString("user_id");
                if (null == login_name || "".equals(login_name)) {
                    UsrPurchase userpurchase = UsrPurchase.chkUniqueFacebook_user_id(false, user_id);
                    if (null == userpurchase) {
                        returnJson.put("ret", "-1");
                        writerOrExport(returnJson);
                        return;
                    } else {
                        setPurchase(userpurchase);
                        JSONObject json = new JSONObject().put("ret", 1);
                        if (!Str.isEmpty(getJumpUrl()))
                            json.put("msg", new JSONArray().put(getJumpUrl()));
                        writeSuccess(json);
                        return;

                    }
                } else {
                    UsrPurchase userpurchase = UsrPurchase.chkUniqueLogin_name(false, login_name);
                    if (null == userpurchase) {
                        returnJson.put("ret", "-2");
                        writerOrExport(returnJson);
                        return;
                    }
                    if (null == userpurchase.getFacebookUserId()) {
                        userpurchase.setFacebookUserId(user_id);
                        UsrPurchaseDAO.setB(userpurchase);
                        UsrPurchaseDAO.commit();
                        setPurchase(userpurchase);
                        JSONObject json = new JSONObject().put("ret", 1);
                        if (!Str.isEmpty(getJumpUrl()))
                            json.put("msg", new JSONArray().put(getJumpUrl()));
                        writeSuccess(json);
                        return;
                    } else {
                        if (user_id.equals(userpurchase.getFacebookUserId())) {
                            setPurchase(userpurchase);
                            JSONObject json = new JSONObject().put("ret", 1);
                            if (!Str.isEmpty(getJumpUrl()))
                                json.put("msg", new JSONArray().put(getJumpUrl()));
                            writeSuccess(json);
                            return;
                        } else {
                            writeErr("该账户未与邮箱绑定，无法第三方登陆");
                            return;
                        }
                    }
                }
            } else {
                writeErr("校验失败，请确认FaceBook账号是否正确！");
                return;
            }
        }
    }

    private String googleToken;

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    /**
     * GOOGLE第三方登录
     *
     * @author GS
     */
    public void googleLogin() throws Exception {
        JSONObject returnJson = new JSONObject();
        UsrPurchaseDAO.upGoogleBook UsrPurchaseDAO = new UsrPurchaseDAO.upGoogleBook();
        String user_id = "";
        if (null == googleToken || "".equals(googleToken)) {
            writeErr("参与校验的Token不能为空!");
            return;
        } else {
            String google_checkUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleToken;
            String checkResult = sendHttpsUtils.sendGet(google_checkUrl, "127.0.0.1", 1080);
            JSONObject ValidJson = new JSONObject(checkResult);
            boolean valid = ValidJson.getBoolean("email_verified");
            if (valid) {
                user_id = ValidJson.getString("email");
                if (null == login_name || "".equals(login_name)) {
                    UsrPurchase userpurchase = UsrPurchase.chkUniqueGoogle_user_id(false, user_id);
                    if (null == userpurchase) {
                        returnJson.put("ret", "-1");
                        writerOrExport(returnJson);
                        return;
                    } else {
                        setPurchase(userpurchase);
                        JSONObject json = new JSONObject().put("ret", 1);
                        if (!Str.isEmpty(getJumpUrl()))
                            json.put("msg", new JSONArray().put(getJumpUrl()));
                        writeSuccess(json);
                        return;
                    }
                } else {
                    UsrPurchase userpurchase = UsrPurchase.chkUniqueLogin_name(false, login_name);
                    if (null == userpurchase) {
                        returnJson.put("ret", "-2");
                        writerOrExport(returnJson);
                        return;
                    }
                    if (null == userpurchase.getGoogleUserId()) {
                        userpurchase.setGoogleUserId(user_id);
                        UsrPurchaseDAO.setB(userpurchase);
                        UsrPurchaseDAO.commit();
                        setPurchase(userpurchase);
                        JSONObject json = new JSONObject().put("ret", 1);
                        if (!Str.isEmpty(getJumpUrl()))
                            json.put("msg", new JSONArray().put(getJumpUrl()));
                        writeSuccess(json);
                        return;
                    } else {
                        if (user_id.equals(userpurchase.getFacebookUserId())) {
                            setPurchase(userpurchase);
                            JSONObject json = new JSONObject().put("ret", 1);
                            if (!Str.isEmpty(getJumpUrl()))
                                json.put("msg", new JSONArray().put(getJumpUrl()));
                            writeSuccess(json);
                            return;
                        } else {
                            writeErr("该账户未与邮箱绑定，无法第三方登陆");
                            return;
                        }
                    }
                }
            } else {
                writeErr("校验失败，请确认Google账号是否正确！");
                return;
            }
        }
    }

    public List<CountryView> getCountrys() {
        return countrys;
    }

    public void setCountrys(List<CountryView> countrys) {
        this.countrys = countrys;
    }

    public IndexAdView4PC getIndexAdView4PC() {
        return indexAdView4PC;
    }

    public void setIndexAdView4PC(IndexAdView4PC indexAdView4PC) {
        this.indexAdView4PC = indexAdView4PC;
    }

}
