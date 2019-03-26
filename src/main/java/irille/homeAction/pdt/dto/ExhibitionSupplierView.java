package irille.homeAction.pdt.dto;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * 展会供应商信息
 *
 * @author GS
 */
@Getter
@Setter
public class ExhibitionSupplierView implements BaseView {

  private Integer id;

  private String name;

  private Byte isCertificate; // 是否认证

  private String region; // 所属地区

  private Integer responseRate; // 响应率

  private String grade; // 供应商等级

  private Integer isO2O; // 是否是O2O
}
