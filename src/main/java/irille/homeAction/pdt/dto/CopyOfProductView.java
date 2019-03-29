package irille.homeAction.pdt.dto;

import java.math.BigDecimal;
import java.util.List;

import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;

public class CopyOfProductView {
  private Integer shop;
  private String shopName;
  private String name;
  private String code;
  private String img;
  private BigDecimal price;
  private List<SpecView> specs;

  public static CopyOfProductView build(PdtProduct product, List<PdtSpec> specs) {
    CopyOfProductView view = new CopyOfProductView();
    view.setShop(product.getSupplier());
    view.setShopName(product.gtSupplier().getName());
    view.setName(product.getName());
    view.setCode(product.getCode());
    String[] pics = product.getPicture().split(",");
    view.setImg(pics.length > 0 ? pics[0] : "");
    view.setPrice(product.getCurPrice());
    // view.setSpecs(SpecView.build(specs, view.getImg()));
    return view;
  }

  public Integer getShop() {
    return shop;
  }

  public void setShop(Integer shop) {
    this.shop = shop;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<SpecView> getSpecs() {
    return specs;
  }

  public void setSpecs(List<SpecView> specs) {
    this.specs = specs;
  }
}
