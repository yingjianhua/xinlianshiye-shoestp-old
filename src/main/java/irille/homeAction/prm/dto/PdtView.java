package irille.homeAction.prm.dto;

import java.math.BigDecimal;
import java.util.List;

import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtProduct;

public class PdtView {
	private Integer qty;
	private BigDecimal totalPrice;
	
	public static PdtView build(List<OdrOrderLine> orderList){
		PdtView view = null;
		for(OdrOrderLine order:orderList){
				if(view == null){
					view = new PdtView();
					view.setQty(order.getQty());
					view.setTotalPrice(order.getSubtotal());
				}else{
					view.setQty(view.getQty() + order.getQty());
					view.setTotalPrice(view.getTotalPrice().add(order.getSubtotal()));
				}
			}
		return view;
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
