package com.xinlianshiye.shoestp.shop.view.usr;

import java.util.Date;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SupplierView implements BaseView {
 
	private Integer pkey;
	private String country;
	private Date yearEstablished;//公司成立年份
	private String mainProducts;//主要产品
	private String location;//地址
	private String employeeCount;//员工数量
	private String annualRevenue;//年收入
	private String transactionCount;//业务量
	private Date memberSince;
	private String Department;
	private String jobTitle;
	private String company;
	private String businessType;
	private String mainMarket;
	private String contractManufacturing;
	private String overseasOffice;
	private String companyCertification;
	private String daysVisited;
	private String listedAsAContact;
	
}
