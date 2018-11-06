package irille.sellerAction.pdt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "产品", name = "产品属性")
public interface IPdtAttrAction extends ISellerAction {

    @RequestMapping(alias = "查询包括资明细项")
    public void AttrLinelist() throws Exception;

    @RequestMapping(alias = "查询产品所有属性")
    void AttrList() throws Exception;
}
