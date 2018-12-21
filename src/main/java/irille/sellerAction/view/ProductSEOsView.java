package irille.sellerAction.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import lombok.Data;

@Data
public class ProductSEOsView {
    private Integer productId;//产品ID
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String productName;//产品名称
    private String seo_title;//标题
    private String seo_keyword;//关键词
    private String seo_description;//简述
}
