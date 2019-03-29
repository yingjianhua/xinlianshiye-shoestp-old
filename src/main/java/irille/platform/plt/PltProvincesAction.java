package irille.platform.plt;

import java.io.IOException;

import irille.homeAction.HomeAction;
import irille.shop.plt.PltProvinces;
import irille.shop.plt.PltProvincesDao;

/**
 * 中国省份
 * @author: lingjian
 * @Date: 2019/3/21 9:56
 */
public class PltProvincesAction extends HomeAction<PltProvinces> {

  public void list() throws IOException {
    write(PltProvincesDao.list());;
  }
}
