package irille.sellerAction.activitys;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Dao.Old.Activity.Romania.SupGoogleViewDelDAO;
import irille.Dao.Old.Activity.Romania.SupGoogleViewInsDAO;
import irille.Dao.Old.Activity.Romania.SupGoogleViewUpdDAO;
import irille.Entity.Activity.ActivityInfo;
import irille.Entity.Activity.SupGoogleView;
import irille.Service.Activity.IActivityService;
import irille.Service.Manage.Activity.IActivitySignInInfoManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.activitys.Inf.IActivityAction;
import lombok.Getter;
import lombok.Setter;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/20 Time: 14:22 */
public class ActivityAction extends SellerAction<ActivityInfo> implements IActivityAction {

  @Inject private IActivitySignInInfoManageService activitySignInInfoManageService;

  @Inject private ObjectMapper objectMapper;
  @Inject private IActivityService activityService;

  @Inject private SupGoogleViewInsDAO supGoogleViewDAO;

  @Inject private SupGoogleViewDelDAO supGoogleViewDelDAO;

  @Inject private SupGoogleViewUpdDAO supGoogleViewUpdDAO;

  @Getter @Setter private int country;
  @Getter @Setter private String keyword;

  @Getter @Setter private Date startDate;
  @Getter @Setter private Date endDate;

  @Override
  public void list() throws Exception {
    write(
        activitySignInInfoManageService.getAllSignInInfo(
            getStart(), getLimit(), getCountry(), getKeyword()));
  }

  @Override
  public void load() throws Exception {
    write(activitySignInInfoManageService.getSignInInfo(Integer.valueOf(String.valueOf(getId()))));
  }

  @Override
  public void del() {
    activitySignInInfoManageService.del(Integer.valueOf(String.valueOf(getId())));
  }

  /**
   * @Description: 获取Pk大赛数据
   *
   * @date 2018/12/5 14:55
   * @author lijie@shoestp.cn
   */
  public void getPkData() throws IOException {

    write(
        activityService.getPkCompetitionData(
            startDate, endDate, getSupplier().getPkey(), getParams("type")));
  }

  public void saveGoogleViewId() throws IOException {
    Map map = objectMapper.readValue(getJsonBody(), HashMap.class);
    SupGoogleView supGoogleView = null;
    try {
      supGoogleView = SupGoogleView.loadUniqueSup_id(false, getSupplier().getPkey());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (supGoogleView == null) {
      supGoogleView = new SupGoogleView();
      supGoogleView.setViewid(String.valueOf(map.get("data")));
      supGoogleView.setSupid(getSupplier().getPkey());
      supGoogleViewDAO.setB(supGoogleView).commit();
    } else {
      supGoogleView.setViewid(String.valueOf(map.get("data")));
      if (supGoogleView.getViewid().length() < 1) {
        supGoogleViewDelDAO.setB(supGoogleView).commit();
      } else {
        supGoogleViewUpdDAO.setB(supGoogleView).commit();
      }
    }
    writeErr(1, null);
  }
}
