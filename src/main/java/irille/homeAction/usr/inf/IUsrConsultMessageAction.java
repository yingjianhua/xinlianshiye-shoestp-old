package irille.homeAction.usr.inf;

import java.io.IOException;

import org.json.JSONException;

public interface IUsrConsultMessageAction {

	/**
	 * 询盘留言页面
	 * @author yingjianhua
	 */
	public String dialogView();
	
	/**
	 * 获取该询盘每个供应商的所有留言记录
	 * @author yingjianhua
	 */
	public void list() throws IOException, JSONException;
	
	/**
	 * 发送消息给供应商
	 * @author yingjianhua
	 */
	public void send2Supplier() throws IOException, JSONException;
}
