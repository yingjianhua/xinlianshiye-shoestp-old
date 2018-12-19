package irille.Dao.Old.OdrMeeting;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.shop.odr.Odr;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderListView;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMeetingOrderDao {
    public Page getOmtOrderList(Integer omtId,Integer productId,Integer supplierId,Integer supplier, Integer start, Integer limit, Integer classification, Integer status,Integer orderStatus, String input) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {
            {
                SELECT(OdrOrder.T.ORDER_NUM,//订单号
                        OdrOrder.T.PROD_PRICE,//产品总价
                        OdrOrder.T.PRICE_TOTAL,//订单总额
                        OdrOrder.T.TIME,//下单时间
                        UsrPurchase.T.EMAIL,//邮箱
                        OrderMeetingOrder.T.PAYMENTTIME,//付款时间
                        OrderMeetingOrder.T.BILLINGSTATUS,//是否结算
                        OrderMeetingOrder.T.WHETHER_TO_SEND,//是否发送
                        OrderMeetingOrder.T.PKEY,//订购会订单pkey
                        OdrOrder.T.CURRENCY,//货币(用来拼接)
                        OdrOrder.T.STATE,//订单状态
                        PltCountry.T.NAME,//国家
                        PdtSpec.T.PRODUCT,//产品id
                        OrderMeeting.T.STATUS,//订购会活动状态
                        OrderMeeting.T.SUPPLIERID,//订购会发起者
                        OrderMeetingOrder.T.PARTNER//订单拥有者
                        )//下单时间
                        .FROM(OrderMeetingOrder.class)
                        .LEFT_JOIN(OrderMeeting.class,OrderMeeting.T.PKEY,OrderMeetingOrder.T.ORDERMEETINGID)
                        .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID)
                        .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, OdrOrder.T.PURCHASE)
                        .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, OrderMeeting.T.COUNTRY)
                        .LEFT_JOIN(OdrOrderLine.class,OdrOrderLine.T.MAIN,OdrOrder.T.PKEY)
                        .LEFT_JOIN(PdtSpec.class,PdtSpec.T.PKEY,OdrOrderLine.T.SPEC)
                        .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", omtId);
                if (productId != null) {
                    WHERE(PdtSpec.T.PRODUCT, "=?", productId);
                }
                if (supplierId != null) {
                    WHERE(OrderMeetingOrder.T.PARTNER, "=?", supplierId);
                }
                if (classification != null) {
                    if(classification!=1){
                        WHERE(OrderMeeting.T.SUPPLIERID," <> ?",supplier);
                    }else{
                        WHERE(OrderMeeting.T.SUPPLIERID,"=?",supplier);
                    }
                }else{
                    WHERE(OrderMeeting.T.SUPPLIERID,"=?",supplier);
                }
                if (status != null) {
                    if(status!=-1){
                        WHERE(OdrOrder.T.STATE,"=?",status);
                    }
                }
                if(orderStatus!=null){
                    if(orderStatus!=-1){
                        WHERE(OdrOrder.T.STATE,"=?",orderStatus);
                    }
                }
                if (input != null) {
                    WHERE(OdrOrder.T.ORDER_NUM, "=?", input);
                }
            }
        };
        List<orderOrderListView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            orderOrderListView view = new orderOrderListView();
            view.setOrderId(Integer.valueOf(String.valueOf(o.get(OrderMeetingOrder.T.PKEY.getFld().getCodeSqlField()))));
            view.setWhetherToSend(Integer.valueOf(String.valueOf(o.get(OrderMeetingOrder.T.WHETHER_TO_SEND.getFld().getCodeSqlField()))));
            view.setOmtStatus(Integer.valueOf(String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
            view.setOmtSupplier(Integer.valueOf(String.valueOf(o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()))));
            view.setSupplier(Integer.valueOf(String.valueOf(o.get(OrderMeetingOrder.T.PARTNER.getFld().getCodeSqlField()))));
            view.setProduct(Integer.valueOf(String.valueOf(o.get(PdtSpec.T.PRODUCT.getFld().getCodeSqlField()))));
            view.setOrderNum(String.valueOf(o.get(OdrOrder.T.ORDER_NUM.getFld().getCodeSqlField())));
            view.setEmail(String.valueOf(o.get(UsrPurchase.T.EMAIL.getFld().getCodeSqlField())));
            view.setProdPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(OdrOrder.T.PROD_PRICE.getFld().getCodeSqlField())))));
            view.setPriceTotal(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(OdrOrder.T.PRICE_TOTAL.getFld().getCodeSqlField())))));
            view.setOrderStatus(Integer.valueOf(String.valueOf(o.get(OdrOrder.T.STATE.getFld().getCodeSqlField()))));
            view.setCountry(String.valueOf(o.get(PltCountry.T.NAME.getFld().getCodeSqlField())));
            view.setPaymentTime(String.valueOf(o.get(OrderMeetingOrder.T.PAYMENTTIME.getFld().getCodeSqlField())));
            view.setWhetherSettlement(Integer.valueOf(String.valueOf(o.get(OrderMeetingOrder.T.BILLINGSTATUS.getFld().getCodeSqlField()))));
            view.setTime(String.valueOf(o.get(OdrOrder.T.TIME.getFld().getCodeSqlField())));
            return view;
        }).collect(Collectors.toList());
        Integer count = Query.sql(sql).queryCount();
        return new Page(list, start, limit, count);
    }

    public List<orderOrderStatusListView> getOrderStatus() {
        List<orderOrderStatusListView> list = new ArrayList<>();
        for (Odr.OdrState value : Odr.OdrState.values()) {
            orderOrderStatusListView view = new orderOrderStatusListView();
            view.setId(Integer.valueOf(String.valueOf(value.getLine().getKey())));
            view.setName(value.getLine().getName());
            list.add(view);
        }
        return list;
    }

    public void updSendStatus(Integer id) {
        OrderMeetingOrder order = BeanBase.load(OrderMeetingOrder.class, id);
        if (order.getWhetherToSend() == 0) {
            order.setWhetherToSend(Sys.OYn.YES.getLine().getKey());
        }
        order.setCreatedTime(Env.getTranBeginTime());
        order.upd();
    }
}
