package irille.view.pdt;

import java.math.BigDecimal;
import java.math.RoundingMode;

import irille.homeAction.HomeAction;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.shop.plt.PltErateDAO;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/8/14 Time: 11:09 */
public class PdtYouMayLikeView {
  @SetBean(OriginalField = "pkey")
  private int id;

  private String name;

  @SetBean(OriginalField = "cur_price")
  private BigDecimal price;

  @SetBean(OriginalField = "picture")
  private String image;

  private Integer favorite_count;

  private String rewrite;

  public String getRewrite() {
    return rewrite;
  }

  public void setRewrite(String rewrite) {
    this.rewrite = rewrite;
  }

  public String getImage() {
    return image;
  }

  public PdtYouMayLikeView setImage(String image) {
    if (image != null) {
      String[] strings = image.split(",");
      this.image = strings.length > 0 ? strings[0] : image;
    }
    return this;
  }

  public int getId() {
    return id;
  }

  public PdtYouMayLikeView setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public PdtYouMayLikeView setName(String name) {
    this.name = name;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public PdtYouMayLikeView setPrice(BigDecimal price) {
    this.price =
        PltErateDAO.Query.getTargetPrice(price, HomeAction.curCurrency().getNowrate())
            .setScale(2, RoundingMode.HALF_UP);
    ;
    return this;
  }

  public Integer getFavorite_count() {
    return favorite_count;
  }

  public void setFavorite_count(Integer favorite_count) {
    this.favorite_count = favorite_count;
  }

  @Override
  public String toString() {
    return "PdtYouMayLikeView{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", price='"
        + price
        + '\''
        + ", images='"
        + image
        + '\''
        + "} "
        + super.toString();
  }
}
