package irille.platform.rfq;

import java.io.IOException;

/**
 * RFQ询盘控制层接口
 * 
 * @author Jianhua Ying
 *
 */
public interface IRFQConsultAction {

	/**
	 * <h1>RFQ列表</h1>
	 * 
	 * <p>搜索条件</p>
	 * <ul>
	 * <li>询盘状态</li>
	 * <li>询盘名字</li>
	 * <li>采购商名称</li>
	 * <li>国家</li>
	 * </ul>
	 * @throws IOException 将结果传递至请求端时可能出现IO异常
	 * @author Jianhua Ying
	 * 
	 */
	void list() throws IOException ;
	
	/**
	 * <h1>查看询盘详情</h1>
	 * 
	 * @throws IOException 将结果传递至请求端时可能出现IO异常
	 * @author Jianhua Ying
	 */
	void detail() throws IOException ;
	
	/**
	 * 审核
	 * 
	 * <p>审核通过和审核失败
	 * 
	 * @throws IOException
	 */
	void approve() throws IOException ;
	
	/**
	 * 删除询盘
	 * 
	 * @throws IOException
	 */
	void delete() throws IOException ;

	/**
	 * 是否推荐RFQ到首页
	 * @throws IOException
	 */
	void recommend()throws IOException;
	
	/**
	 * 商家报价信息
	 * 
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	void offerInfo() throws IOException ;
}
