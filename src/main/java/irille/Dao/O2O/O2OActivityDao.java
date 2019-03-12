package irille.Dao.O2O;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.DbPool;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRole;
import irille.view.Page;
import irille.view.O2O.O2OActivityView;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26
 * Time: 12:50
 */
public class O2OActivityDao {

    public O2O_Activity getActivityInfoById(Integer id) {
        return Query.SELECT(O2O_Activity.class).WHERE(T.PKEY, "=?", id).query();
    }

    public Page<O2OActivityView> pageView(Integer start, Integer limit, O2OActivityView condition) {
        if (null == condition)
            condition = new O2OActivityView();
        BeanQuery<O2O_Activity> query = Query.SELECT(O2O_Activity.class)
                // 起始时间
                .WHERE(condition.getStartDate() != null, O2O_Activity.T.START_DATE, ">=?", condition.getStartDate())
                // 截止时间
                .WHERE(condition.getEndDate() != null, O2O_Activity.T.END_DATE, "<=?", condition.getEndDate())
                // 活动名称
                .WHERE(condition.getName() != null && !"".equals(condition.getName().trim()), O2O_Activity.T.NAME, "like ?", "%" + condition.getName() + "%");
        // 活动状态//TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
        Date now = new Date();
        if (null != condition.getStatus()) {
            if (condition.getStatus().equals(O2O_ActivityStatus.TOBEGIN.getLine().getKey())) {
                //未开始
                query.WHERE(T.START_DATE, ">?", now);
            } else if (condition.getStatus().equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey())) {
                //活动中
                query.WHERE(T.START_DATE, "<?", now);
                query.WHERE(T.END_DATE, ">?", now);
            } else if (condition.getStatus().equals(O2O_ActivityStatus.END.getLine().getKey())) {
                //结束
                query.WHERE(T.END_DATE, "<?", now);
            }
        }
//				query.WHERE(condition.getStatus()!=null,T.STATUS,"=?",condition.getStatus());

        List<O2OActivityView> result = query.queryList().stream().map(bean -> {
            return O2OActivityView.toView(bean);
        }).collect(Collectors.toList());
        Integer count = query.queryCount();
        return new Page<O2OActivityView>(result, start, limit, count);
    }

    public O2O_Activity findById(Integer id) {
        return Query.SELECT(O2O_Activity.class, id);
    }


    public List<Map<String, Object>> privetePdtList(int start, int limit, Integer status, Integer verify_status, String cat, String supName,String pdtName) {
        BeanQuery query = Query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE,
                PdtProduct.T.MKT_PRICE,
                PdtProduct.T.SKU,
                PdtProduct.T.STOCK,
                PdtProduct.T.MIN_OQ,
                PdtProduct.T.UPDATE_TIME,
                O2O_PrivateExpoPdt.T.PKEY,
                O2O_PrivateExpoPdt.T.STATUS,
                O2O_PrivateExpoPdt.T.VERIFY_STATUS

        ).SELECT(UsrSupplier.T.NAME, "supName")
                .SELECT(UsrProductCategory.T.NAME, "SuppdtCat")
                .SELECT(PdtCat.T.NAME, "catName")
                .FROM(PdtProduct.class)
                .LEFT_JOIN(
                        O2O_PrivateExpoPdt.class, O2O_PrivateExpoPdt.T.PDT_ID, PdtProduct.T.PKEY
                ).LEFT_JOIN(
                        PdtCat.class, PdtCat.T.PKEY, PdtProduct.T.CATEGORY
                ).LEFT_JOIN(
                        UsrSupplier.class, UsrSupplier.T.PKEY, PdtProduct.T.SUPPLIER
                ).LEFT_JOIN(UsrSupplierRole.class, UsrSupplierRole.T.PKEY, UsrSupplier.T.ROLE)
                .LEFT_JOIN(UsrProductCategory.class, UsrProductCategory.T.PKEY, PdtProduct.T.CATEGORY_DIY)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.PrivateExpo)
                .WHERE(supName != null, UsrSupplier.T.NAME, "like", supName)
                .WHERE(status != null, O2O_PrivateExpoPdt.T.STATUS, "=?", status)
                .WHERE(verify_status != null, O2O_PrivateExpoPdt.T.VERIFY_STATUS, "=?", verify_status)
                .WHERE(cat != null, PdtCat.T.NAME, "like ?", "%" + cat + "%")
                .WHERE(pdtName != null&&pdtName.length()>0, PdtProduct.T.NAME, "like ?", "%" + pdtName + "%")
                .limit(start, limit);
        return query.queryMaps();
    }

    public Integer privetePdtListCount() {
        BeanQuery query = Query.SELECT(
                O2O_PrivateExpoPdt.T.VERIFY_STATUS
        )
                .FROM(O2O_PrivateExpoPdt.class);
        return query.queryCount();
    }
    
    public List<O2O_Activity> findAllByStatusExceptEnd(){
    	SQL sql = new SQL();
    	sql.SELECT(O2O_Activity.class).FROM(O2O_Activity.class)
    	.WHERE(O2O_Activity.T.STATUS, " <>? ",O2O_ActivityStatus.END.getLine().getKey())
    	.WHERE(O2O_Activity.T.STATUS, " <>? ",O2O_ActivityStatus.CLOSE.getLine().getKey());
    	return Query.sql(sql).queryList(O2O_Activity.class);
    }
    
    public void upd(List<O2O_Activity> activities) {
    	BatchUtils.batchUpd(O2O_Activity.class, Arrays.asList(O2O_Activity.T.STATUS), Arrays.asList(O2O_Activity.T.PKEY), activities);
   	}
}
