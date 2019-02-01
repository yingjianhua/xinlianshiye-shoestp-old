package irille.Dao.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultRelationDaoImpl;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.RFQConsultRelationView;

@ImplementedBy(RFQConsultRelationDaoImpl.class)
public interface RFQConsultRelationDao {
	
	RFQConsultRelation findByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey);
	
	Integer countByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey);

	List<RFQConsultRelationView> findAllViewByConsultId(Integer id);
	
	List<RFQConsultRelation> findAllByConsult_PkeySupplier_Pkey(String consultPkeys, Integer supplierPkey);
	
	void save(RFQConsultRelation bean);
}
