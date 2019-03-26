package irille.view.Manage.OdrMeeting.Sale;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class OdrSalesDetailsView implements BaseView {
  private Integer pkey; // 产品pkey

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String productName; // 产品名称

  private String productImage; // 产品图片
  private Integer billingStatus; // 自有/合作商状态
  private BigDecimal meetingPrice; // 订购会价
  private Integer status; // 上下架状态
  private List<ColorView> color; // 规格
  private Integer activeSatatus; // 活动状态
  private Integer qty; // 数量
  private Integer persons; // 人数
  private BigDecimal subTotal; // 总金额
}
