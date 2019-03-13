package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultRelationView implements BaseView {

  private Boolean unread; // 是否有未读消息
  private RFQQuotationView quotation; // 报价信息
  private RFQSupplierView supplier; // 供应商
  private RFQConsultView consult; // 询盘
}
