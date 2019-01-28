package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.O2O.Imp.O2OPdtServerImp;
import irille.view.Page;

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
    Page getO2OActivityList(Integer start, Integer limit, Date startDate, Date endDate, String keyword, int supId, Integer countryId);

    Page getO2OActivityInfo(Integer start, Integer limit, Long id, Integer pkey);

}
