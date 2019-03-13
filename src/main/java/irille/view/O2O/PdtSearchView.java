package irille.view.O2O;

import lombok.Data;

@Data
public class PdtSearchView {
  private Integer actId; // 活动pkey
  private String activity; // 活动名称
  private String category; // 商品分类名称
  private String supplier; // 供应商名称
  private String role; // 供应商等级名称
  private String area; // 活动地区
  private Byte status; // 活动状态
  private Byte state; // 上下架状态
}
