package irille.sellerAction.activitys;

import irille.Entity.Activity.ActivityInfo;
import irille.Service.Activity.IActivityService;
import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.activitys.Inf.IActivityAction;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 14:22
 */
public class ActivityAction extends SellerAction<ActivityInfo> implements IActivityAction {

    @Inject
    private IActivitySignInInfoManageService activitySignInInfoManageService;


    @Inject
    private IActivityService activityService;


    @Getter
    @Setter
    private int country;
    @Getter
    @Setter
    private String keyword;

    @Getter
    @Setter
    private Date startDate;
    @Getter
    @Setter
    private Date endDate;

    @Override
    public void list() throws Exception {
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


    /**
     * @Description: 获取Pk大赛数据
     * @date 2018/12/5 14:55
     * @author lijie@shoestp.cn
     */
    public void getPkData() throws IOException {
        write(activityService.getPkCompetitionData(startDate, endDate, getSupplier().getPkey()));
    }

}
