package irille.Dao.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultGroupDaoImpl;
import irille.Entity.RFQ.RFQConsultGroup;

@ImplementedBy(RFQConsultGroupDaoImpl.class)
public interface RFQConsultGroupDao {

	void save(RFQConsultGroup bean);
	
	Integer countBySupplier_PkeyName(Integer supplierPkey, String name);
	
	RFQConsultGroup findByPkeySupplier_Pkey(Integer pkey, Integer supplierPkey);
	
	List<RFQConsultGroup> findAllBySupplier_Pkey(Integer supplierPkey);
	
	void delete(Integer pkey);
}
