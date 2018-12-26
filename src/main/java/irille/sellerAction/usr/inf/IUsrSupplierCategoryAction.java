package irille.sellerAction.usr.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module="用户管理",name="供应商分类")
public interface IUsrSupplierCategoryAction extends ISellerAction{

	 @RequestMapping(alias = "获取供应商分类",sort=5)
	public void listTop() throws IOException;
	@RequestMapping(alias = "加载下拉列表",sort=6)
	public void loadlist() throws IOException;


}
