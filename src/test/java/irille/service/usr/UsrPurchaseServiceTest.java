package irille.service.usr;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.Service.Usr.UsrPurchaseService;
import irille.homeAction.usr.dto.PurchaseView;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseServiceTest {

	@Test
	@Ignore
	public void test() throws JsonProcessingException {
		Injector in = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder arg0) {
			}
		});
		// 得到HelloCaller的实例
		UsrPurchaseServiceTest caller = in.getInstance(UsrPurchaseServiceTest.class);
		caller.testGetProfile();
	}

	@Inject
	private UsrPurchaseService usrPurchaseService;
	@Inject
	private ObjectMapper om;

	public void testEditAvatar() {

	}

	public void testGetProfile() throws JsonProcessingException {
		RFQConsultRelation.TB.getCode();
		RFQConsultMessage.TB.getCode();
		RFQPurchaseContact.TB.getCode();

		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setIcon("hjajdlj");
		purchase.setName("nihao");
		PurchaseView view = usrPurchaseService.getProfile(purchase);
		System.out.println(om.writeValueAsString(view));
	}
}
