package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

/**
 * @Description: 翻译条件
 *
 * @date 2019/1/15 11:14
 * @anthor wilson zhang
 */
@Data
public class TrantslateConditionView implements BaseView {
  // 字段对比
  private String mode;
  // 查询条件逻辑符号
  private String condition;
  // 查询条件内容
  private String content;
}
