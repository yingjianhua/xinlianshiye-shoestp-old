package irille.homeAction.prm.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrCart;

public class SpecStaView {
	private Integer colorId;
	private Integer specId;
	private String size;
	private BigDecimal price;
	private Integer qty;
	private BigDecimal totalPrice;
	
	public static SpecStaView buildByCart(UsrCart cart){
		SpecStaView view = new SpecStaView();
		PdtSpec spec = cart.gtSpec();
		try {
			view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
			view.setPrice(spec.getPrice());
			view.setTotalPrice(cart.getAmtTotal());
			view.setQty(cart.getQty());
			view.setSpecId(spec.getPkey());
			view.setColorId(spec.getColor());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public static Map<Integer,List<SpecStaView>> buildByCart(List<UsrCart> carts){
		Map<Integer,List<SpecStaView>> views = new HashMap<Integer,List<SpecStaView>>();
		for (UsrCart cart : carts) {
			if(views.get(cart.gtSpec().getProduct()) == null){
				List<SpecStaView> view = new ArrayList<SpecStaView>();
				view.add(buildByCart(cart));
				views.put(cart.gtSpec().getProduct(), view);
			}else{
				List<SpecStaView> view = views.get(cart.gtSpec().getProduct());
				view.add(buildByCart(cart));
			}
		}
		return views;
	}
	
	public static SpecStaView build(OdrOrderLine orderLine){
		SpecStaView view = new SpecStaView();
		PdtSpec spec = orderLine.gtSpec();
		try {
			view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
			view.setPrice(spec.getPrice());
			view.setQty(orderLine.getQty());
			view.setTotalPrice(spec.getPrice().multiply(new BigDecimal(orderLine.getQty())));
			view.setSpecId(spec.getPkey());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public static Map<String,List<SpecStaView>> build(List<OdrOrderLine> orderList){
		Map<String,List<SpecStaView>> views = new HashMap<String,List<SpecStaView>>();
			for(OdrOrderLine orderLine : orderList){
				String colorName = null;
				String sizeName = null;
				try {
					colorName = orderLine.gtSpec().gtColor().getName(HomeAction.curLanguage());
					sizeName = orderLine.gtSpec().gtSize().getName(HomeAction.curLanguage());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(views.get(colorName) == null ){
					List<SpecStaView> view = new ArrayList<SpecStaView>();
					view.add(build(orderLine));
					views.put(colorName, view);
				}else{
					List<SpecStaView> view = views.get(colorName);
					boolean flag = false;
					for(SpecStaView spec : view){
						if(spec.getSize().equals(sizeName)){
							spec.setQty(spec.getQty()+orderLine.getQty());
							spec.setTotalPrice(spec.getTotalPrice().add(orderLine.gtSpec().getPrice().multiply(new BigDecimal(orderLine.getQty()))));
							flag = true;
						}
					}
					if(flag == false){
						view.add(build(orderLine));
					}
				}
			}
		return views;
	}
	
	

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
