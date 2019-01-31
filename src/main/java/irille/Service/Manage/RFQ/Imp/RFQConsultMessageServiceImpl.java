package irille.Service.Manage.RFQ.Imp;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Service.Manage.RFQ.RFQConsultMessageService;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.sellerAction.rfq.view.RFQConsultMessageContactView;
import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.sellerAction.rfq.view.RFQConsultMessagesView;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultMessageServiceImpl implements RFQConsultMessageService {
	
	@Inject
	private RFQConsultMessageDao rFQConsultMessageDao;
	@Inject
	private RFQConsultDao rFQConsultDao;
	@Inject
	private RFQConsultRelationDao rFQConsultRelationDao;

	@Override
	public RFQConsultMessagesView page(UsrSupplier supplier, Integer start, Integer limit, Integer consultPkey) {
		RFQConsult consult = rFQConsultDao.findById(consultPkey);
		if(rFQConsultRelationDao.countByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey()) < 1) {
			throw new WebMessageException(ReturnCode.service_gone, "您并没有抢到该询盘");
		}
		UsrPurchase purchase = consult.gtPurchaseId();
		List<RFQConsultMessage> list = rFQConsultMessageDao.findAll(start, limit, consultPkey, supplier.getPkey());
		List<RFQConsultMessageView> msgs = list.stream().map(bean->{
			RFQConsultMessageView msg = new RFQConsultMessageView();
			msg.setPkey(bean.getPkey());
			msg.setContent(bean.getContent());
			msg.setType(bean.getType());
			msg.setSendTime(bean.getSendTime());
			msg.setP2S(bean.gtP2S());
			msg.setHadRead(bean.gtHadRead());
			if(!bean.gtHadRead()) {
				bean.stHadRead(true);
				rFQConsultMessageDao.save(bean);
			}
			return msg;
		}).collect(Collectors.toList());
		
		RFQConsultMessageContactView myself = new RFQConsultMessageContactView();
		myself.setName(supplier.getName());
		RFQConsultMessageContactView another = new RFQConsultMessageContactView();
		another.setName(purchase.getName());
		
		return new RFQConsultMessagesView(msgs, myself, another);
	}

}
