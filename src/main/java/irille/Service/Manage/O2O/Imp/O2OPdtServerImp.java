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
import irille.pub.tb.IEnumOpt;
import irille.pub.util.GetValue;
import irille.view.O2O.O2OManageActivityListView;
import irille.view.Page;
import org.json.JSONException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 15:59
 */
public class O2OPdtServerImp implements IO2OPdtServer {

    @Inject
    private O2O_ActivityInsDAO o2OActivityInsDAO;
    @Inject
    private O2O_ProductInsDAO o2OProductInsDAO;
    @Inject
    private O2O_PrivateExpoPdtInsDAO o2OPrivateExpoPdtInsDAO;
    @Inject
    private O2O_JoinInfoInsDAO o2OJoinInfoInsDAO;
    @Inject
    private O2OProductDao o2OProductDao;
    @Inject
    private O2OActivityDao o2OActivityDao;

    @Override
    public Page getO2OActivityList(Integer start, Integer limit, Date startDate, Date endDate, String keyword, int supId, Integer countryId) {
        List<O2OManageActivityListView> result = new ArrayList<>();
        keyword = "%" + keyword + "%";
        for (Map<String, Object> stringObjectMap :
                o2OProductDao.getO2OActivityList(
                        start, limit, startDate, endDate, keyword, supId, countryId)) {
            O2OManageActivityListView o2OManageActivityListView = new O2OManageActivityListView();
            o2OManageActivityListView.setId(
                    GetValue.get(stringObjectMap, O2O_Activity.T.PKEY, Integer.class, -1));
            o2OManageActivityListView.setTitle(
                    GetValue.get(stringObjectMap, O2O_Activity.T.NAME, String.class, "NULL"));
            o2OManageActivityListView.setCatId(
                    GetValue.get(stringObjectMap, O2O_Activity.T.ACTIVITY_CAT, Integer.class, -1));
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
                    ((IEnumOpt) O2O_ActivityStatus.DEFAULT
                            .getLine()
                            .get(GetValue.get(stringObjectMap, O2O_Activity.T.STATUS, Byte.class, (byte) 0))).getLine().getName()
            );
            result.add(o2OManageActivityListView);
        }
        return new Page(
                result,
                start,
                limit,
                o2OProductDao.getO2OActivityListCount(startDate, endDate, keyword)
        );
    }


    @Override
    public Page getO2OActivityInfo(Integer start, Integer limit, Long id, Integer pkey) {
        O2O_Activity o2O_activity = o2OActivityDao.getActivityInfoById(id);
        try {
            o2O_activity.gtAddress().get("info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(o2O_activity);
        List pdtresult = o2OProductDao.getO2OActivityPdtList(start, limit, id, pkey);
        System.out.println(pdtresult);
        return null;
    }
}
