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

	@Override
	public void moveToGroup() throws IOException {
		// TODO Auto-generated method stub
		
	}

	private String groupName;
	private Integer groupPkey;
	
	@Override
	public void addGroup() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroup() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editGroup() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
