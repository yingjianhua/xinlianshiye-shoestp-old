package irille.sellerAction.plt.inf;

import java.io.IOException;

import org.json.JSONException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="用户管理",name="基础配置")
public interface IPltConfigAction extends ISellerAction {
	
	@RequestMapping(alias="语言列表",sort=10)
	public void enabledLanguage() throws IOException, JSONException;

}
