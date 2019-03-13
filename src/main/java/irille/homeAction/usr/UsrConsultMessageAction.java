package irille.homeAction.usr;

import java.io.IOException;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.inf.IUsrConsultMessageAction;
import irille.shop.usr.UsrConsultMessage;
import irille.shop.usr.UsrConsultMessageDAO;
import org.json.JSONException;

public class UsrConsultMessageAction extends HomeAction<UsrConsultMessage>
    implements IUsrConsultMessageAction {

  private static final long serialVersionUID = -3937970656901492904L;

  private int relation;
  private String content;

  /**
   * 询盘留言页面(需要登录)
   *
   * @author yingjianhua
   */
  @Override
  @NeedLogin
  public String dialogView() {
    setResult("/home/my-inquiry-dialog.jsp");
    return TRENDS;
  }

  /**
   * 获取该询盘每个供应商的所有留言记录
   *
   * @author yingjianhua
   */
  @Override
  @NeedLogin
  public void list() throws IOException, JSONException {
    write(UsrConsultMessageDAO.listViewByConsult((Integer) getId()));
  }

  /**
   * 发送消息给供应商
   *
   * @throws JSONException
   * @throws IOException
   * @author yingjianhua
   */
  @NeedLogin
  public void send2Supplier() throws IOException, JSONException {
    new UsrConsultMessageDAO.Send(true, relation, getPurchase().getPkey(), content).commit();
    write();
  }

  public int getRelation() {
    return relation;
  }

  public void setRelation(int relation) {
    this.relation = relation;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
