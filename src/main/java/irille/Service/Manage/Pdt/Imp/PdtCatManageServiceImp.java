package irille.Service.Manage.Pdt.Imp;

import irille.Dao.PdtProductCatDao;
import irille.Service.Manage.Pdt.IPdtCatManageService;
import irille.pub.bean.BeanBase;
import irille.pub.tb.FldLanguage;
import irille.sellerAction.view.ProductSEOsView;
import irille.shop.pdt.PdtProduct;
import irille.view.Page;
import irille.view.pdt.CategoryView;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
        pdtProductCatDao
            .getProductSEOs(supplier)
            .stream()
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
      return pdtProductCatDao.pList().stream().map(cat->{
        CategoryView view = new CategoryView();
        view.setId(cat.getPkey());
        view.setName(cat.getName());
        return view;
      }).collect(Collectors.toList());
    }
}
