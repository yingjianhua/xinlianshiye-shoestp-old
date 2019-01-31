package irille.Service.Manage.RFQ;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.RFQ.Imp.RFQConsultMessageServiceImpl;
import irille.sellerAction.rfq.view.RFQConsultMessagesView;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultMessageServiceImpl.class)
public interface RFQConsultMessageService {

	RFQConsultMessagesView page(UsrSupplier supplier, Integer start, Integer limit, Integer consultPkey);
}
