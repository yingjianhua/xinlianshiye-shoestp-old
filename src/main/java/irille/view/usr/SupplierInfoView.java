package irille.view.usr;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import irille.view.v3.svs.SvsRatingAndRosDTO;
import lombok.Data;
/**
 * 手机端商家信息
 *
 * @author GS
 */
@Data
public class SupplierInfoView implements BaseView {

  private Integer id;

  private String logo;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String supplierName; // 供应商名称

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String companyName; // 公司名称

  private BackgroundInfView backgroundInf; // 预留的店招背景字段

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime; // 开店时间

  private SvsRatingAndRosDTO svs;
}
