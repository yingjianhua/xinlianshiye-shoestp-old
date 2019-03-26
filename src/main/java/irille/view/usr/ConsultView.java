package irille.view.usr;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class ConsultView implements BaseView {
  private Integer id;
  private String title;
  private String image;
  private Integer product;
  private String productNum;
  private String name;
  private String email;
  private Integer country;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String countryName;

  private String countryFlag;
  private String content;
  private String supplierName; // 供应商名字
  private Integer quantity; // 鞋子的数量
  private Boolean haveNewMsg; // 是否有新消息
  private Integer supplierCount; // 接单的供应商数量
  private Integer supplierId; // 若为专属询盘,则该字段表示该询盘属于哪个供应商
  private Integer count; // 剩余抢单次数

  @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
  private Date createTime;

  private List<ConsultRelationView> relations; // 询盘关联供应商,包含留言记录
  private String purchaseimage; // 采购商头像
  private String supplierimage; // 供应商头像
  private String supplieremail; // 供应商邮箱
}
