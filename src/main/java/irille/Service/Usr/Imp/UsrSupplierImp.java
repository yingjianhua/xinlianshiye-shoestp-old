package irille.Service.Usr.Imp;

import irille.Dao.PdtProductDao;
import irille.Dao.UsrSupplierDao;
import irille.Service.Usr.IUsrSupplierService;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.FavoritesView;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:25
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
		UsrSupplier us = usrSupplierDao.getUsrShopSetting(i);
		UsrshopSettingView ssv = new UsrshopSettingView();
		ssv.setLogo(us.getLogo());
		ssv.setSignBackGD(us.getSignBackgd());
		Map<String, String[]> AdPhotoMap = new HashMap<>();
		if (us.getAdPhoto() != null && us.getAdPhoto().length() > 0) {
			try {
				new JSONObject().getJSONObject(us.getAdPhoto());
			} catch (JSONException e) {
				String[] str = us.getAdPhoto().split(",");
				for (int k = 0; k < Language.values().length; k++) {
					AdPhotoMap.put(Language.values()[k].toString(), str);
				}
				ssv.setAdPhoto(AdPhotoMap);
			}
		} else {
			String[] str = { " " };
			for (int k = 0; k < Language.values().length; k++) {
				AdPhotoMap.put(Language.values()[k].toString(), str);
			}
			ssv.setAdPhoto(AdPhotoMap);
		}

		Map<String, String[]> AdPhotoLinkMap = new HashMap<>();
		if (us.getAdPhotoLink() != null && us.getAdPhotoLink().length() > 0) {
			try {
				new JSONObject().getJSONObject(us.getAdPhotoLink());
			} catch (JSONException e) {
				String[] str = us.getAdPhotoLink().split(",");
				for (int k = 0; k < Language.values().length; k++) {
					AdPhotoLinkMap.put(Language.values()[k].toString(), str);
				}
				ssv.setAdPhotoLink(AdPhotoLinkMap);
			}
		} else {
			String[] str = { " " };
			for (int k = 0; k < Language.values().length; k++) {
				AdPhotoLinkMap.put(Language.values()[k].toString(), str);
			}
			ssv.setAdPhotoLink(AdPhotoLinkMap);
		}

		Map<String, String[]> CompanyPhotoMap = new HashMap<>();
		if (us.getCompanyPhoto() != null && us.getCompanyPhoto().length() > 0) {
			try {
				new JSONObject().getJSONObject(us.getCompanyPhoto());
			} catch (JSONException e) {
				String[] str = us.getCompanyPhoto().split(",");
				for (int k = 0; k < Language.values().length; k++) {
					CompanyPhotoMap.put(Language.values()[k].toString(), str);
				}
				ssv.setCompanyPhoto(CompanyPhotoMap);
			}
		} else {
			String[] str = { " " };
			for (int k = 0; k < Language.values().length; k++) {
				CompanyPhotoMap.put(Language.values()[k].toString(), str);
			}
			ssv.setCompanyPhoto(CompanyPhotoMap);
		}

		Map<String, String[]> CompanyPhotoLinkMap = new HashMap<>();
		if (us.getCompanyPhotoLink() != null && us.getCompanyPhotoLink().length() > 0) {
			try {
				new JSONObject().getJSONObject(us.getCompanyPhoto());
			} catch (JSONException e) {
				String[] str = us.getCompanyPhotoLink().split(",");
				for (int k = 0; k < Language.values().length; k++) {
					CompanyPhotoLinkMap.put(Language.values()[k].toString(), str);
				}
				ssv.setCompanyPhotoLink(CompanyPhotoLinkMap);
			}
		} else {
			String[] str = { " " };
			for (int k = 0; k < Language.values().length; k++) {
				CompanyPhotoLinkMap.put(Language.values()[k].toString(), str);
			}
			ssv.setCompanyPhotoLink(CompanyPhotoLinkMap);
		}

		ssv.setHomePageDIY(us.getHomePageDiy());
		ssv.setHomePageOn(us.getHomePageOn());
		return ssv;
	}


    public List<FavoritesView> getFavoritesListByCat(IduPage page, int cat, int purId, Sys.OYn showState) {
        FormaterSql sql = FormaterSql.build(this);
        sql.select(
                UsrFavorites.T.PKEY,
                UsrFavorites.T.PRODUCT,
                PdtProduct.T.PRODUCT_TYPE,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE

        )
                .eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
                    if (s == null)
                        return false;
                    return s.intValue() > 0 ? true : false;
                }).page(page)
                .leftjoin(PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
                .eqAutoAnd(UsrFavorites.T.SHOW_STATE, showState.getLine().getKey())
        ;
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
                view.setGroupLine(sql.castInt(BeanBase.queryOneRowIsNull(sql.buildSql(), view.getPdtPkey())));
            }
            result.add(view);
        });
        return result;
    }

    public Long getFavoritesCountByCat(IduPage page, int cat, int purId, Sys.OYn showState) {
        FormaterSql sql = FormaterSql.build(this);
        sql.select(
                UsrFavorites.T.PKEY,
                UsrFavorites.T.PRODUCT,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE

        )
                .eqAutoAnd(UsrFavorites.T.PURCHASE, purId, s -> {
                    if (s == null)
                        return false;
                    return s.intValue() > 0 ? true : false;
                }).page(page)
                .leftjoin(PdtProduct.T.PKEY, UsrFavorites.T.PRODUCT)
                .eqAutoAnd(UsrFavorites.T.SHOW_STATE, showState.getLine().getKey())
        ;
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
	public List<SupplierListView> getSupplierListAndPdtList(IduPage iduPage) {
		return usrSupplierDao.getSupplierListAndPdtList(iduPage);
	}
}
