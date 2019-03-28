package irille.Service.Pdt.Imp;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import irille.Aops.Caches;
import irille.Dao.PdtProductDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Pdt.IPdtProductService;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.ExhibitionSupplierView;
import irille.homeAction.pdt.dto.PdtExhibitionView;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.pub.Obj;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.RFQ.RFQPdtInfo;
import irille.view.pdt.PdtProductBaseInfoView;
import irille.view.pdt.PdtProductCatView;
import irille.view.pdt.PdtSearchView;
import irille.view.pub.PageSearch;
import irille.view.usr.MobilePdtView;
import irille.view.v2.Pdt.PdtNewPdtInfo;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/7 Time: 14:54 */
public class PdtProductServiceImp implements IPdtProductService {

  @Inject PdtProductDao pdtProductDao;

  @Inject O2OProductDao o2OProductDao;

  @Override
  public List<PdtProductBaseInfoView> getNewProductsListByIndex(IduPage iduPage) {
    List<Map> result =
        pdtProductDao.getNewProductsList(
            iduPage.getStart(), iduPage.getLimit(), iduPage.getWhere());
    return SetBeans.setList(fromPdtProductBaseInfoView(result), PdtProductBaseInfoView.class);
  }

  @Override
  public List<PdtProductBaseInfoView> getProductsIndexHot(IduPage iduPage) {
    List<Map> result = pdtProductDao.getProductsIndexHot(iduPage.getStart(), iduPage.getLimit());
    return SetBeans.setList(fromPdtProductBaseInfoView(result), PdtProductBaseInfoView.class);
  }

  @Override
  public Map getProductListByCategory(
      IduPage page,
      String[] flds,
      boolean order,
      int category,
      String spec,
      String onlyFld,
      String keyword,
      Integer type) {
    PdtProductView pdtProductView = new PdtProductView();
    pdtProductView
        .setPage(page)
        .setFlds(flds)
        .setOrder(order)
        .setCategory(category)
        .setSpec(spec)
        .setKeyword(keyword);
    if (type == null) type = 0;
    switch (type) {
      case 0:
        pdtProductView.setProductType(Pdt.OProductType.GENERAL);
        break;
      case 1:
        pdtProductView.setProductType(Pdt.OProductType.GROUP);
        break;
    }
    if (onlyFld != null) {
      try {
        pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
      } catch (Exception e) {

      }
    }
    /**
     * @Description: 现在转变分页逻辑 不再是 页面数
     *
     * @date 2018/11/8 17:51
     * @author lijie@shoestp.cn
     */
    if (page.getStart() > 0) {
      page.setStart(page.getStart() - 1);
    }
    page.setStart(page.getStart() * page.getLimit());
    return pdtProductDao.getProductList(pdtProductView);
  }

  @Override
  public List<PdtProductBaseInfoView> getYouMayLike(IduPage iduPage, int cat) {
    List<Map<String, Object>> result = pdtProductDao.getYouMayLike(cat, null);
    return result.stream()
        .map(
            bean -> {
              PdtProductBaseInfoView pdtProductBaseInfoView = new PdtProductBaseInfoView();
              pdtProductBaseInfoView.setRewrite(
                  SEOUtils.getPdtProductTitle(
                      GetValue.get(bean, PdtProduct.T.PKEY, Integer.class, 0),
                      GetValue.get(bean, PdtProduct.T.NAME, String.class, "{}")));
              translateUtil.getLanguage(bean, HomeAction.curLanguage());
              pdtProductBaseInfoView.setId(GetValue.get(bean, PdtProduct.T.PKEY, Integer.class, 0));
              pdtProductBaseInfoView.setPrice(
                  GetValue.get(bean, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO));
              pdtProductBaseInfoView.setImage(
                  GetValue.get(bean, PdtProduct.T.PICTURE, String.class, ""));
              pdtProductBaseInfoView.setName(
                  GetValue.get(bean, PdtProduct.T.NAME, String.class, ""));
              return pdtProductBaseInfoView;
            })
        .collect(Collectors.toList());
  }

  /**
   * @Description: 随机商品
   *
   * <p>查出所有所有的商品,然后随机数去取
   *
   * @date 2019年3月19日 16点31分
   * @author lijie@shoestp.cn
   */
  @Override
  public List<PdtNewPdtInfo> getRandomPdt(Integer limit, int cat, UsrPurchase purchase) {
    List<PdtNewPdtInfo> resultSet = new ArrayList<>();
    SecureRandom secureRandom = new SecureRandom();
    for (Map<String, Object> map :
        pdtProductDao.getYouMayLike(
            secureRandom.nextInt(pdtProductDao.getPdtCount()),
            100,
            cat,
            purchase == null ? null : purchase.getPkey())) {
      PdtNewPdtInfo info = new PdtNewPdtInfo();
      info.setId(Long.valueOf(GetValue.get(map, PdtProduct.T.PKEY, Integer.class, 0)));
      info.setTitle(GetValue.get(map, PdtProduct.T.NAME, String.class, ""));
      info.setImage(
          GetValue.getFirstImage(GetValue.get(map, PdtProduct.T.PICTURE, String.class, "")));
      info.setPrice(GetValue.get(map, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO));
      info.setMin_order(GetValue.get(map, PdtProduct.T.MIN_OQ, Integer.class, 0));
      Integer integer = GetValue.get(map, "isFavorite", Integer.class, 0);
      info.setFavorite(integer > 0);
      info.setRewrite(
          SEOUtils.getPdtProductTitle(
              GetValue.get(map, PdtProduct.T.PKEY, Integer.class, 0),
              GetValue.get(map, PdtProduct.T.NAME, String.class, "")));
      resultSet.add(info);
    }
    List result = new ArrayList();
    List<Integer> list = new ArrayList();
    while (true) {
      Integer i = secureRandom.nextInt(resultSet.size());
      if (!list.contains(i)) {
        result.add(resultSet.get(i));
      }
      if (result.size() >= limit) break;
    }
    return result;
  }

  @Caches
  @Override
  public List<PdtProductCatView> getProductsIndexCategories(
      int start, int limit, FldLanguage.Language language) {
    return pdtProductDao.getCatChildNodesByCatId(0, language);
  }

  @Override
  public List<PdtNewPdtInfo> getNewProducts(
      IduPage page, UsrPurchase pkey, FldLanguage.Language language) {
    int start = page.getStart();
    int limit = page.getLimit() == 0 ? 10 : page.getLimit();
    List<Map<String, Object>> result;
    if (pkey == null) result = pdtProductDao.getNewProductsAndFavoritesInfoList(start, limit, null);
    else result = pdtProductDao.getNewProductsAndFavoritesInfoList(start, limit, pkey.getPkey());
    List<PdtNewPdtInfo> list = new ArrayList();
    for (Map<String, Object> stringObjectMap : result) {
      PdtNewPdtInfo pdtInfo = new PdtNewPdtInfo();
      pdtInfo.setRewrite(
          SEOUtils.getPdtProductTitle(
              Integer.parseInt(String.valueOf(stringObjectMap.get("id"))),
              String.valueOf(stringObjectMap.get("name"))));
      pdtInfo.setId(Long.valueOf(String.valueOf(stringObjectMap.get("id"))));
      pdtInfo.setTitle(GetValue.get(stringObjectMap, "name", String.class, ""));
      String[] string = String.valueOf(stringObjectMap.get("image")).split(",");
      if (string != null && string.length > 0) {
        pdtInfo.setImage(string[0]);
      }
      pdtInfo.setPrice(GetValue.get(stringObjectMap, "price", BigDecimal.class, BigDecimal.ZERO));
      pdtInfo.setMin_order(GetValue.get(stringObjectMap, "min_oq", Integer.class, 0));
      if (stringObjectMap.get("ismyfavorite") != null
          && !stringObjectMap.get("ismyfavorite").toString().equalsIgnoreCase("false")) {
        pdtInfo.setFavorite(true);

      } else {
        pdtInfo.setFavorite(false);
      }
      list.add(pdtInfo);
    }
    return list;
  }

  // TODO 变成DTO返回
  @Override
  public Map getProductListByCategoryV2(
      IduPage iduPage,
      String[] orderfld,
      boolean order,
      int cated,
      String spec,
      String onlyFld,
      String keyword,
      Integer type) {
    PdtProductView pdtProductView = new PdtProductView();
    pdtProductView
        .setPage(iduPage)
        .setFlds(orderfld)
        .setOrder(order)
        .setCategory(cated)
        .setSpec(spec)
        .setKeyword(keyword);
    pdtProductView.setProductType(Pdt.OProductType.GENERAL);
    if (onlyFld != null) {
      try {
        pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
      } catch (Exception e) {

      }
    }
    /**
     * @Description: 现在转变分页逻辑 不再是 页面数
     *
     * @date 2018/11/8 17:51
     * @author lijie@shoestp.cn
     */
    if (iduPage.getStart() > 0) {
      iduPage.setStart(iduPage.getStart() - 1);
    }
    iduPage.setStart(iduPage.getStart() * iduPage.getLimit());
    if (type == 1) {
      return pdtProductDao.getProductListV2(pdtProductView);
    }
    return pdtProductDao.getProductList(pdtProductView);
  }

  @Override
  public RFQPdtInfo getInquiryPdtInfo(Integer id) {
    Map<String, Object> map = pdtProductDao.getInquiryPdtInfo(id);
    RFQPdtInfo rfqPdtInfo = new RFQPdtInfo();
    rfqPdtInfo.setId(GetValue.get(map, PdtProduct.T.PKEY, Integer.class, -1));
    rfqPdtInfo.setTitle(
        translateUtil.getLanguage(
            GetValue.get(map, PdtProduct.T.NAME, String.class, null), HomeAction.curLanguage()));
    rfqPdtInfo.setSupName(GetValue.get(map, "supName", String.class, null));
    rfqPdtInfo.setSupLogo(GetValue.get(map, UsrSupplier.T.LOGO, String.class, null));
    rfqPdtInfo.setType(GetValue.get(map, PdtProduct.T.PRODUCT_TYPE, Byte.class, (byte) -1));
    rfqPdtInfo.setMin_oq(GetValue.get(map, PdtProduct.T.MIN_OQ, Integer.class, 0));
    String[] images = GetValue.get(map, PdtProduct.T.PICTURE, String.class, "").split(",");
    if (images.length > 0) {
      rfqPdtInfo.setImage(images[0]);
    } else {
      rfqPdtInfo.setImage(GetValue.get(map, PdtProduct.T.PICTURE, String.class, ""));
    }
    rfqPdtInfo.setSuplevel(
        String.valueOf(GetValue.get(map, UsrSupplier.T.ROLE, Integer.class, -1)));
    return rfqPdtInfo;
  }

  private List fromPdtProductBaseInfoView(List<Map> result) {
    return result.stream()
        .map(
            o -> {
              String name = String.valueOf(o.get("name"));
              if (name != null && name.length() > 0) {
                try {
                  o.put(
                      "rewrite",
                      SEOUtils.getPdtProductTitle(
                          Integer.parseInt(String.valueOf(o.get("pkey"))), name));
                  o.put("name", translateUtil.getLanguage(name, HomeAction.curLanguage()));
                } catch (Exception e) {
                  e.getStackTrace();
                }
              }
              return o;
            })
        .collect(Collectors.toList());
  }

  /**
   * 获取私人展会商品信息
   *
   * @param page
   * @return
   */
  public List<PdtExhibitionView> findExhibitionGoods(IduPage page) {
    int start = page.getStart();
    int limit = page.getLimit() == 0 ? 10 : page.getLimit();
    String where = " 3";
    List<PdtExhibitionView> exhibitionGoods =
        pdtProductDao.getExhibitionProductsList(start, limit, where).stream()
            .map(
                goods -> {
                  PdtExhibitionView view = new PdtExhibitionView();
                  ExhibitionSupplierView supplierView = new ExhibitionSupplierView();
                  view.setId(goods.getPkey());
                  view.setImg(goods.getPicture());
                  view.setPrice(goods.getCurPrice());
                  view.setStartQuantity(goods.getMinOq());
                  view.setTitle(goods.getName());
                  supplierView.setId(goods.gtSupplier().getPkey());
                  supplierView.setName(goods.gtSupplier().getName());
                  supplierView.setIsCertificate(goods.gtSupplier().getIsAuth());
                  supplierView.setRegion(goods.gtSupplier().getCompanyAddr());
                  view.setSupplier(supplierView);
                  return view;
                })
            .collect(Collectors.toList());
    return exhibitionGoods;
  }

  /**
   * xy -pc商城端新搜索商品功能
   *
   * @return
   */
  @Override
  public PageSearch searchPdt(
      String[] orderfld,
      UsrPurchase purchase,
      Integer supplier,
      FldLanguage.Language curLanguage,
      Integer lose,
      String pName,
      Integer cate,
      Integer level,
      String export,
      Integer mOrder,
      BigDecimal min,
      BigDecimal max,
      Integer IsO2o,
      String o2oAddress,
      Integer start,
      Integer limit) {

    PageSearch pageSearch =
        new PageSearch(
            pdtProductDao.searchPdtByQuery(
                orderfld,
                purchase,
                supplier,
                curLanguage,
                lose,
                pName,
                cate,
                level,
                export,
                mOrder,
                min,
                max,
                IsO2o,
                o2oAddress,
                start,
                limit));
    List<PdtSearchView> data = pageSearch.getItems();
    List<PdtSearchView> result =
        data.stream()
            .filter(
                o -> {
                  if (o.getPdtType()
                      .equals(Integer.valueOf(Pdt.OProductType.O2O.getLine().getKey()))) {
                    List<O2O_Product> o2os = o2OProductDao.findAllByProd_Pkey(o.getPdtId());
                    if (o2os != null && o2os.size() > 0) {
                      o.setPdtType(Integer.valueOf(Pdt.OProductType.O2O.getLine().getKey()));
                    } else {
                      o.setPdtType(Integer.valueOf(Pdt.OProductType.GENERAL.getLine().getKey()));
                    }
                  }
                  return true;
                })
            .collect(Collectors.toList());
    pageSearch.setItems(result);
    if (cate != null && cate > 0) pageSearch.setBreadcrumbnav(pdtProductDao.getBreadcrumbNav(cate));
    return pageSearch;
  }
  /**
   * 获取手机端商家产品列表
   *
   * @author GS
   */
  @Override
  public Page findPdtList(Integer pkey, Integer start, Integer limit, Integer checkType) {
    System.out.println(pkey);
    List<MobilePdtView> views = new ArrayList<>();
    List<Map<String, Object>> map =
        pdtProductDao.findPdtListBySupplier(pkey, start, limit, checkType);

    for (Map<String, Object> pdt : map) {
      MobilePdtView view = new MobilePdtView();
      view.setPkey(GetValue.get(pdt, PdtProduct.T.PKEY, Integer.class, 0));
      view.setName(GetValue.get(pdt, PdtProduct.T.NAME, String.class, null));
      view.setPicture(GetValue.get(pdt, PdtProduct.T.PICTURE, String.class, null));
      view.setMinPrice(GetValue.get(pdt, "minPrice", BigDecimal.class, null));
      view.setMaxPrice(GetValue.get(pdt, "maxPrice", BigDecimal.class, null));
      view.setArtNo(GetValue.get(pdt, PdtProduct.T.CODE, String.class, null));
      views.add(view);
    }

    return new Page<>(views, start, limit, pdtProductDao.count(pkey, start, limit, checkType));
  }
}
