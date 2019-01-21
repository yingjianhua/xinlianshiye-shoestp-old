package irille.platform.lg.View;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class statisticsView implements BaseView {
    private String requestUrl;//请求地址
    private Long count;//总数
    private BigDecimal processTime;//处理用时
}
