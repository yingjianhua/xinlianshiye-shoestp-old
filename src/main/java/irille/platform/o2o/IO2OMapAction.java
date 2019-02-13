package irille.platform.o2o;

import org.json.JSONException;

import java.io.IOException;

public interface IO2OMapAction {

    void list() throws IOException;

    void ins() throws IOException, JSONException;

    void del() throws IOException, JSONException;

    void load() throws IOException;
}
