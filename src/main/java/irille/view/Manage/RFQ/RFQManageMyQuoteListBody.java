package irille.view.Manage.RFQ;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/31 Time: 15:40 */
@Data
public class RFQManageMyQuoteListBody {
  private int id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
  private Date rfqCreate_date;

  private String descriotion;
  private String title;
  private int quantity;
  private String quoteTitle;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
  private Date quoteRFQCreate_date;

  private String quoteDescriotion;
  private int status;
  private String PurchaseName; // 采购商名称
  private String PurchaseCountryIMG; // 采购商注册时选择的国家
}
