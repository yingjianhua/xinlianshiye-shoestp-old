package com.xinlianshiye.shoestp.shop.service.usr;

import org.json.JSONException;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.usr.impl.UsrSupplierServiceImpl;
import com.xinlianshiye.shoestp.shop.view.usr.SupplierView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;

@ImplementedBy(UsrSupplierServiceImpl.class)
public interface UsrSupplierService {

	SupplierView detail(UsrPurchase purchase, Integer supplierPkey, Language lang) throws JSONException;
}
