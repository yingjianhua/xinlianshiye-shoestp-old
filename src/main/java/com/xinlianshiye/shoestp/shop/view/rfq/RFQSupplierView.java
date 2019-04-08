package com.xinlianshiye.shoestp.shop.view.rfq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import irille.view.v3.svs.SvsRatingAndRosDTO;
import lombok.Data;

@Data
public class RFQSupplierView implements BaseView {

  private Integer pkey; // 主键
  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name; // 企业名字
  private String logo; // 商家logo
  @JsonSerialize(using = I18NFieldSerializer.class)
  private String contacts; // 联系人名字
  private RFQCountryView country; // 国家
  private SvsRatingAndRosDTO svsInfo;//供应商的svs信息
}
