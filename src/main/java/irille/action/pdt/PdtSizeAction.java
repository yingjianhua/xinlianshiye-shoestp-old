package irille.action.pdt;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.bean.BeanInt;
import irille.pub.bean.BeanLong;
import irille.pub.bean.BeanStr;
import irille.pub.idu.IduPage;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtColorDAO;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSizeDAO;

public class PdtSizeAction extends MgtAction<PdtSize> {
	
	private static final long serialVersionUID = -6798366774586659428L;

	@Override
	public Class<PdtSize> beanClazz() {
		return PdtSize.class;
	}
	
	@Override
	public void list() throws Exception {
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(getLimit());
		page.setWhere(where);
		page.commit();
		List<PdtSize> list = page.getList();
		JSONObject lineJson = null;
		for (PdtSize line : list) {
			if(!line.gtDeleted()) {
				lineJson = crtJsonByBean(line);
				ja.put(lineJson);
			}
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}
	
	
	private Serializable[] tranMuliKeys(String[] ary) {
		Tb tb = tb();
		Class clazz = null;
		if (BeanInt.class.isAssignableFrom(tb.getClazz()))
			clazz = Integer.class;
		else if (BeanStr.class.isAssignableFrom(tb.getClazz()))
			clazz = String.class;
		else if (BeanLong.class.isAssignableFrom(tb.getClazz()))
			clazz = Long.class;
		Serializable[] svs = new Serializable[ary.length];
		for (int i = 0; i < ary.length; i++) {
			String line = ary[i];
			Serializable obj = line;
			if (clazz.equals(Integer.class))
				obj = Integer.parseInt(line);
			else if (clazz.equals(Long.class))
				obj = Long.parseLong(line);
			svs[i] = obj;
		}
		return svs;
	}
	
	@Override
	public void delMulti() throws Exception {
		Serializable[] svs = tranMuliKeys(getPkeys().split(","));
		for (Serializable line : svs) {
			PdtSizeDAO.Del upd = new PdtSizeDAO.Del();
			upd.setBKey(line);
			upd.commit();
		}
		writeSuccess();
	}
	
}