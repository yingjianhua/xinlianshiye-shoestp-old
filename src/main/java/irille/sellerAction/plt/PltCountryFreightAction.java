package irille.sellerAction.plt;

import java.util.List;

import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.Tb;
import irille.sellerAction.SellerAction;
import irille.sellerAction.plt.inf.IPltCountryFreightAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountryFreight;
import irille.shop.plt.PltCountryFreightDAO;
import irille.shop.plt.PltFreightSeller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PltCountryFreightAction extends SellerAction<PltCountryFreight>
    implements IPltCountryFreightAction {
  private Integer section;

  public Integer getSection() {
    return section;
  }

  public void setSection(Integer section) {
    this.section = section;
  }

  @Override
  public void ins() throws Exception {
    PltCountryFreightDAO.Ins ins = new PltCountryFreightDAO.Ins();
    ins.setSection(section);
    ins.setPkeys(getPkeys());
    ins.commit();
    writeSuccess();
  }

  public void del() throws Exception {
    PltCountryFreightDAO.del del = new PltCountryFreightDAO.del();
    del.setSection(section);
    del.setSupplier(getSupplier().getPkey());
    del.setPkeys(getPkeys());
    del.commit();
    writeSuccess();
  }

  public String crtFilter() throws JSONException {
    String sql = "";
    if (Str.isEmpty(getFilter())) {
      sql +=
          " AND "
              + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
              + "="
              + SellerAction.getSupplier().getPkey();
      return crtFilterAll() + sql + orderByAsc();
    }
    JSONArray ja = new JSONArray(getFilter());
    Tb tb = tb();
    sql +=
        " AND "
            + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
            + "="
            + SellerAction.getSupplier().getPkey();
    for (int i = 0; i < ja.length(); i++) {
      JSONObject json = ja.getJSONObject(i);
      String fldName = json.getString(QUERY_PROPERTY);
      String param = json.getString(QUERY_VALUE);
      if (Str.isEmpty(param)) continue;
      if (!tb.chk(fldName)) continue;
      Fld fld = tb.get(fldName);
      if (fld == null) continue;
      sql += " AND " + Env.INST.getDB().crtWhereSearch(fld, param);
    }
    return crtFilterAll() + sql + orderBy();
  }

  public void list() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<PltCountryFreight> list = page.getList();
    JSONObject lineJson = null;
    for (PltCountryFreight line : list) {
      lineJson = crtJsonByBean(line);
      lineJson.put(
          "countrys",
          line.gtCountry().getName(PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }
}
