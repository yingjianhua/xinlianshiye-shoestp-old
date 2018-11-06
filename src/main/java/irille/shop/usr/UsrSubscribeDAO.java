package irille.shop.usr;

import irille.pub.Log;
import irille.pub.idu.IduIns;
import irille.shop.usr.UsrPurchaseLineDAO.Ins;

public class UsrSubscribeDAO {
	public static final Log LOG = new Log(UsrSubscribe.class);
	public static class Ins extends IduIns<Ins, UsrPurchaseLine> {
		public void before() {
			super.before();
		}
	}
}
