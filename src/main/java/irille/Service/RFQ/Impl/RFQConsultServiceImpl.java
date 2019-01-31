package irille.Service.RFQ.Impl;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.Enums.RFQConsultStatus;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Entity.RFQ.Enums.RFQConsultVerifyStatus;
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
		//标记为已删除的询盘不在平台端显示
		condition.setIsDeleted(false);
		return rFQConsultDao.findAllView(start, limit, condition);
	}

	@Override
	public RFQConsultView detail(RFQConsultView condition) {
		Integer id = condition.getPkey();
		if(id == null)
			throw new WebMessageException(ReturnCode.valid_notnull, "请选择询盘");
		
		RFQConsultView view = rFQConsultDao.findViewById(id);
		
		//如果是RFQ询盘 即公共询盘, 还需要额外返回已抢单的供应商的信息
		if(view.getType().equals(RFQConsultType.RFQ.getLine().getKey()+""))
			view.setRelations(rFQConsultRelationDao.findAllViewByConsultId(view.getPkey()));
		
		return view;
	}

	@Override
	public void approve(RFQConsultView view, Boolean verify) {
		if(verify == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请选择操作");
		}
		if(view.getPkey() == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请选择询盘");
		}
		RFQConsult consult = rFQConsultDao.findById(view.getPkey());
		if(!consult.gtType().equals(RFQConsultType.RFQ)) {
			//不是RFQ询盘不需要进行审核
			throw new WebMessageException(ReturnCode.service_state_error, "类型错误");
		}
		//必须要询盘状态为待发布,审核状态为未审核
		if(consult.gtStatus().equals(RFQConsultStatus.ready) && consult.gtVerifyStatus().equals(RFQConsultVerifyStatus.UNAUDITED)) {
			if(verify) {
				//审核通过
				consult.stVerifyStatus(RFQConsultVerifyStatus.PASS);
				consult.stStatus(RFQConsultStatus.runing);
			} else {
				//审核不通过
				consult.stVerifyStatus(RFQConsultVerifyStatus.FAIL);
				//若询盘修改次数已经大于三次,则直接关闭询盘
				if(consult.getChangeCount() > 3) {
					consult.stStatus(RFQConsultStatus.close);
				}
			}
		} else {
			throw new WebMessageException(ReturnCode.service_state_error, "状态错误");
		}
		rFQConsultDao.save(consult);
	}

	@Override
	public void delete(RFQConsultView view) {
		RFQConsult consult = null;
		if(view.getPkey() == null || (consult = rFQConsultDao.findById(view.getPkey())) == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请选择询盘");
		}
		if(!consult.gtType().equals(RFQConsultType.RFQ)) {
			//不是RFQ询盘不能删除
			throw new WebMessageException(ReturnCode.service_state_error, "类型错误");
		}
		if(consult.gtStatus() != RFQConsultStatus.close && consult.gtStatus() != RFQConsultStatus.complete) {
			//只能删除状态为已关闭和已完成的RFQ询盘
			throw new WebMessageException(ReturnCode.service_state_error, "状态错误");
		}
		if(!consult.gtIsDeleted()) {
			consult.stIsDeleted(true);
			rFQConsultDao.save(consult);
		}
	}

}
