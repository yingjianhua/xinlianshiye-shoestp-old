package com.xinlianshiye.shoestp.shop.view.usr;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class SupplierView implements BaseView {

  private Integer pkey;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name; // 供应商名称(showName)

  private String logo; // logo(头像)
  private String country; // 国家
  private String countryFlag; // 国家国旗
  private Date yearEstablished; // 公司成立年份
  private String mainProducts; // 主要产品
  private String location; // 地址
  private String employeeCount; // 员工数量
  private String annualRevenue; // 年收入
  private String transactionCount; // 业务量
  private String transactionAmount; // 总业务量
  private Date memberSince; // 注册时间
  private String department; // 部门
  private String jobTitle; //

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String company; // 公司名字

  private String businessType; // 企业类型
  private String mainMarket; // 主要市场
  private String contractManufacturing;
  private String overseasOffice;
  private String companyCertification;
  private String daysVisited;
  private String listedAsAContact;
}
