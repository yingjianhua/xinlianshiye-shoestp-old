package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.O2O.Imp.O2OMapServerImp;
import irille.view.O2O.O2OMapView;
import org.json.JSONObject;

import java.util.List;
@ImplementedBy(O2OMapServerImp.class)
public interface IO2OMapServer {
    List<O2OMapView> list();

    void ins(O2OMapView view);

    void del(Integer id);

    JSONObject load(String address);
}
