package irille.Dao.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultGroupRelationDaoImpl;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.view.Page;
import irille.view.RFQ.InquiryMessageView;

@ImplementedBy(RFQConsultGroupRelationDaoImpl.class)
public interface RFQConsultGroupRelationDao {

  void save(RFQConsultGroupRelation bean);

  RFQConsultGroupRelation findByConsult_PkeySupplier_Pkey(
      Integer consultPkey, Integer supplierPkey);

  Integer countByGroup_pkey(Integer groupId);

  void deleteByGroup_pkey(Integer groupId);

  List<RFQConsultGroupRelation> findAllByGroup_Pkey(Integer groupPkey);

  Page<InquiryMessageView> message(Integer rec, boolean supplier, Integer start, Integer limit);
}
