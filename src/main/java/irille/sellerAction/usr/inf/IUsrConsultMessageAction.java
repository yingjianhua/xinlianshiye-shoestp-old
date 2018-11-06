package irille.sellerAction.usr.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

import org.json.JSONException;

@Controller(name="询盘留言", module="询盘")
public interface IUsrConsultMessageAction extends ISellerAction {

	/**
	 * 发送消息给供应商
	 * @throws JSONException 
	 * @throws IOException 
	 * @author yingjianhua
	 */
	@RequestMapping(alias="留言", sort=1)
	public void send2Purchase() throws IOException, JSONException;
	
    /**
     * 上传图片
     *
     * @throws IOException
     * @author ***
     */
    @RequestMapping(alias = "上传图片", sort = 2)
    public void upload() throws IOException;
}
