package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class CurrencysView implements BaseView {
    private Integer id;
    private String symbol;
    private Byte siteCur;
}
