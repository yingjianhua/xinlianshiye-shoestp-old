package irille.sellerAction.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class timeZone implements BaseView {
    private String id;//时区ID
    private String name; // 时区名称
}
