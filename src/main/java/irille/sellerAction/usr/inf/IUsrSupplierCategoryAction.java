package irille.sellerAction.usr.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理",name="供应商分类")
public interface IUsrSupplierCategoryAction extends ISellerAction{

	 @RequestMapping(alias = "获取供应商分类",sort=5)
	public void listTop() throws IOException;

}
