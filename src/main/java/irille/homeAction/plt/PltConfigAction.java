package irille.homeAction.plt;

import irille.homeAction.HomeAction;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.CacheUtils;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.plt.PltErateDAO;
import org.json.JSONException;

import java.io.IOException;
import java.util.Locale;

public class PltConfigAction extends HomeAction<PltConfig> {

    private static final long serialVersionUID = -2543571532902429613L;
    private static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";

    private Integer currency;

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

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
