package irille.homeAction.pdt.dto;

import irille.shop.usr.UsrSupplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierView {

	private Integer id;
	private String name;
	
	private SupplierView() {};
	
	public static SupplierView build(UsrSupplier supplier) {
		SupplierView view = new SupplierView();
		view.setId(supplier.getPkey());
		view.setName(supplier.getName());
		return view;
	}
	
	public static List<SupplierView> build(List<UsrSupplier> suppliers) {
		List<SupplierView> views = new ArrayList<SupplierView>();
		for(UsrSupplier supplier:suppliers) {
			views.add(build(supplier));
		}
		return views;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
