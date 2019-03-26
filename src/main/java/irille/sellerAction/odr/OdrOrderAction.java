package irille.sellerAction.odr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.pub.Exp;
import irille.pub.Str;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.Tb;
import irille.sellerAction.SellerAction;
import irille.sellerAction.odr.inf.IOdrOrderAction;
import irille.shop.odr.Odr;
import irille.shop.odr.Odr.OdrState;
import irille.shop.odr.Odr.OdrType;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.odr.OdrOrderLine;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltPayDAO;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import irille.view.odr.OrderView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OdrOrderAction extends SellerAction<OdrOrder> implements IOdrOrderAction {
  private List<OdrOrderLine> _listLine = new ArrayList<OdrOrderLine>();

  public List<OdrOrderLine> getListLine() {
    return _listLine;
  }

  public void setListLine(List<OdrOrderLine> listLine) {
    this._listLine = listLine;
  }

  @Override
  public OdrOrder insRun() throws Exception {
    OdrOrderDAO.Ins insDao = new OdrOrderDAO.Ins();
    insDao.setB(getBean());
    insDao.setLines(getListLine());
    insDao.commit();
    return insDao.getB();
  }

  @Override
  public OdrOrder updRun() throws Exception {
    OdrOrderDAO.Upd upd = new OdrOrderDAO.Upd();
    upd.setB(getBean());
    upd.setLines(getListLine());
    upd.commit();
    return upd.getB();
  }

  public void del() throws Exception {
    OdrOrderDAO.Del del = new OdrOrderDAO.Del();
    del.setB(getBean());
    del.commit();
    writeSuccess(getBean());
  }

  /**
   * 加载订单支付方式
   *
   * @throws zw
   */
  public void loadpay() throws Exception {
    write(PltPayDAO.listViewBySupplier(getSupplier().getPkey()));
  }

  /**
   * 加载订单状态
   *
   * @throws zw
   */
  public void loadstate() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    for (OdrState o : Odr.OdrState.values()) {
      JSONObject lineJsona = new JSONObject();
      lineJsona.put("name", o.getLine().getName());
      lineJsona.put("pkey", o.getLine().getKey());
      ja.put(lineJsona);
    }
    json.put("STORE_ROOT", ja);
    writerOrExport(json);
  }

  /**
   * 加载订单类型
   *
   * @throws zw
   */
  public void loadtype() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    for (OdrType o : Odr.OdrType.values()) {
      JSONObject lineJsona = new JSONObject();
      lineJsona.put("name", o.getLine().getName());
      lineJsona.put("pkey", o.getLine().getKey());
      ja.put(lineJsona);
    }
    json.put("STORE_ROOT", ja);
    writerOrExport(json);
  }

  @Override
  public String crtFilter() throws JSONException {
    String sql = "";
    if (Str.isEmpty(getFilter())) {
      sql +=
          " AND "
              + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
              + "="
              + SellerAction.getSupplier().getPkey();
      return crtFilterAll() + sql + orderByAsc();
    }
    JSONArray ja = new JSONArray(getFilter());
    Tb tb = tb();
    sql +=
        " AND "
            + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
            + "="
            + SellerAction.getSupplier().getPkey();
    for (int i = 0; i < ja.length(); i++) {
      JSONObject json = ja.getJSONObject(i);
      String fldName = json.getString(QUERY_PROPERTY);
      String param = json.getString(QUERY_VALUE);
      if (fldName.equals("purchase.name")) {
        sql +=
            " AND "
                + OdrOrder.T.PURCHASE.getFld().getCodeSqlField()
                + " IN (SELECT "
                + UsrPurchase.T.PKEY.getFld().getCodeSqlField()
                + " FROM "
                + UsrPurchase.TB.getCodeSqlTb()
                + " WHERE "
                + UsrPurchase.T.NAME.getFld().getCodeSqlField()
                + " LIKE '%"
                + param
                + "%')";
        continue;
      }
      if (Str.isEmpty(param)) continue;
      if (!tb.chk(fldName)) continue;
      Fld fld = tb.get(fldName);
      if (fld == null) continue;
      sql += " AND " + Env.INST.getDB().crtWhereSearch(fld, param);
    }
    return crtFilterAll() + sql + orderBy();
  }

  /**
   * 查询订单
   *
   * @throws zw
   */
  private OrderSearchView search;

  @Getter @Setter private Integer productId;
  @Getter @Setter private Integer billingStatus;

  @Override
  public void list() throws Exception {
    Page<OrderView> page =
        OdrOrderDAO.pageBySupplierWithDetail(
            getSupplier().getPkey(),
            search,
            getStart(),
            getLimit(),
            PltConfigDAO.supplierLanguage(getSupplier()));
    write(page);
  }

  public void lists() throws IOException {
    write(
        OdrOrderDAO.lists(
            getStart(),
            getLimit(),
            search,
            billingStatus,
            productId,
            PltConfigDAO.supplierLanguage(getSupplier())));
  }

  /** 取消订单 */
  public void cancel() throws IOException {
    OdrOrderDAO.updcancel upd = new OdrOrderDAO.updcancel();
    getBean().stState(OdrState.CANCEL);
    upd.setB(getBean());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 修改订单类型
   *
   * @throws zw
   */
  public void updline() throws Exception {
    OdrOrderDAO.updline upd = new OdrOrderDAO.updline();
    upd.setB(getBean());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 修改订单地址
   *
   * @throws zw
   */
  public void updaddress() throws Exception {
    OdrOrderDAO.updaddress upd = new OdrOrderDAO.updaddress();
    upd.setB(getBean());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 修改订单 运费模板
   *
   * @author zw
   */
  public void updcompany() throws Exception {
    OdrOrderDAO.updcompany upd = new OdrOrderDAO.updcompany();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  /**
   * 修改订单确认发货
   *
   * @author zw
   */
  public void upddeliver() throws Exception {
    OdrOrderDAO.upddeliver upd = new OdrOrderDAO.upddeliver();
    upd.setB(getBean());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 修改订单
   *
   * @throws zw
   */
  public void updorder() throws Exception {
    OdrOrderDAO.updorder upd = new OdrOrderDAO.updorder();
    upd.setB(getBean());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 查找订单详情页面
   *
   * @throws zw
   */
  private String orderNum;

  /**
   * 商家获取订单详情内容
   *
   * @throws Exception
   */
  @Override
  public void select() throws Exception {
    OrderView view =
        OdrOrderDAO.findViewByOrderNumSupplier4Supplier(
            orderNum, getSupplier(), PltConfigDAO.supplierLanguage(getSupplier()));
    write(view);
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public OrderSearchView getSearch() {
    return search;
  }

  public void setSearch(OrderSearchView search) {
    this.search = search;
  }
}
