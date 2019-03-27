package irille.view.usr;

import irille.view.BaseView;
import lombok.Data;
/**
 * 3.1.1 供应商列表显示产品列表信息
 *
 * @author GS
 */
@Data
public class UsrSupplierPdtView implements BaseView {
  private Integer pdtId; // 产品ID

  private String pdtName; // 产品名称

  private String pdtPictures; // 产品图片
}
