package irille.Service.Manage.Activity.Imp;

import irille.Dao.ActivitySignInDao;
import irille.Dao.Old.ActivitySignIn.ActivitySignInDelDao;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsDao;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsGetDao;
import irille.Entity.Activity.ActivityInfo;
import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.view.ActivitySigninInfo.ActivitySignInInfoView;
import irille.view.Page;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 11:53
 */
public class ActivitySignInInfoManageServiceImp implements IActivitySignInInfoManageService {

    @Inject
    private ActivitySignInsDao activitySignInsDao;
    @Inject
    private ActivitySignInDelDao activitySignInDelDao;

    @Inject
    private ActivitySignInDao activitySignInDao;
    @Inject
    private ActivitySignInsGetDao activitySignInsGetDao;

    @Override
    public int ins(ActivitySignInInfoView activitySignInInfoView) {
        ActivityInfo entity = new ActivityInfo();
        entity.setName(activitySignInInfoView.getName());
        entity.setCountry(activitySignInInfoView.getCountry());
        entity.setTel(activitySignInInfoView.getTel());
        entity.setEmail(activitySignInInfoView.getEmail());
        entity.setInquiry(activitySignInInfoView.getInquiry());
        activitySignInsDao.setB(entity).commit();
        return 1;
    }

    @Override
    public int del(int id) {
        activitySignInDelDao.load(id).del();
        return 1;
    }

    @Override
    public Page getAllSignInInfo(int start, int limit, int country, String keyword) {
        if (start < 0) {
            start = 0;
        }
        if (limit <= 0) {
            limit = 20;
        }
        if (keyword != null) {
            if (keyword.length() < 1)
                keyword = null;
            else
                keyword = "%" + keyword + "%";
        }
        Page resultSet = activitySignInDao.getAllSignInInfo(start, limit, country, keyword);
        List result = new ArrayList();
        for (Object object : resultSet.getItems()) {
            ActivityInfo activitySignInInfo = (ActivityInfo) object;
            ActivitySignInInfoView activitySignInInfoView = new ActivitySignInInfoView();
            activitySignInInfoView.setId(activitySignInInfo.getPkey());
            activitySignInInfoView.setName(activitySignInInfo.getName());
            activitySignInInfoView.setCountry(activitySignInInfo.getCountry());
            activitySignInInfoView.setTel(activitySignInInfo.getTel());
            activitySignInInfoView.setEmail(activitySignInInfo.getEmail());
            if (activitySignInInfo.getInquiry() != null && activitySignInInfo.getInquiry().length() > 200) {
                activitySignInInfoView.setInquiry(activitySignInInfo.getInquiry().substring(0, 199));
            } else {
                activitySignInInfoView.setInquiry(activitySignInInfo.getInquiry());
            }
            result.add(activitySignInInfoView);
        }
        resultSet.setItems(result);
        return resultSet;
    }

    @Override
    public ActivitySignInInfoView getSignInInfo(int id) {
        ActivityInfo activitySignInInfo = activitySignInDao.getSignInInfo(id);
        ActivitySignInInfoView activitySignInInfoView = new ActivitySignInInfoView();
        activitySignInInfoView.setId(activitySignInInfo.getPkey());
        activitySignInInfoView.setName(activitySignInInfo.getName());
        activitySignInInfoView.setCountry(activitySignInInfo.getCountry());
        activitySignInInfoView.setTel(activitySignInInfo.getTel());
        activitySignInInfoView.setEmail(activitySignInInfo.getEmail());
        activitySignInInfoView.setInquiry(activitySignInInfo.getInquiry());
        return activitySignInInfoView;
    }

    @Override
    public Page getActivitySignInsList(Integer start, Integer limit) {
        return activitySignInsGetDao.getPKContestList(start,limit);
    }
}
