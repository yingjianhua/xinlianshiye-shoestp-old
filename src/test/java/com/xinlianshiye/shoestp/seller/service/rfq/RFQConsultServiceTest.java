package com.xinlianshiye.shoestp.seller.service.rfq;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class RFQConsultServiceTest extends BaseTest {

	@Inject
	private RFQConsultService service;
	
	@Before
	public void initBean() {
		RFQConsultMessage.TB.getCode();
        RFQConsultRelation.TB.getCode();
        RFQConsult.TB.getCode();
        UsrPurchase.TB.getCode();
        RFQConsultGroupRelation.TB.getCode();
        PltCountry.TB.getCode();
	}
	
	@Test
	public void testCount() {
		UsrSupplier supplier = new UsrSupplier();
		supplier.setPkey(1);
		
		Boolean isDeleted = null;
		System.out.println(service.count(supplier, isDeleted));;
	}
	
	@Test
    public void testPage() {
        long l1 = System.currentTimeMillis();
        UsrSupplier supplier = new UsrSupplier();
		supplier.setPkey(1);
		
        Page<RFQConsultRelationView> page = service.page(supplier, 0, 10, "", 1, true, (byte) 1, (byte) 2, false, new Date(), new Date(), (byte) 1);
        System.out.println(page.getTotalCount());
        System.out.println(page.getItems());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
	
}
