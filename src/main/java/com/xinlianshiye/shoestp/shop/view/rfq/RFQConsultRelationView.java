package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultRelationView implements BaseView {

	private RFQQuotationView quotation;
	private RFQSupplierView supplier;
}
