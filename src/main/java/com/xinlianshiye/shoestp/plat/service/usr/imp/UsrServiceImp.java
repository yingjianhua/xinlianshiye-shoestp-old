package com.xinlianshiye.shoestp.plat.service.usr.imp;

import java.util.List;

import com.xinlianshiye.shoestp.plat.dao.usr.IUsrPurchaseDao;
import com.xinlianshiye.shoestp.plat.dao.usr.IUsrSupplierDao;
import com.xinlianshiye.shoestp.plat.dao.usr.imp.UsrPurchaseDaoImp;
import com.xinlianshiye.shoestp.plat.dao.usr.imp.UsrSupplierDaoImp;
import com.xinlianshiye.shoestp.plat.service.usr.IUsrService;

import irille.Entity.pm.PM.ORCVRType;

public class UsrServiceImp implements IUsrService{
	
	private IUsrSupplierDao supplierDao = new UsrSupplierDaoImp();
	
	private IUsrPurchaseDao purchaseDao = new UsrPurchaseDaoImp();
	
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
