package irille.view.O2O;

import irille.view.BaseView;
import lombok.Data;

@Data
public class O2OMapView implements BaseView {
    private Integer id;
    private String name;
    private String longitude;
    private String latitude;
}
