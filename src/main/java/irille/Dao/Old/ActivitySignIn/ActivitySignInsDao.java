package irille.Dao.Old.ActivitySignIn;

import irille.Entity.Activity.ActivityInfo;
import irille.pub.idu.IduIns;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 13:45
 */
public class ActivitySignInsDao extends IduIns<ActivitySignInsDao, ActivityInfo> {
    @Override
    public void before() {
        super.before();
        getB().setCreatedTime(new Date());
    }
}

