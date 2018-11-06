package irille.homeAction.usr.inf;

import java.io.IOException;

import org.json.JSONException;

public interface IUsrConsultAction {
	
	/**
	 * 公共询盘列表页面
	 * @return
	 * @author yingjianhua
	 */
	public String publicListView();
	
	/**
	 * 发布询盘页面(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	public String publishView();
	
	/**
	 * 我的询盘列表页面(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	public String listView();
	
	/**
	 * 询盘详情页面(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	public String detailView();
	
	/**
	 * 采购商发布询盘
	 * @throws Exception
	 * @author yingjianhua
	 */
	public void publish() throws Exception;
	
	/**
	 * 列表公共询盘数据
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	public void pagePublic() throws IOException, JSONException;
	
	/**
	 * 列表我的询盘数据
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	public void pagePrivate() throws IOException, JSONException;
	
	/**
	 * 查看询盘详细信息
	 * @throws Exception
	 * @author yingjianhua
	 */
	public void detail() throws Exception;
	
	/**
	 * 删除询盘
	 * @throws Exception
	 */
	public void delete() throws Exception;
	
	/**
	 * 批量删除询盘
	 * @throws Exception
	 */
	public void deletes() throws Exception;

}
