package irille.Service.Plt;

import com.google.inject.ImplementedBy;
import irille.Service.Plt.Imp.PltServiceImp;
import irille.pub.tb.FldLanguage;
import irille.view.plt.CountryView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 9:53
 */
@ImplementedBy(PltServiceImp.class)
public interface PltService {
    List<CountryView> getCountryList(FldLanguage.Language language);
    List<CountryView> getCountryList(FldLanguage.Language language, String filterKeyWord);
}
