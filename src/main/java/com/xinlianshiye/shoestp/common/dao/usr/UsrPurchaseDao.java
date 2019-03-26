package com.xinlianshiye.shoestp.common.dao.usr;

import java.util.Optional;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.common.dao.usr.impl.UsrPurchaseDaoImpl;

import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrPurchaseDaoImpl.class)
public interface UsrPurchaseDao {

  void save(UsrPurchase purchase);

  Optional<UsrPurchase> findByLoginNameOrEmail(String loginName);
}
