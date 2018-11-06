package irille.homeAction.usr.dto;

import irille.shop.usr.UsrCart;

import java.util.ArrayList;
import java.util.List;

public class CartView {
	private Integer id;
	private Integer spec;
	private String color;
	private String size;
	private Integer product;
	private int num;
	
	private CartView(){};
	public static CartView build(UsrCart cart) {
		CartView view = new CartView();
		view.id = cart.getPkey();
		view.spec = cart.getSpec();
		view.num = cart.getQty().intValue();
		
		return view;
	}
	public static List<CartView> build(List<UsrCart> carts) {
		List<CartView> view = new ArrayList<>();
		for(UsrCart cart:carts) {
			view.add(build(cart));
		}
		return view;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpec() {
		return spec;
	}
	public void setSpec(Integer spec) {
		this.spec = spec;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
