package irille.action.plt;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.plt.PltPay;

public class PltPayAction extends MgtAction<PltPay> {
	
	private static final long serialVersionUID = 1L;

	public PltPay getBean() {
		return _bean;
	}

	public void setBean(PltPay bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PltPay.class;
	}

	@Override
	public void list() throws Exception {
		String a = "";
		a.hashCode();
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(getLimit());
		page.setWhere(where);
		page.commit();
		List<PltPay> list = page.getList();
		JSONObject lineJson = null;
		for (PltPay line : list) {
			lineJson = crtJsonByBean(line);
			JSONObject paySetting = line.gtPaysetting();
			if(paySetting!=null) {
				lineJson.put("account_name", paySetting.get("account_name"));
				lineJson.put("bank_account", paySetting.get("bank_account"));
				lineJson.put("depos_it_bank_subbranch", paySetting.get("depos_it_bank_subbranch"));
				lineJson.put("depos_it_bank_subbranch_ascription", paySetting.get("depos_it_bank_subbranch_ascription"));
			}
			ja.put(lineJson);
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}
}
