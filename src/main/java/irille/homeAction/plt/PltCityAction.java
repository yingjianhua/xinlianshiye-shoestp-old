package irille.homeAction.plt;

import java.io.IOException;

import irille.homeAction.HomeAction;
import irille.shop.plt.PltCity;
import irille.shop.plt.PltCityDao;

/**
 * 中国城市
 *
 * @author chen
 */
public class PltCityAction extends HomeAction<PltCity> {

  private Integer province;

  public Integer getProvince() {
    return province;
  }

  public void setProvince(Integer province) {
    this.province = province;
  }

  public void findByProvince() throws IOException {
    write(PltCityDao.listByProvince(province));
  }
}
