package irille.Service.Manage.Usr.Imp;

import irille.Dao.UsrSupplierDao;
import irille.Service.Manage.Usr.IUsrSupplierManageService;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.usr.UsrSupplier;
import irille.view.usr.UsrSupplierInfoView;
import irille.view.usr.UsrshopSettingView;
import java.util.Map;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/12 Time: 16:22
 */
public class UsrSupplierManageServiceImp implements IUsrSupplierManageService {

  @Inject
  UsrSupplierDao usrSupplierDao;

  public void updShopSetting(int i, UsrshopSettingView bean) {
    UsrSupplier user = usrSupplierDao.getUsrShopSetting(i);
    System.out.println(bean);
    user.setLogo(bean.getLogo());
    user.setSignBackgd(bean.getSignBackGD());
  }

  @Override
  public UsrSupplierInfoView getInfoById(int i) {
    Map map = usrSupplierDao.getInfoById(i);
    return SetBeans.set(map, UsrSupplierInfoView.class);
  }

  @Override
  public UsrshopSettingView getSettingInfoById(int i) {
    UsrSupplier us = usrSupplierDao.getUsrShopSetting(i);
    UsrshopSettingView ssv = new UsrshopSettingView();
    ssv.setLogo(us.getLogo());
    ssv.setSignBackGD(us.getSignBackgd());
    ssv.setAdPhoto(isJson(us.getAdPhoto()).toString());
    ssv.setAdPhotoLink(isJson(us.getAdPhotoLink()).toString());
    ssv.setCompanyPhoto(isJson(us.getCompanyPhoto()).toString());
    ssv.setCompanyPhotoLink(isJson(us.getCompanyPhotoLink()).toString());
    ssv.setHomePageDIY(isJson(us.getHomePageDiy()).toString());
    ssv.setHomePageOn(us.getHomePageOn());
    return ssv;
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
