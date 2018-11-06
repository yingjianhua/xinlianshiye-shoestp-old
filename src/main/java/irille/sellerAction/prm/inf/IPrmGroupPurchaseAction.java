package irille.sellerAction.prm.inf;

import java.io.IOException;

import org.json.JSONException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理",name="联合采购")
public interface IPrmGroupPurchaseAction extends ISellerAction,IEnableable{
	@RequestMapping(alias="获取活动产品", sort=1)
	public void showUnionPro() throws Exception;
	
	@RequestMapping(alias="显示规格",sort=2)
	public void showProSpec() throws Exception;
	
	@RequestMapping(alias="获取活动列表",sort=4)
	public void groupList() throws Exception;
	
	@RequestMapping(alias="统计订单",sort=5)
	public void send()throws Exception;
	
	@RequestMapping(alias="上下架商品",sort=6)
	public void underCarriage() throws IOException;
	
	@RequestMapping(alias="删除",sort=7)
	public void logicDelete() throws IOException;
	
	@RequestMapping(alias="复制产品详情",sort=8)
	public void copyDetail() throws IOException;

}
