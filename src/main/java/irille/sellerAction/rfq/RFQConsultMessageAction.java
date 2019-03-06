package irille.sellerAction.rfq;

import java.io.File;
import java.io.IOException;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.seller.service.rfq.RFQConsultMessageService;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.pub.util.upload.ImageUpload;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.inf.IRFQConsultMessageAction;
import irille.sellerAction.rfq.view.RFQConsultMessageView;
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
	private Byte type = RFQConsultMessageType.TEXT.getLine().getKey();
	private String content;
	private String imageUrl;
	private Integer productId;
	
	@Override
	public void send() throws IOException {
		RFQConsultMessageView message = null;
		if(RFQConsultMessageType.TEXT.getLine().getKey() == type) {
			message = rFQConsultMessageService.sendTextMessage(getSupplier(), consultId, content);
		} else if(RFQConsultMessageType.IMAGE.getLine().getKey() == type) {
			message = rFQConsultMessageService.sendImageMessage(getSupplier(), consultId, imageUrl);
		} else if(RFQConsultMessageType.URL.getLine().getKey() == type) {
			message = rFQConsultMessageService.sendPrivateExpoPdt(getSupplier(), consultId, productId);
		} else {
			
		}
		write(message);
	}
	
	private Integer consultId;

	@Override
	public void list() throws IOException {
		write(rFQConsultMessageService.page(getSupplier(), getStart(), getLimit(), consultId));
	}
	private String fileFileName = "";
	private File file;

	public void upload() throws IOException {
		if(getSupplier() == null) {
			writeTimeout();
		} else {
			write(ImageUpload.upload(beanClazz(), fileFileName, file));
		}
	}

}
