package irille.action.plt;

import java.util.ArrayList;
import java.util.List;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.pub.inf.IExtName;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltCountryDAO;
import irille.shop.plt.PltCountryDAO.Hot;
import irille.shop.plt.PltProvince;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class PltCountryAction extends MgtAction<PltCountry> {
  private List<PltProvince> _listLine = new ArrayList<PltProvince>();

  public List<PltProvince> getListLine() {
    return _listLine;
  }

  public void setListLine(List<PltProvince> listLine) {
    this._listLine = listLine;
  }

  @Override
  public void list() throws Exception {
    // TODO Auto-generated method stub
    super.list();
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

  /**
   * 热门国家是否启用
   *
   * @throws Exception
   */
  public void hot() throws Exception {
    Hot act = new Hot();
    act.setBKey(getPkeys());
    act.commit();
    writeSuccess(act.getB());
  }

  /** 对应EXT上的外键下拉框组件 在store加载调用 */
  public void getComboTrigger() throws Exception {
    String where = crtQueryAll();
    if (tb().chk("enabled")) {
      where += " AND enabled = 1";
    }
    if (!Str.isEmpty(getSarg1())) where += " AND (" + getSarg1() + ")";
    where += orderBy();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(0); // 取所有数据，下拉框不分页
    page.setWhere(where);
    page.commit();
    List<PltCountry> list = page.getList();
    JSONArray ja = new JSONArray();
    JSONObject lineJson = null;
    boolean isinf = false;
    if (list.size() > 0 && list.get(0) instanceof IExtName) isinf = true;
    for (PltCountry line : list) {
      lineJson = new JSONObject();
      // 注意不论主键是否为STRING,都转成字符串
      // 前EXT组件初始化时,数字也是以字符形式判断其值
      lineJson.put("value", line.getPkey().toString());
      if (isinf) lineJson.put("text", line.getName(PltConfigDAO.manageLanguage()));
      else lineJson.put("text", line.getPkey());
      ja.put(lineJson);
    }
    JSONObject json = new JSONObject();
    json.put(STORE_ROOT, ja);
    ServletActionContext.getResponse().getWriter().print(json.toString());
  }
}
