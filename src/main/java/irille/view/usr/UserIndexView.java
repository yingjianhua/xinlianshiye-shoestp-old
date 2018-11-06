package irille.view.usr;

import irille.view.BaseView;
import irille.view.odr.OrderView;

import java.util.List;

public class UserIndexView implements BaseView{

	private String loginName;
	private String email;
	private List<OrderView> orders;
	private AddressView defaultAddress;
	private List<FavoriteView> favorites;

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<OrderView> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderView> orders) {
		this.orders = orders;
	}
	public AddressView getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(AddressView defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	public List<FavoriteView> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<FavoriteView> favorites) {
		this.favorites = favorites;
	}
	
}
