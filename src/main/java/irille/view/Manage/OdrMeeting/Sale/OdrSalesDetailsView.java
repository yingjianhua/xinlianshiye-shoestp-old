package irille.view.Manage.OdrMeeting.Sale;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class OdrSalesDetailsView implements BaseView {
    private Integer pkey;//产品id用于查看订单
    private String image;//图片
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name;//产品名称
    private BigDecimal orderPrice;//订购会价
    private Byte status;//状态(上架-下架)
    private Byte activeStatus;//活动状态(是否结束)
    private Byte billingStatus;//自有状态
    private Map<String, ColorView> specification;
}
