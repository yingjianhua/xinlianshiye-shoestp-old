package com.xinlianshiye.shoestp.common.dao.usr;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;

import irille.shop.usr.UsrPurchase;

public class UsrPurchaseDaoTest extends BaseTest {
	
	@Inject
	private UsrPurchaseDao dao;
	
	@Before
	public void initBean() {
        UsrPurchase.TB.getCode();
	}
	
	@Test
	public void testFindByLoginNameOrEmailAndPassword() {
		String loginName = "admin@163.com";
		
		System.out.println(dao.findByLoginNameOrEmail(loginName));
	}
}
