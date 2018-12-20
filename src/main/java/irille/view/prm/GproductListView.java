package irille.view.prm;

import irille.view.BaseView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class GproductListView implements BaseView {
    @Setter
    @Getter
    private List manshoes;
    @Setter
    @Getter
    private List womanshoes;
    @Setter
    @Getter
    private List childrenshoes;

}
