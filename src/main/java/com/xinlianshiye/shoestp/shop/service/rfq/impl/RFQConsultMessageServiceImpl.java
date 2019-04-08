package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultMessageService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageContactView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessagesView;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.Entity.RFQ.Enums.RFQConsultRelationReadStatus;
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.RFQ.JSON.RFQConsultPrivateProductUrlMessage;
import irille.Entity.RFQ.JSON.RFQConsultImageMessage;
import irille.Entity.RFQ.JSON.RFQConsultTextMessage;
import irille.Entity.pm.PM.OTempType;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultMessageServiceImpl implements RFQConsultMessageService {

  @Inject private ObjectMapper om;

  @Inject private IPMMessageService messageService;

  @Override
  public RFQConsultMessagesView page(
      UsrPurchase purchase,
      Integer relationPkey,
      Integer nextMessagePkey,
      Integer preMessagePkey,
      Integer start,
      Integer limit,
      Language language) {
    BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
    query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
    query.WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
    RFQConsultRelation relation = query.query();
    if (relation == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, language));
    }

    // 买家读取消息后, 若消息状态为采购商未读, 则修改为采购商已读
    if (relation.gtReadStatus() == RFQConsultRelationReadStatus.PURCHASE_UNREAD) {
      relation.stReadStatus(RFQConsultRelationReadStatus.PURCHASE_HADREAD);
      // 读取消息后 该报价就不是一个新报价了, isNew设置为false
      relation.stIsNew(false);
      relation.upd();
    }

    BeanQuery<RFQConsultMessage> query2 = Query.selectFrom(RFQConsultMessage.class);
    query2.WHERE(RFQConsultMessage.T.RELATION, "=?", relationPkey);
    if (nextMessagePkey != null) {
      query2.WHERE(RFQConsultMessage.T.PKEY, ">?", nextMessagePkey);
    }
    if (preMessagePkey != null) {
      query2.WHERE(RFQConsultMessage.T.PKEY, "<?", preMessagePkey);
    }
    query2.ORDER_BY(RFQConsultMessage.T.SEND_TIME, "desc");
    query2.limit(start, limit);
    List<RFQConsultMessage> messages = query2.queryList();

    List<RFQConsultMessageView> msgs =
        messages.stream()
            .map(
                bean -> {
                  RFQConsultMessageView view = RFQConsultMessageView.Builder.toView(bean);
                  // 若消息为未读消息, 设置为已读
                  if (!bean.gtHadRead() && !bean.gtP2s()) {
                    bean.stHadRead(true);
                    bean.upd();
                  }
                  return view;
                })
            .collect(Collectors.toList());

    RFQConsultMessageContactView myself = new RFQConsultMessageContactView();
    myself.setAvatar(purchase.getIcon());
    myself.setName(purchase.getName());

    RFQConsultMessageContactView another = new RFQConsultMessageContactView();
    UsrSupplier supplier = relation.gtSupplierId();
    another.setAvatar(supplier.getLogo());
    another.setName(supplier.getName());

    return new RFQConsultMessagesView(msgs, myself, another);
  }

  @Override
  public RFQConsultMessageView sendTextMessage(
      UsrPurchase purchase, Integer relationPkey, String content) {
    RFQConsultTextMessage textMessage = new RFQConsultTextMessage();
    textMessage.setContent(content);
    return sendMessage(purchase, relationPkey, textMessage);
  }

  @Override
  public RFQConsultMessageView sendImageMessage(
      UsrPurchase purchase, Integer relationPkey, String imageUrl) {
    RFQConsultImageMessage imageMessage = new RFQConsultImageMessage();
    imageMessage.setImageUrl(imageUrl);
    return sendMessage(purchase, relationPkey, imageMessage);
  }

  private RFQConsultMessageView sendMessage(
      UsrPurchase purchase, Integer relationPkey, ConsultMessage message) {
    RFQConsultRelation relation =
        Query.selectFrom(RFQConsultRelation.class)
            .WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey())
            .WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey)
            .query();

    RFQConsultMessage bean = new RFQConsultMessage();
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

    // 采购商发送消息后 设置消息状态为-供应商未读, 并记录最后一条聊天消息
    relation.stReadStatus(RFQConsultRelationReadStatus.SUPPLIER_UNREAD);
    relation.stLastMessage(bean);
    relation.setLastMessageSendTime(bean.getSendTime());
    relation.upd();

    // 更新询盘的最新事件时间
    RFQConsult consult = relation.gtConsult();
    consult.setLastMessageSendTime(new Date());
    consult.upd();

    messageService.send(OTempType.RFQ_REPLY, relation.gtSupplierId(), null, bean);
    return RFQConsultMessageView.Builder.toView(bean);
  }

  @Inject private RFQConsultMessageDao rFQConsultMessageDao;

  @Override
  public Integer checkPrivateExpoKey(String expoKey) {
    RFQConsultMessage message = rFQConsultMessageDao.findByUuid(expoKey);
    if (message == null) return null;
    if (message.gtType() != RFQConsultMessageType.PRIVATE_PRODUCT_URL) return null;
    try {
      RFQConsultPrivateProductUrlMessage content =
          om.readValue(message.getContent(), RFQConsultPrivateProductUrlMessage.class);
      if (content.getValidDate() == null) {
        content.setValidDate(
            Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()));
        rFQConsultMessageDao.updateValidDateAndContentByUuid(
            expoKey, content.getValidDate(), om.writeValueAsString(content));
        return content.getProductId();
      } else if (content.getValidDate().after(new Date())) {
        return content.getProductId();
      } else {
        return null;
      }
    } catch (IOException e) {
      return null;
    }
  }
}
