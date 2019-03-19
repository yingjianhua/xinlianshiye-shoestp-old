package com.xinlianshiye.shoestp.seller.service.usr.imp;

import com.xinlianshiye.shoestp.seller.service.usr.IUsrSupplierSellerDao;

import irille.pub.bean.query.BeanQuery;
import irille.shop.usr.UsrSupplier;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/19 Time: 14:36 */
public class UsrSupplierSellerDaoImp implements IUsrSupplierSellerDao {
  @Override
  public UsrSupplier findByUsrMainId(Integer id) {
    BeanQuery<UsrSupplier> beanQuery = new BeanQuery();
    beanQuery
        .SELECT(UsrSupplier.class)
        .FROM(UsrSupplier.class)
        .WHERE(UsrSupplier.T.UserId, "=?", id);
    return beanQuery.query(UsrSupplier.class);
  }
}
