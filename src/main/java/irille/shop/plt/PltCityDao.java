package irille.shop.plt;

import java.util.List;
import java.util.stream.Collectors;

import irille.pub.bean.Query;
import irille.shop.plt.PltCity.T;
import irille.view.plt.ProvincesView;

public class PltCityDao {

  public static List<ProvincesView> listByProvince(Integer province) {
    List<PltCity> list = Query.SELECT(PltCity.class).WHERE(T.PROVINCES, "=?", province).queryList();
    List<ProvincesView> views =
        list.stream()
            .map(
                pro -> {
                  ProvincesView pv = new ProvincesView();
                  pv.setId(pro.getPkey());
                  pv.setName(pro.getName());
                  return pv;
                })
            .collect(Collectors.toList());
    return views;
  }
}
