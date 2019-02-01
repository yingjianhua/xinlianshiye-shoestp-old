package irille.Dao.O2O;

import irille.Aops.Caches;
import irille.Entity.O2O.*;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity.T;
import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRole;
import irille.view.O2O.PdtSearchView;
import irille.view.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 12:50
 */
public class O2OProductDao {
    @Caches
    public List<?> getPrivateExpoPdtList(int start, int limit) {
        SQL sql = new SQL();
        sql.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.PICTURE,
                PdtProduct.T.NAME,
                O2O_PrivateExpoPdt.T.PRICE,
                O2O_PrivateExpoPdt.T.MIN_OQ,
                O2O_PrivateExpoPdt.T.STATUS,
                O2O_PrivateExpoPdt.T.VERIFY_STATUS,
                O2O_PrivateExpoPdt.T.MESSAGE)
                .LEFT_JOIN(PdtProduct.class, O2O_PrivateExpoPdt.T.PDT_ID, PdtProduct.T.PKEY)
                .WHERE(O2O_PrivateExpoPdt.T.STATUS, "=?", O2O_PrivateExpoPdtStatus.ON)
                .WHERE(O2O_PrivateExpoPdt.T.VERIFY_STATUS, "=?", O2O_PrivateExpoPdtStatus.PASS);
        return Query.sql(sql).queryMaps();
    }

    @Caches
    public List<Map<String,Object>> o2oList(UsrPurchase purchase, Integer start, Integer limit){
        SQL sql = new SQL();
        SQL childrenQuery = new SQL();
        if(null != purchase)
            childrenQuery
                .SELECT(UsrFavorites.T.PKEY)
                .FROM(UsrFavorites.class)
                .WHERE(UsrFavorites.T.PURCHASE, "=?", purchase.getPkey())
                .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY)
        ;
        sql.SELECT(PdtProduct.T.PKEY,
                    PdtProduct.T.NAME,
                    PdtProduct.T.PICTURE,
                    O2O_Product.T.PRICE,
                    O2O_Product.T.MIN_OQ);
        if(null != purchase)
            sql.SELECT(childrenQuery,"ismyfavorite");
            sql.FROM(O2O_Product.class)
                .LEFT_JOIN(PdtProduct.class,PdtProduct.T.PKEY,O2O_Product.T.PRODUCT_ID)
                    .LEFT_JOIN(O2O_Activity.class, T.PKEY,O2O_Product.T.ACTIVITY_ID)
                    //TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
                    .WHERE(T.START_DATE,"<?",new Date())
                    .WHERE(T.END_DATE,">?",new Date())
//                    .WHERE(T.STATUS,"=?",O2O_ActivityStatus.ACTIVITY)
                .WHERE(O2O_Product.T.VERIFY_STATUS,"=?", O2O_ProductStatus.PASS.getLine().getKey())
                .WHERE(O2O_Product.T.STATUS,"=?",O2O_ProductStatus.ON.getLine().getKey());
        sql.LIMIT(start,limit);
        return Query.sql(sql).queryMaps();
}

    public List<Map<String, Object>> getO2OActivityList(
            int start, int limit, Date startDate, Date endDate, String keyWord,Integer status, int supId, Integer countryId) {
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Activity.T.PKEY,
                O2O_Activity.T.NAME,
                O2O_Activity.T.ACTIVITY_CAT,
                O2O_Activity.T.START_DATE,
                O2O_Activity.T.END_DATE,
                O2O_Activity.T.STATUS
        ).SELECT(O2O_Map.T.NAME,"mapName")
                .FROM(O2O_Activity.class)
                .LEFT_JOIN(O2O_Map.class,O2O_Map.T.PKEY, T.ADDRESS)
                .WHERE(startDate != null, O2O_Activity.T.START_DATE, ">?", startDate)
                .WHERE(endDate != null, T.END_DATE, "<?", endDate)
                .WHERE(keyWord != null && keyWord.length() > 0, T.NAME, "like ?", keyWord)
                .WHERE(T.STATUS,"<>?", O2O_ActivityStatus.TOBEGIN.getLine().getKey());
//                .WHERE(status != null,T.STATUS,"=?",status)
        //TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
        Date now = new Date();
        if(null != status){
            if(status.equals(O2O_ActivityStatus.TOBEGIN.getLine().getKey())){
                //未开始
                sql.WHERE(T.START_DATE,">?",now);
            }else if(status.equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey())){
                //活动中
                sql.WHERE(T.START_DATE,"<?",now);
                sql.WHERE(T.END_DATE,">?",now);
            }else if(status.equals(O2O_ActivityStatus.END.getLine().getKey())){
                //结束
                sql.WHERE(T.END_DATE,"<?",now);
            }
        }

        sql.LIMIT(start, limit);
        if (supId > 0) {
            sql.WHERE(O2O_JoinInfo.T.SUPPLIER, "=?", supId);
            sql.LEFT_JOIN(O2O_JoinInfo.class, O2O_JoinInfo.T.ACTIVITY, T.PKEY);
        }
        if (countryId != null && countryId > 0) {
            sql.WHERE("address->'$.countryId'=?", countryId);
        }
        sql.ORDER_BY(T.UPDATED_TIME," DESC ");
        return Query.sql(sql).queryMaps();
    }

    public Integer getO2OActivityListCount(int supId,Date startDate, Date endDate, String keyWord,Integer status,Integer countryId) {
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Activity.T.STATUS)
                .FROM(O2O_Activity.class)
                .WHERE(startDate != null, O2O_Activity.T.START_DATE, ">?", startDate)
                .WHERE(endDate != null, T.END_DATE, "<?", endDate)
                .WHERE(keyWord != null && keyWord.length() > 0, T.NAME, "like ?", keyWord);
        // 活动状态//TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
        Date now = new Date();
        if(null != status){
            if(status.equals(O2O_ActivityStatus.TOBEGIN.getLine().getKey())){
                //未开始
                sql.WHERE(T.START_DATE,">?",now);
            }else if(status.equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey())){
                //活动中
                sql.WHERE(T.START_DATE,"<?",now);
                sql.WHERE(T.END_DATE,">?",now);
            }else if(status.equals(O2O_ActivityStatus.END.getLine().getKey())){
                //结束
                sql.WHERE(T.END_DATE,"<?",now);
            }
        }
//                .WHERE(status != null,T.STATUS,"=?",status);
        if (supId > 0) {
            sql.WHERE(O2O_JoinInfo.T.SUPPLIER, "=?", supId);
            sql.LEFT_JOIN(O2O_JoinInfo.class, O2O_JoinInfo.T.ACTIVITY, T.PKEY);
        }
        if (countryId != null && countryId > 0) {
            sql.WHERE("address->'$.countryId'=?", countryId);
        }
        return Query.sql(sql).queryCount();
    }

    public List<Map<String, Object>> getO2OActivityPdtList(int start, int limit, Integer id, Integer pkey) {
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Product.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.SKU,
                O2O_Product.T.PRICE,
                O2O_Product.T.MIN_OQ,
                O2O_Product.T.STATUS,
                O2O_Product.T.REMARK)
                .FROM(O2O_Product.class)
                .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, O2O_Product.T.PKEY)
                .LEFT_JOIN(O2O_JoinInfo.class, O2O_JoinInfo.T.PKEY, O2O_Product.T.JOIN_INFO_ID)
                .WHERE(O2O_JoinInfo.T.ACTIVITY, "=?", id)
                .WHERE(O2O_JoinInfo.T.SUPPLIER, "=?", pkey)
                .LIMIT(start, limit);
        return Query.sql(sql).queryMaps();
    }

    public List<Map<String,Object>> findAllByJoinInfoId(Integer infoId){
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Product.T.PKEY,
                PdtProduct.T.PICTURE,
                PdtProduct.T.NAME,
                PdtProduct.T.CODE,
                O2O_Product.T.PRICE,
                O2O_Product.T.MIN_OQ,
                O2O_Product.T.STATUS,
                O2O_Product.T.MESSAGE,
                O2O_Product.T.VERIFY_STATUS,
                O2O_Product.T.REMARK)
                .FROM(O2O_Product.class)
                .LEFT_JOIN(PdtProduct.class,PdtProduct.T.PKEY,O2O_Product.T.PRODUCT_ID)
                .WHERE(O2O_Product.T.JOIN_INFO_ID,"=?",infoId);
        return Query.sql(sql).queryMaps();
    }

    public O2O_Product findByPkey(Integer pkey){
        return Query.SELECT(O2O_Product.class).WHERE(O2O_Product.T.PKEY,"=?",pkey).query();
    }

    public List<Map<String,Object>> findAllGeneralByIsVerifyAndStateAndSupplier(UsrSupplier supplier,List<Integer> cats){
        String pkeys = "";
        for(int i=0;i<cats.size();i++){
            if(i != cats.size()-1){
                pkeys += cats.get(i) + ",";
            }else{
                pkeys += cats.get(i);
            }
        }
        SQL sql = new SQL();
        sql.SELECT(PdtProduct.T.PKEY,
                    PdtProduct.T.CODE,
                    PdtProduct.T.MIN_OQ,
                    PdtProduct.T.CUR_PRICE,
                    PdtProduct.T.PICTURE)
                .SELECT(PdtProduct.T.NAME,"pdtName")
                .SELECT(UsrSupplier.T.NAME,"supName")
                .FROM(PdtProduct.class)
                .LEFT_JOIN(UsrSupplier.class,UsrSupplier.T.PKEY,PdtProduct.T.SUPPLIER)
                .WHERE(!pkeys.equals(""),PdtProduct.T.CATEGORY," in("+pkeys+")")
                .WHERE(PdtProduct.T.SUPPLIER,"=?",supplier.getPkey())
                .WHERE(PdtProduct.T.IS_VERIFY,"=?", Sys.OYn.YES.getLine().getKey())
                .WHERE(PdtProduct.T.STATE,"=?",Pdt.OState.ON.getLine().getKey())
                .WHERE(PdtProduct.T.PRODUCT_TYPE,"=?",Pdt.OProductType.GENERAL.getLine().getKey());
        return Query.sql(sql).queryMaps();

    }
    
    /**
     * 统计活动下有多少参加的商品
     * 
     * @param activityId 活动的Id 不能为空
     * @return 参加活动的商品的数量
     * @author Jianhua Ying
     */
    public Integer countByActivity(@Nonnull Integer activityId) {
    	return Query.SELECT(O2O_Product.class).WHERE(O2O_Product.T.ACTIVITY_ID, "=?", activityId).queryCount();
    }


    public List<Map<String,Object>> enrollList(PdtSearchView search,Integer start,Integer limit){
        if(null == search){
            search = new PdtSearchView();
        }
        SQL sql = new SQL();
        sql.SELECT(T.NAME,
                O2O_Product.T.MIN_OQ,
                O2O_Product.T.PRICE,
                O2O_JoinInfo.T.Tel,
                O2O_Product.T.STATUS,
                O2O_Product.T.VERIFY_STATUS,
                O2O_Product.T.REMARK,
                O2O_Product.T.PKEY)
                .SELECT(PdtProduct.T.PKEY,"pdtPkey")
                .SELECT(O2O_Map.T.NAME,"mapName")
                .SELECT(PdtProduct.T.NAME,"pdtName")
                .SELECT(PdtCat.T.NAME,"catName")
                .SELECT(UsrSupplier.T.NAME,"supName")
                .SELECT(UsrSupplierRole.T.NAME,"roleName")
                .SELECT(O2O_JoinInfo.T.NAME,"joinInfo")
                .FROM(O2O_Product.class)
                .LEFT_JOIN(O2O_JoinInfo.class,O2O_JoinInfo.T.PKEY,O2O_Product.T.JOIN_INFO_ID)
                .LEFT_JOIN(O2O_Activity.class, T.PKEY,O2O_Product.T.ACTIVITY_ID)
                .LEFT_JOIN(O2O_Map.class,O2O_Map.T.PKEY, T.ADDRESS)
                .LEFT_JOIN(PdtProduct.class,PdtProduct.T.PKEY,O2O_Product.T.PRODUCT_ID)
                .LEFT_JOIN(PdtCat.class,PdtCat.T.PKEY,PdtProduct.T.CATEGORY)
                .LEFT_JOIN(UsrSupplier.class,UsrSupplier.T.PKEY,O2O_JoinInfo.T.SUPPLIER)
                .LEFT_JOIN(UsrSupplierRole.class,UsrSupplierRole.T.PKEY,UsrSupplier.T.ROLE);
            sql.WHERE(null != search.getActId(),O2O_Activity.T.PKEY,"= ?",search.getActId());
            sql.WHERE(null != search.getActivity(), T.NAME,"like ?","%"+search.getActivity()+"%");
            sql.WHERE(null != search.getCategory(),PdtCat.T.NAME,"like ?","%"+search.getCategory()+"%");
            sql.WHERE(null != search.getSupplier(),UsrSupplier.T.NAME,"like ? ","%"+search.getSupplier()+"%");
            sql.WHERE(null != search.getRole(),UsrSupplierRole.T.NAME,"like ?","%"+search.getRole()+"%");
            sql.WHERE(null != search.getArea(),O2O_Map.T.NAME,"like ?","%"+search.getArea()+"%");
            sql.WHERE(null != search.getStatus(),O2O_Product.T.VERIFY_STATUS,"=?",search.getStatus());
            sql.WHERE(null != search.getState(),O2O_Product.T.STATUS,"=?",search.getState());
            sql.ORDER_BY(O2O_Product.T.UPDATED_TIME,"DESC").LIMIT(start,limit);
        return Query.sql(sql).queryMaps();
    }

    public Integer countEnroll(PdtSearchView search){
        if(null == search){
            search = new PdtSearchView();
        }
        SQL sql = new SQL();
        sql.SELECT(O2O_Product.class).FROM(O2O_Product.class)
            .LEFT_JOIN(O2O_JoinInfo.class,O2O_JoinInfo.T.PKEY,O2O_Product.T.JOIN_INFO_ID)
            .LEFT_JOIN(O2O_Activity.class, T.PKEY,O2O_JoinInfo.T.ACTIVITY)
            .LEFT_JOIN(O2O_Map.class,O2O_Map.T.PKEY,O2O_JoinInfo.T.ACTIVITY)
            .LEFT_JOIN(PdtProduct.class,PdtProduct.T.PKEY,O2O_Product.T.PRODUCT_ID)
            .LEFT_JOIN(PdtCat.class,PdtCat.T.PKEY,PdtProduct.T.CATEGORY)
            .LEFT_JOIN(UsrSupplier.class,UsrSupplier.T.PKEY,O2O_JoinInfo.T.SUPPLIER)
            .LEFT_JOIN(UsrSupplierRole.class,UsrSupplierRole.T.PKEY,UsrSupplier.T.ROLE)
            .WHERE(null != search.getActId(),O2O_Product.T.PKEY,"=?",search.getActId())
            .WHERE(null != search.getActivity(), T.NAME,"like ?","%"+search.getActivity()+"%")
            .WHERE(null != search.getCategory(),PdtCat.T.NAME,"like ?","%"+search.getCategory()+"%")
            .WHERE(null != search.getSupplier(),UsrSupplier.T.NAME,"like ? ","%"+search.getSupplier()+"%")
            .WHERE(null != search.getRole(),UsrSupplierRole.T.NAME,"like ?","%"+search.getRole()+"%")
            .WHERE(null != search.getArea(),O2O_Map.T.NAME,"like ?","%"+search.getArea()+"%")
            .WHERE(null != search.getStatus(),O2O_Product.T.VERIFY_STATUS,"=?",search.getStatus());
        return Query.sql(sql).queryCount();
    }


    public O2O_Product findEarliestActByPdt_Pkey(Integer pdtId){
        SQL sql = new SQL();
        sql.SELECT(O2O_Product.class).FROM(O2O_Product.class)
                .LEFT_JOIN(O2O_Activity.class, T.PKEY,O2O_Product.T.ACTIVITY_ID)
                .WHERE(O2O_Product.T.PRODUCT_ID,"=?",pdtId);
        sql.WHERE(O2O_Product.T.VERIFY_STATUS,"=?",O2O_ProductStatus.PASS);
        sql.WHERE(O2O_Product.T.STATUS,"=?",O2O_ProductStatus.ON);
        //TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
        Date now = new Date();
        sql.WHERE(T.START_DATE,"<?",now);
        sql.WHERE(T.END_DATE,">?",now);
//        sql.WHERE(T.STATUS,"<>?",O2O_ActivityStatus.TOBEGIN);
        sql.ORDER_BY(O2O_Product.T.UPDATED_TIME," ASC ");
        List<O2O_Product> o2oPdts = Query.sql(sql).queryList(O2O_Product.class);
        if(o2oPdts.size()>0){
            return o2oPdts.get(0);
        }else{
            return null;
        }
    }

}
