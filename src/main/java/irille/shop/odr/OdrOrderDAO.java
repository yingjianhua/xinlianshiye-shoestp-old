package irille.shop.odr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingOrder;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.ColorView;
import irille.homeAction.usr.dto.OdrView;
import irille.homeAction.usr.dto.ProductView;
import irille.homeAction.usr.dto.SpecView;
import irille.platform.odr.view.OdrOrder.ConditionView;
import irille.platform.odr.view.OdrOrder.OdrOrderView;
import irille.platform.odr.view.OdrOrder.StatusView;
import irille.platform.odr.view.OdrOrder.TypeView;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.util.excel.BaseExcel;
import irille.sellerAction.odr.OdrExportExcelAction;
import irille.shop.odr.Odr.OdrState;
import irille.shop.odr.OdrOrder.T;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltFreightSellerDAO;
import irille.shop.plt.PltPay;
import irille.shop.plt.PltPayDAO;
import irille.shop.prm.Prm;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.Usr;
import irille.shop.usr.Usr.OAddress;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseLine;
import irille.shop.usr.UsrPurchaseLineDAO;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.odr.HistoryView;
import irille.view.odr.OrderLineView;
import irille.view.odr.OrderSearchView;
import irille.view.odr.OrderView;
import irille.view.plt.FreightSellerlistView;
import irille.view.plt.PayTypeView;
import irille.view.usr.AddressView;
import irille.view.usr.SupplierView;

public class OdrOrderDAO {

  private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final LogMessage LOG = new LogMessage(OdrOrderDAO.class);

  public static OrderView findSummary(String number, Integer purchase, Language lang)
      throws JSONException, IOException {
    OdrOrder order = loadByOrderNumPurchase(number, purchase);
    if (order == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, number));
      //    	throw LOG.err("not exists", "订单编号为[{0}]且采购商pkey为[{1}]的订单不存在", number, purchase);
    }
    List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
    OrderView view = new OrderView();
    PltErate currency = order.gtCurrency();
    view.setNumber(order.getOrderNum());
    view.setTotal(order.getPriceTotal(), currency, true, false);
    List<OrderLineView> lines = new ArrayList<>();
    Integer itemsCount = 0;
    for (OdrOrderLine line : odrLineList) {
      lines.add(OrderLineView.build(line, lang, currency));
      itemsCount += line.getQty();
    }
    view.setOrderType(Integer.valueOf(order.getType()));
    view.setItemsCount(itemsCount);
    view.setCurrency(currency.getCurName());
    view.setStatus(order.getState());
    view.setPayType(
        new PayTypeView() {
          {
            PltPay payType = order.gtPayType();
            setId(payType.getPkey());
            setMode(payType.gtMode());
            setName(payType.gtMode());
            setSetting(payType.getPaysetting());
          }
        });
    view.setSupplier(
        new SupplierView() {
          {
            UsrSupplier supplier = order.gtSupplier();
            setName(supplier.getName());
            setCompanyAddr(supplier.getCompanyAddr(lang));
          }
        });
    return view;
  }

  /**
   * 返回订单详情页需要的数据PC和Mobile共用
   *
   * @param orderNum 订单号
   * @param purchase 采购商pkey
   * @param lang 语言
   * @author yingjianhua
   */
  public static OrderView detail(String orderNum, UsrPurchase purchase, Language lang)
      throws JSONException, JsonParseException, JsonMappingException, IOException {
    OdrOrder order = loadByOrderNumPurchase(orderNum, purchase.getPkey());
    if (order == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, orderNum));
      //          	throw LOG.err("not exists", "订单编号为[{0}]的订单不存在", orderNum,
      // purchase);
    }
    List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
    OrderView view = new OrderView();
    PltErate currency = order.gtCurrency();
    view.setNumber(order.getOrderNum());
    view.setDate(order.getTime());
    view.setStatus(order.getState());
    view.setPayType(
        new PayTypeView() {
          {
            PltPay payType = order.gtPayType();
            setId(payType.getPkey());
            setMode(payType.gtMode());
            setName(payType.gtMode());
            setSetting(payType.getPaysetting());
          }
        });
    view.setPayContent(order.getPaycontent());
    // TODO 运费和保险费的币种根据什么来确定?
    view.setShippingCharges(order.getFreightPrice(), currency, true, false);
    view.setInsurance(order.getInsurancePrice(), currency, true, false);
    view.setShippingChargesAndInsurance(
        order.getFreightPrice().add(order.getInsurancePrice()), currency, true, false);
    view.setTotal(order.getPriceTotal(), currency, true, false);
    view.setDeliveryType(order.getDelivery());
    view.setShipName(order.getName());
    view.setShipAddress(new ObjectMapper().readValue(order.getAddress(), AddressView.class));
    view.setBillAddress(new ObjectMapper().readValue(order.getBillingAddress(), AddressView.class));
    view.setContactNum(order.getPhone());
    view.setCancelReason(order.getOdrCancel());
    view.setRemark(order.getOdrRemarks());
    view.setExpressNum(order.getExpressNum());
    view.setPagRemark(order.getPagRemarks());

    List<OrderLineView> lines = new ArrayList<>();
    Integer itemsCount = 0;
    for (OdrOrderLine line : odrLineList) {
      lines.add(OrderLineView.build(line, lang, currency));
      itemsCount += line.getQty();
    }
    List<HistoryView> historys = new ArrayList<>();
    for (OdrHistory line : OdrHistoryDAO.listByOrder(order.getPkey())) {
      historys.add(HistoryView.build(line));
    }
    view.setLines(lines);
    view.setHistorys(historys);
    view.setItemsCount(itemsCount);
    view.setSubtotal(order.getProdPrice(), currency, false, false);
    //		view.setSubtotal(subtotal, currency, true, false);
    view.setCurrency(currency.getCurName());
    return view;
  }

  /**
   * 返回订单详情页需要的数据打印
   *
   * @param orderNum 订单号
   * @param purchase 采购商pkey
   * @param lang 语言
   * @author yingjianhua
   */
  public static OrderView detailprint(String orderNum, Language lang)
      throws JSONException, JsonParseException, JsonMappingException, IOException {
    OdrOrder order = OdrOrder.chkUniqueOrderNum(false, orderNum);
    if (order == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, orderNum));
      //    	throw LOG.err("not exists", "订单编号为[{0}]的订单不存在", orderNum);
    }
    List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
    OrderView view = new OrderView();
    PltErate currency = order.gtCurrency();
    view.setNumber(order.getOrderNum());
    UsrPurchase upc = BeanBase.load(UsrPurchase.class, order.getPurchase());
    view.setUsrEmail(upc.getEmail());
    view.setDate(order.getTime());
    view.setStatus(order.getState());
    view.setTotal(order.getPriceTotal(), currency, true, false);
    view.setInsurance(order.getInsurancePrice(), currency, true, false);
    view.setShippingCharges(order.getFreightPrice(), currency, true, false);
    view.setRemark(order.getOdrRemarks());
    view.setUsrlang(lang.name());

    view.setShipAddress(new ObjectMapper().readValue(order.getAddress(), AddressView.class));
    view.setBillAddress(new ObjectMapper().readValue(order.getBillingAddress(), AddressView.class));
    view.setPagRemark(order.getPagRemarks());
    view.setExpressNum(order.getExpressNum());
    view.setPayType(
        new PayTypeView() {
          {
            PltPay payType = order.gtPayType();
            setId(payType.getPkey());
            setMode(payType.gtMode());
            setName(payType.gtMode());
            setSetting(payType.getPaysetting());
          }
        });
    UsrSupplier usl = BeanBase.load(UsrSupplier.class, order.getSupplier());
    view.setSupplierShowName(usl.getShowName(lang));
    view.setPayContent(order.getPaycontent());
    view.setShippingChargesAndInsurance(
        order.getFreightPrice().add(order.getInsurancePrice()), currency, true, false);
    view.setDeliveryType(order.getDelivery());
    view.setShipName(order.getName());
    view.setContactNum(order.getPhone());
    view.setCancelReason(order.getOdrCancel());

    List<OrderLineView> lines = new ArrayList<>();
    Integer itemsCount = 0;
    for (OdrOrderLine line : odrLineList) {
      lines.add(OrderLineView.build(line, lang, currency));
      itemsCount += line.getQty();
    }
    List<HistoryView> historys = new ArrayList<>();
    for (OdrHistory line : OdrHistoryDAO.listByOrder(order.getPkey())) {
      historys.add(HistoryView.build(line));
    }
    view.setLines(lines);
    view.setHistorys(historys);
    view.setItemsCount(itemsCount);
    //	view.setSubtotal(subtotal, currency, true, false);
    view.setSubtotal(order.getProdPrice(), currency, true, false);
    view.setCurrency(currency.getCurName());
    return view;
  }

  /**
   * 返回订单详情页需要的数据给采购商
   *
   * @param orderNum 订单号
   * @param supplier 供应商pkey
   * @param lang 语言
   * @author yingjianhua
   */
  public static OrderView findViewByOrderNumSupplier4Supplier(
      String orderNum, UsrSupplier supplier, Language lang)
      throws JSONException, JsonParseException, JsonMappingException, IOException {
    OdrOrder order = loadByOrderNumSupplier(orderNum, supplier.getPkey());
    if (order == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, orderNum));
      //    	throw LOG.err("not exists", "订单编号为[{0}]且供应商pkey为[{1}]的订单不存在", orderNum, supplier);
    }
    List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
    OrderView view = new OrderView();
    PltErate currency = order.gtCurrency();
    view.setNumber(order.getOrderNum());
    view.setDate(order.getTime());
    view.setStatus(order.getState());
    view.setPayType(
        new PayTypeView() {
          {
            PltPay payType = order.gtPayType();
            setId(payType.getPkey());
            setMode(payType.gtMode());
            setName(payType.gtMode());
            setSetting(payType.getPaysetting());
          }
        });
    //		view.setPayType(order.gtPayType().getMode());
    UsrPurchase upc = BeanBase.load(UsrPurchase.class, order.getPurchase());
    view.setUsrEmail(upc.getEmail());
    view.setPayContent(order.getPaycontent());
    // TODO 运费和保险费的币种根据什么来确定?
    view.setShippingCharges(order.getFreightPrice());
    view.setInsurance(order.getInsurancePrice());
    view.setShippingChargesAndInsurance(order.getFreightPrice().add(order.getInsurancePrice()));
    view.setTotal(order.getPriceTotal());
    view.setDeliveryType(order.getDelivery());
    view.setShipName(order.getName());
    view.setShipAddress(new ObjectMapper().readValue(order.getAddress(), AddressView.class));
    view.setBillAddress(new ObjectMapper().readValue(order.getBillingAddress(), AddressView.class));
    view.setContactNum(order.getPhone());
    view.setCancelReason(order.getOdrCancel());
    view.setRemark(order.getOdrRemarks());

    List<OrderLineView> lines = new ArrayList<>();
    Integer itemsCount = 0;
    for (OdrOrderLine line : odrLineList) {
      lines.add(OrderLineView.build(line, lang, currency));
      itemsCount += line.getQty();
    }
    List<HistoryView> historys = new ArrayList<>();
    for (OdrHistory line : OdrHistoryDAO.listByOrder(order.getPkey())) {
      historys.add(HistoryView.build(line));
    }
    view.setLines(lines);
    view.setHistorys(historys);
    view.setItemsCount(itemsCount);
    view.setSubtotal(order.getProdPrice());
    //		view.setSubtotal(subtotal, currency, false, false);
    view.setCurrency(currency.getCurName());
    view.setCurrencySymbol(currency.getSymbol());

    return view;
  }

  /**
   * 通过订单编号和采购商pkey取订单
   * <li>订单状态不为已删除
   *
   * @param orderNum 订单编号
   * @param purchase 采购商pkey
   * @author yingjianhua
   */
  public static OdrOrder loadByOrderNumPurchase(String orderNum, Integer purchase) {
    if (orderNum == null || purchase == null) return null;
    return irille.pub.bean.Query.SELECT(OdrOrder.class)
        .WHERE(T.ORDER_NUM, "=?", orderNum)
        .WHERE(T.PURCHASE, "=?", purchase)
        .WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey())
        .query();
  }

  /**
   * 通过订单编号和供应商pkey取订单
   * <li>订单状态不为已删除
   *
   * @param orderNum 订单编号
   * @param supplier 供应商pkey
   * @author yingjianhua
   */
  public static OdrOrder loadByOrderNumSupplier(String orderNum, Integer supplier) {
    if (orderNum == null || supplier == null) return null;
    return irille.pub.bean.Query.SELECT(OdrOrder.class)
        .WHERE(T.ORDER_NUM, "=?", orderNum)
        .WHERE(T.SUPPLIER, "=?", supplier)
        .WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey())
        .query();
  }

  /**
   * 分页查询订单列表,只包含订单的简略信息
   *
   * @param purchase 采购商pkey
   * @param status 订单状态
   *     <li>-1 除已删除之外的所有状态
   *     <li>0 WAIT(0,"待付款")
   *     <li>1 WAITCONFIRM(1,"等待确认付款")
   *     <li>2 ERROR(2,"付款错误")
   *     <li>3 WAITDELIVER(3,"等待发货")
   *     <li>4 DELIVER(4,"已发货")
   *     <li>5 COMPLETE(5,"完成订单")
   *     <li>6 CANCEL(6,"取消订单")
   *     <li>7 除已删除之外的所有状态 同-1
   * @param start
   * @param limit
   * @author yingjianhua
   */
  public static Page<OrderView> pageByPurchase(
      Integer purchase, Integer status, Integer start, Integer limit) {
    BeanQuery<OdrOrder> q =
        irille.pub.bean.Query.SELECT(OdrOrder.class).WHERE(T.PURCHASE, "=?", purchase);
    if (status.intValue() == -1
        || status == Byte.valueOf(Odr.OdrState.DELETED.getLine().getKey()).intValue())
      q.WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey());
    else q.WHERE(T.STATE, "=?", status);
    int totalCount = q.queryCount();
    List<OdrOrder> list = q.limit(start, limit).ORDER_BY(T.TIME, "desc").queryList();
    List<OrderView> views = new ArrayList<>();
    for (OdrOrder bean : list) {
      OrderView view = new OrderView();
      view.setDate(bean.getTime());
      view.setNumber(bean.getOrderNum());
      view.setTotal(bean.getPriceTotal(), bean.gtCurrency(), true, true);
      view.setStatus(bean.getState());
      views.add(view);
    }
    return new Page<>(views, start, limit, totalCount);
  }

  private static Page<OrderView> asViewWithDetail(
      List<OdrOrder> list,
      List<OdrOrder> list2,
      Integer start,
      Integer limit,
      Integer totalCount,
      Language lang)
      throws JSONException {
    List<OrderView> views = new ArrayList<>();
    for (OdrOrder order : list) {
      List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
      OrderView view = new OrderView();
      PltErate currency = order.gtCurrency();

      view.setDate(order.getTime());
      view.setNumber(order.getOrderNum());
      view.setStatus(order.getState());
      view.setTotal(order.getPriceTotal(), currency, true, false);

      List<OrderLineView> lines = new ArrayList<>();
      BigDecimal subtotal = BigDecimal.ZERO;
      Integer itemsCount = 0;
      for (OdrOrderLine line : odrLineList) {
        lines.add(OrderLineView.build(line, lang, currency));
        subtotal = subtotal.add(line.getSubtotal());
        itemsCount += line.getQty();
      }
      view.setLines(lines);
      view.setItemsCount(itemsCount);
      view.setCurrency(currency.getCurName());
      views.add(view);
    }
    for (OdrOrder order : list2) {
      List<OdrOrderLine> odrLineList = OdrOrderLineDAO.listByOrder(order.getPkey());
      OrderView view = new OrderView();
      PltErate currency = order.gtCurrency();

      view.setDate(order.getTime());
      view.setNumber(order.getOrderNum());
      view.setStatus(order.getState());
      view.setTotal(order.getPriceTotal(), currency, true, false);

      List<OrderLineView> lines = new ArrayList<>();
      BigDecimal subtotal = BigDecimal.ZERO;
      Integer itemsCount = 0;
      for (OdrOrderLine line : odrLineList) {
        lines.add(OrderLineView.build(line, lang, currency));
        subtotal = subtotal.add(line.getSubtotal());
        itemsCount += line.getQty();
      }
      view.setLines(lines);
      view.setItemsCount(itemsCount);
      view.setCurrency(currency.getCurName());
      views.add(view);
    }
    return new Page<>(views, start, limit, totalCount);
  }

  /**
   * 分页查询订单列表,包括订单的详细信息
   *
   * @param purchase 采购商pkey
   * @param start
   * @param limit
   * @param lang 显示语言
   * @author yingjianhua
   */
  public static Page<OrderView> pageByPurchaseWithDetail(
      Integer purchase, Integer start, Integer limit, Language lang)
      throws JsonParseException, JsonMappingException, JSONException, IOException {
    BeanQuery<OdrOrder> q = irille.pub.bean.Query.SELECT(OdrOrder.class);
    q.WHERE(T.PURCHASE, "=?", purchase);
    q.WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey());
    BeanQuery<OdrOrder> q2 = irille.pub.bean.Query.SELECT(OdrOrder.class);
    q2.WHERE(T.PURCHASE, "=?", purchase);
    q2.WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey());
    q2.LEFT_JOIN(OrderMeetingOrder.class, OrderMeetingOrder.T.ORDERID, T.PKEY);
    q2.WHERE(OrderMeetingOrder.T.WHETHER_TO_SEND, "=?", Sys.OYn.NO.getLine().getKey());
    q.limit(start, limit);
    q2.limit(start, limit);
    return asViewWithDetail(
        q.queryList(), q2.queryList(), start, limit, q.queryCount() + q2.queryCount(), lang);
  }

  /**
   * 分页查询订单列表,包括订单的详细信息
   *
   * @param supplier 供应商pkey
   * @param start
   * @param limit
   * @param lang 显示语言
   * @author yingjianhua
   */
  public static Page<OrderView> pageBySupplierWithDetail(
      Integer supplier, OrderSearchView search, Integer start, Integer limit, Language lang)
      throws JsonParseException, JsonMappingException, JSONException, IOException {
    SQL sql =
        new SQL() {
          {
            SELECT(OdrOrder.class)
                .FROM(OdrOrder.class)
                .WHERE(T.SUPPLIER, "=?", supplier)
                .WHERE(T.TYPE, "=?", Odr.OdrType.DEFAULT.getLine().getKey());
            if (search != null) {
              if (search.getEmail() != null) {
                LEFT_JOIN(UsrPurchase.class, T.PURCHASE, UsrPurchase.T.PKEY);
                WHERE(UsrPurchase.T.EMAIL, "like ?", "%" + search.getEmail() + "%");
              }
              if (search.getNumber() != null) {
                WHERE(T.ORDER_NUM, "like ?", "%" + search.getNumber() + "%");
              }
              if (search.getBeginTime() != null) {
                WHERE(T.TIME, ">?", search.getBeginTime());
              }
              if (search.getEndTime() != null) {
                WHERE(T.TIME, "<?", search.getEndTime());
              }
              if (search.getPayType() != null) {
                WHERE(T.PAY_TYPE, "=?", search.getPayType());
              }
              if (search.getStatus() != null) {
                WHERE(T.STATE, "=?", search.getStatus());
              }
              if (search.getTypes() != null) {
                WHERE(T.TYPE, "=?", search.getTypes());
              }
            }
          }
        };
    SQL omt =
        new SQL() {
          {
            SELECT(OrderMeeting.class)
                .FROM(OrderMeeting.class)
                .WHERE(OrderMeeting.T.SUPPLIERID, "=?", supplier);
          }
        };
    SQL omtSql =
        new SQL() {
          {
            SELECT(OdrOrder.class)
                .FROM(OdrOrder.class)
                .LEFT_JOIN(OrderMeetingOrder.class, OrderMeetingOrder.T.ORDERID, T.PKEY)
                .WHERE(T.SUPPLIER, "=?", supplier)
                .WHERE(T.TYPE, "=?", Odr.OdrType.STATEONE.getLine().getKey());
            if (irille.pub.bean.Query.sql(omt).queryMaps().size() <= 0) {
              LEFT_JOIN(
                  OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingOrder.T.ORDERMEETINGID);
              WHERE(OrderMeeting.T.STATUS, "=?", OrderMeetingStatus.END.getLine().getKey());
              WHERE(OrderMeetingOrder.T.WHETHER_TO_SEND, "=?", Sys.OYn.YES.getLine().getKey());
            }
            if (search != null) {
              if (search.getEmail() != null) {
                LEFT_JOIN(UsrPurchase.class, T.PURCHASE, UsrPurchase.T.PKEY);
                WHERE(UsrPurchase.T.EMAIL, "like ?", "%" + search.getEmail() + "%");
              }
              if (search.getNumber() != null) {
                WHERE(T.ORDER_NUM, "like ?", "%" + search.getNumber() + "%");
              }
              if (search.getBeginTime() != null) {
                WHERE(T.TIME, ">?", search.getBeginTime());
              }
              if (search.getEndTime() != null) {
                WHERE(T.TIME, "<?", search.getEndTime());
              }
              if (search.getPayType() != null) {
                WHERE(T.PAY_TYPE, "=?", search.getPayType());
              }
              if (search.getStatus() != null) {
                WHERE(T.STATE, "=?", search.getStatus());
              }
              if (search.getTypes() != null) {
                WHERE(T.TYPE, "=?", search.getTypes());
              }
            }
          }
        };
    Integer count =
        irille.pub.bean.Query.sql(sql).queryCount()
            + irille.pub.bean.Query.sql(omtSql).queryCount();
    //        BeanQuery<OdrOrder> q = irille.pub.bean.Query.SELECT(OdrOrder.class);
    //        q.WHERE(T.SUPPLIER, "=?", supplier);
    //        //	q.WHERE(T.STATE, "!=?", Odr.OdrState.DELETED.getLine().getKey());
    //        if (search != null) {
    //            if (search.getEmail() != null) {
    //                q.LEFT_JOIN(UsrPurchase.class, T.PURCHASE, UsrPurchase.T.PKEY);
    //                q.WHERE(UsrPurchase.T.EMAIL, "like ?", "%" + search.getEmail() + "%");
    //            }
    //            if (search.getNumber() != null) {
    //                q.WHERE(T.ORDER_NUM, "like ?", "%" + search.getNumber() + "%");
    //            }
    //            if (search.getBeginTime() != null) {
    //                q.WHERE(T.TIME, ">?", search.getBeginTime());
    //            }
    //            if (search.getEndTime() != null) {
    //                q.WHERE(T.TIME, "<?", search.getEndTime());
    //            }
    //            if (search.getPayType() != null) {
    //                q.WHERE(T.PAY_TYPE, "=?", search.getPayType());
    //            }
    //            if (search.getStatus() != null) {
    //                q.WHERE(T.STATE, "=?", search.getStatus());
    //
    //            }
    //            if (search.getTypes() != null) {
    //                q.WHERE(T.TYPE, "=?", search.getTypes());
    //            }
    //        }
    //        q.limit(start, limit);
    //        q.ORDER_BY(T.TIME, " DESC ");
    //        return asViewWithDetail(q.queryList(), start, limit, q.queryCount(), lang);
    return asViewWithDetail(
        irille.pub.bean.Query.sql(sql).queryList(OdrOrder.class),
        irille.pub.bean.Query.sql(omtSql).queryList(OdrOrder.class),
        start,
        limit,
        count,
        lang);
  }

  public static Page<OrderView> lists(
      Integer start,
      Integer limit,
      OrderSearchView search,
      Integer billingStatus,
      Integer productId,
      Language lang) {
    SQL sql =
        new SQL() {
          {
            SELECT(T.CURRENCY)
                .SELECT(T.ORDER_NUM)
                .SELECT(T.TIME)
                .SELECT(T.STATE)
                .SELECT(T.PRICE_TOTAL)
                .SELECT(T.PKEY)
                .FROM(OdrOrderLine.class)
                .LEFT_JOIN(OdrOrder.class, T.PKEY, OdrOrderLine.T.MAIN)
                .LEFT_JOIN(PdtSpec.class, PdtSpec.T.PKEY, OdrOrderLine.T.SPEC)
                .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PdtSpec.T.PRODUCT)
                .LEFT_JOIN(OrderMeetingOrder.class, OrderMeetingOrder.T.ORDERID, OdrOrder.T.PKEY)
                .WHERE(T.TYPE, "=?", Odr.OdrType.STATEONE.getLine().getKey())
                .WHERE(PdtProduct.T.PKEY, "=?", productId)
                .WHERE(OrderMeetingOrder.T.BILLINGSTATUS, "=?", billingStatus);
            if (search != null) {
              if (search.getEmail() != null) {
                LEFT_JOIN(UsrPurchase.class, T.PURCHASE, UsrPurchase.T.PKEY);
                WHERE(UsrPurchase.T.EMAIL, "like ?", "%" + search.getEmail() + "%");
              }
              if (search.getNumber() != null) {
                WHERE(T.ORDER_NUM, "like ?", "%" + search.getNumber() + "%");
              }
              if (search.getBeginTime() != null) {
                WHERE(T.TIME, ">?", search.getBeginTime());
              }
              if (search.getEndTime() != null) {
                WHERE(T.TIME, "<?", search.getEndTime());
              }
              if (search.getPayType() != null) {
                WHERE(T.PAY_TYPE, "=?", search.getPayType());
              }
              if (search.getStatus() != null) {
                WHERE(T.STATE, "=?", search.getStatus());
              }
              if (search.getTypes() != null) {
                WHERE(T.TYPE, "=?", search.getTypes());
              }
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<OrderView> views =
        irille.pub.bean.Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OrderView view = new OrderView();
                  PltErate plt =
                      BeanBase.load(
                          PltErate.class,
                          String.valueOf(o.get(T.CURRENCY.getFld().getCodeSqlField())));
                  PltErate currency =
                      BeanBase.load(
                          PltErate.class,
                          String.valueOf(o.get(T.CURRENCY.getFld().getCodeSqlField())));
                  ;
                  view.setNumber(String.valueOf(o.get(T.ORDER_NUM.getFld().getCodeSqlField())));
                  view.setDate((Date) (o.get(T.TIME.getFld().getCodeSqlField())));
                  view.setStatus(
                      Byte.valueOf(String.valueOf(o.get(T.STATE.getFld().getCodeSqlField()))));
                  view.setTotal(
                      BigDecimal.valueOf(
                          Double.valueOf(
                              String.valueOf(o.get(T.PRICE_TOTAL.getFld().getCodeSqlField())))));
                  List<OdrOrderLine> odrLineList =
                      OdrOrderLineDAO.listByOrder(
                          Long.valueOf(String.valueOf(o.get(T.PKEY.getFld().getCodeSqlField()))));
                  List<OrderLineView> lines = new ArrayList<>();
                  BigDecimal subtotal = BigDecimal.ZERO;
                  Integer itemsCount = 0;
                  for (OdrOrderLine line : odrLineList) {
                    try {
                      lines.add(OrderLineView.build(line, lang, currency));
                      subtotal = subtotal.add(line.getSubtotal());
                      itemsCount += line.getQty();
                    } catch (JSONException e) {
                      e.printStackTrace();
                    }
                  }
                  view.setLines(lines);
                  view.setItemsCount(itemsCount);
                  view.setCurrency(currency.getCurName());
                  return view;
                })
            .collect(Collectors.toList());
    return new Page(views, start, limit, count);
  }

  /** @author yingjianhua */
  public static class Query extends IduOther<Query, OdrOrder> {

    /**
     * 根据采购商ID获取所有订单总数
     *
     * @author lijie@shoestp.cn @Description: 根据采购商Id获取所有订单总数
     * @date 2018/8/1 10:46
     */
    public long getOrderCountByPuchaseId(Integer pkey) {
      FormaterSql sql = FormaterSql.build(true);
      sql.eq(OdrOrder.T.PURCHASE);
      List parmList = new ArrayList();
      parmList.add(pkey);
      // TODO 需要排除状态为已删除的订单
      return countWhere(sql.toWhereString(), sql.getParms(parmList));
    }

    /**
     * @Description: 判断用户是否购买过改商品 商品id 采购商id
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/16 15:00
     */
    public boolean isBuyProduct(int proId, int pubId) {
      FormaterSql sql = FormaterSql.build();
      sql.from(OdrOrderLine.T.SPEC)
          .leftjoin(PdtSpec.T.PKEY, OdrOrderLine.T.SPEC)
          .leftjoin(PdtProduct.T.PKEY, PdtSpec.T.PRODUCT)
          .leftjoin(OdrOrder.T.PKEY, OdrOrderLine.T.MAIN)
          .eqAutoAnds(OdrOrder.T.STATE, Odr.OdrState.COMPLETE)
          .eqAutoAnd(
              OdrOrder.T.PURCHASE,
              pubId,
              t -> {
                if (t.intValue() > 0) {
                  return true;
                }
                return false;
              })
          .eqAutoAnd(
              PdtProduct.T.PKEY,
              proId,
              t -> {
                if (t.intValue() > 0) {
                  return true;
                }
                return false;
              });
      return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())) > 0
          ? true
          : false;
    }

    public OdrOrder loadByOrderNum(String orderId) {
      return OdrOrder.chkUniqueOrderNum(false, orderId);
    }
  }

  public static class other extends IduOther<other, OdrOrder> {

    public ByteArrayOutputStream getExcelData(
        int pk, String[] flds, String startDate, String endDate) throws IOException {
      /** * 国家具体取值未定义 未实现 */
      //            int pk = Idu.getUser().getPkey();
      //            int pk = 1;
      List<Serializable> params = new ArrayList<>();
      params.add(pk);
      List<String> titleList = new ArrayList();
      //            List<IEnumFld> fldList = new ArrayList();
      FormaterSql formaterSql = FormaterSql.build(true);
      for (String fld : flds) {
        try {
          OdrExportExcelAction.efld f = OdrExportExcelAction.efld.valueOf(fld);
          if (f != null) {
            if (f.isPk()) {
              formaterSql.leftjoin(f.getFld(), f.getOutkey());
            }
            formaterSql.select(f.getFld());
            //                        fldList.add(f.getFld());
            titleList.add(f.getName());
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      formaterSql.eq(OdrOrder.T.PKEY);
      if (endDate != null && endDate.length() > 1) {
        formaterSql.and().lt(OdrOrder.T.TIME);
        params.add(endDate);
      }
      if (startDate != null && startDate.length() > 1) {
        formaterSql.and().gt(OdrOrder.T.TIME);
        params.add(startDate);
      }
      ;
      BaseExcel baseExcel = new BaseExcel();
      baseExcel.witerColData(0, 0, titleList.toArray(new String[] {}));
      List<Object[]> list = BeanBase.list(formaterSql.buildSql(), formaterSql.getParms(params));
      for (int i = 0; i < list.size(); i++) {
        baseExcel.witerColData(0, i + 1, list.get(i));
      }
      return baseExcel.saveToOutputStream();
    }
  }

  public static class Insodr extends IduIns<Insodr, OdrOrder> {

    @Override
    public void before() {
      System.out.println(666);
      getB().setTime(Env.getTranBeginTime());
      getB().setState((byte) 0);
      getB().setType((byte) 1);
      getB().setFreightPrice(new BigDecimal("66.66"));
      getB().setInsurancePrice(new BigDecimal("66.6"));
      getB().setCurrency(1);
      getB().setName("00");
      getB().setPostalcode("11");
      getB().setPhone("111");
      // getB().setRowVersion(new Short("11"));
    }
  }

  /**
   * 插入订单
   *
   * @author zw
   */
  public static class Ins extends IduInsLines<Ins, OdrOrder, OdrOrderLine> {
    @Override
    public void before() {
      super.before();
      getB().setTime(Env.getTranBeginTime());
    }

    @Override
    public void after() {
      insLine(getB(), getLines(), OdrOrderLine.T.MAIN.getFld());
      super.after();
      List<OdrOrderLine> list = getLines(); // 所有子明细
      BigDecimal countprice = new BigDecimal(0); // 计算总价
      for (int i = 0; i < list.size(); i++) {
        BigDecimal num = new BigDecimal(list.get(i).getQty());
        BigDecimal price = list.get(i).gtSpec().getPrice();
        BigDecimal count = num.multiply(price);
        list.get(i).setSubtotal(count); // 计算该产品总价
        countprice = countprice.add(count);
      }
      updLine(getB(), list, OdrOrderLine.T.MAIN.getFld());
      getB().setProdPrice(countprice); // 产品总价
      BigDecimal freight = getB().getFreightPrice(); // 运费
      BigDecimal safe = getB().getInsurancePrice(); // 保险费
      BigDecimal cprice = countprice.add(freight).add(safe);
      getB().setPriceTotal(cprice); // 订单总价
      String key = getB().getPkey().toString();
      int length = getB().getPkey().toString().length();
      String str = df.format(getB().getTime());
      str = str.substring(0, str.length() - length);
      getB().setOrderNum(str + key);
      System.out.println(getB().getOrderNum());
      getB().upd();
    }
  }

  public static class InsPRM extends IduInsLines<InsPRM, OdrOrder, OdrOrderLine> {
    @Override
    public void before() {
      super.before();
      getB().setTime(Env.getTranBeginTime());
    }

    @Override
    public void after() {
      insLine(getB(), getLines(), OdrOrderLine.T.MAIN.getFld());
      super.after();
      List<OdrOrderLine> list = getLines(); // 所有子明细
      BigDecimal countprice = new BigDecimal(0); // 计算总价
      for (int i = 0; i < list.size(); i++) {
        BigDecimal price = list.get(i).getSubtotal();
        countprice = countprice.add(price);
      }
      getB().setProdPrice(countprice); // 产品总价
      BigDecimal freight = getB().getFreightPrice(); // 运费
      BigDecimal safe = getB().getInsurancePrice(); // 保险费
      BigDecimal cprice = countprice.add(freight).add(safe);
      getB().setPriceTotal(cprice); // 订单总价
      String key = getB().getPkey().toString();
      int length = getB().getPkey().toString().length();
      String str = df.format(getB().getTime());
      str = str.substring(0, str.length() - length);
      int i = (1 + (int) (Math.random() * 8));
      getB().setOrderNum(str + key + i);
      System.out.println(getB().getOrderNum());
      getB().upd();
    }
  }

  /**
   * 订单支付
   *
   * @author yingjianhua
   */
  public static void pay(
      String number, String payContent, Integer currency, UsrPurchase purchase, Language language) {
    OdrOrder bean = loadByOrderNumPurchase(number, purchase.getPkey());
    if (bean.gtState() != OdrState.WAIT) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_service_state_error, language));
      //    	throw LOG.err("订单状态错误", "订单状态为[{0}]不能支付", bean.gtState().getLine().getName());
    }
    bean.stState(Odr.OdrState.WAITCONFIRM);
    try {
      JSONObject json = new JSONObject(payContent);
      String SentMoney =
          I18NUtil.format(
              BigDecimal.valueOf(json.getDouble("SentMoney")),
              PltErateDAO.find(currency),
              true,
              true);
      JSONObject json2 = new JSONObject();
      String SentMoney2 = SentMoney.replace("USD ", "");
      json2.put(I18NUtil.getBundle("Global.Name"), json.getString("Name"));
      json2.put(I18NUtil.getBundle("cart3.Remittance"), SentMoney2);
      json2.put(I18NUtil.getBundle("cart3.Reference_Number"), json.getInt("MTCNNumber"));
      json2.put(I18NUtil.getBundle("Global.Remarks"), json.getString("Contents"));
      bean.setPaycontent(json2.toString());
    } catch (JSONException e) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, language));
      // throw LOG.err("数据异常", "支付信息数据异常");
    }
    OdrHistoryDAO.add(
        bean.getPkey(),
        "Update order status from WAIT to WAITCONFIRM",
        "(" + UsrPurchase.TB.getName() + ")" + purchase.getEmail(),
        OdrState.WAITCONFIRM);
    bean.upd();
  }

  /**
   * 修改订单内容
   *
   * @author zw
   */
  public static class updorder extends IduUpd<updorder, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(
          dbbean,
          getB(),
          OdrOrder.T.PROD_PRICE,
          OdrOrder.T.PRICE_TOTAL,
          OdrOrder.T.FREIGHT_PRICE,
          OdrOrder.T.INSURANCE_PRICE,
          OdrOrder.T.ODR_REMARKS);
      setB(dbbean);
    }
  }

  /**
   * 修改订单 确认收款
   *
   * @author zw
   */
  public static class updline extends IduOther<updline, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(dbbean, getB(), OdrOrder.T.STATE);
      setB(dbbean);
    }

    @Override
    public void run() {
      getB().upd();
    }
  }

  /**
   * 修改订单取消订单和取消原因
   *
   * @author zw
   */
  public static class updcancel extends IduOther<updline, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(dbbean, getB(), OdrOrder.T.STATE, OdrOrder.T.ODR_CANCEL);
      setB(dbbean);
    }

    @Override
    public void run() {
      getB().upd();
    }
  }

  /**
   * 修改订单 运费模板
   *
   * @author zw
   */
  public static class updcompany extends IduUpd<updcompany, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(dbbean, getB(), OdrOrder.T.DELIVERY);
      setB(dbbean);
    }
  }

  /**
   * 修改订单确认发货
   *
   * @author zw
   */
  public static class upddeliver extends IduUpd<upddeliver, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(
          dbbean, getB(), OdrOrder.T.EXPRESS_NUM, OdrOrder.T.PAG_REMARKS, OdrOrder.T.STATE);
      setB(dbbean);
    }
  }

  /**
   * 修改订单地址
   *
   * @author zw
   */
  public static class updaddress extends IduUpd<updaddress, OdrOrder> {
    @Override
    public void before() {
      OdrOrder dbbean = getB().loadUniqueOrderNum(true, getB().getOrderNum());
      PropertyUtils.copyProperties(dbbean, getB(), OdrOrder.T.ADDRESS);
      try {
        JSONObject jsStr = new JSONObject(getB().getAddress());
        dbbean.setName(jsStr.get("name").toString());
        dbbean.setPostalcode(jsStr.get("postalCode").toString());
        dbbean.setPhone(jsStr.get("phone").toString());
      } catch (JSONException e) {
        e.printStackTrace();
      }
      setB(dbbean);
    }
  }

  public static class Upd extends IduUpdLines<Upd, OdrOrder, OdrOrderLine> {
    @Override
    public void before() {
      super.before();
      OdrOrder dbbean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbbean,
          getB(),
          OdrOrder.T.PKEY,
          OdrOrder.T.TIME,
          OdrOrder.T.ORDER_NUM,
          OdrOrder.T.PRICE_TOTAL,
          OdrOrder.T.TYPE,
          OdrOrder.T.SERVICE_CHARGE,
          OdrOrder.T.ADDITIONALCOST,
          OdrOrder.T.BILLING_ADDRESS);
      setB(dbbean);
      updLine(getB(), getLines(), OdrOrderLine.T.MAIN.getFld());
    }

    @Override
    public void after() {
      super.after();
      List<OdrOrderLine> list = getLines(); // 所有子明细
      BigDecimal countprice = new BigDecimal(0); // 计算总价
      for (int i = 0; i < list.size(); i++) {
        BigDecimal num = new BigDecimal(list.get(i).getQty());
        BigDecimal price = list.get(i).gtSpec().getPrice();
        BigDecimal count = num.multiply(price);
        list.get(i).setSubtotal(count); // 计算该产品总价
        countprice = countprice.add(count);
      }
      updLine(getB(), getLines(), OdrOrderLine.T.MAIN.getFld());
      getB().setProdPrice(countprice); // 产品总价
      BigDecimal freight = getB().getFreightPrice(); // 运费
      BigDecimal safe = getB().getInsurancePrice(); // 保险费
      BigDecimal cprice = countprice.subtract(freight).subtract(safe);
      getB().setPriceTotal(cprice); // 订单总价
      getB().upd();
    }
  }

  /**
   * 确认收货
   *
   * @param number 订单编号
   * @param purchase 采购商pkey
   * @author yingjianhua
   */
  public static void confirmReceiving(String number, Integer purchase, Language lang) {
    OdrOrder bean = loadByOrderNumPurchase(number, purchase);
    if (bean == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, number));
      //    	throw LOG.err("not exists", "订单编号为[{0}]且采购商pkey为[{1}]的订单不存在", number, purchase);
    }
    switch (bean.gtState()) {
      case DELIVER:
        OdrHistory history = new OdrHistory();
        history.setDescrip(
            "Update order status from "
                + bean.gtState().name()
                + " to "
                + OdrState.COMPLETE.name());
        history.stState(OdrState.COMPLETE);
        history.stOdrorder(bean);
        history.setTime(Env.getTranBeginTime());
        history.setOperator("(用户)" + bean.gtPurchase().getName());
        history.setRowVersion((short) 0);
        history.ins();
        bean.stState(OdrState.COMPLETE);
        bean.upd();
        break;
      default:
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.order_wrong_data, lang, number));
        //        throw LOG.err(
        //            "statusErr",
        //            "订单编号为[{0}]的订单状态为[{1}],不能确认收货",
        //            number,
        //            bean.gtState().getLine().getName());
    }
  }

  /**
   * <strong>删除订单</strong>
   *
   * <p>
   *
   * <ul>
   *   <li>只有订单状态为 待付款\已完成\已取消 的订单可以删除
   *   <li>删除操作是将订单状态设置为已删除,并不删除数据
   *   <li>并新增一条订单历史,用于记录删除操作
   * </ul>
   *
   * @param number 订单编号
   * @param purchase 采购商pkey
   * @author yingjianhua
   * @see irille.shop.odr.Odr.OdrState
   */
  //    public static void delete(String number, Integer purchase) {
  //    	OdrOrder bean = loadByOrderNumPurchase(number, purchase);
  //    	if(bean == null)
  //    		throw LOG.err("not exists", "订单编号为[{0}]且采购商pkey为[{1}]的订单不存在", number, purchase);
  ////
  //	WAIT(0,"待付款"),WAITCONFIRM(1,"等待确认付款"),ERROR(2,"付款错误"),WAITDELIVER(3,"等待发货"),DELIVER(4,"已发货"),COMPLETE(5,"完成订单"),CANCEL(6,"已取消订单"),DELETED(7, "已删除");
  //    	switch (bean.gtState()) {
  //		case WAIT:
  //		case CANCEL:
  //		case COMPLETE:
  //			OdrHistory history= new OdrHistory();
  //			history.setDescrip("Update order status from "+bean.gtState().name()+" to
  // "+OdrState.DELETED.name());
  //			history.stState(OdrState.DELETED);
  //			history.stOdrorder(bean);
  //			history.setTime(Env.getTranBeginTime());
  //			history.setOperator("(用户)"+bean.gtPurchase().getName());
  //			history.setRowVersion((short)0);
  //			history.ins();
  //			bean.stState(OdrState.DELETED);
  //			bean.upd();
  //			break;
  //		case WAITCONFIRM:
  //		case ERROR:
  //		case WAITDELIVER:
  //		case DELIVER:
  //		case DELETED:
  //			throw LOG.err("statusErr", "订单编号为[{0}]的订单状态为[{1}],不能删除", number,
  // bean.gtState().getLine().getName());
  //		default:
  //			throw LOG.err("statusErr", "订单编号为[{0}]的订单状态为[{1}],不能删除", number,
  // bean.gtState().getLine().getName());
  //    	}
  //    }

  public static class Del extends IduDel<Del, OdrOrder> {
    @Override
    public void before() {
      super.before();
      delLine(getLines(OdrOrderLine.T.MAIN, getB().getPkey()));
    }
  }

  public static class BuildOrder extends IduOther<BuildOrder, OdrOrder> {
    private List<OdrView> odrView;
    private String carts;
    private Integer address;
    private Integer currency;
    private Language language;
    private Map<Integer, List<UsrCart>> cartMap = new HashMap<Integer, List<UsrCart>>();
    private List<UsrSupplier> supplierList = new ArrayList<UsrSupplier>();
    private List<PdtSpec> specList = new ArrayList<PdtSpec>();

    public void before() {
      List<UsrCart> cartList =
          BeanBase.list(
              UsrCart.class,
              UsrCart.T.PKEY.getFld().getCodeSqlField() + " in(" + getCarts() + ")",
              false);
      for (UsrCart cart : cartList) {
        for (UsrCart cart2 : cartList) {
          if (!cart.getPurchase().equals(cart2.getPurchase())) {
            throw new WebMessageException(
                MessageBuild.buildMessage(ReturnCode.service_gone, language));
            //            throw LOG.err(Usr.ErrMsgs.noAccess);
          }
        }
        if (cartMap.get(cart.getSupplier()) == null) {
          List<UsrCart> newCartList = new ArrayList<UsrCart>();
          newCartList.add(cart);
          cartMap.put(cart.getSupplier(), newCartList);
        } else {
          List<UsrCart> newCartList = cartMap.get(cart.getSupplier());
          newCartList.add(cart);
        }
      }
    }

    public void valid() {
      for (OdrView view : odrView) {
        if (view.getExpress().intValue() == -1) {
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.choose_express, language));
          //          throw LOG.err("noChoose", "请选择快递方式");
        }
        if (view.getPayMethod().intValue() == -1) {
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.choose_paymode, language));
          //          throw LOG.err("noChoose", "请选择支付方式");
        }
      }
    }

    public void run() {
      UsrPurchaseLine address = BeanBase.load(UsrPurchaseLine.class, getAddress());
      for (Integer supplier : cartMap.keySet()) {
        OdrOrderDAO.BuildOrderByCart buildOrder = new OdrOrderDAO.BuildOrderByCart();
        for (OdrView view : odrView) {
          if (view.getSupplier().equals(supplier)) {
            buildOrder.setDelivery(
                BeanBase.load(PltFreightSeller.class, view.getExpress()).getCompany());
            buildOrder.setPayType(view.getPayMethod());
            buildOrder.setPagRemarks(view.getRemarks());
            buildOrder.setOdrRemarks(view.getOdrRemarks());
          }
        }
        buildOrder.setLanguage(language);
        buildOrder.setCurrency(getCurrency());
        buildOrder.setState("0");
        buildOrder.setAddress(address);
        buildOrder.setCarts(cartMap.get(supplier));
        buildOrder.commit();
      }
    }

    public Integer getCurrency() {
      return currency;
    }

    public void setCurrency(Integer currency) {
      this.currency = currency;
    }

    public String getCarts() {
      return carts;
    }

    public void setCarts(String carts) {
      this.carts = carts;
    }

    public Integer getAddress() {
      return address;
    }

    public void setAddress(Integer address) {
      this.address = address;
    }

    public List<OdrView> getOdrView() {
      return odrView;
    }

    public void setOdrView(List<OdrView> odrView) {
      this.odrView = odrView;
    }

    public Language getLanguage() {
      return language;
    }

    public void setLanguage(Language language) {
      this.language = language;
    }
  }

  /**
   * 根据购物车生成订单
   *
   * @author liyichao
   */
  public static class BuildOrderByCart extends IduOther<BuildOrderByCart, OdrOrder> {
    private List<UsrCart> carts;
    private UsrPurchaseLine address;
    private String delivery;
    private String odrRemarks;
    private String pagRemarks;
    private Integer currency;
    private Integer payType;
    private List<OdrOrderLine> generalOrderLineList = new ArrayList<OdrOrderLine>();
    private List<OdrOrderLine> groupOrderLineList = new ArrayList<OdrOrderLine>();
    private Set<Integer> typeSet = new HashSet<Integer>();
    private BigDecimal generalTotalAmt = BigDecimal.ZERO;
    private BigDecimal groupTotalAmt = BigDecimal.ZERO;
    private List<String> orders = new ArrayList<String>();
    private String orderNum;
    private String state;
    private Language language;

    public void before() {
      if (getCurrency() == null) {
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.choose_currency, language));
        //        throw LOG.err("noCurrency", "未选择货币");
      }
      UsrPurchaseLine upl =
          irille.pub.bean.Query.SELECT(UsrPurchaseLine.class)
              .WHERE(UsrPurchaseLine.T.PURCHASE, "=?", address.getPurchase())
              .WHERE(UsrPurchaseLine.T.ADDRSSTYPE, "=?", OAddress.BILLED.getLine().getKey())
              .query();
      if (upl == null) {
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.Please_Select_The_Billing_Address, language));
        //        throw LOG.errTran("addressfrom%Please_Select_The_Billing_Address", "请先设置帐单邮寄地址");
      }
      for (UsrCart cart : carts) {
        OdrOrderLine orderLine = new OdrOrderLine();
        orderLine.setQty(cart.getQty());
        orderLine.setSpec(cart.getSpec());
        orderLine.setSubtotal(cart.getAmtTotal());
        if (Integer.valueOf(cart.gtSpec().gtProduct().getProductType())
            .equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
          if (groupTotalAmt == null) {
            groupTotalAmt = cart.getAmtTotal();
          } else {
            groupTotalAmt = groupTotalAmt.add(cart.getAmtTotal());
          }
          PrmGroupPurchaseLine groupLine =
              PrmGroupPurchaseLine.chkUniqueProduct(false, cart.gtSpec().getProduct());
          if (groupLine.getBoughtCount() == null) {
            groupLine.setBoughtCount((long) cart.getQty());
          } else {
            groupLine.setBoughtCount(groupLine.getBoughtCount() + (long) cart.getQty());
          }
          groupLine.upd();

          groupOrderLineList.add(orderLine);
        } else {
          if (generalTotalAmt == null) {
            generalTotalAmt = cart.getAmtTotal();
          } else {
            generalTotalAmt = generalTotalAmt.add(cart.getAmtTotal());
          }
          generalOrderLineList.add(orderLine);
          PdtSpec spec = cart.gtSpec();
          if (spec.getStoreCount() < cart.getQty()) {
            throw LOG.err(Usr.ErrMsgs.soldOut);
          } else {
            if (spec.getStoreCount() < 0) {
              PdtProduct product = spec.gtProduct();
              product.setStock(product.getStock() - cart.getQty());
              product.upd();
            } else {
              spec.setStoreCount(spec.getStoreCount() - cart.getQty());
              spec.upd();
            }
          }
        }
        typeSet.add(Integer.valueOf(cart.gtSpec().gtProduct().getProductType()));
      }
    }

    public void run() {
      for (Integer type : typeSet) {
        OdrOrder order = new OdrOrder();
        order.setPurchase(address.getPurchase());
        order.setTime(Env.getSystemTime());
        order.setState(Odr.OdrState.DEFAULT.getLine().getKey());
        order.setDelivery(getDelivery());
        order.setPayType(getPayType());
        order.setName(getAddress().getName());
        order.setFreightPrice(BigDecimal.ZERO); // TODO 待完善
        order.setInsurancePrice(BigDecimal.ZERO); // TODO 待完善
        order.setCurrency(getCurrency());
        order.setPostalcode(address.getEmailcode());
        order.setPhone(address.getPhonenumber());
        order.setPayType(payType);
        order.setSupplier(carts.get(0).getSupplier());
        order.setOdrRemarks(getOdrRemarks());
        order.setPagRemarks(getPagRemarks());
        order.setAdditionalcost(BigDecimal.ZERO); // TODO 待完善
        order.setServiceCharge(BigDecimal.ZERO); // TODO 待完善
        try {
          order.setAddress(new ObjectMapper().writeValueAsString(AddressView.trans(address)));
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
        try {
          UsrPurchaseLine upl =
              irille.pub.bean.Query.SELECT(UsrPurchaseLine.class)
                  .WHERE(UsrPurchaseLine.T.PURCHASE, "=?", address.getPurchase())
                  .WHERE(UsrPurchaseLine.T.ADDRSSTYPE, "=?", OAddress.BILLED.getLine().getKey())
                  .query();
          order.setBillingAddress(new ObjectMapper().writeValueAsString(AddressView.trans(upl)));
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }

        if (type.equals(Integer.valueOf(Odr.OdrType.STATEONE.getLine().getKey()))) {
          order.setType(Odr.OdrType.STATEONE.getLine().getKey());
          order.setProdPrice(getGroupTotalAmt());
          order.setPriceTotal(
              order.getProdPrice().add(order.getFreightPrice()).add(order.getInsurancePrice()));
          order.ins();
          Idu.insLine(order, groupOrderLineList, OdrOrderLine.T.MAIN.getFld());
        } else {
          order.setType(Odr.OdrType.STATETWO.getLine().getKey());
          order.setProdPrice(getGeneralTotalAmt());
          order.setPriceTotal(
              order.getProdPrice().add(order.getFreightPrice()).add(order.getInsurancePrice()));
          order.ins();
          Idu.insLine(order, generalOrderLineList, OdrOrderLine.T.MAIN.getFld());
        }
        buildOrderNum(order);
        orders.add(order.getOrderNum());
      }
    }

    public void after() {
      for (String str : orders) {
        if (orderNum == "" || orderNum == null) {
          orderNum = str;
        } else {
          orderNum += "," + str;
        }
        OdrHistory history = new OdrHistory();
        OdrOrder order = OdrOrder.chkUniqueOrderNum(false, str);
        history.setOdrorder(order.getPkey());
        if (order.gtPurchase().getName() != null) {
          history.setOperator("(" + UsrPurchase.TB.getName() + ")" + order.gtPurchase().getName());
        } else {
          history.setOperator(
              "(" + UsrPurchase.TB.getName() + ")" + order.gtPurchase().getLoginName());
        }
        history.setTime(Env.getSystemTime());
        history.setState(order.getState());
        history.setDescrip(order.gtState().getLine().getName());
        history.ins();
      }
      if (state.equals("0")) {
        Iterator<UsrCart> cartIterator = carts.iterator();
        while (cartIterator.hasNext()) {
          cartIterator.next().del();
        }
      }
    }

    private void buildOrderNum(OdrOrder order) {
      // 设置订单号
      String timeStamp = Env.getSystemTime().getTime() + "";
      String pkey = String.valueOf(order.getPkey());
      String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
      order.setOrderNum(orderid);
      order.upd();
    }

    public Integer getCurrency() {
      return currency;
    }

    public void setCurrency(Integer currency) {
      this.currency = currency;
    }

    public String getPagRemarks() {
      return pagRemarks;
    }

    public void setPagRemarks(String pagRemarks) {
      this.pagRemarks = pagRemarks;
    }

    public String getState() {
      return state;
    }

    public void setState(String state) {
      this.state = state;
    }

    public String getOdrRemarks() {
      return odrRemarks;
    }

    public void setOdrRemarks(String odrRemarks) {
      this.odrRemarks = odrRemarks;
    }

    public String getOrderNum() {
      return orderNum;
    }

    public void setOrderNum(String orderNum) {
      this.orderNum = orderNum;
    }

    public List<UsrCart> getCarts() {
      return carts;
    }

    public void setCarts(List<UsrCart> carts) {
      this.carts = carts;
    }

    public UsrPurchaseLine getAddress() {
      return address;
    }

    public void setAddress(UsrPurchaseLine address) {
      this.address = address;
    }

    public String getDelivery() {
      return delivery;
    }

    public void setDelivery(String delivery) {
      this.delivery = delivery;
    }

    public Integer getPayType() {
      return payType;
    }

    public void setPayType(Integer payType) {
      this.payType = payType;
    }

    public List<OdrOrderLine> getGeneralOrderLineList() {
      return generalOrderLineList;
    }

    public void setGeneralOrderLineList(List<OdrOrderLine> generalOrderLineList) {
      this.generalOrderLineList = generalOrderLineList;
    }

    public List<OdrOrderLine> getGroupOrderLineList() {
      return groupOrderLineList;
    }

    public void setGroupOrderLineList(List<OdrOrderLine> groupOrderLineList) {
      this.groupOrderLineList = groupOrderLineList;
    }

    public BigDecimal getGeneralTotalAmt() {
      return generalTotalAmt;
    }

    public void setGeneralTotalAmt(BigDecimal generalTotalAmt) {
      this.generalTotalAmt = generalTotalAmt;
    }

    public BigDecimal getGroupTotalAmt() {
      return groupTotalAmt;
    }

    public void setGroupTotalAmt(BigDecimal groupTotalAmt) {
      this.groupTotalAmt = groupTotalAmt;
    }

    public Set<Integer> getTypeSet() {
      return typeSet;
    }

    public void setTypeSet(Set<Integer> typeSet) {
      this.typeSet = typeSet;
    }

    public List<String> getOrders() {
      return orders;
    }

    public void setOrders(List<String> orders) {
      this.orders = orders;
    }

    public Language getLanguage() {
      return language;
    }

    public void setLanguage(Language language) {
      this.language = language;
    }
  }

  /**
   * 生成统计订单
   *
   * @author liyichao
   */
  public static class StaOrder extends IduOther<StaOrder, OdrOrder> {
    private List<OdrOrderLine> specList;
    private UsrPurchaseLine address;
    private String id;
    private String orderNum;

    public UsrPurchaseLine getAddress() {
      return address;
    }

    public void setAddress(UsrPurchaseLine address) {
      this.address = address;
    }

    public String getOrderNum() {
      return orderNum;
    }

    public void setOrderNum(String orderNum) {
      this.orderNum = orderNum;
    }

    public List<OdrOrderLine> getSpecList() {
      return specList;
    }

    public void setSpecList(List<OdrOrderLine> specList) {
      this.specList = specList;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public void before() {
      JSONObject json = new JSONObject();
      try {
        json.put(UsrPurchaseLine.T.SURNAME.getFld().getCode(), address.getSurname());
        json.put(UsrPurchaseLine.T.ADDRESS.getFld().getCode(), address.getAddress());
        json.put(UsrPurchaseLine.T.CITY.getFld().getCode(), address.getCity());
        json.put(UsrPurchaseLine.T.REGION.getFld().getCode(), address.getRegion());
        json.put(UsrPurchaseLine.T.COUNTRY.getFld().getCode(), address.getCountry());
      } catch (JSONException e) {
        e.printStackTrace();
      }
      getB().setAddress(json.toString());
      // getB().setAddress(address.getAddress());
      String sql =
          UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()
              + " = "
              + address.getPurchase()
              + " AND "
              + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField()
              + " = "
              + OAddress.BILLED.getLine().getKey();
      UsrPurchaseLine upl = BeanBase.list(UsrPurchaseLine.class, sql, false).get(0);
      JSONObject jsonzd = new JSONObject();
      JSONObject jsonzdline = new JSONObject();
      try {
        jsonzdline.put(UsrPurchaseLine.T.ADDRESS.getFld().getCode(), address.getAddress());
        jsonzdline.put(UsrPurchaseLine.T.CITY.getFld().getCode(), address.getCity());
        jsonzdline.put(UsrPurchaseLine.T.REGION.getFld().getCode(), address.getRegion());
        jsonzdline.put(UsrPurchaseLine.T.COUNTRY.getFld().getCode(), address.getCountry());
        jsonzd.put(UsrPurchaseLine.T.ADDRESS.getFld().getCode(), jsonzdline);
        jsonzd.put(UsrPurchaseLine.T.NAME.getFld().getCode(), upl.getName());
        jsonzd.put(UsrPurchaseLine.T.SURNAME.getFld().getCode(), upl.getSurname());
        jsonzd.put(UsrPurchaseLine.T.EMAILCODE.getFld().getCode(), upl.getEmailcode());
        jsonzd.put(UsrPurchaseLine.T.PHONENUMBER.getFld().getCode(), upl.getPhonenumber());
      } catch (JSONException e) {
        e.printStackTrace();
      }
      getB().setBillingAddress(jsonzd.toString());
      getB().setName(address.getName() + " " + address.getSurname());
      getB().setPhone(address.getPhonenumber());
      getB().setPostalcode(address.getEmailcode());
      getB().setPurchase(HomeAction.getPurchase().getPkey());
      getB().setSupplier(specList.get(0).gtSpec().gtProduct().gtSourceProduct().getSupplier());
      getB().setTime(Env.getSystemTime());
      getB().setState(Odr.OdrState.WAIT.getLine().getKey());
      getB().setType(Odr.OdrType.STATEONE.getLine().getKey());
      getB().setCurrency(HomeAction.getPurchase().getCurrency());
      getB().setServiceCharge(BigDecimal.ZERO);
      getB().setAdditionalcost(BigDecimal.ZERO);
    }

    public void run() {
      getB().ins();
      insLine(getB(), specList, OdrOrderLine.T.MAIN.getFld());
      PrmGroupPurchaseLine line = BeanBase.load(PrmGroupPurchaseLine.class, getId());
      List<PdtSpec> specList =
          BeanBase.list(
              PdtSpec.class,
              PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = ? ",
              false,
              line.getProduct());
      String specId = "";
      for (PdtSpec spec : specList) {
        if (specId.equals("")) {
          specId += spec.getPkey();
        } else {
          specId += "," + spec.getPkey();
        }
      }
      List<OdrOrderLine> odrLine =
          BeanBase.list(
              OdrOrderLine.class,
              OdrOrderLine.T.SPEC.getFld().getCodeSqlField() + " in(" + specId + ")",
              false);
      for (OdrOrderLine odrLi : odrLine) {
        PdtSpec actSpec = odrLi.gtSpec();
        PdtProduct sourceProduct = odrLi.gtSpec().gtProduct().gtSourceProduct();
        PdtSpec sourceSpec =
            PdtSpec.chkUniquePdt_color_size(
                false, sourceProduct.getPkey(), actSpec.getColor(), actSpec.getSize());
        sourceSpec.setStoreCount(sourceSpec.getStoreCount() - odrLi.getQty());
        sourceSpec.upd();
      }
      line.setState(Prm.OSend.ALSEND.getLine().getKey());
      line.upd();
    }

    @Override
    public void after() {
      // 设置订单号
      String timeStamp = Env.getSystemTime().getTime() + "";
      String pkey = getB().getPkey() + "";
      String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
      getB().setOrderNum(orderid);
      getB().upd();
      setOrderNum(orderid);
    }
  }

  /** <<<<<<<<<<<<<< ---------- 订单确认页 -------------- >>>>>>>>>>>>>>>>>* */
  /** createdBy liyichao */
  public static class SettlePage extends IduOther<SettlePage, OdrOrder> {
    private JSONObject info;
    private List<irille.homeAction.usr.dto.SupplierView> supplierViews =
        new ArrayList<irille.homeAction.usr.dto.SupplierView>();
    private List<ProductView> productViews = new ArrayList<ProductView>();
    private List<ColorView> colorViews = new ArrayList<ColorView>();
    private List<SpecView> specViews = new ArrayList<SpecView>();
    private Map<Integer, List<FreightSellerlistView>> freightViews =
        new HashMap<Integer, List<FreightSellerlistView>>();
    private Map<Integer, List<PayTypeView>> payViews = new HashMap<Integer, List<PayTypeView>>();
    private Integer allQty = 0;
    private BigDecimal totalAmt = BigDecimal.ZERO;

    public SettlePage(JSONObject info) {
      this.info = info;
    }

    public void run() {
      Iterator infoIte = info.keys();
      while (infoIte.hasNext()) {
        try {
          // key = 规格pkey 	value = 数量
          String key = (String) infoIte.next();
          // 获得规格
          PdtSpec spec = BeanBase.load(PdtSpec.class, key);

          // 获得产品
          PdtProduct product =
              translateUtil.getAutoTranslate(spec.gtProduct(), HomeAction.curLanguage());
          String pdtImg = "";
          if (product.getPicture() != null) {
            String[] pics = product.getPicture().split(",");
            pdtImg = pics.length > 0 ? pics[0] : "";
          }
          // 获得该产品的数量
          Integer qty = null;
          try {
            qty = info.getInt(key);
          } catch (JSONException e) {
            e.printStackTrace();
          }
          BigDecimal specTotalPrice = BigDecimal.ZERO;
          if (qty != null && spec.getPrice() != null) {
            specTotalPrice = new BigDecimal(qty).multiply(spec.getPrice());
            setAllQty(getAllQty() + qty);
            setTotalAmt(getTotalAmt().add(specTotalPrice));
          }
          // 获得供应商
          UsrSupplier supplier =
              translateUtil.getAutoTranslate(product.gtSupplier(), HomeAction.curLanguage());

          // 获取供应商集合
          if (supplierViews.size() == 0) {
            supplierViews.add(
                new irille.homeAction.usr.dto.SupplierView(
                    specTotalPrice, qty, supplier.getPkey(), supplier.getShowName()));
          } else {
            boolean flag = false;
            for (irille.homeAction.usr.dto.SupplierView view : supplierViews) {
              if (view.getId().equals(supplier.getPkey())) {
                view.setQty(view.getQty() + qty);
                view.setTotalPrice(view.getTotalPrice().add(specTotalPrice));
                flag = true;
              }
            }
            if (flag == false) {
              supplierViews.add(
                  new irille.homeAction.usr.dto.SupplierView(
                      specTotalPrice, qty, supplier.getPkey(), supplier.getShowName()));
            }
          }

          // 获取产品集合
          if (productViews.size() == 0) {
            if (Integer.valueOf(product.getProductType())
                .equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
              PrmGroupPurchaseLine line =
                  PrmGroupPurchaseLine.loadUniqueProduct(false, product.getPkey());
              productViews.add(
                  new ProductView(
                      specTotalPrice,
                      qty,
                      product.getPkey(),
                      product.getSupplier(),
                      product.getName(),
                      pdtImg,
                      product.getCurPrice(),
                      product.getProductType(),
                      product.getCode(),
                      line.getPkey()));
            } else {
              productViews.add(
                  new ProductView(
                      specTotalPrice,
                      qty,
                      product.getPkey(),
                      product.getSupplier(),
                      product.getName(),
                      pdtImg,
                      product.getCurPrice(),
                      product.getProductType(),
                      product.getCode(),
                      null));
            }
          } else {
            boolean flag = false;
            for (ProductView view : productViews) {
              if (view.getId().equals(product.getPkey())) {
                view.setQty(view.getQty() + qty);
                view.setTotalAmt(view.getTotalAmt().add(specTotalPrice));
                flag = true;
              }
            }
            if (flag == false) {
              if (Integer.valueOf(product.getProductType())
                  .equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
                PrmGroupPurchaseLine line =
                    PrmGroupPurchaseLine.loadUniqueProduct(false, product.getPkey());
                productViews.add(
                    new ProductView(
                        specTotalPrice,
                        qty,
                        product.getPkey(),
                        product.getSupplier(),
                        product.getName(),
                        pdtImg,
                        product.getCurPrice(),
                        product.getProductType(),
                        product.getCode(),
                        line.getPkey()));
              } else {
                productViews.add(
                    new ProductView(
                        specTotalPrice,
                        qty,
                        product.getPkey(),
                        product.getSupplier(),
                        product.getName(),
                        pdtImg,
                        product.getCurPrice(),
                        product.getProductType(),
                        product.getCode(),
                        null));
              }
            }
          }

          // 获取颜色
          PdtColor color = translateUtil.getAutoTranslate(spec.gtColor(), HomeAction.curLanguage());
          // 获取规格第一张图片
          String specImg = "";
          if (spec.getPics() != null) {
            String[] pics = spec.getPics().split(",");
            specImg = pics.length > 0 ? pics[0] : "";
          }

          // 获取颜色集合
          if (colorViews.size() == 0) {
            colorViews.add(
                new ColorView(
                    product.getName(),
                    color.getPkey(),
                    color.getName(),
                    specImg,
                    product.getPkey(),
                    qty,
                    new BigDecimal(spec.getPrice().longValue() * qty)));
          } else {
            boolean flag = false;
            for (ColorView view : colorViews) {
              if (view.getId().equals(color.getPkey())
                  && view.getProId().equals(spec.getProduct())) {
                view.setQty(view.getQty() + qty);
                view.setTotalPrice(
                    view.getTotalPrice().add(new BigDecimal(qty * spec.getPrice().longValue())));
                flag = true;
              }
            }
            if (flag == false) {
              colorViews.add(
                  new ColorView(
                      product.getName(),
                      color.getPkey(),
                      color.getName(),
                      specImg,
                      product.getPkey(),
                      qty,
                      new BigDecimal(spec.getPrice().longValue() * qty)));
            }
          }

          // 获取规格集合
          specViews.add(
              new SpecView(
                  spec.getPkey(),
                  spec.gtSize().getName(HomeAction.curLanguage()),
                  spec.getPrice(),
                  qty,
                  spec.getProduct(),
                  spec.getColor()));

          // 获取运费模板集合
          freightViews.put(
              supplier.getPkey(), PltFreightSellerDAO.getFreightBySupplier(supplier.getPkey()));

          // 获取商家支付方式集合
          payViews.put(supplier.getPkey(), PltPayDAO.getPayMethodBySupplier(supplier.getPkey()));

        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }

    public Integer getAllQty() {
      return allQty;
    }

    public void setAllQty(Integer allQty) {
      this.allQty = allQty;
    }

    public BigDecimal getTotalAmt() {
      return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
      this.totalAmt = totalAmt;
    }

    public List<irille.homeAction.usr.dto.SupplierView> getSupplierViews() {
      return supplierViews;
    }

    public void setSupplierViews(List<irille.homeAction.usr.dto.SupplierView> supplierViews) {
      this.supplierViews = supplierViews;
    }

    public List<ProductView> getProductViews() {
      return productViews;
    }

    public void setProductViews(List<ProductView> productViews) {
      this.productViews = productViews;
    }

    public List<ColorView> getColorViews() {
      return colorViews;
    }

    public void setColorViews(List<ColorView> colorViews) {
      this.colorViews = colorViews;
    }

    public List<SpecView> getSpecViews() {
      return specViews;
    }

    public void setSpecViews(List<SpecView> specViews) {
      this.specViews = specViews;
    }

    public Map<Integer, List<FreightSellerlistView>> getFreightViews() {
      return freightViews;
    }

    public void setFreightViews(Map<Integer, List<FreightSellerlistView>> freightViews) {
      this.freightViews = freightViews;
    }

    public Map<Integer, List<PayTypeView>> getPayViews() {
      return payViews;
    }

    public void setPayViews(Map<Integer, List<PayTypeView>> payViews) {
      this.payViews = payViews;
    }
  }

  /** 生成订单 createdBy liyichao */
  public static class GenerateOrder extends IduOther<GenerateOrder, OdrOrder> {
    private String jsonCarts;
    private UsrPurchaseLine address;
    private Integer currency;
    private List<OdrView> odrViews;
    private Integer enterType;
    private String orderNumber = "";
    private UsrPurchaseLine billAddress;
    /** <<<<<<<以下Map的key为 supplier##type >>>>>>* */
    private Map<String, Map<PdtSpec, Integer>> orderInfo =
        new HashMap<String, Map<PdtSpec, Integer>>();

    public GenerateOrder(
        String jsonCarts,
        UsrPurchaseLine purchaseLine,
        Integer currency,
        List<OdrView> odrViews,
        Integer enterType) {
      this.jsonCarts = jsonCarts;
      this.address = purchaseLine;
      this.currency = currency;
      this.odrViews = odrViews;
      this.enterType = enterType;
    }

    public void before() {
      try {
        billAddress =
            UsrPurchaseLineDAO.listByPurchaseAddrsstype(
                    HomeAction.getPurchase().getPkey(), Usr.OAddress.BILLED)
                .get(0);
      } catch (ArrayIndexOutOfBoundsException e) {
        throw LOG.errTran("addressfrom%Please_Select_The_Billing_Address", "请先设置帐单邮寄地址");
      }
      try {
        JSONObject json = new JSONObject(jsonCarts);
        Iterator infoIte = json.keys();
        while (infoIte.hasNext()) {
          Integer specId = Integer.valueOf((String) infoIte.next());
          Integer qty = json.getInt(String.valueOf(specId));
          PdtSpec spec = BeanBase.load(PdtSpec.class, specId);
          PdtProduct product = spec.gtProduct();
          UsrSupplier supplier = product.gtSupplier();

          String key = supplier.getPkey() + "##" + product.getProductType();

          if (Integer.valueOf(product.getProductType())
              .equals(Pdt.OProductType.GENERAL.getLine().getKey())) {
            PdtSpecDAO.judgeCount(
                spec, translateUtil.getAutoTranslate(product, HomeAction.curLanguage()), qty);
          }
          if (orderInfo.get(key) == null) {
            Map<PdtSpec, Integer> orderLineInfo = new HashMap<PdtSpec, Integer>();
            orderLineInfo.put(spec, qty);
            orderInfo.put(key, orderLineInfo);
          } else {
            orderInfo.get(key).put(spec, qty);
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    public void run() {
      for (String key : orderInfo.keySet()) {
        Integer supplier = Integer.valueOf(key.split("##")[0]);
        Integer type = Integer.valueOf(key.split("##")[1]);
        Map<PdtSpec, Integer> orderDetail = orderInfo.get(key);

        List<OdrOrderLine> orderLineList = new ArrayList<OdrOrderLine>();
        BigDecimal subtotalPrice = BigDecimal.ZERO;
        for (PdtSpec spec : orderDetail.keySet()) {
          OdrOrderLine orderLine = OdrOrderLineDAO.buildOrderLine(spec, orderDetail.get(spec));
          orderLineList.add(orderLine);
          Integer qty = orderDetail.get(spec);
          subtotalPrice =
              subtotalPrice.add(
                  new BigDecimal(qty)
                      .multiply(spec.getPrice())
                      .setScale(4, BigDecimal.ROUND_HALF_UP));
          PdtSpecDAO.reduceStock(spec, qty);
          if (enterType.equals(1)) {
            UsrCartDAO.delCart(spec.getPkey());
          }
        }
        OdrOrder order = null;
        for (OdrView view : odrViews) {
          if (view.getSupplier().equals(supplier)) {
            order = getOrder(view, billAddress, address, currency, type, subtotalPrice);
            break;
          }
        }
        Idu.insLine(order, orderLineList, OdrOrderLine.T.MAIN.getFld());
        String operator = "(" + UsrPurchase.TB.getName() + ")";
        UsrPurchase purchase = order.gtPurchase();
        if (purchase.getName() == null) {
          operator += purchase.getLoginName();
        } else {
          operator += purchase.getName();
        }
        OdrHistoryDAO.add(
            order.getPkey(), order.gtState().getLine().getName(), operator, order.gtState());
        if (orderNumber.equals("")) {
          orderNumber += order.getOrderNum();
        } else {
          orderNumber += "," + order.getOrderNum();
        }
      }
    }

    public String getOrderNumber() {
      return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
    }
  }

  private static OdrOrder buildOrderNum(OdrOrder order) {
    // 设置订单号
    String timeStamp = Env.getSystemTime().getTime() + "";
    String pkey = String.valueOf(order.getPkey());
    String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
    order.setOrderNum(orderid);
    OdrOrder orderHasOrderNum = order.upd();
    return orderHasOrderNum;
  }

  /** 生成订单 */
  public static OdrOrder getOrder(
      OdrView view,
      UsrPurchaseLine billAddress,
      UsrPurchaseLine address,
      Integer currency,
      Integer type,
      BigDecimal subtotal) {
    OdrOrder order = new OdrOrder();
    try {
      order.setPurchase(HomeAction.getPurchase().getPkey());
      order.setSupplier(view.getSupplier());
      PltFreightSeller express = BeanBase.load(PltFreightSeller.class, view.getExpress());
      order.setDelivery(express.getCompany());
      order.setPagRemarks(view.getRemarks());
      order.setOdrRemarks(view.getOdrRemarks());
      order.setTime(Env.getSystemTime());
      order.setState(Odr.OdrState.WAIT.getLine().getKey());
      order.setType(type.byteValue());
      order.setFreightPrice(BigDecimal.ZERO);
      order.setInsurancePrice(BigDecimal.ZERO);
      order.setProdPrice(subtotal);

      order.setCurrency(currency);
      order.setAddress(new ObjectMapper().writeValueAsString(AddressView.trans(address)));
      order.setBillingAddress(new ObjectMapper().writeValueAsString(AddressView.trans(address)));
      order.setName(address.getName());
      order.setPostalcode(address.getEmailcode());
      order.setPhone(address.getPhonenumber());
      PltPay pay = BeanBase.load(PltPay.class, view.getPayMethod());
      order.setPaycontent(pay.getPaysetting());
      order.setServiceCharge(BigDecimal.ZERO);
      order.setAdditionalcost(BigDecimal.ZERO);
      order.setPriceTotal(
          order
              .getFreightPrice()
              .add(order.getInsurancePrice().add(order.getProdPrice()))
              .add(order.getServiceCharge())
              .add(order.getAdditionalcost()));
      order.setPayType(view.getPayMethod());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    OdrOrder orderHasOrderNum = buildOrderNum(order.ins());
    return orderHasOrderNum;
  }

  public static class intOrder extends IduOther<intOrder, OdrOrder> {
    private String jsonCarts;
    private UsrPurchaseLine address;
    private Integer currency;
    private List<OdrView> odrViews;
    private Integer enterType;
    private String orderNumber = "";
    private UsrPurchaseLine billAddress;
    /** <<<<<<<以下Map的key为 supplier##type >>>>>> */
    private Map<String, Map<PdtSpec, Integer>> orderInfo =
        new HashMap<String, Map<PdtSpec, Integer>>();

    public intOrder(
        String jsonCarts,
        UsrPurchaseLine purchaseLine,
        Integer currency,
        List<OdrView> odrViews,
        Integer enterType) {
      this.jsonCarts = jsonCarts;
      this.address = purchaseLine;
      this.currency = currency;
      this.odrViews = odrViews;
      this.enterType = enterType;
    }

    public void before() {
      try {
        billAddress =
            UsrPurchaseLineDAO.listByPurchaseAddrsstype(
                    HomeAction.getPurchase().getPkey(), Usr.OAddress.BILLED)
                .get(0);
      } catch (ArrayIndexOutOfBoundsException e) {
        throw LOG.errTran("addressfrom%Please_Select_The_Billing_Address", "请先设置帐单邮寄地址");
      }
      try {
        JSONObject json = new JSONObject(jsonCarts);
        Iterator infoIte = json.keys();
        while (infoIte.hasNext()) {
          Integer specId = Integer.valueOf((String) infoIte.next());
          Integer qty = json.getInt(String.valueOf(specId));
          PdtSpec spec = BeanBase.load(PdtSpec.class, specId);
          PdtProduct product = spec.gtProduct();
          UsrSupplier supplier = product.gtSupplier();
          String key = supplier.getPkey() + "##" + product.getProductType();
          if (Integer.valueOf(product.getProductType())
              .equals(Pdt.OProductType.GENERAL.getLine().getKey())) {
            PdtSpecDAO.judgeCount(
                spec, translateUtil.getAutoTranslate(product, HomeAction.curLanguage()), qty);
          }
          if (orderInfo.get(key) == null) {
            Map<PdtSpec, Integer> orderLineInfo = new HashMap<PdtSpec, Integer>();
            orderLineInfo.put(spec, qty);
            orderInfo.put(key, orderLineInfo);
          } else {
            orderInfo.get(key).put(spec, qty);
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    public void run() {
      for (String key : orderInfo.keySet()) {
        Integer supplier = Integer.valueOf(key.split("##")[0]);
        Integer type = Integer.valueOf(key.split("##")[1]);
        Map<PdtSpec, Integer> orderDetail = orderInfo.get(key);
        List<OdrOrderLine> orderLineList = new ArrayList<OdrOrderLine>();
        BigDecimal subtotalPrice = BigDecimal.ZERO;
        for (PdtSpec spec : orderDetail.keySet()) {
          OdrOrderLine orderLine = OdrOrderLineDAO.buildOrderLine(spec, orderDetail.get(spec));
          orderLineList.add(orderLine);
          Integer qty = orderDetail.get(spec);
          subtotalPrice =
              subtotalPrice.add(
                  new BigDecimal(qty)
                      .multiply(spec.getPrice())
                      .setScale(4, BigDecimal.ROUND_HALF_UP));
          PdtSpecDAO.reduceStock(spec, qty);
          if (enterType.equals(1)) {
            UsrCartDAO.delCart(spec.getPkey());
          }
        }
        OdrOrder order = null;
        for (OdrView view : odrViews) {
          if (view.getSupplier().equals(supplier)) {
            order = getOrder(view, billAddress, address, currency, type, subtotalPrice);
            break;
          }
        }
        order.setState(OdrState.COMPLETE.getLine().getKey());
        Idu.insLine(order, orderLineList, OdrOrderLine.T.MAIN.getFld());
        String operator = "(" + UsrPurchase.TB.getName() + ")";
        UsrPurchase purchase = order.gtPurchase();
        if (purchase.getName() == null) {
          operator += purchase.getLoginName();
        } else {
          operator += purchase.getName();
        }
        OdrHistoryDAO.add(
            order.getPkey(), order.gtState().getLine().getName(), operator, order.gtState());

        if (orderNumber.equals("")) {
          orderNumber += order.getOrderNum();
        } else {
          orderNumber += "," + order.getOrderNum();
        }
        SQL sql =
            new SQL() {
              {
                SELECT(OrderMeetingProduct.T.ORDERMEETINGID)
                    .FROM(OdrOrderLine.class)
                    .LEFT_JOIN(PdtSpec.class, PdtSpec.T.PKEY, OdrOrderLine.T.SPEC)
                    .LEFT_JOIN(
                        OrderMeetingProduct.class,
                        OrderMeetingProduct.T.PRODUCTID,
                        PdtSpec.T.PRODUCT)
                    .WHERE(OrderMeetingProduct.T.PRODUCTID, "=", PdtSpec.T.PRODUCT);
              }
            };
        sql.WHERE(OdrOrderLine.T.MAIN, "=?", order.getPkey());
        Map<String, Object> map = irille.pub.bean.Query.sql(sql).queryMap();
        if (map.size() > 0) {
          OrderMeetingOrder orderMeetingOrder = new OrderMeetingOrder();
          orderMeetingOrder.setOrderid(order.getPkey());
          orderMeetingOrder.setOrdermeetingid(
              Integer.valueOf(
                  String.valueOf(
                      irille.pub.bean.Query.sql(sql)
                          .queryMap()
                          .get(OrderMeetingProduct.T.ORDERMEETINGID.getFld().getCodeSqlField()))));
          orderMeetingOrder.setBillingstatus(Sys.OYn.YES.getLine().getKey());
          orderMeetingOrder.setWhetherToSend(Sys.OYn.NO.getLine().getKey());
          orderMeetingOrder.setPaymenttime(null);
          orderMeetingOrder.setBuyers(order.getPurchase());
          orderMeetingOrder.setPartner(order.getSupplier());
          orderMeetingOrder.setCreatedTime(Env.getSystemTime());
          orderMeetingOrder.ins();
          order.setType(Odr.OdrType.STATEONE.getLine().getKey());
          order.upd();
        }
      }
    }

    public String getOrderNumber() {
      return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
    }
  }

  /** ——————————————————————分割线(新平台)———————————————————————————————————— */
  public static Page getOrders(Integer statr, Integer limit, String orderNum, Integer state) {
    if (statr == null) {
      statr = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(OdrOrder.class).FROM(OdrOrder.class);
            if (orderNum != null) {
              WHERE(T.ORDER_NUM, "like '%" + orderNum + "%'");
            }
            if (state != null) {
              WHERE(T.STATE, "=?", state);
            }
            WHERE(T.STATE, "<> ?", OdrState.DELETED.getLine().getKey());
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryMaps().size();
    List<OdrOrderView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(statr, limit)).queryMaps().stream()
            .map(
                o ->
                    new OdrOrderView() {
                      {
                        setId((Long) o.get(T.PKEY.getFld().getCodeSqlField()));
                        setPurchase(
                            BeanBase.load(
                                    UsrPurchase.class,
                                    (Integer) o.get(T.PURCHASE.getFld().getCodeSqlField()))
                                .getName());
                        setOrderNum((String) o.get(T.ORDER_NUM.getFld().getCodeSqlField()));
                        setTime((Date) o.get(T.TIME.getFld().getCodeSqlField()));
                        setState(
                            Byte.valueOf(
                                String.valueOf(o.get(T.STATE.getFld().getCodeSqlField()))));
                        setType(
                            Byte.valueOf(String.valueOf(o.get(T.TYPE.getFld().getCodeSqlField()))));
                        setCurrency(
                            BeanBase.load(
                                    PltErate.class,
                                    (Integer) o.get(T.CURRENCY.getFld().getCodeSqlField()))
                                .getSymbol());
                        setExpressNum((String) o.get(T.EXPRESS_NUM.getFld().getCodeSqlField()));
                        setDelivery((String) o.get(T.DELIVERY.getFld().getCodeSqlField()));
                        setPagRemarks((String) o.get(T.PAG_REMARKS.getFld().getCodeSqlField()));
                        setOdrRemarks((String) o.get(T.ODR_REMARKS.getFld().getCodeSqlField()));
                        setFreightPrice(
                            (BigDecimal) o.get(T.FREIGHT_PRICE.getFld().getCodeSqlField()));
                        setInsurancePrice(
                            (BigDecimal) o.get(T.INSURANCE_PRICE.getFld().getCodeSqlField()));
                        setProdPrice((BigDecimal) o.get(T.PROD_PRICE.getFld().getCodeSqlField()));
                        setPriceTotal((BigDecimal) o.get(T.PRICE_TOTAL.getFld().getCodeSqlField()));
                        setAddress((String) o.get(T.ADDRESS.getFld().getCodeSqlField()));
                        setName((String) o.get(T.NAME.getFld().getCodeSqlField()));
                        setPostalcode((String) o.get(T.POSTALCODE.getFld().getCodeSqlField()));
                        setPhone((String) o.get(T.PHONE.getFld().getCodeSqlField()));
                        setPaycontent((String) o.get(T.PAYCONTENT.getFld().getCodeSqlField()));
                        if (o.get(T.ODR_CANCEL.getFld().getCodeSqlField()) != null) {
                          setOdrCancel(
                              Byte.valueOf(
                                  String.valueOf(o.get(T.ODR_CANCEL.getFld().getCodeSqlField()))));
                        }

                        for (PltPay.OPay_Mode value : PltPay.OPay_Mode.values()) {
                          if (value.getLine().getKey()
                              == BeanBase.load(
                                      PltPay.class,
                                      (Integer) o.get(T.PAY_TYPE.getFld().getCodeSqlField()))
                                  .getMode()) {
                            setPayType(value.getLine().getName());
                          }
                        }
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, statr, limit, count);
  }

  public static ConditionView getStatusList() {
    ConditionView view = new ConditionView();
    List<StatusView> statusList = new ArrayList<>();
    List<TypeView> typeList = new ArrayList<>();
    for (OdrState value : OdrState.values()) {
      if (value.getLine().getKey() != OdrState.DELETED.getLine().getKey()) {
        StatusView status = new StatusView();
        status.setId(value.getLine().getKey());
        status.setValue(value.getLine().getName());
        statusList.add(status);
      }
    }
    for (Odr.OdrType value : Odr.OdrType.values()) {
      TypeView type = new TypeView();
      type.setId(value.getLine().getKey());
      type.setValue(value.getLine().getName());
      typeList.add(type);
    }
    view.setStatusList(statusList);
    view.setTypeList(typeList);
    return view;
  }
  /** ——————————————————————分割线(新平台)END———————————————————————————————————— */
}
