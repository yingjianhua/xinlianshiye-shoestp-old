package irille.platform.pdt.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/** Created by admin on 2019/1/17. */
@Data
public class PdtAttrCatView implements BaseView {
  private Integer id;
  private String name; // 分类名字
  private String createName; // 建党员

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date createTime; // 创建时间
}
