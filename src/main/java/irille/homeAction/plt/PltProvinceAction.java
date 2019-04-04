package irille.homeAction.plt;

import java.io.IOException;
import java.util.List;

import irille.homeAction.HomeAction;
import irille.homeAction.plt.inf.IPltProvinceAction;
import irille.pub.idu.Idu;
import irille.shop.plt.PltProvince;
import irille.shop.plt.PltProvinceDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class PltProvinceAction extends HomeAction<PltProvince> implements IPltProvinceAction {

  private static final long serialVersionUID = 8953977856475932531L;

  private Integer countryPkey;

  public void getAllProByCtr() throws Exception {
    List<PltProvince> list = Idu.getLines(PltProvince.T.MAIN, countryPkey);
    JSONObject json = new JSONObject();
    json.put("provinces", new JSONArray(list, false));
    System.out.println(json);
    writerOrExport(json);
  }

  private Integer countryId;

  /**
   * 获取省份列表,根据国家id
   *
   * @throws IOException
   * @author yingjianhua
   */
  @Override
  // @NeedLogin
  public void list() throws IOException {
    write(PltProvinceDAO.listByCountry(countryId));
    ;
  }

  public Integer getCountryPkey() {
    return countryPkey;
  }

  public void setCountryPkey(Integer countryPkey) {
    this.countryPkey = countryPkey;
  }

  public Integer getCountryId() {
    return countryId;
  }

  public void setCountryId(Integer countryId) {
    this.countryId = countryId;
  }
}
