package irille.Dao.Old.Activity.Romania;

import irille.Entity.Pk.PkCompetitionData;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 13:45
 */
public class PkCompetitionDataDAO extends IduIns<PkCompetitionDataDAO, PkCompetitionData> {
    @Override
    public void before() {
        getB().setCreatedtime(Env.getSystemTime());
    }
}
