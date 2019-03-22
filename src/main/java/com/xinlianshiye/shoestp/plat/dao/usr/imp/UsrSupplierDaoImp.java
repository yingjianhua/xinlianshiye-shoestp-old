package com.xinlianshiye.shoestp.plat.dao.usr.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.plat.dao.usr.IUsrSupplierDao;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrSupplier;

public class UsrSupplierDaoImp implements IUsrSupplierDao {
  @Override
  public List<String> getSupplierEmail() {
    SQL sql = new SQL();
    sql.SELECT(UsrMain.T.EMAIL)
        .LEFT_JOIN(UsrMain.class, UsrMain.T.PKEY, UsrSupplier.T.UserId)
        .FROM(UsrSupplier.class);

    return Query.sql(sql).queryMaps().stream()
        .map(
            mail -> {
              return GetValue.get(mail, UsrSupplier.T.EMAIL, String.class, null);
            })
        .filter(
            mail -> {
              if (null == mail && "".equals(mail)) {
                return false;
              } else {
                return true;
              }
            })
        .collect(Collectors.toList());
  }

  @Override
  public Integer count() {
    SQL sql = new SQL();
    sql.SELECT(UsrSupplier.class).FROM(UsrSupplier.class);
    return Query.sql(sql).queryCount();
  }
}
