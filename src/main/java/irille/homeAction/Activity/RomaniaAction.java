package irille.homeAction.Activity;

import irille.Dao.Old.ActivitySignIn.ActivityNewInqDao;
import irille.Entity.newInq.NewInquiry;
import irille.Service.Activity.ActivityService;
import irille.homeAction.HomeAction;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

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
    @Inject
    private ActivityNewInqDao activityNewInqDao;

    public void inquiry() throws IOException {
        NewInquiry inq = new NewInquiry();
        Integer[] supplierId = {281, 298, 283, 318, 279, 295, 16, 291, 282, 13, 317, 23, 78, 301};
        if(new HashSet<Integer>(Arrays.asList(supplierId)).contains(supId)){
            inq.setSupplierid(supId);
            inq.setName(name);
            inq.setEmail(email);
            inq.setDetail(detail);
            activityNewInqDao.setB(inq).commit();
            writeErr(1, null);
        }else{
            writeErr(-1,"该供应商不是指定供应商");
        }
    }
}
