package irille.sellerAction.usr.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import org.json.JSONException;

@Controller(name = "专属询盘", module = "询盘")
public interface IUsrConsultRelationAction extends ISellerAction {

  /**
   * 公共询盘列表(分页) 根据国家和标题查询
   *
   * @throws IOException
   * @throws JSONException
   * @author yingjianhua
   */
  @RequestMapping(alias = "列表", sort = 1)
  public void page() throws IOException, JSONException;
}
