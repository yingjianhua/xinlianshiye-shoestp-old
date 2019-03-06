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
	 * @author Jianhua Ying
	 */
	void profile() throws IOException;
	
	/**
	 * 编辑头像
	 * @throws IOException 
	 * @author Jianhua Ying
	 */
	void editAvatar() throws IOException;
	
	/**
	 * 上传文件(图片)
	 * @throws IOException 
	 * @author Jianhua Ying
	 */
	void upload() throws IOException;
	
	/**
	 * 我的收藏列表
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void favorite() throws IOException;
	
	/**
	 * 修改密码
	 * <p>需要验证密码
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void changePassword() throws IOException;
	
	/**
	 * 修改邮箱
	 * <p>需要验证密码
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void changeEmail() throws IOException;
	
	/**
	 * 编辑账户基础信息
	 * <p>可修改字段包括
	 * <li>性别
	 * <li>姓
	 * <li>名
	 * <li>手机号码
	 * <li>公司
	 * <li>地址
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void editAccount() throws IOException;
	
	/**
	 * 获取账号信息(用于设置账号信息页面的反填充)
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void accountProfile() throws IOException;
}
