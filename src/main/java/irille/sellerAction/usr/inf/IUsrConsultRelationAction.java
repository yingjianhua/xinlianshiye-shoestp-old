package irille.sellerAction.usr.inf;

import java.io.IOException;

import org.json.JSONException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(name="专属询盘", module="询盘")
public interface IUsrConsultRelationAction extends ISellerAction {

	/**
	 * 公共询盘列表(分页) 根据国家和标题查询
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	@RequestMapping(alias="列表", sort=1)
	public void page() throws IOException, JSONException;
}
