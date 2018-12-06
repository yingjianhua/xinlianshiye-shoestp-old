package irille.sellerAction.eo;

import com.google.inject.Inject;
import irille.Dao.EO.EasyOdrDao;
import irille.Entity.EO.EasyOdr;
import irille.Service.Eo.EasyOdrService;
import irille.homeAction.HomeAction;
import irille.sellerAction.SellerAction;
import irille.view.EO.easyodrView;
import irille.view.EO.eolineView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class EasyOdrAction extends SellerAction<EasyOdr> {

    private OrderSearchView search;
    @Inject
    private EasyOdrDao easyOdrDao;
    public  void   newOrderlist() throws IOException {
        Page<easyodrView> page=easyOdrDao.sellerlist(getStart(),getLimit(),search,getSupplier().getPkey());
        write(page);
    }


}
