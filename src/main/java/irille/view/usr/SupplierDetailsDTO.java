package irille.view.usr;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SupplierDetailsDTO implements BaseView {
    private Integer pkey;//供应商pkey
    private String logo;//logo
    private String name;//店铺名称
    private String userName;//用户名
    private String targetedMarket;//目标市场
}
