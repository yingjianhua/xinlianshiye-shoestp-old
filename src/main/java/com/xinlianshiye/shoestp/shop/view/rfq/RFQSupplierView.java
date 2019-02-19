package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQSupplierView implements BaseView {

	private Integer pkey;
	private String name;
	private RFQCountryView country;
}
