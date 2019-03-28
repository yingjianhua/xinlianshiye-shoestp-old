/** */
package com.xinlianshiye.shoestp.shop.view.pdt;

import java.math.BigDecimal;

import lombok.Data;

/** @author liyichao */
@Data
public class ProdSearchView {
  private Integer supplier; // 供应商id
  private Byte grade; // 供应商SVS等级
  private Integer category; // 产品分类
  private BigDecimal minCurPrice; // 最低价
  private BigDecimal maxCurPrice; // 最高价
  private Integer minOq; // 最小起订量
  private Byte isO2o; // 是否为O2O商品
  private String o2oAddress; // O2O地址
  private String keywords; // 产品关键字(产品名称/编号/出厂编码/供应商名称)
  private String export; // 产品主要销售区域

  // 排序字段
  private String sort; // json
}
