package irille.platform.o2o;

import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.action.ActionBase;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/28
 * Time: 9:53
 */
public class O2oActivityAction extends ActionBase<O2O_Activity> {

    @Inject
    private IO2OPdtServer o2odtServer;

    @Override
    public Class beanClazz() {
        return O2O_Activity.class;
    }


}
