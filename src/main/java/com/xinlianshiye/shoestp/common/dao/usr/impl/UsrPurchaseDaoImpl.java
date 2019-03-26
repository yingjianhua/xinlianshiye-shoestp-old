package com.xinlianshiye.shoestp.common.dao.usr.impl;

import java.util.Optional;

import com.xinlianshiye.shoestp.common.dao.usr.UsrPurchaseDao;

import irille.pub.bean.Query;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseDaoImpl implements UsrPurchaseDao {

  @Override
  public void save(UsrPurchase purchase) {
    if (purchase.getPkey() != null) purchase.upd();
    else purchase.ins();
  }

  @Override
  public Optional<UsrPurchase> findByLoginNameOrEmail(String loginName) {
    return Optional.ofNullable(
        Query.selectFrom(UsrPurchase.class)
            .WHERE(UsrPurchase.T.LOGIN_NAME, "=?", loginName)
            .orWhere(UsrPurchase.T.EMAIL, "=?", loginName)
            .query());
  }
}
