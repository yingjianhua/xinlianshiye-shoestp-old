package irille.homeAction.ActivitySignIn;

import com.google.inject.Inject;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsDao;
import irille.Entity.Activity.ActivityInfo;
import irille.homeAction.HomeAction;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 16:00
 */
public class ActivitySignInAction extends HomeAction<ActivityInfo> {
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String tel;

    @Setter
    @Getter
    private String inquiry;
    @Setter
    @Getter
    private int country;
    @Inject
    private ActivitySignInsDao activitySignInsDao;

    public void signIn() throws IOException {
        ActivityInfo entity=new ActivityInfo();
        entity.setName(getName());
        entity.setCountry(getCountry());
        entity.setTel(getTel());
        entity.setEmail(getEmail());
        entity.setInquiry(getInquiry());
        activitySignInsDao.setB(entity).commit();
        writeErr(1,null);
    }
}
