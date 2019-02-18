package com.xinlianshiye.shoestp.shop.service.usr;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.usr.impl.UsrFavoriteServiceImpl;
import com.xinlianshiye.shoestp.shop.view.usr.FavoritesView;

import irille.shop.usr.UsrPurchase;
import irille.view.Page;

@ImplementedBy(UsrFavoriteServiceImpl.class)
public interface UsrFavoriteService {

	/**
	 * 分页查询我的收藏
	 * 
	 * @param purchase 采购商
	 * @param categoryPkey 产品分类主键
	 * @param showState 显示状态
	 * @param start 开始记录数
	 * @param limit 每页记录数
	 * @return
	 * @author Jianhua Ying
	 */
	Page<FavoritesView> page(UsrPurchase purchase, Integer categoryPkey, Boolean showState, Integer start, Integer limit);
}
