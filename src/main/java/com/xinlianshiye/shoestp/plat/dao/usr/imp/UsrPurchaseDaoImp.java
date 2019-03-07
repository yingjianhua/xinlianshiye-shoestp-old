package com.xinlianshiye.shoestp.plat.dao.usr.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.plat.dao.usr.IUsrPurchaseDao;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseDaoImp implements IUsrPurchaseDao{
	
	@Override
	public List<String> getPurchaseEmail(){
		SQL sql = new SQL();
		sql.SELECT(UsrPurchase.T.EMAIL)
			.FROM(UsrPurchase.class);
		
		return Query.sql(sql).queryMaps().stream().map(mail->{
			return GetValue.get(mail, UsrPurchase.T.EMAIL, String.class, null);
		}).filter(mail->{
			if(null == mail && mail.trim().equals("")) {
				return false;
			}else {
				return true;
			}
		}).collect(Collectors.toList());
	}

	@Override
	public Integer count() {
		SQL sql = new SQL();
		sql.SELECT(UsrPurchase.class)
			.FROM(UsrPurchase.class);
		return Query.sql(sql).queryCount();
	}
}
