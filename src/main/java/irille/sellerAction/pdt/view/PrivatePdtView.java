package irille.sellerAction.pdt.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PrivatePdtView implements BaseView {
  private Integer pkey;
  private String name;
  private String images;
}
