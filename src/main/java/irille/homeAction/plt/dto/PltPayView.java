package irille.homeAction.plt.dto;

import java.util.ArrayList;
import java.util.List;

import irille.shop.plt.PltPay;

public class PltPayView {
	private String name;
	private PltPay pay;
	private Integer supId;
	
	public static PltPayView build(PltPay pay){
		PltPayView view = new PltPayView();
		view.setName(pay.gtMode().getLine().getName());
		view.setPay(pay);
		view.setSupId(pay.getSupplier());
		return view;
	}
	
	public static List<PltPayView> build(List<PltPay> payList){
		List<PltPayView> views = new ArrayList<PltPayView>();
		for(PltPay pay : payList){
			views.add(build(pay));
		}
		return views;
	}
	
	public Integer getSupId() {
		return supId;
	}
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PltPay getPay() {
		return pay;
	}
	public void setPay(PltPay pay) {
		this.pay = pay;
	}
	
	
}
