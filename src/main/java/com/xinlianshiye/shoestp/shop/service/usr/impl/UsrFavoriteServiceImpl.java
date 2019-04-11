package com.xinlianshiye.shoestp.shop.service.usr.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.usr.UsrFavoriteService;
import com.xinlianshiye.shoestp.shop.view.usr.FavoritesView;

import irille.Dao.PdtProductDao;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

public class UsrFavoriteServiceImpl implements UsrFavoriteService {

  @Inject private PdtProductDao pdtProductDao;
  @Inject IO2OPdtServer IO2OPdtServer;


  @Override
  public Page<FavoritesView> page(
      UsrPurchase purchase, Integer categoryPkey, Boolean showState, Integer start, Integer limit) {
    BeanQuery<PdtProduct> query =
        Query.SELECT(
                UsrFavorites.T.PKEY,
                UsrFavorites.T.PRODUCT,
                PdtProduct.T.PRODUCT_TYPE,
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.MIN_OQ,
                PdtProduct.T.CUR_PRICE)
            .FROM(PdtProduct.class)
            .LEFT_JOIN(UsrFavorites.class, UsrFavorites.T.PRODUCT, PdtProduct.T.PKEY)
            .WHERE(UsrFavorites.T.PURCHASE, "=?", purchase.getPkey())
            .WHERE(UsrFavorites.T.SHOW_STATE, "=?", showState);
    if (categoryPkey != null) {
      List<Integer> catsNodeByCatId = pdtProductDao.getCatsNodeByCatId(categoryPkey);
      query.WHERE(
          PdtProduct.T.CATEGORY,
          SQL.getInSql(catsNodeByCatId.size()),
          catsNodeByCatId.toArray(new Serializable[catsNodeByCatId.size()]));
    }
    Integer totalCount = query.queryCount();
    query.limit(start, limit);
    List<FavoritesView> result =
        query.queryMaps().stream()
            .map(
                map -> {
                  FavoritesView view = SetBeans.set(map, FavoritesView.class);
                  PdtProduct product = new PdtProduct();
                  product.setPkey(GetValue.get(map, PdtProduct.T.PKEY, Integer.class, 0));
                  view.setIsO2O(IO2OPdtServer.judgeO2o(product) ? 1 : 0);
                  return view;
                })
            .collect(Collectors.toList());
    return new Page<FavoritesView>(result, start, limit, totalCount);
  }

  @Override
  public Page<FavoritesView> getMyFavoritesList(
      int start, int limit, int cat, UsrPurchase purchase) {
    BeanQuery<PdtProduct> query =
        Query.SELECT(
                UsrFavorites.T.PKEY,
                UsrFavorites.T.PRODUCT,
                PdtProduct.T.PRODUCT_TYPE,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE,
                PdtProduct.T.MIN_OQ)
            .FROM(PdtProduct.class)
            .LEFT_JOIN(UsrFavorites.class, UsrFavorites.T.PRODUCT, PdtProduct.T.PKEY)
            .WHERE(UsrFavorites.T.PURCHASE, "=?", purchase.getPkey());
    if (cat > 0) {
      List<Integer> catsNodeByCatId = pdtProductDao.getCatsNodeByCatId(cat);
      query.WHERE(
          PdtProduct.T.CATEGORY,
          SQL.getInSql(catsNodeByCatId.size()),
          catsNodeByCatId.toArray(new Serializable[catsNodeByCatId.size()]));
      query.WHERE(UsrFavorites.T.SHOW_STATE, "=?", Sys.OYn.YES);
    } else {
      if (cat < 0) {
        query.WHERE(UsrFavorites.T.SHOW_STATE, "=?", Sys.OYn.NO);
      } else {
        query.WHERE(UsrFavorites.T.SHOW_STATE, "=?", Sys.OYn.YES);
      }
    }
    Integer totalCount = query.queryCount();
    query.limit(start, limit);
    List<FavoritesView> result =
        query.queryMaps().stream()
            .map(
                map -> {
                  FavoritesView view = SetBeans.set(map, FavoritesView.class);
                  return view;
                })
            .collect(Collectors.toList());
    return new Page<FavoritesView>(result, start, limit, totalCount);
  }
}
