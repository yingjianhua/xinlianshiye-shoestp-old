package com.xinlianshiye.shoestp.shop.service.usr.impl;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.common.dao.usr.UsrMainDao;
import com.xinlianshiye.shoestp.common.dao.usr.UsrPurchaseDao;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.shop.service.usr.UsrPurchaseService;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView.EditAccountValidator;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.pub.DateTools;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseServiceImpl implements UsrPurchaseService {

  @Inject private UsrMainDao usrMainDao;
  @Inject private UsrPurchaseDao usrPurchaseDao;
  @Inject private RFQConsultMessageDao rFQConsultMessageDao;
  @Inject private RFQConsultRelationDao rFQConsultRelationDao;

  @Override
  public void editAvatar(UsrPurchase purchase, String avatar, Language language) {
    if (avatar == null || avatar.isEmpty()) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.reselect_upload_headpic, language));
    }
    purchase.setIcon(avatar);
    usrPurchaseDao.save(purchase);
  }

  @Override
  public PurchaseView getProfile(UsrPurchase purchase) {
    PurchaseView view = new PurchaseView();
    view.setAvatar(purchase.getIcon());
    view.setNickname(purchase.getName());
    view.setRequestsFromConnectionsCount(
        rFQConsultRelationDao.countNewByPurchaseGroupBySupplier(purchase.getPkey()));
    view.setUnreadMessagersCount(
        rFQConsultMessageDao.countPurchaseUnreadByRelation_PurchaseGroupByRelation(
            purchase.getPkey()));
    return view;
  }

  @Override
  public void changeEmail(UsrPurchase purchase, String email, String password, Language language) {
    UsrMain main = purchase.gtUserid();
    // 检查密码
    checkPassword(main, password);
    // 校验邮箱地址格式的有效性
    validEmail(email);
    // 忽略大小写
    email = email.toLowerCase();
    if (usrPurchaseDao.findByLoginNameOrEmail(email).isPresent()) {
      // 用户名或邮箱地址已被使用
      throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.mail_exists, language));
    }
    if (usrMainDao.findByEmail(email).isPresent()) {
      // 用户名或邮箱地址已被使用
      throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.mail_exists, language));
    }
    purchase.setEmail(email.toLowerCase());
    purchase.setLoginName(email.toLowerCase());
    main.setEmail(email);
    main.upd();
    usrPurchaseDao.save(purchase);
  }

  @Override
  public void changePassword(UsrPurchase purchase, String newPassword, String password) {
    UsrMain main = purchase.gtUserid();
    // 检查密码
    checkPassword(main, password);
    // 校验新密码格式的有效性
    validPassword(newPassword);
    purchase.setPassword(DateTools.getDigest(purchase.getPkey() + newPassword));
    usrPurchaseDao.save(purchase);
    main.setPassword(DateTools.getDigest(main.getPkey() + newPassword));
    usrMainDao.save(main);
  }

  @Override
  public void editAccount(UsrPurchase purchase, PurchaseView accountSetting) {
    // 校验数据
    accountSetting.validThrow(EditAccountValidator.class);
    purchase.setSex(accountSetting.getGender());
    purchase.setName(accountSetting.getFirstName());
    purchase.setSurname(accountSetting.getSurname());
    // 校验手机号码格式的有效性
    if (accountSetting.getPhone() != null) validMobile(accountSetting.getPhone());
    purchase.setTelphone(accountSetting.getPhone());
    purchase.setCompany(accountSetting.getCompany());
    purchase.setAddress(accountSetting.getAddress());
    UsrMain main = BeanBase.load(UsrMain.class, purchase.getUserid());
    main.setNickname(accountSetting.getSurname() + "," + accountSetting.getFirstName());
    main.setTelphone(accountSetting.getPhone());
    main.setCompany(accountSetting.getCompany());
    main.upd();
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
