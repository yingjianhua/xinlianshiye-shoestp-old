package com.xinlianshiye.shoestp.shop.service.rfq;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessagesView;

import irille.shop.usr.UsrPurchase;

public class RFQConsultMessageServiceTest extends BaseTest {

	@Inject
	private RFQConsultMessageService service;
	@Inject
	private ObjectMapper om;
	
	@Test
	public void testPage() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		Integer relationPkey = 1;
		Integer start = 0;
		Integer limit = 10;
		
		RFQConsultMessagesView page = service.page(purchase, relationPkey, start, limit);
		System.out.println(om.writeValueAsString(page));
	}
	
	@Test
	public void testSend() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		
		Integer relationPkey = 1;
		String content = "nihao ";
		
		RFQConsultMessageView message = service.send(purchase, relationPkey, content);
		System.out.println(om.writeValueAsString(message));
	}
}
