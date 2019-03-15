package irille.homeAction.plt;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import irille.Service.Plt.PltService;
import irille.Service.Usr.IUsrSupplierService;
import irille.homeAction.HomeAction;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.CacheUtils;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrConsultDAO;
import irille.shop.usr.UsrFavoritesDAO;
 import irille.view.usr.UserView;
import irille.view.v2.Plt.PltSysConfigView;
import irille.view.v2.Plt.PltUserInfo;
import org.json.JSONException;

public class PltConfigAction extends HomeAction<PltConfig> {

  private static final long serialVersionUID = -2543571532902429613L;
  private static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";

  private Integer currency;
  @Inject private PltService pltService;
  @Inject private IUsrSupplierService UsrSupplierService;

  /**
   * 切换网站展示语言
   *
   * @throws IOException
   * @throws JSONException
   */
  public void changeLanguage() throws IOException, JSONException {
    Locale l = (Locale) session.get(WW_TRANS_I18N_LOCALE);
    if (l != null && PltConfig.getVariable(Variable.Language).contains(l.toString())) {
      setCurLanguage(Language.valueOf(l.toString()));
      CacheUtils.cache.invalidateAll();
    }
    write();
  }

  /**
   * 切换网站展示货币
   *
   * @throws IOException
   * @throws JSONException
   */
  public void changeCurrency() throws IOException, JSONException {
    curCurrency(PltErateDAO.find(currency));
    write();
  }

  public void getSysConfig() throws IOException {
    PltSysConfigView sysConfigView = new PltSysConfigView();
    sysConfigView.setLanguages(pltService.getLangList());
    sysConfigView.setCurrent_language(HomeAction.curLanguage());
    sysConfigView.setCurrency_symbol(HomeAction.curCurrency().getSymbol());
    sysConfigView.setBaseImageUrl("https://image.shoestp.com");
    UserView userView = getUser();
    if (userView.haveUser()) {
      PltUserInfo userInfo = new PltUserInfo();
      userInfo.setId(userView.getPkey());
      userInfo.setName(userView.getLoginName());
      if (userInfo.getUser_type() == 0) {
        userInfo.setFavorite_count(UsrFavoritesDAO.countByPurchase(userView.getPkey()));
        userInfo.setInquiry_count(UsrConsultDAO.countByUsrMainId(userView.getPkey()));
        userInfo.setShopping_cart_count(UsrCartDAO.Query.countByPurchase(userView.getPkey()));
      } else {
        // TODO
        userInfo.setInquiry_count(0);
        userInfo.setFavorite_count(0);
        userInfo.setShopping_cart_count(0);
      }
      userInfo.setUser_type(userInfo.getUser_type()); // 判断是否为商家用户 赋值给usertype 0:普通用户 1: 商家用户
      sysConfigView.setUser(userInfo);
    }
    sysConfigView.setCurrencyList(
        PltErateDAO.list().stream()
            .filter(
                pltErateView -> {
                  return pltErateView.getEnabled();
                })
            .collect(Collectors.toList()));
    write(sysConfigView);
  }

  public Integer getCurrency() {
    return currency;
  }

  public void setCurrency(Integer currency) {
    this.currency = currency;
  }
}
