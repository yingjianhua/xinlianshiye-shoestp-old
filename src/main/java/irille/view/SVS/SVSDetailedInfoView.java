package irille.view.SVS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.shop.usr.UsrSupplier;
import irille.view.BaseView;
import irille.view.SVS.SVSInfoView.*;
import lombok.Data;

@Data
public class SVSDetailedInfoView implements BaseView {

  private Integer id; // SVS认证ID

  private realFactory factory; // 真实工厂信息

  private Byte grade; // 会员等级

  private Integer baseScore; // 基础分

  private research research; // 研发能力信息

  private tradeTeam team; // 外贸团队信息

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date applicationTime; // 申请认证时间

  private Byte status; // 认证状态

  private UsrSupplier supplier; // 申请认证用户

  private exhibitionAttended exhibition; // 展会信息

  private Integer dynamicScore; // 动态分

  private productQuality quality; // 产品质量信息

  private productionCapacity capacity; // 生产能力信息

  private partner part; // 合作商信息

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date autTime; // 审核时间

  private Integer count; // 申请认证次数

  private String reason; // 失败原因
}
