package irille.dao.rfq;

import org.junit.Ignore;
import org.junit.Test;

import irille.Dao.RFQ.impl.RFQConsultMessageDaoImpl;

public class RFQConsultMessageDaoTest {

	@Test
	@Ignore
	public void testCountUnreadByRelation_PurchaseGroupByRelation() {
		Integer purchasePkey = 1261;
		Integer count = new RFQConsultMessageDaoImpl().countUnreadByRelation_PurchaseGroupByRelation(purchasePkey);
		System.out.println(count);
	}
}
