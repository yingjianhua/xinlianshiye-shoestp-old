package com.xinlianshiye.shoestp.shop.service.usr.impl;

import com.xinlianshiye.shoestp.shop.service.usr.UsrSupplierService;
import com.xinlianshiye.shoestp.shop.view.usr.SupplierView;

import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class UsrSupplierServiceImpl implements UsrSupplierService {

	@Override
	public SupplierView detail(UsrPurchase purchase, Integer supplierPkey) {
		BeanQuery<UsrSupplier> query = Query.selectFrom(UsrSupplier.class);
		query.WHERE(UsrSupplier.T.PKEY, "=?", supplierPkey);
		UsrSupplier supplier = query.query();
		
		SupplierView view = new SupplierView();
		view.setCountry(supplier.gtCountry().getName());
		view.setBusinessType(supplier.getBusinessTyp());
		view.setYearEstablished(supplier.getCompanyEstablishTime());
		view.setMainProducts(supplier.getMainProd());
		view.setLocation(supplier.getLocation());
		view.setEmployeeCount(supplier.getTotalEmployees());
		view.setAnnualRevenue(supplier.getAnnualSales());
		view.setMainMarket(supplier.getMainSalesArea());
//		view.setTransactionCount(supplier.getc);
		Integer country = supplier.getCountry();
		String mainSalesArea = supplier.getMainSalesArea();
		return null;
	}

}
