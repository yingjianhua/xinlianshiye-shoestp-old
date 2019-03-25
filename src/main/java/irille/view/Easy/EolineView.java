package irille.view.Easy;

import irille.view.BaseView;
import lombok.Data;

@Data
public class EolineView implements BaseView {
  private String id; // 规格ID
  private String image; // 产品货号
  private String productname; // 产品名称
  private String color; // 颜色
  private String size; // 尺寸
  private String num; // 数量
  private String remarks; // 备注
}
