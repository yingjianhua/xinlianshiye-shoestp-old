package irille.view.v2.Prm;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/19 Time: 11:01 */
@Data
public class PrmPdtInfo implements BaseView {
  private Integer id;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String title;

  private BigDecimal price;
  private String image;
  private Boolean favorite;
  private int min_order;
  private Integer productid;

  public void setImg(String img) {
    if (img != null && img.length() > 0) {
      String[] strings = img.split(",");
      if (strings.length > 0) {
        this.image = strings[0];
        return;
      }
    }
    this.image = "";
  }
}
