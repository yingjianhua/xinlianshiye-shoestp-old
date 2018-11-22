package irille.Dao;

import irille.Entity.Activity.ActivityInfo;
import irille.pub.bean.query.BeanQuery;
import irille.view.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 13:51
 */
public class ActivitySignInDao {

    public Page getAllSignInInfo(int start, int limit, int country, String keyword) {
        BeanQuery query = new BeanQuery();
        query.limit(start, limit);
        if (country > 0) {
            query.WHERE(ActivityInfo.T.COUNTRY, "=?", country);
        }
        if (keyword != null) {
            query.WHERE(ActivityInfo.T.INQUIRY, "like ?", keyword);
        }
        List list = query.SELECT(
                ActivityInfo.class
        ).FROM(ActivityInfo.class).queryList(ActivityInfo.class);
        return new Page(list, start, limit, query.queryCount());
    }

    public ActivityInfo getSignInInfo(int id) {
        return ActivityInfo.load(ActivityInfo.class,id);
    }
}
