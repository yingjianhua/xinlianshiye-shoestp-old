package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultMessageService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageContactView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessagesView;

import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.RFQ.JSON.RFQConsultTextMessage;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultMessageServiceImpl implements RFQConsultMessageService {
	
	@Inject
	private ObjectMapper om;

	@Override
	public RFQConsultMessagesView page(UsrPurchase purchase, Integer relationPkey, Integer start, Integer limit) {
		BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
		query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
		RFQConsultRelation relation = query.query();
		if(relation == null) {
			throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
		}
		
		relation.stHadReadPurchase(true);
		relation.upd();

		BeanQuery<RFQConsultMessage> query2 = Query.selectFrom(RFQConsultMessage.class);
		query2.WHERE(RFQConsultMessage.T.RELATION, "=?", relationPkey);
		query2.limit(start, limit);
		List<RFQConsultMessage> messages = query2.queryList();
		
		List<RFQConsultMessageView> msgs = messages.stream().map(bean->{
			RFQConsultMessageView view = RFQConsultMessageView.Builder.toView(bean);
			//若消息为未读消息, 设置为已读
			if(!bean.gtHadRead() && !bean.gtP2s()) {
				bean.stHadRead(true);
				bean.upd();
			}
			return view;
		}).collect(Collectors.toList());
		
		RFQConsultMessageContactView myself = new RFQConsultMessageContactView();
		myself.setAvatar(purchase.getIcon());
		myself.setName(purchase.getName());
		
		RFQConsultMessageContactView another = new RFQConsultMessageContactView();
		UsrSupplier supplier = relation.gtSupplierId();
		another.setAvatar(supplier.getHeadPic());
		another.setName(supplier.getName());
		
		return new RFQConsultMessagesView(msgs, myself, another);
	}

	@Override
	public RFQConsultMessageView send(UsrPurchase purchase, Integer relationPkey, String content) {
		return sendMessage(purchase, relationPkey, new RFQConsultTextMessage() {{ setContent(content); }});
	}
	
	private RFQConsultMessageView sendMessage(UsrPurchase purchase, Integer relationPkey, ConsultMessage message) {
		RFQConsultRelation relation = Query.selectFrom(RFQConsultRelation.class).WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey()).WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey).query();
		//采购商发送消息后 设置供应商消息未读取
		if(relation.gtHadReadSupplier()) {
			relation.stHadReadSupplier(false);
			relation.upd();
		}
		RFQConsultMessage bean = new RFQConsultMessage();
		bean.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		try {
			bean.setContent(om.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			throw new WebMessageException(ReturnCode.third_unknow, "消息发送失败");
		}
		bean.setType(message.type());
		bean.setSendTime(new Date());
		bean.stRelation(relation);
		bean.stP2s(true);
		bean.stHadRead(false);
		bean.ins();
		return RFQConsultMessageView.Builder.toView(bean);
	}

}
