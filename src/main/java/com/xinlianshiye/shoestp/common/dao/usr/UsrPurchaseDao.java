package com.xinlianshiye.shoestp.common.dao.usr;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.common.dao.usr.impl.UsrPurchaseDaoImpl;

import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrPurchaseDaoImpl.class)
public interface UsrPurchaseDao {

	void save(UsrPurchase purchase);
	
	UsrPurchase findByLoginNameOrEmail(String loginName);
}
