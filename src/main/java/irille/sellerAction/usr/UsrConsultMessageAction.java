package irille.sellerAction.usr;

import java.io.IOException;

import org.json.JSONException;

import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrConsultMessageAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrConsultMessage;
import irille.shop.usr.UsrConsultMessageDAO;

public class UsrConsultMessageAction extends SellerAction<UsrConsultMessage>
    implements IUsrConsultMessageAction {

  private static final long serialVersionUID = -4772801444573443082L;

  private Integer relation;
  private String content;

  /**
   * 发送消息给供应商
   *
   * @throws JSONException
   * @throws IOException
   * @author yingjianhua
   */
  @Override
  public void send2Purchase() throws IOException, JSONException {
    if (getSupplier() == null) {
      writeTimeout();
    } else {
      new UsrConsultMessageDAO.Send(
              false,
              relation,
              getSupplier().getPkey(),
              content,
              PltConfigDAO.supplierLanguage(getSupplier()))
          .commit();
      write();
    }
  }

  public Integer getRelation() {
    return relation;
  }

  public void setRelation(Integer relation) {
    this.relation = relation;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
