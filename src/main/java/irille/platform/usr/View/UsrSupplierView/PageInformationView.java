package irille.platform.usr.View.UsrSupplierView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PageInformationView implements BaseView {
  private String signBackgd; // 店招背景
  private String adPhotoLink; // 广告连接
  private Integer country; // 国家管理
  private Integer province; // 省份
  private Byte isPro; // 供应商首页产品展示
  private String webSite; // 网址
  private String companyPhotoLink; // 企业图片连接
  private String businessTyp; // 业务类型//BUSINESS TYP
  private String location; // 地址//LOCATION
  private String developer; // 开发者//DEVELOPER
  private String production; // 生产//PRODUCTION
  private String totalEmployees; // 员工总数//TOTAL EMPLOYEES
  private String annualSales; // 年度销售额//ANNUAL SALES
  private String topMarkets; // 前三大市场//TOP 3 MARKETS
  private String materials; // 材料//MATERIALS
  private String department; // 部门//DEPARTMENT
  private String jobTitle; // 职称//JOB TITLE
  private String city; // 城市//CITY
  private String adPhoto; // 广告图
  private String adPhotoMobile; // 移动端广告图
  private String companyPhoto; // 企业图片
  private String headPic; // 头像
  private String logo; // logo
}
