package irille.platform.usr;

import irille.action.MgtAction;
import irille.action.usr.inf.IUsrAccessAction;
import irille.shop.usr.UsrAccess;
import irille.shop.usr.UsrAccessDAO;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UsrAccessAction extends MgtAction<UsrAccess> implements IUsrAccessAction {
	
	private static final long serialVersionUID = 1100842014138698019L;
	
	public final static Map<String, String> MODULES = new LinkedHashMap<String, String>();
	
	static {
		List<String> moduleNames = UsrAccessDAO.listModuleName();
		for(int i=0;i<moduleNames.size();i++) {
			MODULES.put(Integer.valueOf(i).toString(), moduleNames.get(i));
		}
	}
	
	@Override
	public Class<UsrAccess> beanClazz() {
		return UsrAccess.class;
	}

	public UsrAccess getBean() {
		return _bean;
	}

	public void setBean(UsrAccess bean) {
		this._bean = bean;
	}

	@Override
	public void listModuleName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray ja = new JSONArray();
		for (String line : MODULES.keySet()) {
			JSONObject json = new JSONObject();
			json.put("value", line);
			json.put("text", MODULES.get(line));
			ja.put(json);
		}
		JSONObject js = new JSONObject();
		js.put("items", ja);
		response.getWriter().print(js.put("success", true).toString());
	}
}
