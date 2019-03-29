package irille.view.pdt;

import java.util.Map;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/8/24 Time: 13:43 */
public class PdtProductSpecSaveView {
  private int id;
  private String name;
  private String sku;
  private Map<String, String> pic;
  private Double price;
  private Integer stock;
  private Integer weight;
  private int color;
  private int size;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public Map<String, String> getPic() {
    return pic;
  }

  public void setPic(Map<String, String> pic) {
    this.pic = pic;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "PdtProductSpecSaveView{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", sku='"
        + sku
        + '\''
        + ", pic="
        + pic
        + ", price="
        + price
        + ", stock="
        + stock
        + ", weight="
        + weight
        + ", color="
        + color
        + ", size="
        + size
        + '}';
  }
}
