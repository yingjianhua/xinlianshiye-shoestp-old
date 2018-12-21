package irille.sellerAction.view;

import irille.pub.bean.BeanLong;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;
@Data
public class AuthenticationView  implements BaseView {
    private Boolean isauth;
    private String time;
    private Integer age;
}
