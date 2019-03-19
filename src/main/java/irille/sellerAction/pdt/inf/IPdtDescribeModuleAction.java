package irille.sellerAction.pdt.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
@Controller(name = "产品描述模板", module = "产品")
public interface IPdtDescribeModuleAction extends ISellerAction{

	@RequestMapping(alias="查询全部")
	public void getList() throws IOException;

	@RequestMapping(alias="查询详情")
	public void getInfo() throws IOException;

	@RequestMapping(alias="删除")
	public void del() throws IOException;

	@RequestMapping(alias="新增")
	public void add() throws IOException;

	@RequestMapping(alias="修改")
	public void upd() throws IOException;
}
