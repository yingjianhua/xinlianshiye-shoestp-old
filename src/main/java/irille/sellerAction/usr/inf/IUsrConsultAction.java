package irille.sellerAction.usr.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

import org.json.JSONException;

@Controller(name="公共询盘", module="询盘")
public interface IUsrConsultAction extends ISellerAction {

	/**
	 * 公共询盘列表(分页) 根据国家和标题查询
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	@RequestMapping(alias="列表", sort=1)
	public void page() throws IOException, JSONException;
	
	/**
	 * 显示询盘的详细信息
	 * @throws IOException 
	 * @author yingjianhua
	 */
	@RequestMapping(alias="详情&聊天记录", sort=2)
	public void detail() throws IOException, JSONException;
	
	/**
	 * 抢单报价
	 * @throws IOException 
	 * @author yingjianhua
	 */
	@RequestMapping(alias="抢单", sort=3)
	public void quote() throws IOException;
}
