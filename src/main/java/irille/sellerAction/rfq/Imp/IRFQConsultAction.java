package irille.sellerAction.rfq.Imp;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

@Controller(module = "询盘", name = "RFQ询盘")
public interface IRFQConsultAction extends ISellerAction, IEnableable {
    @RequestMapping(alias = "获取活动产品", sort = 1)
    void showUnionPro() throws Exception;

}
