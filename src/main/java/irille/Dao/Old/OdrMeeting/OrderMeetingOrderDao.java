package irille.Dao.Old.OdrMeeting;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.odr.Odr;
import irille.shop.odr.OdrOrder;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderListView;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderOrderStatusListView;
import irille.view.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMeetingOrderDao {
    public Page getOmtOrderList(Integer omtId, Integer start, Integer limit, Integer classification, Integer orderStatus, String input) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {
            {
                SELECT(OdrOrder.T.ORDER_NUM)//订单号
                        .SELECT(UsrPurchase.T.EMAIL)//邮箱
                        .SELECT(OdrOrder.T.CURRENCY)//货币(用来拼接)
                        .SELECT(OdrOrder.T.PROD_PRICE)//商品总价
                        .SELECT(OdrOrder.T.PRICE_TOTAL)//订单总价
                        .SELECT(PltCountry.T.NAME)//国家
                        //缺少付款时间
                        //缺少是否结算
                        //缺少是否(自有/合作商) 分类
                        .SELECT(OdrOrder.T.TIME)//下单时间
                        .SELECT(OdrOrder.T.STATE)//订单状态
                        .FROM(OrderMeetingOrder.class)
                        .WHERE(OrderMeeting.T.PKEY, "=?", omtId)
                        .WHERE(OdrOrder.T.STATE, "=?", orderStatus)
                        .LEFT_JOIN(OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingOrder.T.ORDERMEETINGID)
                        .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID)
                        .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, OdrOrder.T.PURCHASE)
                        .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, OrderMeeting.T.COUNTRY);
                if (input != null) {
                    WHERE(OdrOrder.T.ORDER_NUM, "=?", input);
                }
            }
        };
        List<orderOrderListView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            orderOrderListView view = new orderOrderListView();
            view.setOrderNum(String.valueOf(o.get(OdrOrder.T.ORDER_NUM.getFld().getCodeSqlField())));
            view.setEmail(String.valueOf(o.get(UsrPurchase.T.EMAIL.getFld().getCodeSqlField())));
            view.setProdPrice(String.valueOf(o.get(OdrOrder.T.CURRENCY.getFld().getCodeSqlField())) + String.valueOf(o.get(OdrOrder.T.PROD_PRICE.getFld().getCodeSqlField())));
            view.setPriceTotal(String.valueOf(o.get(OdrOrder.T.CURRENCY.getFld().getCodeSqlField())) + String.valueOf(o.get(OdrOrder.T.PRICE_TOTAL.getFld().getCodeSqlField())));
            view.setCountry(String.valueOf(o.get(PltCountry.T.NAME)));
            view.setTime(String.valueOf(o.get(OdrOrder.T.TIME.getFld().getCodeSqlField())));
            view.setOrderStatus(String.valueOf(o.get(OdrOrder.T.STATE.getFld().getCodeSqlField())));


            return view;
        }).collect(Collectors.toList());
        Integer count = Query.sql(sql).queryCount();
        return new Page(list, start, limit, count);
    }

    public List<orderOrderStatusListView> getOrderStatus() {
        List<orderOrderStatusListView> list = new ArrayList<>();
        orderOrderStatusListView view = new orderOrderStatusListView();
        view.setId(-1);
        view.setName("全部");
        list.add(view);
        for (Odr.OdrState value : Odr.OdrState.values()) {
            view = new orderOrderStatusListView();
            view.setId(Integer.valueOf(String.valueOf(value.getLine().getKey())));
            view.setName(value.getLine().getName());
            list.add(view);
        }
        return list;
    }
}
