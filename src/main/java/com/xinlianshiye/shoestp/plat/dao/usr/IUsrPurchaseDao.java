package com.xinlianshiye.shoestp.plat.dao.usr;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.dao.usr.imp.UsrPurchaseDaoImp;

@ImplementedBy(UsrPurchaseDaoImp.class)
public interface IUsrPurchaseDao {

	List<String> getPurchaseEmail();

	Integer count();

}
