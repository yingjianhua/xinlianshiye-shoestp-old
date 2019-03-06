package com.xinlianshiye.shoestp.shop.service.usr;

import org.junit.Test;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseServiceTest extends BaseTest{
	
	@Inject
	private UsrPurchaseService service;
	
	@Test(expected = WebMessageException.class)
	public void testEditAccount() {
		UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 1261);
		
		PurchaseView accountSetting = new PurchaseView();
		accountSetting.setPhone("13555548668");
		accountSetting.setSurname("Ying");
		accountSetting.setGender((byte)2);
//		accountSetting.setFirstName("Jianhua");
		accountSetting.setCompany("有限公司");
		
		service.editAccount(purchase, accountSetting);
		
	}

}
