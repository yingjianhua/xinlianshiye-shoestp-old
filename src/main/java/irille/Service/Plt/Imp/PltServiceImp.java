package irille.Service.Plt.Imp;

import irille.Aops.Caches;
import irille.Dao.PltCountryDao;
import irille.Service.Plt.PltService;
import irille.pub.tb.FldLanguage;
import irille.view.plt.CountryView;
import org.json.JSONException;

import javax.inject.Inject;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 9:53
 */
public class PltServiceImp implements PltService {


    @Inject
    private PltCountryDao pltCountryDao;


    @Override
    public List<CountryView> getCountryList(FldLanguage.Language language) {
        return getCountryList(language, null);
    }

    @Override
    @Caches
    public List<CountryView> getCountryList(FldLanguage.Language language, String filterKeyWord) {
        List<CountryView> result = new ArrayList();
        pltCountryDao.getCountryList().forEach(pltCountry -> {
            CountryView view = new CountryView();
            view.setId(pltCountry.getPkey());
            try {
                view.setName(language == null ? pltCountry.getName() : pltCountry.getName(language));
            } catch (JSONException e) {
                view.setName(pltCountry.getName());
                e.printStackTrace();
            }
            view.setShortName(pltCountry.getShortName());
            view.setFlag(pltCountry.getNationalFlag());
            result.add(view);
        });
        Collections.sort(result, (o1, o2) -> {
            Collator collator;
            switch (language) {
                case en: {
                    collator = Collator.getInstance(Locale.ENGLISH);
                }
                break;
                case ro: {
                    collator = Collator.getInstance(Locale.ENGLISH);
                }
                break;
                case zh_CN: {
                    collator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);
                }
                break;
                default: {
                    collator = Collator.getInstance();
                }
            }
            if (collator.compare(o1.getName(), o2.getName()) > 0)
                return 1;
            return -1;
        });
//        进行过滤
        if (filterKeyWord != null)
            switch (filterKeyWord) {
                case "romania": {
                    return result.stream().filter(countryView -> {
                        int[] filter = new int[]{
                                7, 126, 218, 39, 149, 226
                        };
                        for (int i : filter) {
                            if (i==countryView.getId()){
                                return true;
                            }
                        }
                        return false;
                    }).collect(Collectors.toList());
                }
            }
        return result;
    }
}
