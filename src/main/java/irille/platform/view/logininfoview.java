package irille.platform.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class logininfoview  implements BaseView {
    private  Integer id;//登录ID
    private  String name;//管理员名称

}
