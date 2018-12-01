package irille.view.plt;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class CountryView implements BaseView {

    private int id;
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name;
    private String shortName;
    private String flag;
    private boolean isDefault;

}
