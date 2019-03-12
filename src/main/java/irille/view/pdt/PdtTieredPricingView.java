package irille.view.pdt;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PdtTieredPricingView {

	/*{id:1,count:null,price:null,type:1,discount:0}*/
	private Integer id;
	private Integer count;
	private BigDecimal price;
	private Integer type;
	private String discount;
	@Override
	public String toString() {
		return "PdtTieredPricingView {id=" + id + ", count=" + count + ", price=" + price + ", type=" + type
				+ ", discount=" + discount + "}";
	}
	
}
