package com.xinlianshiye.shoestp.shop.service.usr;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.usr.impl.UsrPurchaseServiceImpl;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseAccountSettingView;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.pub.DateTools;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
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
	
	/**
	 * 修改邮箱
	 * @param purchase 采购商
	 * @param email 邮箱地址
	 * @param password 密码
	 * @author Jianhua Ying
	 */
	void changeEmail(UsrPurchase purchase, String email, String password);
	
	/**
	 * 修改密码
	 * @param purchase 采购商
	 * @param newPassword 新密码
	 * @param password 原密码
	 * @author Jianhua Ying
	 */
	void changePassword(UsrPurchase purchase, String newPassword, String password);
	
	/**
	 * 编辑账号信息
	 * @param purchase 采购商
	 * @param accountSetting 账户设置内容
	 * @author Jianhua Ying
	 */
	void editAccount(UsrPurchase purchase, PurchaseAccountSettingView accountSetting);
	
	/**
	 * 校验密码格式的有效性
	 * <li>长度 目前至少8位
	 * <li>其它要求暂无
	 * @param password 密码
	 * @author Jianhua Ying
	 */
	default void validPassword(String password) {
		if(password == null || password.length() < 8) {
			throw new WebMessageException(ReturnCode.valid_tooShort, "密码过短");
		}
	}
	
	/**
	 * 检查密码是否正确
	 * @param purchase 采购商
	 * @param password 密码
	 * @author Jianhua Ying
	 */
	default void checkPassword(UsrPurchase purchase, String password) {
		if(!purchase.getPassword().equals(DateTools.getDigest(purchase.getPkey()+password))) {
			throw new WebMessageException(ReturnCode.service_unknow, "密码不正确");
		}
	}
	
}
