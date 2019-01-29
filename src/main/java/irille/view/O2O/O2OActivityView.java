package irille.view.O2O;

import java.util.Date;

import irille.Entity.O2O.O2O_Activity;
import irille.view.BaseView;
import lombok.Data;

@Data
public class O2OActivityView implements BaseView {

    private Integer pkey;    // 编号  INT
    private String name;    // 活动名称  STR(100)<null>
    private Byte status;    // 活动状态 <O2OActivityStatus>  BYTE
    // TOBEGIN:1,即将开始
    // ACTIVITY:2,活动中
    // END:3,活动结束
    private String activityCat;    // 产品类目 <表主键:PdtCat>  INT<null>
    private String rules;    // 规则描述  JSONOBJECT
    private String address;    // JSON  JSONOBJECT
    private Date startDate;    // 日期时间  TIME
    private Date endDate;    // 日期时间  TIME
    private Date updatedTime;    // 更新时间  TIME
    private Short rowVersion;    // 版本  SHORT
    
    public static O2OActivityView toView(O2O_Activity bean) {
    	O2OActivityView view = new O2OActivityView();
		view.setName(bean.getName());
		view.setActivityCat(bean.getActivityCat());
		view.setStartDate(bean.getStartDate());
		view.setEndDate(bean.getEndDate());
		view.setAddress(bean.getAddress());
		view.setStatus(bean.getStatus());
		return view;
    }
}
