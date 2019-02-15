package irille.service.usr;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

import irille.Service.Usr.UsrFavoriteService;
import irille.homeAction.usr.dto.FavoritesView;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;

public class UsrFavoriteServiceTest {


	@Test
//	@Ignore
	public void test() throws JsonProcessingException {
		PdtCat.TB.getCode();
        UsrFavorites.TB.getCode();
		PdtProduct.TB.getCode();
		Injector in = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder arg0) {
			}
		});
		// 得到HelloCaller的实例
		UsrFavoriteServiceTest caller = in.getInstance(UsrFavoriteServiceTest.class);
		caller.om.setSerializationInclusion(Include.NON_NULL);
		caller.testPage();
	}

	@Inject
	private UsrFavoriteService usrFavoriteService;
	@Inject
	private ObjectMapper om;

	public void testPage() throws JsonProcessingException {
		UsrPurchase purchase = new UsrPurchase();
		purchase.setPkey(1261);
		purchase.setIcon("hjajdlj");
		purchase.setName("nihao");
		Integer categoryPkey = 380;
//		Integer categoryPkey = 387;
		Integer start = 0;
		Integer limit = 10;
		List<FavoritesView> page = usrFavoriteService.page(purchase, categoryPkey, true, start, limit);
		System.out.println(om.writeValueAsString(page));
	}

}
