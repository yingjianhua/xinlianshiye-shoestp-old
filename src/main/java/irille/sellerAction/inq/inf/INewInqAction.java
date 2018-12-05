package irille.sellerAction.inq.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "新询盘",name="询盘")
public interface INewInqAction extends ISellerAction {
    @RequestMapping(alias = "获取新询盘列表",sort = 5)
    void getList()throws Exception;
}
