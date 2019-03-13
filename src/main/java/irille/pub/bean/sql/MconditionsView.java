package irille.pub.bean.sql;

import irille.view.BaseView;
import lombok.Data;

@Data
public class MconditionsView implements BaseView {
  private String mode; // 实体类字段
  private Integer condition; // 判断条件
  // 1.>大于 2.<小于 3.不等于<>4.等于=5.包含like 6.不包含 not like
  private String content; // 查询内容
  private Integer isdate; // 判断该字段是否是日期类型
}
