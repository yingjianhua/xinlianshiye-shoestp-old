package irille.homeAction.plt;

import irille.homeAction.HomeAction;
import irille.homeAction.plt.inf.IPltCountryAction;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltCountryDAO;
import irille.shop.plt.PltProvince;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PltCountryAction extends HomeAction<PltCountry> implements IPltCountryAction {

	private static final long serialVersionUID = -5535773330197177372L;
    private Map<String, Object> map = new HashMap<>();
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 列表所有国家
	 * @author yingjianhua
	 */ 
	public void list() throws IOException, JSONException {
		write(PltCountryDAO.listView(null));
	}
	
	/**
	 * 获取所有国家并将省份存到国家对象中
	 * @throws Exception 
	 */
	public void countries() throws Exception{
		List<PltCountry> countries = translateUtil.getAutoTranslateList(BeanBase.list(PltCountry.class,null,false),PltConfigDAO.supplierLanguage(SellerAction.getSupplier()));
		JSONArray items = new JSONArray();
		for(PltCountry country : countries){
			BeanQuery<PltProvince> query = Query
			.SELECT(PltProvince.T.PKEY,PltProvince.T.NAME)
			.FROM(PltProvince.class)
			.WHERE(PltProvince.T.MAIN,"=?", country.getPkey());
			List<PltProvince> provinces = translateUtil.getAutoTranslateList(query.queryList(PltProvince.class),PltConfigDAO.supplierLanguage(SellerAction.getSupplier()));
			JSONArray array = new JSONArray(provinces,false);
			JSONObject json = crtJsonByBean(country);
			json.put("provinces", array);
			items.put(json);
		}
		JSONObject json = new JSONObject();
		json.put(STORE_ROOT, items);
		writerOrExport(json);
	}
}