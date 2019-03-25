package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQPurchaseContactGroupView implements BaseView {

  private Integer pkey;
  private String name;
  private Integer count; // 该分组下有几个联系人
}
