package com.xinlianshiye.shoestp.common.service.impl;

import java.util.Date;

import com.xinlianshiye.shoestp.common.service.UsrMainService;

import irille.shop.usr.UsrMain;

public class UsrMainServiceImpl implements UsrMainService {

  @Override
  public void signOut(UsrMain main) {
    if (main != null) {
      main.setLastLogin(new Date());
      main.upd();
    }
  }
}
