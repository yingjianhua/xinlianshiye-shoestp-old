package irille.view.Manage.RFQ;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/31 Time: 15:40 */
@Data
public class RFQManageMyQuoteListBody implements BaseView {
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
  /**
   * 消息读取状态
   * <li>1：　采购商未读
   * <li>2：　采购商已读
   * <li>3：　供应商未读
   * <li>4：　供应商已读
   */
  private Byte readStatus;
  private String purchaseName; // 采购商名称
  private String purchaseCountryIMG; // 采购商注册时选择的国家国旗
  private String purchaseCountry; // 采购商注册时选择的国家
  private Integer purchaseCountryPkey; // 采购商注册时选择的国家Pkey
  private Map<Integer, Object> purchaseCountryMap;
}
