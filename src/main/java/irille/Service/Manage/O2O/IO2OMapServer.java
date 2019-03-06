package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.O2O.Imp.O2OMapServerImp;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.pub.tb.FldLanguage;
import irille.view.O2O.O2OMapView;
import org.json.JSONObject;

import java.util.List;
@ImplementedBy(O2OMapServerImp.class)
public interface IO2OMapServer {
    List<O2OMapView> list();

    O2OMapView ins(O2OMapView view);

    void del(Integer id);

    JSONObject load(String address);

    ProductInfoView findByEarliestPdt_PkeyAnd(FldLanguage.Language language, Integer pdt);
}
