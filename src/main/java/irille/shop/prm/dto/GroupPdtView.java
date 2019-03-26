package irille.shop.prm.dto;

import java.math.BigDecimal;

public class GroupPdtView {
  private Integer groupLinePkey;
  private Integer product;
  private BigDecimal amt;
  private Integer qty;
  private Integer minOq;

  public Integer getMinOq() {
    return minOq;
  }

  public void setMinOq(Integer minOq) {
    this.minOq = minOq;
  }

  public Integer getGroupLinePkey() {
    return groupLinePkey;
  }

  public void setGroupLinePkey(Integer groupLinePkey) {
    this.groupLinePkey = groupLinePkey;
  }

  public Integer getProduct() {
    return product;
  }

  public void setProduct(Integer product) {
    this.product = product;
  }

  public BigDecimal getAmt() {
    return amt;
  }

  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }
}
