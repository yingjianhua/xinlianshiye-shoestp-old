package irille.Service.Manage.Pdt.Imp;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import irille.Dao.PdtProductCatDao;
import irille.Service.Manage.Pdt.IPdtCatManageService;
import irille.platform.pdt.View.pdtCatView.PdtCatView;
import irille.pub.bean.BeanBase;
import irille.pub.tb.FldLanguage;
import irille.sellerAction.view.ProductSEOsView;
import irille.shop.pdt.PdtProduct;
import irille.view.Page;
import irille.view.pdt.CategoryView;
import irille.view.pdt.PdtProductCatView;
import org.json.JSONException;
import org.json.JSONObject;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time: 11:32 */
public class PdtCatManageServiceImp implements IPdtCatManageService {
  @Inject PdtProductCatDao pdtProductCatDao;

  @Override
  public Page getProductCatList(int start, int limit, int supplierId) {
    //        for (Map map : pdtProductCatDao.getPdtCatBySupplierId(supplierId)) {
    //
    //        }
    //        List<PdtCatsView> list =
    // pdtProductCatDao.getPdtCatBySupplierId(supplierId).stream().map(map -> {
    //            PdtCatsView pdtCatsView = new PdtCatsView();
    //            return SetBeans.set(map, PdtCatsView.class);
    //        }
    return null;
  }

  @Override
  public Page getProductSEOs(
      Integer statr, Integer limit, Integer supplier, FldLanguage.Language language) {
    List<ProductSEOsView> list =
        pdtProductCatDao.getProductSEOs(supplier).stream()
            .map(
                o -> {
                  JSONObject json;
                  ProductSEOsView view = new ProductSEOsView();
                  view.setProductId(
                      Integer.valueOf(
                          String.valueOf(o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()))));
                  try {
                    if (String.valueOf(o.get(PdtProduct.T.SEO_TITLE.getFld().getCodeSqlField()))
                        != null) {
                      json =
                          new JSONObject(
                              String.valueOf(
                                  o.get(PdtProduct.T.SEO_TITLE.getFld().getCodeSqlField())));
                      if (json.get(language.toString()) != null) {
                        view.setSeo_title(json.get(language.toString()).toString());
                      } else {
                        view.setSeo_title(json.get(FldLanguage.Language.en.toString()).toString());
                      }
                    }
                    if (String.valueOf(o.get(PdtProduct.T.SEO_KEYWORD.getFld().getCodeSqlField()))
                        != null) {
                      json =
                          new JSONObject(
                              String.valueOf(
                                  o.get(PdtProduct.T.SEO_KEYWORD.getFld().getCodeSqlField())));
                      if (json.get(language.toString()) != null) {
                        view.setSeo_keyword(json.get(language.toString()).toString());
                      } else {
                        view.setSeo_keyword(
                            json.get(FldLanguage.Language.en.toString()).toString());
                      }
                    }
                    if (String.valueOf(
                            o.get(PdtProduct.T.SEO_DESCRIPTION.getFld().getCodeSqlField()))
                        != null) {
                      json =
                          new JSONObject(
                              String.valueOf(
                                  o.get(PdtProduct.T.SEO_DESCRIPTION.getFld().getCodeSqlField())));
                      if (json.get(language.toString()) != null) {
                        view.setSeo_description(json.get(language.toString()).toString());
                      } else {
                        view.setSeo_description(
                            json.get(FldLanguage.Language.en.toString()).toString());
                      }
                    }
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                  return view;
                })
            .collect(Collectors.toList());
    Integer count = pdtProductCatDao.getProductSEOs(supplier).size();
    return new Page(list, statr, limit, count);
  }

  @Override
  public ProductSEOsView getSEO(Integer product) {
    PdtProduct obj = (PdtProduct) pdtProductCatDao.getSEO(product);
    ProductSEOsView view = new ProductSEOsView();
    view.setProductId(product);
    view.setSeo_title(obj.getSeoTitle());
    view.setSeo_keyword(obj.getSeoKeyword());
    view.setSeo_description(obj.getSeoDescription());
    return view;
  }

  @Override
  public void updSEO(ProductSEOsView view) {
    PdtProduct pp = BeanBase.load(PdtProduct.class, view.getProductId());
    pp.setSeoTitle(view.getSeo_title());
    pp.setSeoKeyword(view.getSeo_keyword());
    pp.setSeoDescription(view.getSeo_description());
    pp.upd();
  }

  @Override
  public List<CategoryView> pList() {
    return pdtProductCatDao.pList().stream()
        .map(
            cat -> {
              CategoryView view = new CategoryView();
              view.setId(cat.getPkey());
              view.setName(cat.getName());
              return view;
            })
        .collect(Collectors.toList());
  }

  @Override
  public List<PdtCatView> findCategory(Integer id) {
    return pdtProductCatDao.findCategory(id).stream()
        .map(
            cat -> {
              PdtCatView view = new PdtCatView();
              view.setId(cat.getPkey());
              view.setName(cat.getName());
              view.setCategoryId(cat.getCategoryUp());
              view.setProductImage(cat.getProductImage());
              view.setDisplay(cat.getDisplay());
              List<PdtCatView> category = findCategory(cat.getPkey());
              if (category.size() > 0) {
                view.setChildren(category);
              }
              return view;
            })
        .collect(Collectors.toList());
  }

    @Override
    public List<PdtProductCatView> findCategoryCascader(Integer id) {
        return pdtProductCatDao.findCategory(id).stream()
                .map(
                        cat -> {
                            PdtProductCatView view = new PdtProductCatView();
                            view.setValue(cat.getPkey());
                            try {
                                view.setLabel(cat.getName(FldLanguage.Language.zh_CN));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            List<PdtProductCatView> category = findCategoryCascader(cat.getPkey());
                            if (category.size() > 0) {
                                view.setChildren(category);
                            }
                            return view;
                        })
                .collect(Collectors.toList());
    }

}
