package irille.view.O2O;

import irille.Entity.O2O.O2O_Map;
import irille.pub.tb.FldLanguage;
import irille.view.BaseView;
import lombok.Data;
import org.json.JSONException;

@Data
public class O2OMapView implements BaseView {
  private Integer id;
  private String name;
  private String longitude;
  private String latitude;

  public static O2OMapView toView(FldLanguage.Language language, O2O_Map map) {
    O2OMapView view = new O2OMapView();
    try {
      view.setName(map.getName(language));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    view.setId(map.getPkey());
    view.setLatitude(map.getLatitude());
    view.setLongitude(map.getLongitude());
    return view;
  }
}
