package irille.Service.RFQ.Impl;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Service.RFQ.RFQConsultService;
import irille.platform.rfq.view.RFQConsultView;
import irille.view.Page;

public class RFQConsultServiceImpl implements RFQConsultService {
	
	@Inject
	private RFQConsultDao rFQConsultDao;

	@Override
	public Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition) {
		// TODO Auto-generated method stub
		return rFQConsultDao.findAll(start, limit, condition);
	}

}
