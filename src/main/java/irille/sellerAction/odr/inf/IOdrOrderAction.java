package irille.sellerAction.odr.inf;

import java.io.IOException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理", name="订单")
public interface IOdrOrderAction extends ISellerAction {
	@RequestMapping(alias="查询订单", sort=3)
	public void select() throws Exception;
	
	@RequestMapping(alias="修改地址", sort=4)
	public void loadpay() throws Exception;
	
	@RequestMapping(alias="支付方式", sort=5)
	public void loadstate() throws Exception;
	
	@RequestMapping(alias="订单类型", sort=11)
	public void loadtype() throws Exception;
	
	@RequestMapping(alias="订单地址", sort=6)
	public void updaddress() throws Exception;
	/**
	 * 价格运费保险费,总价等
	 * @throws Exception
	 */
	@RequestMapping(alias="修改订单内容", sort=7)
	public void updorder() throws Exception;
	//取消订单和取消原因
	@RequestMapping(alias="修改订单状态", sort=11)
	public void cancel() throws IOException;
	//改变状态
	@RequestMapping(alias="修改订单状态", sort=8)
	public void updline() throws Exception;
	
	@RequestMapping(alias="修改订单运费", sort=9)
	public void updcompany() throws Exception;
	
	@RequestMapping(alias="修改订单确认发货", sort=10)
	public void upddeliver() throws Exception;
	
}
 