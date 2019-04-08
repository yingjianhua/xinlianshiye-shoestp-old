package irille.platform.pdt.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/**
 * 产品尺寸对象类
 *
 * @author lingjian
 * @date 2019/1/22 13:37
 */
@Data
public class PdtSizeView implements BaseView {
  private Integer id;
  //    @JsonSerialize(using = I18NFieldSerializer.class)
  private String name; // 名称
  //    @JsonSerialize(using = I18NFieldSerializer.class)
  private String productCategory; // 产品类目
  private String createdBy; // 建档员

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date createdTime; // 建档时间

  private Byte type;

  private Byte typeVer;
}
