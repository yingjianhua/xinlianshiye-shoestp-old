package com.xinlianshiye.shoestp.shop.service.rfq;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactView;

import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

public class RFQPurchaseContactServiceTest extends BaseTest {

	@Inject
	private RFQPurchaseContactService service;
	@Inject
	private ObjectMapper om;
	
	@Test
	public void testDeleteGroup() {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1);
		Integer groupPkey = 1;
		service.deleteGroup(purchase, groupPkey);
	}
	
	@Test
	public void testPage() throws JsonProcessingException {
		RFQPurchaseContact.TB.getCode();
		RFQConsultRelation.TB.getCode();
		om.setSerializationInclusion(Include.NON_NULL);
		
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		String keyword = "亿力";
//		String keyword = "girl";
		Integer groupPkey = null;
		Integer start = 0;
		Integer limit = 10;
		Page<RFQPurchaseContactView> page = service.page(purchase, keyword, groupPkey, start, limit);
		System.out.println(om.writeValueAsString(page));
	}
}
