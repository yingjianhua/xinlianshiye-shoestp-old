package irille.homeAction.Activity;

import irille.Service.Activity.ActivityService;
import irille.homeAction.HomeAction;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;

/**
 * 罗马尼亚活动页临时action
 */

public class RomaniaAction extends HomeAction {


    @Inject
    private ActivityService activityService;

    public String execute() {
        if (isMobile()) {
            setResult("../activity/Jsp/m/Romaniaindex/order-meeting-en.jsp");
            return HomeAction.TRENDS;
        }
        setResult("../activity/Jsp/Romania/Romaniaindex/order-meeting-en.jsp");
        return HomeAction.TRENDS;
    }

    public String classifyactivity() {
        setResult("../activity/Jsp/Romania/ProductList/index.jsp");
        return HomeAction.TRENDS;
    }

    @Getter
    @Setter
    private int supId;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String detail;


    public void inquiry() throws IOException {
        System.out.println(supId);
        System.out.println(email);
        System.out.println(name);
        System.out.println(detail);
        writeErr(1, null);
    }

}
