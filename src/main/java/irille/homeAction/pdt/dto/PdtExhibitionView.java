package irille.homeAction.pdt.dto;

import java.math.BigDecimal;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * 展会商品信息
 * 
 * @author GS
 *
 */
@Getter
@Setter
public class PdtExhibitionView implements BaseView {

	private Integer id; // 商品ID

	private String title; // 展会商品标题

	private String img; // 商品图片

	private BigDecimal price; // 展会商品价格

	private String gender; // 性别

	private String insole; // 鞋内底材料

	private String outsole; // 鞋外底材料

	private String originPlace; // 原厂地

	private Integer startQuantity;// 起订量

	private ExhibitionSupplierView supplier; // 对应商品的供应商信息

}
