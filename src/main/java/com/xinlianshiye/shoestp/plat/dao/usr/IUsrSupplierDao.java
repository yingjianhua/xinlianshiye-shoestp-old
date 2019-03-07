package com.xinlianshiye.shoestp.plat.dao.usr;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.dao.usr.imp.UsrSupplierDaoImp;
@ImplementedBy(UsrSupplierDaoImp.class)
public interface IUsrSupplierDao {

	List<String> getSupplierEmail();

	Integer count();

}
