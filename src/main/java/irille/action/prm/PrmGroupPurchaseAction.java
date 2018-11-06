package irille.action.prm;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.shop.prm.Info;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseDAO;
import irille.shop.prm.PrmGroupPurchaseLine;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrmGroupPurchaseAction extends MgtAction<PrmGroupPurchase>{
	private List<PrmGroupPurchaseLine> _listLine = new ArrayList<PrmGroupPurchaseLine>();
	
	public PrmGroupPurchase getBean(){
		return _bean;
	}
	
	public void setBean(PrmGroupPurchase bean){
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PrmGroupPurchase.class;
	}
	public List<PrmGroupPurchaseLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<PrmGroupPurchaseLine> listLine) {
		_listLine = listLine;
	}
	
	
	/**
	 * 发送对应供应商的订单
	 */
	public void send() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		Info info = new Info(address,name,phone,code);
		JSONObject json = new JSONObject();
		PrmGroupPurchaseDAO.Send send = new PrmGroupPurchaseDAO.Send();
		try{
			send.setBKey(getPkey());
			send.setInfo(info);
			send.commit();
			json.put("success", true);
		}catch(Exception e){
			json.put("success", false);
		}
		writerOrExport(json);
		
	}
	
	
	
	

}
