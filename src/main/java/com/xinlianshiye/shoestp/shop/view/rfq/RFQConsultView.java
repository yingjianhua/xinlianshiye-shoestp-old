package com.xinlianshiye.shoestp.shop.view.rfq;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultView implements BaseView {
	private Integer pkey;//询盘主键
	private String title;//询盘标题(产品名称)
	private String detail;//询盘详细描述
	private Integer quantity;//询盘数量
	private String unit;//数量单位
	private String price;//单位价格
	private Byte type;//询盘类型
	private List<String> images;//询盘图片
	private List<RFQConsultRelationView> relations;//询盘关联
}
