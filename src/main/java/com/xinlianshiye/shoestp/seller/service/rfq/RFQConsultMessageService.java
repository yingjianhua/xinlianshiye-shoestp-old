package com.xinlianshiye.shoestp.seller.service.rfq;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.seller.service.rfq.impl.RFQConsultMessageServiceImpl;

import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.sellerAction.rfq.view.RFQConsultMessagesView;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultMessageServiceImpl.class)
public interface RFQConsultMessageService {

	/**
	 * 查询聊天记录
	 * 
	 * @param supplier 供应商
	 * @param start 开始记录数
	 * @param limit 每页记录数
	 * @param consultPkey 询盘主键
	 * @return 聊天记录 包含聊天双方的姓名,聊天信息
	 * @author Jianhua Ying
	 */
	RFQConsultMessagesView page(UsrSupplier supplier, Integer start, Integer limit, Integer consultPkey);
	/**
	 * 发送文本消息
	 * @param supplier 供应商
	 * @param consultPkey 询盘
	 * @param content 文本内容
	 * @author Jianhua Ying
	 */
	RFQConsultMessageView sendTextMessage(UsrSupplier supplier, Integer consultPkey, String content);
	
	/**
	 * 发送图片消息
	 * 
	 * @param supplier 供应商
	 * @param consultPkey 询盘
	 * @param imageUrl 图片路径
	 * @author Jianhua Ying
	 */
	RFQConsultMessageView sendImageMessage(UsrSupplier supplier, Integer consultPkey, String imageUrl);
	
	/**
	 * 发送私人展厅商品的链接消息
	 * 
	 * @param supplier 供应商
	 * @param consultPkey 询盘
	 * @param productPkey 私人展厅商品的主键
	 * @author Jianhua Ying
	 */
	RFQConsultMessageView sendPrivateExpoPdt(UsrSupplier supplier, Integer consultPkey, Integer productPkey);
	
	/**
	 * 核对私人展厅产品密钥
	 * <p>若密钥存在并且没有过期,则返回产品主键,否则返回null
	 * @param expoKey 私人展厅产品密钥
	 * @author Jianhua Ying
	 */
	Integer checkPrivateExpoKey(String expoKey);
}
