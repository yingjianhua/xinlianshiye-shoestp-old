package irille.view.usr;

import irille.view.BaseView;

import java.util.List;

public class ConsultRelationView implements BaseView {

	private Integer id;
	private String supplierName;
	private Boolean haveNewMsg;
	private List<ConsultMessageView> msgs;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Boolean isHaveNewMsg() {
		return haveNewMsg;
	}
	public void setHaveNewMsg(Boolean haveNewMsg) {
		this.haveNewMsg = haveNewMsg;
	}
	public List<ConsultMessageView> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<ConsultMessageView> msgs) {
		this.msgs = msgs;
	}
	
}
