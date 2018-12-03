package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingProductStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeeting.T;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.html.Nodes;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.pub.idu.IduUpd;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.view.Manage.OdrMeeting.initiatedActivity.AllProductsView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderGoodsView;
import irille.view.Page;

import javax.swing.plaf.SliderUI;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    public Page getOrderGoodsList(Integer start, Integer limit, Integer id, Integer status, String inputContent, Integer supplierId) {
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
                        .WHERE(OrderMeeting.T.PKEY, "=?", id)
                        .WHERE(OrderMeetingProduct.T.STATUS, "<>?", OrderMeetingProductStatus.IRREGULARITIESDELETE.getLine().getKey())
                        .WHERE(OrderMeetingProduct.T.STATUS, "<>?", OrderMeetingProductStatus.DELETE.getLine().getKey());
                if (supplierId != null) {
                    WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", supplierId);
                }
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
        List<OrderGoodsView> viewList = Query.sql(sql).limit(start, limit).queryMaps().stream().map(o -> {
            OrderGoodsView view = new OrderGoodsView();
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
        order.setUpdatedTime(Env.getTranBeginTime());
        order.upd();
    }

    public void removePorduct(Integer id) {
        OrderMeetingProduct mo = BeanBase.load(OrderMeetingProduct.class, id);
        mo.setStatus(OrderMeetingProductStatus.DELETE.getLine().getKey());
        mo.setUpdatedTime(Env.getTranBeginTime());
        mo.upd();
    }

    public List<AllProductsView> getProducts(Integer id) {
        SQL sql = new SQL() {
            {
                SELECT(PdtProduct.T.PKEY,
                        PdtProduct.T.NAME,
                        PdtProduct.T.PICTURE,
                        PdtProduct.T.CODE,
                        PdtProduct.T.CUR_PRICE,
                        PdtProduct.T.MIN_OQ)
                        .SELECT(OrderMeetingProduct.T.PRICE,
                                OrderMeetingProduct.T.MOQ,
                                OrderMeetingProduct.T.NEWPRICE,
                                OrderMeetingProduct.T.NEWMOQ,
                                OrderMeetingProduct.T.TARGET_COUNT)
                        .FROM(PdtProduct.class)
                        .LEFT_JOIN(OrderMeetingProduct.class, OrderMeetingProduct.T.PRODUCTID, PdtProduct.T.PKEY)
                        .WHERE(PdtProduct.T.SUPPLIER, "=?", id);
            }
        };
        List<AllProductsView> listView = Query.sql(sql).queryMaps().stream().map(o -> {
            AllProductsView view = new AllProductsView();
            view.setId(Integer.valueOf(o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()).toString()));
            if (o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != null && o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != "") {
                view.setImage(String.valueOf(o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField())).split(",")[0]);
            } else {
                view.setImage("");
            }
            view.setName(String.valueOf(o.get(PdtProduct.T.NAME.getFld().getCodeSqlField())));
            view.setCode(String.valueOf(o.get(PdtProduct.T.CODE.getFld().getCodeSqlField())));
            if (o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()) != null && o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()) != "") {
                view.setOldPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField())))));
            } else {
                view.setOldPrice(new BigDecimal(0));
            }
            if (o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()) != null && o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()) != "") {
                view.setOldMoq(Integer.valueOf(String.valueOf(o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()))));
            } else {
                view.setOldMoq(0);
            }
            if (o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField()) != null && o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField()) != "") {
                view.setNewPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField())))));
            } else {
                view.setNewPrice(new BigDecimal(0));
            }
            if (o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()) != null && o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()) != "") {
                view.setMoq(Integer.valueOf(String.valueOf(o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()))));
            } else {
                view.setMoq(0);
            }
            if (o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()) != null && o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()) != "") {
                view.setAims(Integer.valueOf(String.valueOf(o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()))));
            } else {
                view.setAims(0);
            }
            return view;
        }).collect(Collectors.toList());
        return listView;
    }

    public List getAddedProducts(Integer id) {
        List<OrderMeetingProduct> list = BeanBase.list(OrderMeetingProduct.class, OrderMeetingProduct.T.ORDERMEETINGID + "=" + id, false);
        List view = new ArrayList();
        for (OrderMeetingProduct orderMeetingProduct : list) {
            view.add(orderMeetingProduct.getProductid());
        }
        return view;
    }


    /**
     * @Description: 逻辑删除 参加订购会合作商 所对应的商品
     * @date 2018/11/22 11:14
     * @anthor wilson zhang
     */
    public static void deletejoinOdr(OrderMeetingProduct omp) {
        String sql = "update " + OrderMeetingProduct.TB.getCodeSqlTb()
                + " set " + OrderMeetingProduct.T.STATUS.getFld().getCodeSqlField()
                + " =?  WHERE " + OrderMeetingProduct.T.ORDERMEETINGID.getFld().getCodeSqlField()
                + " =? AND " + OrderMeetingProduct.T.SUPPLIERID.getFld().getCodeSqlField() + " =?";
        BeanBase.executeUpdate(sql, OrderMeetingProductStatus.DELETE.getLine().getKey(), omp.getOrdermeetingid(), omp.getSupplierid());
    }

    public void addProducts(Integer id, Integer pkey, List<AllProductsView> list) {
        for (AllProductsView allProductsView : list) {
            SQL sql = new SQL() {
                {
                    SELECT(OrderMeetingProduct.class)
                            .FROM(OrderMeetingProduct.class).
                            WHERE(OrderMeetingProduct.T.PRODUCTID, "=?", allProductsView.getId()).
                            WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id);
                }
            };
            OrderMeetingProduct o = Query.sql(sql).query(OrderMeetingProduct.class);
            if (o != null) {
                o.setMoq(allProductsView.getOldMoq());
                o.setPrice(allProductsView.getOldPrice());
                o.setNewmoq(allProductsView.getMoq());
                o.setNewprice(allProductsView.getNewPrice());
                o.setTargetCount(allProductsView.getAims());
                o.setUpdatedTime(Env.getTranBeginTime());
                o.upd();
            } else {
                o = new OrderMeetingProduct();
                o.setOrdermeetingid(id);
                o.setSupplierid(pkey);
                o.setProductid(allProductsView.getId());
                o.setMoq(allProductsView.getOldMoq());
                o.setPrice(allProductsView.getOldPrice());
                o.setNewmoq(allProductsView.getMoq());
                o.setNewprice(allProductsView.getNewPrice());
                o.setTargetCount(allProductsView.getAims());
                o.setStatus(OrderMeetingProductStatus.ON.getLine().getKey());
                o.setUpdatedTime(Env.getTranBeginTime());
                o.ins();
            }
        }
    }
    /**
    *@Description:  销售明细 查询明细列表
    *@date 2018/12/3 15:25
    *@anthor wilson zhang
    */

    public Page salesDetailslist(Integer start, Integer limit, Integer status, String pruductname){

        return  new Page(null, start, limit, 0);
    }
}
