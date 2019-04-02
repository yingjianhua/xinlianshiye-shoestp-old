package irille.Filter.svr;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.xinlianshiye.shoestp.common.dao.usr.UsrMainDao;
import com.xinlianshiye.shoestp.common.dao.usr.impl.UsrMainDaoImpl;
import com.xinlianshiye.shoestp.seller.service.usr.IUsrSupplierSellerDao;
import com.xinlianshiye.shoestp.seller.service.usr.imp.UsrSupplierSellerDaoImp;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
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
  //  作为保留,不用每次去数据库查询相应的ID
  @Setter @Getter private Integer purchaseId;
  @Setter @Getter private Integer supplierId;
  private static IUsrSupplierSellerDao supplierSellerDao;
  private static UsrMainDao usrMainDao = new UsrMainDaoImpl();

  public static SessionMsg build() {
    SessionMsg msg = new SessionMsg();
    msg.isPurchase = false;
    msg.isSupplier = false;
    msg.setIsMobile(false);
    msg.setCurrency(PltErateDAO.Query.siteDefCurrency().getPkey());
    supplierSellerDao = new UsrSupplierSellerDaoImp();
    return msg;
  }

  public static void update(SessionMsg sessionmsg, ActionInvocation actionInvocation) {
    HttpServletRequest request = ServletActionContext.getRequest();
    Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
    HttpServletResponse response =
        (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
    // 设置是否为移动设备
    String agent = request.getHeader("User-Agent").toLowerCase();
    for (String device : mobile_device_array) {
      if (agent.indexOf(device) > 0) {
        response.setStatus(301);
        response.setHeader("Location",  "/m/");
        // response.setHeader( "Location", "http://www.hao123.com");
         response.setHeader( "Connection", "close" );
        return;
      }
    }

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
    return isPurchase ? UsrUserDAO.findByUesrId(pkey) : null;
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
    if (isSupplier) {
      return supplierSellerDao.findByUsrMainId(pkey);
    }
    return null;
    //    return isSupplier ? UsrUserDAO.findSupplierByLoginName(loginName) : null;
  }

  public void setSupplier(UsrSupplier supplier) {
    if (supplier == null) {
      isSupplier = false;
    } else {
      isSupplier = true;
      loginName = supplier.getLoginName();
    }
  }

  public boolean haveUser() {
    return pkey != null;
  }

  public UsrMain getUsrMain() {
    return usrMainDao.findByPkey(pkey).orElse(null);
  }

  public UserView getUser() {
    if (pkey != null) {
      UserView user = new UserView();
      user.setPkey(pkey);
      user.setSupplier(this.getSupplier());
      user.setPurchase(this.getPurchase());
      user.setLoginName(loginName);
      if (isSupplier) {
        user.setUser_type(1);
        user.setSupplierId(getSupplierId());
      } else {
        user.setUser_type(0);
        user.setPurchaseId(getPurchaseId());
      }
      return user;
    } else {
      return null;
    }
  }

  public void setUser(UserView user) {
    if (user == null || !user.haveUser()) {
      isPurchase = false;
      isSupplier = false;
      pkey = null;
      loginName = null;
    } else {
      this.setSupplier(user.getSupplier());
      this.setPurchase(user.getPurchase());
      this.pkey = user.getPkey();
      this.loginName = user.getLoginName();
      switch (user.getUser_type()) {
        case 0:
          {
            isPurchase = true;
            isSupplier = false;
            if (user.getPurchase() != null) purchaseId = user.getPurchase().getPkey();
          }
          break;
        case 1:
          {
            isSupplier = true;
            isPurchase = false;
            if (user.getSupplier() != null) supplierId = user.getSupplier().getPkey();
          }
      }
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

  //  TODO Remove Method 2019/4/10
  public Boolean getIsMobile() {
    return isMobile;
  }

  //  TODO Remove Method 2019/4/10
  public void setIsMobile(Boolean isMobile) {
    this.isMobile = isMobile;
  }
}
