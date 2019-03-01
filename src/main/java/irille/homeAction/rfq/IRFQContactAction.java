package irille.homeAction.rfq;

import java.io.IOException;

public interface IRFQContactAction {
	
	/**
	 * 在询盘关系的基础上建立联系人关系
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void add() throws IOException;
	
	/**
	 * 删除联系人关系
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void delete() throws IOException;
	
	/**
	 * 列表联系人 包含联系人的询盘记录
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void page() throws IOException;
	
	/**
	 * 移动分组
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void moveToGroup() throws IOException;
	
	/**
	 * 添加分组
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void addGroup() throws IOException;
	
	/**
	 * 删除分组
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void deleteGroup() throws IOException;
	
	/**
	 * 修改分组
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void editGroup() throws IOException;
	
	/**
	 * 列表分组
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void listGroup() throws IOException;
}
