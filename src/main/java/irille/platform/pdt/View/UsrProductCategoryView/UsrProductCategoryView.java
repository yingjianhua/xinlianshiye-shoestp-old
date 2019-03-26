package irille.platform.pdt.View.UsrProductCategoryView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrProductCategoryView implements BaseView {
  private Integer id;
  private String name; // 标题
  private String supplier; // 供应商
  private String category; // 所属分类
  private Integer enabled; // 是否启用
  private String seoTitle; // 标题
  private String seoKeyword; // 关键
  private String seoDescription; // 简述
  private Integer categoryId; // 所属分类ID
  private Integer supplierId; // 供应商id
}
