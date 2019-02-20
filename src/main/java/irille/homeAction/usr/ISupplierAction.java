package irille.homeAction.usr;

import java.io.IOException;

import org.json.JSONException;

public interface ISupplierAction {

	/**
	 * 获取供应商详情
	 * @throws IOException
	 * @author Jianhua Ying
	 * @throws JSONException 
	 */
	void getDetail() throws IOException, JSONException;
}
