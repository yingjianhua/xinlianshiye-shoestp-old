package irille.homeAction.p;

import java.util.List;
import java.util.Map;

import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.Filter.svr.ItpSessionmsg;
import irille.homeAction.pdt.dto.ProductCatView;
import irille.homeAction.usr.dto.EnvView;
import irille.pub.bean.BeanBase;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.AppConfig;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrConsultDAO;
import irille.shop.usr.UsrFavoritesDAO;
import irille.shop.usr.UsrPurchase;
import irille.view.home.EnvConfig;
import irille.view.plt.CurrencyView;
import irille.view.usr.UserView;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;

/**
 * 页面action的抽象类, 由于页面数据统一为异步请求,继承该action的子类专门处理页面请求(相对于数据请求)
 *
 * @author Jianhua Ying
 */
public abstract class AbstractPageAction implements RequestAware, SessionAware, ApplicationAware {

  protected Map<String, Object> request;
  protected Map<String, Object> session;
  protected Map<String, Object> application;
  protected String _result = null;
  protected String _sarg1 = null;

  public static final String SESSION_ENV = "SESSION_ENV";
  public static final String LOGIN = "login";
  public static final String TRENDS = "trends";
  public static final String RTRENDS = "rtrends";
  public static final String ERR404 = "404";
  private String jumpUrl = null; // 登录跳转

  private EnvView env = null;

  public Map<String, Object> getRequest() {
    return request;
  }

  public void setRequest(Map<String, Object> request) {
    this.request = request;
  }

  public Map<String, Object> getSession() {
    return session;
  }

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public Map<String, Object> getApplication() {
    return application;
  }

  public void setApplication(Map<String, Object> application) {
    this.application = application;
  }

  public String getResult() {
    return _result;
  }

  /**
   * 自动转换,规则如下 系统判断是手机端访问的 index.jsp => /mobile/index.jsp /mobile/index.jsp => /mobile/index.jsp
   * /home/index.jsp => /mobile/index.jsp 系统判断是pc端访问的 index.jsp => /mobile/index.jsp
   * /mobile/index.jsp => /home/index.jsp /home/index.jsp => /home/index.jsp
   *
   * @param result
   */
  public void setResult(String result) {
    setResult(result, true);
  }

  public void setResult(String result, boolean autoTrans) {
    if (autoTrans) {
      if (result.startsWith("/home") && isMobile()) {
        _result = "/mobile" + result.substring("/home".length());
      } else if (result.startsWith("/mobile") && !isMobile()) {
        _result = "/home" + result.substring("/mobile".length());
      } else if (!result.startsWith("/home") && !result.startsWith("/mobile")) {
        _result = (isMobile() ? "/mobile/" : "/home/") + result;
      } else {
        _result = result;
      }
    } else {
      _result = result;
    }
  }

  public String getSarg1() {
    return _sarg1;
  }

  public void setSarg1(String sarg1) {
    this._sarg1 = sarg1;
  }

  private EnvConfig envConfig;

  public void initEnv() throws JSONException {
    String lang = curLanguage().name();
    String currency = curCurrency().getCurName();
    String currency_symbols = curCurrency().getSymbol();
    String currency_rate = curCurrency().getRate().toString();
    String userId = getPurchase() == null ? "" : getPurchase().getPkey() + "";
    envConfig =
        EnvConfig.build(AppConfig.domain, lang, currency, currency_symbols, currency_rate, userId);

    UsrPurchase purchase = getPurchase();
    PurchaseView login = null;
    if (purchase != null) {
      int countFavorite = UsrFavoritesDAO.countByPurchase(purchase.getPkey());
      int countCart = UsrCartDAO.Query.countByPurchase(purchase.getPkey());
      int countInquiry = UsrConsultDAO.countByPurchase(purchase.getPkey());
      login = PurchaseView.build(purchase, countFavorite, countCart, countInquiry);
    }
    // String languages = PltConfig.getVariable(Variable.Language);
    String curLanguage = curLanguage().name();
    List<CurrencyView> currencys = PltErateDAO.listCurrencyView();
    setEnv(
        EnvView.build(
            login,
            PltConfigDAO.listLanguageView(),
            currencys,
            curLanguage,
            CurrencyView.build(curCurrency()),
            ProductCatView.build(PdtCatDAO.Query.listTopCat(), curLanguage())));
  }

  public static final void setUser(UserView user) {
    ItpSessionmsg.getSessionmsg().setUser(user);
  }

  public static final UserView getUser() {
    return ItpSessionmsg.getSessionmsg().getUser();
  }

  public static final void setPurchase(UsrPurchase purchase) {
    ItpSessionmsg.getSessionmsg().setPurchase(purchase);
  }

  public static final UsrPurchase getPurchase() {
    return ItpSessionmsg.getSessionmsg().getPurchase();
  }

  public static final PltErate curCurrency() {
    return BeanBase.load(PltErate.class, ItpSessionmsg.getSessionmsg().getCurrency());
  }

  public static final void curCurrency(PltErate currency) {
    ItpSessionmsg.getSessionmsg().setCurrency(currency.getPkey());
  }

  public static final Language curLanguage() {
    return ItpSessionmsg.getSessionmsg().getLang();
  }

  public static final void setCurLanguage(Language lang) {
    ItpSessionmsg.getSessionmsg().setLang(lang);
  }

  public static final boolean isMobile() {
    return ItpSessionmsg.getSessionmsg().getIsMobile();
  }

  public static final void setMobile(boolean isMobile) {
    ItpSessionmsg.getSessionmsg().setIsMobile(isMobile);
  }

  // ---------------------------------------------getter/setter--------------------------------------
  public EnvConfig getEnvConfig() {
    return envConfig;
  }

  public void setEnvConfig(EnvConfig envConfig) {
    this.envConfig = envConfig;
  }

  public EnvView getEnv() {
    return env;
  }

  public void setEnv(EnvView env) {
    this.env = env;
  }

  public final String getJumpUrl() {
    return jumpUrl;
  }

  public final void setJumpUrl(String jumpUrl) {
    this.jumpUrl = jumpUrl;
  }
}
