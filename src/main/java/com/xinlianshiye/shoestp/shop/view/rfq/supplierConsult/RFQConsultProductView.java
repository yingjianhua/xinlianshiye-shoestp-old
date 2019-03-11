package com.xinlianshiye.shoestp.shop.view.rfq.supplierConsult;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultProductView implements BaseView {

	private Integer pkey;//产品主键
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String name;//产品名称
	private String image;//产品图片
}
