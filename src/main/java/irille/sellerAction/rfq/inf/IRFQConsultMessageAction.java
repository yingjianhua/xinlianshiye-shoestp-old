package irille.sellerAction.rfq.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

@Controller(module = "询盘", name = "RFQ询盘")
public interface IRFQConsultMessageAction extends ISellerAction, IEnableable {

    /**
     * 询盘聊天消息查询
     */
    @RequestMapping(alias = "询盘聊天消息查询", sort = 1)
    void list() throws IOException;

    /**
     * 发送消息
     * 有文本消息
     * 有图片消息
     * 有产品链接(有时效)
     */
    @RequestMapping(alias = "发送消息", sort = 2)
    void send() throws IOException;

    @RequestMapping(alias = "上传图片重写", sort = 3)
    void upload() throws IOException;
}
