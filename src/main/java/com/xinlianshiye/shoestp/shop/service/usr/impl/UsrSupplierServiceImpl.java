package com.xinlianshiye.shoestp.shop.service.usr.impl;

import org.json.JSONException;

import com.xinlianshiye.shoestp.shop.service.usr.UsrSupplierService;
import com.xinlianshiye.shoestp.shop.view.usr.SupplierView;

import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;

public class UsrSupplierServiceImpl implements UsrSupplierService {

	@Override
	public SupplierView detail(UsrPurchase purchase, Integer supplierPkey, Language lang) throws JSONException {
		BeanQuery<UsrSupplier> query = Query.selectFrom(UsrSupplier.class);
		query.WHERE(UsrSupplier.T.PKEY, "=?", supplierPkey);
		UsrSupplier supplier = query.query();
		
		SupplierView view = new SupplierView();
		view.setPkey(supplier.getPkey());
		view.setName(supplier.getShowName());
		view.setLogo(supplier.getLogo());
		PltCountry country = supplier.gtCountry();
		if(country != null) {
			view.setCountry(country.getName(lang));
			view.setCountryFlag(country.getNationalFlag());
		}
		view.setBusinessType(supplier.getBusinessTyp(lang));
		view.setYearEstablished(supplier.getCompanyEstablishTime());
		view.setMainProducts(supplier.getMainProd(lang));
		view.setLocation(supplier.getLocation(lang));
		view.setEmployeeCount(supplier.getTotalEmployees(lang));
		view.setAnnualRevenue(supplier.getAnnualSales(lang));
		view.setMainMarket(supplier.getMainSalesArea(lang));
		view.setTransactionCount("");
		view.setTransactionAmount("");
		view.setMemberSince(supplier.getApprTime());
		view.setDepartment(supplier.getDepartment(lang));
		view.setJobTitle(supplier.getJobTitle(lang));
		view.setCompany(supplier.getName());
		view.setContractManufacturing("");
		view.setOverseasOffice("");
		view.setCompanyCertification("");
		return view;
	}

}
