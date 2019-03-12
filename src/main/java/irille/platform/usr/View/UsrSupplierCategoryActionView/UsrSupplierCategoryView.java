package irille.platform.usr.View.UsrSupplierCategoryActionView;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrSupplierCategoryView implements BaseView {
  private Integer id; // pkey
  private String name; // 名称
  private String showName; // 网站显示名称
  private Integer categoryUpId; // 上级分类id
  private String categoryUp; // 上级分类
  private Byte createBy; // 建档员

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date createTime; // 建档时间
}
