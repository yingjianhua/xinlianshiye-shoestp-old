package irille.sellerAction.usr.inf;

import java.io.IOException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理",name="供应商分类")
public interface IUsrSupplierCategoryAction extends ISellerAction{
	
	 @RequestMapping(alias = "获取供应商分类",sort=5)
	public void listTop() throws IOException;
	@RequestMapping(alias = "加载下拉列表",sort=6)
	public void loadlist() throws IOException;

	
}
