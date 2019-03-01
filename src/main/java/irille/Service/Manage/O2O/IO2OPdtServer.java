package irille.Service.Manage.O2O;

import java.util.Date;
import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Service.Manage.O2O.Imp.O2OPdtServerImp;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.O2O.O2OActivityInfoView;
import irille.view.O2O.O2OActivityPdtInfoView;
import irille.view.v2.Pdt.PdtNewPdtInfo;

/** O2O商品添加 Date: 2019/1/26 Time: 15:59 */
@ImplementedBy(O2OPdtServerImp.class)
public interface IO2OPdtServer {

  /**
   * * o2o活动列表
   *
   * @date 2019/1/26 16:01
   */
  Page getO2OActivityList(
      UsrSupplier supplier,
      Integer start,
      Integer limit,
      Date startDate,
      Date endDate,
      String keyword,
      Integer status,
      int supId,
      Integer countryId);

  Page getO2OActivityInfo(Integer start, Integer limit, Integer id, Integer pkey);

  O2OActivityInfoView load(UsrSupplier supplier, Integer pkey);

  List<O2OActivityPdtInfoView> listAllGeneral(UsrSupplier supplier, Integer activity);

  void lowerAndUpper(Integer pkey, String reason, O2O_ProductStatus status);

  void appr(Integer pkey, String reason, O2O_ProductStatus status);

  List<PdtNewPdtInfo> O2OList(UsrPurchase purchase, Integer start, Integer limit);

  List<PdtNewPdtInfo> O2OPrivateList(UsrPurchase purchase, int start, int limit);

  Boolean judgeO2o(PdtProduct product);
}
