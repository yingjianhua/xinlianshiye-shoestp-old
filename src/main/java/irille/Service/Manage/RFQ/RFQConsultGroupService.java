package irille.Service.Manage.RFQ;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.RFQ.Imp.RFQConsultGroupServiceImpl;
import irille.sellerAction.rfq.view.RFQConsultGroupView;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultGroupServiceImpl.class)
public interface RFQConsultGroupService {

	RFQConsultGroupView create(UsrSupplier supplier, RFQConsultGroupView view);
    
	RFQConsultGroupView edit(UsrSupplier supplier, RFQConsultGroupView view);
	
	List<RFQConsultGroupView> list(UsrSupplier supplier);
    
    void delete(UsrSupplier supplier, RFQConsultGroupView view);
}
