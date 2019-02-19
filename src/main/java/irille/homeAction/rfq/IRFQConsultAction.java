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
