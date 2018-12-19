package irille.view.Manage.OdrMeeting.Sale;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SizeView implements BaseView {
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String sizeName;//尺寸
    private Integer SpecQuantity;//数量
    private BigDecimal subToat;//总金额
}
