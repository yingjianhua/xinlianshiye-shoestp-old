package irille.platform.odr.view.OdrOrder;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class ConditionView implements BaseView {
  private List<StatusView> statusList;
  private List<TypeView> typeList;
}
