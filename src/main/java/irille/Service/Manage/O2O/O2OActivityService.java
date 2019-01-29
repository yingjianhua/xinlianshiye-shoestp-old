package irille.Service.Manage.O2O;

import irille.view.Page;
import irille.view.O2O.O2OActivityView;

/**
 * O2O活动业务层
 * 
 * @author Jianhua Ying
 *
 */
public interface O2OActivityService {

	/**
	 * <p>活动列表
	 * <p>搜索条件
	 * <ul>
	 * <li>开始时间
	 * <li>截止时间
	 * <li>活动名称
	 * <li>活动状态
	 * 
	 */
	Page<O2OActivityView> list(Integer start, Integer limit, O2OActivityView condition);
	
	/**
	 * <p>取消活动
	 * 
	 */
	void cancel(O2OActivityView view);
	
	/**
	 * <p>发布活动
	 * 
	 */
	O2OActivityView deploy(O2OActivityView view);
	
	/**
	 * <p>编辑活动
	 * 
	 */
	O2OActivityView edit(O2OActivityView view);
}
