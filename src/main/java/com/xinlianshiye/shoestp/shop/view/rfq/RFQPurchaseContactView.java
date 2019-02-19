package com.xinlianshiye.shoestp.shop.view.rfq;

import java.util.Date;
import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQPurchaseContactView implements BaseView {

	private Integer pkey;//主键
	private Date createdDate;//添加时间
	private RFQSupplierView supplier;//供应商
	private List<RFQConsultRelationView> relation;//询盘关联
}
