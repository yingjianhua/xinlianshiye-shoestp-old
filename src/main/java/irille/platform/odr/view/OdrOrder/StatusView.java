package irille.platform.odr.view.OdrOrder;

import irille.view.BaseView;
import lombok.Data;

@Data
public class StatusView implements BaseView {
  private Byte id;
  private String value;
}
