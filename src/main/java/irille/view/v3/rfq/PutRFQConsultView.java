package irille.view.v3.rfq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 9:43 */
@Data
public class PutRFQConsultView implements BaseView {
  private String title;
  //    描述
  private String descriotion;
  private String images;
  // 数量
  private int quantity;
  // 数量单位  枚举类对应
  private int unit = 1;
  private BigDecimal min_price;
  private BigDecimal max_price;
  //    币种外键
  private int currency;
  private Date valid_date;
  //    支付类型
  private int pay_type;
  //    运送条款
  private int shipping_type;
  //    目的地
  private String destination;
  // 额外请求
  private List<String> extraRequest;
}
