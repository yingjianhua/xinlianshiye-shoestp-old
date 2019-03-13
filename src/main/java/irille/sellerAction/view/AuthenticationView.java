package irille.sellerAction.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class AuthenticationView implements BaseView {
  private Boolean isauth;
  private String time;
  private Integer age;
}
