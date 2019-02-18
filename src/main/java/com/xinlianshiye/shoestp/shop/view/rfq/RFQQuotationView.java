package com.xinlianshiye.shoestp.shop.view.rfq;

import java.util.Date;
import java.util.List;

import irille.view.BaseView;
import lombok.Data;
/**
 * 报价信息
 * @author Jianhua Ying
 *
 */
@Data
public class RFQQuotationView implements BaseView {

	private Integer pkey; //主键
	private String title; //标题
	private String description; //描述
	private List<String> images; //图片 多图
	private Integer minPrice; //最低价格
	private Integer maxPrice; //最高价格
	private RFQCurrencyView currency; //货币缩写
	private Boolean sample; //是否有样品
	private Boolean isNew; //是否是新报价
	private Date createDate; //报价时间
}
