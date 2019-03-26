package irille.platform.odr.view.OdrOrderLine;

import java.math.BigDecimal;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OdrOrderLineView implements BaseView {
  private String product; // 产品
  private Integer qty; // 数量
  private BigDecimal subTotal; // 小计
}
