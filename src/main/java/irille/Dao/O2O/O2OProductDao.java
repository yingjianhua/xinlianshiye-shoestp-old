package irille.Dao.O2O;

import irille.Aops.Caches;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.PdtProduct;

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
            int start, int limit, Date startDate, Date endDate, String keyWord, int supId, Integer countryId) {
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
                .LIMIT(start, limit);
        if (supId > 0) {
            sql.WHERE(O2O_JoinInfo.T.SUPPLIER_ID, "=?", supId);
            sql.LEFT_JOIN(O2O_JoinInfo.class, O2O_JoinInfo.T.ACTIVITY_ID, T.PKEY);
        }
        if (countryId != null && countryId > 0) {
            sql.WHERE("address->'$.countryId'=?", countryId);
        }
        return Query.sql(sql).queryMaps();
    }

    public Integer getO2OActivityListCount(Date startDate, Date endDate, String keyWord) {
        SQL sql = new SQL();
        sql.SELECT(
                O2O_Activity.T.STATUS)
                .FROM(O2O_Activity.class)
                .WHERE(startDate != null, O2O_Activity.T.START_DATE, ">?", startDate)
                .WHERE(endDate != null, T.END_DATE, "<?", endDate)
                .WHERE(keyWord != null && keyWord.length() > 0, T.NAME, "like ?", keyWord);
        return Query.sql(sql).queryCount();
    }

    public List<Map<String, Object>> getO2OActivityPdtList(int start, int limit, Long id, Integer pkey) {
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
                .WHERE(O2O_JoinInfo.T.ACTIVITY_ID, "=?", id)
                .WHERE(O2O_JoinInfo.T.SUPPLIER_ID, "=?", pkey)
                .LIMIT(start, limit);
        return Query.sql(sql).queryMaps();
    }
}
