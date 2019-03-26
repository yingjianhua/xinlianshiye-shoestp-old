package irille.view.Manage.OdrMeeting;

import java.util.Map;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OdrMeetingOrderView implements BaseView {
  private Map<Integer, Integer> specificationQuantity; // 规格id:数量
  private Integer supplier; // 供应商
  private String productNote; // 产品备注
  private Integer paymentMethod; // 支付方式id
  private String deliveryMethod; // 配送方式Id
  private String orderNotes; // 订单备注
}
