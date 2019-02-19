package com.xinlianshiye.shoestp.shop.service.rfq;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class RFQConsultServiceTest extends BaseTest {
	
	@Inject
	private RFQConsultService service;
	@Inject
	private ObjectMapper om;
	
	@Test
	public void testPageMine() throws JsonProcessingException {
		RFQConsultRelation.TB.getCode();
		RFQConsult.TB.getCode();
		UsrSupplier.TB.getCode();
		
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		Byte type = 1;
		String keyword = "";
		Boolean unread = null;
		Integer start = 0;
		Integer limit = 10;
		Page<RFQConsultView> page = service.pageMine(purchase, type, keyword, unread, start, limit);
		System.out.println(om.writeValueAsString(page));
	}
	
	@Test
	public void testGetQuotation() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		
		Integer relationPkey = 1;
		RFQQuotationView quotation = service.getQuotation(purchase, relationPkey);
		System.out.println(om.writeValueAsString(quotation));
	}
}
