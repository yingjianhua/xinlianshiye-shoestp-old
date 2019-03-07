package com.xinlianshiye.shoestp.shop.service.rfq;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactGroupView;
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
	
	@BeforeClass
	public static void initBean() {
		RFQPurchaseContact.TB.getCode();
		RFQConsultRelation.TB.getCode();
	}
	
	@Test
	@Ignore
	public void testDeleteGroup() {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1);
		Integer groupPkey = 1;
		service.deleteGroup(purchase, groupPkey);
	}
	
	@Test
	@Ignore
	public void testAdd() {
		UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 1261);
		Integer supplierPkey = 23;
		service.add(purchase, supplierPkey);
	}
	
	@Test
	public void testPage() throws JsonProcessingException {
		om.setSerializationInclusion(Include.NON_NULL);
		
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(838);
		
		String keyword = null;
//		String keyword = "亿力";
//		String keyword = "girl";
		Integer groupPkey = null;
		Integer start = 0;
		Integer limit = 10;
		Page<RFQPurchaseContactView> page = service.page(purchase, keyword, groupPkey, start, limit);
		System.out.println(om.writeValueAsString(page));
	}
	
	@Test
	@Ignore
	public void testListGroup() throws JsonProcessingException {
		UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 838);
		List<RFQPurchaseContactGroupView> listGroup = service.listGroup(purchase);
		System.out.println(om.writeValueAsString(listGroup));
	}
}
