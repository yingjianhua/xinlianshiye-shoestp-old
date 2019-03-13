package irille.platform.plt.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

/**
 * @Description: 翻译缓存列表对象
 *
 * @date 2019/1/15 11:13
 * @anthor wilson zhang
 */
@Data
public class Plttrantslate implements BaseView {
  private Integer id;
  // HashCode
  private String hashcode;
  // 翻译前内容
  private String sourceText;
  // 目标语言
  private String target;
  // 翻译后语言
  private String targetText;

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date time;
}
