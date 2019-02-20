package com.xinlianshiye.shoestp.shop.service.usr;

import com.xinlianshiye.shoestp.shop.view.usr.SupplierView;

import irille.shop.usr.UsrPurchase;

public interface UsrSupplierService {

	SupplierView detail(UsrPurchase purchase, Integer supplierPkey);
}
