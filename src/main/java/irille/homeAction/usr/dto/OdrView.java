package irille.homeAction.usr.dto;

public class OdrView {
	private Integer supplier;
	private String remarks;
	private Integer payMethod;
	private Integer express;
	private String odrRemarks;
	
	public String getOdrRemarks() {
		return odrRemarks;
	}
	public void setOdrRemarks(String odrRemarks) {
		this.odrRemarks = odrRemarks;
	}
	public Integer getSupplier() {
		return supplier;
	}
	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Integer getExpress() {
		return express;
	}
	public void setExpress(Integer express) {
		this.express = express;
	}
	
	
}
