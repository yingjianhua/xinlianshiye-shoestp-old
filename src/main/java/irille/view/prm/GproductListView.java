package irille.view.prm;

import irille.pub.html.Nodes;
import irille.view.BaseView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class GproductListView implements BaseView {
    @Setter
    @Getter
    private List<shoesView> manshoes;
    @Setter
    @Getter
    private List<shoesView> womanshoes;
    @Setter
    @Getter
    private List<shoesView> childrenshoes;

}
