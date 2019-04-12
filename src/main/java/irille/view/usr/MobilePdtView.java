package irille.view.usr;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/**
 * 手机端商家产品列表信息
 *
 * @author GS
 */
@Data
public class MobilePdtView implements BaseView {

  private Integer pkey;
  
  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name; // 名称

  private String picture; // 图片

  private BigDecimal minPrice; // 阶梯价
  

  private BigDecimal maxPrice; // 阶梯价

  private String artNo; // 货号
  
  private Integer  isO2O;
}
