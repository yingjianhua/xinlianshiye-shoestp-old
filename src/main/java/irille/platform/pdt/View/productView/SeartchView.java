package irille.platform.pdt.View.productView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SeartchView implements BaseView {
    private Integer productType;//商品类型
    private String shopName;//店铺名称
    private String productClassification;//产品分类
    private Integer productStatus;//产品状态
    private Integer verify;//审核状态
}
