package irille.view.prm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import lombok.Data;

@Data
public class shoesView {
  private Integer id;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name;

  private String img;
  private Boolean isFavorite;
  private Integer productid;

  public void setImg(String img) {
    if (img != null && img.length() > 0) {
      String[] strings = img.split(",");
      if (strings.length > 0) {
        this.img = strings[0];
        return;
      }
    }
    this.img = "";
  }
}
