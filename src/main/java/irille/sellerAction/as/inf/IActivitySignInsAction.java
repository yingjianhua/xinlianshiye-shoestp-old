package irille.sellerAction.as.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "其他", name = "PK大赛")
public interface IActivitySignInsAction extends ISellerAction {
  @RequestMapping(alias = "查看报名Pk大赛列表")
  void getActivitySignInsList() throws Exception;
}
