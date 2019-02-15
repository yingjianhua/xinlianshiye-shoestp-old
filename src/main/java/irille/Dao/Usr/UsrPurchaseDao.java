package irille.Dao.Usr;

import com.google.inject.ImplementedBy;

import irille.Dao.Usr.impl.UsrPurchaseDaoImpl;
import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrPurchaseDaoImpl.class)
public interface UsrPurchaseDao {

	void save(UsrPurchase purchase);
}
