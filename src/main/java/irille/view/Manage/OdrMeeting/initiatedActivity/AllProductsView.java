package irille.view.Manage.OdrMeeting.initiatedActivity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllProductsView implements BaseView {
    private Integer id;//产品pkey
    private String Image;//产品图片
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String name;//产品名称
    private String code;//产品编号
    private BigDecimal oldPrice;//原采购价
    private Integer oldMoq;//原起订量
    private BigDecimal newPrice;//订购会价
    private Integer moq;//现起订量
    private Integer aims;//目标量
}
