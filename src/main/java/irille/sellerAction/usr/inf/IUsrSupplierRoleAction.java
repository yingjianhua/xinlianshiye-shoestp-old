package irille.sellerAction.usr.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;


@Controller(module="用户管理", name="供应商角色")
public interface IUsrSupplierRoleAction extends ISellerAction{

	/**
	 * 用户功能缓存初始化 前台EXT加载时AJAX同步初台化后，再开始加载菜单
	 * @throws Exception
	 */
	public void initAct() throws Exception;
	/**
	 * 加载商家的功能菜单
	 * @throws Exception
	 */
	@RequestMapping(alias="加载菜单")
	public void loadMenu() throws Exception;
}
