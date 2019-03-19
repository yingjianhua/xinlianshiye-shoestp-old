package irille.view.pdt;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PdtDescribeModuleView implements BaseView {

  private Integer id;
  private String name;
  private String remark;
  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  private Integer defaultModule;
  private Integer sup;
}
