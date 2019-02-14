package irille.Dao.Usr.impl;

import irille.Dao.Usr.UsrPurchaseDao;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseDaoImpl implements UsrPurchaseDao {

	@Override
	public void save(UsrPurchase purchase) {
		if(purchase.getPkey() != null)
			purchase.upd();
		else
			purchase.ins();
	}

}
