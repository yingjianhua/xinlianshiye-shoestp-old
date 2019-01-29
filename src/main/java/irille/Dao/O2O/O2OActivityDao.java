package irille.Dao.O2O;

import java.util.List;
import java.util.stream.Collectors;

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
		BeanQuery<O2O_Activity> query = Query.SELECT(O2O_Activity.class)
				// 起始时间
				.WHERE(condition.getStartDate() != null, O2O_Activity.T.START_DATE, "=", condition.getStartDate())
				// 截止时间
				.WHERE(condition.getEndDate() != null, O2O_Activity.T.END_DATE, "=", condition.getEndDate())
				// 活动名称
				.WHERE(condition.getName() != null && !"".equals(condition.getName().trim()), O2O_Activity.T.NAME, "like", "%" + condition.getName() + "%")
				// 活动状态
				.WHERE(condition.getStatus() != null, O2O_Activity.T.STATUS, "=", condition.getStatus());

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
