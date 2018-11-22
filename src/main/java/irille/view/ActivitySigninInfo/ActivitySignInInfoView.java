package irille.view.ActivitySigninInfo;

import irille.view.BaseView;
import lombok.Data;

/**
 * 活动页
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 13:38
 */
@Data
public class ActivitySignInInfoView implements BaseView {
    private int id;
    private String name;
    private Integer country;
    private String tel;
    private String email;
    private String inquiry;
}
