package irille.sellerAction.pdt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "产品", name = "产品类目")
public interface IPdtCatAction extends ISellerAction {

    /**
     *
     */
    @RequestMapping(alias = "查询产品类目")
    void getComboTrigger() throws Exception;

    @RequestMapping(alias = "查询产品类目_Vue")
    void getPdtCatList();


}
