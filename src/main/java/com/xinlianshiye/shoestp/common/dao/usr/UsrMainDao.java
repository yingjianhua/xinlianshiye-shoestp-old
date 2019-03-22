package com.xinlianshiye.shoestp.common.dao.usr;

import java.util.Optional;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.common.dao.usr.impl.UsrMainDaoImpl;

import irille.shop.usr.UsrMain;

/** 
 * @author Jianhua Ying
 * @date 2019年3月22日 
 *
 */
@ImplementedBy(UsrMainDaoImpl.class)
public interface UsrMainDao {
	
	void save(UsrMain main);
	
	Optional<UsrMain> findByEmail(String email);
}
