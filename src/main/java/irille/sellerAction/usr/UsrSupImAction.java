package irille.sellerAction.usr;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Exp;
import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrSupImAction;
import irille.shop.usr.UsrSupIm;
import irille.shop.usr.UsrSupImDAO;

public class UsrSupImAction extends SellerAction<UsrSupIm> implements IUsrSupImAction{
	
	public void getDeviceType() throws Exception{
		JSONObject json = new JSONObject().put(STORE_ROOT, UsrSupImDAO.getDeviceType());
		writerOrExport(json);
	}
	
	
	/**
	 * 添加im代码
	 */
	public void addImSetting() throws IOException {
		UsrSupImDAO.AddImSetting setting = new UsrSupImDAO.AddImSetting();
		setting.setB(getBean());
		try {
			setting.commit();
			write();
		}catch(Exp e) {
			writeErr(e.getLastMessage());
		}
	}
	
	/**
	 * 获取im列表
	 */
	public void getImSetting() throws Exception {
		List<UsrSupIm> imList = UsrSupImDAO.getImSetting(SellerAction.getSupplier().getPkey());
		JSONArray ja = new JSONArray(imList,false);
		JSONObject json = new JSONObject();
		json.put(STORE_ROOT, ja);
		writerOrExport(json);
	}
	
	/**
	 * 修改im
	 * @throws IOException 
	 */
	public void updImSetting() throws IOException{
		UsrSupImDAO.UpdImSetting updSetting = new UsrSupImDAO.UpdImSetting();
		updSetting.setB(getBean());
		try{
			updSetting.commit();
			write();
		}catch(Exp e){
			writeErr(e.getLastMessage());
		}
	}
}
