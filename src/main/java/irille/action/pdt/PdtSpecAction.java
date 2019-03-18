package irille.action.pdt;

import java.util.List;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.pdt.PdtSpec;
import org.json.JSONArray;
import org.json.JSONObject;

public class PdtSpecAction extends MgtAction<PdtSpec> {

  public PdtSpec getBean() {
    return _bean;
  }

  public void setBean(PdtSpec bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return PdtSpec.class;
  }

  public void listline() throws Exception { // 和list方法一样，为何不直接调用list方法
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<PdtSpec> list = page.getList();
    JSONObject lineJson = null;
    for (PdtSpec line : list) {
      if (!line.gtDeleted()) {
        lineJson = crtJsonByBean(line);
        ja.put(lineJson);
      }
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }
}
