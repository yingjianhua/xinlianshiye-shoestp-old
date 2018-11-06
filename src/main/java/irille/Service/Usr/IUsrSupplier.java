package irille.Service.Usr;

import com.google.inject.ImplementedBy;
import irille.Service.Usr.Imp.UsrSupplierImp;
import irille.view.usr.UsrSupplierInfoView;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:23
 */
@ImplementedBy(UsrSupplierImp.class)
public interface IUsrSupplier {
    /**
     * @Description: 获取的商家详细信息
     * @date 2018/11/5 16:36
     * @author lijie@shoestp.cn
     */
    UsrSupplierInfoView getInfoById(int i);
}
