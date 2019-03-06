package irille.sellerAction.rfq;

import java.io.IOException;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.seller.service.rfq.RFQConsultGroupService;

import irille.Entity.RFQ.RFQConsultGroup;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.inf.IRFQConsultGroupAction;
import irille.sellerAction.rfq.view.RFQConsultGroupView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RFQConsultGroupAction extends SellerAction<RFQConsultGroup> implements IRFQConsultGroupAction {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RFQConsultGroupService rFQConsultGroupService;

	private RFQConsultGroupView group;
	
	@Override
	public void ins() throws IOException {
		write(rFQConsultGroupService.create(getSupplier(), group));
	}

	@Override
	public void upd() throws IOException {
		write(rFQConsultGroupService.edit(getSupplier(), group));
	}

	@Override
	public void del() throws IOException {
		rFQConsultGroupService.delete(getSupplier(), group);
		write();
	}
	
	@Override
	public void list() throws IOException {
		write(rFQConsultGroupService.list(getSupplier()));
	}

}
