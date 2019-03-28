package irille.view.usr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
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

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String pdtName; // 产品名称

  private String pdtPictures; // 产品图片
  
  private String link;
}
