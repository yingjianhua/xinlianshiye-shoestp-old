	package irille.homeAction.cnt.inf;

import irille.pub.svr.Controller;
import irille.sellerAction.ISellerAction;

@Controller(module="单页管理", name="单页")
public interface ICntSqlPageAction extends ISellerAction {
	/**
	 * 获取当前pkey所对应的单页
	 * @author yingjianhua
	 */
	public void findpage() throws Exception;
}
