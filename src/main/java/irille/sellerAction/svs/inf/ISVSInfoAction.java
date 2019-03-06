package irille.sellerAction.svs.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "SVS管理", name = "SVS认证")
public interface ISVSInfoAction extends ISellerAction {

	@RequestMapping(alias = "申请认证", sort = 1)
	void application() throws Exception;

	@RequestMapping(alias = "修改认证信息", sort = 2)
	void updAutInfo() throws Exception;

	@RequestMapping(alias = "获取信息", sort = 2)
	void getAutInfo() throws Exception;

}
