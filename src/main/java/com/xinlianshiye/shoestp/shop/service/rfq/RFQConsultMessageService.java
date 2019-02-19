package com.xinlianshiye.shoestp.shop.service.rfq;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQConsultMessageServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessagesView;

import irille.shop.usr.UsrPurchase;

@ImplementedBy(RFQConsultMessageServiceImpl.class)
public interface RFQConsultMessageService {

	/**
	 * 分页查询聊天信息
	 * @param purchase 采购商
	 * @param relationPkey 询盘关联主键
	 * @param start 开始记录数
	 * @param limit 每页记录数
	 * @author Jianhua Ying
	 * @return 
	 */
	RFQConsultMessagesView page(UsrPurchase purchase, Integer relationPkey, Integer start, Integer limit);
	
	void send(UsrPurchase purchase, Integer relationPkey, String content);
}
