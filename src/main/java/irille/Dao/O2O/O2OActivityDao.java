package irille.Dao.O2O;

import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.pub.bean.Query;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 12:50
 */
public class O2OActivityDao {

    public O2O_Activity getActivityInfoById(Long id) {
        return Query.SELECT(O2O_Activity.class).WHERE(T.PKEY, "=?", id).query();
    }
}
