package irille.homeAction.plt;

import java.io.IOException;

import irille.homeAction.HomeAction;
import irille.shop.plt.PltArea;
import irille.shop.plt.PltAreaDao;

/**
 * 中国地区
 *
 * @author chen
 */
public class PltAreaAction extends HomeAction<PltArea> {

  private Integer city;

  public Integer getCity() {
    return city;
  }

  public void setCity(Integer city) {
    this.city = city;
  }

  public void findByCity() throws IOException {
    write(PltAreaDao.listByCity(city));
  }
}
