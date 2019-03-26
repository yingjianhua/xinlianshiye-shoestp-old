package irille.action.usr;

import javax.servlet.http.HttpServletResponse;

import irille.action.MgtAction;
import irille.shop.usr.UsrMessages;
import irille.shop.usr.UsrMessagesDAO;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class UsrMessagesAction extends MgtAction<UsrMessages> {

  public UsrMessages getBean() {
    return _bean;
  }

  public void setBean(UsrMessages bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return UsrMessages.class;
  }

  private int _userId;

  public int getUserId() {
    return _userId;
  }

  public void setUserId(int userId) {
    this._userId = userId;
  }

  public void getName() throws Exception {
    UsrMessagesDAO.select usrSelect = new UsrMessagesDAO.select();
    ServletActionContext.getResponse().getWriter().write(usrSelect.getName(_userId));
  }

  public void inssys() throws Exception {
    UsrMessagesDAO.Sys_Ins sysIns = new UsrMessagesDAO.Sys_Ins();
    sysIns.setB(this._bean);
    sysIns.commit();
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = this.crtJsonByBean(sysIns.getB(), "bean.");
    json.put("success", true);
    response.getWriter().print(json.toString());
  }
}
