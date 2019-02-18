package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCountryView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCurrencyView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQSupplierView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.util.GetValue;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class RFQConsultServiceImpl implements RFQConsultService {

	@Override
	public Page<RFQConsultView> pageMine(UsrPurchase purchase, Byte type, String keyword, Boolean unread, Integer start, Integer limit) {
		BeanQuery<?> query = Query.SELECT(RFQConsult.T.PKEY);
		query.SELECT(RFQConsult.T.TYPE);
		query.SELECT(RFQConsult.T.TITLE);
		query.SELECT(RFQConsult.T.IMAGE);
		query.SELECT(RFQConsult.T.TYPE);
		query.FROM(RFQConsult.class);
		query.LEFT_JOIN(RFQConsultRelation.class, RFQConsult.T.PKEY, RFQConsultRelation.T.CONSULT);
		if(keyword != null && !keyword.isEmpty()) {
			query.LEFT_JOIN(UsrSupplier.class, RFQConsultRelation.T.SUPPLIER_ID, UsrSupplier.T.PKEY);
			//关键字匹配询盘标题和报价供应商名称
			query
			.WHERE(RFQConsult.T.TITLE, "like ?", "%"+keyword+"%")
			.OR()
			.WHERE(UsrSupplier.T.NAME, "like ?", "%"+keyword+"%");
		}
		if(type != null) {
			//询盘类型 
			query.WHERE(RFQConsult.T.TYPE, "=?", type);
		}
		if(unread != null) {
			//是否有新消息
			query.WHERE(RFQConsultRelation.T.IS_NEW, "=?", unread);
		}
		query.WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.GROUP_BY(RFQConsult.T.PKEY);
		query.limit(start, limit);
		List<RFQConsultView> result = query.queryMaps().stream().map(map->{
			RFQConsultView consult = new RFQConsultView();
			consult.setPkey(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, null));
			consult.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
			consult.setType(GetValue.get(map, RFQConsult.T.TYPE, Byte.class, null));
			consult.setImages(Arrays.asList(GetValue.get(map, RFQConsult.T.IMAGE, String.class, "").split(",")));
			consult.setRelations(listRelation(consult.getPkey()));
			return consult;
		}).collect(Collectors.toList());
		Integer totalCount = query.queryCount();
		return new Page<>(result, start, limit, totalCount);
	}
	
	@Override
	public List<RFQConsultRelationView> listRelation(Integer consultPkey) {
		BeanQuery<?> query = Query.SELECT(RFQConsultRelation.T.PKEY);
		query.SELECT(RFQConsultRelation.T.TITLE);
		query.SELECT(RFQConsultRelation.T.IMAGE);
		query.SELECT(RFQConsultRelation.T.MINPRICE);
		query.SELECT(RFQConsultRelation.T.MAXPRICE);
		query.SELECT(RFQConsultRelation.T.CURRENCY);
		query.SELECT(RFQConsultRelation.T.SAMPLE);
		query.SELECT(RFQConsultRelation.T.CREATE_DATE);
		query.SELECT(RFQConsultRelation.T.IS_NEW);
		query.SELECT(UsrSupplier.T.PKEY, "supplierPkey");
		query.SELECT(UsrSupplier.T.NAME, "supplierName");
		query.SELECT(UsrSupplier.T.COUNTRY, "supplierCountry");
		query.SELECT(PltCountry.T.SHORT_NAME, "countryShortName");
		query.SELECT(PltCountry.T.NATIONAL_FLAG, "countryFlag");
		query.SELECT(PltErate.T.PKEY, "currencyPkey");
		query.SELECT(PltErate.T.CUR_NAME, "currencyShortName");
		query.FROM(RFQConsultRelation.class);
		query.LEFT_JOIN(UsrSupplier.class, RFQConsultRelation.T.SUPPLIER_ID, UsrSupplier.T.PKEY);
		query.LEFT_JOIN(PltCountry.class, UsrSupplier.T.COUNTRY, PltCountry.T.PKEY);
		query.LEFT_JOIN(PltErate.class, RFQConsultRelation.T.CURRENCY, PltErate.T.PKEY);
		query.WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey);
		query.WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false);
		
		List<RFQConsultRelationView> result = query.queryMaps().stream().map(map->{
			RFQConsultRelationView relation = new RFQConsultRelationView();
			RFQQuotationView quotation = new RFQQuotationView();
			quotation.setPkey(GetValue.get(map, RFQConsultRelation.T.PKEY, Integer.class, null));
			quotation.setTitle(GetValue.get(map, RFQConsultRelation.T.TITLE, String.class, null));
			quotation.setDescription(GetValue.get(map, RFQConsultRelation.T.DESCRIPTION, String.class, null));
			quotation.setImages(Arrays.asList(GetValue.get(map, RFQConsultRelation.T.IMAGE, String.class, "").split(",")));
			quotation.setMinPrice(GetValue.get(map, RFQConsultRelation.T.MINPRICE, Integer.class, null));
			quotation.setMaxPrice(GetValue.get(map, RFQConsultRelation.T.MAXPRICE, Integer.class, null));
			RFQCurrencyView currency = new RFQCurrencyView();
			currency.setPkey(GetValue.get(map, "currencyPkey", Integer.class, null));
			currency.setShortName(GetValue.get(map, "currencyShortName", String.class, null));
			quotation.setCurrency(currency);
			quotation.setCreateDate(GetValue.get(map, RFQConsultRelation.T.CREATE_DATE, Date.class, null));
			quotation.setSample(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.SAMPLE, Byte.class, null)));
			quotation.setIsNew(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.IS_NEW, Byte.class, null)));
			RFQSupplierView supplier = new RFQSupplierView();
			supplier.setPkey(GetValue.get(map, "supplierPkey", Integer.class, null));
			supplier.setName(GetValue.get(map, "supplierName", String.class, null));
			RFQCountryView country = new RFQCountryView();
			country.setPkey(GetValue.get(map, "supplierCountry", Integer.class, null));
			country.setShortName(GetValue.get(map, "countryShortName", String.class, null));
			country.setFlag(GetValue.get(map, "countryFlag", String.class, null));
			supplier.setCountry(country);
			relation.setQuotation(quotation);
			relation.setSupplier(supplier);
			return relation;
		}).collect(Collectors.toList());
		return result;
	}
	
}
