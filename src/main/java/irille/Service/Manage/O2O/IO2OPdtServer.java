package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.Imp.O2OPdtServerImp;
import irille.shop.usr.UsrSupplier;
import irille.view.O2O.O2OActivityInfoView;
import irille.view.O2O.O2OActivityPdtInfoView;
import irille.view.Page;

import java.util.Date;
import java.util.List;

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
    Page getO2OActivityList(UsrSupplier supplier, Integer start, Integer limit, Date startDate, Date endDate, String keyword,Integer status, int supId, Integer countryId);

    Page getO2OActivityInfo(Integer start, Integer limit, Integer id, Integer pkey);

    O2OActivityInfoView load(UsrSupplier supplier,Integer pkey);

    List<O2OActivityPdtInfoView> listAllGeneral(UsrSupplier supplier,Integer activity);

    void lowerAndUpper(Integer pkey, String reason, O2O_ProductStatus status);

    void appr(Integer pkey,String reason,O2O_ProductStatus status);
}
