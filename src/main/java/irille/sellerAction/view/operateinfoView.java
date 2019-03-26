package irille.sellerAction.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class operateinfoView implements BaseView {
  private String curlang; // 当前语言 /默认语言
  private String websizetitle; // 自定义链接名称
  private String production; // 生产能力
  private String totalProduction; // 生产总数
  private String numberEmployees; // 员工总数
  private String annualSalesFigure; // 年销售量
  private String TOP3MARKETS; // 前3市场
  private String website; // 网站
  private String country; // 当前国家
  private String province; // 当前省份
  private String city; // 所在城市
  private String headpic; // 头像
  private String contacts; // 联系人
  private String phone; // 手机
  private String department; // 部门
  private String jobTitle; // 职称
  private String settlementbank; // 结算开户行
  private String bankaccount; // 银行账号
  private String bankbranch; // 银行开户行
  private String bankcountry; // 开户行所在国家
  private String bankprovince;
  private String operateidcard;
  private String contactsidcardfront; // 正反照片
  private String contactsidcardback;
}
