package irille.Service.RFQ;

import com.google.inject.ImplementedBy;

import irille.Service.RFQ.Impl.RFQConsultServiceImpl;
import irille.platform.rfq.view.RFQConsultView;
import irille.view.Page;

@ImplementedBy(RFQConsultServiceImpl.class)
public interface RFQConsultService {

	/**
	 * 分页查询询盘列表
	 * 
	 * @return
	 * @author Jianhua Ying
	 */
	Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition);
	
	/**
	 * 查询询盘的详细信息
	 * 
	 * @param condition
	 * @return
	 * @author Jianhua Ying
	 */
	RFQConsultView detail(RFQConsultView condition);
}
