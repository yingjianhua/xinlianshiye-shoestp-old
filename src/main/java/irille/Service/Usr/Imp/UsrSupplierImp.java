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
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.usr.UsrSupplierInfoView;
import irille.view.usr.UsrshopSettingView;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 16:25
 */
public class UsrSupplierImp implements IUsrSupplierService {

  @Inject
  UsrSupplierDao usrSupplierDao;
  @Inject
  PdtProductDao pdtProductDao;

  @Override
  public UsrSupplierInfoView getInfoById(int i) {
    Map map = usrSupplierDao.getInfoById(i);
    return SetBeans.set(map, UsrSupplierInfoView.class);
  }

  @Override
  public UsrshopSettingView getSettingInfoById(int i) {
    UsrSupplierImp imp = new UsrSupplierImp();
    UsrSupplier us = usrSupplierDao.getUsrShopSetting(i);
    UsrshopSettingView ssv = new UsrshopSettingView();
    ssv.setLogo(us.getLogo());
    ssv.setSignBackGD(us.getSignBackgd());
    ssv.setAdPhoto(imp.isJson(us.getAdPhoto()).toString());
    ssv.setAdPhotoLink(imp.isJson(us.getAdPhotoLink()).toString());
    ssv.setCompanyPhoto(imp.isJson(us.getCompanyPhoto()).toString());
    ssv.setCompanyPhotoLink(imp.isJson(us.getCompanyPhotoLink()).toString());
    ssv.setHomePageDIY(imp.isJson(us.getHomePageDiy()).toString());
    ssv.setHomePageOn(us.getHomePageOn());
    return ssv;
  }

  private JSONObject isJson(String str) {
    UsrSupplierImp imp = new UsrSupplierImp();
    JSONObject json = new JSONObject();
    Language[] lang = Language.values();
    if (!(imp.idJson(str))) {
      for (Language language : lang) {
        try {
          if (str.isEmpty() || str == null) {
            json.put(language.toString(), " ");
          } else {
            json.put(language.toString(), str);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
      return json;
    }
    try {
      json = new JSONObject(str);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return json;
  }

  private boolean idJson(String str) {
    try {
      JSONObject json = new JSONObject(str);
      return true;
    } catch (JSONException e) {
      return false;
    }
  }

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

  public void updShopSetting(int i, UsrshopSettingView bean) {
    UsrSupplier user = usrSupplierDao.getUsrShopSetting(i);
    System.out.println(bean);
    user.setLogo(bean.getLogo());
    user.setSignBackgd(bean.getSignBackGD());
  }
}
