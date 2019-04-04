package com.xinlianshiye.shoestp.plat.service.pm.imp;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.dao.pm.IPMMessageDao;
import com.xinlianshiye.shoestp.plat.dao.pm.IPMTemplateDao;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.IPMTemplateService;
import com.xinlianshiye.shoestp.plat.service.pm.IVariableService;
import com.xinlianshiye.shoestp.plat.service.usr.IUsrService;
import com.xinlianshiye.shoestp.plat.service.usr.imp.UsrServiceImp;
import com.xinlianshiye.shoestp.plat.view.pm.MessageView;

import irille.Entity.pm.PM.OMessageType;
import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PM.OTempType;
import irille.Entity.pm.PMMessage;
import irille.Entity.pm.PMTemplate;
import irille.core.sys.Sys.OYn;
import irille.pub.util.EmailUtils;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.se.sendEmail;
import nl.justobjects.pushlet.util.Log;

public class PMMessageServiceImp implements IPMMessageService {

  @Inject private IPMMessageDao messageDao;

  @Inject private IPMTemplateDao templateDao;

  private IPMTemplateService templateService = new PMTemplateServiceImp();

  private IVariableService variableService = new VariableServiceImp();

  private IUsrService usrService = new UsrServiceImp();

  // 获取站内信列表
  // 根据站内信/邮件    收件人类型   时间   收件人搜索
  @Override
  public Page list(MessageView view, Integer start, Integer limit) {
    return messageDao.list(view, start, limit);
  }

  @Override
  public Page list(
      UsrSupplier supplier, UsrPurchase purchase, ORCVRType type, Integer start, Integer limit) {
    if (type.getLine().getKey() == ORCVRType.PURCHASE.getLine().getKey()) {
      return messageDao.list(purchase.getPkey(), type, start, limit);
    } else if (type.getLine().getKey() == ORCVRType.SUPPLIER.getLine().getKey()) {
      return messageDao.list(supplier.getPkey(), type, start, limit);
    } else {
      return messageDao.list(null, null, start, limit);
    }
  }

  @Override
  public void send(
      OTempType tempType, UsrSupplier supplier, UsrPurchase purchase, Object... objects) {
    PMTemplate template =
        templateService.getTemplateMap().get(Integer.valueOf(tempType.getLine().getKey()));
    if (null != template) {
      if (template.getEmailStatus().equals(OYn.YES.getLine().getKey())
          && null != template.getMailContent()) {
        // 发送邮件
        String content =
            variableService.render(
                variableService.getMap(tempType), template.getMailContent(), objects);
        if (tempType.getRcvrType().equals(ORCVRType.SUPPLIER) && null != supplier) {
          sendEmail(tempType, supplier, null, template.getMailTitle(), content);
        } else if (tempType.getRcvrType().equals(ORCVRType.PURCHASE) && null != purchase) {
          sendEmail(tempType, null, purchase, template.getMailTitle(), content);
        } else {
          sendEmail(tempType, null, null, template.getMailTitle(), content);
        }
      }

      if (template.getPmStatus().equals(OYn.YES.getLine().getKey())
          && null != template.getPmContent()) {
        // 发送站内信
        String content =
            variableService.render(
                variableService.getMap(tempType), template.getPmContent(), objects);
        if (tempType.getRcvrType().equals(ORCVRType.SUPPLIER) && null != supplier) {
          sendPm(tempType, supplier, null, template.getTitle(), content);
        } else if (tempType.getRcvrType().equals(ORCVRType.PURCHASE) && null != purchase) {
          sendPm(tempType, null, purchase, template.getTitle(), content);
        } else {
          sendPm(tempType, null, null, template.getTitle(), content);
        }
      }
    }
  }

  private void sendPm(
      OTempType type, UsrSupplier supplier, UsrPurchase purchase, String title, String content) {
    PMMessage message = initMessage(type, supplier, purchase, title, content);
    message.setMsgType(OMessageType.PM.getLine().getKey());
    if (message.getRcvr().equals(-1)) {
      Integer count = usrService.count(type.getRcvrType());
      message.setCount(count + "/" + count);
    } else {
      message.setCount("1/1");
    }
    message.setType(type.getLine().getKey());
    message.ins();
  }

  private void sendEmail(
      OTempType type, UsrSupplier supplier, UsrPurchase purchase, String title, String content) {
    PMMessage message = initMessage(type, supplier, purchase, title, content);
    message.setMsgType(OMessageType.EMAIL.getLine().getKey());

    sendEmail mail = new sendEmail();
    mail.setSubject(title);
    mail.setContent(content);
    if (message.getRcvrType().equals(ORCVRType.PURCHASE.getLine().getKey())
        && !message.getRcvr().equals(-1)) {
      mail.setReceiver(purchase.getEmail());
    } else if (message.getRcvrType().equals(ORCVRType.SUPPLIER.getLine().getKey())
        && !message.getRcvr().equals(-1)) {
      mail.setReceiver(supplier.gtUserid().getEmail());
    }
    if (message.getRcvr() == -1) {
      int i = 0;
      // 邮件群发
      List<String> mails = usrService.getMailsBy(message.gtRcvrType());
      if (null != mails) {
        for (String sendMail : mails) {
          mail.setReceiver(sendMail);
          try {
            EmailUtils.sendMail(mail);
            i++;
          } catch (IOException e) {
            Log.info(mail + "邮件发送失败");
          }
        }
      }
      message.setCount(mail == null ? String.valueOf(0) : (mails.size() + "/" + i));
    } else {
      try {
        EmailUtils.sendMail(mail);
      } catch (IOException e) {
        e.printStackTrace();
      }
      message.setCount("1/1");
    }
    message.ins();
  }

  private PMMessage initMessage(
      OTempType type, UsrSupplier supplier, UsrPurchase purchase, String title, String content) {
    PMMessage message = new PMMessage();
    message.setRcvrType(type.getRcvrType().getLine().getKey());
    message.setTitle(title);
    message.setContent(content);
    message.setType(type.getLine().getKey());
    if (type.getRcvrType().getLine().getKey() == ORCVRType.PURCHASE.getLine().getKey()
        && purchase != null) {
      message.setRcvr(purchase.getPkey());
    } else if (type.getRcvrType().getLine().getKey() == ORCVRType.SUPPLIER.getLine().getKey()
        && supplier != null) {
      message.setRcvr(supplier.getPkey());
    } else {
      message.setRcvr(-1);
    }
    message.setSendTime(new Date());
    return message;
  }
}
