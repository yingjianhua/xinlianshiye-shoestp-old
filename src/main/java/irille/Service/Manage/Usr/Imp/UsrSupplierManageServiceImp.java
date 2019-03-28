package irille.Service.Manage.Usr.Imp;

import java.util.Map;

import javax.inject.Inject;

import com.google.gson.JsonObject;

import irille.Dao.UsrSupplierDao;
import irille.Service.Manage.Usr.IUsrSupplierManageService;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrSupplier;
import irille.view.usr.UsrSupplierInfoView;
import irille.view.usr.UsrshopSettingView;
import org.json.JSONException;
import org.json.JSONObject;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/12 Time: 16:22 */
public class UsrSupplierManageServiceImp implements IUsrSupplierManageService {

  @Inject UsrSupplierDao usrSupplierDao;

  public void updShopSetting(int i, UsrshopSettingView bean) {
    UsrSupplier user = usrSupplierDao.getUsrShopSetting(i);
    System.out.println(bean);
    user.setLogo(bean.getLogo());
    user.setSignBackgd(bean.getSignBackGD());
  }

  @Override
  public UsrSupplierInfoView getInfoById(int i) {
    Map map = usrSupplierDao.getInfoById(i);
    UsrSupplierInfoView view = SetBeans.set(map, UsrSupplierInfoView.class);
    view.setLanguage(PltConfigDAO.supplierLanguage(i).name());
    return view;
  }

  @Override
  public UsrshopSettingView getSettingInfoById(int i) {
    UsrSupplier us = usrSupplierDao.getUsrShopSetting(i);
    UsrshopSettingView ssv = new UsrshopSettingView();
    ssv.setLogo(us.getLogo());
    ssv.setSignBackGD(us.getSignBackgd() == null ? "" : us.getSignBackgd());
    ssv.setAdPhoto(string(us.getAdPhoto()));
    ssv.setAdPhotoLink(string(us.getAdPhotoLink()));
    ssv.setCompanyPhoto(string(us.getCompanyPhoto()));
    ssv.setCompanyPhotoLink(string(us.getCompanyPhotoLink()));
    ssv.setHomePageDIY(string(us.getHomePageDiy()));
    ssv.setHomePageOn(us.gtHomePageOn());
    ssv.setCompanyIntroductionPageCustomDecoration(
        string(us.getCompanyIntroductionPageCustomDecoration()));
    ssv.setBottomproductdisplay(us.gtBottomHomeProductsOn());
    ssv.setHomebigposter(us.gtHomePosterOn());
    ssv.setHomebusinessbigposter(us.gtHomeBusinessBigPosterOn());
    ssv.setAboutpagecustomdecoration(us.gtCompanyIntroductionPageCustomDecorationOn());
    return ssv;
  }

  private String string(String str) {
    if (str != null && str != "") {
      return str;
    } else {
      JsonObject json = new JsonObject();
      for (Language value : Language.values()) {
        json.addProperty(value.toString(), "");
      }
      return json.toString();
    }
  }

  private JSONObject isJson(String str) {
    JSONObject json = new JSONObject();
    Language[] lang = Language.values();
    if (!(idJson(str))) {
      for (Language language : lang) {
        try {
          if (str.isEmpty() || str == null) {
            json.put(language.toString(), " ");
          } else {
            json.put(language.toString(), str);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
      return json;
    }
    try {
      json = new JSONObject(str);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return json;
  }

  private boolean idJson(String str) {
    try {
      JSONObject json = new JSONObject(str);
      return true;
    } catch (JSONException e) {
      return false;
    }
  }
}
