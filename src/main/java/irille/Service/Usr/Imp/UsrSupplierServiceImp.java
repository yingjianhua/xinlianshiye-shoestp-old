package irille.Service.Usr.Imp;

import irille.Dao.PdtProductDao;
import irille.Dao.UsrSupplierDao;
import irille.Service.Usr.IUsrSupplierService;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.FavoritesView;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrFavorites;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

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
    sql.select(UsrFavorites.T.PKEY, UsrFavorites.T.PRODUCT, PdtProduct.T.PRODUCT_TYPE,
        PdtProduct.T.NAME,
        PdtProduct.T.PICTURE, PdtProduct.T.CUR_PRICE

    ).eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
      if (s == null) {
        return false;
      }
      return s.intValue() > 0 ? true : false;
    }).page(page).leftjoin(PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
        .eqAutoAnd(UsrFavorites.T.SHOW_STATE,
            showState.getLine().getKey());
    sql.in(PdtProduct.T.CATEGORY, pdtProductDao.getCatsNodeByCatId(cat), s -> {
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
    sql.select(UsrFavorites.T.PKEY, UsrFavorites.T.PRODUCT, PdtProduct.T.NAME, PdtProduct.T.PICTURE,
        PdtProduct.T.CUR_PRICE

    ).eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
      if (s == null) {
        return false;
      }
      return s.intValue() > 0 ? true : false;
    }).page(page).leftjoin(PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
        .eqAutoAnd(UsrFavorites.T.SHOW_STATE,
            showState.getLine().getKey());
    if (cat > 0) {
      sql.in(PdtProduct.T.CATEGORY, pdtProductDao.getCatsNodeByCatId(cat), s -> {
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


}
