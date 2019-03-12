package irille.sellerAction.pdt.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "产品", name = "产品属性")
public interface IPdtAttrAction extends ISellerAction {

    @RequestMapping(alias = "查询包括资明细项")
    public void AttrLinelist() throws Exception;

    @RequestMapping(alias = "查询产品所有属性")
    void AttrList() throws Exception;
    
    @RequestMapping(alias = "新商家新增属性与属性明细")
    public void addAttr() throws IOException;
    
    @RequestMapping(alias = "新商家删除属性与属性明细")
    public void del() throws IOException;
}
