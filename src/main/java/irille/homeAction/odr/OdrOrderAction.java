package irille.homeAction.odr;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import irille.Dao.Old.Odr.GenerateOrder;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.odr.inf.IOdrOrderAction;
import irille.homeAction.usr.dto.OdrView;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.shop.odr.OdrHistoryDAO;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.odr.OdrOrderLine;
import irille.shop.plt.PltCountryDAO;
import irille.shop.plt.PltErateDAO;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltProvinceDAO;
import irille.shop.usr.*;
import irille.view.Page;
import irille.view.odr.OrderView;
import irille.view.plt.CurrencyView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 采购商action
 *
 * @author yjh
 */
public class OdrOrderAction extends HomeAction<OdrOrder> implements IOdrOrderAction {
  private static final LogMessage LOG = new LogMessage(OdrOrderAction.class);
  private static final long serialVersionUID = 4656654859885701903L;

  private static final UsrCartDAO.Query UsrCartquery = new UsrCartDAO.Query(); // 购物车
  private static final OdrOrderDAO.Query query = new OdrOrderDAO.Query();
  private static final OdrHistoryDAO.Ins hisIns = new OdrHistoryDAO.Ins();

  private Integer status;
  private String orderId;
  private String prmorderId;

  private OdrOrder odrOrder;
  private Map<String, Object> map = new HashMap<String, Object>();

  private String strSpec;
  private String address;
  private Integer delivery;
  private Integer payType;
  private String carts;

  /**
   * 查询我的订单列表内容(分页查询)pc和mobile返回不同的内容
   *
   * @author yingjianhua
   */
  @NeedLogin
  public void list() throws Exception {
    Page<OrderView> page;
    if (isMobile())
      page = OdrOrderDAO.pageByPurchaseWithDetail(getPurchase().getPkey(), 0, 0, curLanguage());
    else
      page =
          OdrOrderDAO.pageByPurchase(getPurchase().getPkey(), getStatus(), getStart(), getLimit());
    write(page);
  }

  private Page<OrderView> orderViews;

  /**
   * 我的订单页面
   *
   * @throws IOException
   * @throws JSONException
   * @throws JsonMappingException
   * @throws JsonParseException
   */
  @Override
  @NeedLogin
  public String orders() throws Exception {
    if (!isMobile()) orderViews = OdrOrderDAO.pageByPurchase(getPurchase().getPkey(), -1, 0, 8);
    setResult("/home/my-order.jsp");
    return TRENDS;
  }

  private OrderView orderView;

  /**
   * 订单详情页面
   *
   * @throws Exception
   * @author yingjianhua
   */
  @Override
  @NeedLogin
  public String detail() throws Exception {
    orderView = OdrOrderDAO.detail(orderId, getPurchase(), curLanguage());
    setResult("/home/order-detail.jsp");
    return TRENDS;
  }

  /**
   * 订单详情页面打印
   *
   * @throws Exception
   * @throws IOException
   * @throws JSONException
   * @author yingjianhua
   */
  @Override
  public void print() throws JsonParseException, JsonMappingException, JSONException, IOException {
    orderView = OdrOrderDAO.detailprint(orderId, curLanguage());
    write(orderView);
  }

  /**
   * 确认收货
   *
   * @throws IOException
   * @author yingjianhua
   */
  @NeedLogin
  public void confirmReceiving() throws IOException {
    OdrOrderDAO.confirmReceiving(orderNumber, getPurchase().getPkey());
    write();
  }

  /**
   * 跳转订单打印
   *
   * @author zhangwei
   */
  public String junpOrderPrint() throws Exception {
    setResult("/home/order-print.jsp");
    return TRENDS;
  }

  private String odrRemarks;
  private String state;
  private String specAndQty;
  private String pagRemarks;
  private Integer currency;

  /**
   * xiayan app
   *
   * @updateBy liyichao
   * @updateTime 2018-8-17 用户新增订单 json 1:成功 -1:商品不为同一供应商 -2 :未找到商品
   */
  public void appAddOrder() throws Exception {
    List<UsrCart> carts = new ArrayList<UsrCart>();
    if (state.equals("0")) {
      carts =
          BeanBase.list(
              UsrCart.class,
              UsrCart.T.PKEY.getFld().getCodeSqlField() + " in ( " + getCarts() + ") ",
              false);
    } else {
      JSONObject js = new JSONObject(specAndQty);
      Iterator it = js.keys();
      while (it.hasNext()) {
        // 获取map的key
        String key = (String) it.next();
        // 得到value的值
        Integer value = (Integer) js.get(key);
        UsrCart uc = new UsrCart();
        uc.setSpec(Integer.valueOf(key));
        uc.setQty(value);
        uc.setSupplier(uc.gtSpec().gtProduct().getSupplier());
        uc.setAmtTotal(uc.gtSpec().getPrice().multiply(new BigDecimal(uc.getQty())));
        carts.add(uc);
      }
    }
    if (getAddress() == null) {
      throw LOG.errTran("addressfrom%Please_Select_The_Shipping_Address", "请选择收货地址");
    }
    UsrPurchaseLine address = BeanBase.load(UsrPurchaseLine.class, getAddress());
    OdrOrderDAO.BuildOrderByCart buildOrder = new OdrOrderDAO.BuildOrderByCart();
    if (delivery != null) {
      buildOrder.setDelivery(BeanBase.load(PltFreightSeller.class, delivery).getCompany());
    }
    buildOrder.setPayType(payType);
    buildOrder.setCarts(carts);
    buildOrder.setOdrRemarks(getOdrRemarks());
    buildOrder.setAddress(address);
    buildOrder.setState(state);
    buildOrder.setCurrency(getCurrency());
    buildOrder.setPagRemarks(getPagRemarks());
    JSONObject json = new JSONObject();
    buildOrder.commit();
    String ordersNum = buildOrder.getOrderNum();
    json.put("order", ordersNum);
    writerOrExport(json);
  }

  private String payContent;

  /** 确认付款 */
  public void pay() throws IOException, JSONException {
    OdrOrderDAO.pay(orderNumber, payContent, currency, getPurchase());
    write();
  }

  private List<OdrView> odrView;

  public void submitOrder() throws IOException {
    OdrOrderDAO.BuildOrder buildOrder = new OdrOrderDAO.BuildOrder();
    buildOrder.setAddress(getPurchaseLine());
    buildOrder.setCarts(getCarts());
    buildOrder.setOdrView(getOdrView());
    buildOrder.setCurrency(getCurrency());
    try {
      buildOrder.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  public List<OdrView> getOdrView() {
    return odrView;
  }

  public void setOdrView(List<OdrView> odrView) {
    this.odrView = odrView;
  }

  /*
   * 订单详情
   *
   */
  private String id;
  private String orderNumber;
  private List<CurrencyView> currencys;

  /** 订单支付页面 */
  @NeedLogin
  public String payOrder() throws JSONException, IOException {
    orderView = OdrOrderDAO.findSummary(getOrderNumber(), getPurchase().getPkey(), curLanguage());
    currencys = PltErateDAO.listCurrencyView();
    setResult("/home/cart3.jsp");
    return TRENDS;
  }

  private List<OdrOrderLine> specList;
  private Integer purchaseLine;

  /**
   * 统计订单
   *
   * @throws Exception
   */
  public void staOrder() throws Exception {
    UsrPurchaseLine address = BeanBase.load(UsrPurchaseLine.class, getPurchaseLine());
    OdrOrderDAO.StaOrder staOrder = new OdrOrderDAO.StaOrder();
    staOrder.setAddress(address);
    staOrder.setB(getBean());
    staOrder.setId(getId());
    staOrder.setSpecList(getSpecList());
    staOrder.commit();
    String orderNum = staOrder.getOrderNum();
    JSONObject json = new JSONObject();
    json.put(OdrOrder.T.ORDER_NUM.getFld().getCode(), orderNum);
    writerOrExport(json);
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSpecAndQty() {
    return specAndQty;
  }

  public void setSpecAndQty(String specAndQty) {
    this.specAndQty = specAndQty;
  }

  public String getPrmorderId() {
    return prmorderId;
  }

  public void setPrmorderId(String prmorderId) {
    this.prmorderId = prmorderId;
  }

  public String getCarts() {
    return carts;
  }

  public void setCarts(String carts) {
    this.carts = carts;
  }

  public String getStrSpec() {
    return strSpec;
  }

  public void setStrSpec(String strSpec) {
    this.strSpec = strSpec;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }

  public OdrOrder getOdrOrder() {
    return odrOrder;
  }

  public void setOdrOrder(OdrOrder odrOrder) {
    this.odrOrder = odrOrder;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Integer getPurchaseLine() {
    return purchaseLine;
  }

  public void setPurchaseLine(Integer purchaseLine) {
    this.purchaseLine = purchaseLine;
  }

  public List<OdrOrderLine> getSpecList() {
    return specList;
  }

  public void setSpecList(List<OdrOrderLine> specList) {
    this.specList = specList;
  }

  public irille.view.odr.OrderView getOrderView() {
    return orderView;
  }

  public void setOrderView(irille.view.odr.OrderView orderView) {
    this.orderView = orderView;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOdrRemarks() {
    return odrRemarks;
  }

  public void setOdrRemarks(String odrRemarks) {
    this.odrRemarks = odrRemarks;
  }

  public Page<OrderView> getOrderViews() {
    return orderViews;
  }

  public void setOrderViews(Page<OrderView> orderViews) {
    this.orderViews = orderViews;
  }

  public Integer getDelivery() {
    return delivery;
  }

  public void setDelivery(Integer delivery) {
    this.delivery = delivery;
  }

  public String getPagRemarks() {
    return pagRemarks;
  }

  public void setPagRemarks(String pagRemarks) {
    this.pagRemarks = pagRemarks;
  }

  public List<CurrencyView> getCurrencys() {
    return currencys;
  }

  public void setCurrencys(List<CurrencyView> currencys) {
    this.currencys = currencys;
  }

  public String getPayContent() {
    return payContent;
  }

  public void setPayContent(String payContent) {
    this.payContent = payContent;
  }

  public Integer getCurrency() {
    return currency;
  }

  public void setCurrency(Integer currency) {
    this.currency = currency;
  }

  /** <<<<<<<<<<<<<< ---------- 订单确认页 -------------- >>>>>>>>>>>>>>>>>* */
  /** createdBy liyichao */
  private static final Map<Integer, String> CONFIRM_ORDER_MAP =
      new ConcurrentHashMap<Integer, String>();

  public static Map<Integer, String> getConfirmOrderMap() {
    return CONFIRM_ORDER_MAP;
  }

  // json {"规格":数量}
  private String jsonCarts;
  private String supplier;
  private Integer enterType = 0;

  // 返回页面map
  public String toSettlementPage() {
    if (getPurchase() == null) {
      setJumpUrl("/home/usr_UsrPurchase");
      return LOGIN;
    }
    if (CONFIRM_ORDER_MAP.get(getPurchase().getPkey()) == null) {
      setResult("/home/usr_UsrPurchase");
      return RTRENDS;
    }
    if (getJsonCarts() == null) {
      setResult("/home/usr_UsrPurchase");
      return RTRENDS;
    }
    JSONObject info = null;
    try {
      info = new JSONObject(getJsonCarts());
    } catch (JSONException e) {
      e.printStackTrace();
    }

    OdrOrderDAO.SettlePage settlePage = new OdrOrderDAO.SettlePage(info);
    settlePage.commit();

    // 获取采购商收获地址
    List<UsrPurchaseLine> commonAddress =
        UsrPurchaseLineDAO.listByPurchaseAddrsstype(getPurchase().getPkey(), Usr.OAddress.COMMON);
    // 获取采购商默认收获地址
    UsrPurchaseLine defaultAddress = null;
    try {
      defaultAddress =
          BeanBase.list(
                  UsrPurchaseLine.class,
                  UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()
                      + " = ? AND "
                      + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField()
                      + " =? AND "
                      + UsrPurchaseLine.T.ISDEFAULT.getFld().getCodeSqlField()
                      + " =? ",
                  false,
                  getPurchase().getPkey(),
                  Usr.OAddress.COMMON.getLine().getKey(),
                  Sys.OYn.YES.getLine().getKey())
              .get(0);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("未设置默认收获地址");
      // throw LOG.err("noDefaultAddress","未设置默认收获地址");
    }
    // 获取采购商账单地址
    UsrPurchaseLine billAddress = null;
    try {
      billAddress =
          UsrPurchaseLineDAO.listByPurchaseAddrsstype(getPurchase().getPkey(), Usr.OAddress.BILLED)
              .get(0);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("未设置账单地址");
      // throw LOG.err("noBillAddress","未设置账单地址");
    }
    // 获取国家列表
    // 获取省列表
    map.put("defaultAddress", defaultAddress);
    map.put("allQyt", settlePage.getAllQty());
    map.put("allAmt", settlePage.getTotalAmt());
    map.put("addressList", commonAddress); // 收获地址集合
    map.put("billAddress", billAddress); // 账单地址集合
    map.put("countries", PltCountryDAO.list(curLanguage())); // 国家地址集合
    map.put("provinces", PltProvinceDAO.list(curLanguage())); // 省份地址集合
    map.put("suppliers", settlePage.getSupplierViews()); // 供应商集合
    map.put("products", settlePage.getProductViews()); // 产品集合
    map.put("colors", settlePage.getColorViews()); // 颜色集合
    map.put("specs", settlePage.getSpecViews()); // 规格集合
    map.put("freights", settlePage.getFreightViews()); // 运费模板集合
    map.put("payMethods", settlePage.getPayViews()); // 支付方式集合
    map.put("enterType", getEnterType());
    if (getSupplier() != null) {
      UsrPurchase purchase = UsrPurchase.loadUniqueLogin_name(false, getSupplier());
      setPurchase(purchase);
    }
    setResult("/home/settlementPage.jsp");
    return TRENDS;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public String getJsonCarts() {
    return jsonCarts;
  }

  public void setJsonCarts(String orderInfo) {
    this.jsonCarts = orderInfo;
  }

  public Integer getEnterType() {
    return enterType;
  }

  public void setEnterType(Integer enterType) {
    this.enterType = enterType;
  }
  /** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 生成订单 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>* */
  /**
   * 商品信息:jsonCarts 地址信息:purchaseLine 商家信息:OdrView ---> 供应商pkey == supplier ---> 包裹备注PAG_REMARKS ==
   * remarks ---> 支付方式PAY_TYPE == payMethod ---> 快递方式DELIVERY == express ---> 订单备注ODR_REMARKS ==
   * odrRemarks createdBy liyichao
   */
  @Inject private GenerateOrder generateOrder;

  public void generateOrder() throws Exception {
    UsrPurchaseLine address = null;
    try {
      address = BeanBase.load(UsrPurchaseLine.class, getPurchaseLine());
    } catch (Exp e) {
      throw LOG.errTran("addressfrom%Please_Select_The_Shipping_Address", "请选择收货地址");
    }
    generateOrder.setJsonCarts(getJsonCarts());
    generateOrder.setAddress(address);
    generateOrder.setCurrency(curCurrency().getPkey());
    generateOrder.setOdrViews(getOdrView());
    generateOrder.setEnterType(getEnterType());
    JSONObject json = new JSONObject();
    generateOrder.commit();
    CONFIRM_ORDER_MAP.remove(getPurchase().getPkey());
    String orderNumber = generateOrder.getOrderNumber();
    json.put("ret", 1);
    json.put("orderNumbers", orderNumber);
    writerOrExport(json);
  }

  public void insOrder() throws Exception {
    UsrPurchaseLine address = null;
    try {
      address = BeanBase.load(UsrPurchaseLine.class, getPurchaseLine());
    } catch (Exp e) {
      throw LOG.errTran("addressfrom%Please_Select_The_Shipping_Address", "请选择收货地址");
    }
    OdrOrderDAO.intOrder intOrder =
        new OdrOrderDAO.intOrder(
            getJsonCarts(), address, curCurrency().getPkey(), getOdrView(), getEnterType());
    JSONObject json = new JSONObject();
    intOrder.commit();
    CONFIRM_ORDER_MAP.remove(getPurchase().getPkey());
    String orderNumber = intOrder.getOrderNumber();
    json.put("ret", 1);
    json.put("orderNumbers", orderNumber);
    writerOrExport(json);
  }
}
