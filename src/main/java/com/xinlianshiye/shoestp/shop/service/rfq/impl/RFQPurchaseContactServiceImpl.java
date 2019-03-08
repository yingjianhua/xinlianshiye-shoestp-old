package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactGroupView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQSupplierView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.Entity.RFQ.RFQPurchaseContactGroup;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetValue;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class RFQPurchaseContactServiceImpl implements RFQPurchaseContactService {

	@Override
	public Page<RFQPurchaseContactView> page(UsrPurchase purchase, String keyword, Integer groupPkey, Integer start, Integer limit) {
		BeanQuery<?> query = Query.SELECT(RFQPurchaseContact.T.PKEY);
		query.SELECT(RFQPurchaseContact.T.CREATED_TIME);
		query.SELECT(UsrSupplier.T.PKEY, "supplierPkey");
		query.SELECT(UsrSupplier.T.NAME);
		query.SELECT(UsrSupplier.T.LOGO);
		//TODO svs 认证信息待完善 
		query.SELECT(UsrSupplier.T.CONTACTS);
		query.FROM(RFQPurchaseContact.class);
		query.LEFT_JOIN(UsrSupplier.class, RFQPurchaseContact.T.SUPPLIER, UsrSupplier.T.PKEY);
		query.LEFT_JOIN(RFQConsultRelation.class, RFQPurchaseContact.T.SUPPLIER, RFQConsultRelation.T.SUPPLIER_ID);
		query.LEFT_JOIN(RFQConsult.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY);
		query.WHERE(RFQPurchaseContact.T.PURCHASE, "=?", purchase.getPkey());
		if(groupPkey != null)
			query.WHERE(RFQPurchaseContact.T.CONTACT_GROUP, "=?", groupPkey);
		if(keyword != null && !keyword.isEmpty()) {
			query.AND();
			query.WHERE(UsrSupplier.T.NAME, "like ?", "%"+keyword+"%");
			query.or();
			query.WHERE(RFQConsult.T.TITLE, "like ?", "%"+keyword+"%");
		}
		
		query.GROUP_BY(RFQPurchaseContact.T.PKEY);
		query.limit(start, limit);
		List<RFQPurchaseContactView> result = query.queryMaps().stream().map(map -> {
			RFQPurchaseContactView contact = new RFQPurchaseContactView();
			contact.setPkey(GetValue.get(map, RFQPurchaseContact.T.PKEY, Integer.class, null));
			contact.setCreatedDate(GetValue.get(map, RFQPurchaseContact.T.CREATED_TIME, Date.class, null));
			RFQSupplierView supplier = new RFQSupplierView();
			supplier.setPkey(GetValue.get(map, "supplierPkey", Integer.class, null));
			supplier.setContacts(GetValue.get(map, UsrSupplier.T.CONTACTS, String.class, null));
			supplier.setName(GetValue.get(map, UsrSupplier.T.NAME, String.class, ""));
			supplier.setLogo(GetValue.get(map, UsrSupplier.T.LOGO, String.class, ""));
			contact.setSupplier(supplier);
			contact.setRelation(listConsultRelation(purchase.getPkey(), supplier.getPkey(), (keyword == null || supplier.getName().contains(keyword))?null:keyword));
			return contact;
		}).collect(Collectors.toList());
		Integer totalCount = query.queryCount();
		return new Page<>(result, start, limit, totalCount);
	}
	
	/**
	 * 查询采购商和供应商之间的询盘记录
	 * @param purchasePkey 采购商主键
	 * @param supplierPkey 供应商主键
	 * @author Jianhua Ying
	 */
	public List<RFQConsultRelationView> listConsultRelation(Integer purchasePkey, Integer supplierPkey, String keyword) {
		BeanQuery<?> query = Query.SELECT(RFQConsultRelation.T.PKEY);
		query.SELECT(RFQConsultRelation.T.IS_NEW);
		query.SELECT(RFQConsultRelation.T.CREATE_DATE);
		query.SELECT(RFQConsult.T.PKEY, "consultPkey");
		query.SELECT(RFQConsult.T.TITLE);
		query.SELECT(RFQConsult.T.IMAGE);
		query.SELECT(RFQConsult.T.TYPE);
		query.FROM(RFQConsultRelation.class);
		query.LEFT_JOIN(RFQConsult.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY);
		query.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey);
		query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchasePkey);
		query.WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false);
		if(keyword != null)
			query.WHERE(RFQConsult.T.TITLE, "like ?", "%"+keyword+"%");
		List<RFQConsultRelationView> result = query.queryMaps().stream().map(map->{
			RFQConsultRelationView relation = new RFQConsultRelationView();
			RFQQuotationView quotation = new RFQQuotationView();
			quotation.setPkey(GetValue.get(map, RFQConsultRelation.T.PKEY, Integer.class, null));
			quotation.setIsNew(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.IS_NEW, Byte.class, null)));
			quotation.setCreateDate(GetValue.get(map, RFQConsultRelation.T.CREATE_DATE, Date.class, null));
			relation.setQuotation(quotation);
			RFQConsultView consult = new RFQConsultView();
			consult.setPkey(GetValue.get(map, "consultPkey", Integer.class, null));
			consult.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, ""));
			consult.setType(GetValue.get(map, RFQConsult.T.TYPE, Byte.class, null));
			consult.setImages(Arrays.asList(GetValue.get(map, RFQConsult.T.IMAGE, String.class, "").split(",")));
			relation.setConsult(consult);
			return relation;
		}).collect(Collectors.toList());
		return result;
	}

	@Override
	public void add(UsrPurchase purchase, Integer supplierPkey) {
		BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
		query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey);

		BeanQuery<RFQPurchaseContact> query2 = Query.selectFrom(RFQPurchaseContact.class);
		query2.WHERE(RFQPurchaseContact.T.PURCHASE, "=?", purchase.getPkey());
		query2.WHERE(RFQPurchaseContact.T.SUPPLIER, "=?", supplierPkey);

		if (query.exists() && !query2.exists()) {
			// 只有建立了询盘关系 才能添加联系人
			RFQPurchaseContact contact = new RFQPurchaseContact();
			contact.setPurchase(purchase.getPkey());
			contact.setSupplier(supplierPkey);
			contact.setCreatedTime(new Date());
			contact.ins();
		}
	}

	@Override
	public void delete(UsrPurchase purchase, Integer supplierPkey) {
		BeanQuery<RFQPurchaseContact> query = Query.selectFrom(RFQPurchaseContact.class);
		query.WHERE(RFQPurchaseContact.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContact.T.SUPPLIER, "=?", supplierPkey);
		RFQPurchaseContact contact = query.query();
		if (contact != null)
			contact.del();
	}

	@Override
	public void addGroup(UsrPurchase purchase, String groupName) {
		if(groupName == null || groupName.isEmpty()) {
			throw new WebMessageException(ReturnCode.valid_notempty, "请输入分组名字");
		}
		BeanQuery<RFQPurchaseContactGroup> query = Query.selectFrom(RFQPurchaseContactGroup.class);
		query.WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContactGroup.T.NAME, "=?", groupName);
		if(query.exists()) {
			throw new WebMessageException(ReturnCode.valid_notempty, "请重新填写分组名字");
		}
		RFQPurchaseContactGroup group = new RFQPurchaseContactGroup();
		group.setName(groupName);
		group.setPurchase(purchase.getPkey());
		group.setCreatedTime(new Date());
		group.ins();
	}

	@Override
	public void deleteGroup(UsrPurchase purchase, Integer groupPkey) {
		BeanQuery<RFQPurchaseContactGroup> query = Query.selectFrom(RFQPurchaseContactGroup.class);
		query.WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContactGroup.T.PKEY, "=?", groupPkey);
		RFQPurchaseContactGroup group = query.query();
		if(group != null) {
			group.del();
			Query.UPDATE(RFQPurchaseContact.class)
			.SET(RFQPurchaseContact.T.CONTACT_GROUP, null)
			.WHERE(RFQPurchaseContact.T.CONTACT_GROUP, "=?", groupPkey)
			.executeUpdate();
		}
	}

	@Override
	public void editGroup(UsrPurchase purchase, Integer groupPkey, String groupName) {
		if(groupName == null || groupName.isEmpty()) {
			throw new WebMessageException(ReturnCode.valid_notempty, "请输入分组名字");
		}
		BeanQuery<RFQPurchaseContactGroup> query = Query.selectFrom(RFQPurchaseContactGroup.class);
		query.WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContactGroup.T.NAME, "=?", groupName);
		if(query.exists()) {
			throw new WebMessageException(ReturnCode.valid_notempty, "请重新填写分组名字");
		}
		BeanQuery<RFQPurchaseContactGroup> query2 = Query.selectFrom(RFQPurchaseContactGroup.class);
		query2.WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey());
		query2.WHERE(RFQPurchaseContactGroup.T.PKEY, "=?", groupPkey);
		RFQPurchaseContactGroup contact = query2.query();
		contact.setName(groupName);
		contact.upd();
	}

	@Override
	public void moveToGroup(UsrPurchase purchase, Integer contactPkey, Integer groupPkey) {
		BeanQuery<RFQPurchaseContactGroup> query = Query.selectFrom(RFQPurchaseContactGroup.class);
		query.WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContactGroup.T.PKEY, "=?", groupPkey);
		RFQPurchaseContactGroup group = query.query();
		if(group == null) {
			throw new WebMessageException(ReturnCode.service_gone, "请重新选择分组");
		}
		BeanQuery<RFQPurchaseContact> query2 = Query.selectFrom(RFQPurchaseContact.class);
		query2.WHERE(RFQPurchaseContact.T.PURCHASE, "=?", purchase.getPkey());
		query2.WHERE(RFQPurchaseContact.T.PKEY, "=?", contactPkey);
		RFQPurchaseContact contact = query2.query();
		if(contact == null) {
			throw new WebMessageException(ReturnCode.service_gone, "请重新选择联系人");
		}
		if(group.getPkey().equals(contact.getContactGroup())) {
			throw new WebMessageException(ReturnCode.service_wrong_data, "该联系人已在" + group.getName());
		}
		contact.setContactGroup(group.getPkey());
		contact.upd();
	}

	@Override
	public List<RFQPurchaseContactGroupView> listGroup(UsrPurchase purchase) {
		List<RFQPurchaseContactGroup> list = Query.selectFrom(RFQPurchaseContactGroup.class).WHERE(RFQPurchaseContactGroup.T.PURCHASE, "=?", purchase.getPkey()).queryList();
		RFQPurchaseContactGroup nogroup = new RFQPurchaseContactGroup();
		nogroup.setName("nogroup");
		list.add(nogroup);
		RFQPurchaseContactGroupView all = new RFQPurchaseContactGroupView();
		all.setName("all");
		all.setCount(0);
		List<RFQPurchaseContactGroupView> result = list.stream().map(map->{
			RFQPurchaseContactGroupView view = new RFQPurchaseContactGroupView();
			view.setPkey(map.getPkey());
			view.setName(map.getName());
			BeanQuery<RFQPurchaseContact> query = Query.SELECT(RFQPurchaseContact.class);
			view.setCount(map.getPkey() == null ? query.WHERE(RFQPurchaseContact.T.CONTACT_GROUP, "is null").queryCount() : query.WHERE(RFQPurchaseContact.T.CONTACT_GROUP, "=?", map.getPkey()).queryCount());
			all.setCount(all.getCount() + view.getCount());
			return view;
		}).collect(Collectors.toList());
		result.add(all);
		return result;
	}
	
}
