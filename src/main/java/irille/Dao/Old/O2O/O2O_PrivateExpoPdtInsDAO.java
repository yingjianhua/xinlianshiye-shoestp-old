package irille.Dao.Old.O2O;

import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
/***
 * 私人展会
 * @author Lijie<HelloBox@outlook.com>
 * @date 2019/1/26 15:58
 */
public class O2O_PrivateExpoPdtInsDAO extends IduIns<O2O_PrivateExpoPdtInsDAO, O2O_PrivateExpoPdt> {
    @Override
    public void before() {
        getB().setCreateTime(Env.getSystemTime());
        super.before();
    }
}
