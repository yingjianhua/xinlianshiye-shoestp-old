package irille.homeAction.pdt;

import irille.action.ActionBase;
import irille.shop.pdt.PdtAttrLine;

public class PdtAttrLineAction extends ActionBase<PdtAttrLine> {
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

  //	@Override
  //	public void list() throws Exception {
  //		JSONObject json = new JSONObject();
  //		JSONArray ja = new JSONArray();
  //		// 目前过滤器的搜索，是肯定会带初始条件的
  //		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
  //		IduPage page = newPage();
  //		page.setStart(getStart());
  //		page.setLimit(getLimit());
  //		page.setWhere(where);
  //		page.commit();
  //		List<PdtAttrLine> list = page.getList();
  //		JSONObject lineJson = null;
  //		for (PdtAttrLine line : list) {
  //			lineJson = crtJsonByBean(line);
  //			ja.put(lineJson);
  //		}
  //		System.out.println(getFilter()+">>>>>>>>>>>>>>>>>>>>>>>>>");
  //		System.out.println(ja+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  //		json.put(STORE_ROOT, ja);
  //		json.put(STORE_TOTAL, page.getCount());
  //		writerOrExport(json);
  //	}

}
