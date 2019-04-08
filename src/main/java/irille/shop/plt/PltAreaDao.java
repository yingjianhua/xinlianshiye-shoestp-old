package irille.shop.plt;

import java.util.List;
import java.util.stream.Collectors;

import irille.pub.bean.Query;
import irille.shop.plt.PltArea.T;
import irille.view.plt.ProvincesView;

public class PltAreaDao {

  public static List<ProvincesView> listByCity(Integer city) {
    List<PltArea> list = Query.SELECT(PltArea.class).WHERE(T.CITY, "=?", city).queryList();
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
