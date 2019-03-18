package irille.view.pdt;

import irille.pub.Str;
import irille.pub.formatter.CurrencyFormatter;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.PltErate;
import org.json.JSONException;

public class SpecView {

  private Integer product;
  private String productName;
  private String productCode;
  private Byte productType;
  private String keyName;
  private String images;
  private String size;
  private String color;
  private String price;

  public static SpecView build(PdtSpec bean, Language l, PltErate currency) throws JSONException {
    SpecView view = new SpecView();
    PdtProduct product = translateUtil.getAutoTranslate(bean.gtProduct(), l);
    view.setProduct(bean.getProduct());
    view.setProductName(product.getName());
    view.setProductCode(product.getCode());
    view.setProductType(product.getProductType());
    view.setKeyName(bean.getKeyName(l));
    String images = Str.isEmpty(bean.getPics()) ? product.getPicture() : bean.getPics();
    view.setImages(images.split(",")[0]);
    view.setSize(bean.gtSize().getName(l));
    view.setColor(bean.gtColor().getName(l));
    view.setPrice(CurrencyFormatter.format(bean.getPrice(), currency, true, false));
    return view;
  }

  public Integer getProduct() {
    return product;
  }

  public void setProduct(Integer product) {
    this.product = product;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getKeyName() {
    return keyName;
  }

  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Byte getProductType() {
    return productType;
  }

  public void setProductType(Byte productType) {
    this.productType = productType;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
