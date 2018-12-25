package irille.sellerAction.plt.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理", name="运费")
public interface IPltFreightSellerAction extends ISellerAction {
	@RequestMapping(alias="恢复默认公司", sort=1)
	public void recovery() throws Exception;

	@RequestMapping(alias="运费模板",sort=2)
	public void listfreight() throws Exception;
	@RequestMapping(alias="上传图片",sort=3)
	public void upload() throws Exception;
	@RequestMapping(alias="模糊查询",sort=4)
	public void listfreightlike() throws Exception;
}
