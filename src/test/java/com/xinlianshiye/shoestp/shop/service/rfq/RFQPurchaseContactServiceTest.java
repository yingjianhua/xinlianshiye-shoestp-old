package com.xinlianshiye.shoestp.shop.service.rfq;

import org.junit.Test;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;

import irille.shop.usr.UsrPurchase;

public class RFQPurchaseContactServiceTest extends BaseTest {

	@Inject
	private RFQPurchaseContactService service;
	
	@Test
	public void testDeleteGroup() {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1);
		Integer groupPkey = 1;
		service.deleteGroup(purchase, groupPkey);
	}
}
