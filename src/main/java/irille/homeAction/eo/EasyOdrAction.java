package irille.homeAction.eo;

import com.google.inject.Inject;
import irille.shop.easy.EasyOdr;
import irille.Service.Eo.EasyOdrService;
import irille.homeAction.HomeAction;
import irille.view.EO.easyodrView;
import irille.view.EO.eolineView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EasyOdrAction  extends HomeAction<EasyOdr> {
    @Inject
    private EasyOdrService easyOdrService;
    private  Integer purchaseLine;
    private List<easyodrView> list;
    private List<eolineView> list2;
    public  void   generateOrder() throws Exception {
        easyOdrService.generateOrder(purchaseLine,getPurchase().getPkey(),list);
        write();
    }


}
