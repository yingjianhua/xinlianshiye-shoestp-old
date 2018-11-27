package irille.sellerAction.as;

import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.as.inf.IActivitySignInsAction;
import irille.Entity.As.PKContest;
import javax.inject.Inject;
import java.io.IOException;

public class ActivitySignInsAction extends SellerAction<PKContest> implements IActivitySignInsAction {
    @Inject
    private IActivitySignInInfoManageService activitySignInInfoManageService;

    public void getActivitySignInsList() throws IOException {
        write(activitySignInInfoManageService.getActivitySignInsList(getStart(),getLimit()));
    }
}
