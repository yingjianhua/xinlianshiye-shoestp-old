package irille.platform.pdt.view1;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PdtProductView implements BaseView {
    private Integer pkey;
    private String picture; //产品图片
    private String name; //名称
    private String category; //产品类目
    private String code; //编号
    private String sku; //SKU
    private String supplier; //供应商


}
