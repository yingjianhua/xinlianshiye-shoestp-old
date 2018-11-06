package irille.shop.odr;

import java.math.BigDecimal;
import java.util.List;

import irille.pub.bean.Query;
import irille.shop.odr.OdrOrderLine.T;
import irille.shop.pdt.PdtSpec;


public class OdrOrderLineDAO {

	/**
	 * 列表该订单下的所有明细
	 * @param order 订单pkey
	 * @author yingjianhua
	 */
	public static List<OdrOrderLine> listByOrder(Long order) {
		return Query.SELECT(OdrOrderLine.class).WHERE(T.MAIN, "=?", order).queryList();
	}
	
	

	/**
	 * 新增订单明细
	 */
	public static OdrOrderLine buildOrderLine(PdtSpec spec,Integer qty){
		OdrOrderLine line = new OdrOrderLine();
		line.setSpec(spec.getPkey());
		line.setQty(qty);
		BigDecimal price = BigDecimal.ZERO;
		if(spec.getPrice() == null){
			price = spec.gtProduct().getCurPrice();
		}else{
			price = spec.getPrice();
		}
		BigDecimal subtotal = price.multiply(new BigDecimal(qty)).setScale(4,BigDecimal.ROUND_HALF_UP);
		line.setSubtotal(subtotal);
		return line;
	}

}
