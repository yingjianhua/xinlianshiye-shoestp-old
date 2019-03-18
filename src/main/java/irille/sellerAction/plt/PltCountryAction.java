package irille.sellerAction.plt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import irille.Service.Plt.PltService;
import irille.action.ActionBase;
import irille.sellerAction.SellerAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltCountryDAO;
import irille.shop.plt.PltProvince;
import irille.view.plt.CountryView;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class PltCountryAction extends ActionBase<PltCountry> {
  @Inject private PltService pltService;
  private List<PltProvince> _listLine = new ArrayList<PltProvince>();

  public List<PltProvince> getListLine() {
    return _listLine;
  }

  public void setListLine(List<PltProvince> listLine) {
    this._listLine = listLine;
  }

  @Override
  public Class beanClazz() {
    return PltCountry.class;
  }

  public PltCountry getBean() {
    return _bean;
  }

  public void setBean(PltCountry bean) {
    this._bean = bean;
  }

  @Override
  public PltCountry insRun() throws Exception {
    PltCountryDAO.Ins insDao = new PltCountryDAO.Ins();
    insDao.setB(getBean());
    insDao.setLines(getListLine());
    insDao.commit();
    return insDao.getB();
  }

  @Override
  public PltCountry updRun() throws Exception {
    PltCountryDAO.Upd upd = new PltCountryDAO.Upd();
    upd.setB(getBean());
    upd.setLines(getListLine());
    upd.commit();
    return upd.getB();
  }

  public void del() throws Exception {
    PltCountryDAO.Del del = new PltCountryDAO.Del();
    del.setB(getBean());
    del.commit();
    writeSuccess(getBean());
  }

  public void list() throws Exception {
    List<CountryView> views =
        pltService.getCountryList(PltConfigDAO.supplierLanguage(SellerAction.getSupplier()), null);
    JSONObject json = new JSONObject().put(STORE_ROOT, new JSONArray(views, false));
    HttpServletResponse response = ServletActionContext.getResponse();
    response.getWriter().print(json.toString());
  }
}
