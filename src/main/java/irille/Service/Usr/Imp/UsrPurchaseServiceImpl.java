package irille.Service.Usr.Imp;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Dao.Usr.UsrPurchaseDao;
import irille.Service.Usr.UsrPurchaseService;
import irille.homeAction.usr.dto.PurchaseView;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseServiceImpl implements UsrPurchaseService {
	
	@Inject
	private UsrPurchaseDao usrPurchaseDao;
	@Inject
	private RFQConsultMessageDao rFQConsultMessageDao;
	@Inject
	private RFQConsultRelationDao rFQConsultRelationDao;

	@Override
	public void editAvatar(UsrPurchase purchase, String avatar) {
		if(avatar == null || avatar.isEmpty()) {
			throw new WebMessageException(ReturnCode.valid_notblank, "请重新上传头像");
		}
		purchase.setIcon(avatar);
		usrPurchaseDao.save(purchase);
	}

	@Override
	public PurchaseView getProfile(UsrPurchase purchase) {
		PurchaseView view = new PurchaseView();
		view.setAvatar(purchase.getIcon());
		view.setNickname(purchase.getName());
		view.setRequestsFromConnectionsCount(rFQConsultRelationDao.countNewByPurchaseGroupBySupplier(purchase.getPkey()));
		view.setUnreadMessagersCount(rFQConsultMessageDao.countUnreadByRelation_PurchaseGroupByRelation(purchase.getPkey()));
		return view;
	}

}
