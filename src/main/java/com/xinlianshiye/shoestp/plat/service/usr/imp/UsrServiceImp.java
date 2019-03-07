package com.xinlianshiye.shoestp.plat.service.usr.imp;

import java.util.List;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.dao.usr.IUsrPurchaseDao;
import com.xinlianshiye.shoestp.plat.dao.usr.IUsrSupplierDao;
import com.xinlianshiye.shoestp.plat.service.usr.IUsrService;

import irille.Entity.pm.PM.ORCVRType;

public class UsrServiceImp implements IUsrService{
	
	@Inject
	private IUsrSupplierDao supplierDao;
	
	@Inject
	private IUsrPurchaseDao purchaseDao;
	
	@Override
	public List<String> getMailsBy(ORCVRType type){
		List<String> mails = null;
		if(type.equals(ORCVRType.SUPPLIER)) {
			mails = supplierDao.getSupplierEmail();
		}else if(type.equals(ORCVRType.PURCHASE)) {
			mails = purchaseDao.getPurchaseEmail();
		}
		return mails;
	}
	
	@Override
	public Integer count(ORCVRType type) {
		if(type.equals(ORCVRType.SUPPLIER)) {
			return supplierDao.count();
		}else if(type.equals(ORCVRType.PURCHASE)) {
			return purchaseDao.count();
		}else {
			return 0;
		}
	}
}
