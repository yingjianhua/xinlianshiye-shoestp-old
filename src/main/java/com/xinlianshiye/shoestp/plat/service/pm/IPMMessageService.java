package com.xinlianshiye.shoestp.plat.service.pm;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMMessageServiceImp;
import com.xinlianshiye.shoestp.plat.view.pm.MessageView;

import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PM.OTempType;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

@ImplementedBy(PMMessageServiceImp.class)
public interface IPMMessageService {

  /** 发送 */
  void send(OTempType tempType, UsrSupplier supplier, UsrPurchase purchase, Object... objects);

  Page list(MessageView view, Integer start, Integer limit);

  Page list(
      UsrSupplier supplier, UsrPurchase purchase, ORCVRType type, Integer start, Integer limit);
}
