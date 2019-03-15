package irille.Filter.svr;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionInvocation;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.UserView;
import org.apache.struts2.ServletActionContext;

/**
 * 存放在session中,用于统计记录该会话的用户消息
 *
 * @author yingjianhua
 */
public class SessionMsg {

  /** 存放于session中的键值 */
  public static final String session_key = "SESSION_MSG";

  private static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";
  private static final String[] mobile_device_array =
      new String[] {"android", "windows phone", "mobile", "iphone"};

  private String loginName;
  private Integer pkey;
  private boolean isPurchase;
  private boolean isSupplier;
  private Language lang;
  private Integer currency;
  private Boolean isMobile;
  private static Integer count = 0;

  public static SessionMsg build() {
    SessionMsg msg = new SessionMsg();
    msg.isPurchase = false;
    msg.isSupplier = false;
    msg.setIsMobile(false);
    msg.setCurrency(PltErateDAO.Query.siteDefCurrency().getPkey());
    return msg;
  }

  public static void update(SessionMsg sessionmsg, ActionInvocation actionInvocation) {
    HttpServletRequest request = ServletActionContext.getRequest();
    Map<String, Object> session = actionInvocation.getInvocationContext().getSession();

    // 设置是否为移动设备
    String agent = request.getHeader("User-Agent").toLowerCase();
    Boolean isMobile = false;
    for (String device : mobile_device_array) {
      if (agent.indexOf(device) > 0) {
        isMobile = true;
        break;
      }
    }
    sessionmsg.setIsMobile(isMobile);

    Object localObj = session.get(WW_TRANS_I18N_LOCALE);
    if (localObj == null) {
      session.put(WW_TRANS_I18N_LOCALE, Locale.ENGLISH);
      localObj = Locale.ENGLISH;
      ServletActionContext.getContext().setLocale(Locale.ENGLISH);
    } else {
      localObj = session.get(WW_TRANS_I18N_LOCALE);
    }
    Locale locale = (Locale) localObj;
    // 设置语言

    if (sessionmsg.getLang() == null || !sessionmsg.getLang().name().equals(locale.toString())) {
      Set<String> languages = new HashSet<>();
      for (String l : PltConfig.getVariable(Variable.Language).split(",")) {
        languages.add(l);
      }
      Language lang;
      try {
        if (languages.contains(locale.toString())) {
          lang = Language.valueOf(locale.toString());
        } else {
          if (languages.contains(locale.getLanguage())) {
            lang = Language.valueOf(locale.getLanguage());
          } else {
            lang = Language.valueOf(PltConfig.getVariable(Variable.LanguageDefault));
          }
          actionInvocation.getInvocationContext().setLocale(new Locale(lang.name()));
        }
      } catch (java.lang.IllegalArgumentException e) {
        lang = Language.en;
        actionInvocation.getInvocationContext().setLocale(new Locale(lang.name()));
      }
      sessionmsg.setLang(lang);
    }
  }

  public boolean isPurchase() {
    return isPurchase;
  }

  public boolean isSupplier() {
    return isSupplier;
  }

  public UsrPurchase getPurchase() {
    return isPurchase ? UsrUserDAO.findPurchaseByLoginName(loginName) : null;
  }

  public void setPurchase(UsrPurchase purchase) {
    if (purchase == null) {
      isPurchase = false;
    } else {
      isPurchase = true;
      loginName = purchase.getLoginName();
    }
  }

  public UsrSupplier getSupplier() {
    return isSupplier ? UsrUserDAO.findSupplierByLoginName(loginName) : null;
  }

  public void setSupplier(UsrSupplier supplier) {
    if (supplier == null) {
      isSupplier = false;
    } else {
      isSupplier = true;
      loginName = supplier.getLoginName();
    }
  }

  public UserView getUser() {
    UserView user = new UserView();
    user.setPkey(pkey);
    user.setSupplier(this.getSupplier());
    user.setPurchase(this.getPurchase());
    user.setLoginName(loginName);
    return user;
  }

  public void setUser(UserView user) {
    if (user == null || !user.haveUser()) {
      isPurchase = false;
      isSupplier = false;
    } else {
      this.setSupplier(user.getSupplier());
      this.setPurchase(user.getPurchase());
      this.pkey = user.getPkey();
    }
  }

  public Language getLang() {
    return lang;
  }

  public void setLang(Language lang) {
    this.lang = lang;
  }

  public PltErate gtCurrency() {
    return getCurrency() == null ? null : PltErate.load(PltErate.class, getCurrency());
  }

  public void stCurrency(PltErate currency) {
    setCurrency(currency == null ? null : currency.getPkey());
  }

  public Integer getCurrency() {
    return currency;
  }

  public void setCurrency(Integer currency) {
    this.currency = currency;
  }

  public Boolean getIsMobile() {
    return isMobile;
  }

  public void setIsMobile(Boolean isMobile) {
    this.isMobile = isMobile;
  }
}
