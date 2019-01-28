package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.O2O.Imp.O2OPdtServerImp;
import irille.pub.Page;
import irille.pub.idu.IduPage;
import java.util.Date;

/**
 * O2O商品添加
 * Date: 2019/1/26
 * Time: 15:59
 */
@ImplementedBy(O2OPdtServerImp.class)
public interface IO2OPdtServer {

    /***
     *  o2o活动列表
     * @date 2019/1/26 16:01
     */
    Page getO2OActivityList(IduPage page, Date startDate, Date endDate, String keyword);

    Page getO2OActivityInfo(IduPage page, Long id);

}
