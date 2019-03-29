package irille.view.usr;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import irille.view.v3.svs.SvsRatingAndRosDTO;
import lombok.Data;
/**
 * 3.1.1 供应商列表显示信息
 *
 * @author GS
 */
@Data
public class UsrSupplierInfView implements BaseView {

  private Integer id; // 供应商ID

  private String logo; // 供应商LOGO

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String storeName; // 店铺名称

  private List<UsrSupplierPdtView> products; // 产品列表信息

  private SvsRatingAndRosDTO svs; // svs信息

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String address;
}
