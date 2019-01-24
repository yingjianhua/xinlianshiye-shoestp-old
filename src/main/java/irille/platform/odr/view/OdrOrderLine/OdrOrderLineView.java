package irille.platform.odr.view.OdrOrderLine;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OdrOrderLineView implements BaseView {
    private String product;//产品
    private Integer qty;//数量
    private BigDecimal subTotal;//小计
}
