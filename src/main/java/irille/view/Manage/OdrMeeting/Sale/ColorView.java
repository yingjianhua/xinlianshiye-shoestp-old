package irille.view.Manage.OdrMeeting.Sale;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class ColorView implements BaseView {
    private Integer productId;
    private String image;//颜色图片
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String color;//颜色名称
    private List<SizeView> size;
}
