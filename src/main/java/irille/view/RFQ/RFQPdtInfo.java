package irille.view.RFQ;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 11:39 */
@Data
public class RFQPdtInfo implements BaseView {
  private int id; // 产品的Id

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String supName;

  private String title;
  private String image;
  private String suplevel;
  private String supLogo;
  private int min_oq;
  private byte type;
}
