package irille.sellerAction.eo.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module="用户管理", name="新订单")
public interface IEasyOdrAction extends ISellerAction {
	@RequestMapping(alias="新订单列表", sort=1)
	public void newOrderlist() throws Exception;
	@RequestMapping(alias="导出表格", sort=2)
	public void oneexport() throws Exception;
	@RequestMapping(alias="导出多表格", sort=4)
	public void manyexport() throws Exception;


}
