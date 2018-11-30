package irille.homeAction.Activity;

import irille.homeAction.HomeAction;

/**
 * 罗马尼亚活动页临时action
 */

public class RomaniaAction extends HomeAction {


    public String execute() {
        if(isMobile()){
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
}
