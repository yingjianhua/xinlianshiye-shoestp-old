package irille.sellerAction.as.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "PK大赛", name = "PK大赛")
public interface IActivitySignInsAction extends ISellerAction {
    @RequestMapping(alias = "查看报名Pk大赛列表")
    void getActivitySignInsList() throws Exception;
}
