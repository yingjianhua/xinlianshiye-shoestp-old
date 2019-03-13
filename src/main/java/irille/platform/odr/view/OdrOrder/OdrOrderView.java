package irille.platform.odr.view.OdrOrder;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OdrOrderView implements BaseView {
  private Long id; // pkey
  private String purchase; // 用户
  private String orderNum; // 订单号

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
  private Date time; // 下单时间

  private Byte state; // 订单状态
  private Byte type; // 订单类型
  private String currency; // 货币
  private String expressNum; // 运单号
  private String delivery; // 发货方式
  private String pagRemarks; // 包裹备注
  private String odrRemarks; // 订单备注
  private BigDecimal freightPrice; // 运费
  private BigDecimal insurancePrice; // 保险费
  private BigDecimal prodPrice; // 产品总价
  private BigDecimal priceTotal; // 总价
  private String address; // 地址
  private String name; // 名字
  private String postalcode; // 邮政编码
  private String phone; // 电话号码
  private String paycontent; // 支付内容
  private Byte odrCancel; // 取消原因
  private String payType; // 支付方式
}
