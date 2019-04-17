package irille.view.newInq;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class newInqView implements BaseView {
  private Integer inqPkey; // pk大赛询盘pkey
  private String supplierName; // 供应商名字
  private String name; // 询盘名字
  private String email; // 邮箱
  private String detail; // 详情
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime; // 发布时间
}
