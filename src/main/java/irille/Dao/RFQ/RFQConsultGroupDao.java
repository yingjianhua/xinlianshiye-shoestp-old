package irille.Dao.RFQ;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultGroupDaoImpl;
import irille.Entity.RFQ.RFQConsultGroup;

@ImplementedBy(RFQConsultGroupDaoImpl.class)
public interface RFQConsultGroupDao {

	void save(RFQConsultGroup bean);
	
	Integer countBySupplier_PkeyName(Integer supplierId, String name);
	
	RFQConsultGroup findByPkeySupplier_Pkey(Integer pkey, Integer supplierId);
	
	void delete(Integer pkey);
}
