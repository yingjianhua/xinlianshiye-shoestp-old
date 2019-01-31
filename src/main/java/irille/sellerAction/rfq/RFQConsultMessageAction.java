package irille.sellerAction.rfq;

import java.io.IOException;

import com.google.inject.Inject;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
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
	
	/**
	 * 消息类型 @see RFQConsultMessageType
	 */
	private Byte type;
	private String content;
	private String imageUrl;
	private Integer productId;
	
	@Override
	public void send() throws IOException {
		if(RFQConsultMessageType.TEXT.getLine().getKey() == type) {
			rFQConsultMessageService.sendTextMessage(getSupplier(), consultId, content);
		} else if(RFQConsultMessageType.IMAGE.getLine().getKey() == type) {
			rFQConsultMessageService.sendImageMessage(getSupplier(), consultId, imageUrl);
		} else if(RFQConsultMessageType.TEXT.getLine().getKey() == type) {
			rFQConsultMessageService.sendPrivateExpoPdt(getSupplier(), consultId, productId);
		} else {
			
		}

	}
	
	private Integer consultId;

	@Override
	public void list() throws IOException {
		write(rFQConsultMessageService.page(getSupplier(), getStart(), getLimit(), consultId));
	}

}
