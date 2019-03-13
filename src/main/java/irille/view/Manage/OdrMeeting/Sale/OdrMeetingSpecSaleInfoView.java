package irille.view.Manage.OdrMeeting.Sale;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import lombok.Data;

/** 具体规格 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/15 Time: 15:12 */
@Data
public class OdrMeetingSpecSaleInfoView {

  private int id;

  @SetBean(OriginalField = "specName")
  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name;

  private String order_num;
  private Integer counts;
  private Double newprice;
  private Integer qty;
  private Double subtotal;
  private Long overall;
  private String pic;

  public void setPic(String pic) {
    if (pic != null) {
      String[] string = pic.split(",");
      if (string.length > 0) {
        this.pic = string[0];
      } else {
        this.pic = pic;
      }
    }
  }
}
