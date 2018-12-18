package irille.view.v2.Pdt;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 首页新品
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/17
 * Time: 16:11
 */
@Data
public class NewPdtInfo implements BaseView {
    private Long id;
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String title;
    private String image;
    private BigDecimal price;
    private int min_order;
    private boolean isFavorite;
    private String rewrite;
}
