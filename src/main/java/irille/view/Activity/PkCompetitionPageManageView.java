package irille.view.Activity;

import irille.Entity.Pk.PkCompetitionData;
import irille.view.BaseView;
import lombok.Data;

/**
 * Pk大赛数据统计    http://localhost:9528/#/pkStatistics/index
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 13:48
 */
@Data
public class PkCompetitionPageManageView implements BaseView {
    private PkCompetitionData pkCompetitionData;
    private PkCompetitionGlobalDataView pkCompetitionGlobalDataView;
    private String googleViewId = null;
}
