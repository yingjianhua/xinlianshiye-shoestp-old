package irille.sellerAction.activitys;

import irille.Entity.Activity.ActivityInfo;
import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.sellerAction.activitys.Inf.IActivityAction;
import irille.sellerAction.SellerAction;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 14:22
 */
public class ActivityAction extends SellerAction<ActivityInfo> implements IActivityAction {

    @Inject
    private IActivitySignInInfoManageService activitySignInInfoManageService;


    @Getter
    @Setter
    private int country;
    @Getter
    @Setter
    private String keyword;

    @Override
    public void list() throws Exception {
        System.out.println("awdwad");
        write(activitySignInInfoManageService.getAllSignInInfo(getStart(), getLimit(), getCountry(), getKeyword()));
    }

    @Override
    public void load() throws Exception {
        write(activitySignInInfoManageService.getSignInInfo(Integer.valueOf(String.valueOf(getId()))));
    }

    @Override
    public void del() {
        activitySignInInfoManageService.del(Integer.valueOf(String.valueOf(getId())));
    }

}
