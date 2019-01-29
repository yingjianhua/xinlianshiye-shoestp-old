package irille.Dao.O2O;

import irille.Aops.Caches;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26 Time: 12:50
 */
public class O2OProductDao {
    @Caches
    public List getPrivateExpoPdtList(int start, int limit) {
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

    public List<Map<String, Object>> getO2OActivityList(
            int start, int limit, Date startDate, Date endDate, String keyWord,Integer status, int supId, Integer countryId) {
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Activity.T.PKEY,
                O2O_Activity.T.NAME,
                O2O_Activity.T.ACTIVITY_CAT,
                O2O_Activity.T.START_DATE,
                O2O_Activity.T.END_DATE,
                O2O_Activity.T.ADDRESS,
                O2O_Activity.T.STATUS
        )
                .FROM(O2O_Activity.class)
                .WHERE(startDate != null, O2O_Activity.T.START_DATE, ">?", startDate)
                .WHERE(endDate != null, T.END_DATE, "<?", endDate)
                .WHERE(keyWord != null && keyWord.length() > 0, T.NAME, "like ?", keyWord)
                .WHERE(status != null,T.STATUS,"=?",status)
                .WHERE(T.STATUS,"<>?", O2O_ActivityStatus.TOBEGIN.getLine().getKey())
                .LIMIT(start, limit);
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
                .WHERE(keyWord != null && keyWord.length() > 0, T.NAME, "like ?", keyWord)
                .WHERE(status != null,T.STATUS,"=?",status);
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

    public List<Map<String,Object>> findAllGeneralByIsVerifyAndStateAndSupplier(UsrSupplier supplier){
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
                .WHERE(PdtProduct.T.SUPPLIER,"=?",supplier.getPkey())
                .WHERE(PdtProduct.T.IS_VERIFY,"=?", Sys.OYn.YES.getLine().getKey())
                .WHERE(PdtProduct.T.STATE,"=?",Pdt.OState.ON.getLine().getKey())
                .WHERE(PdtProduct.T.PRODUCT_TYPE,"=?",Pdt.OProductType.GENERAL.getLine().getKey());
        return Query.sql(sql).queryMaps();

    }

}
