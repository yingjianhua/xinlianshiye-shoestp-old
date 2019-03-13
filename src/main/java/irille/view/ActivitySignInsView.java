package irille.view;

import lombok.Data;

@Data
public class ActivitySignInsView implements BaseView {
  private Integer id; // pkey
  private String name; // 姓名
  private String tel; // 电话
  private String email; // 邮箱
  private String companyName; // 公司名称
}
