package irille.view.odr;

import java.util.Date;

public class OrderSearchView {
	
	private String email;
	private String number;
	private Date beginTime;
	private Date endTime;
	private Byte payType;
	private Byte status;
	private Byte types;
	

	public Byte getTypes() {
		return types;
	}
	public void setTypes(Byte types) {
		this.types = types;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Byte getPayType() {
		return payType;
	}
	public void setPayType(Byte payType) {
		this.payType = payType;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
