package irille.platform.lg.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class LgAccessView implements BaseView {
  private Integer id;
  private String user; // 用户
  private String loginName; // 登录名
  private String remoteAddr; // 远程地址
  private String requestUrl; // 请求地址
  private String params; // 请求参数
  private Byte success; // 是否成功
  private String remark; // 备注
  private Long processTime; // 处理用时

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date requestTime; // 请求时间
}
