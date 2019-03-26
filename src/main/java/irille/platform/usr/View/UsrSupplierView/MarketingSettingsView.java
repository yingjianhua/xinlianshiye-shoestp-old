package irille.platform.usr.View.UsrSupplierView;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class MarketingSettingsView implements BaseView {
  private String traceCode; // 跟踪代码
  private String webSizeTitle; // 自定义链接名称
  private String webSite; // 网址
  private String tongjiUrl; // 第三方统计地址
  private String tongjiPwd; // 第三方统计密码

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime; // 更新时间
}
