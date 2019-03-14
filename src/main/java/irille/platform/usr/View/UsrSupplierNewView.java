package irille.platform.usr.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrSupplierNewView implements BaseView {
  private Integer pkey;
  // 公司及联系人信息
  private String name; // 公司名称
  private String website; // 公司官网网站地址
  private String englishName; // 公司英文名称
  private String companyAddr; // 公司详细地址
  private String telephone; // 公司电话
  private String postcode; // 邮编
  private String fax; // 传真
  private String mainEmail; // 供应商注册时的邮箱
  private String mainContacts; // 供应商注册时的联系人姓名
  private String mainTelphone; // 供应商注册时的联系人电话
  private Integer mainProvince; // 供应商注册时的省
  private Integer mainCity; // 供应商注册时的市
  private Integer mainZone; // 供应商注册时的区

  // 营业执照副本
  private String companyType; // 公司类型
  private String companyNature; // 企业性质

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date companyEstablishTime; // 企业成立时间

  private String targetedMarket; // 目标市场
  private String prodPattern; // 生产模式
  private String annualProduction; // 年产量
  private String registeredCapital; // 注册资本
  private String entity; // 法定代表人
  private String taxpayerType; // 纳税人类型
  private String creditCode; // 统一社会信用代码
  private Byte businessLicenseIsSecular; // 营业执照是否长期
  private String businessLicenseBeginTime; // 营业执照开始时间
  private String businessLicenseEndTime; // 营业执照到期时间
  // 店铺经营信息
  private String contacts; // 联系人
  private String department; // 联系人部门
  private String jobTitle; // 联系人职称
  private String phone; // 联系人手机
  private String contactEmail; // 联系人邮箱
  // 证件信息
  private String certPhoto; // 企业营业执照副本复印件
  private String certPhotoName; // 企业营业执照副本复印件文件名
  private String idCardFrontPhoto; // 法人代表身份证正反面复印件
  private String idCardFrontPhotoName; // 法人代表身份证正反面复印件文件名
  private String contactsIdCardFrontPhoto; // 运营人员身份证正反面复印件
  private String contactsIdCardFrontPhotoName; // 运营人员身份证正反面复印件文件名

  private Byte status; // 审核状态
  private String reason; // 审核不通过理由

  private Byte storeStatus; // 店铺状态
  private Integer userId; // UsrMain的外键
  private String closeReason; // 店铺关闭原因

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date applicationTime; // 申请时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date storeopenTime; // 店铺入驻时间
}
