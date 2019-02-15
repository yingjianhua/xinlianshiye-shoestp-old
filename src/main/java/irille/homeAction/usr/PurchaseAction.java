package irille.homeAction.usr;

import java.io.IOException;

import com.google.inject.Inject;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Service.Usr.UsrFavoriteService;
import irille.Service.Usr.UsrPurchaseService;
import irille.homeAction.AbstractHomeAction;
import irille.pub.util.upload.ImageUpload;
import irille.shop.usr.UsrPurchase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseAction extends AbstractHomeAction implements IPurchaseAction {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsrPurchaseService usrPurchaseService;
	@Inject
	private UsrFavoriteService usrFavoriteService;

	@Override
	@NeedLogin
	public void profile() throws IOException {
		write(usrPurchaseService.getProfile(getPurchase()));
	}
	
	private String avatar;

	@Override
	@NeedLogin
	public void editAvatar() throws IOException {
		usrPurchaseService.editAvatar(getPurchase(), avatar);
		write();
	}

	@Override
	@NeedLogin
	public void upload() throws IOException {
		write(ImageUpload.upload(UsrPurchase.class, getFileFileName(), getFile()));
	}
	
    private Integer category;

	@Override
	@NeedLogin
	public void favorite() throws IOException {
		write(usrFavoriteService.page(getPurchase(), category, true,  start,  limit));
	}

}
