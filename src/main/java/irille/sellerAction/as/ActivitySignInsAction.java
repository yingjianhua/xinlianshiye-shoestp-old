package irille.sellerAction.as;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.As.PKContest;
import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.as.inf.IActivitySignInsAction;

public class ActivitySignInsAction extends SellerAction<PKContest>
    implements IActivitySignInsAction {
  @Inject private IActivitySignInInfoManageService activitySignInInfoManageService;

  public void getActivitySignInsList() throws IOException {
    write(activitySignInInfoManageService.getActivitySignInsList(getStart(), getLimit()));
  }
}
