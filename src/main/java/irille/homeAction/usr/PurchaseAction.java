package irille.homeAction.usr;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.usr.UsrFavoriteService;
import com.xinlianshiye.shoestp.shop.service.usr.UsrPurchaseService;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin.UserType;
import irille.homeAction.AbstractHomeAction;
import irille.pub.util.upload.ImageUpload;
import irille.shop.usr.UsrPurchase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseAction extends AbstractHomeAction implements IPurchaseAction {

  private static final long serialVersionUID = 1L;

  @Inject private UsrPurchaseService usrPurchaseService;
  @Inject private UsrFavoriteService usrFavoriteService;
  @Inject private ObjectMapper om;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void profile() throws IOException {
    write(usrPurchaseService.getProfile(getPurchase()));
  }

  private String avatar;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void editAvatar() throws IOException {
    usrPurchaseService.editAvatar(getPurchase(), avatar, curLanguage());
    write();
  }

  @Override
  @NeedLogin
  public void upload() throws IOException {
    write(ImageUpload.upload(UsrPurchase.class, getFileFileName(), getFile()));
  }

  private Integer category;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void favorite() throws IOException {
    write(usrFavoriteService.page(getPurchase(), category, true, start, limit));
  }

  private String password;
  private String email;
  private String newPassword;

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void changePassword() throws IOException {
    usrPurchaseService.changePassword(getPurchase(), newPassword, password);
    write();
  }

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void changeEmail() throws IOException {
    usrPurchaseService.changeEmail(getPurchase(), email, password, curLanguage());
    write();
  }

  @Override
  @NeedLogin(userType = UserType.PURCHASE)
  public void editAccount() throws IOException {
    usrPurchaseService.editAccount(getPurchase(), om.readValue(getJsonBody(), PurchaseView.class));
    write();
  }

  @NeedLogin(userType = UserType.PURCHASE)
  public void accountProfile() throws IOException {
    write(usrPurchaseService.getAccount(getPurchase()));
  }
}
