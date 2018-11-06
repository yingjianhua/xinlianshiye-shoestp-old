package irille.homeAction.pdt.dto;

import irille.shop.pdt.PdtProduct;
import irille.view.BaseView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductView implements BaseView{
	private Integer id;
	private Integer shopId;
	private String name;
	private String code;
	private String img;
	private BigDecimal price;
	
	public static ProductView build(PdtProduct product) {
		ProductView view = new ProductView();
		view.setId(product.getPkey());
		view.setShopId(product.getSupplier());
		view.setName(product.getName());
		view.setCode(product.getCode());
		String[] pics = product.getPicture().split(",");
		view.setImg(pics.length>0?pics[0]:"");
		view.setPrice(product.getCurPrice());
		return view;
	}
	public static List<ProductView> build(List<PdtProduct> products) {
		List<ProductView> views = new ArrayList<ProductView>();
		for(PdtProduct product:products) {
			views.add(build(product));
		}
		return views;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}


