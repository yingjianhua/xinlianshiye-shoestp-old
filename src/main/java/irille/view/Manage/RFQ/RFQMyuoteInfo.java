package irille.view.Manage.RFQ;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/31 Time: 19:12 */
@Data
public class RFQMyuoteInfo implements BaseView {
  private int id;
  private String title;
  private String descriotion;
  private String images;
  private int quantity;
  private int currency;
  private int shipping_type;
  private int min_price;
  private int max_price;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date valid_date;

  private int pay_type;
  private boolean sample; // 是否提供样品
  private String companyDescribe; // 公司简介
  private String throwaway; // 宣传图片
}
