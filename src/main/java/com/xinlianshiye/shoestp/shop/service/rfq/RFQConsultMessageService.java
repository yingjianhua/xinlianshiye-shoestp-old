package com.xinlianshiye.shoestp.shop.service.rfq;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQConsultMessageServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessageView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultMessagesView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;

@ImplementedBy(RFQConsultMessageServiceImpl.class)
public interface RFQConsultMessageService {

  /**
   * 分页查询聊天信息
   *
   * @param purchase 采购商
   * @param relationPkey 询盘关联主键
   * @param nextMessagePkey 从指定message开始查询, 读取新消息
   * @param preMessagePkey 从指定message开始查询, 读取历史消息
   * @param start 开始记录数
   * @param limit 每页记录数
   * @return
   * @author Jianhua Ying
   */
  RFQConsultMessagesView page(
      UsrPurchase purchase,
      Integer relationPkey,
      Integer nextMessagePkey,
      Integer preMessagePkey,
      Integer start,
      Integer limit,
      Language language);

  /**
   * 采购商发送聊天消息
   *
   * @param purchase 采购商
   * @param relationPkey 询盘关联主键
   * @param content 消息内容
   * @return
   * @author Jianhua Ying
   */
  RFQConsultMessageView send(UsrPurchase purchase, Integer relationPkey, String content);

  /**
   * 核对私人展厅产品密钥
   *
   * <p>若密钥存在并且没有过期,则返回产品主键,否则返回null
   *
   * @param expoKey 私人展厅产品密钥
   * @author Jianhua Ying
   */
  Integer checkPrivateExpoKey(String expoKey);
}
