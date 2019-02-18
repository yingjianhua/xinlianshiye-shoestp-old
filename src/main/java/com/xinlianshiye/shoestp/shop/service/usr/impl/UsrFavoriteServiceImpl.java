package com.xinlianshiye.shoestp.shop.service.usr.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.usr.UsrFavoriteService;
import com.xinlianshiye.shoestp.shop.view.usr.FavoritesView;

import irille.Dao.PdtProductDao;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

public class UsrFavoriteServiceImpl implements UsrFavoriteService {
	
	@Inject
	private PdtProductDao pdtProductDao;

	@Override
	public Page<FavoritesView> page(UsrPurchase purchase, Integer categoryPkey, Boolean showState, Integer start, Integer limit) {
		BeanQuery<PdtProduct> query = Query.SELECT(
				UsrFavorites.T.PKEY, 
				UsrFavorites.T.PRODUCT, 
				PdtProduct.T.PRODUCT_TYPE,
		        PdtProduct.T.NAME,
		        PdtProduct.T.PICTURE, 
		        PdtProduct.T.CUR_PRICE)
		.FROM(PdtProduct.class)
		.LEFT_JOIN(UsrFavorites.class, UsrFavorites.T.PRODUCT, PdtProduct.T.PKEY)
		.WHERE(UsrFavorites.T.PURCHASE, "=?", purchase.getPkey())
		.WHERE(UsrFavorites.T.SHOW_STATE, "=?", showState);
		if(categoryPkey != null) {
			List<Integer> catsNodeByCatId = pdtProductDao.getCatsNodeByCatId(categoryPkey);
			query.WHERE(PdtProduct.T.CATEGORY, SQL.getInSql(catsNodeByCatId.size()), catsNodeByCatId.toArray(new Serializable[catsNodeByCatId.size()]));
		}
		Integer totalCount = query.queryCount();
		query.limit(start, limit);
		List<FavoritesView> result = query.queryMaps().stream().map(map->{
			FavoritesView view = SetBeans.set(map, FavoritesView.class);
			return view;
		}).collect(Collectors.toList());
		return new Page<FavoritesView>(result, start, limit, totalCount);
	}
}