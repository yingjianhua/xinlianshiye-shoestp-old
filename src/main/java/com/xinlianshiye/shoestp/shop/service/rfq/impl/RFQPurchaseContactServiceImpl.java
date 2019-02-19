package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.util.Date;

import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;

import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
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

}
