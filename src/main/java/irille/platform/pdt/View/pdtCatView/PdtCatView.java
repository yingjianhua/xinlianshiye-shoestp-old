package irille.platform.pdt.View.pdtCatView;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

@Data
public class PdtCatView implements BaseView {
  private Integer id;
  private Integer categoryId; // 隶属分类ID
  private String name; // 标题
  private String productImage; //产品分类图片
  private Byte display; //首页显示
  private List children; // 子分类

  private String subjectionCat; // 隶属分类
  private Boolean enabled; // 启用
  private String createBy; // 建档员
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date createTime; // 建档时间
  private String seoName; // seo标题
  private String seoKeyword; // seo关键字
  private String seoDescription; // seo描述
}
