package irille.Service.RFQ.Impl;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Service.RFQ.RFQConsultService;
import irille.platform.rfq.view.RFQConsultView;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.view.Page;

public class RFQConsultServiceImpl implements RFQConsultService {
	
	@Inject
	private RFQConsultDao rFQConsultDao;
	@Inject
	private RFQConsultRelationDao rFQConsultRelationDao;

	@Override
	public Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition) {
		return rFQConsultDao.findAllView(start, limit, condition);
	}

	@Override
	public RFQConsultView detail(RFQConsultView condition) {
		Integer id = condition.getPkey();
		if(id == null)
			throw new WebMessageException(ReturnCode.valid_notnull, "请选择询盘");
		
		RFQConsultView view = rFQConsultDao.findViewById(id);
		
		//如果是RFQ询盘 即公共询盘, 还需要额外返回已抢单的供应商的信息
		if(view.getType().equals(RFQConsultType.RFQ.getLine().getKey()))
			view.setRelations(rFQConsultRelationDao.findAllViewByConsultId(view.getPkey()));
		
		return view;
	}

}
