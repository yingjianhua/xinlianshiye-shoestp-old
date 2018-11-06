package irille.sellerAction.pdt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

//@Controller(module="产品", name="询盘")
public interface IPdtConsultAction extends ISellerAction {
	
	/**
	 * 更新LOGO
	 * @throws Exception
	 */
	@RequestMapping(alias="更新询盘次数" ,sort=4)
	public void updCount() throws Exception;
	
	@RequestMapping(alias="条件查询", sort=1)
	public void termSelect() throws Exception;
	@RequestMapping(alias="查询询盘列表", sort=3)
	public void selConsult() throws Exception;
	@RequestMapping(alias="根据主键查询询盘", sort=5)
	public void selConsultByPkey() throws Exception;

}
