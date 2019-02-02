package irille.Service.Manage.O2O;

import irille.view.Page;
import irille.view.O2O.O2OProductView;

/**
 * O2O活动商品业务层
 * 
 * @author Jianhua Ying
 *
 */
public interface O2OProductService {

	/**
	 * <p>活动商品列表
	 * <ul>搜索条件
	 * <li>活动名称
	 * <li>商品类目
	 * <li>供应商名称
	 * <li>供应商等级
	 * <li>活动地区
	 * <li>活动商品状态
	 */
	Page<O2OProductView> list(Integer start, Integer limit, O2OProductView condition);
	
	/**
	 * <p>审核
	 * <ul>
	 * <li>审核通过
	 * <li>审核失败
	 */
	O2OProductView approve(O2OProductView view);
}
