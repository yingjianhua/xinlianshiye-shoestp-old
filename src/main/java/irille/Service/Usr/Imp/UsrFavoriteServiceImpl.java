package irille.Service.Usr.Imp;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import irille.Dao.PdtProductDao;
import irille.Service.Usr.UsrFavoriteService;
import irille.homeAction.usr.dto.FavoritesView;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;

public class UsrFavoriteServiceImpl implements UsrFavoriteService {
	
	@Inject
	private PdtProductDao pdtProductDao;

	@Override
	public List<FavoritesView> page(UsrPurchase purchase, Integer categoryPkey, Boolean showState, Integer start, Integer limit) {
		List<Integer> catsNodeByCatId = pdtProductDao.getCatsNodeByCatId(categoryPkey);
		
		return Query.SELECT(
				UsrFavorites.T.PKEY, 
				UsrFavorites.T.PRODUCT, 
				PdtProduct.T.PRODUCT_TYPE,
		        PdtProduct.T.NAME,
		        PdtProduct.T.PICTURE, 
		        PdtProduct.T.CUR_PRICE)
		.FROM(PdtProduct.class)
		.LEFT_JOIN(UsrFavorites.class, UsrFavorites.T.PRODUCT, PdtProduct.T.PKEY)
		.WHERE(UsrFavorites.T.PURCHASE, "=?", purchase.getPkey())
		.WHERE(UsrFavorites.T.SHOW_STATE, "=?", showState)
		.WHERE(PdtProduct.T.CATEGORY, SQL.getInSql(catsNodeByCatId.size()), catsNodeByCatId.toArray(new Serializable[catsNodeByCatId.size()]))
		.limit(start, limit)
		.queryMaps().stream().map(map->{
			FavoritesView view = SetBeans.set(map, FavoritesView.class);
			return view;
		}).collect(Collectors.toList());
	}
}
