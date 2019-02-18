package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.util.Date;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;

import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.Entity.RFQ.RFQPurchaseContactGroup;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;

public class RFQPurchaseContactServiceImpl implements RFQPurchaseContactService {

	@Override
	public void add(UsrPurchase purchase, Integer supplierPkey) {
		BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
		query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
		query.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey);

		BeanQuery<RFQPurchaseContact> query2 = Query.selectFrom(RFQPurchaseContact.class);
		query.WHERE(RFQPurchaseContact.T.PURCHASE, "=?", purchase.getPkey());
		query.WHERE(RFQPurchaseContact.T.SUPPLIER, "=?", supplierPkey);

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
		contact.setContactGroup(group.getPkey());
		contact.upd();
	}

}
