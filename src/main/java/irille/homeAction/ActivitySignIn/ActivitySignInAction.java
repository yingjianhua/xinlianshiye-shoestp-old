package irille.homeAction.ActivitySignIn;

import com.google.inject.Inject;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsDao;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsOneDao;
import irille.Entity.Activity.ActivityInfo;
import irille.homeAction.HomeAction;
import irille.shop.as.PKContest;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 16:00
 */
public class ActivitySignInAction extends HomeAction<PKContest> {
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
    private String companyname;

    @Setter
    @Getter
    private String inquiry;
    @Setter
    @Getter
    private int country;
    @Inject
    private ActivitySignInsDao activitySignInsDao;
    @Inject
    private ActivitySignInsOneDao activitySignInsOneDao;

    public void signIn() throws IOException {
        ActivityInfo entity = new ActivityInfo();
        entity.setName(getName());
        entity.setCountry(getCountry());
        entity.setTel(getTel());
        entity.setEmail(getEmail());
        entity.setInquiry(getInquiry());
        activitySignInsDao.setB(entity).commit();
        writeErr(1, null);
    }

    public void signInPk() throws IOException {
        PKContest pkContest = new PKContest();
        pkContest.setName(getName());
        pkContest.setTel(getTel());
        pkContest.setEmail(getEmail());
        pkContest.setCompanyname(getCompanyname());
        activitySignInsOneDao.setB(pkContest).commit();
        write();
    }
}
