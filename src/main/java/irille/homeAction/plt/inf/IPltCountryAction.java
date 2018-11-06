package irille.homeAction.plt.inf;

import java.io.IOException;

import org.json.JSONException;

public interface IPltCountryAction {
	
	/**
	 * 列表所有国家
	 * @author yingjianhua
	 */
	public void list() throws IOException, JSONException;
}
