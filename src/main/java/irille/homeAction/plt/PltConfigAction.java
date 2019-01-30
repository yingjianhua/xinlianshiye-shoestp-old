package irille.homeAction.plt;

import irille.Service.Plt.PltService;
import irille.homeAction.HomeAction;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.CacheUtils;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrConsultDAO;
import irille.shop.usr.UsrFavoritesDAO;
import irille.view.v2.Plt.PltSysConfigView;
import irille.view.v2.Plt.PltUserInfo;
import org.json.JSONException;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Locale;

public class PltConfigAction extends HomeAction<PltConfig> {

    private static final long serialVersionUID = -2543571532902429613L;
    private static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";

    private Integer currency;
    @Inject
    private PltService pltService;


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
        if (getPurchase() != null) {
            PltUserInfo userInfo = new PltUserInfo();
            userInfo.setId(getPurchase().getPkey());
            userInfo.setName(getPurchase().getLoginName());
            userInfo.setFavorite_count(UsrFavoritesDAO.countByPurchase(getPurchase().getPkey()));
            userInfo.setInquiry_count(UsrConsultDAO.countByPurchase(getPurchase().getPkey()));
            userInfo.setShopping_cart_count(UsrCartDAO.Query.countByPurchase(getPurchase().getPkey()));
            sysConfigView.setUser(userInfo);
        }
        sysConfigView.setCurrencyList(PltErateDAO.list());
        write(sysConfigView);
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
