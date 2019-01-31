package irille.sellerAction.rfq.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

@Controller(module = "询盘", name = "RFQ询盘")
public interface IRFQConsultMessageAction extends ISellerAction, IEnableable {

	/**
	 * 询盘聊天消息查询
	 */
	void list() throws IOException ;
	
	/**
	 * 发送消息
	 * 有文本消息
	 * 有图片消息
	 * 有产品链接(有时效)
	 */
	void send() throws IOException ;
}
