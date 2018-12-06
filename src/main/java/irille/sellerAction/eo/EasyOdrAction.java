package irille.sellerAction.eo;

import com.google.inject.Inject;
import irille.Dao.EO.EasyOdrDao;
import irille.shop.easy.EasyOdr;
import irille.sellerAction.SellerAction;
import irille.view.EO.easyodrView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

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
