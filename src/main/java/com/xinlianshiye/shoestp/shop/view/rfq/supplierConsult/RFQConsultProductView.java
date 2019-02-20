package com.xinlianshiye.shoestp.shop.view.rfq.supplierConsult;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultProductView implements BaseView {

	private Integer pkey;//产品主键
	private String name;//产品名称
	private String image;//产品图片
}
