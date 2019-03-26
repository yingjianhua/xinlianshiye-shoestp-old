package irille.sellerAction.plt.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import org.json.JSONException;

@Controller(module = "用户管理", name = "基础配置")
public interface IPltConfigAction extends ISellerAction {

  @RequestMapping(alias = "语言列表", sort = 10)
  public void enabledLanguage() throws IOException, JSONException;
}
