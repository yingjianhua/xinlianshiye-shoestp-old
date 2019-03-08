package com.xinlianshiye.shoestp.plat.view.pm;

import java.util.Date;

import irille.view.BaseView;
import lombok.Data;

@Data
public class MessageView implements BaseView{
	private Integer pkey;
	private String msgType;
	private String rcvrType;
	private String title;
	private Date sendTime;
	
	private String content;
	private Integer read;
	private long time;
	private String count;
	
	//搜索相关
	private Date startDate;
	private Date endDate;
	private Integer msgTypeId;
	private Integer rcvrTypeId;
}
