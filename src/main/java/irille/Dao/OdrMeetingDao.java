package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingExhibitionStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingAuditRelease;
import irille.Entity.OdrerMeetings.OrderMeetingExhibition;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrSupplier;
import irille.view.Manage.OdrMeeting.OdrMeetingLaunchlistView;
import irille.view.Manage.OdrMeeting.OdrMeetingOtherlistView;
import irille.view.Manage.OdrMeeting.OdrMeetingParticipatelistView;
import irille.view.Page;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingDao {

    /**
     * @Description: 检查是否有条件参加订购会
     * @date 2018/11/15 11:21
     * @author lijie@shoestp.cn
     */
    public boolean isJoinOdrMeeting(int odrMeetIngId, int supplier) {
        //参加的人不能是发起人,且不再审核列状态中  订购会已通过审核 状态为即将开始
        BeanQuery query = new BeanQuery();
//        query.SELECT(OrderMeeting.T.SUPPLIERID)
//                .FROM(OrderMeetingAudit.class)
//                .LEFT_JOIN(OrderMeeting.class, OrderMeetingAudit.T.PKEY, OrderMeetingAudit.T.OrderMeeting)
//                .WHERE(OrderMeeting.T.SUPPLIERID, "<> ?", supplier)
//                .WHERE(OrderMeeting.T.STATUS, "=?", OrderMeetingStatus.TOBEGIN)
//                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)
//                .WHERE(OrderMeetingAudit.T.STATUS, "<>?", OrderMeetingAuditStatus.ACTIVITY)
////        .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.PASS)
//                .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetIngId)
//        ;
        return query.queryCount() == 0;
    }


    /**
     * @Description: 我发起订购会列表
     * @date 2018/11/14 18:30
     * @anthor wilson zhang
     */
    public Page Launchlist(Integer start, Integer limit, String name, Integer supstate, Integer onstate, Integer getSupplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(OrderMeeting.class);
            SELECT(OrderMeetingAuditRelease.T.STATUS, "AuditStatus");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingAuditRelease.class, OrderMeetingAuditRelease.T.ODRMEETING, OrderMeeting.T.PKEY);

            if (name != "" && name != null) {
                WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            } else {
                WHERE(OrderMeeting.T.STATUS, " <>?", OrderMeetingStatus.DELETE.getLine().getKey());
            }
            if (getSupplier != null) {
                WHERE(OrderMeeting.T.SUPPLIERID, " =?", getSupplier);
            }
            if (supstate != null) {
                WHERE(OrderMeetingAuditRelease.T.STATUS, " =?", supstate);
            } else {
                WHERE(OrderMeetingAuditRelease.T.STATUS, " <>?", OrderMeetingAuditStatus.DELETE.getLine().getKey());
            }
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
        }};
        Integer count = Query.sql(sql).queryCount();
        sql.LIMIT(start, limit);
        List<OdrMeetingLaunchlistView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            OdrMeetingLaunchlistView oml = new OdrMeetingLaunchlistView();
            oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
            oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
            PltCountry pc = BeanBase.load(PltCountry.class, (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
            oml.setCountry(pc.getName());
            oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
            oml.setStarttime((Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
            oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
            if (Integer.parseInt(String.valueOf(o.get("AuditStatus"))) == 1) {
                oml.setState(Integer.parseInt(String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
            } else {
                oml.setState(Integer.parseInt(String.valueOf(o.get("AuditStatus"))));
            }
            return oml;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * @Description: 参与订购会列表页面
     * @date 2018/11/14 20:32
     * @anthor wilson zhang
     */
    public Page participatelist(Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(
                    OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.SUPPLIERID,
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME
            ).SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry")
                    .SELECT(OrderMeetingExhibition.T.NAME, "OMENAME");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
            LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.ODRMEETING, OrderMeeting.T.PKEY);
            if (name != null) {
                WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            if (getSupplier != null) {
                WHERE(OrderMeetingAudit.T.SUPPLIERID, " =?", getSupplier);
            }
            WHERE(OrderMeeting.T.SUPPLIERID, " <> ?", getSupplier);
            WHERE(OrderMeeting.T.STATUS, " <> ?", OrderMeetingStatus.DELETE.getLine().getKey());
            WHERE(OrderMeetingAudit.T.STATUS, " =?", OrderMeetingAuditStatus.ACTIVITY.getLine().getKey());
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
        }};
        Integer count = Query.sql(sql).queryCount();
        sql.LIMIT(start, limit);
        Query.sql(sql).limit(start, limit).queryMaps();
        List<OdrMeetingParticipatelistView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            OdrMeetingParticipatelistView oml = new OdrMeetingParticipatelistView();
            oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
            oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
            oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
            if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null
                    && !o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()).equals("")) {
                oml.setExhibition((String) o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            } else {
                oml.setExhibition((String) o.get("OMENAME"));
            }
            UsrSupplier usr = BeanBase.load(UsrSupplier.class, (Integer)
                    o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()));
            PltCountry pc = BeanBase.load(PltCountry.class, (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
            oml.setSuppliername(usr.getName());
            oml.setCountry(pc.getName());
            oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
            oml.setStarttime((Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
            oml.setState(Integer.parseInt(String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
            return oml;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * @Description: 其他订购会列表页面
     * @date 2018/11/14 20:32
     * @anthor wilson zhang
     */
    public Page Otherlist(Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.SUPPLIERID,
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME
            )
                    .SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry")
                    .SELECT(OrderMeetingExhibition.T.NAME, "OMENAME")
            .SELECT(OrderMeetingAudit.T.STATUS, "OMASTATUS");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
            LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.ODRMEETING, OrderMeeting.T.PKEY);
            if (name != null) {
                WHERE(OrderMeeting.T.NAME, " like  '%" + name + "%'");
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            WHERE(OrderMeeting.T.SUPPLIERID, " <> ?", getSupplier);
            AND().WHERE(OrderMeeting.T.STATUS, " <>?", OrderMeetingStatus.DELETE.getLine().getKey());
            AND();
            WHERE(OrderMeetingAudit.T.STATUS, " not in(" + OrderMeetingAuditStatus.ACTIVITY.getLine().getKey() + "," + OrderMeetingAuditStatus.DELETE.getLine().getKey() + ")");
            or().
            WHERE(OrderMeetingAudit.T.PKEY.getFld().getTb().getCode() + "." + OrderMeetingAudit.T.STATUS + " is null ");
            AND().
            WHERE(OrderMeetingAudit.T.SUPPLIERID, " = ?", getSupplier);
            or().
            WHERE(OrderMeetingAudit.T.SUPPLIERID, " is null");
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
        }};
        Integer count = Query.sql(sql).queryCount();
        List<OdrMeetingOtherlistView> list = Query.sql(sql).limit(start, limit).queryMaps().stream().map(o -> {
            OdrMeetingOtherlistView oml = new OdrMeetingOtherlistView();
            oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
            oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
            oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
            if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null
                    && !o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()).equals("")) {
                oml.setExhibition((String) o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            } else {
                oml.setExhibition((String) o.get("OMENAME"));
            }
            //判断当前供应商没有审核过的订购会为负数
            System.out.println(o.get("OMASTATUS"));
            if(o.get("OMASTATUS")!= null){
                oml.setApplyodr(Integer.parseInt(String.valueOf(o.get("OMASTATUS"))));
            }else{
                oml.setApplyodr(-1);
            }
            UsrSupplier usr = BeanBase.load(UsrSupplier.class, (Integer)
                    o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()));
            oml.setSuppliername(usr.getName());
            PltCountry pc = BeanBase.load(PltCountry.class, (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
            oml.setCountry(pc.getName());
            oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
            oml.setStarttime((Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
            oml.setState(Integer.parseInt(String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
            return oml;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }


    /**
     * @Description: 发起订购会列表页面  批量删除功能  逻辑删除
     * @date 2018/11/14 19:48
     * @anthor wilson zhang
     */
    public void batchdelete(String pkeys) {
        Bean.executeUpdate("update " + OrderMeeting.TB.getCodeSqlTb() + " set " + OrderMeeting.T.STATUS.getFld().getCodeSqlField()
                + "= " + OrderMeetingStatus.DELETE.getLine().getKey()
                + " where " + OrderMeeting.T.PKEY.getFld().getCodeSqlField()
                + " in(" + pkeys + ")");
    }

    /**
     * @Description: 参加购会列表页面  批量删除功能  逻辑删除
     * @date 2018/11/16 10:06
     * @anthor wilson zhang
     */
    public void joindelete(String pkeys) {
        Bean.executeUpdate("update " + OrderMeetingAudit.TB.getCodeSqlTb() + " set " + OrderMeetingAudit.T.STATUS.getFld().getCodeSqlField()
                + "= " + OrderMeetingAuditStatus.DELETE.getLine().getKey()
                + " where " + OrderMeetingAudit.T.PKEY.getFld().getCodeSqlField()
                + " in(" + pkeys + ")");
    }


    /**
     * @Description: 订购会列表  修改状态
     * @date 2018/11/14 20:15
     * @anthor wilson zhang
     */
    public static class Meettingupdstate extends IduUpd<Meettingupdstate, OrderMeeting> {
        @Override
        public void before() {

            OrderMeeting dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), OrderMeeting.T.STATUS);
            setB(dbBean);
            super.before();
        }
    }


}
