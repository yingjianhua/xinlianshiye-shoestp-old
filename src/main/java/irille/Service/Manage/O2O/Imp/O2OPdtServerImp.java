package irille.Service.Manage.O2O.Imp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Dao.Old.O2O.O2O_ActivityInsDAO;
import irille.Dao.Old.O2O.O2O_JoinInfoInsDAO;
import irille.Dao.Old.O2O.O2O_PrivateExpoPdtInsDAO;
import irille.Dao.Old.O2O.O2O_ProductInsDAO;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.pub.Page;
import irille.pub.idu.IduPage;
import irille.pub.util.GetValue;
import irille.view.Manage.O2O.O2OManageActivityListView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/** Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 15:59 */
public class O2OPdtServerImp implements IO2OPdtServer {

  @Inject private O2O_ActivityInsDAO o2OActivityInsDAO;
  @Inject private O2O_ProductInsDAO o2OProductInsDAO;
  @Inject private O2O_PrivateExpoPdtInsDAO o2OPrivateExpoPdtInsDAO;
  @Inject private O2O_JoinInfoInsDAO o2OJoinInfoInsDAO;
  @Inject private O2OProductDao o2OProductDao;
  @Inject private O2OActivityDao o2OActivityDao;

  @Override
  public Page getO2OActivityList(IduPage page, Date startDate, Date endDate, String keyword) {
    List<O2OManageActivityListView> result = new ArrayList<>();
    for (Map<String, Object> stringObjectMap :
        o2OProductDao.getO2OActivityList(
            page.getStart(), page.getLimit(), startDate, endDate, keyword)) {
      O2OManageActivityListView o2OManageActivityListView = new O2OManageActivityListView();
      o2OManageActivityListView.setId(
          GetValue.get(stringObjectMap, O2O_Activity.T.PKEY, Integer.class, -1));
      o2OManageActivityListView.setTitle(
          GetValue.get(stringObjectMap, O2O_Activity.T.NAME, String.class, "NULL"));
      o2OManageActivityListView.setEnd_date(
          GetValue.get(stringObjectMap, O2O_Activity.T.ACTIVITY_CAT, Date.class, null));
      o2OManageActivityListView.setStart_date(
          GetValue.get(stringObjectMap, O2O_Activity.T.START_DATE, Date.class, null));
      o2OManageActivityListView.setEnd_date(
          GetValue.get(stringObjectMap, O2O_Activity.T.END_DATE, Date.class, null));
      // 获取国家
      JsonParser jsonParser = new JsonParser();
      JsonObject jsonObject =
          jsonParser
              .parse(
                  String.valueOf(
                      stringObjectMap.get(O2O_Activity.T.ADDRESS.getFld().getCodeSqlField())))
              .getAsJsonObject();
      o2OManageActivityListView.setCountryId(jsonObject.get("countryId").getAsInt());
      // 获取状态
      o2OManageActivityListView.setStatus(
          O2O_ActivityStatus.DEFAULT
              .getLine()
              .get(GetValue.get(stringObjectMap, O2O_Activity.T.STATUS, Integer.class, 0))
              .name());
      result.add(o2OManageActivityListView);
    }
    return new Page(
        page.getStart(),
        page.getLimit(),
        o2OProductDao.getO2OActivityListCount(startDate, endDate, keyword),
        result);
  }

  @Override
  public Page getO2OActivityInfo(IduPage page, Long id) {
    O2O_Activity o2O_activity = o2OActivityDao.getActivityInfoById(id);
    List Pdtresult = o2OProductDao.getO2OActivityPdtList(page.getStart(), page.getLimit(), id);
    return null;
  }
}
