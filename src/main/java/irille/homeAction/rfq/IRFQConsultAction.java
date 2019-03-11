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
	void pageMine() throws IOException;
	
	/**
	 * 统计我的询盘的未读消息数量, 一个询盘的一个供应商的多条消息记一条
	 * @throws IOException
	 */
	void unreadCount() throws IOException;
	
	/**
	 * 询盘详情
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void detail() throws IOException;
	
	/**
	 * 编辑RFQ询盘
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void edit() throws IOException;
	
	/**
	 * 给询盘添加格外的信息
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void addInformation() throws IOException;
	
	/**
	 * 关闭RFQ询盘
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void close() throws IOException;
	
    /**
     * 询盘聊天消息查询
     * @throws IOException
     * @author Jianhua Ying
     */
    void pageMsgs() throws IOException;
    
    /**
     * 给询盘添加图片
     * <p>上限五张 超出五张不能继续增加, 目前只有"供应商询盘"能在发布后继续添加图片
     * @throws IOException
     * @author Jianhua Ying
     */
    void addImage() throws IOException;
    
    /**
     * 添加感兴趣的产品
     * <p>仅"供应商询盘"能添加感兴趣产品, 上限50个
     * @throws IOException
     * @author Jianhua Ying
     */
    void addProductRequest() throws IOException;

    /**
     * 发送消息
     * 有文本消息
     * 有图片消息
     * 有产品链接(有时效)
     * @throws IOException
     * @author Jianhua Ying
     */
    void sendMessage() throws IOException;
	
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
//	void quotationDetail() throws IOException;

	/**
	 * 获取RFQ列表
	 * @author zjl
	 * @Date 2019/2/27 16:10
	 * @return
	 * @throws IOException
	 */
	void getRFQList() throws IOException;

	/**
	 * 获取RFQ详情
	 * @author zjl
	 * @Date 2019/2/27 16:10
	 * @throws IOException
	 */
	void getRFQDetails() throws IOException;
}
