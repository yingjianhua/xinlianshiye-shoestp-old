package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingProductStatus;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.usr.UsrSupplier;
import irille.view.Manage.OdrMeeting.OdrAuditsupplierView;
import irille.view.Page;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMeetingAuditDao {
    private static final Log LOG = new Log(OrderMeetingAuditDao.class);

    /**
     * @Description: 商家申请 参加订购会
     * @date 2018/11/19 20:09
     * @anthor wilson zhang
     */
    public static class insertjoinOdr extends IduIns<insertjoinOdr, OrderMeetingAudit> {
        @Override
        public void before() {
            getB().setCreatedTime(Env.getTranBeginTime());
            getB().setStatus(OrderMeetingAuditStatus.PENDING.getLine().getKey());
            super.before();
        }
    }

    /**
     * @Description: 逻辑删除 参加订购会合作商
     * @date 2018/11/22 11:14
     * @anthor wilson zhang
     */
    public static class deletejoinOdr extends IduUpd<deletejoinOdr, OrderMeetingAudit> {
        @Override
        public void before() {
            OrderMeetingAudit dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), OrderMeetingAudit.T.STATUS);
            setB(dbBean);
            OrderMeetingProduct omp = new OrderMeetingProduct();
            omp.setPkey(getB().getPkey());
            omp.setSupplierid(getB().getSupplierid());
            OdrMeetingProductDao.deletejoinOdr(omp);
            super.before();
        }
    }

    /**
     * @Description: 显示合作商列表
     * @date 2018/11/22 13:55
     * @anthor wilson zhang
     */
    public Page cooperationsupplier(Integer start, Integer limit, Integer status, String name, Integer omtid) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(OrderMeetingAudit.T.PKEY, "omatid").SELECT(OrderMeetingAudit.T.STATUS, OrderMeetingAudit.T.SAMPLEADDRESS)
                    .SELECT(UsrSupplier.T.PKEY, "supplierid")
                    .SELECT(UsrSupplier.T.NAME, UsrSupplier.T.CONTACTS, UsrSupplier.T.EMAIL,
                            UsrSupplier.T.IS_AUTH, UsrSupplier.T.CERT_PHOTO);
            FROM(OrderMeetingAudit.class);
            LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, OrderMeetingAudit.T.SUPPLIERID);
            if (name != null) {
                WHERE(UsrSupplier.T.NAME, " like  '%" + name + "%'");
            }
            if (status != null) {
                WHERE(OrderMeetingAudit.T.STATUS, " =? ", status);
            }
            WHERE(OrderMeetingAudit.T.ODRMEETING, "= ? ", omtid);
            WHERE(OrderMeetingAudit.T.STATUS, " <>?", OrderMeetingAuditStatus.DELETE.getLine().getKey());
        }};
        Integer count = Query.sql(sql).queryCount();
        Query.sql(sql).limit(start, limit);
        List<OdrAuditsupplierView> oal = Query.sql(sql).queryMaps().stream().map(o -> {
            OdrAuditsupplierView omv = new OdrAuditsupplierView();
            omv.setId((Integer) o.get("omatid"));
            omv.setCompanyname((String) o.get(UsrSupplier.T.NAME.getFld().getCodeSqlField()));
            omv.setName((String) o.get(UsrSupplier.T.CONTACTS.getFld().getCodeSqlField()));
            omv.setEmail((String) o.get(UsrSupplier.T.EMAIL.getFld().getCodeSqlField()));
            omv.setIsauth(Integer.valueOf(String.valueOf(o.get(UsrSupplier.T.IS_AUTH.getFld().getCodeSqlField()))));
            if(o.get(UsrSupplier.T.CERT_PHOTO.getFld().getCodeSqlField())!=null){
                omv.setImage(String.valueOf(o.get(UsrSupplier.T.CERT_PHOTO.getFld().getCodeSqlField().split(",")[0]) ));
            }
            omv.setStatus(Integer.parseInt(String.valueOf(o.get(OrderMeetingAudit.T.STATUS.getFld().getCodeSqlField()))));
            omv.setAddress((String) o.get(OrderMeetingAudit.T.SAMPLEADDRESS.getFld().getCodeSqlField()));
            SQL sql1 = new SQL() {{
                SELECT(OrderMeetingProduct.T.PKEY).FROM(OrderMeetingProduct.class).WHERE(OrderMeetingProduct.T.ORDERMEETINGID, " =?", omtid)
                        .WHERE(OrderMeetingProduct.T.STATUS, " =?", OrderMeetingProductStatus.ON)
                        .WHERE(OrderMeetingProduct.T.SUPPLIERID, " =?", (Integer) o.get(UsrSupplier.T.PKEY));
            }};
            omv.setShopnum(Query.sql(sql1).queryCount());
            return omv;
        }).collect(Collectors.toList());
        return new Page(oal, start, limit, count);
    }
}
