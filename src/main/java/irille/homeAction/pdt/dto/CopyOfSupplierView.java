package irille.homeAction.pdt.dto;

import irille.shop.usr.UsrSupplier;

import java.util.List;

public class CopyOfSupplierView {

	private Integer id;
	private String name;
	private List<ProductView> products;
	
	private CopyOfSupplierView() {};
	
	public static CopyOfSupplierView build(UsrSupplier supplier, List<ProductView> products) {
		CopyOfSupplierView view = new CopyOfSupplierView();
		view.setId(supplier.getPkey());
		view.setName(supplier.getName());
		view.setProducts(products);
		return view;
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
	public List<ProductView> getProducts() {
		return products;
	}
	public void setProducts(List<ProductView> products) {
		this.products = products;
	}
}
