package irille.view.odr;

import irille.shop.odr.OdrHistory;

import java.util.Date;

public class HistoryView {
	
	private Date date;
	private Byte status;
	private String operator;
	private String descrip;
	
	public static HistoryView build(OdrHistory bean) {
		HistoryView view = new HistoryView();
		view.setDate(bean.getTime());
		view.setStatus(bean.getState());
		view.setOperator(bean.getOperator());
		view.setDescrip(bean.getDescrip());
		return view;
	}
	
	public Long getDate() {
		return date.getTime();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
}
