package irille.Service.Manage.RFQ;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.RFQ.Imp.RFQConsultGroupServiceImpl;
import irille.sellerAction.rfq.view.RFQConsultGroupView;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultGroupServiceImpl.class)
public interface RFQConsultGroupService {

	RFQConsultGroupView create(UsrSupplier supplier, RFQConsultGroupView view);
    
	RFQConsultGroupView edit(UsrSupplier supplier, RFQConsultGroupView view);
    
    void delete(UsrSupplier supplier, RFQConsultGroupView view);
}
