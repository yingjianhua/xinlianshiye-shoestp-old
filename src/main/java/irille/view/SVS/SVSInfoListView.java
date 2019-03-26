package irille.view.SVS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/**
 * 平台端SVS认证信息
 *
 * @author GS
 */
@Data
public class SVSInfoListView implements BaseView {
  private Integer id;
  
  private Integer supplierId;
  
  private String shopName; // 店铺名称

  private String name; // 联系人名称

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date applicationTime; // 申请认证时间

  private Byte status; // SVS认证状态

  private Byte grade; // SVS认证等级

  private Byte shopStatus; // 店铺状态
}
