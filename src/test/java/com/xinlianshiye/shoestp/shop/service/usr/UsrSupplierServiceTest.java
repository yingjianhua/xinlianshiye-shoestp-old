package com.xinlianshiye.shoestp.shop.service.usr;

import org.json.JSONException;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.usr.SupplierView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;

public class UsrSupplierServiceTest extends BaseTest {
	
	@Inject
	private UsrSupplierService service;
	@Inject
	private ObjectMapper om;

	@Test
	public void testDetail() throws JsonProcessingException, JSONException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		Integer supplierPkey = 10;
		
		SupplierView supplier = service.detail(purchase, supplierPkey, Language.zh_CN);
		System.out.println(om.writeValueAsString(supplier));
	}
}
