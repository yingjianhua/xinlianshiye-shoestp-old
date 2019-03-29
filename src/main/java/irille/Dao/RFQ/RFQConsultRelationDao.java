package irille.Dao.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultRelationDaoImpl;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.RFQConsultRelationView;

@ImplementedBy(RFQConsultRelationDaoImpl.class)
public interface RFQConsultRelationDao {

  RFQConsultRelation findByPkey(Integer relationPkey);

  RFQConsultRelation findByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey);

  Integer countByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey);

  List<RFQConsultRelationView> findAllViewByConsultId(Integer id);

  List<RFQConsultRelation> findAllByConsult_PkeySupplier_Pkey(
      String consultPkeys, Integer supplierPkey);

  void save(RFQConsultRelation bean);

  /**
   * 统计用户的询盘有几个有新供应商报价(如果该供应商已经是联系人 则不进入统计)
   *
   * @param purchasePkey
   * @return
   */
  Integer countNewByPurchaseGroupBySupplier(Integer purchasePkey);
}
