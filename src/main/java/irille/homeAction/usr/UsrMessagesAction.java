package irille.homeAction.usr;

import java.io.IOException;

import irille.Filter.svr.ItpCheckPurchaseLogin;
import irille.homeAction.HomeAction;
import irille.pub.idu.IduPage;
import irille.shop.usr.UsrMessages;
import irille.shop.usr.UsrMessagesDAO;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/19 Time: 10:29 */
public class UsrMessagesAction extends HomeAction<UsrMessages> {
  private static final UsrMessagesDAO.select select = new UsrMessagesDAO.select();
  private static final UsrMessagesDAO.Ins ins = new UsrMessagesDAO.Ins();
  private static final UsrMessagesDAO.Upd upd = new UsrMessagesDAO.Upd();
  private String message;
  private String title;
  private int type;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public void sysMessageList() throws Exception {
    IduPage page = this.newPage();
    page.setLimit(8);
    page.setStart(getPage());
    write(select.sysMessageList(page));
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public void getMyInbox() {
    IduPage page = new IduPage();
    page.setStart(getPage());
    page.setLimit(8);
    try {
      write(select.getMyInboxList(page, getPurchase().getPkey(), type));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public void writeMessage() {
    UsrMessages usrMessage = new UsrMessages();
    usrMessage.setSenduser(getPurchase().getPkey());
    usrMessage.setContent(message);
    usrMessage.setTitle(title);
    usrMessage.stType(UsrMessages.OMessageType.USER_MESSAGE);
    usrMessage.setReciver(-1);
    ins.setB(usrMessage);
    ins.commit();
  }

  public void replyMessage() {
    UsrMessages usrMessages = UsrMessages.get(UsrMessages.class, (Integer) getId());
    usrMessages.setPkey((Integer) getId());
    usrMessages.setReply(getMessage());
    //        usrMessages.setReciver(-1);
    upd.setB(usrMessages);
    upd.commit();
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public String inbox() {
    setResult("/home/my-Inbox.jsp");
    return HomeAction.TRENDS;
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public String center() {
    setResult("/home/user-msgCenter.jsp");
    return HomeAction.TRENDS;
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public String systemMessage() {
    setResult("/home/systemMessage.jsp");
    return HomeAction.TRENDS;
  }

  @ItpCheckPurchaseLogin.NeedLogin
  public void viewMessage() {
    try {
      write(select.getMessageById((Integer) getId()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
