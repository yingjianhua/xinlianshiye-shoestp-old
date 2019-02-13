package irille.view.v2.Plt;

import irille.pub.tb.FldLanguage;
import irille.view.BaseView;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/19
 * Time: 14:07
 */
@Data
public class PltSysConfigView implements BaseView {
    private List<PltSysLangInfoView> languages;
    private FldLanguage.Language current_language;
    private String currency_symbol;
    private String baseImageUrl;
    private PltUserInfo user;
    private List currencyList;
}
