package irille.view.v2.Pdt;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/** 首页新品 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/17 Time: 16:11 */
@Data
public class PdtNewPdtInfo implements BaseView {
  private Long id;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String title;

  private String image;
  private BigDecimal price;
  private int min_order;
  private boolean isFavorite;
  private String rewrite;
  private int product_type;
}
