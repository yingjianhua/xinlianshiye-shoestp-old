package irille.platform.lg.View;

import java.math.BigDecimal;

import irille.view.BaseView;
import lombok.Data;

@Data
public class statisticsView implements BaseView {
  private String requestUrl; // 请求地址
  private Long count; // 总数
  private BigDecimal processTime; // 处理用时
}
