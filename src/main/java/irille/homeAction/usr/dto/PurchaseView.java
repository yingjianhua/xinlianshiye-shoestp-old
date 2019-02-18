package irille.homeAction.usr.dto;

import irille.shop.usr.UsrPurchase;
import irille.view.BaseView;
import lombok.Data;

@Data
public class PurchaseView implements BaseView {
	private String nickname;
	private String avatar;
	private Integer unreadMessagersCount;//未读取发信人数量
	private Integer requestsFromConnectionsCount;//新联系人数量
	private String loginName; //登录名
	private Integer favoriteCount; //收藏数
	private Integer cartCount; //购物车数
	private Integer inquiryCount; //询盘数
	
	public static PurchaseView build(UsrPurchase purchase, Integer favoriteCount, Integer cartCount, Integer inquiryCount) {
		PurchaseView view = new PurchaseView();
		view.setLoginName(purchase.getLoginName());
		view.setFavoriteCount(favoriteCount);
		view.setCartCount(cartCount);
		view.setInquiryCount(inquiryCount);
		return view;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public Integer getCartCount() {
		return cartCount;
	}
	public void setCartCount(Integer cartCount) {
		this.cartCount = cartCount;
	}
	public Integer getInquiryCount() {
		return inquiryCount;
	}
	public void setInquiryCount(Integer inquiryCount) {
		this.inquiryCount = inquiryCount;
	}
}
