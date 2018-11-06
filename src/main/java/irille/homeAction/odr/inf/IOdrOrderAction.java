package irille.homeAction.odr.inf;

import java.io.IOException;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IOdrOrderAction {
	
	/**
	 * 订单中心页面
	 * @return
	 * @author yingjianhua
	 */
	public String orders() throws Exception;
	
	/**
	 * 订单详情页面
	 * xiayan
	 * @return
	 */
	public String detail() throws Exception;

	/**
	 * 打印订单
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void print() throws JsonParseException, JsonMappingException, JSONException, IOException ;
	/**
	 * xiayan
	 * app 用户新增订单
	 * @return 
	 */
	public void appAddOrder()throws Exception;
	/**
	 * 确认付款
	 */
	public void pay() throws IOException, JSONException;
	
//	/**
//	 * 删除订单接口
//	 * @author yingjianhua
//	 */
//	public void delete() throws Exception;
//	
	/**
	 * 确认收货
	 * @author yingjianhua
	 */
	public void confirmReceiving() throws IOException;
	
}
