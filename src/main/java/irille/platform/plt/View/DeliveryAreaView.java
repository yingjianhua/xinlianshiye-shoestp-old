package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryAreaView implements BaseView {
    private Integer pkey;//配送区域pkey
    private String interval;//区间
    private String introduction;//简介
    private Byte freeShipping;//免运费
    private BigDecimal freeShippingPrice;//免运费价格
    private BigDecimal additionalFees;//附加费用
    private Integer firstInterval;//首重区间
    private Integer continuedInterval;//续重区间
    private BigDecimal firstPrice;//首重价格
    private BigDecimal aggravatingPrice;//加重价格
}