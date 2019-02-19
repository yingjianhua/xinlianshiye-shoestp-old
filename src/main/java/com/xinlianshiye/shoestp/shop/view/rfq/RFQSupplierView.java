package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQSupplierView implements BaseView {

	private Integer pkey;//主键
	private String name;//企业名字
	private String contacts;//联系人名字
	private RFQCountryView country;//国家
}
