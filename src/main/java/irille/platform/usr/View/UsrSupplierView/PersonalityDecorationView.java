package irille.platform.usr.View.UsrSupplierView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PersonalityDecorationView implements BaseView {
  private String homePageDiy; // 首页个性装修
  private String productPageDiy; // 产品个性装修
  private String contactPageDiy; // 联系页个性装修
  private String homePageDiyMobile; // 首页个性装修(移动)
  private String productPageDiyMobile; // 产品个性装修(移动)
  private String contactPageDiyMobile; // 联系页个性装修(移动)
}
