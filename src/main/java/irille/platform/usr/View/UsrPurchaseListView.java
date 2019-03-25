package irille.platform.usr.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrPurchaseListView implements BaseView {
  private Integer id;
  private String name; // 名字
  private String surname; // 姓氏
  private Integer sex; // 性别
  private String usrmemberlevel; // 用户等级
  private String icon; // 头像
  private String email; // 邮箱
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date regTime; // 注册时间
  private String loginName; // 登录账号
  private String currency; // 货币类型
  private String regIp; // 注册IP
  private String telphone; // 手机号码
  private String company; // 公司名称
  private String address; // 地址
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date lastLoginTime; // 最后登录时间
  private String country; // 国家
  private String facebookuserid; // FACEBOOK用户ID
  private String googleuserid; // 谷歌用户ID
}
