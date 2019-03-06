package irille.view.O2O;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private Integer addr;
    private String latitude;//纬度
    private String longitude;//经度
    private String activityCat;    // 产品类目 <表主键:PdtCat>  INT<null>
    private List<Map<String,Object>> catList;
    private String rules;    // 规则描述  JSONOBJECT
    private String address;    // JSON  JSONOBJECT
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;    // 日期时间  TIME
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;    // 日期时间  TIME
    private Date updatedTime;    // 更新时间  TIME
    private Short rowVersion;    // 版本  SHORT
    
    
    private long startTime;
    private long endTime;
    
    public static O2OActivityView toView(O2O_Activity bean) {
    	O2OActivityView view = new O2OActivityView();
    	view.setPkey(bean.getPkey());
		view.setName(bean.getName());
		view.setActivityCat(bean.getActivityCat());
		view.setStartDate(bean.getStartDate());
		view.setEndDate(bean.getEndDate());
		view.setAddress(bean.gtAddress().getName());
		view.setStatus(bean.getStatus());
		return view;
    }
}
