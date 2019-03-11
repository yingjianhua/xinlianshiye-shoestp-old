package com.xinlianshiye.shoestp.shop.view.rfq;

import java.util.Date;
import java.util.List;

import com.xinlianshiye.shoestp.shop.view.rfq.supplierConsult.RFQConsultProductView;

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
	private Date valieDate;//有效时间
	private String paymentTerms;//支付条约
	private String shippingTerms;//运送条款
	private String extraRequest;//额外请求
	private List<RFQConsultProductView> productRequest;//感兴趣产品列表
	private List<String> images;//询盘图片
	private List<RFQConsultRelationView> relations;//询盘关联
	private Byte verifyStatus;//审核状态 	1: 未审核, 2: 未通过, 3: 通过
	private Byte status;//询盘状态 1: 待发布, 2: 进行中,3: 已完成, 4: 已关闭
}
