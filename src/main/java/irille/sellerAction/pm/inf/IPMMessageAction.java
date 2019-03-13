package irille.sellerAction.pm.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "其他", name = "站内信")
public interface IPMMessageAction extends ISellerAction {
  @RequestMapping(alias = "获取站内信列表")
  void list() throws Exception;

  @RequestMapping(alias = "阅读站内信")
  void read() throws Exception;
}
