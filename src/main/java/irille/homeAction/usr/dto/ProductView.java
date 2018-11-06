package irille.homeAction.usr.dto;

import irille.homeAction.HomeAction;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

public class ProductView {
	private Integer id;
	private Integer supId;
	private String name;
	private String img;
	private BigDecimal price;
	private Integer qty;
	private BigDecimal totalAmt;
	private String imgs;
	private Integer type;
	private String sku;
	private Integer minOq;
	private Integer line;
	
	public ProductView() {
		
	}
	
	

	public ProductView(BigDecimal totalAmt,Integer qty,Integer id, Integer supId, String name, String img, BigDecimal price, byte type, String sku,Integer line) {
		this.totalAmt = totalAmt;
		this.qty = qty;
		this.id = id;
		this.supId = supId;
		this.name = name;
		this.img = img;
		this.price = price;
		this.type = Integer.valueOf(type);
		this.sku = sku;
		this.line = line;
	}
	
	public static ProductView build(UsrCart cart){
		ProductView view = new ProductView();
		PdtSpec spec = cart.gtSpec();
		PdtProduct product = translateUtil.getAutoTranslate(spec.gtProduct(), HomeAction.curLanguage());
		view.setId(product.getPkey());
		view.setName(product.getName());
		view.setSupId(cart.gtSupplier().getPkey());
		view.setMinOq(product.getMinOq().intValue());
		if(product.getPicture() != null){
			String[] pics = product.getPicture().split(",");
			view.setImg(pics.length>0?pics[0]:"");
		}
		view.setSku(product.getCode());
		if(Integer.valueOf(product.getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))){
			view.setPrice(product.getCurPrice());
			view.setType(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()));
			PrmGroupPurchaseLine line = PrmGroupPurchaseLine.chkUniqueProduct(false,product.getPkey());
			view.setLine(line.getPkey());
		}else{
			view.setPrice(product.getCurPrice());
			view.setType(Integer.valueOf(Pdt.OProductType.GENERAL.getLine().getKey()));
			view.setLine(-1);
		}
		if(view == null || view.getQty() == null){
			view.setQty(cart.getQty());
			view.setTotalAmt(cart.getAmtTotal());
		}else{
			view.setQty(view.getQty() + cart.getQty());
			view.setTotalAmt(view.getTotalAmt().add(cart.getAmtTotal()));
		}
			
			view.setImgs(product.getPicture());
		return view;
	}
	
	public static List<ProductView> build(List<UsrCart> carts){
		List<ProductView> views = new ArrayList<ProductView>();
		for(UsrCart cart : carts){
			boolean flag = false;
			for(ProductView view : views){
				if(view.getId().equals(cart.gtSpec().getProduct())){
					view.setTotalAmt(view.getTotalAmt().add(cart.getAmtTotal()));
					view.setQty(view.getQty()+cart.getQty());
					flag = true;
					break;
				}
			}
			if(flag == false){
				views.add(build(cart));
			}
		}
		return views;
		
	}
	
	
	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public Integer getMinOq() {
		return minOq;
	}

	public void setMinOq(Integer minOq) {
		this.minOq = minOq;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	
	
	
}
