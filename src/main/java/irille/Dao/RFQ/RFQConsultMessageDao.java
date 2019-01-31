package irille.Dao.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultMessageDaoImpl;
import irille.Entity.RFQ.RFQConsultMessage;

@ImplementedBy(RFQConsultMessageDaoImpl.class)
public interface RFQConsultMessageDao {

	List<RFQConsultMessage> findAll(Integer start, Integer limit, Integer consultPkey, Integer supplierPkey);
	
	void save(RFQConsultMessage bean);
}
