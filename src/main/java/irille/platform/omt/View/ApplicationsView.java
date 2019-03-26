package irille.platform.omt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class ApplicationsView implements BaseView {
  private Integer pkey; // 审核id
  private Integer omtpkey; // 订购会id
  private String companyName; // 公司名称
  private String country; // 所在国家
  private String omtShowroom; // 订购会展厅
  private String contact; // 联系人
  private String contactInformation; // 联系方式
  private Byte whetherCertification; // 是否认证
  private String image; // 营业执照
  private Byte status; // 审核状态
}
