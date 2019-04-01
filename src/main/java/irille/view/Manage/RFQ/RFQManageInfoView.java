package irille.view.Manage.RFQ;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 15:47 */
@Data
public class RFQManageInfoView implements BaseView {
  private int id;
  private String title;
  private Byte type; // 询盘类型
  private Integer productId; // 产品主键, 普通产品询盘和私人展厅产品询盘时 有值
  private String purchaseName;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
  private Date create_date;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
  private Date valid_date;

  private int left_count;
  private boolean inquiry;
  private String image;
  private String productImage;// 产品图片 只有产品询盘和私人展厅产品询盘时 该字段会有值
  private BigDecimal min_price;
  private BigDecimal max_price;
  private int countryId;
  private int quantity;
  private String descriotion; // 描述
  private List<String> moreInformation; // RFQ额外信息, 用户可以在询盘没有报价的情况下, 添加三次信息
  private String pay_type;
  private String shipping_type;
  private Integer currency;
  private String destination; // 目的地
  private List quotation_record;
  private String currencyname; // 货币名称
  // -1 close 1报价  0可以报价
  private int status;
}
