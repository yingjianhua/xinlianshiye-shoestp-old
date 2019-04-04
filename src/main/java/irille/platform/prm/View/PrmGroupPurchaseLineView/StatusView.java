package irille.platform.prm.View.PrmGroupPurchaseLineView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class StatusView implements BaseView {
  private Byte id;
  private String status;
}
