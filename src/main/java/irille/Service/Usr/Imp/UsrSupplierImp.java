package irille.Service.Usr.Imp;

import irille.Dao.UsrSupplierDao;
import irille.Service.Usr.IUsrSupplier;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.usr.UsrSupplier;
import irille.view.usr.UsrSupplierInfoView;
import irille.view.usr.UsrshopSettingView;

import javax.inject.Inject;

import com.google.gson.JsonParser;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:25
 */
public class UsrSupplierImp implements IUsrSupplier {
    @Inject
    UsrSupplierDao usrSupplierDao;

    @Override
    public UsrSupplierInfoView getInfoById(int i) {
        Map map= usrSupplierDao.getInfoById(i);
        return SetBeans.set(map, UsrSupplierInfoView.class);
    }

	@Override
	public UsrshopSettingView getSettingInfoById(int i) {
		UsrSupplier us=usrSupplierDao.getUsrShopSetting(i);
		UsrshopSettingView ssv = new UsrshopSettingView();
        ssv.setLogo(us.getLogo());
        ssv.setSignBackGD(us.getSignBackgd());
        ssv.setAdPhoto(us.getAdPhoto());
        ssv.setAdPhotoLink(us.getAdPhotoLink());
        ssv.setCompanyPhoto( new JsonParser().parse(us.getCompanyPhoto()).getAsJsonObject());
        ssv.setCompanyPhotoLink(us.getCompanyPhotoLink());
        ssv.setHomePageDIY(us.getHomePageDiy());
        ssv.setHomePageOn(us.getHomePageOn());
        return ssv;
	}
    
}
