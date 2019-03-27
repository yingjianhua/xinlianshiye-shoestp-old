/** */
package com.xinlianshiye.shoestp.shop.view.pdt;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/** @author liyichao */
@Data
public class SortView {

  public static final Map<Integer, String> sortMap;

  static {
    Map<Integer, String> map = new HashMap<>();
    map.put(0, "ASC"); // 正序
    map.put(1, "DESC"); // 倒序
    sortMap = new HashMap<>(map);
  }

  private String name;
  private int sort;
  private Integer rule;
}
