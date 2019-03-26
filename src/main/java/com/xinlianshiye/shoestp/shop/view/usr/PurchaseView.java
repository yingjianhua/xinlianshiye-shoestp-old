package com.xinlianshiye.shoestp.shop.view.usr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import irille.shop.usr.UsrPurchase;
import irille.view.BaseView;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PurchaseView implements BaseView {
  private String nickname;
  private String avatar;
  private Integer unreadMessagersCount; // 未读取发信人数量
  private Integer requestsFromConnectionsCount; // 新联系人数量
  private String loginName; // 登录名
  private Integer favoriteCount; // 收藏数
  private Integer cartCount; // 购物车数
  private Integer inquiryCount; // 询盘数
  private String email;

  @NotNull(message = "请填写性别", groups = EditAccountValidator.class)
  @Range(max = 2, min = 0, message = "请填写性别", groups = EditAccountValidator.class)
  private Byte gender;

  @NotBlank(message = "请填写名称", groups = EditAccountValidator.class)
  private String firstName;

  @NotBlank(message = "姓不能为空", groups = EditAccountValidator.class)
  private String surname;

  @NotBlank(message = "请填写手机号码", groups = EditAccountValidator.class)
  private String phone;

  @NotBlank(message = "请填写公司名称", groups = EditAccountValidator.class)
  private String company;

  private String address;

  /**
   * 校验分组
   *
   * @author Jianhua Ying
   * @see com.xinlianshiye.shoestp.shop.service.usr.UsrPurchaseService#editAccount(UsrPurchase
   *     purchase, PurchaseAccountSettingView accountSetting)
   */
  public interface EditAccountValidator {}

  public static PurchaseView build(
      UsrPurchase purchase, Integer favoriteCount, Integer cartCount, Integer inquiryCount) {
    PurchaseView view = new PurchaseView();
    view.setLoginName(purchase.getLoginName());
    view.setFavoriteCount(favoriteCount);
    view.setCartCount(cartCount);
    view.setInquiryCount(inquiryCount);
    return view;
  }
}
