package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCountryView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCurrencyView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationThrowawayView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQSupplierView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultStatus;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetValue;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.RFQ.PutRFQConsultView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RFQConsultServiceImpl implements RFQConsultService {
	
	@Inject
	private ObjectMapper om;

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
	
	@Override
	public RFQQuotationView getQuotation(UsrPurchase purchase, Integer relationPkey) {
		BeanQuery<RFQConsultRelation> query = Query
				.selectFrom(RFQConsultRelation.class)
				.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey())
				.WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
		RFQConsultRelation relation = query.query();
		RFQQuotationView quotation = new RFQQuotationView();
		quotation.setPkey(relation.getPkey());
		quotation.setTitle(relation.getTitle());
		quotation.setImages(Arrays.asList((relation.getImage()==null?"":relation.getImage()).split(",")));
		quotation.setDescription(relation.getDescription());
		quotation.setQuantity(relation.getQuantity());
		quotation.setMinPrice(relation.getMinprice());
		quotation.setMaxPrice(relation.getMaxprice());
		PltErate erate = relation.gtCurrency();
		RFQCurrencyView currency = new RFQCurrencyView();
		currency.setPkey(erate.getPkey());
		currency.setShortName(erate.getCurName());
		quotation.setCurrency(currency);
		quotation.setValidDate(relation.getValidDate());
		quotation.setPaymentTerms(relation.gtPaytype().getLine().getName());
		quotation.setShippingTerms(relation.gtTransittype().getLine().getName());
		quotation.setSample(relation.gtSample());
		quotation.setCompanyProfile(relation.getCompanydescribe());
		quotation.setCreateDate(relation.getCreateDate());
		try {
			quotation.setThrowaways(om.readValue(relation.getThrowaway(), new TypeReference<List<RFQQuotationThrowawayView>>() {}));
		} catch (IOException e) {
			log.warn("RFQConsultRelation表主键为{}的记录 字段throwaway格式错误", relation.getPkey());
		}
		return quotation;
	}

	@Override
	public void deleteQuotation(UsrPurchase purchase, Integer relationPkey) {
		BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
		query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
		RFQConsultRelation relation = query.query();
		if(relation != null && !relation.gtIsDeletedPurchase()) {
			relation.stIsDeletedPurchase(true);
			relation.upd();
		}
	}

	@Override
	public RFQConsultView getDetail(UsrPurchase purchase, Integer consultPkey) {
		BeanQuery<RFQConsult> query = Query.selectFrom(RFQConsult.class);
		query.WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.WHERE(RFQConsult.T.PKEY, "=?", consultPkey);
		RFQConsult consult = query.query();
		if(consult == null) {
			throw new WebMessageException(ReturnCode.service_gone, "询盘不存在");
		}
		RFQConsultView view = new RFQConsultView();
		view.setPkey(consult.getPkey());
		view.setTitle(consult.getTitle());
		view.setType(consult.getType());
		view.setDetail(consult.getContent());
		view.setQuantity(consult.getQuantity());
		view.setPrice(consult.getPrice());
		view.setUnit(consult.gtUnit().getLine().getName());
		view.setType(consult.getType());
		view.setImages(Arrays.asList((consult.getImage()==null?"":consult.getImage()).split(",")));
		return view;
	}

	@Override
	public void addMoreInformation(UsrPurchase purchase, Integer consultPkey, String information, Date validDate) {
		RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
		if(consult == null) {
			throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
		}
		if(consult.gtType() != RFQConsultType.RFQ) {
			//只有RFQ询盘能添加额外信息
			throw new WebMessageException(ReturnCode.service_state_error, "数据错误");
		}
		if(consult.gtStatus() != RFQConsultStatus.ready && consult.gtStatus() != RFQConsultStatus.runing) {
			//只有待发布和进行中能添加额外信息
			throw new WebMessageException(ReturnCode.service_state_error, "状态错误");
		}
		if(consult.getChangeCount() >= 3) {
			//最多修改三次
			throw new WebMessageException(ReturnCode.service_state_error, "最多修改三次");
		}
		if(consult.getValidDate().before(new Date())) {
			//已经过期
			throw new WebMessageException(ReturnCode.service_state_error, "已过期");
		}
		if(Query.selectFrom(RFQConsultRelation.class).WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey).exists()) {
			//已经有报价 不能添加额外信息
			throw new WebMessageException(ReturnCode.service_state_error, "已有报价");
		}
		if(validDate.before(new Date())) {
			throw new WebMessageException(ReturnCode.valid_illegal, "有效时间不合法");
		}
		consult.setExtraDescription(information);
		consult.setValidDate(validDate);
		consult.upd();
	}

	@Override
	public void close(UsrPurchase purchase, Integer consultPkey) {
		RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
		if(consult == null) {
			throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
		}
		if(consult.gtStatus() == RFQConsultStatus.close) {
			throw new WebMessageException(ReturnCode.service_state_error, "已关闭,不能进行操作");
		}
		consult.stStatus(RFQConsultStatus.close);
		consult.upd();
	}
	
}
