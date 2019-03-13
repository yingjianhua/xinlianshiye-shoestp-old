package irille.platform.usr.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/**
 * 平台询盘对象类
 *
 * @author lingjian
 * @date 2019/1/23 13:50
 */
@Data
public class UsrConsultView implements BaseView {
  private Integer id;
  private String title; // 标题
  private String image; // 图片地址
  private String country; // 国家
  //    @JsonSerialize(using = I18NFieldSerializer.class)
  private String product; // 产品
  private Byte haveNewMsg; // 是否新消息
  private String content; // 内容
  private Byte isPublic; // 是否是公共询盘
  private Integer count; // 剩余抢单次数
  private Integer quantity; // 商品数量
  private String purchase; // 采购商
  private String name; // 采购商名字
  private String email; // 采购商邮箱

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date createdTime; // 创建时间
}
