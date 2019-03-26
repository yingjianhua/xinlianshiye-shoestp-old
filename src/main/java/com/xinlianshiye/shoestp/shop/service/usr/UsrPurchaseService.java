package com.xinlianshiye.shoestp.shop.service.usr;

import java.util.regex.Pattern;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.usr.impl.UsrPurchaseServiceImpl;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.pub.DateTools;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.validate.Regular;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrPurchaseServiceImpl.class)
public interface UsrPurchaseService {

  /**
   * 编辑我的头像
   *
   * @param purchase 供应商
   * @param avatar 头像地址
   * @author Jianhua Ying
   */
  void editAvatar(UsrPurchase purchase, String avatar, Language language);

  /**
   * 获取个人信息
   *
   * @param purchase 供应商
   * @return 包含头像, 名称, 几个询盘有新消息, 几个新联系人
   * @author Jianhua Ying
   */
  PurchaseView getProfile(UsrPurchase purchase);

  /**
   * 修改邮箱
   *
   * @param purchase 采购商
   * @param email 邮箱地址
   * @param password 密码
   * @author Jianhua Ying
   */
  void changeEmail(UsrPurchase purchase, String email, String password, Language language);

  /**
   * 修改密码
   *
   * @param purchase 采购商
   * @param newPassword 新密码
   * @param password 原密码
   * @author Jianhua Ying
   */
  void changePassword(UsrPurchase purchase, String newPassword, String password);

  /**
   * 编辑账号信息
   *
   * @param purchase 采购商
   * @param accountSetting 账户设置内容
   * @author Jianhua Ying
   */
  void editAccount(UsrPurchase purchase, PurchaseView accountSetting);

  /**
   * 获取账号信息
   *
   * @param purchase
   * @return
   */
  PurchaseView getAccount(UsrPurchase purchase);

  public static final Pattern password_regex = Pattern.compile("^[^\\s]{6,20}$");
  /**
   * 校验密码格式的有效性
   * <li>长度 目前至少8位
   * <li>其它要求暂无
   *
   * @param password 密码
   * @author Jianhua Ying
   */
  default void validPassword(String password) {
    if (password == null || !password_regex.matcher(password).matches()) {
      throw new WebMessageException(ReturnCode.valid_pwdRegex, "密码格式不正确");
    }
  }

  public static final Pattern mobile_regex = Pattern.compile("^1\\d{10}$");
  public static final Pattern mobile_regex2 = Pattern.compile("^[+\\d]?\\d{1,3}-\\d{6,16}$");
  /**
   * 校验手机号码格式的有效性
   *
   * @param mobile
   */
  default void validMobile(String mobile) {
    if (mobile == null
        || (!mobile_regex.matcher(mobile).matches() && !mobile_regex2.matcher(mobile).matches())) {
      throw new WebMessageException(ReturnCode.valid_phoneRegex, "手机格式不正确");
    }
  }

  public static final Pattern email_regex = Pattern.compile(Regular.REGULAR_EMAIL);

  /**
   * 校验邮箱地址格式的有效性
   *
   * @param email
   */
  default void validEmail(String email) {
    if (email == null || !email_regex.matcher(email).matches()) {
      throw new WebMessageException(ReturnCode.valid_mailRegex, "邮箱格式不正确");
    }
  }

  /**
   * 检查密码是否正确
   *
   * @param purchase 采购商
   * @param password 密码
   * @author Jianhua Ying
   */
  default void checkPassword(UsrMain usrMain, String password) {
    if (!usrMain.getPassword().equals(DateTools.getDigest(usrMain.getPkey() + password))) {
      throw new WebMessageException(ReturnCode.service_unknow, "密码不正确");
    }
  }
}
