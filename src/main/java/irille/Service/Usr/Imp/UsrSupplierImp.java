package irille.Service.Usr.Imp;

import irille.Dao.UsrSupplierDao;
import irille.Service.Usr.IUsrSupplier;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.view.usr.UsrSupplierInfoView;

import javax.inject.Inject;
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
}
