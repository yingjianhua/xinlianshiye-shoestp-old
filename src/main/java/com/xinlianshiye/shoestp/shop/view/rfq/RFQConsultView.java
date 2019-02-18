package com.xinlianshiye.shoestp.shop.view.rfq;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultView implements BaseView {
	private Integer pkey;
	private String title;
	private Byte type;
	private List<String> images;
	private List<RFQConsultRelationView> relations;
}
