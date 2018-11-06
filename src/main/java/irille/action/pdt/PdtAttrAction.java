package irille.action.pdt;

import irille.action.MgtAction;
import irille.core.sys.Sys.OYn;
import irille.pub.Str;
import irille.pub.bean.BeanInt;
import irille.pub.bean.BeanLong;
import irille.pub.bean.BeanStr;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduPage;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtAttrLine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PdtAttrAction extends MgtAction<PdtAttr> {
	private List<PdtAttrLine> _listLine = new ArrayList<PdtAttrLine>();
	
	public PdtAttr getBean() {
		return _bean;
	}

	public void setBean(PdtAttr bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtAttr.class;
	}
	
	public List<PdtAttrLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<PdtAttrLine> listLine) {
		_listLine = listLine;
	}
	
	@Override
	public PdtAttr insRun() throws Exception {
		PdtAttrDAO.Ins ins = new PdtAttrDAO.Ins();
		ins.setB(getBean());
		ins.setLines(getListLine());
		ins.commit();
		return ins.getB();
	}
	
	@Override
	public PdtAttr updRun() throws Exception {
		PdtAttrDAO.Upd upd = new PdtAttrDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		return upd.getB();
	}
	
	public void del() throws Exception {
		PdtAttrDAO.Del del = new  PdtAttrDAO.Del();
		del.setB(getBean());
		del.commit();
		writeSuccess();
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
			PdtAttrDAO.Del upd = new PdtAttrDAO.Del();
			upd.setBKey(line);
			upd.commit();
		}
		writeSuccess();
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
		List<PdtAttr> list = page.getList();
		JSONObject lineJson = null;
		for (PdtAttr line : list) {
			if(!line.gtDeleted()) {
				lineJson = crtJsonByBean(line);
				ja.put(lineJson);
			}
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}
	
	
}