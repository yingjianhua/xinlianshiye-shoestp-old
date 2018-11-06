package irille.sellerAction.usr;

import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrConsultAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrConsult;
import irille.shop.usr.UsrConsultDAO;
import irille.shop.usr.UsrConsultRelationDAO;

import java.io.IOException;

import org.json.JSONException;

public class UsrConsultAction extends SellerAction<UsrConsult> implements IUsrConsultAction {
	
	private static final long serialVersionUID = 5377523976884558640L;
	
	private String countryName;
	private String title;
	private String qdvalue;
	/**
	 * 公共询盘列表(分页) 根据国家和标题查询
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	@Override
	public void page() throws IOException, JSONException {
		write(UsrConsultDAO.pagePublic(getStart(), getLimit(), getCountryName(), getTitle(),getQdvalue(),getSupplier().getPkey(), PltConfigDAO.supplierLanguage(getSupplier())));
	}
	
	/**
	 * 显示询盘的详细信息
	 * @throws IOException 
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	@Override
	public void detail() throws IOException, JSONException {
		if(getSupplier() != null) {
			if(getId() == null) {
				writeErr("consult is not exists");
			} else if(!UsrConsultRelationDAO.isOwner((Integer)getId(), getSupplier().getPkey())) {
				write(UsrConsultDAO.load((Integer)getId(), PltConfigDAO.supplierLanguage(getSupplier())));
			} else {
				write(UsrConsultRelationDAO.load((Integer)getId(), getSupplier().getPkey(), PltConfigDAO.supplierLanguage(getSupplier())));
			}
		} else {
			writeTimeout();
		}
	}
	private String msg;
	private String quotedPrice;
	/**
	 * 抢单报价
	 * @throws IOException 
	 * @author yingjianhua
	 */
	@Override
	public void quote() throws IOException {
		if(getSupplier() != null) {
			if(getId() == null) {
				writeErr("consult is not exists");
			} else {
				new UsrConsultRelationDAO.Quote((Integer)getId(), getSupplier().getPkey(), msg, quotedPrice).commit();
				write();
			}
		} else {
			writeTimeout();
		}
	}
	
	 private String _askName;
	  
	public String getAskName() {
		return _askName;
	}

	public void setAskName(String _askName) {
		this._askName = _askName;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(String quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public String getQdvalue() {
		return qdvalue;
	}

	public void setQdvalue(String qdvalue) {
		this.qdvalue = qdvalue;
	}
	
}
