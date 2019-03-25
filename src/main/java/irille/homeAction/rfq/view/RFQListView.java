package irille.homeAction.rfq.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQListView implements BaseView {
  private Integer pkey; // RFQpkey

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String country; // RFQ国家名称

  private String countryLogo; // RFQ国家国旗
  private String title; // RFQ标题

  @JsonFormat(pattern = "MM-dd-yyyy", timezone = "GMT+8")
  private Date time; // RFQ有效期

  private Integer quantity; // RFQ商品数量
  private String currency; // RFQ商品数量单位
}
