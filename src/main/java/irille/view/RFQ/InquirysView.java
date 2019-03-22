package irille.view.RFQ;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class InquirysView implements BaseView {
  private Integer rfqPkey;//询盘pkey
  private Byte rfqStatus;
  private String productName;
  private String productCategories;
  private Integer quantity;
  private String purchaseName;
  private String purchaseCountry;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
  private Date releaseTime;

  private String supplierName;
  private Byte grade;
}
