package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingExhibitionStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingExhibition;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.shop.plt.PltCountry;
import irille.view.Manage.OdrMeeting.OdrMeetingLaunchlistView;
import irille.view.Manage.OdrMeeting.OdrMeetingParticipatelistView;
import irille.view.Page;

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
        query.SELECT(OrderMeeting.T.SUPPLIERID)
                .FROM(OrderMeetingAudit.class)
                .LEFT_JOIN(OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingAudit.T.OrderMeeting)//订购会与审核表进行关联
                .WHERE(OrderMeeting.T.SUPPLIERID, "<> ?", supplier)   //不能是订购会发起人
                .WHERE(OrderMeeting.T.STATUS, "=?", OrderMeetingStatus.TOBEGIN)  //订购会状态要未开始,否则不能参加
                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)//并且该订购会该用户应该是未参加的
                .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.ACTIVITY)
                .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetIngId) // 查看该订订购会的
        ;
        return query.queryCount() == 0;
    }


    /**
     * @Description: 我发起订购会列表
     * @date 2018/11/14 18:30
     * @anthor wilson zhang
     */
    public Page Launchlist(Integer start, Integer limit, String name,Integer supstate, Integer onstate, Integer getSupplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT( OrderMeeting.class);
            SELECT(OrderMeetingAuditRelease.T.STATUS,"AuditStatus");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingAuditRelease.class,OrderMeetingAuditRelease.T.ODRMEETING,OrderMeeting.T.PKEY);

            if (name != "" && name != null) {
                WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }else{
                WHERE(OrderMeeting.T.STATUS, " <>?", OrderMeetingStatus.DELETE.getLine().getKey());
            }
            if (getSupplier != null) {
                WHERE(OrderMeeting.T.SUPPLIERID, " =?", getSupplier);
            }
            if (supstate != null) {
                WHERE(OrderMeetingAuditRelease.T.STATUS, " =?", supstate);
            }else{
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
            if(Integer.parseInt(String.valueOf(o.get("AuditStatus")))==1){
                oml.setState(Integer.parseInt(String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
            }else{
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
    public  Page participatelist(Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
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
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME
            ).SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
      //      LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.OrderMeeting, OrderMeeting.T.PKEY);
            if (name != null) {
                WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            if (getSupplier != null) {
                WHERE(OrderMeetingAudit.T.SUPPLIERID, " =?", getSupplier);
            }
            WHERE(OrderMeeting.T.STATUS, " <> ?", OrderMeetingStatus.DELETE.getLine().getKey());
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
            System.out.println(o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null) {
                oml.setExhibition((String) o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            } else {
                oml.setExhibition((String) o.get(OrderMeetingExhibition.T.NAME.getFld().getCodeSqlField()));
            }
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
     * @Description: 其他订购会列表页面
     * @date 2018/11/14 20:32
     * @anthor wilson zhang
     */
    public  Page Otherlist(Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME
            ).SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry");
            FROM(OrderMeeting.class);
            LEFT_JOIN(OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
         //   LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.OrderMeeting, OrderMeeting.T.PKEY);
            WHERE(OrderMeeting.T.STATUS, " <>", OrderMeetingExhibitionStatus.DELETE.getLine().getKey());
            if (name.equals("")) {
                WHERE(OrderMeeting.T.NAME, " like %?%", name);
            }
            if (onstate != null) {
                WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
            GROUP_BY(OrderMeetingAudit.T.SUPPLIERID);
            HAVING(OrderMeetingAudit.T.SUPPLIERID.getFld().getCodeSqlField() + "<>" + getSupplier);
        }};
        Integer count = Query.sql(sql).queryCount();
        List<OdrMeetingParticipatelistView> list = Query.sql(sql).limit(start, limit).queryMaps().stream().map(o -> {
            OdrMeetingParticipatelistView oml = new OdrMeetingParticipatelistView();
            oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
            oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
            oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
            System.out.println(o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            System.out.println(">>>>>>>>>>>>>>>>>站厅名称");
            if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null) {
                oml.setExhibition((String) o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
            } else {
                oml.setExhibition((String) o.get(OrderMeetingExhibition.T.NAME.getFld().getCodeSqlField()));
            }
            PltCountry pc = BeanBase.load(PltCountry.class, (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
            oml.setCountry(pc.getName());
            oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
            oml.setStarttime((Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
            oml.setState((Integer) o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()));
            return oml;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }


    /**
     * @Description: 发起订购会列表页面  批量删除功能  逻辑删除
     * @date 2018/11/14 19:48
     * @anthor wilson zhang
     */
    public  void batchdelete(String pkeys) {
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

    }

    /**
     * @Description: 获取该订购会所有销售信息
     * @date 2018/11/16 9:46
     * @author lijie@shoestp.cn
     */
    public List<OdrMeetingSaleInfoView> getMeetingAllSaleInfo(int start, int limit, int odrMeeting) {
        BeanQuery query = new BeanQuery();
        SQL sql = new SQL();
        sql.SELECT(
                OrderMeetingProduct.T.PKEY,
                PdtSpec.T.KEY_NAME,
                PdtSpec.T.PICS,
                OrderMeetingProduct.T.NEWPRICE,
                OrderMeetingProduct.T.NEWMOQ,
                OrderMeetingProduct.T.STATUS
        )
                .FROM(OrderMeetingOrder.class)
                .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID)  //联合订单表
                .LEFT_JOIN(OdrOrderLine.class, OdrOrderLine.T.MAIN, OdrOrder.T.PKEY)  //联合订单明细表
                .LEFT_JOIN(PdtSpec.class, OdrOrderLine.T.SPEC, PdtSpec.T.PKEY)  //联合订单明细表
                .LEFT_JOIN(OrderMeetingProduct.class, OrderMeetingProduct.T.ORDERMEETINGID,
                        OrderMeetingOrder.T.ORDERMEETINGID)//联合联合采购订单补充表
                .WHERE(OdrOrder.T.TYPE, "=?", Odr.OdrType.STATEONE) //订单类型是  联合采购订单
                .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", odrMeeting); //并且是该订购会的
        Query.sql(sql).queryMaps();
        return null;
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
