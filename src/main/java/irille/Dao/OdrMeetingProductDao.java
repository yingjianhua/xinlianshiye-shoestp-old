package irille.Dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingProductStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeeting.T;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;
import irille.view.Manage.OdrMeeting.Sale.ColorView;
import irille.view.Manage.OdrMeeting.Sale.OdrSalesDetailsView;
import irille.view.Manage.OdrMeeting.Sale.SizeView;
import irille.view.Manage.OdrMeeting.initiatedActivity.AllProductsView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderGoodsView;
import irille.view.Page;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37 */
public class OdrMeetingProductDao {

  /**
   * @Description: 检查是否有条件参加添加该商品到订购会
   *
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
    //                .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.ACTIVITY)
    // //状态通过
    //                .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetingId)
    //        ;
    return query.queryCount() > 0;
  }

  public Page getOrderGoodsList(
      Integer start,
      Integer limit,
      Integer id,
      Integer status,
      String inputContent,
      Integer supplierId,
      Integer supplier) {
    SQL mop =
        new SQL() {
          {
            SELECT(OrderMeetingProduct.class)
                .FROM(OrderMeetingProduct.class)
                .WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id)
                .WHERE(T.SUPPLIERID, "=?", supplierId)
                .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingProduct.T.ORDERMEETINGID);
          }
        };
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql;
    if (Query.sql(mop).query(OrderMeetingProduct.class) != null) {
      sql =
          new SQL() {
            {
              SELECT(
                      OrderMeetingProduct.T.PRODUCTID,
                      PdtProduct.T.PICTURE,
                      PdtProduct.T.NAME,
                      OrderMeetingProduct.T.BILLINGSTATUS,
                      OrderMeetingProduct.T.PRICE,
                      OrderMeetingProduct.T.MOQ,
                      OrderMeetingProduct.T.NEWPRICE,
                      OrderMeetingProduct.T.NEWMOQ,
                      OrderMeetingProduct.T.TARGET_COUNT,
                      OrderMeetingProduct.T.STATUS)
                  .FROM(OrderMeetingProduct.class)
                  .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, OrderMeetingProduct.T.PRODUCTID)
                  .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingProduct.T.ORDERMEETINGID)
                  .WHERE(OrderMeeting.T.PKEY, "=?", id)
                  .WHERE(
                      OrderMeetingProduct.T.STATUS,
                      "<>?",
                      OrderMeetingProductStatus.IRREGULARITIESDELETE.getLine().getKey())
                  .WHERE(
                      OrderMeetingProduct.T.STATUS,
                      "<>?",
                      OrderMeetingProductStatus.DELETE.getLine().getKey());
              if (supplier != null) {
                WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", supplier);
              }
              if (inputContent != null) {
                WHERE(PdtProduct.T.NAME, "like'%" + inputContent + "%'");
              }
              if (status != null) {
                switch (status) {
                  case 1:
                    {
                      WHERE(OrderMeeting.T.SUPPLIERID, "=", OrderMeetingProduct.T.SUPPLIERID);
                      break;
                    }
                  case 2:
                    {
                      WHERE(OrderMeeting.T.SUPPLIERID, "<>", OrderMeetingProduct.T.SUPPLIERID);
                      break;
                    }
                }
              }
            }
          };
    } else {
      sql =
          new SQL() {
            {
              SELECT(
                      OrderMeetingProduct.T.PRODUCTID,
                      PdtProduct.T.PICTURE,
                      PdtProduct.T.NAME,
                      OrderMeetingProduct.T.BILLINGSTATUS,
                      OrderMeetingProduct.T.PRICE,
                      OrderMeetingProduct.T.MOQ,
                      OrderMeetingProduct.T.NEWPRICE,
                      OrderMeetingProduct.T.NEWMOQ,
                      OrderMeetingProduct.T.TARGET_COUNT,
                      OrderMeetingProduct.T.STATUS)
                  .FROM(OrderMeetingProduct.class)
                  .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, OrderMeetingProduct.T.PRODUCTID)
                  .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingProduct.T.ORDERMEETINGID)
                  .WHERE(OrderMeeting.T.PKEY, "=?", id)
                  .WHERE(
                      OrderMeetingProduct.T.STATUS,
                      "<>?",
                      OrderMeetingProductStatus.IRREGULARITIESDELETE.getLine().getKey())
                  .WHERE(
                      OrderMeetingProduct.T.STATUS,
                      "<>?",
                      OrderMeetingProductStatus.DELETE.getLine().getKey());
              if (supplierId != null) {
                WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", supplierId);
              }
              if (inputContent != null) {
                WHERE(PdtProduct.T.NAME, "like'%" + inputContent + "%'");
              }
              if (status != null) {
                switch (status) {
                  case 1:
                    {
                      WHERE(OrderMeeting.T.SUPPLIERID, "=", OrderMeetingProduct.T.SUPPLIERID);
                      break;
                    }
                  case 2:
                    {
                      WHERE(OrderMeeting.T.SUPPLIERID, "<>", OrderMeetingProduct.T.SUPPLIERID);
                      break;
                    }
                }
              }
            }
          };
    }

    Integer count = Query.sql(sql).queryCount();
    List<OrderGoodsView> viewList =
        Query.sql(sql).limit(start, limit).queryMaps().stream()
            .map(
                o -> {
                  OrderGoodsView view = new OrderGoodsView();
                  view.setId(
                      Integer.valueOf(
                          String.valueOf(
                              o.get(OrderMeetingProduct.T.PRODUCTID.getFld().getCodeSqlField()))));
                  if (o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != null
                      || o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != "") {
                    view.setImage(
                        String.valueOf(o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()))
                            .split(",")[0]);
                  }
                  view.setProductName(
                      String.valueOf(o.get(PdtProduct.T.NAME.getFld().getCodeSqlField())));
                  //            if
                  // (o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()).equals(o.get("productSupplier"))) {
                  //                view.setMode("自有");
                  //            } else {
                  //                view.setMode("合作商");
                  //            }
                  view.setMode(
                      Byte.valueOf(
                          String.valueOf(
                              o.get(
                                  OrderMeetingProduct.T
                                      .BILLINGSTATUS
                                      .getFld()
                                      .getCodeSqlField()))));
                  view.setOriginalPurchasePrice(
                      ((BigDecimal) o.get(OrderMeetingProduct.T.PRICE.getFld().getCodeSqlField()))
                          .doubleValue());
                  view.setOriginalOrderQuantity(
                      Integer.parseInt(
                          o.get(OrderMeetingProduct.T.MOQ.getFld().getCodeSqlField()).toString()));
                  view.setOrderPrice(
                      Integer.parseInt(
                          o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField())
                              .toString()));
                  view.setOrderQuantityorderPrice(
                      ((BigDecimal)
                              o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField()))
                          .doubleValue());
                  view.setTargetAmount(
                      (Integer)
                          o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()));
                  view.setStatus(
                      (Byte) o.get(OrderMeetingProduct.T.STATUS.getFld().getCodeSqlField()));
                  return view;
                })
            .collect(Collectors.toList());
    return new Page(viewList, start, limit, count);
  }

  public void updateStatus(Integer id) {
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeetingProduct.class)
                .FROM(OrderMeetingProduct.class)
                .WHERE(OrderMeetingProduct.T.PRODUCTID, "=?", id);
          }
        };
    OrderMeetingProduct order = Query.sql(sql).query(OrderMeetingProduct.class);
    if (order.getStatus() == 0) {
      order.setStatus(OrderMeetingProductStatus.ON.getLine().getKey());
    } else {
      order.setStatus(OrderMeetingProductStatus.DEFAULT.getLine().getKey());
    }
    order.setUpdatedTime(Env.getTranBeginTime());
    order.upd();
  }

  public void removePorduct(Integer id) {
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeetingProduct.class)
                .FROM(OrderMeetingProduct.class)
                .WHERE(OrderMeetingProduct.T.PRODUCTID, "=?", id);
          }
        };
    OrderMeetingProduct mo = Query.sql(sql).query(OrderMeetingProduct.class);
    mo.setStatus(OrderMeetingProductStatus.DELETE.getLine().getKey());
    mo.setUpdatedTime(Env.getTranBeginTime());
    mo.upd();
  }

  public List<AllProductsView> getProducts(Integer id) {
    SQL sql =
        new SQL() {
          {
            SELECT(
                    PdtProduct.T.PKEY,
                    PdtProduct.T.NAME,
                    PdtProduct.T.PICTURE,
                    PdtProduct.T.CODE,
                    PdtProduct.T.CUR_PRICE,
                    PdtProduct.T.MIN_OQ)
                .SELECT(
                    OrderMeetingProduct.T.PRICE,
                    OrderMeetingProduct.T.MOQ,
                    OrderMeetingProduct.T.NEWPRICE,
                    OrderMeetingProduct.T.NEWMOQ,
                    OrderMeetingProduct.T.TARGET_COUNT)
                .FROM(PdtProduct.class)
                .LEFT_JOIN(
                    OrderMeetingProduct.class, OrderMeetingProduct.T.PRODUCTID, PdtProduct.T.PKEY)
                .WHERE(PdtProduct.T.SUPPLIER, "=?", id);
          }
        };
    List<AllProductsView> listView =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  AllProductsView view = new AllProductsView();
                  view.setId(
                      Integer.valueOf(
                          o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()).toString()));
                  if (o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != null
                      && o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()) != "") {
                    view.setImage(
                        String.valueOf(o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()))
                            .split(",")[0]);
                  } else {
                    view.setImage("");
                  }
                  view.setName(String.valueOf(o.get(PdtProduct.T.NAME.getFld().getCodeSqlField())));
                  view.setCode(String.valueOf(o.get(PdtProduct.T.CODE.getFld().getCodeSqlField())));
                  if (o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()) != null
                      && o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()) != "") {
                    view.setOldPrice(
                        BigDecimal.valueOf(
                            Double.valueOf(
                                String.valueOf(
                                    o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField())))));
                  } else {
                    view.setOldPrice(new BigDecimal(0));
                  }
                  if (o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()) != null
                      && o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()) != "") {
                    view.setOldMoq(
                        Integer.valueOf(
                            String.valueOf(o.get(PdtProduct.T.MIN_OQ.getFld().getCodeSqlField()))));
                  } else {
                    view.setOldMoq(0);
                  }
                  if (o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField()) != null
                      && o.get(OrderMeetingProduct.T.NEWPRICE.getFld().getCodeSqlField()) != "") {
                    view.setNewPrice(
                        BigDecimal.valueOf(
                            Double.valueOf(
                                String.valueOf(
                                    o.get(
                                        OrderMeetingProduct.T
                                            .NEWPRICE
                                            .getFld()
                                            .getCodeSqlField())))));
                  } else {
                    view.setNewPrice(new BigDecimal(0));
                  }
                  if (o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()) != null
                      && o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()) != "") {
                    view.setMoq(
                        Integer.valueOf(
                            String.valueOf(
                                o.get(OrderMeetingProduct.T.NEWMOQ.getFld().getCodeSqlField()))));
                  } else {
                    view.setMoq(0);
                  }
                  if (o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField()) != null
                      && o.get(OrderMeetingProduct.T.TARGET_COUNT.getFld().getCodeSqlField())
                          != "") {
                    view.setAims(
                        Integer.valueOf(
                            String.valueOf(
                                o.get(
                                    OrderMeetingProduct.T
                                        .TARGET_COUNT
                                        .getFld()
                                        .getCodeSqlField()))));
                  } else {
                    view.setAims(0);
                  }
                  return view;
                })
            .collect(Collectors.toList());
    return listView;
  }

  public List getAddedProducts(Integer id, Integer supplierId) {
    List<OrderMeetingProduct> list =
        BeanBase.list(
            OrderMeetingProduct.class,
            OrderMeetingProduct.T.ORDERMEETINGID
                + "="
                + id
                + " AND "
                + OrderMeetingProduct.T.STATUS
                + "<>"
                + OrderMeetingProductStatus.DELETE.getLine().getKey()
                + " AND "
                + OrderMeetingProduct.T.SUPPLIERID
                + "="
                + supplierId,
            false);
    List view = new ArrayList();
    for (OrderMeetingProduct orderMeetingProduct : list) {
      view.add(orderMeetingProduct.getProductid());
    }
    return view;
  }

  /**
   * @Description: 逻辑删除 参加订购会合作商 所对应的商品
   *
   * @date 2018/11/22 11:14
   * @anthor wilson zhang
   */
  public static void deletejoinOdr(OrderMeetingProduct omp) {
    String sql =
        "update "
            + OrderMeetingProduct.TB.getCodeSqlTb()
            + " set "
            + OrderMeetingProduct.T.STATUS.getFld().getCodeSqlField()
            + " =?  WHERE "
            + OrderMeetingProduct.T.ORDERMEETINGID.getFld().getCodeSqlField()
            + " =? AND "
            + OrderMeetingProduct.T.SUPPLIERID.getFld().getCodeSqlField()
            + " =?";
    BeanBase.executeUpdate(
        sql,
        OrderMeetingProductStatus.DELETE.getLine().getKey(),
        omp.getOrdermeetingid(),
        omp.getSupplierid());
  }

  public void addProducts(Integer id, Integer pkey, List<AllProductsView> list) {
    SQL mop =
        new SQL() {
          {
            SELECT(OrderMeeting.class)
                .FROM(OrderMeeting.class)
                .WHERE(T.PKEY, "=?", id)
                .WHERE(T.SUPPLIERID, "=?", pkey);
          }
        };
    if (list.size() > 0) {
      SQL database =
          new SQL() {
            {
              SELECT(OrderMeetingProduct.T.PRODUCTID)
                  .FROM(OrderMeetingProduct.class)
                  .WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id)
                  .WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", pkey);
            }
          };
      List<OrderMeetingProduct> orderMeetingProductList =
          Query.sql(database).queryList(OrderMeetingProduct.class);
      List<Integer> db = new ArrayList<>();
      List<AllProductsView> newDb = new ArrayList<>();
      List<AllProductsView> updDb = new ArrayList<>();
      List<Integer> integerList = new ArrayList<>(); // 记录修改的产品id
      List<Integer> delDb = new ArrayList<>();
      for (OrderMeetingProduct orderMeetingProduct : orderMeetingProductList) {
        db.add(orderMeetingProduct.getProductid());
      }
      for (AllProductsView allProductsView : list) {
        if (db.contains(allProductsView.getId())) {
          updDb.add(allProductsView);
          integerList.add(allProductsView.getId());
        } else {
          newDb.add(allProductsView);
        }
      }
      for (Integer integer : db) {
        if (!(integerList.contains(integer))) {
          delDb.add(integer);
        }
      }
      for (Integer integer : delDb) {
        SQL del =
            new SQL() {
              {
                UPDATE(OrderMeetingProduct.class)
                    .SET(OrderMeetingProduct.T.STATUS, OrderMeetingProductStatus.DELETE)
                    .WHERE(OrderMeetingProduct.T.PRODUCTID, "=?", integer)
                    .WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id)
                    .WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", pkey);
              }
            };
        System.out.println(Query.sql(del).executeUpdate());
      }
      for (AllProductsView allProductsView : updDb) {
        SQL upd =
            new SQL() {
              {
                SELECT(OrderMeetingProduct.class)
                    .SELECT(T.SUPPLIERID)
                    .FROM(OrderMeetingProduct.class)
                    .WHERE(OrderMeetingProduct.T.PRODUCTID, "=?", allProductsView.getId())
                    .WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id)
                    .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingProduct.T.ORDERMEETINGID);
              }
            };
        OrderMeetingProduct orderMeetingProduct = Query.sql(upd).query(OrderMeetingProduct.class);
        orderMeetingProduct.setMoq(allProductsView.getOldMoq());
        orderMeetingProduct.setPrice(allProductsView.getOldPrice());
        orderMeetingProduct.setNewmoq(allProductsView.getMoq());
        orderMeetingProduct.setNewprice(allProductsView.getNewPrice());
        orderMeetingProduct.setTargetCount(allProductsView.getAims());
        orderMeetingProduct.setStatus(OrderMeetingProductStatus.ON.getLine().getKey());
        orderMeetingProduct.setUpdatedTime(Env.getTranBeginTime());
        if (Query.sql(mop).query(OrderMeetingProduct.class) != null) {
          orderMeetingProduct.setBillingstatus(OrderMeetingProductStatus.OWN.getLine().getKey());
        } else {
          orderMeetingProduct.setBillingstatus(
              OrderMeetingProductStatus.PARTNER.getLine().getKey());
        }
        orderMeetingProduct.upd();
      }
      for (AllProductsView allProductsView : newDb) {
        OrderMeetingProduct orderMeetingProduct = new OrderMeetingProduct();
        orderMeetingProduct.setOrdermeetingid(id);
        orderMeetingProduct.setSupplierid(pkey);
        orderMeetingProduct.setProductid(allProductsView.getId());
        orderMeetingProduct.setMoq(allProductsView.getOldMoq());
        orderMeetingProduct.setPrice(allProductsView.getOldPrice());
        orderMeetingProduct.setNewmoq(allProductsView.getMoq());
        orderMeetingProduct.setNewprice(allProductsView.getNewPrice());
        orderMeetingProduct.setTargetCount(allProductsView.getAims());
        orderMeetingProduct.setStatus(OrderMeetingProductStatus.ON.getLine().getKey());
        orderMeetingProduct.setUpdatedTime(Env.getTranBeginTime());
        if (Query.sql(mop).queryMaps().size() > 0) {
          orderMeetingProduct.setBillingstatus(OrderMeetingProductStatus.OWN.getLine().getKey());
        } else {
          orderMeetingProduct.setBillingstatus(
              OrderMeetingProductStatus.PARTNER.getLine().getKey());
        }
        orderMeetingProduct.ins();
      }
    } else {
      SQL sql =
          new SQL() {
            {
              SELECT(OrderMeetingProduct.class)
                  .FROM(OrderMeetingProduct.class)
                  .WHERE(
                      OrderMeetingProduct.T.STATUS,
                      "<>?",
                      OrderMeetingProductStatus.DELETE.getLine().getKey())
                  .WHERE(OrderMeetingProduct.T.SUPPLIERID, "=?", pkey)
                  .WHERE(OrderMeetingProduct.T.ORDERMEETINGID, "=?", id);
            }
          };
      List<OrderMeetingProduct> listomp = Query.sql(sql).queryList(OrderMeetingProduct.class);
      for (OrderMeetingProduct orderMeetingProduct : listomp) {
        orderMeetingProduct.setStatus(OrderMeetingProductStatus.DELETE.getLine().getKey());
        orderMeetingProduct.upd();
      }
    }
  }

  /**
   * @Description: 销售明细 查询明细列表
   *
   * @date 2018/12/3 15:25
   * @anthor wilson zhang
   */
  private boolean isOmt(Integer omtId, Integer supplier) {
    StringBuilder sb = new StringBuilder();
    SQL sql = new SQL();
    sb.append(
        "SELECT *\n"
            + "FROM order_meeting orderMeeting\n"
            + "WHERE orderMeeting.pkey = "
            + omtId
            + " \n"
            + "AND orderMeeting.supplierid = "
            + supplier
            + "");
    sql.SELECT(sb.toString());
    if (Query.sql(sql).queryCount() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public Page salesDetailslist(
      Integer start,
      Integer limit,
      Integer id,
      String input,
      Integer status,
      Integer productId,
      Integer supplierId) {
    SQL sql = new SQL();
    StringBuilder sb = new StringBuilder();
    sb.append(
        "MeetingProduct.price AS price,\n"
            + "\tMeetingProduct.STATUS AS STATUS,\n"
            + "\tMeetingProduct.billingstatus AS billingstatus,\n"
            + "\tProduct.picture AS picture,\n"
            + "\tProduct.NAME AS NAME,\n"
            + "\tProduct.pkey AS productPkey,\n"
            + "\torderMeeting.`status` AS omtStatus,\n"
            + "\ttotalQty.qty,\n"
            + "\t(\n"
            + "SELECT\n"
            + "\tcount( * ) \n"
            + "FROM\n"
            + "\todr_order_line OrderLine\n"
            + "\tLEFT JOIN odr_order OdrOrder ON OdrOrder.pkey = OrderLine.main\n"
            + "\tLEFT JOIN pdt_spec PdtSpec ON PdtSpec.pkey = OrderLine.spec \n"
            + "WHERE\n"
            + "\tPdtSpec.product = productPkey \n"
            + "GROUP BY\n"
            + "\tOdrOrder.purchase \n"
            + "\t) AS person,\n"
            + "\ttotalAmt.amt \n"
            + "FROM\n"
            + "\torder_meeting_product MeetingProduct\n"
            + "\tLEFT JOIN pdt_product Product ON Product.pkey = MeetingProduct.productid\n"
            + "\tLEFT JOIN order_meeting orderMeeting ON MeetingProduct.ordermeetingid\n"
            + "\tLEFT JOIN (\n"
            + "SELECT\n"
            + "\tPdtSpec.product AS productid,\n"
            + "\tsum( OrderLine.qty ) AS qty \n"
            + "FROM\n"
            + "\todr_order_line OrderLine\n"
            + "\tLEFT JOIN pdt_spec PdtSpec ON PdtSpec.pkey = OrderLine.spec \n"
            + "GROUP BY\n"
            + "\tPdtSpec.product \n"
            + "\t) totalQty ON totalQty.productid = Product.pkey\n"
            + "\tLEFT JOIN (\n"
            + "SELECT\n"
            + "\tPdtSpec.product AS productid,\n"
            + "\tsum( OrderLine.subtotal ) AS amt \n"
            + "FROM\n"
            + "\todr_order_line OrderLine\n"
            + "\tLEFT JOIN pdt_spec PdtSpec ON PdtSpec.pkey = OrderLine.spec \n"
            + "GROUP BY\n"
            + "\tPdtSpec.product \n"
            + "\t) totalAmt ON totalAmt.productid = Product.pkey \n"
            + "WHERE\n"
            + "\tMeetingProduct.ordermeetingid = "
            + id
            + "\n"
            + "\tAND totalQty.qty is not null");
    if (productId != null) {
      sb.append(" AND MeetingProduct.productid=" + productId + "");
    }
    if (input != null) {
      sb.append(" AND Product.NAME like '%" + input + "%'");
    }
    if (status != null) {
      sb.append(" AND MeetingProduct.billingstatus=" + status + "");
    }
    if (!isOmt(id, supplierId)) {
      sb.append(" AND MeetingProduct.supplierid=" + supplierId + "");
    }

    sql.SELECT(sb.toString());

    SQL productSpec =
        new SQL() {
          {
            SELECT("SUM(" + OdrOrderLine.T.QTY + ")", "qty") // 数量
                .SELECT("SUM(" + OdrOrderLine.T.SUBTOTAL + ")", "subTotal") // 总金额
                .SELECT(OdrOrderLine.T.MAIN, "orderPkey") // 订单pkey
                .SELECT(PdtColor.T.PKEY, "colorPkey") // 颜色pkey
                .SELECT(PdtColor.T.NAME, "colorName") // 颜色名称
                .SELECT(PdtSize.T.NAME, "sizeName") // 尺寸名称
                .SELECT(PdtSpec.T.PRODUCT, "productId")
                .SELECT(PdtSpec.T.PICS); // 颜色图片
            SQL sql =
                new SQL() {
                  {
                    SELECT("COUNT(*)")
                        .FROM(OrderMeetingOrder.class)
                        .WHERE(OrderMeetingOrder.T.ORDERID, "=?", "orderPkey")
                        .GROUP_BY(OrderMeetingOrder.T.BUYERS);
                  }
                };
            SELECT(sql, "person")
                .FROM(OdrOrderLine.class)
                .LEFT_JOIN(
                    OrderMeetingOrder.class, OrderMeetingOrder.T.ORDERID, OdrOrderLine.T.MAIN)
                .LEFT_JOIN(PdtSpec.class, PdtSpec.T.PKEY, OdrOrderLine.T.SPEC)
                .LEFT_JOIN(PdtColor.class, PdtColor.T.PKEY, PdtSpec.T.COLOR)
                .LEFT_JOIN(PdtSize.class, PdtSize.T.PKEY, PdtSpec.T.SIZE)
                .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", id)
                .WHERE(OrderMeetingOrder.T.BILLINGSTATUS, "=?", Sys.OYn.YES.getLine().getKey())
                .GROUP_BY(OdrOrderLine.T.SPEC);
          }
        };
    Integer country = Query.sql(sql).queryMaps().size();
    List<OdrSalesDetailsView> listSalDetils =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OdrSalesDetailsView salesDetails = new OdrSalesDetailsView(); // 产品
                  salesDetails.setQty(Integer.valueOf(String.valueOf(o.get("qty")))); // 数量
                  salesDetails.setPersons(Integer.valueOf(String.valueOf(o.get("person")))); // 人数
                  salesDetails.setSubTotal(
                      BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get("amt"))))); // 总金额
                  salesDetails.setPkey(
                      Integer.valueOf(String.valueOf(o.get("productPkey")))); // 产品pkey
                  salesDetails.setProductName(String.valueOf(o.get("NAME"))); // 产品名称
                  if (o.get("picture") != null) {
                    salesDetails.setProductImage(
                        String.valueOf(o.get("picture")).split(",")[0]); // 产品图片
                  }
                  salesDetails.setBillingStatus(
                      Integer.valueOf(String.valueOf(o.get("billingstatus")))); // 自有/合作商状态
                  salesDetails.setMeetingPrice(
                      BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get("price"))))); // 订购会价
                  salesDetails.setStatus(Integer.valueOf(String.valueOf(o.get("STATUS")))); // 上下架状态
                  salesDetails.setActiveSatatus(
                      Integer.valueOf(String.valueOf(o.get("omtStatus")))); // 活动状态

                  List<ColorView> colorList =
                      Query.sql(productSpec).queryMaps().stream()
                          .filter(
                              new Predicate<Map<String, Object>>() {
                                @Override
                                public boolean test(Map<String, Object> stringObjectMap) {
                                  if (Integer.valueOf(
                                          String.valueOf(stringObjectMap.get("productId")))
                                      .equals(
                                          Integer.valueOf(String.valueOf(o.get("productPkey"))))) {
                                    return true;
                                  } else {
                                    return false;
                                  }
                                }
                              })
                          .map(
                              p -> {
                                ColorView color = new ColorView();
                                color.setColor(String.valueOf(p.get("colorName")));
                                if (p.get(PdtSpec.T.PICS.getFld().getCodeSqlField()) != null) {
                                  color.setImage(
                                      String.valueOf(
                                              p.get(PdtSpec.T.PICS.getFld().getCodeSqlField()))
                                          .split(",")[0]); // 产品图片
                                }
                                color.setProductId(
                                    Integer.valueOf(String.valueOf(p.get("productId"))));
                                List<SizeView> sizeList =
                                    Query.sql(productSpec).queryMaps().stream()
                                        .filter(
                                            new Predicate<Map<String, Object>>() {
                                              @Override
                                              public boolean test(
                                                  Map<String, Object> stringObjectMap) {
                                                if (Integer.valueOf(
                                                            String.valueOf(
                                                                stringObjectMap.get("productId")))
                                                        .intValue()
                                                    == Integer.valueOf(
                                                            String.valueOf(o.get("productPkey")))
                                                        .intValue()) {
                                                  return true;
                                                } else {
                                                  return false;
                                                }
                                              }
                                            })
                                        .map(
                                            s -> {
                                              SizeView size = new SizeView();
                                              size.setSizeName(String.valueOf(s.get("sizeName")));
                                              size.setSpecQuantity(
                                                  Integer.valueOf(String.valueOf(s.get("qty"))));
                                              size.setSubToat(
                                                  BigDecimal.valueOf(
                                                      Double.valueOf(
                                                          String.valueOf(s.get("subTotal")))));
                                              return size;
                                            })
                                        .collect(Collectors.toList());
                                color.setSize(sizeList);
                                return color;
                              })
                          .collect(Collectors.toList());
                  salesDetails.setColor(colorList);
                  return salesDetails;
                })
            .collect(Collectors.toList());
    return new Page(listSalDetils, start, limit, country);
  }
}
