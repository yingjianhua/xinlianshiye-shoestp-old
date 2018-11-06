package irille.action.lg;

import irille.action.MgtAction;
import irille.shop.lg.LgAccess;
import irille.shop.lg.LgAccessDAO;

import org.json.JSONException;
import org.json.JSONObject;

public class LgAccessAction extends MgtAction<LgAccess>{

	private static final long serialVersionUID = -1447408760268593125L;
	
	@Override
	public Class<LgAccess> beanClazz() {
		return LgAccess.class;
	}
	
	private String query;
	
	/**
	 * 获取统计功能的列表
	 * @throws Exception 
	 * @throws JSONException 
	 */
	public void queryList() throws JSONException, Exception {
		writerOrExport(new JSONObject().put("success", true).put("items", LgAccessDAO.listQuerys()));
	}
	public void query() throws JSONException, Exception {
		writerOrExport(new JSONObject().put("success", true).put("items", LgAccessDAO.query(query)));;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

}
