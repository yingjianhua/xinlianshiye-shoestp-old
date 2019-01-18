package irille.platform.prm.View.PrmGroupPurchaseLineView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PrmGroupPurchaseLineView implements BaseView {
    private Integer id;//联合采购商品主键
    private String product;//产品名称
    private Long count;//数量
    private Long boughtCount;//已订购数量
    private Integer status;//明细状态
}
