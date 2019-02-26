package irille.Service.Usr.Imp;

import irille.Dao.PdtProductDao;
import irille.Dao.UsrSupplierDao;
import irille.Service.Usr.IUsrSupplierService;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.GetValue;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.v3.Pdt.PdtProductView;
import irille.view.v3.usr.UsrSupplierView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

import com.xinlianshiye.shoestp.shop.view.usr.FavoritesView;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 16:25
 */
public class UsrSupplierServiceImp implements IUsrSupplierService {

  @Inject
  UsrSupplierDao usrSupplierDao;
  @Inject
  PdtProductDao pdtProductDao;

  @Override
  public List<FavoritesView> getFavoritesListByCat(IduPage page, int cat, int purId,
      Sys.OYn showState) {
    FormaterSql sql = FormaterSql.build(this);
    sql.select(UsrFavorites.T.PKEY, UsrFavorites.T.PRODUCT, irille.shop.pdt.PdtProduct.T.PRODUCT_TYPE,
        irille.shop.pdt.PdtProduct.T.NAME,
        irille.shop.pdt.PdtProduct.T.PICTURE, irille.shop.pdt.PdtProduct.T.CUR_PRICE

    ).eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
      if (s == null) {
        return false;
      }
      return s.intValue() > 0 ? true : false;
    }).page(page).leftjoin(irille.shop.pdt.PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
        .eqAutoAnd(UsrFavorites.T.SHOW_STATE,
            showState.getLine().getKey());
    sql.in(irille.shop.pdt.PdtProduct.T.CATEGORY, pdtProductDao.getCatsNodeByCatId(cat), s -> {
      if (s == null) {
        return false;
      }
      return s.size() > 0 ? true : false;
    });
    List result = new ArrayList();
    List list = sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms()));
    sql.clean().select(PrmGroupPurchaseLine.T.PKEY).eq(PrmGroupPurchaseLine.T.PRODUCT);
    list.forEach(s -> {
      FavoritesView view = SetBeans.set(s, FavoritesView.class);
      if (view.getGroupLine() != null && view.getGroupLine() == 1) {
        view.setGroupLine(
            sql.castInt(BeanBase.queryOneRowIsNull(sql.buildSql(), view.getPdtPkey())));
      }
      result.add(view);
    });
    return result;
  }

  @Override
  public Long getFavoritesCountByCat(IduPage page, int cat, int purId, Sys.OYn showState) {
    FormaterSql sql = FormaterSql.build(this);
    sql.select(UsrFavorites.T.PKEY, UsrFavorites.T.PRODUCT, irille.shop.pdt.PdtProduct.T.NAME, irille.shop.pdt.PdtProduct.T.PICTURE,
        irille.shop.pdt.PdtProduct.T.CUR_PRICE

    ).eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
      if (s == null) {
        return false;
      }
      return s.intValue() > 0 ? true : false;
    }).page(page).leftjoin(irille.shop.pdt.PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
        .eqAutoAnd(UsrFavorites.T.SHOW_STATE,
            showState.getLine().getKey());
    if (cat > 0) {
      sql.in(irille.shop.pdt.PdtProduct.T.CATEGORY, pdtProductDao.getCatsNodeByCatId(cat), s -> {
        if (s == null) {
          return false;
        }
        return s.size() > 0 ? true : false;
      });
    }
    return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms()));
  }

  @Override
  public List<SupplierListView> getSupplierListAndPdtList(IduPage iduPage,
      FldLanguage.Language language) {
    List<SupplierListView> list = usrSupplierDao
        .getSupplierListAndPdtList(iduPage.getStart(), iduPage.getLimit(), iduPage.getWhere());
    return list.stream().map(supplierListView -> {
      supplierListView
          .setProdpattern(translateUtil.getLanguage(supplierListView.getProdpattern(), language));
      return supplierListView;
    }).collect(Collectors.toList());
  }

  @Override
  public List getSupplierInfo(IduPage page) {
    return usrSupplierDao.getSupplier(page.getStart(), page.getLimit());
  }

  @Override
  public Page listSupplier(Integer start , Integer limit) {
    Page page = null;
    List<UsrSupplierView> supplierViews = new ArrayList<>();
    List<Map> usrSuppliers = usrSupplierDao.listSuppliers(start , limit);
    for (Map map : usrSuppliers) {
      UsrSupplierView supplierView = new UsrSupplierView();
      Integer usrSupplierPkey = GetValue.get(map,UsrSupplier.T.PKEY , Integer.class, 0);
      supplierView.setId(usrSupplierPkey);
      supplierView.setShowName(GetValue.get(map,UsrSupplier.T.SHOW_NAME , String.class, null));
      supplierView.setIsAuth(GetValue.get(map,UsrSupplier.T.IS_AUTH , Byte.class, (byte)0));
      supplierView.setLogo(GetValue.get(map,UsrSupplier.T.LOGO , String.class, null));
      supplierView.setMainSalesArea(GetValue.get(map,UsrSupplier.T.SHOW_NAME , String.class, null));
      //添加商品列表
      List<PdtProductView> pdtProductViews = new ArrayList<>();
      List<Map> pdtProducts = pdtProductDao.findBySupplier(usrSupplierPkey);
      for (Map map1 : pdtProducts) {
        PdtProductView pdtProductView = new PdtProductView();
        pdtProductView.setId(GetValue.get(map1 , PdtProduct.T.PKEY , Integer.class , 0));
        pdtProductView.setName(GetValue.get(map1 , PdtProduct.T.NAME , String.class , null));
        pdtProductView.setPicture(GetValue.get(map1 , PdtProduct.T.PICTURE , String.class , null));
        pdtProductViews.add(pdtProductView);
      }
      supplierView.setProducts(pdtProductViews);
      //将所有查找到的供应商的数据加入List
      supplierViews.add(supplierView);
      page = new Page(supplierViews , start , limit , usrSupplierDao.listSuppliers(null , null).size());
    }
    return page;
  }

  @Override
  public Integer isSupplier(String loginName) {
    return usrSupplierDao.isSupplier(loginName) ? 1 : 0;
  }
}
