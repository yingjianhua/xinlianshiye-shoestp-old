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
	 * 询盘详情
	 * @throws IOException
	 * @author Jianhua Ying
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void detail() throws IOException;
	
    /**
     * 询盘聊天消息查询
     * @throws IOException
     * @author Jianhua Ying
     */
    void pageMsgs() throws IOException;

    /**
     * 发送消息
     * 有文本消息
     * 有图片消息
     * 有产品链接(有时效)
     * @throws IOException
     * @author Jianhua Ying
     */
    void send() throws IOException;
	
	/**
	 * 删除报价
	 * @throws IOExcepton
	 * @author Jianhua Ying
	 */
	void deleteQuotation() throws IOException;
	
	/**
	 * 报价详情
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void quotationDetail() throws IOException;

}
