package irille.Service.Usr;

import com.google.inject.ImplementedBy;

import irille.Service.Usr.Imp.UsrPurchaseServiceImpl;
import irille.homeAction.usr.dto.PurchaseView;
import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrPurchaseServiceImpl.class)
public interface UsrPurchaseService {

	/**
	 * 编辑我的头像
	 * @param purchase 供应商
	 * @param avatar 头像地址
	 * @author Jianhua Ying
	 */
	void editAvatar(UsrPurchase purchase, String avatar);
	
	/**
	 * 获取个人信息
	 * @param purchase 供应商
	 * @return 包含头像, 名称, 几个询盘有新消息, 几个新联系人
	 * @author Jianhua Ying
	 */
	PurchaseView getProfile(UsrPurchase purchase);
}
