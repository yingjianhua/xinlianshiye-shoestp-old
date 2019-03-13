package com.xinlianshiye.shoestp.seller.service.rfq.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
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
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.RFQ.JSON.RFQConsultAlertUrlMessage;
import irille.Entity.RFQ.JSON.RFQConsultImageMessage;
import irille.Entity.RFQ.JSON.RFQConsultTextMessage;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.pm.PM.OTempType;
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
    if (rFQConsultRelationDao.countByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey())
        < 1) {
      throw new WebMessageException(ReturnCode.service_gone, "您并没有抢到该询盘");
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
    myself.setAvatar(supplier.getHeadPic());
    myself.setName(supplier.getName());
    RFQConsultMessageContactView another = new RFQConsultMessageContactView();
    another.setAvatar(purchase.getIcon());
    another.setName(purchase.getName());

    return new RFQConsultMessagesView(msgs, myself, another);
  }

  private RFQConsultMessageView sendMessage(
      UsrSupplier supplier, Integer consultPkey, ConsultMessage message) {
    return sendMessage(supplier, consultPkey, message, createUuid());
  }

  private RFQConsultMessageView sendMessage(
      UsrSupplier supplier, Integer consultPkey, ConsultMessage message, String uuid) {
    RFQConsultRelation relation =
        rFQConsultRelationDao.findByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey());
    // 商家发送消息后 设置采购商消息未读取
    if (relation.gtHadReadPurchase()) {
      relation.stHadReadPurchase(false);
      rFQConsultRelationDao.save(relation);
    }
    RFQConsultMessage bean = new RFQConsultMessage();
    bean.setUuid(uuid);
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
    rFQConsultMessageDao.save(bean);
    messageService.send(
        OTempType.RFQ_MESSAGE_NOTICE, null, relation.gtPurchaseId(), relation, bean, supplier);
    return RFQConsultMessageView.Builder.toView(bean);
  }

  private static String createUuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
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
      UsrSupplier supplier, Integer consultPkey, Integer productPkey) {
    PdtProduct product = pdtProductDao.findByPkey(productPkey);
    if (product.getSupplier() != supplier.getPkey()) {
      throw new WebMessageException(ReturnCode.service_gone, "商品错误");
    }
    if (!product.gtProductType().equals(OProductType.PrivateExpo)) {
      throw new WebMessageException(ReturnCode.service_gone, "不是私人展厅商品");
    }
    RFQConsultAlertUrlMessage message = new RFQConsultAlertUrlMessage();
    //		//三天(72小时)后过期
    //	message.setValidDate(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()));
    message.setProductId(productPkey);
    message.setAlertMsg("该链接被打开后72小时内有效，72小时后该链接失效，买家将无法查看该产品");
    message.setShowMsg(product.getName());
    String uuid = createUuid();
    message.setUrl(
        AppConfig.domain
            + "home/pdt_PdtProduct_gtProductsInfo?id="
            + product.getPkey()
            + "&expoKey="
            + uuid); // TODO 链接现在尚未确定  确定后补上 带上询盘聊天消息的uuid做为参数
    return sendMessage(supplier, consultPkey, message, uuid);
  }
}
