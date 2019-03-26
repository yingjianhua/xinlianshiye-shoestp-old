package irille.platform.o2o.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class O2o_RegistrationView implements BaseView {

  private Integer id;
  private String fullName; // 名称
  private String gender; // 性别
  private String country; // 国家
  private String email; // 邮箱 STR(100)<null>
  private String telphone; // 手机号码 STR(200)<null>
  private String footwear; // 鞋分类 STR(100)
  private String activityName; // O2O活动信息 <表主键:O2OActivity> INT
  private String marketing; // 销售市场 <O2OMarketing> BYTE
  private String buyertype; // 买家类型
  private String exhibitionCountry; // 展会国家
  private String remarks; // 备注
  private Integer activityId; // 展会id
}
