package irille.shop.usr;

import irille.pub.DateTools;
import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Query;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrSupplier.T;
import irille.view.usr.UserView;

public class UsrUserDAO {
  private static final Log LOG = new Log(UsrUserDAO.class);

  public static UserView purchaseSignIn(String loginName, String password) {
    UserView view = new UserView();
    UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, loginName);
    if (purchase != null
        && purchase.getPassword().equals(DateTools.getDigest(purchase.getPkey() + password))) {
      UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, loginName);
      view.setPurchase(purchase);
      view.setSupplier(supplier);
    }
    return view;
  }

  /**
   * 用户登录验证
   *
   * @param code 登录用户名
   * @param password 登录密码
   * @param type 用户类型
   * @return
   */
  public static UserView supplierSignIn(String loginName, String password) {
    if (Str.isEmpty(loginName)) throw LOG.err("loginCheck", "请输入用户名");
    if (password == null || Str.isEmpty(password)) throw LOG.err("loginCheck", "请输入密码");
    UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, loginName);
    UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, loginName);
    if (supplier == null) {
      throw LOG.err("loginName not exists", "用户不存在");
    }
    if (supplier.gtStatus() == OStatus.INIT) {
      throw LOG.err("wait for appr", "审核中不能登录");
    }
    if (purchase == null) {
      // 由于原来系统有很多供应商账号是没有对应的采购商账号的,在只有供应商账号的情况下,根据供应商的账号密码登录
      // throw LOG.err("loginName not exists", "供应商账户没有采购商账号");
      if (!DateTools.getDigest(supplier.getPkey() + password).equals(supplier.getPassword())) {
        throw LOG.err("wrong password", "用户名和密码不匹配");
      }
    } else {
      if (!DateTools.getDigest(purchase.getPkey() + password).equals(purchase.getPassword())) {
        throw LOG.err("wrong password", "用户名和密码不匹配");
      }
    }
    UserView view = new UserView();
    view.setSupplier(supplier);
    view.setPurchase(purchase);

    return view;
  }

  /**
   * 修改供应商登录密码
   *
   * <p>若该供应商同时也是采购商,则同时修改供应商和采购商的密码
   *
   * @param id 供应商主键
   * @param origin 原密码
   * @param target 新密码
   * @author Jianhua Ying
   */
  public static void updSupplierPassword(Integer id, String origin, String target) {
    UsrSupplier supplier = Query.SELECT(UsrSupplier.class, id);
    UsrPurchase purchase =
        Query.SELECT(UsrPurchase.class)
            .LEFT_JOIN(UsrSupplier.class, UsrPurchase.T.LOGIN_NAME, T.LOGIN_NAME)
            .WHERE(T.PKEY, "=?", id)
            .query();
    if (purchase == null) {
      if (!supplier.getPassword().equals(DateTools.getDigest(supplier.getPkey() + origin)))
        throw LOG.err("pwdCheck", "原密码输入错误");
      supplier.setPassword(DateTools.getDigest(supplier.getPkey() + target));
      supplier.upd();
    } else {
      if (!purchase.getPassword().equals(DateTools.getDigest(purchase.getPkey() + origin)))
        throw LOG.err("pwdCheck", "原密码输入错误");
      supplier.setPassword(DateTools.getDigest(supplier.getPkey() + target));
      supplier.upd();
      purchase.setPassword(DateTools.getDigest(purchase.getPkey() + target));
      purchase.upd();
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
