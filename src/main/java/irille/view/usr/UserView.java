package irille.view.usr;

import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

/**
 * 登录用户
 *
 * @author yingjianhua
 */
public class UserView {

  private String loginName;
  private UsrSupplier supplier;
  private UsrPurchase purchase;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public UsrSupplier getSupplier() {
    return supplier;
  }

  public void setSupplier(UsrSupplier supplier) {
    this.supplier = supplier;
  }

  public UsrPurchase getPurchase() {
    return purchase;
  }

  public void setPurchase(UsrPurchase purchase) {
    this.purchase = purchase;
  }

  public boolean haveUser() {
    return supplier != null || purchase != null;
  }

  public boolean isPurchase() {
    return purchase != null;
  }

  public boolean isSupplier() {
    return supplier != null;
  }
}
