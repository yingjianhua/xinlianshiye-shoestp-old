package irille.view.Manage.OdrMeeting.initiatedActivity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/**
 * 订购会订单列表
 *
 * @author zjl
 * @date 2018/11/29 16:29
 */
@Data
public class orderOrderListView implements BaseView {
  private Integer orderId; // 订单pkey
  private Integer whetherToSend; // 是否发送
  private Integer omtStatus; // 活动状态
  private Integer omtSupplier; // 订购会发起者
  private Integer supplier; // 合作商
  private Integer product; // 产品id
  private String orderNum; // 订单号
  private String email; // 邮箱
  private BigDecimal prodPrice; // 产品总价
  private BigDecimal priceTotal; // 订单总额
  private Integer orderStatus; // 订单状态

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String country; // 国家

  private String paymentTime; // 付款时间
  private Integer whetherSettlement; // 是否结算
  private String time; // 下单时间
}
