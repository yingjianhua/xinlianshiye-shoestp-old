package irille.platform.rfq.view;

import lombok.Data;

@Data
public class ProductView {

  private Integer pkey; // 编号 INT
  private String name; // 名称 JSONOBJECT
  private String catName; // 商品分类名称
}
