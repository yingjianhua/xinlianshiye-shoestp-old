package irille.sellerAction.rfq.Imp;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module = "询盘", name = "RFQ询盘")
public interface IRFQConsultAction extends ISellerAction, IEnableable {
    @RequestMapping(alias = "获取RFQ询盘列表", sort = 1)
    void getRFQList() throws Exception;

    @RequestMapping(alias = "获取RFQ询盘详情", sort = 2)
    void getRFQInfo() throws Exception;

    @RequestMapping(alias = "RFQ询盘报价", sort = 3)
    void putRFQQuoteInfo() throws IOException;

}
