package irille.homeAction.rfq;

import java.io.IOException;

public interface IRFQConsultAction {

	/**
	 * 我的询盘(包括RFQ)
	 * 搜索条件
	 * 关键字, 未读, 询盘类型(询盘, RFQ, 私人展厅询盘等)
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void pageMine() throws IOException ;
	
	/**
	 * 在询盘关系的基础上建立联系人关系
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void addContact() throws IOException;
	
	/**
	 * 删除联系人关系
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void deleteContact() throws IOException;
}
