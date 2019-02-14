package irille.dao.rfq;

import org.junit.Test;

import irille.Dao.RFQ.impl.RFQConsultRelationDaoImpl;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;

public class RFQConsultRelationDaoTest {

	@Test
//	@Ignore
	public void testCountNewByPurchaseGroupBySupplier() {
		RFQConsultRelation.TB.getCode();
		RFQPurchaseContact.TB.getCode();
		Integer purchasePkey = 1261;
		Integer count = new RFQConsultRelationDaoImpl().countNewByPurchaseGroupBySupplier(purchasePkey);
		System.out.println(count);
	}
}
