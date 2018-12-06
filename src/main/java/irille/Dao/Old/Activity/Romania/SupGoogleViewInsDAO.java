package irille.Dao.Old.Activity.Romania;

import irille.Entity.Activity.SupGoogleView;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/**
 * Pk大赛 商家谷歌数据视图 保存表
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 11:21
 */
public class SupGoogleViewInsDAO extends IduIns<SupGoogleViewInsDAO, SupGoogleView> {
    @Override
    public void before() {
        getB().setCreatedTime(Env.getSystemTime());
    }
}
