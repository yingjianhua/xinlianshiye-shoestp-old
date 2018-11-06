package irille.sellerAction.usr.inf;

import java.io.IOException;

import org.json.JSONException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(name="基础配置", module="用户管理")
public interface IUsrSupImAction extends ISellerAction{
	@RequestMapping(alias="获取设备类型", sort=1)
	public void getDeviceType() throws Exception;
	
	
	@RequestMapping(alias="添加IM配置",sort=2)
	public void addImSetting() throws IOException;
	
	@RequestMapping(alias="获取im列表",sort=3)
	public void getImSetting() throws Exception;
	
	@RequestMapping(alias="修改IM配置",sort=4)
	public void updImSetting() throws IOException;
}
