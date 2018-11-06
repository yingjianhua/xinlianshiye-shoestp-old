package irille.action.pdt;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.bean.BeanInt;
import irille.pub.bean.BeanLong;
import irille.pub.bean.BeanStr;
import irille.pub.idu.IduPage;
import irille.pub.tb.Tb;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrCatDAO;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.plt.PltConfigDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class PdtCatAction extends MgtAction<PdtCat> {
	public PdtCat getBean() {
		return _bean;
	}

	public void setBean(PdtCat bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtCat.class;
	}
	public String orderBy() {
		return " ORDER BY PKEY ASC";
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
		List<PdtCat> list = page.getList();
		JSONObject lineJson = null;
		for (PdtCat line : list) {
			if(!line.gtDeleted()) {
				lineJson = crtJsonByBean(line);
				ja.put(lineJson);
			}
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}
	
	
	
	@Override
	public void getComboTrigger() throws Exception {
		String where = crtQueryAll();
		if (!Str.isEmpty(getSarg1()))
			where += " AND (" + getSarg1() + ")";
		where += orderBy();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(0); // 取所有数据，下拉框不分页
		page.setWhere(where);
		page.commit();
		List<PdtCat> list = page.getList();
		
		Map<Integer, String> map1 = new TreeMap<Integer, String>();
		Map<Integer, String> map2 = new TreeMap<Integer, String>();
		Map<Integer, String> map3 = new TreeMap<Integer, String>();
		for(PdtCat line:list) {
			if(line.getCategoryUp() == null)
				map1.put(line.getPkey(), "├"+line.getName(PltConfigDAO.manageLanguage()));
		}
		for(PdtCat line:list) {
			if(line.getCategoryUp()!=null&&map1.containsKey(line.getCategoryUp()))
				map2.put(line.getPkey(), "｜├"+line.getName(PltConfigDAO.manageLanguage()));
		}
		for(PdtCat line:list) {
			if(line.getCategoryUp()!=null&&map2.containsKey(line.getCategoryUp()))
				map3.put(line.getPkey(), "｜｜├"+line.getName(PltConfigDAO.manageLanguage()));
		}
		map1.putAll(map2);
		map1.putAll(map3);
		JSONArray ja = new JSONArray();
		JSONObject lineJson = null;
		for(Integer value:map1.keySet()) {
			lineJson = new JSONObject();
			// 注意不论主键是否为STRING,都转成字符串
			// 前EXT组件初始化时,数字也是以字符形式判断其值
			lineJson.put("value", value.toString());
			lineJson.put("text", map1.get(value));
			ja.put(lineJson);
		}
		JSONObject json = new JSONObject();
		json.put(STORE_ROOT, ja);
		ServletActionContext.getResponse().getWriter().print(json.toString());
	};
	
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
			PdtCatDAO.Del upd = new PdtCatDAO.Del();
			upd.setBKey(line);
			upd.commit();
		}
		writeSuccess();
	}
	
}