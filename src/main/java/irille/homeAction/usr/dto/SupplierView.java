package irille.homeAction.usr.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import irille.homeAction.HomeAction;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrSupplier;

public class SupplierView {
  private String name;
  private Integer id;
  private String img;
  private Integer qty;
  private BigDecimal totalPrice;

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public SupplierView() {}

  public SupplierView(BigDecimal totalPrice, Integer qty, Integer id, String name) {
    this.totalPrice = totalPrice;
    this.qty = qty;
    this.name = name;
    this.id = id;
  }

  public static SupplierView buildByCart(UsrCart cart) {
    SupplierView view = new SupplierView();
    view.setName(cart.gtSupplier().getName());
    view.setId(cart.gtSupplier().getPkey());
    view.setQty(cart.getQty());
    view.setTotalPrice(cart.getAmtTotal());
    return view;
  }

  public static List<SupplierView> buildByCart(List<UsrCart> carts) {
    List<SupplierView> views = new ArrayList<SupplierView>();
    for (UsrCart cart : carts) {
      if (views != null && views.size() >= 0) {
        boolean flag = false;
        for (SupplierView view : views) {
          if (view.getId().equals(cart.getSupplier())) {
            view.setQty(view.getQty() + cart.getQty());
            view.setTotalPrice(view.getTotalPrice().add(cart.getAmtTotal()));
            flag = true;
            break;
          }
        }
        if (flag == false) {
          views.add(buildByCart(cart));
        }
      } else {
        views.add(buildByCart(cart));
      }
    }
    return views;
  }

  public static SupplierView build(UsrCart cart) {
    SupplierView view = new SupplierView();
    UsrSupplier supplier =
        translateUtil.getAutoTranslate(cart.gtSupplier(), HomeAction.curLanguage());
    view.setName(supplier.getShowName());
    view.setId(cart.gtSupplier().getPkey());
    return view;
  }

  public static List<SupplierView> build(List<UsrCart> carts) {
    List<SupplierView> views = new ArrayList<SupplierView>();
    for (UsrCart cart : carts) {
      if (views != null && views.size() >= 0) {
        boolean flag = false;
        for (SupplierView view : views) {
          if (view.getId().equals(cart.getSupplier())) {
            flag = true;
            break;
          }
        }
        if (flag == false) {
          views.add(build(cart));
        }
      } else {
        views.add(build(cart));
      }
    }
    return views;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
