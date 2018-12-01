package irille.Service.Activity;

import com.google.inject.ImplementedBy;
import irille.Service.Activity.Imp.ActivityServiceImp;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/1
 * Time: 14:08
 */
@ImplementedBy(ActivityServiceImp.class)
public interface ActivityService {
    void addRomaniaInquiry(int sup, String name, String email, String detail);
}
