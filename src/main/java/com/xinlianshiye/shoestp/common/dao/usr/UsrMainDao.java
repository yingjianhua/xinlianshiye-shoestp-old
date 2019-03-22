package com.xinlianshiye.shoestp.common.dao.usr;

import java.util.Optional;

import irille.shop.usr.UsrMain;

/** 
 * @author Jianhua Ying
 * @date 2019年3月22日 
 *
 */
public interface UsrMainDao {
	
	void save(UsrMain main);
	
	Optional<UsrMain> findByEmail(String email);
}
