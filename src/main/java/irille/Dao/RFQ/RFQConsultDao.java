package irille.Dao.RFQ;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultDaoImpl;
import irille.platform.rfq.view.RFQConsultView;
import irille.view.Page;

@ImplementedBy(RFQConsultDaoImpl.class)
public interface RFQConsultDao {

	/**
	 * 分页查询询盘列表
	 * 
	 * @param start 起始记录数
	 * @param limit 每页记录数
	 * @param condition 查询条件 有效条件包括:
	 * <ul>
	 * <li>询盘名称
	 * <li>采购商名称
	 * <li>供应商名称
	 * <li>产品名称
	 * <li>国家
	 * <li>询盘类型
	 * <li>询盘的审核状态
	 * </ul>
	 * @return 分页结果
	 * @author Jianhua Ying
	 */
	Page<RFQConsultView> findAllView(Integer start, Integer limit, RFQConsultView condition);

	/**
	 * 根据id查询询盘信息
	 * 
	 * @param id 询盘主键
	 * @return 查询结果
	 * @author Jianhua Ying
	 */
	RFQConsultView findViewById(Integer id);
}
