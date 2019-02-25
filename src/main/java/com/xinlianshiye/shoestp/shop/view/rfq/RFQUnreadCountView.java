package com.xinlianshiye.shoestp.shop.view.rfq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQUnreadCountView implements BaseView {

	private int all;//所有类型询盘的未读消息
	private int t1;//RFQ询盘的未读消息
	private int t2;//普通询盘的未读消息
	private int t3;//私人展厅询盘的未读消息
	private int t4;//供应商询盘的未读消息;
}
