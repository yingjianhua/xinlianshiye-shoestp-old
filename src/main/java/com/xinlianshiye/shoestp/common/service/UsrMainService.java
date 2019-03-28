package com.xinlianshiye.shoestp.common.service;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.common.service.impl.UsrMainServiceImpl;

import irille.shop.usr.UsrMain;

@ImplementedBy(UsrMainServiceImpl.class)
public interface UsrMainService {

  /**
   * 用户登出,
   *
   * <p>记录用户最后一次登录时间
   *
   * @author Jianhua Ying
   * @date 2019年3月28日
   */
  void signOut(UsrMain main);
}
