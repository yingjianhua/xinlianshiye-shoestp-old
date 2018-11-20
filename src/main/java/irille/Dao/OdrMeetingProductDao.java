package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingProductStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeeting.T;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.PdtProduct;
import irille.view.Manage.OdrMeeting.initiatedActivity.orderGoodsView;
import irille.view.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingProductDao {

    /**
     * @Description: 检查是否有条件参加添加该商品到订购会
     * @date 2018/11/15 11:21
     * @author lijie@shoestp.cn
     */
    public boolean isAddToOdrMeeting(int odrMeetingId, int supplier) {
        BeanQuery query = new BeanQuery();
//        query.SELECT(T.SUPPLIERID)
//                .FROM(OrderMeetingAudit.class)
//                .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingAudit.T.OrderMeeting)
//                .WHERE(T.SUPPLIERID, "= ?", supplier)  //判断是不是发起人
//                .WHERE(T.STATUS, "=?", OrderMeetingStatus.TOBEGIN)  //判断订购会状态
//                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)   //判断是通过的供应商
//                .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.ACTIVITY)  //状态通过
//                .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetingId)
//        ;
        return query.queryCount() > 0;
    }

    public Page getOrderGoodsList(Integer start, Integer limit, Integer id, Integer status, String inputContent) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {
            {
                SELECT(OrderMeetingProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE)
                        .SELECT(OrderMeetingProduct.T.SUPPLIERID, "productSupplier")
                        .SELECT(OrderMeeting.T.SUPPLIERID,
                                OrderMeetingProduct.T.PRICE,
                                OrderMeetingProduct.T.MOQ,
                                OrderMeetingProduct.T.NEWMOQ,
                                OrderMeetingProduct.T.NEWPRICE,
                                OrderMeetingProduct.T.TARGET_COUNT,
                                OrderMeetingProduct.T.STATUS)
                        .FROM(OrderMeetingProduct.class)
                        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, OrderMeetingProduct.T.PRODUCTID)
                        .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingProduct.T.ORDERMEETINGID)
                        .WHERE(OrderMeeting.T.PKEY, "=?", id);
                if (inputContent != null) {
                    WHERE(PdtProduct.T.NAME, "like'%" + inputContent + "%'");
                }
                switch (status) {
                    case 1: {
                        WHERE(OrderMeeting.T.SUPPLIERID, "=", OrderMeetingProduct.T.SUPPLIERID);
                        break;
                    }
                    case 2: {
                        WHERE(OrderMeeting.T.SUPPLIERID, "<>", OrderMeetingProduct.T.SUPPLIERID);
                        break;
                    }
                }
            }
        };
        Integer count = Query.sql(sql).queryCount();
        List<orderGoodsView> viewList = Query.sql(sql).limit(start, limit).queryMaps().stream().map(o -> {
            orderGoodsView view = new orderGoodsView();
            view.setId((Integer) o.get(OrderMeetingProduct.T.PKEY.getFld().getCodeSqlField()));
            if (o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != null || o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != "") {
                view.setImage(String.valueOf(o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField())).split(",")[0]);
            }
            view.setProductName(String.valueOf(o.get(PdtProduct.T.NAME.getFld().getCodeSqlField())));
            if (o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()).equals(o.get("productSupplier"))) {
                view.setMode("自有");
            } else {
                view.setMode("合作商");
            }
            view.setOriginalPurchasePrice(((BigDecimal) o.get(OrderMeetingProduct.T.PRICE.getFld().getCodeSqlField())).doubleValue());
            view.setOriginalOrderQuantity(Integer.parseInt(o.get(OrderMeetingProduct.T.MOQ.getFld().getCodeSqlField()).toString()));
            view.setOrderPrice(Integer.parseInt(o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()).toString()));
            view.setOrderQuantityorderPrice(((BigDecimal) o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField())).doubleValue());
            view.setTargetAmount((Integer) o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()));
            view.setStatus((Byte) o.get(OrderMeetingProduct.T.STATUS.getFld().getCodeSqlField()));
            return view;
        }).collect(Collectors.toList());
        return new Page(viewList, start, limit, count);
    }

    public void updateStatus(Integer id) {
        OrderMeetingProduct order = BeanBase.load(OrderMeetingProduct.class, id);
        if (order.getStatus() == 0) {
            order.setStatus(OrderMeetingProductStatus.ON.getLine().getKey());
        } else {
            order.setStatus(OrderMeetingProductStatus.DEFAULT.getLine().getKey());
        }
        order.upd();
    }
    public void removePorduct(Integer id,Integer productId){
        OrderMeetingProduct mo = BeanBase.load(OrderMeetingProduct.class, id);
        mo.setStatus(OrderMeetingProductStatus.DELETE.getLine().getKey());
        mo.upd();
    }
}
