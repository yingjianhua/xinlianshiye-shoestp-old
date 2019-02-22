package com.xinlianshiye.shoestp.common.dao.usr.impl;

import com.xinlianshiye.shoestp.common.dao.usr.UsrPurchaseDao;

import irille.pub.bean.Query;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseDaoImpl implements UsrPurchaseDao {

	@Override
	public void save(UsrPurchase purchase) {
		if(purchase.getPkey() != null)
			purchase.upd();
		else
			purchase.ins();
	}

	@Override
	public UsrPurchase findByLoginNameOrEmail(String loginName) {
		return Query.selectFrom(UsrPurchase.class).WHERE(UsrPurchase.T.LOGIN_NAME, "=?", loginName).orWhere(UsrPurchase.T.EMAIL, "=?", loginName).query();
	}

}
