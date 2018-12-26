package irille.sellerAction.view;


import irille.view.BaseView;
import lombok.Data;

@Data
public class ProductSEOsView implements BaseView {
    private Integer productId;//产品ID
    private String seo_title;//标题
    private String seo_keyword;//关键词
    private String seo_description;//简述
}
