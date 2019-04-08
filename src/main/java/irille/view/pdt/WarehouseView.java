package irille.view.pdt;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class WarehouseView implements BaseView {
	private Integer id;
	private String img;
	private String num;
	private String name;
	private String productCate;
	private String storeCate;
	private BigDecimal price;
	private Integer cat;
	private String sku;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Date time;

	private Integer status;
}
