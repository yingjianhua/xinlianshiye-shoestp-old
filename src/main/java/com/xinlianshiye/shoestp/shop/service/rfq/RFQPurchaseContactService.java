package com.xinlianshiye.shoestp.shop.service.rfq;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQPurchaseContactServiceImpl;

import irille.shop.usr.UsrPurchase;

@ImplementedBy(RFQPurchaseContactServiceImpl.class)
public interface RFQPurchaseContactService {
	
	/**
	 * 添加联系人
	 * @param purchase 采购商
	 * @param supplierPkey 供应商主键
	 * @author Jianhua Ying
	 */
	void add(UsrPurchase purchase, Integer supplierPkey);
	
	/**
	 * 删除联系人
	 * @param purchase 采购商
	 * @param supplierPkey 供应商主键
	 * @author Jianhua Ying
	 */
	void delete(UsrPurchase purchase, Integer supplierPkey);
	
	//联系人列表
}
