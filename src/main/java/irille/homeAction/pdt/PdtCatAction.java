package irille.homeAction.pdt;

import irille.homeAction.HomeAction;
import irille.homeAction.pdt.inf.IPdtCatAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PdtCatAction extends HomeAction<PdtCat> implements IPdtCatAction{

	/**
	 * 列表产品一级类目
	 * @author yingjianhua
	 */
	@Override
	@NeedLogin
	public void listTop() throws IOException, JSONException {
		write(PdtCatDAO.listTopCatView(curLanguage()));
	}
	/**
	 * 查询产品一级类目
	 *
	 * @throws Exception
	 * @author guosong
	 */
	public void selBsicCat() throws Exception {
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
			line.setName(line.getName(HomeAction.curLanguage()));
			if (line.getCategoryUp() == null) {
				lineJson = crtJsonByBean(line);
				ja.put(lineJson);
			}
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}

	/**
	 * 分类详情页面
	 *
	 * @throws Exception
	 * @author
	 */
	private List<PdtCat> list;

	private Map<PdtCat, List<PdtCat>> catMap = new HashMap<>();


	public String details() throws Exception {

		list = PdtCatDAO.Query.listTopCat();
		for(PdtCat top:list) {
			catMap.put(top, PdtCatDAO.Query.listSub(top.getPkey()));
		}
		translateUtil.getAutoTranslate(catMap, HomeAction.curLanguage());
		System.out.println("000000000");
		System.out.println(catMap);
		/*for (int i = 0; i < list.size(); i++) {
			list2 = BeanBase.list(PdtCat.class,PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField() + " = " + list.get(i).getPkey(), false);
			for (int a = 0; a < list2.size(); a++) {
				map.put("list1", list2.get(a));
			}
		}

		*/
		setResult("/home/catalog.jsp");
		return HomeAction.TRENDS;
	}

	public List<PdtCat> getList() {
		return list;
	}

	public void setList(List<PdtCat> list) {
		this.list = list;
	}

	public Map<PdtCat, List<PdtCat>> getCatMap() {
		return catMap;
	}

	public void setCatMap(Map<PdtCat, List<PdtCat>> catMap) {
		this.catMap = catMap;
	}
}
