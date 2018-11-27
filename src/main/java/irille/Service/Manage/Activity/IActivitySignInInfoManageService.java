package irille.Service.Manage.Activity;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.Activity.Imp.ActivitySignInInfoManageServiceImp;
import irille.view.ActivitySignInsView;
import irille.view.ActivitySigninInfo.ActivitySignInInfoView;
import irille.view.Page;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 11:53
 */
@ImplementedBy(ActivitySignInInfoManageServiceImp.class)
public interface IActivitySignInInfoManageService {
    int ins(ActivitySignInInfoView activitySignInInfoView);

    int del(int id);

    /**
     * @Description: 获取所有列表
     * @date 2018/11/21 9:58
     * @author lijie@shoestp.cn
     */
    Page getAllSignInInfo(int start, int limit, int country, String keyword);

    /**
     * @Description: 获取单条信息
     * @date 2018/11/21 9:58
     * @author lijie@shoestp.cn
     */
    ActivitySignInInfoView getSignInInfo(int id);

    /**
     @Description: 获取报名列表
      * @date 2018/11/23 15:58
     * @author zjl
     */
    Page getActivitySignInsList(Integer start, Integer limit);
}
