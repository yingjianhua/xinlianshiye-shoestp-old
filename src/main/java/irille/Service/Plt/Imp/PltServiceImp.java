package irille.Service.Plt.Imp;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import irille.Aops.Caches;
import irille.Dao.PltCountryDao;
import irille.Service.Plt.PltService;
import irille.pub.tb.FldLanguage;
import irille.shop.plt.PltConfigDAO;
import irille.view.plt.CountryView;
import irille.view.plt.LanguageView;
import irille.view.v2.Plt.PltSysLangInfoView;
import org.json.JSONException;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/3 Time: 9:53 */
public class PltServiceImp implements PltService {

  @Inject private PltCountryDao pltCountryDao;

  @Override
  public List<CountryView> getCountryList(FldLanguage.Language language) {
    return getCountryList(language, null);
  }

  @Override
  @Caches
  public List<CountryView> getCountryList(FldLanguage.Language language, String filterKeyWord) {
    List<CountryView> result = new ArrayList();
    pltCountryDao
        .getCountryList()
        .forEach(
            pltCountry -> {
              CountryView view = new CountryView();
              view.setId(pltCountry.getPkey());
              try {
                view.setName(
                    language == null ? pltCountry.getName() : pltCountry.getName(language));
              } catch (JSONException e) {
                view.setName(pltCountry.getName());
                e.printStackTrace();
              }
              view.setShortName(pltCountry.getShortName());
              view.setFlag(pltCountry.getNationalFlag());
              result.add(view);
            });
    Collections.sort(
        result,
        (o1, o2) -> {
          Collator collator;
          switch (language) {
            case en:
              {
                collator = Collator.getInstance(Locale.ENGLISH);
              }
              break;
            case ro:
              {
                collator = Collator.getInstance(Locale.ENGLISH);
              }
              break;
            case zh_CN:
              {
                collator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);
              }
              break;
            default:
              {
                collator = Collator.getInstance();
              }
          }
          if (collator.compare(o1.getName(), o2.getName()) > 0) return 1;
          return -1;
        });
    //        进行过滤
    if (filterKeyWord != null)
      switch (filterKeyWord) {
        case "romania":
          {
            return result.stream()
                .filter(
                    countryView -> {
                      int[] filter = new int[] {7, 126, 218, 39, 149, 226};
                      for (int i : filter) {
                        if (i == countryView.getId()) {
                          return true;
                        }
                      }
                      return false;
                    })
                .collect(Collectors.toList());
          }
      }
    return result;
  }

  @Override
  @Caches
  public List<PltSysLangInfoView> getLangList() {
    List lang = new ArrayList();
    for (LanguageView languageView : PltConfigDAO.listLanguageView()) {
      if (!languageView.getIsEnabled()) continue;
      PltSysLangInfoView sysLangInfoView = new PltSysLangInfoView();
      sysLangInfoView.setShortName(languageView.getShortName());
      sysLangInfoView.setDisplayName(getLangDisplayName(languageView.getShortName()));
      lang.add(sysLangInfoView);
    }

    return lang;
  }

  private String getLangDisplayName(String key) {
    switch (key) {
      case "en":
        return "English";
      case "ja":
        return "日本語";
      case "de":
        return "Deutsch";
      case "fr":
        return "Français";
      case "es":
        return "Español";
      case "ru":
        return "русский";
      case "pt":
        return "Português";
      case "zh_CN":
        return "简体中文";
      case "zh_TW":
        return "繁体中文";
      case "hu":
        return "magyar";
      case "ro":
        return "românesc";
      default:
        return null;
    }
  }
}
