package irille.shop.usr;

import irille.pub.DateTools;
import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.view.usr.UserView;

public class UsrUserDAO {
	private static final Log LOG = new Log(UsrUserDAO.class);

	public static UserView purchaseSignIn(String loginName, String password) {
		UserView view = new UserView();
		UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, loginName);
		if (purchase != null && purchase.getPassword().equals(DateTools.getDigest(purchase.getPkey() + password))) {
			UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, loginName);
			view.setPurchase(purchase);
			view.setSupplier(supplier);
		}
		return view;
	}

	/**
	 * 用户登录验证
	 *
	 * @param code     登录用户名
	 * @param password 登录密码
	 * @param type     用户类型
	 * @return
	 */
	public static UserView supplierSignIn(UsrSupplier supplier, UsrMain main) {

		UserView view = new UserView();
		view.setSupplier(supplier);
		view.setPkey(main.getPkey());
		view.setLoginName(supplier.getLoginName());
		view.setUser_type(1);
		return view;
	}

	/**
	 * 修改供应商登录密码
	 *
	 * <p>
	 * 若该供应商同时也是采购商,则同时修改供应商和采购商的密码
	 *
	 * @param id     供应商主键
	 * @param origin 原密码
	 * @param target 新密码
	 * @author Jianhua Ying
	 */
	public static void updSupplierPassword(Integer id, String origin, String target) {
		UsrMain main = new UsrMain();
		UsrSupplier supplier = Query.SELECT(UsrSupplier.class, id);
		if (supplier != null)
			main = Query.SELECT(UsrMain.class, supplier.getUserid());
		else
			throw new WebMessageException(ReturnCode.failure, "用户信息有误");
		if (null != main.getPkey()) {
			if (!main.getPassword().equals(DateTools.getDigest(main.getPkey() + origin))) {
				throw new WebMessageException(ReturnCode.failure, "原密码错误");
			} else {
				main.setPassword(DateTools.getDigest(main.getPkey() + target));
				main.upd();
			}
		} else {
			throw new WebMessageException(ReturnCode.failure, "用户信息有误");
		}
	}

	public static UserView findByLoginName(String loginName) {
		UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, loginName);
		UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, loginName);
		UserView view = new UserView();
		view.setSupplier(supplier);
		view.setPurchase(purchase);
		return view;
	}

	public static UsrPurchase findPurchaseByLoginName(String loginName) {
		return UsrPurchase.chkUniqueLogin_name(false, loginName);
	}

	public static UsrSupplier findSupplierByLoginName(String loginName) {
		return UsrSupplier.chkUniqueLogin_name(false, loginName);
	}
}
