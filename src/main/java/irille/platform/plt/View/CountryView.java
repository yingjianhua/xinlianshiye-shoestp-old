package irille.platform.plt.View;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class CountryView implements BaseView {
    private int id;
//    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name;//国家
    private String shortName;//国家简称
    private String zone;//区号
    private Integer currencyId;//货币pkey
    private String currency;//货币
    private String flag;//国旗
    private boolean isenable;//启用
    private boolean ishot;//热门国家
    private boolean isdefault;//默认国家
}
