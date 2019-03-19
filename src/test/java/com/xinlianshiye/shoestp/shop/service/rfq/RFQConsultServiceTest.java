package com.xinlianshiye.shoestp.shop.service.rfq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQUnreadCountView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.homeAction.HomeAction;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class RFQConsultServiceTest extends BaseTest {

	@Inject
	private RFQConsultService service;
	@Inject
	private ObjectMapper om;

	@BeforeClass
	public static void initBean() {
		RFQConsultRelation.TB.getCode();
		RFQConsult.TB.getCode();
		UsrSupplier.TB.getCode();
	}

	@Test
	@Ignore
	public void testPageMine() throws JsonProcessingException {
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
	@Ignore
	public void testGetQuotation() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");

		Integer relationPkey = 1;
		RFQQuotationView quotation = service.getQuotation(purchase, relationPkey);
		System.out.println(om.writeValueAsString(quotation));
	}

	@Test
	@Ignore
	public void testGetDetail() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");

		Integer consultPkey = 7;
		RFQConsultView consult = service.getDetail(purchase, consultPkey,HomeAction.curLanguage());
		System.out.println(om.writeValueAsString(consult));
	}

	@Test
	@Ignore
	public void testAddProductRequest() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setName("建化");
		Integer consultPkey = 5;
		RFQConsultView detail2 = service.getDetail(purchase, consultPkey, HomeAction.curLanguage());
		System.out.println(om.writeValueAsString(detail2));
		String products = "339,340";
		service.addProductRequest(purchase, consultPkey, products,HomeAction.curLanguage());
		RFQConsultView detail = service.getDetail(purchase, consultPkey,HomeAction.curLanguage());
		System.out.println(om.writeValueAsString(detail));
	}

	@Test
//	@Ignore
	public void testCountUnread() throws JsonProcessingException {
		UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 3787);

		RFQUnreadCountView unreadCount = service.countUnread(purchase);
		System.out.println(om.writeValueAsString(unreadCount));
	}

	@Test
	@Ignore
	public void testDeleteQuotation() throws JsonProcessingException {
		UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 1261);
		Integer relationPkey = 3;
		service.deleteQuotation(purchase, relationPkey);
		Page<RFQConsultView> view = service.pageMine(purchase, null, null, null, 0, 10);
		System.out.println(om.writeValueAsString(view));
	}
}
