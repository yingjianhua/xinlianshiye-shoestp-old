package irille.homeAction.usr;

import java.io.IOException;

/**
 * 用户相关请求
 * @author Jianhua Ying
 *
 */
public interface IPurchaseAction {

	/**
	 * 获取个人信息
	 * @throws IOException 
	 */
	void profile() throws IOException;
	
	/**
	 * 编辑头像
	 * @throws IOException 
	 */
	void editAvatar() throws IOException;
	
	/**
	 * 上传文件(图片)
	 * @throws IOException 
	 */
	void upload() throws IOException;
}
