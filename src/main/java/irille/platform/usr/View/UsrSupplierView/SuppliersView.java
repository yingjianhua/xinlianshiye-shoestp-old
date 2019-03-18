package irille.platform.usr.View.UsrSupplierView;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SuppliersView implements BaseView {
  private Integer id; // pkey
  private Integer svsId; // svsid

  private String name; // 企业名称
  private String companyAddr; // 所在地

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date applicationTime; // 申请时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date storeopenTime; // 店铺入驻时间

  private Byte storeStatus; // 店铺状态
  private Byte status; // 审核状态

  private Integer mainId; // 注册ID
  private String email; // 注册邮箱
  private String contacts; // 注册联系人姓名
  private String telphone; // 注册联系人号码

  private Byte svsGrade; // svs等级
  private Byte svsStatus; // svs认证状态

  private String category; // 供应商分类
  private Byte auth; // 供应商认证
  private String webSite; // 网址
  private Integer sort; // 排序
}
