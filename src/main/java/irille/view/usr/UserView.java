package irille.view.usr;

import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import lombok.Data;

/**
 * 登录用户
 *
 * @author yingjianhua
 */
@Data
public class UserView {

  private Integer pkey;
  private String loginName;
  private UsrSupplier supplier;
  private UsrPurchase purchase;
  //  不用每次查询数据库
  private Integer supplierId;
  private Integer purchaseId;

  private int user_type = 0;

  public boolean isSupplier() {
    return user_type == 1;
  }

  public boolean isPurchase() {
    return user_type == 0;
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
    return (pkey != null && pkey > 0) || supplier != null || purchase != null;
  }
}
