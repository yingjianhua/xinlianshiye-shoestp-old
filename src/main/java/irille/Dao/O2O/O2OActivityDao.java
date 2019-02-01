package irille.Dao.O2O;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.view.Page;
import irille.view.O2O.O2OActivityView;

/**
 * Created by IntelliJ IDEA. User: Lijie<HelloBox@outlook.com> Date: 2019/1/26
 * Time: 12:50
 */
public class O2OActivityDao {

	public O2O_Activity getActivityInfoById(Integer id) {
		return Query.SELECT(O2O_Activity.class).WHERE(T.PKEY, "=?", id).query();
	}

	public Page<O2OActivityView> pageView(Integer start, Integer limit, O2OActivityView condition) {
		if(null == condition)
			condition = new O2OActivityView();
		BeanQuery<O2O_Activity> query = Query.SELECT(O2O_Activity.class)
				// 起始时间
				.WHERE(condition.getStartDate() != null, O2O_Activity.T.START_DATE, ">=?", condition.getStartDate())
				// 截止时间
				.WHERE(condition.getEndDate() != null, O2O_Activity.T.END_DATE, "<=?", condition.getEndDate())
				// 活动名称
				.WHERE(condition.getName() != null && !"".equals(condition.getName().trim()), O2O_Activity.T.NAME, "like ?", "%" + condition.getName() + "%");
				// 活动状态//TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
				Date now = new Date();
				if(null != condition.getStatus()){
					if(condition.getStatus().equals(O2O_ActivityStatus.TOBEGIN.getLine().getKey())){
						//未开始
						query.WHERE(T.START_DATE,">?",now);
					}else if(condition.getStatus().equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey())){
						//活动中
						query.WHERE(T.START_DATE,"<?",now);
						query.WHERE(T.END_DATE,">?",now);
					}else if(condition.getStatus().equals(O2O_ActivityStatus.END.getLine().getKey())){
						//结束
						query.WHERE(T.END_DATE,"<?",now);
					}
				}
//				query.WHERE(condition.getStatus()!=null,T.STATUS,"=?",condition.getStatus());

		List<O2OActivityView> result = query.queryList().stream().map(bean -> {
			return O2OActivityView.toView(bean);
		}).collect(Collectors.toList());
		Integer count = query.queryCount();
		return new Page<O2OActivityView>(result, start, limit, count);
	}

	public O2O_Activity findById(Integer id) {
		return Query.SELECT(O2O_Activity.class, id);
	}



}
