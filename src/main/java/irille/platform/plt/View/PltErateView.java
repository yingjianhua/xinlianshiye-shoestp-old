package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PltErateView implements BaseView {
    private  Integer id;
    private  String logo;
    private  String  curname;//货币简称
    private  String  symbol;//符号
    private  Boolean enabled;
    private  Boolean  sitecur;//网站默认货币
    private BigDecimal rate;//美元汇率
    private  Boolean supcur;//商家默认货币
    private BigDecimal nowrate;//现用汇率
}
