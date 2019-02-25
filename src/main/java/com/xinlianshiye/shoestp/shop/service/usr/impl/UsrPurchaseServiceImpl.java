package com.xinlianshiye.shoestp.shop.service.usr.impl;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.common.dao.usr.UsrPurchaseDao;
import com.xinlianshiye.shoestp.shop.service.usr.UsrPurchaseService;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView.EditAccountValidator;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.pub.DateTools;
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

	@Override
	public void changeEmail(UsrPurchase purchase, String email, String password) {
		//检查密码
		checkPassword(purchase, password);
		UsrPurchase purchase2 = usrPurchaseDao.findByLoginNameOrEmail(email);
		if(purchase2 != null) {
			//用户名或邮箱地址已被使用
			throw new WebMessageException(ReturnCode.service_unknow, "邮箱重复,请使用其它邮箱");
		}
		purchase.setEmail(email);
		usrPurchaseDao.save(purchase);
	}

	@Override
	public void changePassword(UsrPurchase purchase, String newPassword, String password) {
		//检查密码
		checkPassword(purchase, password);
		//校验新密码格式的有效性
		validPassword(newPassword);
		purchase.setPassword(DateTools.getDigest(purchase.getPkey()+newPassword));
		usrPurchaseDao.save(purchase);
	}

	@Override
	public void editAccount(UsrPurchase purchase, PurchaseView accountSetting) {
		//校验数据
		accountSetting.validThrow(EditAccountValidator.class);
		purchase.setSex(accountSetting.getGender());
		purchase.setName(accountSetting.getFirstName());
		purchase.setSurname(accountSetting.getSurname());
		purchase.setTelphone(accountSetting.getPhone());
		purchase.setCompany(accountSetting.getCompany());
		purchase.setAddress(accountSetting.getAddress());
		usrPurchaseDao.save(purchase);
	}

	@Override
	public PurchaseView getAccount(UsrPurchase purchase) {
		PurchaseView view = new PurchaseView();
		view.setEmail(purchase.getEmail());
		view.setGender(purchase.getSex());
		view.setAddress(purchase.getAddress());
		view.setCompany(purchase.getCompany());
		view.setPhone(purchase.getTelphone());
		view.setSurname(purchase.getSurname());
		view.setFirstName(purchase.getName());
		return view;
	}

}
