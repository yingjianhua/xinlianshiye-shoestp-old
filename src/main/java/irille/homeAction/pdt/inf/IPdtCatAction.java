package irille.homeAction.pdt.inf;

import irille.pub.svr.ItpCheckPurchaseLogin.NeedLogin;

import java.io.IOException;

import org.json.JSONException;

public interface IPdtCatAction {

	/**
	 * 列表产品一级类目
	 * @author yingjianhua
	 */
	@NeedLogin
	public void listTop() throws IOException, JSONException;
}
