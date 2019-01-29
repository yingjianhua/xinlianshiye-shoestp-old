package irille.Service.Manage.O2O.Imp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OJoinInfoDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Dao.Old.O2O.O2O_ActivityInsDAO;
import irille.Dao.Old.O2O.O2O_JoinInfoInsDAO;
import irille.Dao.Old.O2O.O2O_PrivateExpoPdtInsDAO;
import irille.Dao.Old.O2O.O2O_ProductInsDAO;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.pub.Log;
import irille.pub.tb.IEnumOpt;
import irille.pub.util.GetValue;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountryDAO;
import irille.shop.usr.UsrSupplier;
import irille.view.O2O.O2OActivityInfoView;
import irille.view.O2O.O2OActivityPdtInfoView;
import irille.view.O2O.O2OManageActivityListView;
import irille.view.Page;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 15:59
 */
public class O2OPdtServerImp implements IO2OPdtServer {
    private static final Log LOG = new Log(O2OPdtServerImp.class);
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

    @Inject
    private O2OJoinInfoDao o2OJoinInfoDao;

    @Override
    public Page getO2OActivityList(UsrSupplier supplier, Integer start, Integer limit, Date startDate, Date endDate, String keyword,Integer status, int supId, Integer countryId) {
        List<O2OManageActivityListView> result = new ArrayList<>();
        if(null != keyword)
            keyword = "%" + keyword + "%";
        for (Map<String, Object> stringObjectMap :
                o2OProductDao.getO2OActivityList(
                        start, limit, startDate, endDate, keyword,status, supId, countryId)) {
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
            Integer countryPkey = jsonObject.get("countryId").getAsInt();
            o2OManageActivityListView.setCountryId(countryPkey);
            PltCountryDAO countryDAO = new PltCountryDAO();
            try{
                o2OManageActivityListView.setCountry(countryDAO.loadById(countryPkey).getName(PltConfigDAO.supplierLanguage(supplier)));
            }catch (JSONException e){
                e.printStackTrace();
            }

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
                o2OProductDao.getO2OActivityListCount(supId,startDate, endDate, keyword,status,countryId)
        );
    }


    @Override
    public Page getO2OActivityInfo(Integer start, Integer limit, Integer id, Integer pkey) {
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

    @Override
    public O2OActivityInfoView load(UsrSupplier supplier,Integer pkey) {
        O2O_Activity activity = o2OActivityDao.getActivityInfoById(pkey);
        O2O_JoinInfo joinInfo = o2OJoinInfoDao.findByActivityAndSupplier(pkey,supplier.getPkey());
        List<Map<String,Object>> products = null;
        if(null != joinInfo){
            products = o2OProductDao.findAllByJoinInfoId(joinInfo.getPkey());
        }


        O2OActivityInfoView view = new O2OActivityInfoView();
        view.setId(activity.getPkey());
        view.setTitle(activity.getName());
        // 获取国家
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject =
                jsonParser
                        .parse(
                                String.valueOf(
                                        activity.getAddress()))
                        .getAsJsonObject();
        Integer countryPkey = jsonObject.get("countryId").getAsInt();
        view.setCountryId(countryPkey);
        PltCountryDAO countryDAO = new PltCountryDAO();
        try{
            view.setCountry(countryDAO.loadById(countryPkey).getName(PltConfigDAO.supplierLanguage(supplier)));
        }catch (JSONException e){
            e.printStackTrace();
        }
        view.setAddress(jsonObject.get("info").getAsString());
        view.setStartDate(activity.getStartDate());
        view.setEndDate(activity.getEndDate());
        view.setStatus(activity.gtStatus().getLine().getName());
        view.setCat(activity.getActivityCat());
        view.setCategory(activity.gtActivityCat().getName());
        view.setRules(activity.getRules());
        if(null != joinInfo){
            view.setTel(joinInfo.getTel());
            view.setName(joinInfo.getName());
        }
        if(null != products){
            view.setItems(products.stream().map(bean->{
                O2OActivityPdtInfoView v = new O2OActivityPdtInfoView();
                v.setId(GetValue.get(bean, O2O_Product.T.PKEY, Integer.class, -1));
                v.setName(GetValue.get(bean, PdtProduct.T.NAME, String.class, null));
                v.setPicture(GetValue.get(bean, PdtProduct.T.PICTURE, String.class, null));
                v.setCode(GetValue.get(bean, PdtProduct.T.CODE, String.class, null));
                v.setPrice(GetValue.get(bean, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
                v.setMin_oq(GetValue.get(bean, O2O_Product.T.MIN_OQ, Integer.class, 0));
                v.setRemark(GetValue.get(bean, O2O_Product.T.REMARK, String.class, null));
                v.setStatus(GetValue.get(bean, O2O_Product.T.STATUS, Byte.class, (byte) 0));
                v.setAppr(GetValue.get(bean,O2O_Product.T.VERIFY_STATUS,Byte.class,(byte)0));
                return v;
            }).collect(Collectors.toList()));
        }else{
            view.setItems(new ArrayList<>());
        }
        return view;
    }

    @Override
    public void lowerAndUpper(Integer pkey, String reason, O2O_ProductStatus status) {
        if(StringUtils.isBlank(reason) && status.equals(O2O_ProductStatus.OFF)){
            throw LOG.err("noReason","请输入下架理由");
        }
        O2O_Product o2O_product = o2OProductDao.findByPkey(pkey);
        if(null == o2O_product){
            throw LOG.err("noEntity","o2o商品不存在");
        }
        o2O_product.setRemark(reason);
        o2O_product.setStatus(O2O_ProductStatus.WAITOFF.getLine().getKey());
        o2O_product.upd();
    }

    @Override
    public List<O2OActivityPdtInfoView> listAllGeneral(UsrSupplier supplier) {
        return o2OProductDao.findAllGeneralByIsVerifyAndStateAndSupplier(supplier).stream().map(bean->{
            O2OActivityPdtInfoView view = new O2OActivityPdtInfoView();
            view.setId(GetValue.get(bean,PdtProduct.T.PKEY,Integer.class,null));
            view.setCode(GetValue.get(bean,PdtProduct.T.CODE,String.class,""));
            view.setName(GetValue.get(bean,"pdtName",String.class,""));
            view.setMin_oq(GetValue.get(bean,PdtProduct.T.MIN_OQ,Integer.class,0));
            view.setPrice(GetValue.get(bean,PdtProduct.T.CUR_PRICE,BigDecimal.class,BigDecimal.ZERO));
            view.setPicture(GetValue.get(bean,PdtProduct.T.PICTURE,String.class,""));
            view.setSupplier(GetValue.get(bean,"supName",String.class,""));
            return view;
        }).collect(Collectors.toList());
    }
}
