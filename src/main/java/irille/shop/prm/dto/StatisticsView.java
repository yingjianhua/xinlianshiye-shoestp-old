package irille.shop.prm.dto;

import java.math.BigDecimal;

/**
 * 用于统计单一联合采购活动下采购商生成的订单明细
 * @author liyichao
 *
 */
public class StatisticsView {
	private Integer pkey;
	private Integer count;//数量
	private BigDecimal price;//总价
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
}
