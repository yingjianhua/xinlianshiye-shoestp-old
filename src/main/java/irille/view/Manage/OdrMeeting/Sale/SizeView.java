package irille.view.Manage.OdrMeeting.Sale;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SizeView implements BaseView {
    private String size;//尺寸名称
    private BigDecimal normOrderPrice;//订购会价
    private Integer normQuantity;//数量
    private Integer purchaseNumber;//采购人数
    private BigDecimal normTotalAmount;//总金额
}
