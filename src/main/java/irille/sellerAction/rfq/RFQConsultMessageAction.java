package irille.sellerAction.rfq;

import java.io.IOException;

import com.google.inject.Inject;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.Service.Manage.RFQ.RFQConsultMessageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.inf.IRFQConsultMessageAction;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RFQConsultMessageAction extends SellerAction<RFQConsultMessage> implements IRFQConsultMessageAction {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RFQConsultMessageService rFQConsultMessageService;

	@Override
	public void send() throws IOException {
		// TODO Auto-generated method stub

	}
	
	private Integer consultId;

	@Override
	public void list() throws IOException {
		write(rFQConsultMessageService.page(getSupplier(), getStart(), getLimit(), consultId));
	}

}
