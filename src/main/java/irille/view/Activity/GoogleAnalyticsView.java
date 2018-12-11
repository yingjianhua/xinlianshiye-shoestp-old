package irille.view.Activity;

import lombok.Data;

/**
 * 分析统计数据
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 14:50
 */
@Data
public class GoogleAnalyticsView {
    private Integer id;
    private Integer type;
    private int inquiry;
    private int trafficVolume;
    private int pe;

}
