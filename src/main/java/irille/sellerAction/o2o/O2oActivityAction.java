package irille.sellerAction.o2o;

import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.sellerAction.SellerAction;
import irille.sellerAction.o2o.inf.IO2oActivityAction;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/28
 * Time: 9:53
 */
public class O2oActivityAction extends SellerAction<O2O_Activity> implements IO2oActivityAction {

    @Inject
    private IO2OPdtServer o2odtServer;


    @Setter
    private Date startDate;
    @Setter
    private Date endDate;
    @Setter
    private String keyword;
    @Setter
    private Integer countryId;
    @Setter
    private Long id;


    @Setter
    private int isMyjoin;

    public void getActivityList() throws IOException {
        if (getLimit() == 0) setLimit(10);
        if (getStart() < 0) setLimit(10);
        if (getLimit() == 0) setLimit(10);
        int supId = -1;
        if (isMyjoin < 2 || isMyjoin > -1) supId = isMyjoin == 0 ? -1 : getSupplier().getPkey();

        write(o2odtServer.getO2OActivityList(getStart(), getLimit(), startDate, endDate, keyword, supId, countryId));
    }

    @Override
    public void getActivityInfo() throws IOException {
        System.out.println(id);
        write(o2odtServer.getO2OActivityInfo(getStart(), getLimit(), id, getSupplier().getPkey()));
    }


}
