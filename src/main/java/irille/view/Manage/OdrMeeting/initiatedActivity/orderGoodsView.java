package irille.view.Manage.OdrMeeting.initiatedActivity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class orderGoodsView implements BaseView {
    private Integer id;
    private String image;//产品图片
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String productName;//产品名称
    private String mode;//自有/合作商
    private Double originalPurchasePrice;//原采购价
    private Integer originalOrderQuantity;//原起订量
    private Integer orderPrice;//订购价
    private Double orderQuantityorderPrice;//现起订量
    private Integer targetAmount;//目标量
    private Byte status;//状态
    private Integer start;
    private Integer limit;
}
