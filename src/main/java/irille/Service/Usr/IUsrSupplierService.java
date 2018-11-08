package irille.Service.Usr;

import com.google.inject.ImplementedBy;
import irille.Service.Usr.Imp.UsrSupplierImp;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.FavoritesView;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.idu.IduPage;
import irille.view.usr.UsrSupplierInfoView;
import irille.view.usr.UsrshopSettingView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:23
 */
@ImplementedBy(UsrSupplierImp.class)
public interface IUsrSupplierService {
    /**
     * @Description: 获取的商家详细信息
     * @date 2018/11/5 16:36
     * @author lijie@shoestp.cn
     */
    UsrSupplierInfoView getInfoById(int i);

    /**
     * @deprecated:获取店铺装修信息
     * @date 2018/11/6 20:55
     * @author zjl
     */
    UsrshopSettingView getSettingInfoById(int i);

    /**
     * @Description: 根据分类获取收藏夹内容
     * @author lijie@shoestp.cn
     * @date 2018/8/22 18:23
     */
    List<FavoritesView> getFavoritesListByCat(IduPage page, int cat, int purId, Sys.OYn showState);

    Long getFavoritesCountByCat(IduPage page, int cat, int purId, Sys.OYn showState);

    List<SupplierListView> getSupplierListAndPdtList(IduPage iduPage);
}
