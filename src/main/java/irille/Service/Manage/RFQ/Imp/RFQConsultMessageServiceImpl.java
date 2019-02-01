package irille.Service.Manage.RFQ.Imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import irille.Dao.PdtProductDao;
import irille.Dao.RFQ.RFQConsultDao;
import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.RFQ.JSON.RFQConsultAlertUrlMessage;
import irille.Entity.RFQ.JSON.RFQConsultImageMessage;
import irille.Entity.RFQ.JSON.RFQConsultTextMessage;
import irille.Service.Manage.RFQ.RFQConsultMessageService;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.sellerAction.rfq.view.RFQConsultMessageContactView;
import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.sellerAction.rfq.view.RFQConsultMessagesView;
import irille.shop.pdt.Pdt.OProductType;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultMessageServiceImpl implements RFQConsultMessageService {
	
	@Inject
	private RFQConsultMessageDao rFQConsultMessageDao;
	@Inject
	private RFQConsultDao rFQConsultDao;
	@Inject
	private RFQConsultRelationDao rFQConsultRelationDao;
	@Inject
	private PdtProductDao pdtProductDao;
	@Inject
	private ObjectMapper om;

	@Override
	public RFQConsultMessagesView page(UsrSupplier supplier, Integer start, Integer limit, Integer consultPkey) {
		RFQConsult consult = rFQConsultDao.findById(consultPkey);
		if(rFQConsultRelationDao.countByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey()) < 1) {
			throw new WebMessageException(ReturnCode.service_gone, "您并没有抢到该询盘");
		}
		UsrPurchase purchase = consult.gtPurchaseId();
		List<RFQConsultMessage> list = rFQConsultMessageDao.findAll(start, limit, consultPkey, supplier.getPkey());
		List<RFQConsultMessageView> msgs = list.stream().map(bean->{
			RFQConsultMessageView view = RFQConsultMessageView.Builder.toView(bean);
			//若消息为未读消息, 设置为已读
			if(!bean.gtHadRead()) {
				bean.stHadRead(true);
				rFQConsultMessageDao.save(bean);
			}
			return view;
		}).collect(Collectors.toList());
		
		RFQConsultMessageContactView myself = new RFQConsultMessageContactView();
		myself.setName(supplier.getName());
		RFQConsultMessageContactView another = new RFQConsultMessageContactView();
		another.setName(purchase.getName());
		
		return new RFQConsultMessagesView(msgs, myself, another);
	}
	
	@Override
	public RFQConsultMessageView sendTextMessage(UsrSupplier supplier, Integer consultPkey, String content) {
		RFQConsultMessage bean = createMessage(new RFQConsultTextMessage() {{ setContent(content); }});
		rFQConsultMessageDao.save(bean);
		return RFQConsultMessageView.Builder.toView(bean);
	}

	@Override
	public RFQConsultMessageView sendImageMessage(UsrSupplier supplier, Integer consultPkey, String imageUrl) {
		RFQConsultMessage bean = createMessage(new RFQConsultImageMessage() {{ setImageUrl(imageUrl); }});
		rFQConsultMessageDao.save(bean);
		return RFQConsultMessageView.Builder.toView(bean);
	}

	@Override
	public RFQConsultMessageView sendPrivateExpoPdt(UsrSupplier supplier, Integer consultPkey, Integer productPkey) {
		PdtProduct product = pdtProductDao.findByPkey(productPkey);
		if(product.getSupplier() != supplier.getPkey()) {
			throw new WebMessageException(ReturnCode.service_gone, "商品错误");
		}
		if(!product.gtProductType().equals(OProductType.PrivateExpo)) {
			throw new WebMessageException(ReturnCode.service_gone, "不是私人展厅商品");
		}
		RFQConsultAlertUrlMessage message = new RFQConsultAlertUrlMessage();
		//三天(72小时)后过期
		message.setValidDate(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()));
		message.setProductId(productPkey);
		message.setAlertMsg("该链接被打开后72小时内有效，72小时后该链接失效，买家将无法查看该产品");
		message.setShowMsg("产品链接");
		message.setUrl("");//TODO 链接现在尚未确定  确定后补上 带上询盘聊天消息的uuid做为参数
		RFQConsultMessage bean = createMessage(message);
		rFQConsultMessageDao.save(bean);
		return RFQConsultMessageView.Builder.toView(bean);
	}
	private RFQConsultMessage createMessage(ConsultMessage message) {
		RFQConsultMessage bean = new RFQConsultMessage();
		bean.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		try {
			bean.setContent(om.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			throw new WebMessageException(ReturnCode.third_unknow, "消息发送失败");
		}
		bean.stType(RFQConsultMessageType.TEXT);
		bean.setSendTime(new Date());
		bean.setRelation(null);
		bean.stP2S(false);
		bean.stHadRead(false);
		return bean;
	}

}
