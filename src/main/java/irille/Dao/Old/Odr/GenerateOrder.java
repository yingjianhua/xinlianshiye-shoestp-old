package irille.Dao.Old.Odr;

import java.math.BigDecimal;
import java.util.*;

import com.google.inject.Inject;

import irille.Service.Eo.EasyOdrService;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.OdrView;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduOther;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.odr.OdrHistoryDAO;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.odr.OdrOrderLineDAO;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.usr.*;
import irille.view.Easy.EasyodrView;
import irille.view.Easy.EolineView;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import static irille.shop.odr.OdrOrderDAO.getOrder;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/14 Time: 10:16 */
@Data
public class GenerateOrder extends IduOther<GenerateOrder, OdrOrder> {
  private static final LogMessage LOG = new LogMessage(GenerateOrder.class);

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

  @Inject private EasyOdrService easyOdrService;

  public void before() {
    Integer purchaseId = HomeAction.getPurchase().getPkey();
    try {
      billAddress =
          UsrPurchaseLineDAO.listByPurchaseAddrsstype(purchaseId, Usr.OAddress.BILLED).get(0);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw LOG.errTran("addressfrom%Please_Select_The_Billing_Address", "请先设置帐单邮寄地址");
    }
    try {
      // 购物车 json   spec :数量
      JSONObject json = new JSONObject(jsonCarts);
      Iterator infoIte = json.keys();
      while (infoIte.hasNext()) {
        Integer specId = Integer.valueOf((String) infoIte.next());
        Integer qty = json.getInt(String.valueOf(specId));
        PdtSpec spec = BeanBase.load(PdtSpec.class, specId);
        PdtProduct product = spec.gtProduct();
        UsrSupplier supplier = product.gtSupplier();
        String key = supplier.getPkey() + "##" + product.getProductType();
        // 比较商品类型
        if (product.gtProductType() == Pdt.OProductType.GENERAL) {
          PdtSpecDAO.judgeCount(
              spec, translateUtil.getAutoTranslate(product, HomeAction.curLanguage()), qty);
        } else {
          // 新的联合采购订单
          // 新的简单订单
          List<EasyodrView> list = new ArrayList<>();
          for (OdrView odrView : odrViews) {
            EasyodrView easyodrView = new EasyodrView();
            easyodrView.setSupplierid(odrView.getSupplier());
            easyodrView.setRemarks(odrView.getRemarks());
            List l = new ArrayList();
            EolineView eolineView = new EolineView();
            eolineView.setId(String.valueOf(spec.getPkey()));
            eolineView.setProductname(spec.gtKeyName().toString());
            eolineView.setColor(spec.gtColor().toString());
            eolineView.setSize(spec.gtSize().toString());
            eolineView.setNum(String.valueOf(qty));
            eolineView.setRemarks(odrView.getRemarks());
            l.add(eolineView);
            easyodrView.setList(l);
            list.add(easyodrView);
          }
          easyOdrService.generateOrder(address.getPkey(), purchaseId, list);
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
    } catch (Exception e) {
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
