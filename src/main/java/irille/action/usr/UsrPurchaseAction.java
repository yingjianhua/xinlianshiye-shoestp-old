package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseDAO;
import irille.shop.usr.UsrPurchaseLine;
import irille.shop.usr.UsrSupplier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

public class UsrPurchaseAction extends MgtAction<UsrPurchase>{
	private List<UsrPurchaseLine> _listLine = new ArrayList<UsrPurchaseLine>();
	private UsrSupplier usrInfo ;
	
	public UsrSupplier getUsrInfo() {
		return usrInfo;
	}

	public void setUsrInfo(UsrSupplier usrInfo) {
		this.usrInfo = usrInfo;
	}
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	private String copyPassword;
	public String getCopyPassword() {
		return copyPassword;
	}

	public void setCopyPassword(String copyPassword) {
		this.copyPassword = copyPassword;
	}

	public UsrPurchase getBean() {
		return _bean;
	}

	public void setBean(UsrPurchase bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return UsrPurchase.class;
	}
	
	public List<UsrPurchaseLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<UsrPurchaseLine> listLine) {
		_listLine = listLine;
	}
	
	public UsrPurchase insRun() throws IOException, JSONException {
		UsrPurchaseDAO.Ins ins = new UsrPurchaseDAO.Ins();
		ins.setB(getBean());
		ins.commit();
		return ins.getB();
	}
	
	/**
	 * 采购商登录
	 */
	public void login(){
		String agent = ServletActionContext.getRequest().getHeader("User-Agent").toLowerCase();
		System.out.println(agent);
	}
	
	public UsrPurchase updRun() throws IOException, JSONException {
		UsrPurchaseDAO.Upd upd = new UsrPurchaseDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		return upd.getB();
	}
	
	public void uda() throws Exception{
		UsrPurchaseDAO.Uda uda = new UsrPurchaseDAO.Uda();
		uda.setBKey(getPkey());
		uda.setPassword(password);
		uda.setCopyPassword(copyPassword);
		uda.commit();
		writeSuccess();
	}
	
	public void bcme(){
		UsrPurchaseDAO.Bcme bcme = new UsrPurchaseDAO.Bcme();
		bcme.setBKey(getPkey());
		bcme.setSupplier(usrInfo);
		bcme.setCopyPassword(copyPassword);
		bcme.commit();
	}
	
}
