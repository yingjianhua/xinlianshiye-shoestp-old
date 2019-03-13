package irille.action.pdt;

import irille.action.MgtAction;
import irille.core.sys.Sys;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtAttrLine.T;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PdtAttrLineAction extends MgtAction<PdtAttrLine> {

  private static final long serialVersionUID = 1L;

  public PdtAttrLine getBean() {
    return _bean;
  }

  public void setBean(PdtAttrLine bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return PdtAttrLine.class;
  }

  public String crtFilter() throws JSONException {
    JSONArray ja = new JSONArray(getFilter());
    ja.put(
        new JSONObject()
            .put(QUERY_PROPERTY, T.DELETED.getFld().getCode())
            .put(QUERY_VALUE, Sys.OYn.NO.getLine().getKey()));
    setFilter(ja.toString());
    return super.crtFilter();
  }
}
