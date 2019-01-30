package irille.Service.RFQ;

import irille.platform.rfq.view.RFQConsultView;
import irille.view.Page;

public interface RFQConsultService {

	/**
	 * 分页查询询盘列表
	 * 
	 * @return
	 * @author Jianhua Ying
	 */
	Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition);
}
