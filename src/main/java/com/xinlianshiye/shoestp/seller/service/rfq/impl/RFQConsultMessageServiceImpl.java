package com.xinlianshiye.shoestp.seller.service.rfq.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.seller.service.rfq.RFQConsultMessageService;

import irille.Dao.PdtProductDao;
import irille.Dao.RFQ.RFQConsultDao;
import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.Entity.RFQ.Enums.RFQConsultRelationReadStatus;
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.RFQ.JSON.RFQConsultImageMessage;
import irille.Entity.RFQ.JSON.RFQConsultPrivateProductUrlMessage;
import irille.Entity.RFQ.JSON.RFQConsultTextMessage;
import irille.Entity.pm.PM.OTempType;
import irille.pub.bean.Query;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.AppConfig;
import irille.sellerAction.rfq.view.RFQConsultMessageContactView;
import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.sellerAction.rfq.view.RFQConsultMessagesView;
import irille.shop.pdt.Pdt.OProductType;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class RFQConsultMessageServiceImpl implements RFQConsultMessageService {

  @Inject private RFQConsultMessageDao rFQConsultMessageDao;
  @Inject private RFQConsultDao rFQConsultDao;
  @Inject private RFQConsultRelationDao rFQConsultRelationDao;
  @Inject private PdtProductDao pdtProductDao;
  @Inject private ObjectMapper om;

  @Inject private IPMMessageService messageService;

  @Override
  public RFQConsultMessagesView page(
      UsrSupplier supplier, Integer start, Integer limit, Integer consultPkey) {
    RFQConsult consult = rFQConsultDao.findById(consultPkey);

    List<RFQConsultRelation> relations =
        rFQConsultRelationDao.findAllByConsult_PkeySupplier_Pkey(
            consultPkey + "", supplier.getPkey());

    if (relations.size() == 0) {
      throw new WebMessageException(ReturnCode.service_gone, "您并没有抢到该询盘");
    }

    RFQConsultRelation relation = relations.get(0);
    // 供应商读取消息后, 若消息状态为供应商未读, 则修改为供应商已读
    if (relation.gtReadStatus() == RFQConsultRelationReadStatus.SUPPLIER_UNREAD) {
      relation.stReadStatus(RFQConsultRelationReadStatus.SUPPLIER_HADREAD);
      rFQConsultRelationDao.save(relation);
    }

    UsrPurchase purchase = consult.gtPurchaseId();
    List<RFQConsultMessage> list =
        rFQConsultMessageDao.findAll(start, limit, consultPkey, supplier.getPkey());
    List<RFQConsultMessageView> msgs =
        list.stream()
            .map(
                bean -> {
                  RFQConsultMessageView view = RFQConsultMessageView.Builder.toView(bean);
                  // 若消息为未读消息, 设置为已读
                  if (bean.gtP2s() && !bean.gtHadRead()) {
                    bean.stHadRead(true);
                    rFQConsultMessageDao.save(bean);
                  }
                  return view;
                })
            .collect(Collectors.toList());

    RFQConsultMessageContactView myself = new RFQConsultMessageContactView();
    myself.setAvatar(supplier.getLogo());
    myself.setName(supplier.getName());
    RFQConsultMessageContactView another = new RFQConsultMessageContactView();
    another.setAvatar(purchase.getIcon());
    if (null != purchase.getName() || "".equals(purchase.getName()))
      another.setName(purchase.getName());
    else another.setName("Himself");
    return new RFQConsultMessagesView(msgs, myself, another);
  }

  private RFQConsultMessageView sendMessage(
      UsrSupplier supplier, Integer consultPkey, ConsultMessage message) {
    return sendMessage(supplier, consultPkey, message, null);
  }

  private RFQConsultMessageView sendMessage(
      UsrSupplier supplier,
      Integer consultPkey,
      ConsultMessage message,
      Consumer<RFQConsultMessage> callback) {
    RFQConsultRelation relation =
        rFQConsultRelationDao.findByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey());
    RFQConsultMessage bean = new RFQConsultMessage();
    try {
      bean.setContent(om.writeValueAsString(message));
    } catch (JsonProcessingException e) {
      throw new WebMessageException(ReturnCode.third_unknow, "消息发送失败");
    }
    bean.setType(message.type());
    bean.setSendTime(new Date());
    bean.stRelation(relation);
    bean.stP2s(false);
    bean.stHadRead(false);
    // 回调
    if (callback != null) callback.accept(bean);
    rFQConsultMessageDao.save(bean);

    // 商家发送消息后 设置消息状态为-采购商未读, 并记录最后一条聊天消息
    relation.stReadStatus(RFQConsultRelationReadStatus.PURCHASE_UNREAD);
    relation.stLastMessage(bean);
    relation.setLastMessageSendTime(bean.getSendTime());
    rFQConsultRelationDao.save(relation);

    // 更新询盘的最新事件时间
    RFQConsult consult = relation.gtConsult();
    consult.setLastMessageSendTime(new Date());

    rFQConsultDao.save(consult);

    messageService.send(
        OTempType.RFQ_MESSAGE_NOTICE, null, relation.gtPurchaseId(), relation, bean, supplier);
    return RFQConsultMessageView.Builder.toView(bean);
  }

  @Override
  public RFQConsultMessageView sendTextMessage(
      UsrSupplier supplier, Integer consultPkey, String content) {
    return sendMessage(
        supplier,
        consultPkey,
        new RFQConsultTextMessage() {
          {
            setContent(content);
          }
        });
  }

  @Override
  public RFQConsultMessageView sendImageMessage(
      UsrSupplier supplier, Integer consultPkey, String imageUrl) {
    return sendMessage(
        supplier,
        consultPkey,
        new RFQConsultImageMessage() {
          {
            setImageUrl(imageUrl);
          }
        });
  }

  @Override
  public RFQConsultMessageView sendPrivateExpoPdt(
      final UsrSupplier supplier, final Integer consultPkey, final Integer productPkey) {
    PdtProduct product = pdtProductDao.findByPkey(productPkey);
    if (product.getSupplier() != null && !(product.getSupplier().equals(supplier.getPkey()))) {
      throw new WebMessageException(ReturnCode.service_gone, "商品错误");
    }
    if (!product.gtProductType().equals(OProductType.PrivateExpo)) {
      throw new WebMessageException(ReturnCode.service_gone, "不是私人展厅商品");
    }
    // 若供应商上一次发送的私人展厅产品链接还没有失效 信息使用上一次的内容
    RFQConsultMessage sendBefore =
        Query.selectFrom(RFQConsultMessage.class)
            .LEFT_JOIN(
                RFQConsultRelation.class, RFQConsultMessage.T.RELATION, RFQConsultRelation.T.PKEY)
            .LEFT_JOIN(RFQConsult.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY)
            .WHERE(RFQConsultMessage.T.TYPE, "=?", RFQConsultMessageType.PRIVATE_PRODUCT_URL)
            .WHERE(RFQConsult.T.PKEY, "=?", consultPkey)
            .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplier.getPkey())
            .WHERE(RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_PRODUCT_ID, "=?", productPkey)
            .AND()
            //消息未过期或者未开始计时
            .WHERE(
                RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_VALID_DATE,
                ">?",
                LocalDateTime.now())
            .or()
            .WHERE(RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_VALID_DATE, "is null")
            .query();
    RFQConsultPrivateProductUrlMessage message = null;
    String uuid = null;
    Date validDate = null;

    //假如发送过这样的消息, 复制该消息的内容
    if (sendBefore != null) {
      try {
        message = om.readValue(sendBefore.getContent(), RFQConsultPrivateProductUrlMessage.class);
        uuid = sendBefore.getPrivateProductUrlMessageUuid();
        validDate = sendBefore.getPrivateProductUrlMessageValidDate();
      } catch (IOException e) {
      }
    }
    if (message == null) {
      uuid = createUuid();
      message = new RFQConsultPrivateProductUrlMessage();
      message.setProductId(productPkey);
      message.setAlertMsg("该链接被打开后72小时内有效，72小时后该链接失效，买家将无法查看该产品");
      message.setShowMsg(product.getName());
      message.setUrl(
          AppConfig.domain
              + "home/pdt_PdtProduct_gtProductsInfo?id="
              + product.getPkey()
              + "&expoKey="
              + uuid);
    }

    final String final_uuid = uuid;
    final Date final_validDate = validDate;
    final Integer final_productPkey = productPkey;
    
    return sendMessage(
        supplier,
        consultPkey,
        message,
        (bean) -> {
          bean.setPrivateProductUrlMessageUuid(final_uuid);
          bean.setPrivateProductUrlMessageValidDate(final_validDate);
          bean.setPrivateProductUrlMessageProductId(final_productPkey);
        });
  }

  private static String createUuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
