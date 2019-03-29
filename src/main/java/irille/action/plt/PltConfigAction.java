package irille.action.plt;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import irille.action.ActionBase;
import irille.action.MgtAction;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfigDAO;
import irille.view.plt.ConfigView;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;

public class PltConfigAction extends MgtAction<PltConfig> {

  private static final long serialVersionUID = -4992866090170327654L;

  @Override
  public Class<PltConfig> beanClazz() {
    return PltConfig.class;
  }

  public PltConfig getBean() {
    return _bean;
  }

  public void setBean(PltConfig bean) {
    this._bean = bean;
  }

  public void enabledLanguage() throws IOException, JSONException {
    JSONArray array = new JSONArray(PltConfigDAO.listLanguageView(), false);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.getWriter().print(array.toString());
  }

  /**
   * 获取平台端使用语言
   *
   * @throws IOException
   * @author yingjianhua
   */
  public void manageLanguage() throws IOException {
    if (session.get(ActionBase.LOGIN) != null) {
      ConfigView view = new ConfigView();
      view.setManageLanguage(PltConfigDAO.manageLanguage().name());
      write(view);
    } else {
      writeTimeout();
    }
  }
}
