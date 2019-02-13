package irille.platform.odr.view.OdrOrder;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class ConditionView implements BaseView {
    private List<StatusView> statusList;
    private List<TypeView> typeList;
}
