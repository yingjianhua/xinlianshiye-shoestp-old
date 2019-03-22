package com.xinlianshiye.shoestp.common.dao.usr.impl;

import java.util.Optional;

import com.xinlianshiye.shoestp.common.dao.usr.UsrMainDao;

import irille.pub.bean.Query;
import irille.shop.usr.UsrMain;

public class UsrMainDaoImpl implements UsrMainDao {

  @Override
  public void save(UsrMain main) {
    if (main.getPkey() == null) {
      main.upd();
    } else {
      main.ins();
    }
  }

  @Override
  public Optional<UsrMain> findByEmail(String email) {
    return Optional.ofNullable(
        Query.selectFrom(UsrMain.class).WHERE(UsrMain.T.EMAIL, "=?", email).query());
  }
}
