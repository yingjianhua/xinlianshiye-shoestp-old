package irille.sellerAction.plt;

import irille.action.ActionBase;
import irille.pub.tb.FldLanguage.Language;
import irille.sellerAction.plt.inf.IPltConfigAction;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PltConfigAction  extends ActionBase<PltConfig> implements IPltConfigAction{

	@Override
	public Class beanClazz() {
		return PltConfig.class;
	}
	public PltConfig getBean()
	{
		return _bean;
	}
	public void setBean(PltConfig bean)
	{
		this._bean=bean;
	}
	public void enabledLanguage() throws IOException, JSONException {
		PltConfig.getVariable(Variable.Language);
		JSONArray array = new JSONArray();
		for(Language L:Language.values()) {
			JSONObject json = new JSONObject().put("shortName",L.name()).put("displayName", L.displayName());
			boolean f = false;
			for(String s:PltConfig.getVariable(Variable.Language).split(",")) {
				if(s.equals(L.name())) {
					f = true;
					break;
				}
			}
			json.put("isEnabled", f);
			array.put(json);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().print(array.toString());
	}
}
