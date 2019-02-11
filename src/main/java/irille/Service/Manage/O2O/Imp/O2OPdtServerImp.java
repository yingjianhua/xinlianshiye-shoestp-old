package irille.Service.Manage.O2O.Imp;

import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OJoinInfoDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Dao.Old.O2O.O2O_ActivityInsDAO;
import irille.Dao.Old.O2O.O2O_JoinInfoInsDAO;
import irille.Dao.Old.O2O.O2O_PrivateExpoPdtInsDAO;
import irille.Dao.Old.O2O.O2O_ProductInsDAO;
import irille.Dao.PdtCatDao;
import irille.Dao.PdtProductDao;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.pub.Log;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.O2O.O2OActivityInfoView;
import irille.view.O2O.O2OActivityPdtInfoView;
import irille.view.O2O.O2OManageActivityListView;
import irille.view.Page;
import irille.view.v2.Pdt.PdtNewPdtInfo;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Inject
    private PdtCatDao pdtCatDao;

    @Inject
    private PdtProductDao pdtProductDao;

    /**
     * 获取O2O商品数据
     */
    public List<PdtNewPdtInfo> O2OList(UsrPurchase purchase, Integer start, Integer limit) {
        List<PdtNewPdtInfo> items = o2OProductDao.o2oList(purchase, start, limit).stream().map(pdt -> {
            PdtNewPdtInfo item = new PdtNewPdtInfo();
            item.setFavorite(null == GetValue.get(pdt, "ismyfavorite", Boolean.class, null) ? false : GetValue.get(pdt, "ismyfavorite", Boolean.class, null));
            item.setImage(GetValue.getFirstImage(GetValue.get(pdt, PdtProduct.T.PICTURE, String.class, null)));
            Long pdtPkey = Long.valueOf(String.valueOf(GetValue.get(pdt, PdtProduct.T.PKEY, Integer.class, -1)));
            item.setId(pdtPkey);
            item.setMin_order(GetValue.get(pdt, O2O_Product.T.MIN_OQ, Integer.class, -1));
            item.setPrice(GetValue.get(pdt, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
            String name = GetValue.get(pdt, PdtProduct.T.NAME, String.class, "");
            item.setTitle(name);
            item.setRewrite(SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(pdtPkey)), name));
            return item;
        }).collect(Collectors.toList());

        return items;
    }

    @Override
    public List<PdtNewPdtInfo> O2OPrivateList(UsrPurchase purchase, int start, int limit) {
        List<PdtNewPdtInfo> items = o2OProductDao.getPrivateExpoPdtList( start, limit).stream().map(pdt -> {
            PdtNewPdtInfo item = new PdtNewPdtInfo();
            item.setFavorite(null == GetValue.get(pdt, "ismyfavorite", Boolean.class, null) ? false : GetValue.get(pdt, "ismyfavorite", Boolean.class, null));
            item.setImage(GetValue.getFirstImage(GetValue.get(pdt, PdtProduct.T.PICTURE, String.class, null)));
            Long pdtPkey = Long.valueOf(String.valueOf(GetValue.get(pdt, PdtProduct.T.PKEY, Integer.class, -1)));
            item.setId(pdtPkey);
            item.setMin_order(GetValue.get(pdt, O2O_Product.T.MIN_OQ, Integer.class, -1));
            item.setPrice(GetValue.get(pdt, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
            String name = GetValue.get(pdt, PdtProduct.T.NAME, String.class, "");
            item.setTitle(name);
            item.setRewrite(SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(pdtPkey)), name));
            return item;
        }).collect(Collectors.toList());
//
        return items;
    }

    @Override
    public Page getO2OActivityList(UsrSupplier supplier, Integer start, Integer limit, Date startDate, Date endDate, String keyword, Integer status, int supId, Integer countryId) {
        List<O2OManageActivityListView> result = new ArrayList<>();
        if (null != keyword)
            keyword = "%" + keyword + "%";
        for (Map<String, Object> stringObjectMap :
                o2OProductDao.getO2OActivityList(
                        start, limit, startDate, endDate, keyword, status, supId, countryId)) {
            Date sDate = GetValue.get(stringObjectMap, O2O_Activity.T.START_DATE, Date.class, null);
            Date eDate = GetValue.get(stringObjectMap, O2O_Activity.T.END_DATE, Date.class, null);
            O2OManageActivityListView o2OManageActivityListView = new O2OManageActivityListView();
            o2OManageActivityListView.setId(
                    GetValue.get(stringObjectMap, O2O_Activity.T.PKEY, Integer.class, -1));
            o2OManageActivityListView.setTitle(
                    GetValue.get(stringObjectMap, O2O_Activity.T.NAME, String.class, "NULL"));
            o2OManageActivityListView.setStart_date(sDate);
            o2OManageActivityListView.setEnd_date(eDate);
            o2OManageActivityListView.setAddress(GetValue.get(stringObjectMap, "mapName", String.class, ""));
            // 获取状态
//            o2OManageActivityListView.setStatus(
//                    ((IEnumOpt) O2O_ActivityStatus.DEFAULT
//                            .getLine()
//                            .get(GetValue.get(stringObjectMap, O2O_Activity.T.STATUS, Byte.class, (byte) 0))).getLine().getName()
//            );
            //TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
            Date now = new Date();
            if(sDate.after(now)){
                o2OManageActivityListView.setStatus(String.valueOf(O2O_ActivityStatus.TOBEGIN.getLine().getKey()));
            }else if(sDate.before(now) && eDate.after(now)){
                o2OManageActivityListView.setStatus(String.valueOf(O2O_ActivityStatus.ACTIVITY.getLine().getKey()));
            }else if(eDate.before(now)){
                o2OManageActivityListView.setStatus(String.valueOf(O2O_ActivityStatus.END.getLine().getKey()));
            }
            result.add(o2OManageActivityListView);
        }
        return new Page(
                result,
                start,
                limit,
                o2OProductDao.getO2OActivityListCount(supId, startDate, endDate, keyword, status, countryId)
        );
    }


    @Override
    public Page getO2OActivityInfo(Integer start, Integer limit, Integer id, Integer pkey) {
        O2O_Activity o2O_activity = o2OActivityDao.getActivityInfoById(id);
        List pdtresult = o2OProductDao.getO2OActivityPdtList(start, limit, id, pkey);
        return null;
    }

    @Override
    public O2OActivityInfoView load(UsrSupplier supplier, Integer pkey) {
        O2O_Activity activity = o2OActivityDao.getActivityInfoById(pkey);
        O2O_JoinInfo joinInfo = o2OJoinInfoDao.findByActivityAndSupplier(pkey, supplier.getPkey());
        List<Map<String, Object>> products = null;
        if (null != joinInfo) {
            products = o2OProductDao.findAllByJoinInfoId(joinInfo.getPkey());
        }

        Map<Integer, String> cat_pkey_name_map = new HashMap<>();
        O2OActivityInfoView view = new O2OActivityInfoView();
        view.setId(activity.getPkey());
        view.setTitle(activity.getName());
        view.setAddress(activity.gtAddress().getName());
        view.setStartDate(activity.getStartDate());
        view.setEndDate(activity.getEndDate());
        view.setStatus(activity.gtStatus().getLine().getName());
        List<Map<String, Object>> activityCat = activitCat_pkey2Name(activity.getActivityCat(), cat_pkey_name_map);
        view.setCategories(activityCat);
        view.setRules(activity.getRules());
        if (null != joinInfo) {
            view.setTel(joinInfo.getTel());
            view.setName(joinInfo.getName());
        }
        if (null != products) {
            view.setItems(products.stream().map(bean -> {
                O2OActivityPdtInfoView v = new O2OActivityPdtInfoView();
                v.setId(GetValue.get(bean, O2O_Product.T.PKEY, Integer.class, -1));
                v.setName(GetValue.get(bean, PdtProduct.T.NAME, String.class, null));
                v.setPicture(GetValue.get(bean, PdtProduct.T.PICTURE, String.class, null));
                v.setCode(GetValue.get(bean, PdtProduct.T.CODE, String.class, null));
                v.setPrice(GetValue.get(bean, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
                v.setMin_oq(GetValue.get(bean, O2O_Product.T.MIN_OQ, Integer.class, 0));
                v.setRemark(GetValue.get(bean, O2O_Product.T.REMARK, String.class, null));
                v.setStatus(GetValue.get(bean, O2O_Product.T.STATUS, Byte.class, (byte) 0));
                v.setAppr(GetValue.get(bean, O2O_Product.T.VERIFY_STATUS, Byte.class, (byte) 0));
                v.setMessage(GetValue.get(bean, O2O_Product.T.MESSAGE, String.class, null));
                return v;
            }).collect(Collectors.toList()));
        } else {
            view.setItems(new ArrayList<>());
        }
        return view;
    }

    /**
     * 把以逗号分隔的产品分类pkey,转换成以分类名称分隔的字符串
     *
     * @param activityCat       以逗号分隔的分类的pkey
     * @param cat_pkey_name_map pkey和分类名称键值对, 若不存在相应数据 则从数据库中查询后 放入map
     * @return 以逗号分隔的分类名称
     * @author Jianhua Ying
     */
    private List<Map<String, Object>> activitCat_pkey2Name(String activityCat, Map<Integer, String> cat_pkey_name_map) {
        List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();

        if (activityCat == null || activityCat.trim().equals("")) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (FldLanguage.Language language : FldLanguage.Language.values()) {
                map.put(language.name(), "通用");
            }
            array.add(map);
            return array;
        } else {
            Stream.of(activityCat.split(",")).forEach(spkey -> {
                Integer pkey = Integer.parseInt(spkey);
                Map<String, Object> map = new HashMap<String, Object>();
                if (cat_pkey_name_map != null && cat_pkey_name_map.containsKey(pkey)) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(cat_pkey_name_map.get(pkey));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (null != json) {
                        Iterator<String> ite = json.keys();
                        while (ite.hasNext()) {
                            String key = ite.next();
                            try {
                                map.put(key, json.get(key));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        array.add(map);
                    }

                } else {
                    PdtCat cat = pdtCatDao.findById(pkey);
                    if (cat_pkey_name_map != null)
                        cat_pkey_name_map.put(pkey, cat.getName());
                    JSONObject json = null;
                    try {
                        json = new JSONObject(cat.getName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (null != json) {
                        Iterator<String> ite = json.keys();
                        while (ite.hasNext()) {
                            String key = ite.next();
                            try {
                                map.put(key, json.get(key));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        array.add(map);
                    }
                }
            });
            return array;
        }
    }

    @Override
    public void lowerAndUpper(Integer pkey, String reason, O2O_ProductStatus status) {
        if (StringUtils.isBlank(reason) && status.equals(O2O_ProductStatus.OFF)) {
            throw LOG.err("noReason", "请输入下架理由");
        }
        O2O_Product o2O_product = o2OProductDao.findByPkey(pkey);
        if (null == o2O_product) {
            throw LOG.err("noEntity", "o2o商品不存在");
        }
        o2O_product.setRemark(reason);
        o2O_product.setMessage("处理中");
        o2O_product.setStatus(O2O_ProductStatus.WAITOFF.getLine().getKey());
        o2O_product.upd();
    }

    @Override
    public void appr(Integer pkey, String reason, O2O_ProductStatus status) {
        O2O_Product product = o2OProductDao.findByPkey(pkey);
        product.setVerifyStatus(status.getLine().getKey());
        if (status.equals(O2O_ProductStatus.Failed)) {
            if (null == reason && "".equals(reason.trim())) {
                throw new WebMessageException(ReturnCode.failure, "请输入拒绝理由");
            }

        }
    }

    @Override
    public List<O2OActivityPdtInfoView> listAllGeneral(UsrSupplier supplier, Integer activity) {
        //TODO
        O2O_Activity activityEntity = o2OActivityDao.getActivityInfoById(activity);
        if (null == activityEntity)
            throw LOG.err("noActivity", "活动不存在");
        List<Integer> listAll = new ArrayList<>();
        if (null != activityEntity.getActivityCat()) {
            List<Integer> cats = Arrays.asList(activityEntity.getActivityCat().split(",")).stream().map(str -> {
                return Integer.valueOf(str);
            }).collect(Collectors.toList());

            cats.forEach(cat -> {
                listAll.addAll(pdtProductDao.getCatsNodeByCatId(cat));
            });
        }


        return o2OProductDao.findAllGeneralByIsVerifyAndStateAndSupplier(supplier, listAll).stream().map(bean -> {
            O2OActivityPdtInfoView view = new O2OActivityPdtInfoView();
            view.setId(GetValue.get(bean, PdtProduct.T.PKEY, Integer.class, null));
            view.setCode(GetValue.get(bean, PdtProduct.T.CODE, String.class, ""));
            view.setName(GetValue.get(bean, "pdtName", String.class, ""));
            view.setMin_oq(GetValue.get(bean, PdtProduct.T.MIN_OQ, Integer.class, 0));
            view.setPrice(GetValue.get(bean, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO));
            view.setPicture(GetValue.get(bean, PdtProduct.T.PICTURE, String.class, ""));
            view.setSupplier(GetValue.get(bean, "supName", String.class, ""));
            return view;
        }).collect(Collectors.toList());
    }


}
