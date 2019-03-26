package irille.Dao;

import java.util.List;

import irille.pub.bean.Query;
import irille.shop.plt.PltCountry;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/3 Time: 9:57 */
public class PltCountryDao {
  public List<PltCountry> getCountryList() {
    return Query.SELECT(PltCountry.class).queryList();
  }
}
