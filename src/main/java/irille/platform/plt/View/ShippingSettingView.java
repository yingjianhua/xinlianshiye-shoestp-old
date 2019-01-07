package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class ShippingSettingView implements BaseView {
    private Integer pkey;//运费管理pkey
    private String courierCompany;//快递公司
    private String logo;//logo
    private Byte enable;//启用标志
    private Byte weightSelectionMethod;//重量选择方式
}
