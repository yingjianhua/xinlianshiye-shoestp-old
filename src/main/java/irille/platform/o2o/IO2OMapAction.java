package irille.platform.o2o;

import java.io.IOException;

import org.json.JSONException;

public interface IO2OMapAction {

  void list() throws IOException;

  void ins() throws IOException, JSONException;

  void del() throws IOException, JSONException;

  void load() throws IOException;
}
