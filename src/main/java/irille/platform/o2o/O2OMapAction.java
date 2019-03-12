package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.O2O.O2O_Map;
import irille.Service.Manage.O2O.IO2OMapServer;
import irille.action.ActionBase;
import irille.view.O2O.O2OMapView;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

public class O2OMapAction extends ActionBase<O2O_Map> implements IO2OMapAction {

  @Inject private IO2OMapServer io2OMapServer;
  @Setter @Getter private O2OMapView view;

  @Setter private Integer id;

  @Setter private String address;

  @Override
  public Class beanClazz() {
    return O2O_Map.class;
  }

  public void list() throws IOException {
    write(io2OMapServer.list());
  }

  public void ins() throws IOException, JSONException {
    write(io2OMapServer.ins(view));
  }

  public void del() throws IOException, JSONException {
    io2OMapServer.del(id);
    writeSuccess();
  }

  public void load() throws IOException {
    ServletActionContext.getResponse().getWriter().print(io2OMapServer.load(address));
  }
}
