package irille.homeAction.rfq;

import java.io.IOException;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;

import irille.homeAction.AbstractHomeAction;

public class RFQContactAction extends AbstractHomeAction implements IRFQContactAction {
	
	private static final long serialVersionUID = 1L;

	@Inject
    private RFQPurchaseContactService rFQPurchaseContactService;

	private Integer supplierPkey;
	
	@Override
	public void add() throws IOException {
		rFQPurchaseContactService.add(getPurchase(), supplierPkey);
		write();
	}

	@Override
	public void delete() throws IOException {
		rFQPurchaseContactService.delete(getPurchase(), supplierPkey);
		write();
	}

}
