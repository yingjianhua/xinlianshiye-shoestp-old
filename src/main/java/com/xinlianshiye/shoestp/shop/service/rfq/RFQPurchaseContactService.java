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
	
	/**
	 * 添加分组
	 * @param purchase 采购商
	 * @param groupName 分组名字
	 * @author Jianhua Ying
	 */
	void addGroup(UsrPurchase purchase, String groupName);
	
	/**
	 * 删除分组
	 * @param purchase 采购商
	 * @param groupPkey 分组主键
	 * @author Jianhua Ying
	 */
	void deleteGroup(UsrPurchase purchase, Integer groupPkey);
	
	/**
	 * 编辑分组
	 * @param purchase 采购商
	 * @param groupPkey 分组主键
	 * @param groupName 分组名字
	 * @author Jianhua Ying
	 */
	void editGroup(UsrPurchase purchase, Integer groupPkey, String groupName);
	
	/**
	 * 移动分组
	 * @param purchase 采购商
	 * @param contactPkey 联系人主键
	 * @param groupPkey 分组主键
	 * @author Jianhua Ying
	 */
	void moveToGroup(UsrPurchase purchase, Integer contactPkey, Integer groupPkey);
	
	//联系人列表
}
