package com.xinlianshiye.shoestp.seller.service.usr;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.seller.service.usr.imp.UsrSupplierSellerDaoImp;

import irille.shop.usr.UsrSupplier;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/19 Time: 14:35 */
@ImplementedBy(UsrSupplierSellerDaoImp.class)
public interface IUsrSupplierSellerDao {
    UsrSupplier findByUsrMainId(Integer id);
}
