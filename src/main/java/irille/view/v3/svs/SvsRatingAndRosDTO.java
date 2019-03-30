package irille.view.v3.svs;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SvsRatingAndRosDTO implements BaseView {
  private Integer pkey; // SVSpkey
  private Integer supplierId;//供应商pkey
  private Byte grade; // SVS认证等级
  private Byte status; // SVS认证状态
  private Integer baseScore;//SVS基础分
  private Integer researchBase; // R分数
  private Integer factoryBase; // O分数
  private Integer capacityBase; // S分数
  private Double researchBaseStar; // R星级
  private Double factoryBaseStar; // O星级
  private Double capacityBaseStar; // S星级
}
