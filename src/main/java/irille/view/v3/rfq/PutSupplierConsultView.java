package irille.view.v3.rfq;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 9:43 */
@Data
public class PutSupplierConsultView implements BaseView {
  private String title;
  //    描述
  private String description;
  private String images;
  // 数量
  private int quantity;
  // 数量单位  枚举类对应
  private int unit = 1;
  private List<String> extra_request;
  private Integer supplierId;
}
