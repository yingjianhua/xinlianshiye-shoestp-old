package irille.homeAction.rfq;

import java.io.IOException;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;

import irille.homeAction.AbstractHomeAction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	private Integer contactPkey;
	private Integer groupPkey;
	private String groupName;

	@Override
	public void moveToGroup() throws IOException {
		rFQPurchaseContactService.moveToGroup(getPurchase(), contactPkey, groupPkey);
		write();
	}
	
	@Override
	public void addGroup() throws IOException {
		rFQPurchaseContactService.addGroup(getPurchase(), groupName);
		write();
	}

	@Override
	public void deleteGroup() throws IOException {
		rFQPurchaseContactService.deleteGroup(getPurchase(), groupPkey);
		write();
	}

	@Override
	public void editGroup() throws IOException {
		rFQPurchaseContactService.editGroup(getPurchase(), groupPkey, groupName);
		write();
	}

	@Override
	public void listGroup() throws IOException {
		write(rFQPurchaseContactService.listGroup(getPurchase()));
	}

}
