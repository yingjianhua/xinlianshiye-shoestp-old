package irille.view.Manage.OdrMeeting.Sale;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/** 订购会销售信息 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:42 */
@Data
public class OdrMeetingSaleInfoView implements BaseView {

  private int id;
  private int pdtId;
  private String pdtImage;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String pdtName;

  private BigDecimal newPrice;
  private BigDecimal qty;
  private BigDecimal price_total;
  private boolean status;
  private List<OdrMeetingSpecSaleInfoView> items;
  private boolean type;

  public void setPdtImage(String pdtImage) {
    String[] strings = pdtImage.split(",");
    if (strings.length > 0) {
      this.pdtImage = strings[0];
      return;
    }
    this.pdtImage = "";
  }
}
