package irille.view.prm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.bean.BeanLong;
import irille.pub.i18n.I18NFieldSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class shoesView {
    @Setter
    @Getter
    private  Integer id;
    @Setter
    @Getter
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String name;
    @Setter
    @Getter
    private String img;
    @Setter
    @Getter
    private Boolean islove;
}
