package irille.Service.Activity;

import com.google.inject.ImplementedBy;
import irille.Service.Activity.Imp.ActivityServiceImp;
import irille.view.Activity.PkCompetitionPageManageView;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/1
 * Time: 14:08
 */
@ImplementedBy(ActivityServiceImp.class)
public interface IActivityService {

    PkCompetitionPageManageView getPkCompetitionData(Date startDate, Date endDate,Integer supId,String type);

    void generateData();
}
