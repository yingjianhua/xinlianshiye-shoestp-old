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
}
