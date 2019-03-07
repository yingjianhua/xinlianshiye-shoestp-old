package irille.sellerAction.pm;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.IPMReadService;

import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PMMessage;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pm.inf.IPMMessageAction;
import lombok.Getter;
import lombok.Setter;


public class PMMessageAction extends SellerAction<PMMessage> implements IPMMessageAction{

	@Inject
	private IPMMessageService messageService;
	
	@Inject
	private IPMReadService readService;
	@Getter
	@Setter
	private Integer message;
	
	@Override
	public void read() throws Exception {
		readService.read(getSupplier().getPkey(),getMessage());
		writeSuccess();
	}
	

	@Override
	public void list() throws Exception {
		write(messageService.list(getSupplier(), null, ORCVRType.SUPPLIER,getStart(), getLimit()));
	}

}
